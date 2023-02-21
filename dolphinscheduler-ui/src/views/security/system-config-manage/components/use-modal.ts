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

import { reactive, ref, SetupContext } from 'vue'
import { useI18n } from 'vue-i18n'
import {
  createSystemParams,
  updateSystemParams
} from '@/service/modules/system-config'
import { useMessage } from 'naive-ui'
export function useModal(
  props: any,
  ctx: SetupContext<('cancelModal' | 'confirmModal')[]>
) {
  const { t } = useI18n()

  const variables = reactive({
    sysConfigFormRef: ref(),
    loading: ref(false),
    model: {
      id: ref<string>(''),
      name: ref(''),
      paramLable: ref(''),
      paramValue: ref(''),
      isEnable: ref<'0' | '1'>('1'),
      createdTime: ref('')
    },
    rules: {
      name: {
        required: true,
        trigger: ['input', 'blur'],
        validator() {
          if (variables.model.name === '') {
            return new Error(t('security.sys_config.parameter_name_tips'))
          }
        }
      },
      paramLable: {
        required: true,
        trigger: ['input', 'blur'],
        validator() {
          if (variables.model.paramLable === '') {
            return new Error(t('security.sys_config.parameter_key_tips'))
          }
        }
      },
      paramValue: {
        required: true,
        trigger: ['input', 'blur'],
        validator() {
          if (variables.model.paramValue === '') {
            return new Error(t('security.sys_config.parameter_value_tips'))
          }
        }
      }
    }
  })
  const message = useMessage()
  const handleValidate = async (type: String) => {
    await variables.sysConfigFormRef.validate()

    if (variables.loading) return
    variables.loading = true

    try {
      if (type === 'create') {
        await submitSysConfigModal()
      } else if (type === 'update') {
        await updateSysConfigModal()
      }
      variables.loading = false
    } catch (err) {
      variables.loading = false
    }
  }

  const submitSysConfigModal = () => {
    createSystemParams(variables.model).then((res: any) => {
      if (res.status == '200') {
        variables.model.name = ''
        variables.model.isEnable = '1'
        variables.model.paramLable = ''
        variables.model.paramValue = ''
        ctx.emit('confirmModal')
        message.destroyAll()
        message.success('已添加！')
      } else {
        message.destroyAll()
        message.error(res.msg)
      }
    })
  }

  const updateSysConfigModal = () => {
    updateSystemParams(variables.model).then((res: any) => {
      if (res.status == '200') {
        ctx.emit('confirmModal')
        message.destroyAll()
        message.success('已更新！')
      } else {
        message.destroyAll()
        message.error(res.msg)
      }
    })
  }

  return {
    variables,
    handleValidate
  }
}
