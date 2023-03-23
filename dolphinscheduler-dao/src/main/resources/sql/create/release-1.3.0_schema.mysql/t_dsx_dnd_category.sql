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

 Date: 23/03/2023 11:05:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_dsx_dnd_category
-- ----------------------------
DROP TABLE IF EXISTS `t_dsx_dnd_category`;
CREATE TABLE `t_dsx_dnd_category`  (
  `ID` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `PID` bigint NULL DEFAULT 0 COMMENT '父级ID',
  `NAME` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  `SORT` tinyint NULL DEFAULT NULL COMMENT '排序',
  `CONFIGURATION` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '分类配置',
  `CREATED_BY` int NULL DEFAULT NULL COMMENT '创建人',
  `CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_BY` int NULL DEFAULT NULL COMMENT '更新人',
  `UPDATE_TIME` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = 'DND 分类' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
