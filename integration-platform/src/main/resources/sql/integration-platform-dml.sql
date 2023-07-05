# SHOW DATABASES;
# CREATE DATABASE IF NOT EXISTS `integration-platform` DEFAULT CHARSET UTF8 COLLATE UTF8_GENERAL_CI;

TRUNCATE TABLE `t_dsx_dnd_category`;
TRUNCATE TABLE `t_dsx_dnd_registry`;
TRUNCATE TABLE `t_dsx_dnd_component`;

# 组件分类初始化
INSERT INTO `t_dsx_dnd_category` (NAME, SORT, CONFIGURATION, CREATED_BY, CREATE_TIME, UPDATED_BY, UPDATE_TIME)
VALUES ('任务类型', 1, NULL, NULL, '2023-06-27 11:10:29', NULL, '2023-06-27 11:10:29'),
       ('数据读写', 2, NULL, NULL, '2023-06-27 11:10:29', NULL, '2023-06-27 11:10:29'),
       ('数据转换', 3, NULL, NULL, '2023-06-27 11:10:29', NULL, '2023-06-27 11:10:29'),
       ('库级同步', 4, NULL, NULL, '2023-06-27 12:39:45', NULL, '2023-06-27 12:39:45');

# dnd 组件注册
INSERT INTO `t_dsx_dnd_registry` (KEY_ID, NAME, SORT, REMARK, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY)
VALUES ('DSTask', '海豚任务插件', 1, NULL, '2023-06-27 11:10:51', '2023-06-27 11:10:51', NULL, NULL),
       ('DataXPlugin', 'DataX插件', 2, NULL, '2023-06-27 11:10:51', '2023-06-27 11:10:51', NULL, NULL),
       ('DBPlugin', '数据库插件', 3, NULL, '2023-06-27 11:10:51', '2023-06-27 11:10:51', NULL, NULL);

# 任务类型组件初始化

INSERT INTO `t_dsx_dnd_component` (NAME, CID, ICON, TYPE, CONFIGURATION, REGISTRY_ID, CREATED_BY, CREATE_TIME,
                                   UPDATE_TIME, REFINEMENT, DESCRIPTION, SORT, UPDATED_BY)
