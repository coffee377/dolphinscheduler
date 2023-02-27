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

const WhitelistModal = defineComponent({
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
          variables.model.ipSegment = ''
          variables.model.ipType = 1
          variables.model.userId = ''
        } else {
          variables.model.id = props.row.id
          variables.model.ipType = props.row.ipType
          variables.model.userId = props.row.userId
          variables.model.ipSegment = props.row.ipSegment
        }
      }
    )

    watch(
      () => props.row,
      () => {
        variables.model.id = props.row.id
        variables.model.ipType = props.row.ipType
        variables.model.userId = props.row.userId
        variables.model.ipSegment = props.row.ipSegment
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
              ? t('security.whitelist.create')
              : t('security.whitelist.edit')
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
                ref='whitelistFormRef'
              >
                <NFormItem label={t('security.whitelist.ip')} path='ipSegment'>
                  <NInput
                    placeholder={t('security.whitelist.ip_tips')}
                    v-model={[this.model.ipSegment, 'value']}
                  />
                </NFormItem>
                <NFormItem label={t('security.whitelist.ipType')} path='ipType'>
                  <NRadioGroup v-model:value={this.model.ipType}>
                    <NSpace>
                      <NRadio value={1} class='radio-state-enable'>
                        {this.t('security.whitelist.ipType_address')}
                      </NRadio>
                      <NRadio value={2} class='radio-state-disable'>
                        {this.t('security.whitelist.ipType_segment')}
                      </NRadio>
                    </NSpace>
                  </NRadioGroup>
                </NFormItem>
                <NFormItem label={t('security.whitelist.userId')} path='userId'>
                  <NInput
                    placeholder={t('security.whitelist.userId_tips')}
                    v-model={[this.model.userId, 'value']}
                  />
                </NFormItem>
              </NForm>
            )
          }}
        </Modal>
      </div>
    )
  }
})

export default WhitelistModal
