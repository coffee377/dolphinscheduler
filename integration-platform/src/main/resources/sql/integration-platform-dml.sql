# SHOW DATABASES;
# CREATE DATABASE IF NOT EXISTS `integration-platform` DEFAULT CHARSET UTF8 COLLATE UTF8_GENERAL_CI;

TRUNCATE TABLE `t_dsx_dnd_category`;
TRUNCATE TABLE `t_dsx_dnd_registry`;
TRUNCATE TABLE `t_dsx_dnd_component`;

# 组件分类初始化
INSERT INTO `t_dsx_dnd_category` (NAME, SORT)
VALUES ('任务类型', 1),
       ('数据读写', 2),
       ('数据转换', 3);

# dnd 组件注册
INSERT INTO `t_dsx_dnd_registry` (KEY_ID, NAME, SORT)
VALUES ('DSTask', '海豚任务', 1),
       ('DataXPlugin', 'DataX插件', 2);

SET @JSON = '[
  {
    "id": "1",
    "header": "任务类型",
    "children": [
      {
        "id": "95629df8-e71c-47ff-894d-4bf0dcee7419",
        "label": "SHELL",
        "renderKey": "DSTask",
        "isGroup": false,
        "icon": "shell"
      },
      {
        "id": "9953842a-b601-4198-8b3c-96c3b1515f5c",
        "label": "SUB_PROCESS",
        "renderKey": "DSTask",
        "isGroup": false,
        "icon": "sub_process"
      },
      {
        "id": "d46a7794-e99c-4460-a1e2-33cc08c7c16b",
        "label": "PROCEDURE",
        "renderKey": "DSTask",
        "isGroup": false,
        "icon": "procedure"
      },
      {
        "id": "e19eff35-548c-4dac-a633-8eef45317b93",
        "label": "SQL",
        "renderKey": "DSTask",
        "isGroup": false,
        "icon": "sql"
      },
      {
        "id": "2d7727a4-0170-4659-9f75-462eb00d7fbc",
        "label": "SPARK",
        "renderKey": "DSTask",
        "isGroup": false,
        "icon": "spark"
      },
      {
        "id": "fc265a88-98e2-4366-aded-825ffe8700b4",
        "label": "FLINK",
        "renderKey": "DSTask",
        "isGroup": false,
        "icon": "flink"
      },
      {
        "id": "616286ca-47c7-41e1-b0fa-105a1caed946",
        "label": "MR",
        "renderKey": "DSTask",
        "isGroup": false,
        "icon": "mr"
      },
      {
        "id": "12c1dfc3-89ce-45ff-a1e0-e6c667171de8",
        "label": "PYTHON",
        "renderKey": "DSTask",
        "isGroup": false,
        "icon": "python"
      },
      {
        "id": "e82f85d2-707e-4879-944c-fec2a2986bd6",
        "label": "DEPENDENT",
        "renderKey": "DSTask",
        "isGroup": false,
        "icon": "dependent"
      },
      {
        "id": "52ec6ba3-3afb-4301-a2e4-b25892eb3bf6",
        "label": "HTTP",
        "renderKey": "DSTask",
        "isGroup": false,
        "icon": "http"
      },
      {
        "id": "d6aa79a4-d634-4fac-9edf-06634431f335",
        "label": "DATAX",
        "renderKey": "DSTask",
        "isGroup": true,
        "icon": "datax",
        "configuration": {
          "width": 400,
          "height": 300
        },
        "width": 400,
        "height": 300
      },
      {
        "id": "07f7d6be-db5a-4a3e-865f-547d5a0f8a8a",
        "label": "PIGEON",
        "renderKey": "DSTask",
        "isGroup": false,
        "icon": "pigeon"
      },
      {
        "id": "c4101813-aa55-4ad1-a677-01311e44a62c",
        "label": "SQOOP",
        "renderKey": "DSTask",
        "isGroup": false,
        "icon": "sqoop"
      },
      {
        "id": "26c8f722-37db-446b-bf12-4068578e55cb",
        "label": "CONDITIONS",
        "renderKey": "DSTask",
        "isGroup": false,
        "icon": "conditions"
      },
      {
        "id": "510c3a55-159e-45a7-b2b0-417eb51f14f4",
        "label": "SWITCH",
        "renderKey": "DSTask",
        "isGroup": false,
        "icon": "switch"
      },
      {
        "id": "fce3f6ac-c11f-4c26-9278-dd2c3aa199cb",
        "label": "SEATUNNEL",
        "renderKey": "DSTask",
        "isGroup": false,
        "icon": "seatunnel"
      }
    ],
    "isCollapsed": false,
    "data": {}
  },
  {
    "id": "2",
    "header": "数据读写",
    "isCollapsed": false,
    "children": [
      {
        "id": "6011b0b6-7c4c-4860-9203-7e7a26e4b9dd",
        "label": "MySQL",
        "renderKey": "DataXPlugin",
        "isGroup": false,
        "icon": "mysql",
        "configuration": {
          "defaultPluginType": "reader",
          "targetRenderKey": ["DSTask"],
          "supports": 3,
          "hiddenLabel": false
        },
        "width": 180,
        "height": 48
      },
      {
        "id": "448d9a98-3c3f-423e-adf0-fd2374f7ca0c",
        "label": "Oracle",
        "renderKey": "DataXPlugin",
        "isGroup": false,
        "configuration": {
          "defaultPluginType": "reader",
          "targetRenderKey": ["DSTask"],
          "supports": 1
        },
        "width": 180,
        "height": 48
      },
      {
        "id": "c81dae4b-b7df-4682-9e18-479f8e5b427a",
        "label": "Hive",
        "renderKey": "DataXPlugin",
        "isGroup": false,
        "icon": "hive",
        "configuration": {
          "defaultPluginType": "writer",
          "targetRenderKey": ["DSTask"],
          "supports": 2
        },
        "width": 180,
        "height": 48
      }
    ]
  },
  {
    "id": "4",
    "header": "数据转换",
    "children": [
      {
        "id": "27584e36-10b9-4d83-8b1c-2590a571eceb",
        "label": "数据脱敏",
        "renderKey": "DataXPlugin",
        "isGroup": false,
        "icon": "desensitization",
        "configuration": {
          "defaultPluginType": "transformer",
          "targetRenderKey": ["DSTask"],
          "supports": 4,
          "hiddenLabel": false
        },
        "width": 180,
        "height": 48
      },
      {
        "id": "4d5bda0b-0bc7-4fc9-87ea-eb06c8a1d5ed",
        "label": "数据过滤",
        "renderKey": "DataXPlugin",
        "isGroup": false,
        "icon": "filter",
        "configuration": {
          "defaultPluginType": "transformer",
          "targetRenderKey": ["DSTask"],
          "supports": 4
        },
        "width": 180,
        "height": 48
      },
      {
        "id": "cf5dc00e-c2ad-4049-9210-f9322118c868",
        "label": "数据替换",
        "renderKey": "DataXPlugin",
        "isGroup": false,
        "icon": "replace",
        "configuration": {
          "defaultPluginType": "transformer",
          "targetRenderKey": ["DSTask"],
          "supports": 4
        },
        "width": 180,
        "height": 48
      },
      {
        "id": "960eee78-115c-42cb-b670-027d39015e45",
        "label": "数据对照",
        "renderKey": "DataXPlugin",
        "isGroup": false,
        "icon": "dict",
        "configuration": {
          "defaultPluginType": "transformer",
          "targetRenderKey": ["DSTask"],
          "supports": 4
        },
        "width": 180,
        "height": 48
      }
    ]
  }
]';

