/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80036 (8.0.36)
 Source Host           : localhost:3306
 Source Schema         : mytest

 Target Server Type    : MySQL
 Target Server Version : 80036 (8.0.36)
 File Encoding         : 65001

 Date: 19/02/2024 08:23:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for todo
-- ----------------------------
DROP TABLE IF EXISTS `todo`;
CREATE TABLE `todo`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '待办事项的id',
  `user_id` int UNSIGNED NOT NULL,
  `title` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标题',
  `content` varchar(70) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `ddl` datetime NOT NULL COMMENT '截止时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of todo
-- ----------------------------
INSERT INTO `todo` VALUES (1, 1, '我要进入', '好好学习javaweb', '2024-01-01 00:00:00', '2024-05-10 00:00:00');
INSERT INTO `todo` VALUES (3, 1, '我要进入RDC后台组2', '好好学习MySQL', '2024-01-01 00:00:00', '2024-05-10 00:00:00');
INSERT INTO `todo` VALUES (4, 2, '不健康', '三餐都吃,吃好', '2024-01-01 00:00:00', '2024-05-10 00:00:00');
INSERT INTO `todo` VALUES (5, 2, '我要有时间娱乐', '打饥荒', '2024-01-01 00:00:00', '2024-05-10 00:00:00');
INSERT INTO `todo` VALUES (6, 2, '我要有时间锻炼', '校园跑', '2024-01-01 00:00:00', '2024-05-10 00:00:00');
INSERT INTO `todo` VALUES (7, 2, '我要吃KFC', '周一吃KFC', '2024-02-18 11:19:00', '2024-03-04 01:03:05');
INSERT INTO `todo` VALUES (10, 1, '我要进入RDC后台组3', '好好学习Mybatis', '2024-02-19 08:14:30', '2024-05-01 00:00:00');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户姓名',
  `password` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'Yuri', '123456');
INSERT INTO `user` VALUES (2, 'Hana', '456789');

SET FOREIGN_KEY_CHECKS = 1;
