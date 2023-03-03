package net.jqsoft.integration.platform.service;

import net.jqsoft.integration.platform.base.BaseService;
import net.jqsoft.integration.platform.common.CommonResult;
import net.jqsoft.integration.platform.model.bo.TaskDefinitionBO;
import net.jqsoft.integration.platform.model.entity.TaskDefinition;
import net.jqsoft.integration.platform.model.enums.ProcessExecutionTypeEnum;
import net.jqsoft.integration.platform.model.enums.ReleaseState;

import java.util.Map;


public interface TaskDefinitionService extends BaseService<TaskDefinition> {

    /**
     * 转换数据格式
     * @param taskDefinitionJson
     * @return
     */
    String createQueryAndResultSql(String taskDefinitionJson);

    CommonResult pushTaskToDs(long projectCode, long code, String locations, String dataxTaskDefinitionJson, String taskRelationJson, String tenantCode,
                              ProcessExecutionTypeEnum executionType, String description, String globalParams, int timeout, ReleaseState releaseState,String name);

    CommonResult pushCreateTaskToDs(long projectCode, String name, String description, String globalParams, String locations, int timeout, String tenantCode,
                                    String taskRelationJson, String taskDefinitionJson, ProcessExecutionTypeEnum executionType);

    /**
     * 查询工作流下的所有实例
     * @param projectCode
     * @param code
     * @return
     */
    CommonResult queryProcessDefinitionByCode(long projectCode, long code);

    /**
     * 根据数据创建作业同步参数
     * @param taskDefinitionBO
     * @return
     */
    String createProcessDefinition(TaskDefinitionBO taskDefinitionBO);
}