VALUES ('SHELL', 1, 'shell', NULL, NULL, 1, NULL, '2023-06-27 11:10:51', '2023-06-27 11:10:51', 0, NULL, 0, NULL),
       ('SUB_PROCESS', 1, 'sub_process', NULL, NULL, 1, NULL, '2023-06-27 11:10:51', '2023-06-27 11:10:51', 0, NULL, 2, NULL),
       ('PROCEDURE', 1, 'procedure', NULL, NULL, 1, NULL, '2023-06-27 11:10:51', '2023-06-27 11:10:51', 0, NULL, 3, NULL),
       ('SQL', 1, 'sql', NULL, NULL, 1, NULL, '2023-06-27 11:10:51', '2023-06-27 11:10:51', 0, NULL, 4, NULL),
       ('SPARK', 1, 'spark', NULL, NULL, 1, NULL, '2023-06-27 11:10:51', '2023-06-27 11:10:51', 0, NULL, 5, NULL),
       ('FLINK', 1, 'flink', NULL, NULL, 1, NULL, '2023-06-27 11:10:51', '2023-06-27 11:10:51', 0, NULL, 6, NULL),
       ('MR', 1, 'mr', NULL, NULL, 1, NULL, '2023-06-27 11:10:51', '2023-06-27 11:10:51', 0, NULL, 7, NULL),
       ('PYTHON', 1, 'python', NULL, NULL, 1, NULL, '2023-06-27 11:10:51', '2023-06-27 11:10:51', 0, NULL, 8, NULL),
       ('DEPENDENT', 1, 'dependent', NULL, NULL, 1, NULL, '2023-06-27 11:10:51', '2023-06-27 11:10:51', 0, NULL, 9, NULL),
       ('HTTP', 1, 'http', NULL, NULL, 1, NULL, '2023-06-27 11:10:51', '2023-06-27 11:10:51', 0, NULL, 10, NULL),
       ('DATAX', 1, 'datax', NULL, '{"width": 400, "height": 300}', 1, NULL, '2023-06-27 11:10:51', '2023-06-27 11:10:51', 1, NULL, 11, NULL),
       ('PIGEON', 1, 'pigeon', NULL, NULL, 1, NULL, '2023-06-27 11:10:51', '2023-06-27 11:10:51', 0, NULL, 12, NULL),
       ('SQOOP', 1, 'sqoop', NULL, NULL, 1, NULL, '2023-06-27 11:10:51', '2023-06-27 11:10:51', 0, NULL, 13, NULL),
       ('CONDITIONS', 1, 'conditions', NULL, NULL, 1, NULL, '2023-06-27 11:10:51', '2023-06-27 11:10:51', 0, NULL, 14, NULL),
       ('SWITCH', 1, 'switch', NULL, NULL, 1, NULL, '2023-06-27 11:10:51', '2023-06-27 11:10:51', 0, NULL, 15, NULL),
       ('SEATUNNEL', 1, 'seatunnel', NULL, NULL, 1, NULL, '2023-06-27 11:10:51', '2023-06-27 11:10:51', 0, NULL, 16, NULL),
       ('MySQL', 2, 'mysql', NULL, '{"supports": 3, "hiddenLabel": false, "targetRenderKey": ["DSTask"], "defaultPluginType": "reader"}', 2, NULL, '2023-06-27 11:10:51', '2023-06-27 11:10:51', 0, NULL, 1, NULL),
       ('Oracle', 2, NULL, NULL, '{"supports": 1, "targetRenderKey": ["DSTask"], "defaultPluginType": "reader"}', 2, NULL, '2023-06-27 11:10:51', '2023-06-27 11:10:51', 0, NULL, 2, NULL),
       ('Hive', 2, 'hive', NULL, '{"supports": 2, "targetRenderKey": ["DSTask"], "defaultPluginType": "writer"}', 2, NULL, '2023-06-27 11:10:51', '2023-06-27 11:10:51', 0, NULL, 3, NULL),
       ('数据脱敏', 3, 'desensitization', NULL, '{"supports": 4, "hiddenLabel": false, "targetRenderKey": ["DSTask"], "defaultPluginType": "transformer"}', 2, NULL, '2023-06-27 11:10:52', '2023-06-27 11:10:52', 0, NULL, 1, NULL),
       ('数据过滤', 3, 'filter', NULL, '{"supports": 4, "targetRenderKey": ["DSTask"], "defaultPluginType": "transformer"}', 2, NULL, '2023-06-27 11:10:52', '2023-06-27 11:10:52', 0, NULL, 2, NULL),
       ('字符串替换', 3, 'replace', NULL, '{"supports": 4, "targetRenderKey": ["DSTask"], "defaultPluginType": "transformer"}', 2, NULL, '2023-06-27 11:10:52', '2023-06-27 11:10:52', 0, NULL, 3, NULL),
       ('码表查询', 3, 'dict', NULL, '{"supports": 4, "targetRenderKey": ["DSTask"], "defaultPluginType": "transformer"}', 2, NULL, '2023-06-27 11:10:52', '2023-06-27 11:10:52', 0, NULL, 4, NULL),
       ('增加字段', 3, 'field-add', NULL, '{"supports": 4, "targetRenderKey": ["DSTask"], "defaultPluginType": "transformer"}', 2, NULL, '2023-06-27 11:10:52', '2023-06-27 11:10:52', 0, NULL, 1, NULL),
       ('数据校验', 3, 'data-check', NULL, '{"supports": 4, "targetRenderKey": ["DSTask"], "defaultPluginType": "transformer"}', 2, NULL, '2023-06-27 11:10:52', '2023-06-27 11:10:52', 0, NULL, 2, NULL),
       ('MySQL', 4, 'mysql', NULL, '{"supports": 3, "defaultPluginType": "reader"}', 3, NULL, '2023-06-27 11:10:51', '2023-06-27 11:10:51', 0, NULL, 1, NULL),
       ('Oracle', 4, NULL, NULL, '{"supports": 1, "defaultPluginType": "reader"}', 3, NULL, '2023-06-27 11:10:51', '2023-06-27 11:10:51', 0, NULL, 2, NULL),
       ('Hive', 4, 'hive', NULL, '{"supports": 2, "defaultPluginType": "writer"}', 3, NULL, '2023-06-27 11:10:51', '2023-06-27 11:10:51', 0, NULL, 3, NULL);

