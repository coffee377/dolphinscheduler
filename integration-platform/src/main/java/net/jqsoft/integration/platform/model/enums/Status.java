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

package net.jqsoft.integration.platform.model.enums;

import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;
import java.util.Optional;

/**
 * status enum      // todo #4855 One category one interval
 */
public enum Status {

    SUCCESS(200, "success"),

    PARENT_RESOURCE_NOT_EXIST(2001, "父资源文件不存在"),

    RESISTER_NOT_EXIST(2002, "注册组件不存在"),

    REGISTRY_COMPONENT_EXIST(2003, "注册组件已存在"),

    RESOURCE_COMPONENT_EXIST(2004, "分类下包含组件");



    private final int code;

    private final String zhMsg;

    Status(int code,  String zhMsg) {
        this.code = code;
        this.zhMsg = zhMsg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.zhMsg;
    }

    /**
     * Retrieve Status enum entity by status code.
     */
    public static Optional<Status> findStatusBy(int code) {
        for (Status status : Status.values()) {
            if (code == status.getCode()) {
                return Optional.of(status);
            }
        }
        return Optional.empty();
    }
}
