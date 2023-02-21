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

export function queryWhitelist(params: ListReq): any {
  return axios({
    url: '/platform/ipsegment/page',
    method: 'get',
    params
  })
}
/*
 * 新增IP
 * */
export function createWhitelist(data: addReq): any {
  return axios({
    url: '/platform/ipsegment',
    method: 'post',
    data: JSON.stringify(data),
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function deleteWhitelist(id: string): any {
  return axios({
    url: `/platform/ipsegment/${id}`,
    method: 'delete'
  })
}
//批量删除
export function batchDeleteWhitelist(ids: string): any {
  return axios({
    url: `/platform/ipsegment/batch/${ids}`,
    method: 'delete'
  })
}

export function updateWhitelists(data: addReq): any {
  return axios({
    url: '/platform/ipsegment',
    method: 'put',
    data: JSON.stringify(data),
    headers: {
      'Content-Type': 'application/json'
    }
  })
}
export function whitelistDetail(id: string): any {
  return axios({
    url: `/platform/ipsegment/getDetails/${id}}`,
    method: 'get'
  })
}
