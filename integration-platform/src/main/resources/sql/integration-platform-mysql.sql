CREATE TABLE IF NOT EXISTS `t_dsx_column_info`
(
    ID               int AUTO_INCREMENT COMMENT '主键id'
        PRIMARY KEY,
    TABLE_NAME       varchar(255) NOT NULL COMMENT '表名称',
    COLUMN_NAME      varchar(255) NULL COMMENT '字段名称',
    COLUMN_SIZE      varchar(255) NULL COMMENT '字段大小',
    DATA_TYPE        varchar(255) NULL COMMENT '数据类型',
    DECIMAL_DIGITS   varchar(255) NULL COMMENT '小数位数',
    IS_AUTOINCREMENT varchar(255) NULL COMMENT '是否自增',
    IS_NULLABLE      varchar(255) NULL COMMENT '是否可以为null',
    REMARKS          varchar(255) NULL COMMENT '备注',
    TYPE_NAME        varchar(255) NULL COMMENT '类型名称',
    COLUMN_DEF       varchar(255) NULL COMMENT '默认值',
    DATA_SOURCE_ID   int          NOT NULL COMMENT '数据源id'
)
    COMMENT '表字段信息';

CREATE TABLE IF NOT EXISTS `t_dsx_dnd_category`
(
    ID            bigint AUTO_INCREMENT COMMENT '主键'
        PRIMARY KEY,
    PID           bigint   DEFAULT 0                 NULL COMMENT '父级ID',
    NAME          varchar(100)                       NULL COMMENT '分类名称',
    SORT          tinyint                            NULL COMMENT '排序',
    CONFIGURATION text                               NULL COMMENT '分类配置',
    CREATED_BY    int                                NULL COMMENT '创建人',
    CREATE_TIME   datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    UPDATED_BY    int                                NULL COMMENT '更新人',
    UPDATE_TIME   datetime DEFAULT CURRENT_TIMESTAMP NULL COMMENT '更新时间'
)
    COMMENT 'DND 分类';

CREATE TABLE IF NOT EXISTS `t_dsx_dnd_component`
(
    ID            bigint AUTO_INCREMENT
        PRIMARY KEY,
    NAME          varchar(100)                       NULL COMMENT '组件名称',
    CID           bigint                             NOT NULL COMMENT 'DND分类',
    ICON          varchar(100)                       NULL COMMENT '组件图标',
    TYPE          varchar(100)                       NULL COMMENT '类型',
    CONFIGURATION text                               NULL COMMENT '参数配置项',
    REGISTRY_ID   bigint                             NOT NULL COMMENT '组件类型表主键',
    CREATED_BY    int                                NULL COMMENT '操作人',
    CREATE_TIME   datetime DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
    UPDATE_TIME   datetime DEFAULT CURRENT_TIMESTAMP NULL COMMENT '更新时间',
    REFINEMENT    tinyint                            NULL COMMENT '是否可继续细化',
    DESCRIPTION   varchar(100)                       NULL COMMENT '描述',
    SORT          tinyint                            NULL COMMENT 'xuhoa ',
    UPDATED_BY    int                                NULL COMMENT '更新者'
)
    COMMENT '任务组件注册表';

CREATE TABLE IF NOT EXISTS `t_dsx_dnd_registry`
(
    ID          bigint AUTO_INCREMENT COMMENT '主键'
        PRIMARY KEY,
    KEY_ID      varchar(100)                       NULL COMMENT 'render_key',
    CREATE_TIME datetime DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
    UPDATE_TIME datetime DEFAULT CURRENT_TIMESTAMP NULL COMMENT '更新时间',
    NAME        varchar(100)                       NULL,
    SORT        tinyint                            NULL COMMENT '排序',
    REMARK      varchar(100)                       NULL COMMENT '备注',
    CREATED_BY  int                                NULL COMMENT '创建者',
    UPDATED_BY  int                                NULL COMMENT '更新者'
)
    COMMENT '任务组件类型';

