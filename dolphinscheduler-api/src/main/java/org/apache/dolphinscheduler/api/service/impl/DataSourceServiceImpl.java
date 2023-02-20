/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.dolphinscheduler.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.dolphinscheduler.api.enums.Status;
import org.apache.dolphinscheduler.api.exceptions.ServiceException;
import org.apache.dolphinscheduler.api.service.DataSourceService;
import org.apache.dolphinscheduler.api.service.TableInfosService;
import org.apache.dolphinscheduler.api.utils.PageInfo;
import org.apache.dolphinscheduler.api.utils.Result;
import org.apache.dolphinscheduler.api.vo.DataSourceTablesVO;
import org.apache.dolphinscheduler.common.Constants;
import org.apache.dolphinscheduler.common.utils.JSONUtils;
import org.apache.dolphinscheduler.dao.entity.DataSource;
import org.apache.dolphinscheduler.dao.entity.TableInfos;
import org.apache.dolphinscheduler.dao.entity.User;
import org.apache.dolphinscheduler.dao.mapper.DataSourceMapper;
import org.apache.dolphinscheduler.dao.mapper.DataSourceUserMapper;
import org.apache.dolphinscheduler.plugin.datasource.api.datasource.BaseDataSourceParamDTO;
import org.apache.dolphinscheduler.plugin.datasource.api.plugin.DataSourceClientProvider;
import org.apache.dolphinscheduler.plugin.datasource.api.utils.DataSourceUtils;
import org.apache.dolphinscheduler.spi.datasource.BaseConnectionParam;
import org.apache.dolphinscheduler.spi.datasource.ConnectionParam;
import org.apache.dolphinscheduler.spi.enums.DbType;
import org.apache.dolphinscheduler.spi.params.base.ParamsOptions;
import org.apache.dolphinscheduler.spi.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * data source service impl
 */
@Service
public class DataSourceServiceImpl extends BaseServiceImpl implements DataSourceService {
    
    private static final Logger logger = LoggerFactory.getLogger(DataSourceServiceImpl.class);
    
    @Autowired
    private DataSourceMapper dataSourceMapper;
    
    @Autowired
    private DataSourceUserMapper datasourceUserMapper;
    
    private static final String REMARKS = "REMARKS";
    
    private static final String TABLE = "TABLE";
    private static final String VIEW = "VIEW";
    private static final String[] TABLE_TYPES = new String[]{TABLE, VIEW};
    private static final String TABLE_NAME = "TABLE_NAME";
    private static final String TABLE_TYPE = "TABLE_TYPE";
    private static final String REF_GENERATION = "REF_GENERATION";
    private static final String TABLE_CAT = "TABLE_CAT";
    private static final String TABLE_SCHEM = "TABLE_SCHEM";
    private static final String TYPE_CAT = "TYPE_CAT";
    private static final String TYPE_NAME = "TYPE_NAME";
    private static final String TYPE_SCHEM = "TYPE_SCHEM";
    @Resource
    private TableInfosService tableInfosService;
    private static final String COLUMN_NAME = "COLUMN_NAME";
    
    /**
     * create data source
     *
     * @param loginUser       login user
     * @param datasourceParam datasource parameters
     * @return create result code
     */
    @Override
    @Transactional
    public Result<Object> createDataSource(User loginUser, BaseDataSourceParamDTO datasourceParam) {
        DataSourceUtils.checkDatasourceParam(datasourceParam);
        Result<Object> result = new Result<>();
        // check name can use or not
        if (checkName(datasourceParam.getName())) {
            putMsg(result, Status.DATASOURCE_EXIST);
            return result;
        }
        // check connect
        ConnectionParam connectionParam = DataSourceUtils.buildConnectionParams(datasourceParam);
        Result<Object> isConnection = checkConnection(datasourceParam.getType(), connectionParam);
        if (Status.SUCCESS.getCode() != isConnection.getCode()) {
            putMsg(result, Status.DATASOURCE_CONNECT_FAILED);
            return result;
        }

        // build datasource
        DataSource dataSource = new DataSource();
        Date now = new Date();

        dataSource.setName(datasourceParam.getName().trim());
        dataSource.setNote(datasourceParam.getNote());
        dataSource.setUserId(loginUser.getId());
        dataSource.setUserName(loginUser.getUserName());
        dataSource.setType(datasourceParam.getType());
        dataSource.setConnectionParams(JSONUtils.toJsonString(connectionParam));
        dataSource.setCreateTime(now);
        dataSource.setUpdateTime(now);
        try {
            dataSourceMapper.insert(dataSource);
            putMsg(result, Status.SUCCESS);
        } catch (DuplicateKeyException ex) {
            logger.error("Create datasource error.", ex);
            putMsg(result, Status.DATASOURCE_EXIST);
        }
        List<DataSourceTablesVO> dataSourceTablesVOS = this.getTableInfos(dataSource.getId());
        //初始化数据源表数据
        //初始化数据源表数据
        initTableInfos(dataSourceTablesVOS,dataSource.getId());
        return result;
    }

