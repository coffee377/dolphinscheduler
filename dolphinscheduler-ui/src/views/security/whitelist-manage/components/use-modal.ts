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
import { createWhitelist, updateWhitelists } from '@/service/modules/whitelist'
import { useMessage } from 'naive-ui'
export function useModal(
  props: any,
  ctx: SetupContext<('cancelModal' | 'confirmModal')[]>
) {
  const { t } = useI18n()

  const variables = reactive({
    whitelistFormRef: ref(),
    loading: ref(false),
    model: {
      id: ref<string>(''),
      userId: ref(''),
      ipSegment: ref(''),
      ipType: ref(0)
    },
    rules: {
      // userId: {
      //   required: true,
      //   trigger: ['input', 'blur'],
      //   validator() {
      //     if (variables.model.userId === '') {
      //       return new Error(t('security.sys_config.parameter_name_tips'))
      //     }
      //   }
      // },
      ipSegment: {
        required: true,
        trigger: ['input', 'blur'],
        validator() {
          if (variables.model.ipSegment === '') {
            return new Error(t('security.whitelist.ip_tips'))
          }
        }
      }
      // ipType: {
      //   required: true,
      //   trigger: ['input', 'blur'],
      //   validator() {
      //     if (variables.model.ipType) {
      //       return new Error(t('security.sys_config.parameter_value_tips'))
      //     }
      //   }
      // }
    }
  })
  const message = useMessage()
  const handleValidate = async (type: String) => {
    await variables.whitelistFormRef.validate()

    if (variables.loading) return
    variables.loading = true

    try {
      if (type === 'create') {
        await submitWhiteModal()
      } else if (type === 'update') {
        await updateWhitelistModal()
      }
      variables.loading = false
    } catch (err) {
      variables.loading = false
    }
  }

  const submitWhiteModal = () => {
    createWhitelist(variables.model).then((res: any) => {
      if (res.status == '200') {
        variables.model.ipType = 0
        variables.model.ipSegment = ''
        variables.model.userId = ''
        ctx.emit('confirmModal')
        message.destroyAll()
        message.success('已添加！')
      } else {
        message.destroyAll()
        message.error(res.msg)
      }
    })
  }

  const updateWhitelistModal = () => {
    updateWhitelists(variables.model).then((res: any) => {
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
