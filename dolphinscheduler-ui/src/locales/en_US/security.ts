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

export default {
  kerberos: {
    auth: 'Kerberos Authentication'
  },
  tenant: {
    tenant_manage: 'Tenant Manage',
    create_tenant: 'Create Tenant',
    search_tips: 'Please enter keywords',
    tenant_code: 'Operating System Tenant',
    description: 'Description',
    queue_name: 'QueueName',
    create_time: 'Create Time',
    update_time: 'Update Time',
    actions: 'Operation',
    edit_tenant: 'Edit Tenant',
    tenant_code_tips: 'Please enter the operating system tenant',
    queue_name_tips: 'Please select queue',
    description_tips: 'Please enter a description',
    delete_confirm: 'Delete?',
    edit: 'Edit',
    delete: 'Delete'
  },
  alarm_group: {
    create_alarm_group: 'Create Alarm Group',
    edit_alarm_group: 'Edit Alarm Group',
    search_tips: 'Please enter keywords',
    alert_group_name_tips: 'Please enter your alert group name',
    alarm_plugin_instance: 'Alarm Plugin Instance',
    alarm_plugin_instance_tips: 'Please select alert plugin instance',
    alarm_group_description_tips: 'Please enter your alarm group description',
    alert_group_name: 'Alert Group Name',
    alarm_group_description: 'Alarm Group Description',
    create_time: 'Create Time',
    update_time: 'Update Time',
    operation: 'Operation',
    delete_confirm: 'Delete?',
    edit: 'Edit',
    delete: 'Delete'
  },
  worker_group: {
    create_worker_group: 'Create Worker Group',
    edit_worker_group: 'Edit Worker Group',
    search_tips: 'Please enter keywords',
    operation: 'Operation',
    delete_confirm: 'Delete?',
    edit: 'Edit',
    delete: 'Delete',
    group_name: 'Group Name',
    group_name_tips: 'Please enter your group name',
    worker_addresses: 'Worker Addresses',
    worker_addresses_tips: 'Please select worker addresses',
    create_time: 'Create Time',
    update_time: 'Update Time'
  },
  yarn_queue: {
    create_queue: 'Create Queue',
    edit_queue: 'Edit Queue',
    search_tips: 'Please enter keywords',
    queue_name: 'Queue Name',
    queue_value: 'Queue Value',
    create_time: 'Create Time',
    update_time: 'Update Time',
    operation: 'Operation',
    edit: 'Edit',
    queue_name_tips: 'Please enter your queue name',
    queue_value_tips: 'Please enter your queue value'
  },
  environment: {
    create_environment: 'Create Environment',
    edit_environment: 'Edit Environment',
    search_tips: 'Please enter keywords',
    edit: 'Edit',
    delete: 'Delete',
    environment_name: 'Environment Name',
    environment_config: 'Environment Config',
    environment_desc: 'Environment Desc',
    worker_groups: 'Worker Groups',
    create_time: 'Create Time',
    update_time: 'Update Time',
    operation: 'Operation',
    delete_confirm: 'Delete?',
    environment_name_tips: 'Please enter your environment name',
    environment_config_tips: 'Please enter your environment config',
    environment_description_tips: 'Please enter your environment description',
    worker_group_tips: 'Please select worker group'
  },
  token: {
    create_token: 'Create Token',
    edit_token: 'Edit Token',
    search_tips: 'Please enter keywords',
    user: 'User',
    user_tips: 'Please select user',
    token: 'Token',
    token_tips: 'Please click to get token',
    expiration_time: 'Expiration Time',
    expiration_time_tips: 'Please select expiration time',
    create_time: 'Create Time',
    update_time: 'Update Time',
    operation: 'Operation',
    edit: 'Edit',
    delete: 'Delete',
    delete_confirm: 'Delete?'
  },
  user: {
    user_manage: 'User Manage',
    create_user: 'Create User',
    edit_user: 'Edit User',
    delete_user: 'Delete User',
    delete_confirm: 'Are you sure to delete?',
    delete_confirm_tip:
      'Deleting user is a dangerous operation，please be careful',
    project: 'Project',
    resource: 'Resource',
    file_resource: 'File Resource',
    udf_resource: 'UDF Resource',
    datasource: 'Datasource',
    udf: 'UDF Function',
    namespace: 'Namespace',
    authorize_project: 'Project Authorize',
    authorize_resource: 'Resource Authorize',
    authorize_namespace: 'Namespace Authorize',
    authorize_datasource: 'Datasource Authorize',
    authorize_udf: 'UDF Function Authorize',
    username: 'Username',
    username_exists: 'The username already exists',
    username_tips: 'Please enter username',
    user_password: 'Password',
    user_password_tips:
      'Please enter a password containing letters and numbers with a length between 6 and 20',
    user_type: 'User Type',
    ordinary_user: 'Ordinary users',
    administrator: 'Administrator',
    tenant_code: 'Tenant',
    tenant_id_tips: 'Please select tenant',
    queue: 'Queue',
    queue_tips: 'Please select a queue',
    email: 'Email',
    email_empty_tips: 'Please enter email',
    emial_correct_tips: 'Please enter the correct email format',
    phone: 'Phone',
    phone_empty_tips: 'Please enter phone number',
    phone_correct_tips: 'Please enter the correct mobile phone format',
    state: 'State',
    state_enabled: 'Enabled',
    state_disabled: 'Disabled',
    create_time: 'Create Time',
    update_time: 'Update Time',
    operation: 'Operation',
    edit: 'Edit',
    delete: 'Delete',
    authorize: 'Authorize',
    save_error_msg: 'Failed to save, please retry',
    delete_error_msg: 'Failed to delete, please retry',
    auth_error_msg: 'Failed to authorize, please retry',
    auth_success_msg: 'Authorize succeeded',
    enable: 'Enable',
    disable: 'Disable'
  },
  alarm_instance: {
    search_input_tips: 'Please input the keywords',
    alarm_instance_manage: 'Alarm instance manage',
    alarm_instance_name: 'Alarm instance name',
    alarm_instance_name_tips: 'Please enter alarm plugin instance name',
    alarm_plugin_name: 'Alarm plugin name',
    create_time: 'Create Time',
    update_time: 'Update Time',
    operation: 'Operation',
    edit_alarm_instance: 'Edit Alarm Instance',
    delete: 'Delete',
    edit: 'Edit',
    delete_confirm: 'Delete?',
    confirm: 'Confirm',
    cancel: 'Cancel',
    submit: 'Submit',
    create_alarm_instance: 'Create Alarm Instance',
    select_plugin: 'Select plugin',
    select_plugin_tips: 'Select Alarm plugin',
    instance_parameter_exception: 'Instance parameter exception',
    WebHook: 'WebHook',
    webHook: 'WebHook',
    WarningType: 'Warning Type',
    IsEnableProxy: 'Enable Proxy',
    Proxy: 'Proxy',
    Port: 'Port',
    User: 'User',
    corpId: 'CorpId',
    secret: 'Secret',
    Secret: 'Secret',
    users: 'Users',
    userSendMsg: 'UserSendMsg',
    'agentId/chatId': 'AgentId or ChatId',
    showType: 'Show Type',
    receivers: 'Receivers',
    receiverCcs: 'ReceiverCcs',
    serverHost: 'SMTP Host',
    serverPort: 'SMTP Port',
    sender: 'Sender',
    enableSmtpAuth: 'SMTP Auth',
    Password: 'Password',
    starttlsEnable: 'SMTP STARTTLS Enable',
    sslEnable: 'SMTP SSL Enable',
    smtpSslTrust: 'SMTP SSL Trust',
    url: 'URL',
    requestType: 'Request Type',
    headerParams: 'Headers',
    bodyParams: 'Body',
    contentField: 'Content Field',
    Keyword: 'Keyword',
    userParams: 'User Params',
    path: 'Script Path',
    type: 'Type',
    sendType: 'Send Type',
    username: 'Username',
    botToken: 'Bot Token',
    chatId: 'Channel Chat Id',
    parseMode: 'Parse Mode',
    IntegrationKey: 'Integration Key',
    BotAccessToken: 'Bot Access Token',
    RoomId: 'Room Id',
    ToPersonId: 'To Person Id',
    ToPersonEmail: 'To Person Email',
    AtSomeoneInRoom: 'At Someone In Room',
    Destination: 'Destination',
    AtMobiles: 'At User Mobiles',
    AtUserIds: 'At User Ids',
    MsgType: 'Msg Type',
    // eslint-disable-next-line quotes
    IsAtAll: "{'@'}All"
  },
  k8s_namespace: {
    create_namespace: 'Create Namespace',
    edit_namespace: 'Edit Namespace',
    search_tips: 'Please enter keywords',
    k8s_namespace: 'K8S Namespace',
    k8s_namespace_tips: 'Please enter k8s namespace',
    k8s_cluster: 'K8S Cluster',
    k8s_cluster_tips: 'Please enter k8s cluster',
    owner: 'Owner',
    owner_tips: 'Please enter owner',
    limit_cpu: 'Limit CPU',
    limit_cpu_tips: 'Please enter limit CPU',
    limit_memory: 'Limit Memory',
    limit_memory_tips: 'Please enter limit memory',
    create_time: 'Create Time',
    update_time: 'Update Time',
    operation: 'Operation',
    edit: 'Edit',
    delete: 'Delete',
    delete_confirm: 'Delete?'
  },
  sys_config: {
    management: 'Management',
    create: 'Create',
    edit_params: 'Edit',
    operation: 'Operation',
    edit: 'Edit',
    delete: 'Delete',
    delete_confirm: 'Delete?',
    index: '#',
    parameter_name: 'Name',
    parameter_key: 'Key',
    parameter_value: 'Value',
    state: 'State',
    state_enabled: 'enabled',
    state_disabled: 'disabled',
    created_time: 'Create Time',
    parameter_name_tips: 'Please enter name',
    parameter_key_tips: 'Please enter key',
    parameter_value_tips: 'Please enter value',
    search_tips: 'Please enter keywords'
  }
}
