package net.jqsoft.integration.platform.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;
import net.jqsoft.integration.platform.base.BaseServiceImpl;
import net.jqsoft.integration.platform.common.CommonResult;
import net.jqsoft.integration.platform.mapper.TaskDefinitionMapper;
import net.jqsoft.integration.platform.model.entity.DataSource;
import net.jqsoft.integration.platform.model.entity.TaskDefinition;
import net.jqsoft.integration.platform.model.entity.TaskDefinitionLog;
import net.jqsoft.integration.platform.model.enums.ProcessExecutionTypeEnum;
import net.jqsoft.integration.platform.model.enums.ReleaseState;
import net.jqsoft.integration.platform.service.DataSourceService;
import net.jqsoft.integration.platform.service.TaskDefinitionService;
import net.jqsoft.integration.platform.util.JSONUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
@Slf4j
@Service
public class TaskDefinitionServiceImpl extends BaseServiceImpl<TaskDefinitionMapper, TaskDefinition> implements TaskDefinitionService {

    @Resource
    private DataSourceService dataSourceService;

    @Resource
    private RestTemplate dsClient;
    @Value("${dolphinscheduler.root-uri}")
    private String url;

    @Override
    public String createQueryAndResultSql(String taskDefinitionJson) {
        List<TaskDefinitionLog> dataxSql = new ArrayList<>();
        List<TaskDefinitionLog> taskDefinitionLogs = JSONUtils.toList(taskDefinitionJson, TaskDefinitionLog.class);
        if (CollectionUtils.isEmpty(taskDefinitionLogs)) {
            //参数为空返回
            return "false";
        }
        taskDefinitionLogs.forEach(taskDefinition -> {
            String taskParams = taskDefinition.getTaskParams();
            Map<String, Object> taskParamsMap = JSONObject.parseObject(taskParams);
            if (Objects.equals(taskParamsMap.get("customConfig"), 1)) {
                //自定义模式dataX ,默认datax格式
                dataxSql.add(taskDefinition);
            } else {
                String dataxTaskParams = createDataxSql(taskParamsMap);
                taskDefinition.setTaskParams(dataxTaskParams);
                dataxSql.add(taskDefinition);
            }
        });
        String s = JSON.toJSON(dataxSql).toString();
        return s;
    }

    @Override
    public CommonResult pushTaskToDs(long projectCode, long code, String locations, String dataxTaskDefinitionJson, String taskRelationJson,
                                     String tenantCode, ProcessExecutionTypeEnum executionType, String description, String globalParams,
                                     int timeout, ReleaseState releaseState,String name) {
        String postUrl = url + "/dolphinscheduler/projects/{projectCode}/process-definition/{code}?locations={locations}&taskDefinitionJson={taskDefinitionJson}&taskRelationJson=" +
                "{taskRelationJson}&name={name}&tenantCode={tenantCode}&executionType={executionType}&description={description}&globalParams={globalParams}" +
                "&timeout={timeout}&releaseState={releaseState}";
        HttpHeaders headers = new HttpHeaders();
        // 设置content-type,很据需求设置
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        // 设置请求体
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        // 用HttpEntity封装整个请求报文
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        //put传递单参
        Map<String,Object> mapParams=new HashMap<>();
        mapParams.put("projectCode",projectCode);
        mapParams.put("code",code);
        mapParams.put("locations",locations);
        mapParams.put("taskDefinitionJson",dataxTaskDefinitionJson);
        mapParams.put("taskRelationJson",taskRelationJson);
        mapParams.put("name",name);
        mapParams.put("tenantCode",tenantCode);
        mapParams.put("executionType",executionType);
        mapParams.put("description",description);
        mapParams.put("globalParams",globalParams);
        mapParams.put("timeout",timeout);
        mapParams.put("releaseState",releaseState);
        ResponseEntity<String> resp = dsClient.exchange(postUrl, HttpMethod.PUT, request, String.class,mapParams);
        String body = resp.getBody();

        return CommonResult.success();
    }

    @Override
    public CommonResult pushCreateTaskToDs(long projectCode, String name, String description, String globalParams, String locations,
                                           int timeout, String tenantCode, String taskRelationJson, String taskDefinitionJson, ProcessExecutionTypeEnum executionType) {
        String postUrl = url + "/dolphinscheduler/projects/{projectCode}/process-definition?locations={locations}&taskDefinitionJson={taskDefinitionJson}&taskRelationJson=" +
                "{taskRelationJson}&name={name}&tenantCode={tenantCode}&executionType={executionType}&description={description}&globalParams={globalParams}" +
                "&timeout={timeout}";
        HttpHeaders headers = new HttpHeaders();
        // 设置content-type,很据需求设置
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        // 设置请求体
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        // 用HttpEntity封装整个请求报文
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        //put传递单参
        Map<String,Object> mapParams=new HashMap<>();
        mapParams.put("projectCode",projectCode);
        mapParams.put("locations",locations);
        mapParams.put("taskDefinitionJson",taskDefinitionJson);
        mapParams.put("taskRelationJson",taskRelationJson);
        mapParams.put("name",name);
        mapParams.put("tenantCode",tenantCode);
        mapParams.put("executionType",executionType);
        mapParams.put("description",description);
        mapParams.put("globalParams",globalParams);
        mapParams.put("timeout",timeout);
        ResponseEntity<String> resp = dsClient.exchange(postUrl, HttpMethod.POST, request, String.class,mapParams);
        String body = resp.getBody();

        return CommonResult.success();
    }

