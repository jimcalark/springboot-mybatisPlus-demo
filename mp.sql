/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : mp

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 04/09/2019 23:09:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mp_article
-- ----------------------------
DROP TABLE IF EXISTS `mp_article`;
CREATE TABLE `mp_article`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `module_id` bigint(20) NOT NULL COMMENT '所属模块id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `visits` int(11) NOT NULL DEFAULT 0 COMMENT '浏览次数',
  `creator_id` bigint(20) NOT NULL COMMENT '创建人id',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `modifier_id` bigint(20) NULL DEFAULT NULL COMMENT '修改人id',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `state` int(3) NOT NULL DEFAULT 1 COMMENT '是否可见：0：不可见；1：可见',
  `enable` int(3) NOT NULL DEFAULT 1 COMMENT '逻辑删除 0:删除；1：可用',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `module_id`(`module_id`) USING BTREE,
  INDEX `create_user_id`(`creator_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '论坛模块---帖子表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mp_comment
-- ----------------------------
DROP TABLE IF EXISTS `mp_comment`;
CREATE TABLE `mp_comment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `article_id` bigint(20) NOT NULL COMMENT '对应的帖子id',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论内容',
  `creator_id` bigint(20) NOT NULL COMMENT '评论者id',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `modifier_id` bigint(20) NULL DEFAULT NULL COMMENT '修改人id',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `enable` int(3) NOT NULL DEFAULT 1 COMMENT '逻辑删除： 0：删除；1：可用',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`creator_id`) USING BTREE,
  INDEX `artilce_id`(`article_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 129 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '论坛模块--帖子评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mp_module
-- ----------------------------
DROP TABLE IF EXISTS `mp_module`;
CREATE TABLE `mp_module`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模块名称',
  `brief` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模块简介',
  `creator_id` bigint(20) NOT NULL COMMENT '创建者id',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `modifier_id` bigint(20) NULL DEFAULT NULL COMMENT '修改者id',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `state` int(3) NOT NULL DEFAULT 1 COMMENT '模块状态 0：关闭；1：开启',
  `enable` int(3) NOT NULL DEFAULT 1 COMMENT '是否逻辑删除 0：删除；1：可用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '论坛模块--帖子模块表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mp_replay
-- ----------------------------
DROP TABLE IF EXISTS `mp_replay`;
CREATE TABLE `mp_replay`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment_id` bigint(20) NOT NULL COMMENT '评论id',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '回复内容',
  `from_user_id` bigint(20) NOT NULL COMMENT '评论者id',
  `to_user_id` bigint(11) NOT NULL COMMENT '被评论者id',
  `creator_id` bigint(20) NOT NULL COMMENT '创建人id',
  `create_time` datetime(0) NOT NULL COMMENT '回复时间',
  `modifier_id` bigint(20) NULL DEFAULT NULL COMMENT '修改人id',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `enable` int(3) NOT NULL DEFAULT 1 COMMENT '逻辑删除 0：删除；1:可用',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `comment_id`(`comment_id`) USING BTREE,
  INDEX `from_user_id`(`from_user_id`) USING BTREE,
  INDEX `to_user_id`(`to_user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 64 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '论坛模块--帖子评论回复表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mp_user
-- ----------------------------
DROP TABLE IF EXISTS `mp_user`;
CREATE TABLE `mp_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `gender` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性別 1;男性；2;女性',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号',
  `creator_id` bigint(20) NOT NULL COMMENT '创建者id',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `modifier_id` bigint(20) NULL DEFAULT NULL COMMENT '修改人id',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `enable` int(3) NOT NULL DEFAULT 1 COMMENT '1:可用;0:删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mp_user
-- ----------------------------
INSERT INTO `mp_user` VALUES (1, '吴娇凤', 'WOMEN', '152816151231', 100, '2019-09-03 23:13:32', 100, '2019-09-03 23:18:40', 1);

SET FOREIGN_KEY_CHECKS = 1;
