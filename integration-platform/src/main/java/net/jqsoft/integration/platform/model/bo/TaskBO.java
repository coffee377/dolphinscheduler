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

package net.jqsoft.integration.platform.model.bo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import javafx.scene.layout.Priority;
import lombok.Data;
import net.jqsoft.integration.platform.model.enums.Flag;
import net.jqsoft.integration.platform.util.JSONUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * task instance
 */
@Data
public class TaskBO implements Serializable {


    @ApiModelProperty("主键id")
    private int id;

    @ApiModelProperty("任务名称")
    private String name;


    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("类型")
    private Long code ;

    private Flag flag;

    private Priority priority;


    private String description;


    private Integer delay;

    private Long preTask;

    @JsonDeserialize(using = JSONUtils.JsonDataDeserializer.class)
    @JsonSerialize(using = JSONUtils.JsonDataSerializer.class)
    private String params;




}
