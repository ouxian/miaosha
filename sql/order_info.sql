/*
Navicat MySQL Data Transfer

Source Server         : ocean
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : miaosha

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2020-03-04 17:05:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `order_info`
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `id` varchar(32) NOT NULL,
  `user_id` int(11) NOT NULL DEFAULT '0',
  `item_id` int(11) NOT NULL DEFAULT '0',
  `item_price` double NOT NULL DEFAULT '0',
  `amount` int(11) NOT NULL DEFAULT '0',
  `order_price` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES ('2019040200000100', '14', '7', '9999', '1', '9999');
INSERT INTO `order_info` VALUES ('2019040200000200', '14', '7', '9999', '1', '9999');
INSERT INTO `order_info` VALUES ('2019040200000300', '14', '7', '9999', '1', '9999');
INSERT INTO `order_info` VALUES ('2019040200000400', '14', '7', '9999', '1', '9999');
INSERT INTO `order_info` VALUES ('2020030400000500', '15', '8', '2999', '1', '2999');
