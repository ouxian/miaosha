/*
Navicat MySQL Data Transfer

Source Server         : ocean
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : miaosha

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2020-03-04 17:05:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `item`
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(64) NOT NULL DEFAULT '''''',
  `price` double(10,0) NOT NULL DEFAULT '0',
  `description` varchar(500) NOT NULL DEFAULT '''''',
  `sales` int(11) NOT NULL DEFAULT '0',
  `img_url` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES ('7', 'iphoneXS', '9999', 'iphone最新款手机', '3', 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2064428387,788908798&fm=27&gp=0.jpg');
INSERT INTO `item` VALUES ('8', '小米9', '2999', '小米最新款手机', '1', 'https://ss2.bdstatic.com/8_V1bjqh_Q23odCf/pacific/1824682986.jpg');
INSERT INTO `item` VALUES ('9', '小米8', '2199', '过时产品', '0', 'https://paimgcdn.baidu.com/28F709D32AEBBF10?src=http%3A%2F%2Fms.bdimg.com%2Fdsp-image%2F2132984523.jpg&rz=st_2_968_600&v=0');
INSERT INTO `item` VALUES ('10', '华为mateX', '19999', '华为全球首款折叠屏手机', '0', 'https://img.alicdn.com/bao/uploaded/i1/2838892713/O1CN01GsbOJ01Vub2IESgNs_!!2838892713.jpg');
