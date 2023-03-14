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
import net.jqsoft.integration.platform.model.enums.DbType;

import java.util.Date;
@Data
@TableName("dsx_dnd_registry")
public class DxsComponentType {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private int id;


    /**
     * key
     */
    private String keyId;

    /**
     * name
     */
    private String name;

    /**
     * sort
     */
    private int sort;

    /**
     * REMARK
     */
    private String remark;

    /**
     * CREATED_BY
     */
    private int createdBy;

    /**
     * UPDATED_BY
     */
    private int updatedBy;


    /**
     * create time
     */
    private Date createTime;

    /**
     * update time
     */
    private Date updateTime;

    public DxsComponentType() {
    }




}
