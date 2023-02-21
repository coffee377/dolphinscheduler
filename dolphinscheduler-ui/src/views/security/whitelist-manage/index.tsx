import { defineComponent, toRefs, onMounted } from 'vue'
import {
  NButton,
  NInput,
  NIcon,
  NCard,
  NSpace,
  NDataTable,
  NPagination
} from 'naive-ui'
import Card from '@/components/card'
import { useI18n } from 'vue-i18n'
import { SearchOutlined } from '@vicons/antd'
import { useTable } from '../whitelist-manage/use-table'
import WhitelistModal from './components/whitelist-modal'
import styles from './index.module.scss'
const WhitelistManage = defineComponent({
  name: 'whitelist-manage',
  setup() {
    const { t } = useI18n()
    const {
      state,
      getTableData,
      createColumns,
      handleEdit,
      handleCancel,
      handleConfirm,
      handleOpen
    } = useTable()
    const requestData = () => {
      getTableData({
        pageSize: state.pageSize,
        pageNum: state.pageNum,
        ipSegment: state.searchVal
      })
    }
    const handleChangePageSize = () => {
      state.pageNum = 1
      requestData()
    }

    onMounted(() => {
      createColumns(state)
      requestData()
    })
    return {
      t,
      ...toRefs(state),
      handleEdit,
      handleChangePageSize,
      handleCancel,
      handleConfirm,
      handleOpen,
      requestData
    }
  },
  render() {
    return (
      <div class={styles.container}>
        <NCard>
          <div class={styles.header}>
            <NSpace>
              <NInput
                placeholder={this.t('security.whitelist.search_tips')}
                v-model:value={this.searchVal}
                clearable
                onClear={this.requestData}
              />
              <NButton type='primary' onClick={this.requestData}>
                <NIcon>
                  <SearchOutlined />
                </NIcon>
              </NButton>
            </NSpace>
            <NButton
              onClick={this.handleOpen}
              type='primary'
              class='btn-create-user'
            >
              {this.t('security.whitelist.create')}
            </NButton>
          </div>
        </NCard>
        <Card class={styles['table-card']}>
          <NDataTable
            loading={this.loadingRef}
            columns={this.columns}
            data={this.tableData}
            row-class-name='items'
            scrollX={this.tableWidth}
          />
          <div class={styles.pagination}>
            <NPagination
              v-model:page={this.pageNum}
              v-model:page-size={this.pageSize}
              page-count={this.total}
              show-size-picker
              page-sizes={[10, 30, 50]}
              show-quick-jumper
              onUpdatePage={this.requestData}
              onUpdatePageSize={this.handleChangePageSize}
            />
          </div>
        </Card>

        <WhitelistModal
          showModalRef={this.showModalRef}
          typeRef={this.typeRef}
          row={this.row}
          onCancelModal={this.handleCancel}
          onConfirmModal={this.handleConfirm}
        />
      </div>
    )
  }
})

export default WhitelistManage
