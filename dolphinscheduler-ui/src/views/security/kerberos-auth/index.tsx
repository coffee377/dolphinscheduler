import { defineComponent, reactive, toRefs, ref } from 'vue'
import {
  NCollapse,
  NCollapseItem,
  NFormItem,
  NRadio,
  NRadioGroup,
  NSpace,
  NForm,
  NInput,
  NInputNumber,
  NSelect,
  NAlert,
  NButton,
  NCheckbox,
  useMessage
} from 'naive-ui'
import { useI18n } from 'vue-i18n'
import styles from './index.module.scss'
const KerberosAuth = defineComponent({
  name: 'kerberos-auth',
  setup() {
    const { t } = useI18n()
    const message = useMessage()
    const state = reactive({
      kdcType: 1,
      kbSafe: 'ABC',
      kdcHost: 'ABCHOST',
      kdcAdminHost: 'ADMINHOST',
      domainName: [{ dName: 'NAME1' }, { dName: 'NAME2' }],
      kbEncryptionType: [{ type: 'MD5' }],
      kbLifeCycle: 7,
      kbLifeCycleUnit: 'day',
      userName: 'jack',
      pwd: '123456',
      dataNodePort: '1234',
      dataNodeHttpWebUiPort: '2233'
    })

    const agree = ref(false)

    const addDomainName = (i: number) => {
      if (i == -1) {
        state.domainName.push({ dName: '' })
      } else {
        const header = state.domainName.slice(0, i + 1)
        const footer = state.domainName.slice(i + 1)
        state.domainName = [...header, { dName: '' }, ...footer]
      }
    }
    const delDomainName = (i: number) => {
      state.domainName.splice(i, 1)
    }
    const addKerberosType = (i: number) => {
      if (i == -1) {
        state.kbEncryptionType.push({ type: '' })
      } else {
        const header = state.kbEncryptionType.slice(0, i + 1)
        const footer = state.kbEncryptionType.slice(i + 1)
        state.kbEncryptionType = [...header, { type: '' }, ...footer]
      }
    }
    const delKerberosType = (i: number) => {
      state.kbEncryptionType.splice(i, 1)
    }
    const submit = () => {
      if (agree.value) {
        message.destroyAll()
        message.success(t('security.kerberos.hasRestart'))
      } else {
        message.destroyAll()
        message.warning(t('security.kerberos.please_agree'))
      }
    }
    return {
      t,
      ...toRefs(state),
      agree,
      addDomainName,
      delDomainName,
      addKerberosType,
      delKerberosType,
      submit
    }
  },
  render() {
    return (
      <>
        <NForm label-placement={'left'} labelAlign={'left'} labelWidth={200}>
          <NCollapse defaultExpandedNames={[1, 2, 3]}>
            <NCollapseItem title={this.t('security.kerberos.kdc')} name={1}>
              {/*<NCard>*/}
              <NFormItem
                label={this.t('security.kerberos.kdc_type')}
                path={'kdcType'}
                labelStyle={
                  'padding-left:2rem;word-break:break-word;white-space:normal'
                }
              >
                <NRadioGroup v-model:value={this.kdcType}>
                  <NSpace>
                    <NRadio value={1} class='radio-state-enable'>
                      Active Directory
                    </NRadio>
                    <NRadio value={2} class='radio-state-disable'>
                      MIT KDC
                    </NRadio>
                  </NSpace>
                </NRadioGroup>
              </NFormItem>
              <NFormItem
                label={this.t('security.kerberos.kerberos_safe')}
                labelStyle={'textIndent:2rem'}
                path={'kbSafe'}
              >
                <NInput v-model={[this.kbSafe, 'value']} />
              </NFormItem>
              <NFormItem
                label={this.t('security.kerberos.kdc_serve_host')}
                labelStyle={
                  'padding-left:2rem;word-break:break-word;white-space:normal'
                }
                path={'kdcHost'}
              >
                <NInput v-model={[this.kdcHost, 'value']} />
              </NFormItem>
              <NFormItem
                label={this.t('security.kerberos.kdc_admin_host')}
                labelStyle={
                  'padding-left:2rem;word-break:break-word;white-space:normal'
                }
                path={'kdcAdminHost'}
              >
                <NInput v-model={[this.kdcAdminHost, 'value']} />
              </NFormItem>
              <NFormItem
                label={this.t('security.kerberos.domain_name')}
                labelStyle={
                  'padding-left:2rem;word-break:break-word;white-space:normal'
                }
              >
                <div class={styles.domainName}>
                  {this.domainName.length == 0 ? (
                    <span
                      class={styles.span}
                      onClick={this.addDomainName.bind(this, -1)}
                    >
                      +
                    </span>
                  ) : (
                    ''
                  )}

                  {this.domainName.map((item, i) => {
                    return (
                      <>
                        <NInput
                          v-model={[this.domainName[i].dName, 'value']}
                          class={styles.input}
                        />
                        <span
                          class={styles.span}
                          onClick={this.addDomainName.bind(this, i)}
                        >
                          +
                        </span>
                        <span
                          class={styles.span}
                          onClick={this.delDomainName.bind(this, i)}
                        >
                          -
                        </span>
                      </>
                    )
                  })}
                </div>
              </NFormItem>
              <NFormItem
                label={this.t('security.kerberos.kerberos_encryption_type')}
                labelStyle={
                  'padding-left:2rem;word-break:break-word;white-space:normal'
                }
              >
                <div class={styles.domainName}>
                  {this.kbEncryptionType.length == 0 ? (
                    <span
                      class={styles.span}
                      onClick={this.addKerberosType.bind(this, -1)}
                    >
                      +
                    </span>
                  ) : (
                    ''
                  )}

                  {this.kbEncryptionType.map((item, i) => {
                    return (
                      <>
                        <NInput
                          v-model={[this.kbEncryptionType[i].type, 'value']}
                          class={styles.input}
                        />
                        <span
                          class={styles.span}
                          onClick={this.addKerberosType.bind(this, i)}
                        >
                          +
                        </span>
                        <span
                          class={styles.span}
                          onClick={this.delKerberosType.bind(this, i)}
                        >
                          -
                        </span>
                      </>
                    )
                  })}
                </div>
              </NFormItem>
              <NFormItem
                label={this.t('security.kerberos.kerberos_life_cycle')}
                labelStyle={
                  'padding-left:2rem;word-break:break-word;white-space:normal'
                }
                path={'kbLifeCycle'}
              >
                <NInputNumber
                  v-model={[this.kbLifeCycle, 'value']}
                  min={0}
                  clearable
                />
                <NSelect
                  v-model={[this.kbLifeCycleUnit, 'value']}
                  options={[
                    { label: this.t('security.kerberos.day'), value: 'day' },
                    { label: this.t('security.kerberos.week'), value: 'week' },
                    { label: this.t('security.kerberos.month'), value: 'month' }
                  ]}
                  defaultValue={'day'}
                  clearable={false}
                  class={styles.select}
                />
              </NFormItem>
              {/*</NCard>*/}
            </NCollapseItem>
            <NCollapseItem
              title={this.t('security.kerberos.kdc_account_manager')}
              name={2}
            >
              <NFormItem
                label={this.t('security.kerberos.user_name')}
                labelStyle={
                  'padding-left:2rem;word-break:break-word;white-space:normal'
                }
                class={styles.userPwd}
                path={'userName'}
              >
                <NInput v-model={[this.userName, 'value']} />
              </NFormItem>
              <NFormItem
                label={this.t('security.kerberos.pwd')}
                labelStyle={
                  'padding-left:2rem;word-break:break-word;white-space:normal'
                }
                class={styles.userPwd}
                path={'pwd'}
              >
                <NInput
                  v-model={[this.pwd, 'value']}
                  type={'password'}
                  show-password-on={'mousedown'}
                  clearable
                />
              </NFormItem>
            </NCollapseItem>
            <NCollapseItem
              title={this.t('security.kerberos.port_config')}
              name={3}
            >
              {' '}
              <NFormItem
                label={this.t('security.kerberos.data_node_port')}
                labelStyle={
                  'padding-left:2rem;word-break:break-word;white-space:normal'
                }
                class={styles.userPwd}
                path={'dataNodePort'}
              >
                <NInput v-model={[this.dataNodePort, 'value']} />
              </NFormItem>
              <NFormItem
                label={this.t('security.kerberos.data_node_http_port')}
                labelStyle={
                  'padding-left:2rem;word-break:break-word;white-space:normal'
                }
                class={styles.userPwd}
                path={'dataNodeHttpWebUiPort'}
              >
                <NInput v-model={[this.dataNodeHttpWebUiPort, 'value']} />
              </NFormItem>{' '}
            </NCollapseItem>
          </NCollapse>
        </NForm>
        <NAlert
          title={this.t('security.kerberos.agree_tips')}
          type={'info'}
          class={styles.alert}
        >
          <div class={styles.check}>
            <NCheckbox
              size={'small'}
              label={this.t('security.kerberos.agree')}
              v-model={[this.agree, 'checked']}
            />
            <NButton type={'info'} onClick={this.submit} size={'small'}>
              {this.t('security.kerberos.restart')}
            </NButton>
          </div>
        </NAlert>
      </>
    )
  }
})

export default KerberosAuth