# 任务类型组件初始化
SELECT ID
INTO @CATEGORY_ID
FROM `t_dsx_dnd_category`
WHERE NAME = '任务类型';
SELECT ID
INTO @REGISTRY_ID
FROM `t_dsx_dnd_registry`
WHERE KEY_ID = 'DSTask';

INSERT INTO `t_dsx_dnd_component` (CID, NAME, ICON, DESCRIPTION, REFINEMENT, REGISTRY_ID, CONFIGURATION, SORT)
SELECT @CATEGORY_ID                                                    AS CID,
       NAME,
       ICON,
       DESCRIPTION,
       CASE IS_GROUP WHEN 'true' THEN 1 WHEN 'false' THEN 0 ELSE 0 END AS REFINEMENT,
       @REGISTRY_ID                                                    AS REGISTRY_ID,
       CONFIGURATION,
       ROW_ID                                                          AS SORT
FROM JSON_TABLE(
             @JSON, '$[0].children[*]'
             COLUMNS (
                 ROW_ID FOR ORDINALITY,
                 NAME varchar(32) PATH '$.label',
                 ICON varchar(32) PATH '$.icon',
                 DESCRIPTION varchar(32) PATH '$.description',
                 IS_GROUP varchar(32) PATH '$.isGroup',
                 CONFIGURATION json PATH '$.configuration')
         ) AS T;

