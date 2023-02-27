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

import cn.hutool.core.lang.hash.Hash;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import net.jqsoft.integration.platform.base.BaseController;
import net.jqsoft.integration.platform.common.CommonResult;
import net.jqsoft.integration.platform.model.entity.DataSource;
import net.jqsoft.integration.platform.model.entity.DsDriverInfo;
import net.jqsoft.integration.platform.model.entity.TaskDefinitionLog;
import net.jqsoft.integration.platform.model.enums.ProcessExecutionTypeEnum;
import net.jqsoft.integration.platform.model.enums.ReleaseState;
import net.jqsoft.integration.platform.service.DataSourceService;
import net.jqsoft.integration.platform.service.DsDriverInfoService;
import net.jqsoft.integration.platform.service.TaskDefinitionService;
import net.jqsoft.integration.platform.util.JSONUtils;
import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplateHandler;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * task definition controller
 */
@Slf4j
@Api(tags = "TASK_DEFINITION_TAG")
@RestController
@RequestMapping("projects/{projectCode}/task-definition")
public class TaskDefinitionController extends BaseController {

    @Resource
    private RestTemplate dsClient;

    @Resource
    private TaskDefinitionService taskDefinitionService;


    @ApiOperation(value = "createProcessDefinition", notes = "CREATE_PROCESS_DEFINITION_NOTES")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "PROCESS_DEFINITION_NAME", required = true, type = "String"),
            @ApiImplicitParam(name = "locations", value = "PROCESS_DEFINITION_LOCATIONS", required = true, type = "String"),
            @ApiImplicitParam(name = "description", value = "PROCESS_DEFINITION_DESC", required = false, type = "String")
    })
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResult createProcessDefinition(@ApiParam(name = "projectCode", value = "PROJECT_CODE", required = true) @PathVariable long projectCode,
                                          @RequestParam(value = "name", required = true) String name,
                                          @RequestParam(value = "description", required = false) String description,
                                          @RequestParam(value = "globalParams", required = false, defaultValue = "[]") String globalParams,
                                          @RequestParam(value = "locations", required = false) String locations,
                                          @RequestParam(value = "timeout", required = false, defaultValue = "0") int timeout,
                                          @RequestParam(value = "tenantCode", required = true) String tenantCode,
                                          @RequestParam(value = "taskRelationJson", required = true) String taskRelationJson,
                                          @RequestParam(value = "taskDefinitionJson", required = true) String taskDefinitionJson,
                                          @RequestParam(value = "executionType", defaultValue = "PARALLEL") ProcessExecutionTypeEnum executionType) {
        String dataxTaskDefinitionJson = taskDefinitionService.createQueryAndResultSql(taskDefinitionJson);
        CommonResult result = taskDefinitionService.pushCreateTaskToDs( projectCode, name, description, globalParams,
                locations, timeout, tenantCode, taskRelationJson, dataxTaskDefinitionJson,executionType);


        return CommonResult.success();
    }

    /**
     * update process definition
     *
     * @param projectCode project code
     * @param name process definition name
     * @param code process definition code
     * @param description description
     * @param globalParams globalParams
     * @param locations locations for nodes
     * @param timeout timeout
     * @param tenantCode tenantCode
     * @param taskRelationJson relation json for nodes
     * @param taskDefinitionJson taskDefinitionJson
     * @return update result code
     */
    @ApiOperation(value = "update", notes = "UPDATE_PROCESS_DEFINITION_NOTES")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "PROCESS_DEFINITION_NAME", required = true, type = "String"),
            @ApiImplicitParam(name = "code", value = "PROCESS_DEFINITION_CODE", required = true, dataType = "Long", example = "123456789"),
            @ApiImplicitParam(name = "locations", value = "PROCESS_DEFINITION_LOCATIONS", required = true, type = "String"),
            @ApiImplicitParam(name = "description", value = "PROCESS_DEFINITION_DESC", required = false, type = "String"),
            @ApiImplicitParam(name = "releaseState", value = "RELEASE_PROCESS_DEFINITION_NOTES", required = false, dataType = "ReleaseState")
    })
    @PutMapping (value = "/{code}")
    @ResponseStatus(HttpStatus.OK)
    public CommonResult updateProcessDefinition(@ApiParam(name = "projectCode", value = "PROJECT_CODE", required = true) @PathVariable long projectCode,
                                          @RequestParam(value = "name", required = false) String name,
                                          @PathVariable(value = "code", required = true) long code,
                                          @RequestParam(value = "description", required = false) String description,
                                          @RequestParam(value = "globalParams", required = false, defaultValue = "[]") String globalParams,
                                          @RequestParam(value = "locations", required = false) String locations,
                                          @RequestParam(value = "timeout", required = false, defaultValue = "0") int timeout,
                                          @RequestParam(value = "tenantCode", required = true) String tenantCode,
                                          @RequestParam(value = "taskRelationJson", required = true) String taskRelationJson,
                                          @RequestParam(value = "taskDefinitionJson", required = true) String taskDefinitionJson,
                                           @RequestParam(value = "fieldsMap", required = false) String fields,
                                          @RequestParam(value = "executionType", defaultValue = "PARALLEL") ProcessExecutionTypeEnum executionType,
                                          @RequestParam(value = "releaseState", required = false, defaultValue = "OFFLINE") ReleaseState releaseState) {

        String dataxTaskDefinitionJson = taskDefinitionService.createQueryAndResultSql(taskDefinitionJson);
        CommonResult result = taskDefinitionService.pushTaskToDs(projectCode,code,locations,dataxTaskDefinitionJson,taskRelationJson,tenantCode,executionType,
                description,globalParams,timeout,releaseState,name);
        return CommonResult.success();
    }

    /**
     * query detail of process definition by code
     *
     * @param projectCode project code
     * @param code process definition code
     * @return process definition detail
     */
    @ApiOperation(value = "queryProcessDefinitionByCode", notes = "QUERY_PROCESS_DEFINITION_BY_CODE_NOTES")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "PROCESS_DEFINITION_CODE", required = true, dataType = "Long", example = "123456789")
    })
    @GetMapping(value = "/{code}")
    @ResponseStatus(HttpStatus.OK)
    public CommonResult queryProcessDefinitionByCode(@ApiParam(name = "projectCode", value = "PROJECT_CODE", required = true) @PathVariable long projectCode,
                                               @PathVariable(value = "code", required = true) long code) {
        Map<String, Object> result = taskDefinitionService.queryProcessDefinitionByCode(projectCode, code);
        return CommonResult.success(result);
    }




}
