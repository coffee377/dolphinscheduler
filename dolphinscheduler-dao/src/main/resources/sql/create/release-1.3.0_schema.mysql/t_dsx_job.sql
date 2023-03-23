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

 Date: 23/03/2023 11:04:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_dsx_job
-- ----------------------------
DROP TABLE IF EXISTS `t_dsx_job`;
CREATE TABLE `t_dsx_job`  (
  `ID` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `CONTENT` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '任务自定义JSON',
  `CONTENT_HASH` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '任务JSON的哈希值',
  `CREATED_BY` int NULL DEFAULT NULL COMMENT '创建人',
  `CREATE_TIME` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `PROJECT_ID` bigint NULL DEFAULT NULL COMMENT '项目ID',
  `JOB_ID` bigint NULL DEFAULT NULL COMMENT '作业定义ID',
  `PUSH` tinyint NULL DEFAULT 0 COMMENT '是否推送到海豚调度',
  `UPDATED_BY` int NULL DEFAULT NULL COMMENT '更新操作者',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = 'DSX 自定义组件' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