    /**
     * updateProcessInstance datasource
     *
     * @param loginUser login user
     * @param id data source id
     * @return update result code
     */
    @Override
    public Result<Object> updateDataSource(int id, User loginUser, BaseDataSourceParamDTO dataSourceParam) {
        DataSourceUtils.checkDatasourceParam(dataSourceParam);
        Result<Object> result = new Result<>();
        // determine whether the data source exists
        DataSource dataSource = dataSourceMapper.selectById(id);
        if (dataSource == null) {
            putMsg(result, Status.RESOURCE_NOT_EXIST);
            return result;
        }

        if (!canOperator(loginUser, dataSource.getUserId())) {
            putMsg(result, Status.USER_NO_OPERATION_PERM);
            return result;
        }

        //check name can use or not
        if (!dataSource.getName().trim().equals(dataSource.getName()) && checkName(dataSource.getName())) {
            putMsg(result, Status.DATASOURCE_EXIST);
            return result;
        }
        //check password，if the password is not updated, set to the old password.
        BaseConnectionParam connectionParam = (BaseConnectionParam) DataSourceUtils.buildConnectionParams(dataSourceParam);
        String password = connectionParam.getPassword();
        if (StringUtils.isBlank(password)) {
            String oldConnectionParams = dataSource.getConnectionParams();
            ObjectNode oldParams = JSONUtils.parseObject(oldConnectionParams);
            connectionParam.setPassword(oldParams.path(Constants.PASSWORD).asText());
        }

        Result<Object> isConnection = checkConnection(dataSource.getType(), connectionParam);
        if (isConnection.isFailed()) {
            return isConnection;
        }

        Date now = new Date();

        dataSource.setName(dataSourceParam.getName().trim());
        dataSource.setNote(dataSourceParam.getNote());
        dataSource.setUserName(loginUser.getUserName());
        dataSource.setType(dataSource.getType());
        dataSource.setConnectionParams(JSONUtils.toJsonString(connectionParam));
        dataSource.setUpdateTime(now);
        try {
            dataSourceMapper.updateById(dataSource);
            putMsg(result, Status.SUCCESS);
        } catch (DuplicateKeyException ex) {
            logger.error("Update datasource error.", ex);
            putMsg(result, Status.DATASOURCE_EXIST);
        }
        return result;
    }

    private boolean checkName(String name) {
        List<DataSource> queryDataSource = dataSourceMapper.queryDataSourceByName(name.trim());
        return queryDataSource != null && !queryDataSource.isEmpty();
    }

    /**
     * updateProcessInstance datasource
     *
     * @param id datasource id
     * @return data source detail
     */
    @Override
    public Map<String, Object> queryDataSource(int id) {

        Map<String, Object> result = new HashMap<>();
        DataSource dataSource = dataSourceMapper.selectById(id);
        if (dataSource == null) {
            putMsg(result, Status.RESOURCE_NOT_EXIST);
            return result;
        }
        // type
        BaseDataSourceParamDTO baseDataSourceParamDTO = DataSourceUtils.buildDatasourceParamDTO(
                dataSource.getType(), dataSource.getConnectionParams());
        baseDataSourceParamDTO.setId(dataSource.getId());
        baseDataSourceParamDTO.setName(dataSource.getName());
        baseDataSourceParamDTO.setNote(dataSource.getNote());

        result.put(Constants.DATA_LIST, baseDataSourceParamDTO);
        putMsg(result, Status.SUCCESS);
        return result;
    }

