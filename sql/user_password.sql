/*
Navicat MySQL Data Transfer

Source Server         : ocean
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : miaosha

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2020-03-04 17:06:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user_password`
-- ----------------------------
DROP TABLE IF EXISTS `user_password`;
CREATE TABLE `user_password` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `encrpt_password` varchar(128) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_password
-- ----------------------------
INSERT INTO `user_password` VALUES ('1', 'fafjoafjkoaf', '1');
INSERT INTO `user_password` VALUES ('2', 'mqQrMYguwDmWXzxJI86QGw==', '8');
INSERT INTO `user_password` VALUES ('5', 'BdJR6ijFvpQmYRoSHbDJKg==', '13');
INSERT INTO `user_password` VALUES ('6', '4QrcOUm6Wau+VuBX8g+IPg==', '14');
INSERT INTO `user_password` VALUES ('7', '4QrcOUm6Wau+VuBX8g+IPg==', '15');
