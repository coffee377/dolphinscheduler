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

 Date: 23/03/2023 11:04:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_dsx_network
-- ----------------------------
DROP TABLE IF EXISTS `t_dsx_network`;
CREATE TABLE `t_dsx_network`  (
  `ID` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `USER_ID` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户ID',
  `IP_SEGMENT` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `IP_TYPE` smallint NULL DEFAULT NULL COMMENT 'IP类型（1：IP地址，2：IP网段）',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1627964497239572481 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
