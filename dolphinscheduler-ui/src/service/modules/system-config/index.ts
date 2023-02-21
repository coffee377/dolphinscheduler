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

import { axios } from '@/service/request'
import { ListReq, addReq } from './type'

export function querySystemParams(params: ListReq): any {
  return axios({
    url: '/platform/sysConfig/page',
    method: 'get',
    params
  })
}

export function createSystemParams(data: addReq): any {
  return axios({
    url: '/platform/sysConfig',
    method: 'post',
    data: JSON.stringify(data),
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function deleteSystemParams(id: string): any {
  return axios({
    url: `/platform/sysConfig/${id}`,
    method: 'delete'
  })
}
//批量删除
export function batchDeleteSystem(ids: string): any {
  return axios({
    url: `/platform/sysConfig/batch/${ids}`,
    method: 'delete'
  })
}

export function updateSystemParams(data: addReq): any {
  return axios({
    url: '/platform/sysConfig',
    method: 'put',
    data: JSON.stringify(data),
    headers: {
      'Content-Type': 'application/json'
    }
  })
}
export function systemParamsDetail(id: string): any {
  return axios({
    url: `/platform/sysConfig/getDetails/${id}`,
    method: 'get'
  })
}
