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

import axios, { AxiosRequestConfig, AxiosResponse, AxiosError } from 'axios'
import { useUserStore } from '@/store/user/user'
import qs from 'qs'
import _ from 'lodash'
import cookies from 'js-cookie'
import router from '@/router'
import utils from '@/utils'

const userStore = useUserStore()

/**
 * @description Log and display errors
 * @param res
 */
const handleError = (res: AxiosResponse<any, any>) => {
  // Print to console
  if (import.meta.env.MODE === 'development') {
    utils.log.capsule('DolphinScheduler', 'UI')
    utils.log.error(res)
  }
  window.$message.error(res.data.msg)
}

// 接口请求前缀
const prefix = window['__APP_REQUEST_CONF__']?.prefix ?? '/api'

const baseRequestConfig: AxiosRequestConfig = {
  baseURL: prefix + '/dolphinscheduler',
  timeout: 15000,
  transformRequest: (params) => {
    if (_.isPlainObject(params)) {
      return qs.stringify(params, { arrayFormat: 'repeat' })
    } else {
      return params
    }
  },
  paramsSerializer: (params) => {
    return qs.stringify(params, { arrayFormat: 'repeat' })
  }
}

const service = axios.create(baseRequestConfig)
// 自研接口调用实例
const request = axios.create({
  ...baseRequestConfig,
  baseURL: prefix
})

const err = (err: AxiosError): Promise<AxiosError> => {
  if (err.response?.status === 401 || err.response?.status === 504) {
    userStore.setSessionId('')
    userStore.setUserInfo({})
    router.push({ path: '/login' })
  }

  return Promise.reject(err)
}

// 请求拦击
const reqIntercept = (config: AxiosRequestConfig<any>) => {
  config.headers && (config.headers.sessionId = userStore.getSessionId)
  const language = cookies.get('language')
  config.headers = config.headers || {}
  if (language) config.headers.language = language

  return config
}
service.interceptors.request.use(reqIntercept, err)
request.interceptors.request.use(reqIntercept, err)

// 响应拦击
const resIntercept = (res: AxiosResponse) => {
  // No code will be processed
  if (res.data.code === undefined) {
    return res.data
  }

  switch (res.data.code) {
    case 0:
      return res.data.data
    default:
      handleError(res)
      throw new Error()
  }
}
// The response to intercept
service.interceptors.response.use(resIntercept, err)
request.interceptors.response.use(resIntercept, err)

export { service as axios, request }
