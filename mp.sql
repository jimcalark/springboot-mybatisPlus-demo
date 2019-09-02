/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : mp

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-09-02 17:59:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mp_article
-- ----------------------------
DROP TABLE IF EXISTS `mp_article`;
CREATE TABLE `mp_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `module_id` bigint(20) NOT NULL COMMENT '所属模块id',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `content` text NOT NULL COMMENT '内容',
  `visits` int(11) NOT NULL DEFAULT '0' COMMENT '浏览次数',
  `creator_id` bigint(20) NOT NULL COMMENT '创建人id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modifier_id` bigint(20) DEFAULT NULL COMMENT '修改人id',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `state` int(3) NOT NULL DEFAULT '1' COMMENT '是否可见：0：不可见；1：可见',
  `enable` int(3) NOT NULL DEFAULT '1' COMMENT '逻辑删除 0:删除；1：可用',
  PRIMARY KEY (`id`),
  KEY `module_id` (`module_id`) USING BTREE,
  KEY `create_user_id` (`creator_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='论坛模块---帖子表';

-- ----------------------------
-- Records of mp_article
-- ----------------------------
INSERT INTO `mp_article` VALUES ('1', '33', '桥梁有必要每天都要巡检吗？', '<p>如题</p>', '52', '16', '2019-07-13 14:32:56', null, null, '1', '1');
INSERT INTO `mp_article` VALUES ('2', '34', '交流贴', '<p>内容<img src=\"http://106.15.33.125/img/20190713/85656cb69df044e9b96d0e051d86d9ef.jpg\"></p>', '49', '15', '2019-07-13 15:43:56', null, null, '1', '1');

-- ----------------------------
-- Table structure for mp_comment
-- ----------------------------
DROP TABLE IF EXISTS `mp_comment`;
CREATE TABLE `mp_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `article_id` bigint(20) NOT NULL COMMENT '对应的帖子id',
  `content` varchar(255) NOT NULL COMMENT '评论内容',
  `creator_id` bigint(20) NOT NULL COMMENT '评论者id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modifier_id` bigint(20) DEFAULT NULL COMMENT '修改人id',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `enable` int(3) NOT NULL DEFAULT '1' COMMENT '逻辑删除： 0：删除；1：可用',
  PRIMARY KEY (`id`),
  KEY `user_id` (`creator_id`) USING BTREE,
  KEY `artilce_id` (`article_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=129 DEFAULT CHARSET=utf8 COMMENT='论坛模块--帖子评论表';

-- ----------------------------
-- Records of mp_comment
-- ----------------------------
INSERT INTO `mp_comment` VALUES ('126', '2', '很好\n', '15', '2019-07-13 15:44:11', null, null, '1');
INSERT INTO `mp_comment` VALUES ('127', '1', '滴滴滴\n', '6', '2019-07-15 14:09:28', null, null, '1');
INSERT INTO `mp_comment` VALUES ('128', '2', '\n123\n', '16', '2019-07-16 11:11:42', null, null, '1');

-- ----------------------------
-- Table structure for mp_module
-- ----------------------------
DROP TABLE IF EXISTS `mp_module`;
CREATE TABLE `mp_module` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '模块名称',
  `brief` varchar(255) NOT NULL COMMENT '模块简介',
  `creator_id` bigint(20) NOT NULL COMMENT '创建者id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modifier_id` bigint(20) DEFAULT NULL COMMENT '修改者id',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `state` int(3) NOT NULL DEFAULT '1' COMMENT '模块状态 0：关闭；1：开启',
  `enable` int(3) NOT NULL DEFAULT '1' COMMENT '是否逻辑删除 0：删除；1：可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='论坛模块--帖子模块表';

-- ----------------------------
-- Records of mp_module
-- ----------------------------
INSERT INTO `mp_module` VALUES ('33', '桥梁养护交流', '分享交流桥梁养护方法', '0', '2019-07-13 13:55:45', '0', '0000-00-00 00:00:00', '1', '1');
INSERT INTO `mp_module` VALUES ('34', '安全知识交流板块', '交流各种安全相关知识', '0', '2019-07-13 15:36:10', '0', '0000-00-00 00:00:00', '1', '1');
INSERT INTO `mp_module` VALUES ('35', '桥梁施工交流', '桥梁施工相关知识交流', '0', '2019-07-13 15:39:21', '0', '0000-00-00 00:00:00', '1', '1');
INSERT INTO `mp_module` VALUES ('36', '测试1', '士大夫', '0', '2019-07-13 15:41:22', '0', '0000-00-00 00:00:00', '1', '1');
INSERT INTO `mp_module` VALUES ('37', 'png标题', '。。。', '0', '2019-07-15 14:07:56', '0', '0000-00-00 00:00:00', '1', '0');

-- ----------------------------
-- Table structure for mp_replay
-- ----------------------------
DROP TABLE IF EXISTS `mp_replay`;
CREATE TABLE `mp_replay` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment_id` bigint(20) NOT NULL COMMENT '评论id',
  `content` varchar(255) NOT NULL COMMENT '回复内容',
  `from_user_id` bigint(20) NOT NULL COMMENT '评论者id',
  `to_user_id` bigint(11) NOT NULL COMMENT '被评论者id',
  `creator_id` bigint(20) NOT NULL COMMENT '创建人id',
  `create_time` datetime NOT NULL COMMENT '回复时间',
  `modifier_id` bigint(20) DEFAULT NULL COMMENT '修改人id',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `enable` int(3) NOT NULL DEFAULT '1' COMMENT '逻辑删除 0：删除；1:可用',
  PRIMARY KEY (`id`),
  KEY `comment_id` (`comment_id`) USING BTREE,
  KEY `from_user_id` (`from_user_id`) USING BTREE,
  KEY `to_user_id` (`to_user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8 COMMENT='论坛模块--帖子评论回复表';

-- ----------------------------
-- Records of mp_replay
-- ----------------------------
INSERT INTO `mp_replay` VALUES ('34', '126', '不好\n', '15', '15', '0', '2019-07-13 15:44:19', null, null, '1');
INSERT INTO `mp_replay` VALUES ('35', '126', '士大夫\n', '15', '15', '0', '2019-07-13 15:44:46', null, null, '1');
INSERT INTO `mp_replay` VALUES ('36', '126', '回复\n', '1', '15', '0', '2019-07-13 22:47:02', null, null, '1');
INSERT INTO `mp_replay` VALUES ('37', '126', '再回复\n', '1', '15', '0', '2019-07-13 22:49:36', null, null, '1');
INSERT INTO `mp_replay` VALUES ('38', '126', 'sas\n', '1', '15', '0', '2019-07-14 09:13:31', null, null, '1');
INSERT INTO `mp_replay` VALUES ('39', '126', 'zxxzxz\n', '1', '15', '0', '2019-07-14 09:15:31', null, null, '1');
INSERT INTO `mp_replay` VALUES ('40', '126', '123\n', '6', '15', '0', '2019-07-15 09:56:30', null, null, '1');
INSERT INTO `mp_replay` VALUES ('41', '126', '滴滴滴\n', '6', '15', '0', '2019-07-15 14:08:50', null, null, '1');
INSERT INTO `mp_replay` VALUES ('42', '127', '滴滴滴\n', '6', '6', '0', '2019-07-15 14:09:37', null, null, '1');
INSERT INTO `mp_replay` VALUES ('43', '126', '11\n', '6', '15', '0', '2019-07-15 14:12:52', null, null, '1');
INSERT INTO `mp_replay` VALUES ('44', '127', '第三方\n', '6', '6', '0', '2019-07-15 14:13:20', null, null, '1');
INSERT INTO `mp_replay` VALUES ('45', '126', '111\n', '6', '15', '0', '2019-07-15 14:15:21', null, null, '1');
INSERT INTO `mp_replay` VALUES ('46', '126', '123\n', '6', '15', '0', '2019-07-15 14:16:54', null, null, '1');
INSERT INTO `mp_replay` VALUES ('47', '126', '是的发送到发斯蒂芬是东方闪电\n', '6', '15', '0', '2019-07-15 14:17:32', null, null, '1');
INSERT INTO `mp_replay` VALUES ('48', '127', '地方的法规\n', '6', '6', '0', '2019-07-15 14:18:51', null, null, '1');
INSERT INTO `mp_replay` VALUES ('49', '127', '从VB从\n', '6', '6', '0', '2019-07-15 14:19:02', null, null, '1');
INSERT INTO `mp_replay` VALUES ('50', '127', '大范甘迪\n', '6', '6', '0', '2019-07-15 14:19:22', null, null, '1');
INSERT INTO `mp_replay` VALUES ('51', '127', '东方红\n', '6', '6', '0', '2019-07-15 14:19:30', null, null, '1');
INSERT INTO `mp_replay` VALUES ('52', '127', '发过火\n', '6', '6', '0', '2019-07-15 14:19:36', null, null, '1');
INSERT INTO `mp_replay` VALUES ('53', '127', '沃尔\n', '6', '6', '0', '2019-07-15 14:19:45', null, null, '1');
INSERT INTO `mp_replay` VALUES ('54', '127', '电饭锅\n', '6', '6', '0', '2019-07-15 14:19:51', null, null, '1');
INSERT INTO `mp_replay` VALUES ('55', '127', '123123123123\n', '6', '6', '0', '2019-07-15 14:19:59', null, null, '1');
INSERT INTO `mp_replay` VALUES ('56', '127', 'dwrwerwer\n', '6', '6', '0', '2019-07-15 14:20:11', null, null, '1');
INSERT INTO `mp_replay` VALUES ('57', '126', '123\n', '6', '15', '0', '2019-07-15 14:39:35', null, null, '1');
INSERT INTO `mp_replay` VALUES ('58', '126', '吃不到饭\n', '6', '15', '0', '2019-07-15 14:40:45', null, null, '1');
INSERT INTO `mp_replay` VALUES ('59', '127', '饿\n', '6', '6', '0', '2019-07-15 14:41:17', null, null, '1');
INSERT INTO `mp_replay` VALUES ('60', '127', '3收到\n', '6', '6', '0', '2019-07-15 14:41:36', null, null, '1');
INSERT INTO `mp_replay` VALUES ('61', '127', '是否\n', '6', '6', '0', '2019-07-15 14:43:58', null, null, '1');
INSERT INTO `mp_replay` VALUES ('62', '127', '是的发生\n', '6', '6', '0', '2019-07-15 14:44:21', null, null, '1');
INSERT INTO `mp_replay` VALUES ('63', '126', '12312\n', '6', '15', '0', '2019-07-15 17:36:01', null, null, '1');

-- ----------------------------
-- Table structure for mp_user
-- ----------------------------
DROP TABLE IF EXISTS `mp_user`;
CREATE TABLE `mp_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `phone` varchar(20) NOT NULL COMMENT '手机号',
  `creator_id` bigint(20) NOT NULL COMMENT '创建者id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modifier_id` bigint(20) DEFAULT NULL COMMENT '修改人id',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `enable` int(3) DEFAULT '1' COMMENT '1:可用;0:删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mp_user
-- ----------------------------