SELECT ID
INTO @CATEGORY_ID
FROM `t_dsx_dnd_category`
WHERE NAME = '数据读写';
SELECT ID
INTO @REGISTRY_ID
FROM `t_dsx_dnd_registry`
WHERE KEY_ID = 'DataXPlugin';
# 数据读写组件初始化
INSERT INTO `t_dsx_dnd_component` (CID, NAME, ICON, DESCRIPTION, REFINEMENT, REGISTRY_ID, CONFIGURATION, SORT)
SELECT @CATEGORY_ID                                                    AS CID,
       NAME,
       ICON,
       DESCRIPTION,
       CASE IS_GROUP WHEN 'true' THEN 1 WHEN 'false' THEN 0 ELSE 0 END AS REFINEMENT,
       @REGISTRY_ID                                                    AS REGISTRY_ID,
       CONFIGURATION,
       ROW_ID                                                          AS SORT
FROM JSON_TABLE(
             @JSON, '$[1].children[*]'
             COLUMNS (
                 ROW_ID FOR ORDINALITY,
                 NAME varchar(32) PATH '$.label',
                 ICON varchar(32) PATH '$.icon',
                 DESCRIPTION varchar(32) PATH '$.description',
                 IS_GROUP varchar(32) PATH '$.isGroup',
                 CONFIGURATION json PATH '$.configuration')
         ) AS T;

SELECT ID
INTO @CATEGORY_ID
FROM `t_dsx_dnd_category`
WHERE NAME = '数据转换';
SELECT ID
INTO @REGISTRY_ID
FROM `t_dsx_dnd_registry`
WHERE KEY_ID = 'DataXPlugin';
# 数据转换组件初始化
INSERT INTO `t_dsx_dnd_component` (CID, NAME, ICON, DESCRIPTION, REFINEMENT, REGISTRY_ID, CONFIGURATION, SORT)
SELECT @CATEGORY_ID                                                    AS CID,
       NAME,
       ICON,
       DESCRIPTION,
       CASE IS_GROUP WHEN 'true' THEN 1 WHEN 'false' THEN 0 ELSE 0 END AS REFINEMENT,
       @REGISTRY_ID                                                    AS REGISTRY_ID,
       CONFIGURATION,
       ROW_ID                                                          AS SORT
FROM JSON_TABLE(
             @JSON, '$[2].children[*]'
             COLUMNS (
                 ROW_ID FOR ORDINALITY,
                 NAME varchar(32) PATH '$.label',
                 ICON varchar(32) PATH '$.icon',
                 DESCRIPTION varchar(32) PATH '$.description',
                 IS_GROUP varchar(32) PATH '$.isGroup',
                 CONFIGURATION json PATH '$.configuration')
         ) AS T;
