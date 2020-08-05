CREATE DATABASE `spring_security_jwt` DEFAULT CHARACTER SET utf8;

USE `spring_security_jwt`;
SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
--  Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `address` varchar(64) DEFAULT NULL COMMENT '联系地址',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密码',
  `roles` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
-- ----------------------------
--  Records of `tb_user`
-- ----------------------------
BEGIN;
INSERT INTO `tb_user` VALUES ('1', 'Adam', 'beijing', 'adam','$2a$10$sXLFXCzQRu5hURXVspYr8.t2/4a08db.v6J1eHQXTcyG.jKKdXlZa', 'ROLE_USER');
INSERT INTO `tb_user` VALUES ('2', 'SuperMan', 'shanghang', 'super','$2a$10$sXLFXCzQRu5hURXVspYr8.t2/4a08db.v6J1eHQXTcyG.jKKdXlZa', 'ROLE_USER,ROLE_ADMIN');
INSERT INTO `tb_user` VALUES ('3', 'Manager', 'beijing', 'manager','$2a$10$sXLFXCzQRu5hURXVspYr8.t2/4a08db.v6J1eHQXTcyG.jKKdXlZa', 'ROLE_USER,ROLE_MANAGER');
INSERT INTO `tb_user` VALUES ('4', 'User1', 'shanghang', 'user1','$2a$10$sXLFXCzQRu5hURXVspYr8.t2/4a08db.v6J1eHQXTcyG.jKKdXlZa', 'ROLE_USER,ROLE_DEPART1');
INSERT INTO `tb_user` VALUES ('5', 'User2', 'shanghang', 'user2','$2a$10$sXLFXCzQRu5hURXVspYr8.t2/4a08db.v6J1eHQXTcyG.jKKdXlZa', 'ROLE_USER,ROLE_DEPART2');
COMMIT;

-- ----------------------------
--  Table structure for `tb_resource`
-- ----------------------------
DROP TABLE IF EXISTS `tb_resource`;
CREATE TABLE `tb_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL COMMENT '资源',
  `roles` varchar(255) DEFAULT NULL COMMENT '所需角色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
-- ----------------------------
--  Records of `tb_resource`
-- ----------------------------
BEGIN;
INSERT INTO `tb_resource` VALUES ('1', '/depart1/**', 'ROLE_ADMIN,ROLE_MANAGER,ROLE_DEPART1');
INSERT INTO `tb_resource` VALUES ('2', '/depart2/**', 'ROLE_ADMIN,ROLE_MANAGER,ROLE_DEPART2');
INSERT INTO `tb_resource` VALUES ('3', '/user/**', 'ROLE_ADMIN,ROLE_USER');
INSERT INTO `tb_resource` VALUES ('4', '/admin/**', 'ROLE_ADMIN');
COMMIT;
