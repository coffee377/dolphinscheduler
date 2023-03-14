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

package net.jqsoft.integration.platform.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import net.jqsoft.integration.platform.base.BaseEntity;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("dsx_dnd_category")
public class DxsDhdCategory    implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * name
     */
    private String name;

    /**
     * type
     */
    private Long pid;

    /**
     * sort
     */
    private int sort;


    /**
     * configuration
     */
    private String configuration;


    /**
     * create time
     */
    private Date createTime;

    /**
     * update time
     */
    private Date updateTime;
    /**
     * updatedBy
     */
    private int updatedBy;

    /**
     * createBy
     */
    private int createdBy;

    public DxsDhdCategory() {
    }


}
