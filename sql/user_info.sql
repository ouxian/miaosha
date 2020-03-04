/*
Navicat MySQL Data Transfer

Source Server         : ocean
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : miaosha

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2020-03-04 17:06:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '',
  `gender` tinyint(4) NOT NULL DEFAULT '0' COMMENT '为1代表男性，为2代表女性',
  `age` int(11) NOT NULL,
  `telphone` varchar(64) NOT NULL,
  `register_mode` varchar(64) NOT NULL COMMENT 'byphone,bywechat,byalipay',
  `third_party_id` varchar(64) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `telphone_unique_index` (`telphone`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('8', '张三', '1', '1', '123', 'byphone', '');
INSERT INTO `user_info` VALUES ('13', 'gsgs', '2', '2', '456', 'byphone', '');
INSERT INTO `user_info` VALUES ('14', 'baba', '1', '1', '123456', 'byphone', '');
INSERT INTO `user_info` VALUES ('15', 'ocean', '1', '24', '15072931024', 'byphone', '');
