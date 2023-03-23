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

 Date: 23/03/2023 11:06:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_dsx_dnd_component
-- ----------------------------
DROP TABLE IF EXISTS `t_dsx_dnd_component`;
CREATE TABLE `t_dsx_dnd_component`  (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '组件名称',
  `CID` bigint NOT NULL COMMENT 'DND分类',
  `icon` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '组件图标',
  `type` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '类型',
  `CONFIGURATION` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '参数配置项',
  `REGISTRY_ID` bigint NOT NULL COMMENT '组件类型表主键',
  `created_by` int NULL DEFAULT NULL COMMENT '操作人',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `REFINEMENT` tinyint NULL DEFAULT NULL COMMENT '是否可继续细化',
  `DESCRIPTION` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '描述',
  `SORT` tinyint NULL DEFAULT NULL COMMENT 'xuhoa ',
  `UPDATED_BY` int NULL DEFAULT NULL COMMENT '更新者',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '任务组件注册表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