    /**
     * query datasource list by keyword
     *
     * @param loginUser login user
     * @param searchVal search value
     * @param pageNo page number
     * @param pageSize page size
     * @return data source list page
     */
    @Override
    public Result queryDataSourceListPaging(User loginUser, String searchVal, Integer pageNo, Integer pageSize) {
        Result result = new Result();
        IPage<DataSource> dataSourceList;
        Page<DataSource> dataSourcePage = new Page<>(pageNo, pageSize);
    
        if (isAdmin(loginUser)) {
            dataSourceList = dataSourceMapper.selectPaging(dataSourcePage, 0, searchVal);
        } else {
            dataSourceList = dataSourceMapper.selectPaging(dataSourcePage, loginUser.getId(), searchVal);
        }
        Map<Integer, List<TableInfos>> tableMap = new HashMap<>();
        if (dataSourceList != null) {
            List<DataSource> records = dataSourceList.getRecords();
            List<Integer> dsIds = records.stream().map(DataSource::getId).collect(Collectors.toList());
            //根据dsIds查询tablesInfo
            QueryWrapper<TableInfos> query = new QueryWrapper<TableInfos>();
            query.in("data_source_id", dsIds);
            List<TableInfos> tableInfosList = tableInfosService.list(query);
            if (CollectionUtils.isNotEmpty(tableInfosList)) {
                tableMap = tableInfosList.stream().collect(Collectors.groupingBy(TableInfos::getDataSourceId));
            }
        
        }
    
        List<DataSource> dataSources = dataSourceList != null ? dataSourceList.getRecords() : new ArrayList<>();
        handlePasswd(dataSources);
        for (DataSource dataSource : dataSources) {
            dataSource.setChildren(tableMap.get(dataSource.getId()));
        }
        PageInfo<DataSource> pageInfo = new PageInfo<>(pageNo, pageSize);
        pageInfo.setTotal((int) (dataSourceList != null ? dataSourceList.getTotal() : 0L));
        pageInfo.setTotalList(dataSources);
        result.setData(pageInfo);
        putMsg(result, Status.SUCCESS);
    
        return result;
    }

    /**
     * handle datasource connection password for safety
     */
    private void handlePasswd(List<DataSource> dataSourceList) {
        for (DataSource dataSource : dataSourceList) {
            String connectionParams = dataSource.getConnectionParams();
            ObjectNode object = JSONUtils.parseObject(connectionParams);
            object.put(Constants.PASSWORD, getHiddenPassword());
            dataSource.setConnectionParams(object.toString());
        }
    }

    /**
     * get hidden password (resolve the security hotspot)
     *
     * @return hidden password
     */
    private String getHiddenPassword() {
        return Constants.XXXXXX;
    }

    /**
     * query data resource list
     *
     * @param loginUser login user
     * @param type data source type
     * @return data source list page
     */
    @Override
    public Map<String, Object> queryDataSourceList(User loginUser, Integer type) {
        Map<String, Object> result = new HashMap<>();

        List<DataSource> datasourceList;

        if (isAdmin(loginUser)) {
            datasourceList = dataSourceMapper.listAllDataSourceByType(type);
        } else {
            datasourceList = dataSourceMapper.queryDataSourceByType(loginUser.getId(), type);
        }

        result.put(Constants.DATA_LIST, datasourceList);
        putMsg(result, Status.SUCCESS);

        return result;
    }

    /**
     * verify datasource exists
     *
     * @param name datasource name
     * @return true if data datasource not exists, otherwise return false
     */
    @Override
    public Result<Object> verifyDataSourceName(String name) {
        Result<Object> result = new Result<>();
        List<DataSource> dataSourceList = dataSourceMapper.queryDataSourceByName(name);
        if (dataSourceList != null && !dataSourceList.isEmpty()) {
            putMsg(result, Status.DATASOURCE_EXIST);
        } else {
            putMsg(result, Status.SUCCESS);
        }

        return result;
    }

