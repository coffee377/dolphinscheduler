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

package net.jqsoft.integration.platform.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * task instance
 */
@TableName("t_ds_task_instance")
@Data
public class TaskInstanceVO implements Serializable {

    /**
     * id
     */
    private int id;

    /**
     * task name
     */
    private String name;


    /**
     * task type
     */
    private String taskType;

    /**
     * process instance id
     */
    private int processInstanceId;

    /**
     * task code
     */
    private long taskCode;

    /**
     * task definition version
     */
    private int taskDefinitionVersion;

    /**
     * process instance name
     */
    @TableField(exist = false)
    private String processInstanceName;

    /**
     * process instance name
     */
    @TableField(exist = false)
    private int taskGroupPriority;


    /**
     * task first submit time.
     */
    private Date firstSubmitTime;

    /**
     * task submit time
     */
    private Date submitTime;

    /**
     * task start time
     */
    private Date startTime;

    /**
     * task end time
     */
    private Date endTime;

    /**
     * task host
     */
    private String host;

    /**
     * task shell execute path and the resource down from hdfs
     * default path: $base_run_dir/processInstanceId/taskInstanceId/retryTimes
     */
    private String executePath;

    /**
     * task log path
     * default path: $base_run_dir/processInstanceId/taskInstanceId/retryTimes
     */
    private String logPath;

    /**
     * retry times
     */
    private int retryTimes;

    

    /**
     *  state
     */
    private int state;


    /**
     * duration
     */
    @TableField(exist = false)
    private String duration;


    /**
     * duration
     */
    @TableField(exist = false)
    private String projectName;







}
