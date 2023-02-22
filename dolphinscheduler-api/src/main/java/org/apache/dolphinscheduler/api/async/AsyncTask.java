package org.apache.dolphinscheduler.api.async;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dolphinscheduler.api.service.DataSourceService;
import org.apache.dolphinscheduler.api.service.TableInfosService;
import org.apache.dolphinscheduler.api.vo.DataSourceTablesVO;
import org.apache.dolphinscheduler.dao.entity.TableInfos;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 异步处理
 */
@Slf4j
@Component
public class AsyncTask {
    
    @Resource
    @Lazy
    private TableInfosService tableInfosService;
    
    @Resource
    @Lazy
    private DataSourceService dataSourceService;
    
    @Async("taskExecutor")
    public void doTask(int dataSourceId) {
        long start = System.currentTimeMillis();
        QueryWrapper<TableInfos> wrapper = new QueryWrapper<TableInfos>();
        wrapper.eq("data_source_id", dataSourceId);
        tableInfosService.remove(wrapper);
        initTableInfos(dataSourceId);
        log.info("异步任务执行完成！耗时{}秒", (System.currentTimeMillis() - start / 1000));
    }
    
    @Async("taskExecutor")
    public void doInsert(int dataSourceId) {
        long start = System.currentTimeMillis();
        initTableInfos(dataSourceId);
        log.info("异步任务执行完成！耗时{}秒", (System.currentTimeMillis() - start / 1000));
    }
    
    private void initTableInfos(Integer datasourceId) {
        List<DataSourceTablesVO> dataSourceTablesVOS = dataSourceService.getTableInfos(datasourceId);
        List<TableInfos> tableInfosList = new ArrayList<>();
        for (DataSourceTablesVO tableInfo : dataSourceTablesVOS) {
            TableInfos tableInfos = new TableInfos();
            BeanUtils.copyProperties(tableInfo, tableInfos);
            tableInfos.setDataSourceId(datasourceId);
            tableInfosList.add(tableInfos);
        }
        tableInfosService.saveBatch(tableInfosList);
    }
}