    /**
     * check connection
     *
     * @param type data source type
     * @param connectionParam connectionParam
     * @return true if connect successfully, otherwise false
     * @return true if connect successfully, otherwise false
     */
    @Override
    public Result<Object> checkConnection(DbType type, ConnectionParam connectionParam) {
        Result<Object> result = new Result<>();
        try (Connection connection = DataSourceClientProvider.getInstance().getConnection(type, connectionParam)) {
            if (connection == null) {
                putMsg(result, Status.CONNECTION_TEST_FAILURE);
                return result;
            }
            putMsg(result, Status.SUCCESS);
            return result;
        } catch (Exception e) {
            String message = Optional.of(e).map(Throwable::getCause)
                    .map(Throwable::getMessage)
                    .orElse(e.getMessage());
            logger.error("datasource test connection error, dbType:{}, connectionParam:{}, message:{}.", type, connectionParam, message);
            return new Result<>(Status.CONNECTION_TEST_FAILURE.getCode(), message);
        }
    }

    /**
     * test connection
     *
     * @param id datasource id
     * @return connect result code
     */
    @Override
    public Result<Object> connectionTest(int id) {
        DataSource dataSource = dataSourceMapper.selectById(id);
        if (dataSource == null) {
            Result<Object> result = new Result<>();
            putMsg(result, Status.RESOURCE_NOT_EXIST);
            return result;
        }
        return checkConnection(dataSource.getType(), DataSourceUtils.buildConnectionParams(dataSource.getType(), dataSource.getConnectionParams()));
    }

    /**
     * delete datasource
     *
     * @param loginUser login user
     * @param datasourceId data source id
     * @return delete result code
     */
    @Override
    @Transactional
    public Result<Object> delete(User loginUser, int datasourceId) {
        Result<Object> result = new Result<>();
        try {
            //query datasource by id
            DataSource dataSource = dataSourceMapper.selectById(datasourceId);
            if (dataSource == null) {
                logger.error("resource id {} not exist", datasourceId);
                putMsg(result, Status.RESOURCE_NOT_EXIST);
                return result;
            }
            if (!canOperator(loginUser, dataSource.getUserId())) {
                putMsg(result, Status.USER_NO_OPERATION_PERM);
                return result;
            }
            dataSourceMapper.deleteById(datasourceId);
            datasourceUserMapper.deleteByDatasourceId(datasourceId);
            putMsg(result, Status.SUCCESS);
        } catch (Exception e) {
            logger.error("delete datasource error", e);
            throw new RuntimeException("delete datasource error");
        }
        return result;
    }

    /**
     * unauthorized datasource
     *
     * @param loginUser login user
     * @param userId user id
     * @return unauthed data source result code
     */
    @Override
    public Map<String, Object> unauthDatasource(User loginUser, Integer userId) {
        Map<String, Object> result = new HashMap<>();

        List<DataSource> datasourceList;
        if (isAdmin(loginUser)) {
            // admin gets all data sources except userId
            datasourceList = dataSourceMapper.queryDatasourceExceptUserId(userId);
        } else {
            // non-admins users get their own data sources
            datasourceList = dataSourceMapper.selectByMap(Collections.singletonMap("user_id", loginUser.getId()));
        }
        List<DataSource> resultList = new ArrayList<>();
        Set<DataSource> datasourceSet;
        if (datasourceList != null && !datasourceList.isEmpty()) {
            datasourceSet = new HashSet<>(datasourceList);

            List<DataSource> authedDataSourceList = dataSourceMapper.queryAuthedDatasource(userId);

            Set<DataSource> authedDataSourceSet;
            if (authedDataSourceList != null && !authedDataSourceList.isEmpty()) {
                authedDataSourceSet = new HashSet<>(authedDataSourceList);
                datasourceSet.removeAll(authedDataSourceSet);
            }
            resultList = new ArrayList<>(datasourceSet);
        }
        result.put(Constants.DATA_LIST, resultList);
        putMsg(result, Status.SUCCESS);
        return result;
    }

    /**
     * authorized datasource
     *
     * @param loginUser login user
     * @param userId user id
     * @return authorized result code
     */
    @Override
    public Map<String, Object> authedDatasource(User loginUser, Integer userId) {
        Map<String, Object> result = new HashMap<>();

        List<DataSource> authedDatasourceList = dataSourceMapper.queryAuthedDatasource(userId);
        result.put(Constants.DATA_LIST, authedDatasourceList);
        putMsg(result, Status.SUCCESS);
        return result;
    }

