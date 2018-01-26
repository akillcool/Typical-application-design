/*
Navicat MySQL Data Transfer

Source Server         : teacher
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : sims_teacher

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-01-25 09:40:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `courseId` int(11) NOT NULL,
  `advancedPlacement` varchar(45) DEFAULT NULL,
  `credit` varchar(5) DEFAULT NULL,
  `elective` varchar(45) DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  `schoolHours` int(11) DEFAULT NULL,
  `cName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`courseId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', 'java basic learning', '2', 'elective', 'professional', '32', 'java oop');
INSERT INTO `course` VALUES ('2', 'advanced mathematics', '1', 'compulsory', 'public ', '48', 'matrix theory');
INSERT INTO `course` VALUES ('3', 'math', '3', 'elective', 'public ', '48', 'math theory');
INSERT INTO `course` VALUES ('4', 'basement English', '2', 'compulsory', 'public', '48', 'advanced English');
INSERT INTO `course` VALUES ('5', 'software engineering', '2', 'elective', 'professional', '32', 'software testing');
INSERT INTO `course` VALUES ('6', 'none', '2', 'compulsory', 'professional', '48', 'database ');
INSERT INTO `course` VALUES ('7', 'software engineering', '2', 'compulsory', 'professional', '32', 'software architecture');

-- ----------------------------
-- Table structure for offer
-- ----------------------------
DROP TABLE IF EXISTS `offer`;
CREATE TABLE `offer` (
  `offerId` int(11) NOT NULL,
  `courseId` int(11) NOT NULL AUTO_INCREMENT,
  `teacherId` int(11) NOT NULL,
  `year` varchar(45) DEFAULT NULL,
  `semester` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`offerId`),
  KEY `courseId_idx` (`courseId`),
  KEY `fk_teacher_offer_idx` (`teacherId`),
  CONSTRAINT `fk_course_offer` FOREIGN KEY (`courseId`) REFERENCES `course` (`courseId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_teacher_offer` FOREIGN KEY (`teacherId`) REFERENCES `teacher` (`teacherId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of offer
-- ----------------------------
INSERT INTO `offer` VALUES ('1', '1', '1', '2018', 'Spring');
INSERT INTO `offer` VALUES ('2', '2', '2', '2018', 'Fall');
INSERT INTO `offer` VALUES ('3', '3', '1', '2018', 'Spring');
INSERT INTO `offer` VALUES ('4', '4', '4', '2018', 'Fall');
INSERT INTO `offer` VALUES ('5', '5', '3', '2018', 'Fal');
INSERT INTO `offer` VALUES ('6', '6', '6', '2018', 'Spring');
INSERT INTO `offer` VALUES ('7', '7', '9', '2018', 'Fall');
INSERT INTO `offer` VALUES ('8', '1', '1', '2019', 'Spring');
INSERT INTO `offer` VALUES ('9', '2', '2', '2019', 'Fall');
INSERT INTO `offer` VALUES ('10', '3', '1', '2019', 'Spring');
INSERT INTO `offer` VALUES ('11', '4', '5', '2019', 'Fall');
INSERT INTO `offer` VALUES ('12', '5', '7', '2019', 'Spring');
INSERT INTO `offer` VALUES ('13', '6', '10', '2019', 'Fall');
INSERT INTO `offer` VALUES ('14', '7', '11', '2019', 'Fall');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `studentId` int(11) NOT NULL,
  `sName` varchar(45) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`studentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', 'tom', 'male', '23');
INSERT INTO `student` VALUES ('2', 'jane', 'female', '20');
INSERT INTO `student` VALUES ('3', 'jack', 'male', '21');
INSERT INTO `student` VALUES ('4', 'kon', 'female', '25');
INSERT INTO `student` VALUES ('5', 'Martin', 'male', '19');
INSERT INTO `student` VALUES ('6', 'Kevin', 'male', '21');
INSERT INTO `student` VALUES ('7', 'Richard', 'male', '22');
INSERT INTO `student` VALUES ('8', 'Christina', 'female', '23');
INSERT INTO `student` VALUES ('9', 'Judy', 'female', '21');
INSERT INTO `student` VALUES ('10', 'Susan', 'female', '22');
INSERT INTO `student` VALUES ('11', 'Alice', 'female', '21');
INSERT INTO `student` VALUES ('12', 'Andy', 'male', '23');
INSERT INTO `student` VALUES ('13', 'Sam', 'male', '22');
INSERT INTO `student` VALUES ('14', 'Scott', 'male', '24');
INSERT INTO `student` VALUES ('15', 'Paul', 'male', '23');
INSERT INTO `student` VALUES ('16', 'Sally', 'female', '25');
INSERT INTO `student` VALUES ('17', 'Margaret', 'female', '18');
INSERT INTO `student` VALUES ('18', 'Teresa', 'female', '20');
INSERT INTO `student` VALUES ('19', 'Rita', 'female', '22');
INSERT INTO `student` VALUES ('20', 'Jessica', 'female', '21');
INSERT INTO `student` VALUES ('21', 'Albert', 'male', '23');
INSERT INTO `student` VALUES ('22', 'Taylor', 'male', '24');
INSERT INTO `student` VALUES ('23', 'Jackson', 'male', '21');
INSERT INTO `student` VALUES ('24', 'Jimmy', 'male', '22');
INSERT INTO `student` VALUES ('25', 'Allen', 'male', '24');
INSERT INTO `student` VALUES ('26', 'Vincent', 'male', '24');
INSERT INTO `student` VALUES ('27', 'Elizabeth', 'female', '22');
INSERT INTO `student` VALUES ('28', 'Kelly', 'female', '23');
INSERT INTO `student` VALUES ('29', 'May', 'female', '24');
INSERT INTO `student` VALUES ('30', 'Julie', 'female', '22');

-- ----------------------------
-- Table structure for take
-- ----------------------------
DROP TABLE IF EXISTS `take`;
CREATE TABLE `take` (
  `takeId` int(11) NOT NULL,
  `studentId` int(11) NOT NULL DEFAULT '0',
  `offerId` int(11) NOT NULL DEFAULT '0',
  `time` varchar(45) DEFAULT NULL,
  `score` double DEFAULT NULL,
  PRIMARY KEY (`takeId`,`studentId`,`offerId`),
  KEY `fk_student_take_idx` (`studentId`),
  KEY `fk_offer_take_idx` (`offerId`),
  CONSTRAINT `fk_offer_take` FOREIGN KEY (`offerId`) REFERENCES `offer` (`offerId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_student_take` FOREIGN KEY (`studentId`) REFERENCES `student` (`studentId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of take
-- ----------------------------
INSERT INTO `take` VALUES ('1', '1', '1', '2018-06-03', '90');
INSERT INTO `take` VALUES ('2', '2', '2', '2018-07-04', '88');
INSERT INTO `take` VALUES ('3', '3', '1', '2018-06-03', '100');
INSERT INTO `take` VALUES ('4', '2', '1', '2018-06-03', '88');
INSERT INTO `take` VALUES ('5', '4', '5', '2018-11-25', '78');
INSERT INTO `take` VALUES ('6', '4', '4', '2018-12-17', '89');
INSERT INTO `take` VALUES ('7', '5', '6', '2018-07-24', '65');
INSERT INTO `take` VALUES ('8', '6', '7', '2018-12-25', '70');
INSERT INTO `take` VALUES ('9', '12', '3', '2018-06-17', '91');
INSERT INTO `take` VALUES ('10', '21', '1', '2018-06-13', '90');
INSERT INTO `take` VALUES ('11', '8', '4', '2018-12-17', '78');
INSERT INTO `take` VALUES ('12', '9', '3', '2018-06-17', '76');
INSERT INTO `take` VALUES ('13', '10', '5', '2018-11-25', '84');
INSERT INTO `take` VALUES ('14', '22', '7', '2018-12-25', '87');
INSERT INTO `take` VALUES ('15', '11', '6', '2018-07-24', '88');
INSERT INTO `take` VALUES ('16', '13', '5', '2018-11-25', '79');
INSERT INTO `take` VALUES ('17', '15', '4', '2018-12-17', '66');
INSERT INTO `take` VALUES ('18', '17', '3', '2018-06-17', '61');
INSERT INTO `take` VALUES ('19', '19', '7', '2018-12-25', '64');
INSERT INTO `take` VALUES ('20', '25', '2', '2018-07-04', '70');
INSERT INTO `take` VALUES ('21', '24', '5', '2018-11-25', '73');
INSERT INTO `take` VALUES ('22', '30', '4', '2018-12-17', '74');
INSERT INTO `take` VALUES ('23', '21', '4', '2018-12-17', '85');
INSERT INTO `take` VALUES ('24', '16', '5', '2018-11-25', '83');
INSERT INTO `take` VALUES ('25', '11', '3', '2018-06-17', '82');
INSERT INTO `take` VALUES ('26', '8', '2', '2018-07-04', '87');
INSERT INTO `take` VALUES ('27', '6', '7', '2018-12-25', '89');
INSERT INTO `take` VALUES ('28', '7', '6', '2018-07-24', '96');
INSERT INTO `take` VALUES ('29', '4', '3', '2018-06-17', '93');
INSERT INTO `take` VALUES ('30', '5', '5', '2018-11-25', '91');
INSERT INTO `take` VALUES ('31', '15', '3', '2018-06-17', '60');
INSERT INTO `take` VALUES ('32', '20', '6', '2018-07-24', '66');
INSERT INTO `take` VALUES ('33', '12', '4', '2018-12-17', '67');
INSERT INTO `take` VALUES ('34', '15', '7', '2018-12-25', '85');
INSERT INTO `take` VALUES ('35', '18', '7', '2018-12-25', '82');
INSERT INTO `take` VALUES ('36', '23', '3', '2018-06-17', '69');
INSERT INTO `take` VALUES ('37', '26', '2', '2018-07-04', '61');
INSERT INTO `take` VALUES ('38', '27', '7', '2018-12-25', '73');
INSERT INTO `take` VALUES ('39', '10', '3', '2018-06-17', '76');
INSERT INTO `take` VALUES ('40', '9', '2', '2018-07-04', '78');
INSERT INTO `take` VALUES ('41', '8', '4', '2018-12-17', '79');
INSERT INTO `take` VALUES ('42', '5', '6', '2018-07-24', '80');
INSERT INTO `take` VALUES ('43', '23', '5', '2018-11-25', '85');
INSERT INTO `take` VALUES ('44', '22', '3', '2018-06-17', '88');
INSERT INTO `take` VALUES ('45', '26', '4', '2018-12-17', '84');
INSERT INTO `take` VALUES ('46', '17', '6', '2018-07-24', '75');
INSERT INTO `take` VALUES ('47', '10', '1', '2018-06-13', '76');
INSERT INTO `take` VALUES ('48', '8', '1', '2018-06-13', '78');
INSERT INTO `take` VALUES ('49', '3', '4', '2018-12-17', '86');
INSERT INTO `take` VALUES ('50', '4', '3', '2018-06-17', '89');
INSERT INTO `take` VALUES ('51', '7', '6', '2018-07-24', '80');
INSERT INTO `take` VALUES ('52', '27', '7', '2018-12-25', '83');
INSERT INTO `take` VALUES ('53', '28', '1', '2018-06-13', '95');
INSERT INTO `take` VALUES ('54', '26', '3', '2018-06-17', '68');
INSERT INTO `take` VALUES ('55', '17', '4', '2018-12-17', '68');
INSERT INTO `take` VALUES ('56', '15', '5', '2018-11-25', '58');
INSERT INTO `take` VALUES ('57', '13', '7', '2018-12-25', '54');
INSERT INTO `take` VALUES ('58', '19', '2', '2018-07-04', '73');
INSERT INTO `take` VALUES ('59', '24', '5', '2018-11-25', '89');
INSERT INTO `take` VALUES ('60', '28', '7', '2018-12-25', '92');
INSERT INTO `take` VALUES ('61', '30', '2', '2018-07-04', '93');
INSERT INTO `take` VALUES ('62', '17', '4', '2018-12-17', '90');
INSERT INTO `take` VALUES ('63', '14', '3', '2018-06-17', '89');
INSERT INTO `take` VALUES ('64', '11', '2', '2018-07-04', '84');
INSERT INTO `take` VALUES ('65', '18', '6', '2018-07-24', '74');
INSERT INTO `take` VALUES ('66', '19', '7', '2018-12-25', '77');
INSERT INTO `take` VALUES ('67', '28', '3', '2018-06-17', '71');
INSERT INTO `take` VALUES ('68', '24', '1', '2018-06-13', '64');
INSERT INTO `take` VALUES ('69', '26', '7', '2018-12-25', '60');
INSERT INTO `take` VALUES ('70', '29', '3', '2018-06-17', '83');
INSERT INTO `take` VALUES ('71', '23', '3', '2018-06-17', '82');
INSERT INTO `take` VALUES ('72', '15', '5', '2018-11-25', '88');
INSERT INTO `take` VALUES ('73', '12', '2', '2018-07-04', '66');
INSERT INTO `take` VALUES ('74', '1', '2', '2018-07-04', '78');
INSERT INTO `take` VALUES ('75', '5', '2', '2018-07-04', '74');
INSERT INTO `take` VALUES ('76', '2', '4', '2018-12-17', '93');
INSERT INTO `take` VALUES ('77', '7', '12', '2019-06-23', '84');
INSERT INTO `take` VALUES ('78', '9', '11', '2019-11-18', '92');
INSERT INTO `take` VALUES ('79', '10', '10', '2019-06-21', '84');
INSERT INTO `take` VALUES ('80', '20', '13', '2019-12-05', '90');
INSERT INTO `take` VALUES ('81', '26', '14', '2019-12-15', '63');
INSERT INTO `take` VALUES ('82', '16', '8', '2019-05-24', '68');
INSERT INTO `take` VALUES ('83', '17', '9', '2019-12-21', '84');
INSERT INTO `take` VALUES ('84', '19', '10', '2019-06-21', '97');
INSERT INTO `take` VALUES ('85', '15', '14', '2019-12-15', '86');
INSERT INTO `take` VALUES ('86', '14', '8', '2019-05-24', '72');
INSERT INTO `take` VALUES ('87', '19', '9', '2019-12-21', '87');
INSERT INTO `take` VALUES ('88', '26', '10', '2019-06-21', '80');
INSERT INTO `take` VALUES ('89', '17', '14', '2019-12-15', '89');
INSERT INTO `take` VALUES ('90', '24', '9', '2019-12-21', '94');
INSERT INTO `take` VALUES ('91', '23', '8', '2019-05-24', '92');
INSERT INTO `take` VALUES ('92', '21', '12', '2019-06-23', '85');
INSERT INTO `take` VALUES ('93', '10', '13', '2019-12-05', '86');
INSERT INTO `take` VALUES ('94', '5', '11', '2019-11-18', '82');
INSERT INTO `take` VALUES ('95', '6', '12', '2019-06-23', '73');
INSERT INTO `take` VALUES ('96', '4', '14', '2019-12-15', '76');
INSERT INTO `take` VALUES ('97', '6', '8', '2019-05-24', '77');
INSERT INTO `take` VALUES ('98', '16', '9', '2019-12-21', '79');
INSERT INTO `take` VALUES ('99', '27', '10', '2019-06-21', '94');
INSERT INTO `take` VALUES ('100', '22', '14', '2019-12-15', '99');
INSERT INTO `take` VALUES ('101', '25', '11', '2019-11-18', '100');
INSERT INTO `take` VALUES ('102', '21', '9', '2019-12-21', '93');
INSERT INTO `take` VALUES ('103', '27', '8', '2019-05-24', '67');
INSERT INTO `take` VALUES ('104', '29', '13', '2019-12-05', '65');
INSERT INTO `take` VALUES ('105', '30', '10', '2019-06-21', '74');
INSERT INTO `take` VALUES ('106', '21', '12', '2019-06-23', '78');
INSERT INTO `take` VALUES ('107', '11', '8', '2019-05-24', '73');
INSERT INTO `take` VALUES ('108', '22', '10', '2019-06-21', '84');
INSERT INTO `take` VALUES ('109', '15', '11', '2019-11-18', '87');
INSERT INTO `take` VALUES ('110', '18', '12', '2019-06-23', '88');
INSERT INTO `take` VALUES ('111', '19', '14', '2019-12-15', '79');
INSERT INTO `take` VALUES ('112', '27', '8', '2019-05-24', '48');
INSERT INTO `take` VALUES ('113', '26', '12', '2019-06-23', '52');
INSERT INTO `take` VALUES ('114', '22', '8', '2019-05-24', '66');
INSERT INTO `take` VALUES ('115', '19', '9', '2019-12-21', '73');
INSERT INTO `take` VALUES ('116', '17', '14', '2019-12-15', '79');
INSERT INTO `take` VALUES ('117', '15', '13', '2019-12-05', '65');
INSERT INTO `take` VALUES ('118', '14', '8', '2019-05-24', '78');
INSERT INTO `take` VALUES ('119', '10', '13', '2019-12-05', '94');
INSERT INTO `take` VALUES ('120', '6', '14', '2019-12-15', '87');
INSERT INTO `take` VALUES ('121', '8', '8', '2019-05-24', '84');
INSERT INTO `take` VALUES ('122', '23', '13', '2019-12-05', '83');
INSERT INTO `take` VALUES ('123', '24', '12', '2019-06-23', '87');
INSERT INTO `take` VALUES ('124', '22', '13', '2019-12-05', '96');
INSERT INTO `take` VALUES ('125', '16', '14', '2019-12-15', '74');
INSERT INTO `take` VALUES ('126', '17', '11', '2019-11-18', '79');
INSERT INTO `take` VALUES ('127', '14', '9', '2019-11-21', '74');
INSERT INTO `take` VALUES ('128', '23', '8', '2019-05-24', '86');
INSERT INTO `take` VALUES ('129', '20', '14', '2019-12-15', '83');
INSERT INTO `take` VALUES ('130', '26', '9', '2019-12-21', '89');
INSERT INTO `take` VALUES ('131', '25', '10', '2019-06-21', '74');
INSERT INTO `take` VALUES ('132', '24', '8', '2019-05-24', '65');
INSERT INTO `take` VALUES ('133', '23', '9', '2019-12-21', '98');
INSERT INTO `take` VALUES ('134', '16', '13', '2019-12-05', '67');
INSERT INTO `take` VALUES ('135', '17', '14', '2019-12-15', '69');
INSERT INTO `take` VALUES ('136', '18', '9', '2019-12-21', '73');
INSERT INTO `take` VALUES ('137', '14', '10', '2019-06-21', '86');
INSERT INTO `take` VALUES ('138', '30', '11', '2019-11-18', '89');
INSERT INTO `take` VALUES ('139', '23', '8', '2017-05-24', '67');
INSERT INTO `take` VALUES ('140', '26', '11', '2019-11-18', '73');
INSERT INTO `take` VALUES ('141', '21', '13', '2019-12-05', '75');
INSERT INTO `take` VALUES ('142', '18', '12', '2019-06-23', '76');
INSERT INTO `take` VALUES ('143', '16', '8', '2019-05-24', '77');
INSERT INTO `take` VALUES ('144', '12', '9', '2019-12-21', '73');
INSERT INTO `take` VALUES ('145', '17', '10', '2019-06-21', '84');
INSERT INTO `take` VALUES ('146', '14', '11', '2019-11-18', '87');
INSERT INTO `take` VALUES ('147', '24', '12', '2019-06-23', '55');
INSERT INTO `take` VALUES ('148', '30', '13', '2019-12-05', '67');
INSERT INTO `take` VALUES ('149', '22', '13', '2019-12-05', '64');
INSERT INTO `take` VALUES ('150', '16', '14', '2019-12-15', '70');
INSERT INTO `take` VALUES ('151', '23', '10', '2019-06-21', '88');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `teacherId` int(11) NOT NULL,
  `tName` varchar(45) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`teacherId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1', 'Bob', 'male', '44');
INSERT INTO `teacher` VALUES ('2', 'Selina', 'female', '38');
INSERT INTO `teacher` VALUES ('3', 'Mr.Wang', 'male', '60');
INSERT INTO `teacher` VALUES ('4', 'Richard', 'male', '35');
INSERT INTO `teacher` VALUES ('5', 'Bruce', 'male', '37');
INSERT INTO `teacher` VALUES ('6', 'David', 'male', '41');
INSERT INTO `teacher` VALUES ('7', 'Grace', 'female', '58');
INSERT INTO `teacher` VALUES ('8', 'Kate', 'female', '36');
INSERT INTO `teacher` VALUES ('9', 'Robert', 'male', '55');
INSERT INTO `teacher` VALUES ('10', 'Martin', 'male', '48');
INSERT INTO `teacher` VALUES ('11', 'Sim', 'male', '34');

-- ----------------------------
-- View structure for v_all_course
-- ----------------------------
DROP VIEW IF EXISTS `v_all_course`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_all_course` AS select `course`.`cName` AS `cName`,`course`.`credit` AS `credit`,`course`.`category` AS `category`,`course`.`elective` AS `elective`,`course`.`schoolHours` AS `schoolHours`,`offer`.`year` AS `year`,`offer`.`semester` AS `semester`,`teacher`.`tName` AS `tName` from ((`course` join `teacher`) join `offer`) where ((`course`.`courseId` = `offer`.`courseId`) and (`offer`.`teacherId` = `teacher`.`teacherId`)) order by `offer`.`year` ;

-- ----------------------------
-- View structure for v_course_score
-- ----------------------------
DROP VIEW IF EXISTS `v_course_score`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_course_score` AS select `course`.`cName` AS `cName`,`take`.`studentId` AS `studentId`,`student`.`sName` AS `sName`,`take`.`score` AS `score`,`offer`.`year` AS `year`,`offer`.`semester` AS `semester` from (((`offer` join `take`) join `course`) join `student`) where ((`course`.`courseId` = `offer`.`courseId`) and (`take`.`offerId` = `offer`.`offerId`) and (`take`.`studentId` = `student`.`studentId`)) order by `course`.`cName`,`take`.`studentId`,`offer`.`year` ;

-- ----------------------------
-- View structure for v_course_student
-- ----------------------------
DROP VIEW IF EXISTS `v_course_student`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_course_student` AS select `course`.`courseId` AS `courseId`,`course`.`cName` AS `cName`,`student`.`studentId` AS `studentId`,`student`.`sName` AS `sName` from (((`course` join `student`) join `take`) join `offer`) where ((`student`.`studentId` = `take`.`studentId`) and (`take`.`offerId` = `offer`.`offerId`) and (`offer`.`courseId` = `course`.`courseId`)) order by `course`.`courseId`,`student`.`studentId` ;

-- ----------------------------
-- View structure for v_student
-- ----------------------------
DROP VIEW IF EXISTS `v_student`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_student` AS select `student`.`studentId` AS `studentId`,`student`.`sName` AS `sName` from `student` ;

-- ----------------------------
-- View structure for v_student_score
-- ----------------------------
DROP VIEW IF EXISTS `v_student_score`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_student_score` AS select `v_course_score`.`cName` AS `cName`,`student`.`sName` AS `sName`,`student`.`studentId` AS `studentId`,`v_course_score`.`score` AS `score`,`v_course_score`.`year` AS `year`,`v_course_score`.`semester` AS `semester` from (`v_course_score` join `student`) where (`v_course_score`.`studentId` = `student`.`studentId`) order by `v_course_score`.`cName`,`student`.`studentId` ;

-- ----------------------------
-- View structure for v_teacher
-- ----------------------------
DROP VIEW IF EXISTS `v_teacher`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_teacher` AS select `teacher`.`teacherId` AS `teacherId`,`teacher`.`tName` AS `tName` from `teacher` ;

-- ----------------------------
-- View structure for v_teacher_course
-- ----------------------------
DROP VIEW IF EXISTS `v_teacher_course`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_teacher_course` AS select `teacher`.`teacherId` AS `teacherId`,`teacher`.`tName` AS `tName`,`course`.`cName` AS `cName`,`offer`.`year` AS `year`,`offer`.`semester` AS `semester` from ((`course` join `offer`) join `teacher`) where ((`teacher`.`teacherId` = `offer`.`teacherId`) and (`course`.`courseId` = `offer`.`courseId`)) order by `teacher`.`teacherId` ;

-- ----------------------------
-- Procedure structure for proce_addCourse
-- ----------------------------
DROP PROCEDURE IF EXISTS `proce_addCourse`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `proce_addCourse`(
courseId int,
advancedPlacement varchar(45),
credit varchar(5),
elective varchar(45),
category varchar(45),
schoolHours int,
cName varchar(45)
)
BEGIN
insert into course(courseId,advancePlacement,credit,elective,category,schoolHours,cName) 
value (courseId,advancePlacement,credit,elective,category,schoolHours,cName);
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for proce_addStudent
-- ----------------------------
DROP PROCEDURE IF EXISTS `proce_addStudent`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `proce_addStudent`(
Id int,
sName varchar(45),
gender varchar(10),
age int
)
BEGIN
insert into sims_teacher.student(studentId,sName,gender,age) value(Id,sname,gender,age);
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for proce_addTeacher
-- ----------------------------
DROP PROCEDURE IF EXISTS `proce_addTeacher`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `proce_addTeacher`(
Id int,
Tname varchar(45), 
gender varchar(10),
age int
)
BEGIN
insert into sims_teacher.teacher(teacherId,tName,gender,age) value(Id,Tname,gender,age);
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for proce_delCourse
-- ----------------------------
DROP PROCEDURE IF EXISTS `proce_delCourse`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `proce_delCourse`(courseId int)
BEGIN
delete from course where courseId = course.courseId;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for proce_delStudent
-- ----------------------------
DROP PROCEDURE IF EXISTS `proce_delStudent`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `proce_delStudent`(studentId int)
BEGIN
delete from student where studentId = student.studentId;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for proce_delTeacher
-- ----------------------------
DROP PROCEDURE IF EXISTS `proce_delTeacher`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `proce_delTeacher`(teacherId int)
BEGIN
delete from teacher where teacherId = teacher.teacherId;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for proc_teacherForCourse
-- ----------------------------
DROP PROCEDURE IF EXISTS `proc_teacherForCourse`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_teacherForCourse`(
courseId int,
teacherId int,
year varchar(45),
semester varchar(45),
offerId int)
BEGIN
insert into offer(courseId ,teacherId ,year ,semester,offerId) 
value (courseId ,teacherId ,year ,semester,offerId);
END
;;
DELIMITER ;