    @Override
    public Map<String, Object> queryProcessDefinitionByCode(long projectCode, long code) {
        String postUrl = url +"/dolphinscheduler/projects/{projectCode}/process-definition/{code}";
        HttpHeaders headers = new HttpHeaders();
        // 设置验签用的数据
        // 设置content-type,很据需求设置
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        // 设置请求体
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        // 用HttpEntity封装整个请求报文
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        //put传递单参
        Map<String,Object> mapParams=new HashMap<>();
        mapParams.put("projectCode",projectCode);
        mapParams.put("code",code);
        ResponseEntity<String> resp = dsClient.exchange(postUrl, HttpMethod.GET, request, String.class,mapParams);
        Map<String, Object> resonseBody = JSONObject.parseObject(resp.getBody(), Map.class);
        Object data = resonseBody.get("data");
        Map<String, Object> returnData = JSONObject.parseObject(JSONObject.toJSONString(resonseBody.get("data")), Map.class);
        Object taskDefinitionList = returnData.get("taskDefinitionList");
        return returnData;
    }

    private String createDataxSql(Map<String, Object> taskParamsMap) {
        Object fields = taskParamsMap.get("fieldsMap");
        System.out.println(fields);
        Map<String, Object> fieldsMap = JSONObject.parseObject(JSONObject.toJSONString(fields), Map.class);
        StringBuilder querySqlBuilder = new StringBuilder();
        List<String> resultFileds = new ArrayList<>();
        //   Assert.notEmpty(fieldsMap ,"查询参数配置为空");
        //获取数据库名称
        DataSource queryDataSource = dataSourceService.getById(Integer.valueOf(taskParamsMap.get("dataSource").toString()));
        DataSource resultDataSource = dataSourceService.getById(Integer.valueOf(taskParamsMap.get("dataTarget").toString()));
        querySqlBuilder.append("select ");
        fieldsMap.forEach((k, v) -> {
            querySqlBuilder.append(k).append(",");
            resultFileds.add(v.toString());
        });

        String substring = querySqlBuilder.substring(0, querySqlBuilder.length() - 1);

        String querySql = substring + " from " + taskParamsMap.get("sourceTable");

        //拼接 dataX
        Map<String, Object> queryConnectInfo = JSONObject.parseObject(queryDataSource.getConnectionParams());
        Map<String, Object> targetConnectInfo = JSONObject.parseObject(resultDataSource.getConnectionParams());

        JSONObject dataXMap = new JSONObject();
        JSONObject jobMap = new JSONObject();

        JSONObject contentMap = new JSONObject();
        JSONObject connectionMap = new JSONObject();
        List<String> querySqlList = new ArrayList<>();
        querySqlList.add(querySql);

        connectionMap.put("querySql", JSON.toJSON(querySqlList).toString());
        List<String> queryJdbcList = new ArrayList<>();
        queryJdbcList.add(queryConnectInfo.get("jdbcUrl").toString());
        connectionMap.put("jdbcUrl", JSON.toJSON(queryJdbcList).toString());
        JSONObject parameterMap = new JSONObject();
        parameterMap.put("username", queryConnectInfo.get("user").toString());
        parameterMap.put("password", queryConnectInfo.get("password").toString());
        List<JSONObject> connectionList = new ArrayList<>();
        connectionList.add(connectionMap);
        parameterMap.put("connection", JSON.toJSON(connectionList).toString());

        JSONObject readerMap = new JSONObject();
        readerMap.put("name", taskParamsMap.get("dsType").toString().toLowerCase() + "reader");
        readerMap.put("parameter", JSON.toJSONString(parameterMap) );


        JSONObject wiriterConnectionMap = new JSONObject();
        List<String> targetTable = new ArrayList<>();
        targetTable.add(taskParamsMap.get("targetTable").toString());
        wiriterConnectionMap.put("table", JSON.toJSONString(targetTable));
        wiriterConnectionMap.put("jdbcUrl", targetConnectInfo.get("jdbcUrl").toString());
        JSONObject writerParameterMap = new JSONObject();
        writerParameterMap.put("username", targetConnectInfo.get("user").toString());
        writerParameterMap.put("password", targetConnectInfo.get("password").toString());
        List<JSONObject> wiriterConnectionList = new ArrayList<>();
        wiriterConnectionList.add(wiriterConnectionMap);
        writerParameterMap.put("connection", JSON.toJSON(wiriterConnectionList).toString() );
        writerParameterMap.put("column", JSON.toJSON(resultFileds).toString() );

        JSONObject writerMap = new JSONObject();
        writerMap.put("name", taskParamsMap.get("dtType").toString().toLowerCase() + "writer");
        writerMap.put("parameter",  JSON.toJSONString(writerParameterMap));

        contentMap.put("reader",JSON.toJSONString(readerMap) );
        contentMap.put("writer", JSON.toJSONString(writerMap));

        JSONObject speedMap = new JSONObject();
        speedMap.put("bytes", -1);
        speedMap.put("channel", 1);
        JSONObject speedMap2 = new JSONObject();
        speedMap2.put("speed", speedMap);
        List<JSONObject> contentList = new ArrayList<>();
        contentList.add(contentMap);
        jobMap.put("content", JSON.toJSON(contentList).toString());
        jobMap.put("setting", JSON.toJSONString(speedMap2));
        log.info("===================================================================");
        dataXMap.put("job", JSON.toJSONString(jobMap));
        log.info("===================================================================");
        //拼接taskParams
        JSONObject newTaskParamsMap = new JSONObject();
        newTaskParamsMap.put("localParams", taskParamsMap.get("localParams"));
        newTaskParamsMap.put("json", JSON.toJSONString(dataXMap));
        newTaskParamsMap.put("xms", taskParamsMap.get("xms"));
        newTaskParamsMap.put("xmx", taskParamsMap.get("xmx"));
        newTaskParamsMap.put("customConfig", "1");
        return JSON.toJSONString(newTaskParamsMap);
    }
}
