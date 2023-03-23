/*
 Navicat Premium Data Transfer

 Source Server         : integration-platform
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : 10.1.150.150:14924
 Source Schema         : integration-platform

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 23/03/2023 11:07:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_dsx_dnd_registry
-- ----------------------------
DROP TABLE IF EXISTS `t_dsx_dnd_registry`;
CREATE TABLE `t_dsx_dnd_registry`  (
  `ID` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `KEY_ID` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT 'render_key',
  `CREATE_TIME` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `NAME` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `SORT` tinyint NULL DEFAULT NULL COMMENT '排序',
  `REMARK` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  `CREATED_BY` int NULL DEFAULT NULL COMMENT '创建者',
  `UPDATED_BY` int NULL DEFAULT NULL COMMENT '更新者',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '任务组件类型' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
