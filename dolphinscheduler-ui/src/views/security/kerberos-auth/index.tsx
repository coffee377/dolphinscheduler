import { defineComponent } from 'vue'
import { NDescriptions, NDescriptionsItem } from 'naive-ui'
import { repeat } from 'lodash'

const KerberosAuth = defineComponent({
  name: 'kerberos-auth',
  render: () => {
    return (
      <>
        <NDescriptions label-placement={'top'} bordered column={1}>
          <NDescriptionsItem label={'用户认证令牌'}>
            <div>{repeat('*', 32)}</div>
          </NDescriptionsItem>
        </NDescriptions>
      </>
    )
  }
})

export default KerberosAuth