    @Override
    public Map<String, Object> getTables(Integer datasourceId) {
        Map<String, Object> result = new HashMap<>();

        DataSource dataSource = dataSourceMapper.selectById(datasourceId);
    
        List<String> tableList = null;
        BaseConnectionParam connectionParam = (BaseConnectionParam) DataSourceUtils.buildConnectionParams(dataSource.getType(), dataSource.getConnectionParams());
    
        if (null == connectionParam) {
            putMsg(result, Status.DATASOURCE_CONNECT_FAILED);
            return result;
        }
        Connection connection = DataSourceUtils.getConnection(dataSource.getType(), connectionParam);
        ResultSet tables = null;
    
        try {
        
            if (null == connection) {
                putMsg(result, Status.DATASOURCE_CONNECT_FAILED);
                return result;
            }

            DatabaseMetaData metaData = connection.getMetaData();
            String schema = null;
            try {
                schema = metaData.getConnection().getSchema();
            } catch (SQLException e) {
                logger.error("cant not get the schema : {}", e.getMessage(), e);
            }
            tables = metaData.getTables(connectionParam.getDatabase(), getDbSchemaPattern(dataSource.getType(), schema, connectionParam),
                    "%", TABLE_TYPES);
            if (null == tables) {
                putMsg(result, Status.GET_DATASOURCE_TABLES_ERROR);
                return result;
            }
        
            tableList = new ArrayList<>();
            while (tables.next()) {
                String name = tables.getString(TABLE_NAME);
                tableList.add(name);
            }

        } catch (Exception e) {
            logger.error(e.toString(), e);
            putMsg(result, Status.GET_DATASOURCE_TABLES_ERROR);
            return result;
        } finally {
            closeResult(tables);
            releaseConnection(connection);
        }

        List<ParamsOptions> options = getParamsOptions(tableList);

        result.put(Constants.DATA_LIST, options);
        putMsg(result, Status.SUCCESS);
        return result;
    }

    @Override
    public Map<String, Object> getTableColumns(Integer datasourceId,String tableName) {
        Map<String, Object> result = new HashMap<>();

        DataSource dataSource = dataSourceMapper.selectById(datasourceId);
        BaseConnectionParam connectionParam =
                (BaseConnectionParam) DataSourceUtils.buildConnectionParams(
                        dataSource.getType(),
                        dataSource.getConnectionParams());

        if (null == connectionParam) {
            putMsg(result, Status.DATASOURCE_CONNECT_FAILED);
            return result;
        }

        Connection connection =
                DataSourceUtils.getConnection(dataSource.getType(), connectionParam);
        List<String> columnList = new ArrayList<>();
        ResultSet rs = null;

        try {

            String database = connectionParam.getDatabase();
            if (null == connection) {
                return result;
            }

            DatabaseMetaData metaData = connection.getMetaData();

            if (dataSource.getType() == DbType.ORACLE) {
                database = null;
            }
            rs = metaData.getColumns(database, null, tableName, "%");
            if (rs == null) {
                return result;
            }
            while (rs.next()) {
                columnList.add(rs.getString(COLUMN_NAME));
            }
        } catch (Exception e) {
            logger.error(e.toString(), e);
        } finally {
            closeResult(rs);
            releaseConnection(connection);
        }
    
        List<ParamsOptions> options = getParamsOptions(columnList);
    
        result.put(Constants.DATA_LIST, options);
        putMsg(result, Status.SUCCESS);
        return result;
    }
    
