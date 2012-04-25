/*
SQLyog Community v9.0 
MySQL - 5.1.59-community : Database - pms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`pms` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `pms`;

/*Table structure for table `t_atmessage` */

DROP TABLE IF EXISTS `t_atmessage`;

CREATE TABLE `t_atmessage` (
  `messageid` varchar(36) NOT NULL,
  `employeeid` varchar(36) NOT NULL,
  `projectid` varchar(36) NOT NULL,
  PRIMARY KEY (`messageid`,`employeeid`,`projectid`),
  KEY `fk_t_atmessage_t_message1` (`messageid`),
  KEY `fk_t_atmessage_t_employee1` (`employeeid`),
  KEY `fk_t_atmessage_t_project1` (`projectid`),
  CONSTRAINT `fk_t_atmessage_t_employee1` FOREIGN KEY (`employeeid`) REFERENCES `t_employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_atmessage_t_message1` FOREIGN KEY (`messageid`) REFERENCES `t_message` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_atmessage_t_project1` FOREIGN KEY (`projectid`) REFERENCES `t_project` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_atmessage` */

/*Table structure for table `t_contact` */

DROP TABLE IF EXISTS `t_contact`;

CREATE TABLE `t_contact` (
  `id` varchar(36) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `companyname` varchar(100) DEFAULT NULL,
  `mobile` varchar(100) DEFAULT NULL,
  `tel` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `website` varchar(100) DEFAULT NULL,
  `weibo` varchar(100) DEFAULT NULL,
  `content` text,
  `projectid` varchar(36) NOT NULL,
  `createtime` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_contact_t_project1` (`projectid`),
  CONSTRAINT `fk_t_contact_t_project1` FOREIGN KEY (`projectid`) REFERENCES `t_project` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_contact` */

/*Table structure for table `t_contactnote` */

DROP TABLE IF EXISTS `t_contactnote`;

CREATE TABLE `t_contactnote` (
  `id` varchar(36) NOT NULL,
  `content` text,
  `createtime` varchar(20) DEFAULT NULL,
  `contactid` varchar(36) NOT NULL,
  `employeeid` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_contactnote_t_contact1` (`contactid`),
  KEY `fk_t_contactnote_t_employee1` (`employeeid`),
  CONSTRAINT `fk_t_contactnote_t_contact1` FOREIGN KEY (`contactid`) REFERENCES `t_contact` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_contactnote_t_employee1` FOREIGN KEY (`employeeid`) REFERENCES `t_employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_contactnote` */

/*Table structure for table `t_document` */

DROP TABLE IF EXISTS `t_document`;

CREATE TABLE `t_document` (
  `id` varchar(36) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `content` longtext,
  `dcreatetime` varchar(20) DEFAULT NULL,
  `employeeid` varchar(36) NOT NULL,
  `projectid` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_document_t_employee1` (`employeeid`),
  KEY `fk_t_document_t_project1` (`projectid`),
  CONSTRAINT `fk_t_document_t_employee1` FOREIGN KEY (`employeeid`) REFERENCES `t_employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_document_t_project1` FOREIGN KEY (`projectid`) REFERENCES `t_project` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_document` */

/*Table structure for table `t_employee` */

DROP TABLE IF EXISTS `t_employee`;

CREATE TABLE `t_employee` (
  `id` varchar(36) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_employee` */

insert  into `t_employee`(`id`,`username`,`password`) values ('1','admin','123');

/*Table structure for table `t_employee_project` */

DROP TABLE IF EXISTS `t_employee_project`;

CREATE TABLE `t_employee_project` (
  `employeeid` varchar(36) NOT NULL,
  `projectid` varchar(36) NOT NULL,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`employeeid`,`projectid`),
  KEY `fk_t_employee_has_t_project_t_project1` (`projectid`),
  KEY `fk_t_employee_has_t_project_t_employee1` (`employeeid`),
  CONSTRAINT `fk_t_employee_has_t_project_t_employee1` FOREIGN KEY (`employeeid`) REFERENCES `t_employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_employee_has_t_project_t_project1` FOREIGN KEY (`projectid`) REFERENCES `t_project` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_employee_project` */

/*Table structure for table `t_file` */

DROP TABLE IF EXISTS `t_file`;

CREATE TABLE `t_file` (
  `id` varchar(36) NOT NULL,
  `fname` varchar(50) DEFAULT NULL,
  `filename` varchar(50) DEFAULT NULL,
  `filesize` mediumtext,
  `fcreatetime` varchar(20) DEFAULT NULL,
  `fileTypeid` varchar(36) NOT NULL,
  `employeeid` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_file_t_fileType1` (`fileTypeid`),
  KEY `fk_t_file_t_employee1` (`employeeid`),
  CONSTRAINT `fk_t_file_t_employee1` FOREIGN KEY (`employeeid`) REFERENCES `t_employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_file_t_fileType1` FOREIGN KEY (`fileTypeid`) REFERENCES `t_filetype` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_file` */

/*Table structure for table `t_filetype` */

DROP TABLE IF EXISTS `t_filetype`;

CREATE TABLE `t_filetype` (
  `id` varchar(36) NOT NULL,
  `ftname` varchar(50) DEFAULT NULL,
  `ftcreatetime` varchar(20) DEFAULT NULL,
  `employeeid` varchar(36) NOT NULL,
  `projectid` varchar(36) NOT NULL,
  `filetypesize` mediumtext,
  PRIMARY KEY (`id`),
  KEY `fk_t_fileType_t_employee1` (`employeeid`),
  KEY `fk_t_fileType_t_project1` (`projectid`),
  CONSTRAINT `fk_t_fileType_t_employee1` FOREIGN KEY (`employeeid`) REFERENCES `t_employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_fileType_t_project1` FOREIGN KEY (`projectid`) REFERENCES `t_project` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_filetype` */

/*Table structure for table `t_goal` */

DROP TABLE IF EXISTS `t_goal`;

CREATE TABLE `t_goal` (
  `id` varchar(36) NOT NULL,
  `gname` varchar(50) DEFAULT NULL,
  `gdesc` text,
  `gcreatetime` varchar(20) DEFAULT NULL,
  `projectid` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_goal_t_project1` (`projectid`),
  CONSTRAINT `fk_t_goal_t_project1` FOREIGN KEY (`projectid`) REFERENCES `t_project` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_goal` */

/*Table structure for table `t_idea` */

DROP TABLE IF EXISTS `t_idea`;

CREATE TABLE `t_idea` (
  `id` varchar(36) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `content` longtext,
  `icreatetime` varchar(20) DEFAULT NULL,
  `employeeid` varchar(36) NOT NULL,
  `projectid` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_idea_t_employee1` (`employeeid`),
  KEY `fk_t_idea_t_project1` (`projectid`),
  CONSTRAINT `fk_t_idea_t_employee1` FOREIGN KEY (`employeeid`) REFERENCES `t_employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_idea_t_project1` FOREIGN KEY (`projectid`) REFERENCES `t_project` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_idea` */

/*Table structure for table `t_ideacomment` */

DROP TABLE IF EXISTS `t_ideacomment`;

CREATE TABLE `t_ideacomment` (
  `id` varchar(36) NOT NULL,
  `content` text,
  `createtime` varchar(20) DEFAULT NULL,
  `employeeid` varchar(36) NOT NULL,
  `ideaid` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_ideacomment_t_employee1` (`employeeid`),
  KEY `fk_t_ideacomment_t_idea1` (`ideaid`),
  CONSTRAINT `fk_t_ideacomment_t_employee1` FOREIGN KEY (`employeeid`) REFERENCES `t_employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_ideacomment_t_idea1` FOREIGN KEY (`ideaid`) REFERENCES `t_idea` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_ideacomment` */

/*Table structure for table `t_message` */

DROP TABLE IF EXISTS `t_message`;

CREATE TABLE `t_message` (
  `id` varchar(36) NOT NULL,
  `content` text,
  `mcreatetime` varchar(20) DEFAULT NULL,
  `projectid` varchar(36) NOT NULL,
  `employeeid` varchar(36) NOT NULL,
  `link` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_message_t_project1` (`projectid`),
  KEY `fk_t_message_t_employee1` (`employeeid`),
  CONSTRAINT `fk_t_message_t_employee1` FOREIGN KEY (`employeeid`) REFERENCES `t_employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_message_t_project1` FOREIGN KEY (`projectid`) REFERENCES `t_project` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_message` */

/*Table structure for table `t_project` */

DROP TABLE IF EXISTS `t_project`;

CREATE TABLE `t_project` (
  `id` varchar(36) NOT NULL,
  `pname` varchar(50) DEFAULT NULL,
  `pdesc` text,
  `pcreatetime` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_project` */

insert  into `t_project`(`id`,`pname`,`pdesc`,`pcreatetime`) values ('20ff2e71-2943-4746-a6fc-47bee7c05223','asad','asdsadasd','2012-04-20 06:15:13'),('355d0d07-2b8c-4400-8b76-43671f0080bd','asds','asdasdas','2012-04-20 06:16:59'),('bdcd2434-9feb-46e3-af4d-de1b2bd6499c','asf','asdf','2012-04-20 06:18:16'),('df4e5c27-6a25-4eb3-8f29-0a47266c2a91','asad','asdsadasd','2012-04-20 06:16:49');

/*Table structure for table `t_task` */

DROP TABLE IF EXISTS `t_task`;

CREATE TABLE `t_task` (
  `id` varchar(36) DEFAULT NULL,
  `tname` varchar(50) DEFAULT NULL,
  `tdesc` text,
  `state` varchar(20) DEFAULT NULL,
  `level` varchar(20) DEFAULT NULL,
  `begintime` varchar(20) DEFAULT NULL,
  `endtime` varchar(20) DEFAULT NULL,
  `tcreatetime` varchar(20) DEFAULT NULL,
  `rate` int(11) DEFAULT NULL,
  `employeeid` varchar(36) NOT NULL,
  `goalid` varchar(36) NOT NULL,
  KEY `fk_t_task_t_employee1` (`employeeid`),
  KEY `fk_t_task_t_goal1` (`goalid`),
  CONSTRAINT `fk_t_task_t_employee1` FOREIGN KEY (`employeeid`) REFERENCES `t_employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_task_t_goal1` FOREIGN KEY (`goalid`) REFERENCES `t_goal` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_task` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
