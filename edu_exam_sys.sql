/*
 Navicat Premium Data Transfer

 Source Server         : MyConncetion
 Source Server Type    : MariaDB
 Source Server Version : 100312
 Source Host           : localhost:3306
 Source Schema         : edu_exam_sys

 Target Server Type    : MariaDB
 Target Server Version : 100312
 File Encoding         : 65001

 Date: 27/01/2020 22:44:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for chapter
-- ----------------------------
DROP TABLE IF EXISTS `chapter`;
CREATE TABLE `chapter`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `modify_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '章节名',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '对该章节知识点的简单概括',
  `course_id` int(11) NOT NULL COMMENT '所属课程id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '章节' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for classroom
-- ----------------------------
DROP TABLE IF EXISTS `classroom`;
CREATE TABLE `classroom`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `modify_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教室名',
  `volume` int(10) NOT NULL DEFAULT 0 COMMENT '教室容量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '教室表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for clazz
-- ----------------------------
DROP TABLE IF EXISTS `clazz`;
CREATE TABLE `clazz`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `modify_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '班级表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of clazz
-- ----------------------------
INSERT INTO `clazz` VALUES (3, '2020-01-17 21:52:11', '2020-01-17 21:52:11', '软件1701');
INSERT INTO `clazz` VALUES (4, '2020-01-17 21:52:11', '2020-01-17 21:52:11', '软件1901');
INSERT INTO `clazz` VALUES (5, '2020-01-19 18:07:17', '2020-01-19 18:07:20', '数媒1801');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `modify_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程名（英文）',
  `name_zh` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程名',
  `number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程编号',
  `deleted` int(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '是否删除，0=false，1=true',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '课程实体' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (2, '2020-01-23 11:01:27', '2020-01-23 11:01:31', 'C Language', 'C语言程序设计基础', 'C1', 0);
INSERT INTO `course` VALUES (4, '2020-01-23 13:09:28', '2020-01-23 13:09:28', 'Object Oriented Programming A', '面向对象程序设计A', '116A2022', 0);
INSERT INTO `course` VALUES (5, '2020-01-27 21:41:37', '2020-01-27 21:41:37', 'Software Engineering Economics', '软件工程经济学', '116A2018', 0);

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `modify_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `clazz_id` int(11) NOT NULL COMMENT '班级id',
  `course_id` int(11) NOT NULL COMMENT '课程id',
  `start_time` datetime(0) NOT NULL COMMENT '开始时间',
  `end_time` datetime(0) NOT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '考试表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for exam_mid
-- ----------------------------
DROP TABLE IF EXISTS `exam_mid`;
CREATE TABLE `exam_mid`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `modify_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `classroom_id` int(11) NOT NULL COMMENT '考试所用教室',
  `exam_id` int(11) NOT NULL COMMENT '考试id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '考试安排表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for multiple_choice
-- ----------------------------
DROP TABLE IF EXISTS `multiple_choice`;
CREATE TABLE `multiple_choice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question_id` int(11) NOT NULL,
  `create_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `modify_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `option_a` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '选项A',
  `option_b` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '选项B',
  `option_c` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '选项C',
  `option_d` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '选项D',
  `answer` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '答案',
  `student_id` int(11) NULL DEFAULT NULL COMMENT '提供该题的学生id',
  `teacher_id` int(11) NULL DEFAULT NULL COMMENT '录入题库的教师id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '单项选择题' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for program_input
-- ----------------------------
DROP TABLE IF EXISTS `program_input`;
CREATE TABLE `program_input`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `modify_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `program_id` int(11) NOT NULL COMMENT '编程题id',
  `input` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '样例输入',
  `output` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '样例输出',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '编程题的输入输出' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for program_questions
-- ----------------------------
DROP TABLE IF EXISTS `program_questions`;
CREATE TABLE `program_questions`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `modify_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `description` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '题目描述',
  `answer_id` int(11) NOT NULL COMMENT '参考答案',
  `student_id` int(11) NOT NULL COMMENT '提供该题的学生id',
  `teacher_id` int(11) NULL DEFAULT NULL COMMENT '加入该题的教师id',
  `question_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '编程题实体' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `modify_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `type_id` int(11) NOT NULL COMMENT '类型id',
  `chapter_id` int(11) NOT NULL COMMENT '对应章节id',
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '题目简介',
  `difficulty_id` int(11) NOT NULL COMMENT '难度等级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '问题实体' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for question_difficulty
-- ----------------------------
DROP TABLE IF EXISTS `question_difficulty`;
CREATE TABLE `question_difficulty`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述',
  `name_zh` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述（中文）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '题目难度表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for question_type
-- ----------------------------
DROP TABLE IF EXISTS `question_type`;
CREATE TABLE `question_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型名',
  `name_zh` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型名（中文）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '问题类型' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name_zh` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'student', '学生');
INSERT INTO `role` VALUES (2, 'teacher', '教师');
INSERT INTO `role` VALUES (3, 'principal', '课程组组长');
INSERT INTO `role` VALUES (4, 'admin', '管理员');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `modify_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `number` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '学号',
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生姓名',
  `id_number` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '身份证号',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '联系电话',
  `clazz_id` int(11) NOT NULL COMMENT '所在班级id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '电子邮件',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `clazz_id`(`clazz_id`) USING BTREE,
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_ibfk_2` FOREIGN KEY (`clazz_id`) REFERENCES `clazz` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学生表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '2020-01-20 20:47:30', '2020-01-20 20:47:30', '20171611101', 'student1', '', '', 3, 1, '');
INSERT INTO `student` VALUES (3, '2020-01-20 20:47:33', '2020-01-20 20:47:33', '20171611103', 'student3', '', '', 3, 3, '');
INSERT INTO `student` VALUES (4, '2020-01-20 20:49:21', '2020-01-20 20:49:21', '20191611101', 'student4', '', '', 4, 4, '');
INSERT INTO `student` VALUES (5, '2020-01-20 20:49:24', '2020-01-20 20:49:24', '20191611102', 'student5', '', '', 4, 5, '');
INSERT INTO `student` VALUES (6, '2020-01-20 13:56:24', '2020-01-20 13:56:24', '20191611103', 'student6', '', '', 4, 14, '');
INSERT INTO `student` VALUES (7, '2020-01-20 15:50:30', '2020-01-20 15:50:30', '20181611101', 'student7', '', '', 5, 20, '');
INSERT INTO `student` VALUES (8, '2020-01-22 10:25:00', '2020-01-22 10:25:00', '20171611104', 'student7', '', '', 3, 21, '');
INSERT INTO `student` VALUES (9, '2020-01-22 10:25:00', '2020-01-22 10:25:00', '20171611105', 'student8', '', '', 3, 22, '');
INSERT INTO `student` VALUES (10, '2020-01-22 10:25:00', '2020-01-22 10:25:00', '20171611106', 'student9', '', '', 3, 23, '');

-- ----------------------------
-- Table structure for student_answer
-- ----------------------------
DROP TABLE IF EXISTS `student_answer`;
CREATE TABLE `student_answer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `modify_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `student_id` int(11) NOT NULL COMMENT '学生id',
  `question_id` int(11) NOT NULL COMMENT '问题id',
  `score` int(10) NOT NULL DEFAULT -1 COMMENT '本题得分',
  `teacher_id` int(11) NOT NULL COMMENT '阅卷教师id',
  `answer` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生的答案',
  `test_paper_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学生的答案' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for subjective_questions
-- ----------------------------
DROP TABLE IF EXISTS `subjective_questions`;
CREATE TABLE `subjective_questions`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `modify_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `question_id` int(11) NOT NULL COMMENT '题目id',
  `answer_id` int(11) NOT NULL COMMENT '参考答案',
  `description` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '题目描述',
  `student_id` int(11) NULL DEFAULT NULL COMMENT '提供该题的学生id',
  `teacher_id` int(11) NULL DEFAULT NULL COMMENT '录入教师id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '主观题实体' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_or_f_question
-- ----------------------------
DROP TABLE IF EXISTS `t_or_f_question`;
CREATE TABLE `t_or_f_question`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `modify_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `question_id` int(11) NOT NULL COMMENT '题目id',
  `answer_id` int(11) NOT NULL COMMENT '参考答案',
  `description` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '题目描述',
  `student_id` int(11) NULL DEFAULT NULL,
  `teacher_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '判断题实体' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `modify_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '教师号',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '教师姓名',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `id_number` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '身份证',
  `phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '电话',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '电子邮件',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '教师表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, '2020-01-20 13:57:45', '2020-01-20 13:57:45', 'T1', 'teacher1', 6, '', '', '');
INSERT INTO `teacher` VALUES (2, '2020-01-20 13:57:45', '2020-01-20 13:57:45', 'T2', 'teacher2', 7, '', '', '');
INSERT INTO `teacher` VALUES (3, '2020-01-20 13:57:45', '2020-01-20 13:57:45', 'T3', 'teacher3', 8, '', '', '');
INSERT INTO `teacher` VALUES (4, '2020-01-20 13:59:22', '2020-01-20 13:59:22', 'Teacher4', 'teacher4', 15, '', '', '');
INSERT INTO `teacher` VALUES (7, '2020-01-20 15:49:34', '2020-01-20 15:49:34', 'Teacher5', 'teacher5', 19, '', '', '');

-- ----------------------------
-- Table structure for teacher_group
-- ----------------------------
DROP TABLE IF EXISTS `teacher_group`;
CREATE TABLE `teacher_group`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `modify_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `number` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师组编号',
  `principal_id` int(11) NULL DEFAULT NULL COMMENT '负责教师id',
  `course_id` int(11) NOT NULL COMMENT '负责课程id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `course_id`(`course_id`) USING BTREE,
  INDEX `teacher_group_ibfk_2`(`principal_id`) USING BTREE,
  CONSTRAINT `teacher_group_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `teacher_group_ibfk_2` FOREIGN KEY (`principal_id`) REFERENCES `teacher` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '教师组表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher_group
-- ----------------------------
INSERT INTO `teacher_group` VALUES (1, '2020-01-27 20:13:52', '2020-01-27 20:13:52', 'TeacherGroup1', 1, 2);
INSERT INTO `teacher_group` VALUES (2, '2020-01-27 18:17:05', '2020-01-27 18:17:05', 'TeacherGroup2', 4, 4);

-- ----------------------------
-- Table structure for teacher_mid_group
-- ----------------------------
DROP TABLE IF EXISTS `teacher_mid_group`;
CREATE TABLE `teacher_mid_group`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `modify_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `teacher_id` int(11) NOT NULL COMMENT '教师id',
  `teacher_group_id` int(11) NOT NULL COMMENT '教师组id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `teacher_id`(`teacher_id`) USING BTREE,
  INDEX `teacher_group_id`(`teacher_group_id`) USING BTREE,
  CONSTRAINT `teacher_mid_group_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `teacher_mid_group_ibfk_2` FOREIGN KEY (`teacher_group_id`) REFERENCES `teacher_group` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '教师与教师组中间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher_mid_group
-- ----------------------------
INSERT INTO `teacher_mid_group` VALUES (1, '2020-01-27 18:17:05', '2020-01-27 18:17:05', 2, 2);
INSERT INTO `teacher_mid_group` VALUES (2, '2020-01-27 18:17:05', '2020-01-27 18:17:05', 3, 2);
INSERT INTO `teacher_mid_group` VALUES (3, '2020-01-27 18:17:05', '2020-01-27 18:17:05', 4, 2);
INSERT INTO `teacher_mid_group` VALUES (4, '2020-01-27 18:17:05', '2020-01-27 18:17:05', 7, 2);
INSERT INTO `teacher_mid_group` VALUES (5, '2020-01-27 20:13:52', '2020-01-27 20:13:52', 1, 1);

-- ----------------------------
-- Table structure for test_paper
-- ----------------------------
DROP TABLE IF EXISTS `test_paper`;
CREATE TABLE `test_paper`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `modify_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `exam_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `question_score` int(10) NOT NULL COMMENT '本题满分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '试卷' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(70) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `create_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `modify_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `active` int(1) NOT NULL DEFAULT 1 COMMENT '0未激活，1已激活',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '20171611101', '$2a$10$tVnlcn3CYW5TaDcroR7pGO/f3CAlSANXo3f3WFJNZCFPbyX2yrIi.', '2020-01-17 22:23:04', '2020-01-17 22:23:04', 1);
INSERT INTO `user` VALUES (3, '20171611103', '$2a$10$SXAvwVpF4uTsWvIygOWYTezFHLvmtw9VvUoeuQriWd8FVmRykjSWu', '2020-01-17 22:23:05', '2020-01-17 22:23:05', 1);
INSERT INTO `user` VALUES (4, '20191611101', '$2a$10$ofJVGbPzMD9r7effocUqPuDVf6ULvkn.Zh8n7p9E5malswzB0KB9C', '2020-01-17 22:23:05', '2020-01-17 22:23:05', 1);
INSERT INTO `user` VALUES (5, '20191611102', '$2a$10$ue9LQfq2BTNgXmkVHUw0Qu2eRZc6t.xiaK968cmB9SxIjK78GZ87y', '2020-01-17 22:23:05', '2020-01-17 22:23:05', 1);
INSERT INTO `user` VALUES (6, 'T1', '$2a$10$axYz72y/3SISEFQF3/WcGu5IR2Pi6ubk20fMsPFcOpqINDqxW0od.', '2020-01-17 22:27:53', '2020-01-17 22:27:53', 1);
INSERT INTO `user` VALUES (7, 'T2', '$2a$10$yAq9Nce29YbDkENzaRNnNueM1uQM43ao91/zN1JzP/GuEArVDXkYe', '2020-01-17 22:27:53', '2020-01-17 22:27:53', 1);
INSERT INTO `user` VALUES (8, 'T3', '$2a$10$DTNgkJaOSypdH3H7tZbo.Ok4SeGQeO9rg5T5Ae2sg4zLrOIuCOyRS', '2020-01-17 22:27:53', '2020-01-17 22:27:53', 1);
INSERT INTO `user` VALUES (14, '20191611103', '$2a$10$QPNuhUU3Hs.DOfk3Enh0s.kPx7z5/z3rjNvUg3IW5vnHneWlrJyy.', '2020-01-20 13:55:38', '2020-01-20 13:55:38', 1);
INSERT INTO `user` VALUES (15, 'Teacher4', '$2a$10$G5BQmBXcGmez6Gw8jBFxU.ywMyrcg8xuJzjOc11EogUMO5H1hRQme', '2020-01-20 13:58:24', '2020-01-20 13:58:24', 1);
INSERT INTO `user` VALUES (19, 'Teacher5', '$2a$10$jRasoBFlHBKx4HKRyLbzQO6JSf/yNhqlPvnKm/jDRGv43KfZ/iuve', '2020-01-20 15:49:34', '2020-01-20 15:49:34', 1);
INSERT INTO `user` VALUES (20, '20181611101', '$2a$10$OwTsi4xuAE6aL7C45OWCYekUzK4dWfELgyI3Xaplh2dPqlAaSynNm', '2020-01-20 15:50:30', '2020-01-20 15:50:30', 1);
INSERT INTO `user` VALUES (21, '20171611104', '$2a$10$3BDMrCnCSzfjtFZUkd.NbObe8iPJZCoDMADZB0FuRICsH7SgBsJH.', '2020-01-22 10:19:50', '2020-01-22 10:19:50', 1);
INSERT INTO `user` VALUES (22, '20171611105', '$2a$10$D1BrtdIkxRu9bR/SPutjN.TWtxi0Ty/muY0pGHkU5pPvk./54ZZeW', '2020-01-22 10:19:50', '2020-01-22 10:19:50', 1);
INSERT INTO `user` VALUES (23, '20171611106', '$2a$10$gmzqHBgCsnQ1dbnmc2mGnu9FwrhGV/lBzUMEfvRZ/056083aW7gGW', '2020-01-22 10:19:50', '2020-01-22 10:19:50', 1);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `modify_time` datetime(0) NOT NULL,
  `create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1, 1, '2020-01-17 22:23:04', '2020-01-17 22:23:04');
INSERT INTO `user_role` VALUES (3, 3, 1, '2020-01-17 22:23:05', '2020-01-17 22:23:05');
INSERT INTO `user_role` VALUES (4, 4, 1, '2020-01-17 22:23:05', '2020-01-17 22:23:05');
INSERT INTO `user_role` VALUES (5, 5, 1, '2020-01-17 22:23:05', '2020-01-17 22:23:05');
INSERT INTO `user_role` VALUES (6, 6, 1, '2020-01-17 22:27:53', '2020-01-17 22:27:53');
INSERT INTO `user_role` VALUES (7, 7, 1, '2020-01-17 22:27:53', '2020-01-17 22:27:53');
INSERT INTO `user_role` VALUES (8, 8, 1, '2020-01-17 22:27:53', '2020-01-17 22:27:53');
INSERT INTO `user_role` VALUES (13, 14, 1, '2020-01-20 13:55:38', '2020-01-20 13:55:38');
INSERT INTO `user_role` VALUES (14, 15, 2, '2020-01-20 13:58:24', '2020-01-20 13:58:24');
INSERT INTO `user_role` VALUES (18, 19, 2, '2020-01-20 15:49:34', '2020-01-20 15:49:34');
INSERT INTO `user_role` VALUES (19, 20, 1, '2020-01-20 15:50:30', '2020-01-20 15:50:30');
INSERT INTO `user_role` VALUES (20, 21, 1, '2020-01-22 10:19:50', '2020-01-22 10:19:50');
INSERT INTO `user_role` VALUES (21, 22, 1, '2020-01-22 10:19:50', '2020-01-22 10:19:50');
INSERT INTO `user_role` VALUES (22, 23, 1, '2020-01-22 10:19:50', '2020-01-22 10:19:50');

SET FOREIGN_KEY_CHECKS = 1;