    @Override
    public List<DataSourceTablesVO> getTableInfos(Integer datasourceId) {
        List<DataSourceTablesVO> result = null;
        DataSource dataSource = dataSourceMapper.selectById(datasourceId);
        BaseConnectionParam connectionParam = (BaseConnectionParam) DataSourceUtils.buildConnectionParams(dataSource.getType(), dataSource.getConnectionParams());
        if (null == connectionParam) {
            throw new ServiceException(Status.DATASOURCE_CONNECT_FAILED.getCode(), Status.DATASOURCE_CONNECT_FAILED.getMsg());
        }
        Connection connection = DataSourceUtils.getConnection(dataSource.getType(), connectionParam);
        ResultSet tables = null;
        try {
            if (null == connection) {
                throw new ServiceException(Status.DATASOURCE_CONNECT_FAILED.getCode(), Status.DATASOURCE_CONNECT_FAILED.getMsg());
            }
            DatabaseMetaData metaData = connection.getMetaData();
            String schema = null;
            try {
                schema = metaData.getConnection().getSchema();
            } catch (SQLException e) {
                logger.error("cant not get the schema : {}", e.getMessage(), e);
            }
            tables = metaData.getTables(connectionParam.getDatabase(), getDbSchemaPattern(dataSource.getType(), schema, connectionParam), "%", TABLE_TYPES);
            if (null == tables) {
                throw new ServiceException(Status.GET_DATASOURCE_TABLES_ERROR.getCode(), Status.GET_DATASOURCE_TABLES_ERROR.getMsg());
            }
            result = new ArrayList<>();
            while (tables.next()) {
                DataSourceTablesVO dataSourceTablesVO = new DataSourceTablesVO();
                dataSourceTablesVO.setTableName(tables.getString(TABLE_NAME));
                dataSourceTablesVO.setTableSchem(tables.getString(TABLE_SCHEM));
                dataSourceTablesVO.setTableType(tables.getString(TABLE_TYPE));
                dataSourceTablesVO.setRemarks(tables.getString(REMARKS));
                dataSourceTablesVO.setRefGeneration(tables.getString(REF_GENERATION));
                dataSourceTablesVO.setTableCat(tables.getString(TABLE_CAT));
                dataSourceTablesVO.setTypeCat(tables.getString(TYPE_CAT));
                dataSourceTablesVO.setTypeName(tables.getString(TYPE_NAME));
                dataSourceTablesVO.setTypeSchem(tables.getString(TYPE_SCHEM));
                result.add(dataSourceTablesVO);
            }
            
        } catch (Exception e) {
            logger.error(e.toString(), e);
            throw new ServiceException(Status.GET_DATASOURCE_TABLES_ERROR.getCode(), Status.GET_DATASOURCE_TABLES_ERROR.getMsg());
        } finally {
            closeResult(tables);
            releaseConnection(connection);
        }
        return result;
        
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void refreshTableInfo(Integer datasourceId) {
        QueryWrapper<TableInfos> deleteQuery = new QueryWrapper<TableInfos>();
        deleteQuery.eq("data_source_id", datasourceId);
        tableInfosService.remove(deleteQuery);
        List<DataSourceTablesVO> dataSourceTablesVOS = this.getTableInfos(datasourceId);
        //初始化数据源表数据
        initTableInfos(dataSourceTablesVOS,datasourceId);
        
       
    }
    
    private void initTableInfos(List<DataSourceTablesVO> dataSourceTablesVOS, Integer datasourceId) {
        List<TableInfos> tableInfosList = new ArrayList<>();
        for (DataSourceTablesVO tableInfo : dataSourceTablesVOS) {
            TableInfos tableInfos = new TableInfos();
            BeanUtils.copyProperties(tableInfo, tableInfos);
            tableInfos.setDataSourceId(datasourceId);
            tableInfosList.add(tableInfos);
        }
        tableInfosService.saveBatch(tableInfosList);
    }
    
    private List<ParamsOptions> getParamsOptions(List<String> columnList) {
        List<ParamsOptions> options = null;
        if (CollectionUtils.isNotEmpty(columnList)) {
            options = new ArrayList<>();
            
            for (String column : columnList) {
                ParamsOptions childrenOption = new ParamsOptions(column, column, false);
                options.add(childrenOption);
            }
        }
        return options;
    }
    
    private String getDbSchemaPattern(DbType dbType, String schema, BaseConnectionParam connectionParam) {
        if (dbType == null) {
            return null;
        }
        String schemaPattern = null;
        switch (dbType) {
            case HIVE:
                schemaPattern = connectionParam.getDatabase();
                break;
            case ORACLE:
                schemaPattern = connectionParam.getUser();
                if (null != schemaPattern) {
                    schemaPattern = schemaPattern.toUpperCase();
                }
                break;
            case SQLSERVER:
                schemaPattern = "dbo";
                break;
            case CLICKHOUSE:
            case PRESTO:
                if (!StringUtils.isEmpty(schema)) {
                    schemaPattern = schema;
                }
                break;
            default:
                break;
        }
        return schemaPattern;
    }

    private static void releaseConnection(Connection connection) {
        if (null != connection) {
            try {
                connection.close();
            } catch (Exception e) {
                logger.error("Connection release error", e);
            }
        }
    }

    private static void closeResult(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                logger.error("ResultSet close error", e);
            }
        }
    }

}
