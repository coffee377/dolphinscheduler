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

package net.jqsoft.integration.platform.controller.taskInstance;

import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import net.jqsoft.integration.platform.base.BaseController;
import net.jqsoft.integration.platform.common.CommonResult;
import net.jqsoft.integration.platform.model.entity.DataSource;
import net.jqsoft.integration.platform.model.entity.DsDriverInfo;
import net.jqsoft.integration.platform.model.entity.TaskDefinitionLog;
import net.jqsoft.integration.platform.service.DataSourceService;
import net.jqsoft.integration.platform.service.DsDriverInfoService;
import net.jqsoft.integration.platform.util.JSONUtils;
import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.Map;


/**
 * task definition controller
 */
@Slf4j
@Api(tags = "TASK_DEFINITION_TAG")
@RestController
@RequestMapping("projects/{projectCode}/task-definition")
public class TaskDefinitionController extends BaseController {

  /*  @Autowired
    private TaskDefinitionService taskDefinitionService;*/

    @Resource
    private DataSourceService dataSourceService;

    /**
     * create single task definition that binds the workflow
     *
     * @param projectCode           project code
     * @param processDefinitionCode process definition code
     * @param taskDefinitionJsonObj task definition json object
     * @param upstreamCodes         upstream task codes, sep comma
     * @return create result code
     */
    @ApiOperation(value = "saveSingle", notes = "CREATE_SINGLE_TASK_DEFINITION_NOTES")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "projectCode", value = "PROJECT_CODE", required = true, type = "Long"),
        @ApiImplicitParam(name = "processDefinitionCode", value = "PROCESS_DEFINITION_CODE", required = true, type = "processDefinitionCode"),
        @ApiImplicitParam(name = "taskDefinitionJsonObj", value = "TASK_DEFINITION_JSON", required = true, type = "String"),
        @ApiImplicitParam(name = "upstreamCodes", value = "UPSTREAM_CODES", required = false, type = "String")
    })
    @PostMapping("/save-single")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResult createTaskBindsWorkFlow(@ApiParam(name = "projectCode", value = "PROJECT_CODE", required = true) @PathVariable long projectCode,
                                                @RequestParam(value = "processDefinitionCode", required = true) long processDefinitionCode,
                                                @RequestParam(value = "taskDefinitionJsonObj", required = true) String taskDefinitionJsonObj,
                                                @RequestParam(value = "fieldsMap", required = false) String fields,
                                                @RequestParam(value = "upstreamCodes", required = false) String upstreamCodes) {
        TaskDefinitionLog taskDefinition = JSONUtils.parseObject(taskDefinitionJsonObj, TaskDefinitionLog.class);
        String  taskParams= taskDefinition.getTaskParams();
        Map<String,Object> taskParamsMap =  JSONObject.parseObject(taskParams);
        Map<String,Object> fieldsMap =  JSONObject.parseObject(fields);
        createQueryAndResultSql(fieldsMap,taskParamsMap);
        if (taskDefinition == null) {
            log.error("taskDefinitionJsonObj is not valid json");


        }
        return CommonResult.success();
    }

    private void createQueryAndResultSql(Map<String, Object> fieldsMap, Map<String, Object> taskParamsMap) {
        StringBuilder querySql = new StringBuilder();
        StringBuilder ressultSql = new StringBuilder();
        Assert.notEmpty(fieldsMap ,"查询参数配置为空");
        //获取数据库名称

        DataSource queryDataSource = dataSourceService.getById(Integer.valueOf(taskParamsMap.get("dataSource").toString()));
        DataSource resultDataSource = dataSourceService.getById(Integer.valueOf(taskParamsMap.get("dataTarget").toString()));
        querySql.append("select ");
        ressultSql.append("select ");
        fieldsMap.forEach((k,v)->{
            querySql.append(k).append(",");
            ressultSql.append(v).append(",");
        });
        System.out.println("querySql:"+querySql);
        System.out.println("ressultSql:"+ressultSql);



    }


}
