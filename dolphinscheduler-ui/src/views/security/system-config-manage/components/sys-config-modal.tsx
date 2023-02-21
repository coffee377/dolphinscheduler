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

import { defineComponent, PropType, toRefs, watch } from 'vue'
import Modal from '@/components/modal'
import { NForm, NFormItem, NInput, NRadio, NRadioGroup, NSpace } from 'naive-ui'
import { useModal } from './use-modal'
import { useI18n } from 'vue-i18n'

const SysConfigModal = defineComponent({
  name: 'SysConfigModal',
  props: {
    showModalRef: {
      type: Boolean as PropType<boolean>,
      default: false
    },
    typeRef: {
      type: String as PropType<String>,
      default: 'create'
    },
    row: {
      type: Object as PropType<any>,
      default: {}
    }
  },
  emits: ['cancelModal', 'confirmModal'],
  setup(props, ctx) {
    const { variables, handleValidate } = useModal(props, ctx)
    const { t } = useI18n()

    const cancelModal = () => {
      // if (props.typeRef === 'create') {
      //   variables.model.paramLable = ''
      //   variables.model.paramValue = ''
      //   variables.model.isEnable = '0'
      //   variables.model.name = ''
      // }
      ctx.emit('cancelModal')
    }

    const confirmModal = () => {
      handleValidate(props.typeRef)
    }

    watch(
      () => props.typeRef,
      () => {
        if (props.typeRef === 'create') {
          variables.model.id = ''
          variables.model.paramLable = ''
          variables.model.paramValue = ''
          variables.model.isEnable = '1'
          variables.model.name = ''
        } else {
          variables.model.id = props.row.id
          variables.model.paramLable = props.row.paramLable
          variables.model.paramValue = props.row.paramValue
          variables.model.isEnable = props.row.isEnable == '启用' ? '1' : '0'
          variables.model.name = props.row.name
          variables.model.createdTime = props.row.createdTime
        }
      }
    )

    watch(
      () => props.row,
      () => {
        variables.model.id = props.row.id
        variables.model.paramLable = props.row.paramLable
        variables.model.paramValue = props.row.paramValue
        variables.model.isEnable = props.row.isEnable == '启用' ? '1' : '0'
        variables.model.name = props.row.name
        variables.model.createdTime = props.row.createdTime
      }
    )

    return { t, ...toRefs(variables), cancelModal, confirmModal }
  },
  render() {
    const { t } = this
    return (
      <div>
        <Modal
          title={
            this.typeRef === 'create'
              ? t('security.sys_config.create')
              : t('security.sys_config.edit_params')
          }
          show={this.showModalRef}
          onCancel={this.cancelModal}
          onConfirm={this.confirmModal}
          confirmLoading={this.loading}
        >
          {{
            default: () => (
              <NForm
                model={this.model}
                rules={this.rules}
                ref='sysConfigFormRef'
              >
                <NFormItem
                  label={t('security.sys_config.parameter_name')}
                  path='name'
                >
                  <NInput
                    placeholder={t('security.sys_config.parameter_name_tips')}
                    v-model={[this.model.name, 'value']}
                  />
                </NFormItem>
                <NFormItem
                  label={t('security.sys_config.parameter_key')}
                  path='paramLable'
                >
                  <NInput
                    placeholder={t('security.sys_config.parameter_key_tips')}
                    v-model={[this.model.paramLable, 'value']}
                  />
                </NFormItem>
                <NFormItem
                  label={t('security.sys_config.parameter_value')}
                  path='paramValue'
                >
                  <NInput
                    placeholder={t('security.sys_config.parameter_value_tips')}
                    v-model={[this.model.paramValue, 'value']}
                  />
                </NFormItem>
                <NFormItem
                  label={t('security.sys_config.state')}
                  path='isEnable'
                >
                  <NRadioGroup v-model:value={this.model.isEnable}>
                    <NSpace>
                      <NRadio value={'1'} class='radio-state-enable'>
                        {this.t('security.sys_config.state_enabled')}
                      </NRadio>
                      <NRadio value={'0'} class='radio-state-disable'>
                        {this.t('security.sys_config.state_disabled')}
                      </NRadio>
                    </NSpace>
                  </NRadioGroup>
                </NFormItem>
              </NForm>
            )
          }}
        </Modal>
      </div>
    )
  }
})

export default SysConfigModal
