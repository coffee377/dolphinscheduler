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

import { useAsyncState } from '@vueuse/core'
import { reactive, h, ref } from 'vue'
import { format } from 'date-fns'
import { NButton, NIcon, NPopconfirm, NSpace, NTag, NTooltip } from 'naive-ui'
import { useI18n } from 'vue-i18n'
import { queryWhitelist } from '@/service/modules/whitelist'
import { DeleteOutlined, EditOutlined } from '@vicons/antd'
import { parseTime } from '@/common/common'
import type { ListReq, ListResData } from '@/service/modules/whitelist/type'
import { useMessage } from 'naive-ui'
import { deleteWhitelist, whitelistDetail } from '@/service/modules/whitelist'
import {
  COLUMN_WIDTH_CONFIG,
  calculateTableWidth,
  DefaultTableWidth
} from '@/common/column-width-config'
export function useTable() {
  const { t } = useI18n()
  const state = reactive({
    columns: [],
    tableData: [],
    pageNum: ref(1),
    pageSize: ref(10),
    searchVal: ref(''),
    total: ref(1),
    showModalRef: ref(false),
    typeRef: ref<'create' | 'update' | 'view' | String>('create'),
    row: {},
    loadingRef: ref(false),
    tableWidth: DefaultTableWidth
  })

  const createColumns = (state: any) => {
    state.columns = [
      {
        title: '#',
        key: 'index',
        render: (row: any, index: number) => index + 1,
        ...COLUMN_WIDTH_CONFIG['index']
      },
      {
        title: t('security.whitelist.userId'),
        key: 'userId',
        ...COLUMN_WIDTH_CONFIG['userName']
      },
      {
        title: t('security.whitelist.ip'),
        key: 'ipSegment',
        ...COLUMN_WIDTH_CONFIG['name']
      },
      {
        title: t('security.whitelist.ipType'),
        key: 'ipType',
        render: (row: any) => {
          return h('span', row.ipType == 0 ? 'IPv4' : 'IPv6')
        },
        ...COLUMN_WIDTH_CONFIG['userName']
      },
      {
        title: t('security.whitelist.create_time'),
        key: 'createTime',
        ...COLUMN_WIDTH_CONFIG['time']
      },
      {
        title: t('security.whitelist.update_time'),
        key: 'updateTime',
        ...COLUMN_WIDTH_CONFIG['time']
      },

      {
        title: t('security.whitelist.operation'),
        key: 'operation',
        render(row: any) {
          return h(NSpace, null, {
            default: () => [
              h(
                NTooltip,
                {},
                {
                  trigger: () =>
                    h(
                      NButton,
                      {
                        circle: true,
                        type: 'info',
                        size: 'small',
                        onClick: () => {
                          handleEdit(row)
                        }
                      },
                      {
                        icon: () =>
                          h(NIcon, null, { default: () => h(EditOutlined) })
                      }
                    ),
                  default: () => t('security.sys_config.edit')
                }
              ),
              h(
                NPopconfirm,
                {
                  onPositiveClick: () => {
                    handleDelete(row)
                  }
                },
                {
                  trigger: () =>
                    h(
                      NTooltip,
                      {},
                      {
                        trigger: () =>
                          h(
                            NButton,
                            {
                              circle: true,
                              type: 'error',
                              size: 'small'
                            },
                            {
                              icon: () =>
                                h(NIcon, null, {
                                  default: () => h(DeleteOutlined)
                                })
                            }
                          ),
                        default: () => t('security.sys_config.delete')
                      }
                    ),
                  default: () => t('security.sys_config.delete_confirm')
                }
              )
            ]
          })
        },
        ...COLUMN_WIDTH_CONFIG['operation'](2)
      }
    ]
    if (state.tableWidth) {
      state.tableWidth = calculateTableWidth(state.columns)
    }
  }
  const message = useMessage()

  const handleDelete = (row: any) => {
    deleteWhitelist(row.id).then((res: any) => {
      if (res.status == '200') {
        message.success('已删除')
        getTableData({
          pageSize: state.pageSize,
          pageNum:
            state.tableData.length === 1 && state.pageNum > 1
              ? state.pageNum - 1
              : state.pageNum,
          ipSegment: state.searchVal
        })
        state.pageNum =
          state.tableData.length === 1 && state.pageNum > 1
            ? state.pageNum - 1
            : state.pageNum
      } else {
        message.error(res.msg)
      }
    })
  }

  const handleEdit = (row: any) => {
    whitelistDetail(row.id).then((res: any) => {
      state.showModalRef = true
      state.typeRef = 'update'
      state.row = res.data
    })
  }
  const handleOpen = () => {
    state.showModalRef = true
    state.typeRef = 'create'
  }
  const handleCancel = () => {
    state.showModalRef = false
  }
  const handleConfirm = () => {
    state.showModalRef = false
    getTableData({
      pageSize: state.pageSize,
      pageNum: state.pageNum,
      ipSegment: state.searchVal
    })
  }
  const getTableData = (params: ListReq) => {
    if (state.loadingRef) return
    state.loadingRef = true
    const { state: data } = useAsyncState(
      queryWhitelist(params).then((res: ListResData) => {
        if (res.status == 200) {
          state.tableData = res.data.records.map((item) => {
            item.createTime = format(
              parseTime(item.createTime),
              'yyyy-MM-dd HH:mm:ss'
            )
            item.updateTime = format(
              parseTime(item.updateTime),
              'yyyy-MM-dd HH:mm:ss'
            )

            return {
              ...item
            }
          }) as any
          state.total = res.data.total
          state.loadingRef = false
        }
      }),
      {}
    )
    return data
  }

  return {
    state,
    getTableData,
    createColumns,
    handleEdit,
    handleCancel,
    handleConfirm,
    handleOpen
  }
}