CREATE TABLE IF NOT EXISTS `t_dsx_driver_info`
(
    ID           varchar(32)                        NOT NULL COMMENT '主键id'
        PRIMARY KEY,
    TITLE        varchar(32)                        NULL COMMENT '标题',
    DRIVER_NAME  varchar(32)                        NULL COMMENT '驱动程序类名',
    DRIVER_URL   varchar(32)                        NULL COMMENT '驱动地址',
    CREATED_ID   varchar(32)                        NULL COMMENT '创建人id',
    CREATED_NAME varchar(32)                        NULL COMMENT '创建人名称',
    CREATED_TIME datetime DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
    UPDATED_ID   varchar(32)                        NULL COMMENT '修改人id',
    UPDATED_NAME varchar(32)                        NULL COMMENT '修改人名称',
    UPDATED_TIME datetime DEFAULT CURRENT_TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    DS_TYPE      varchar(32)                        NULL COMMENT '数据库类型(如MYSQL,H2,SQLSERVER等)'
)
    COLLATE = UTF8MB4_GENERAL_CI;

CREATE TABLE IF NOT EXISTS `t_dsx_job`
(
    ID           bigint AUTO_INCREMENT COMMENT '主键'
        PRIMARY KEY,
    CONTENT      text                               NULL COMMENT '任务自定义JSON',
    CONTENT_HASH varchar(100)                       NULL COMMENT '任务JSON的哈希值',
    CREATED_BY   int                                NULL COMMENT '创建人',
    CREATE_TIME  datetime DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
    UPDATE_TIME  datetime DEFAULT CURRENT_TIMESTAMP NULL COMMENT '更新时间',
    PROJECT_ID   bigint                             NULL COMMENT '项目ID',
    JOB_ID       bigint                             NULL COMMENT '作业定义ID',
    PUSH         tinyint  DEFAULT 0                 NULL COMMENT '是否推送到海豚调度',
    UPDATED_BY   int                                NULL COMMENT '更新操作者'
)
    COMMENT 'DSX 自定义组件';

CREATE TABLE IF NOT EXISTS `t_dsx_network`
(
    ID          bigint AUTO_INCREMENT COMMENT '主键ID'
        PRIMARY KEY,
    USER_ID     varchar(100)                       NULL COMMENT '用户ID',
    IP_SEGMENT  varchar(100)                       NULL COMMENT 'IP地址',
    CREATE_TIME datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    UPDATE_TIME datetime DEFAULT CURRENT_TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    IP_TYPE     smallint                           NULL COMMENT 'IP类型（1：IP地址，2：IP网段）'
);

CREATE TABLE IF NOT EXISTS `t_dsx_sys_config`
(
    ID           varchar(32)                            NOT NULL COMMENT '主键id'
        PRIMARY KEY,
    NAME         varchar(32)  DEFAULT ''                NULL COMMENT '参数名称',
    PARAM_LABLE  varchar(32)  DEFAULT ''                NULL COMMENT '参数键名',
    PARAM_VALUE  varchar(255) DEFAULT ''                NULL COMMENT '参数键值',
    IS_ENABLE    tinyint      DEFAULT 0                 NULL COMMENT '是否启用(0:否 1:是)',
    CREATED_TIME datetime     DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
    CREATED_ID   varchar(32)  DEFAULT ''                NULL COMMENT '创建人',
    CREATED_USER varchar(32)  DEFAULT ''                NULL COMMENT '创建人姓名',
    UPDATED_ID   varchar(32)  DEFAULT ''                NULL COMMENT '修改人id',
    UPDATED_USER varchar(32)  DEFAULT ''                NULL COMMENT '修改人姓名',
    UPDATED_TIME datetime     DEFAULT CURRENT_TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
)
    COLLATE = UTF8MB4_GENERAL_CI;

CREATE TABLE IF NOT EXISTS `t_dsx_table_info`
(
    ID             int AUTO_INCREMENT COMMENT '主键id'
        PRIMARY KEY,
    TABLE_NAME     varchar(255) NULL COMMENT '表名称',
    REF_GENERATION varchar(255) NULL,
    REMARKS        varchar(255) NULL COMMENT '表备注',
    TABLE_CAT      varchar(255) NULL,
    TABLE_SCHEM    varchar(255) NULL,
    TABLE_TYPE     varchar(255) NULL,
    TYPE_CAT       varchar(255) NULL,
    TYPE_NAME      varchar(255) NULL,
    TYPE_SCHEM     varchar(255) NULL,
    DATA_SOURCE_ID int          NOT NULL COMMENT '数据源id'
)
    COMMENT '表信息';

