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

import { axios } from '@/service/service'
import { IdReq, LogReq } from './types'
import {
  ProjectCodeReq,
  TaskListReq
} from '@/service/modules/task-instances/types'

export function queryLog(params: LogReq): any {
  return axios({
    url: '/log/detail',
    method: 'get',
    params
  })
}

export function downloadTaskLog(params: IdReq): any {
  return axios({
    url: '/log/download-log',
    method: 'get',
    params
  })
}

// export function queryTaskListPaging(
//   params: TaskListReq,
//   projectCode: ProjectCodeReq
// ): any {
//   return axios({
//     url: `/projects/${projectCode.projectCode}/task-instances`,
//     method: 'get',
//     params
//   })
// }
