/*
SQLyog Enterprise - MySQL GUI v8.01 
MySQL - 5.1.45-community : Database - tenfold
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`tenfold` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `tenfold`;

/*Table structure for table `dd_column` */

DROP TABLE IF EXISTS `dd_column`;

CREATE TABLE `dd_column` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `createdBy` int(11) DEFAULT NULL,
  `updatedBy` int(11) DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `tableID` int(11) unsigned NOT NULL,
  `name` varchar(50) NOT NULL,
  `required` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `displayOrder` int(11) NOT NULL DEFAULT '0',
  `columnType` varchar(20) NOT NULL,
  `size` tinyint(4) DEFAULT NULL,
  `precision` tinyint(4) DEFAULT NULL,
  `dbName` varchar(50) NOT NULL,
  `label` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `dd_column` */

insert  into `dd_column`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`tableID`,`name`,`required`,`displayOrder`,`columnType`,`size`,`precision`,`dbName`,`label`) values (1,NULL,NULL,NULL,NULL,1,'TableID',1,10,'int',11,NULL,'id',NULL),(2,NULL,NULL,NULL,NULL,1,'Name',1,20,'varchar',50,NULL,'name',NULL),(3,NULL,NULL,NULL,NULL,1,'DatabaseName',1,30,'varchar',50,NULL,'dbName',NULL),(4,NULL,NULL,NULL,NULL,4,'PageID',1,10,'int',11,NULL,'id',''),(5,NULL,NULL,NULL,NULL,4,'Name',1,20,'varchar',50,NULL,'name',NULL),(6,NULL,NULL,NULL,NULL,4,'URL',1,30,'varchar',50,NULL,'url',NULL),(7,NULL,NULL,NULL,NULL,2,'Name',1,10,'varchar',50,NULL,'name',NULL),(8,NULL,NULL,NULL,NULL,2,'ColumnID',1,5,'int',11,NULL,'id',NULL),(9,NULL,NULL,NULL,NULL,2,'TableID',1,20,'int',11,NULL,'tableID',NULL),(10,NULL,NULL,NULL,NULL,2,'Required',1,30,'boolean',NULL,NULL,'required',NULL),(11,NULL,NULL,NULL,NULL,2,'DisplayOrder',1,40,'int',11,NULL,'displayOrder',NULL),(12,NULL,NULL,NULL,NULL,6,'ViewID',1,10,'int',11,NULL,'id',NULL),(13,NULL,NULL,NULL,NULL,6,'PageID',1,20,'int',11,NULL,'pageID',NULL),(14,NULL,NULL,NULL,NULL,3,'JoinID',1,10,'int',11,NULL,'id',NULL),(15,NULL,NULL,NULL,NULL,3,'FromTableID',1,20,'int',11,NULL,'fromTableID',NULL),(16,NULL,NULL,NULL,NULL,3,'ToTableID',1,30,'int',11,NULL,'toTableID',NULL),(17,NULL,NULL,NULL,NULL,6,'resultsPerPage',0,0,'int',NULL,NULL,'resultsPerPage',NULL);

/*Table structure for table `dd_field` */

DROP TABLE IF EXISTS `dd_field`;

CREATE TABLE `dd_field` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `createdBy` int(11) DEFAULT NULL,
  `updatedBy` int(11) DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `viewID` int(11) unsigned NOT NULL,
  `name` varchar(100) NOT NULL,
  `basisColumnID` int(11) DEFAULT NULL,
  `referenceID` int(11) DEFAULT NULL,
  `visible` tinyint(4) unsigned NOT NULL DEFAULT '1',
  `label` varchar(50) DEFAULT NULL,
  `displayOrder` int(11) NOT NULL DEFAULT '10',
  `editable` tinyint(4) unsigned NOT NULL DEFAULT '1',
  `searchable` tinyint(4) unsigned NOT NULL DEFAULT '0',
  `linkToFieldID` int(11) unsigned DEFAULT NULL,
  `linkFromFieldID` int(11) unsigned DEFAULT NULL,
  `sectionID` int(11) unsigned DEFAULT NULL,
  `displayType` char(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `dd_field` */

insert  into `dd_field`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`viewID`,`name`,`basisColumnID`,`referenceID`,`visible`,`label`,`displayOrder`,`editable`,`searchable`,`linkToFieldID`,`linkFromFieldID`,`sectionID`,`displayType`) values (1,NULL,NULL,NULL,NULL,1,'ManageTablesTableID',1,NULL,0,'ID',10,0,0,NULL,NULL,NULL,NULL),(2,NULL,NULL,NULL,NULL,1,'ManageTablesName',2,NULL,1,'Table name',20,0,1,4,1,NULL,NULL),(3,NULL,NULL,NULL,NULL,1,'ManageTablesDatabaseName',3,NULL,1,'DB name',30,0,1,NULL,NULL,NULL,NULL),(4,NULL,NULL,NULL,NULL,2,'DefineTableTableID',1,NULL,1,'ID',10,0,0,NULL,NULL,NULL,NULL),(5,NULL,NULL,NULL,NULL,2,'DefineTableName',2,NULL,1,'Table name',20,1,0,NULL,NULL,NULL,NULL),(6,NULL,NULL,NULL,NULL,2,'DefineTableDatabaseName',3,NULL,1,'DB name',30,1,0,NULL,NULL,NULL,NULL),(7,NULL,NULL,NULL,NULL,3,'WebpageListID',4,NULL,0,'ID',10,1,0,NULL,NULL,NULL,NULL),(8,NULL,NULL,NULL,NULL,3,'WebpageListName',5,NULL,1,'Name',10,1,1,10,7,NULL,NULL),(9,NULL,NULL,NULL,NULL,3,'WebpageListUrl',6,NULL,0,'URL',10,1,1,NULL,NULL,NULL,NULL),(10,NULL,NULL,NULL,NULL,4,'DefineWebpagePageID',4,NULL,1,'ID',10,0,0,NULL,NULL,NULL,NULL),(11,NULL,NULL,NULL,NULL,4,'DefineWebpageName',5,NULL,1,'Webpage name',10,1,0,NULL,NULL,NULL,NULL),(12,NULL,NULL,NULL,NULL,4,'DefineWebpageURL',6,NULL,1,'URL',10,1,0,NULL,NULL,NULL,NULL),(13,NULL,NULL,NULL,NULL,8,'DefineTableColumnName',7,NULL,1,'Name',20,1,0,NULL,NULL,NULL,NULL),(14,NULL,NULL,NULL,NULL,8,'DefineTableColumnID',8,NULL,1,'Column ID',10,1,0,NULL,NULL,NULL,NULL),(15,NULL,NULL,NULL,NULL,8,'DefineTableColumnRequired',10,NULL,1,'Required',30,1,0,NULL,NULL,NULL,NULL),(16,NULL,NULL,NULL,NULL,8,'DefineTableColumnDisplayOrder',11,NULL,1,'Order',15,1,0,NULL,NULL,NULL,NULL),(17,NULL,NULL,NULL,NULL,10,'DefineTableJoinFromTableID',15,NULL,1,'From table',10,1,0,NULL,NULL,NULL,NULL),(18,NULL,NULL,NULL,NULL,10,'DefineTableJoinJoinID',14,NULL,1,'Join ID',10,1,0,NULL,NULL,NULL,NULL),(19,NULL,NULL,NULL,NULL,9,'DefineWebpageViewViewID',12,NULL,1,'View ID',10,1,0,NULL,NULL,NULL,NULL),(20,NULL,NULL,NULL,NULL,10,'DefineTableJoinToTableID',16,NULL,1,'To ',10,1,0,NULL,NULL,NULL,NULL);

/*Table structure for table `dd_join` */

DROP TABLE IF EXISTS `dd_join`;

CREATE TABLE `dd_join` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `createdBy` int(11) DEFAULT NULL,
  `updatedBy` int(11) DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `joinType` char(2) NOT NULL,
  `fromTableID` int(11) unsigned NOT NULL,
  `childName` varchar(50) DEFAULT NULL,
  `toTableID` int(11) unsigned NOT NULL,
  `parentName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `dd_join` */

insert  into `dd_join`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`joinType`,`fromTableID`,`childName`,`toTableID`,`parentName`) values (1,NULL,NULL,NULL,NULL,'OM',2,'Columns',1,'Table'),(2,NULL,NULL,NULL,NULL,'OM',3,'Joins',1,'Table'),(3,NULL,NULL,NULL,NULL,'OM',6,'Views',4,'Page'),(4,NULL,NULL,NULL,NULL,'OM',5,'Field',6,'View');

/*Table structure for table `dd_join_column` */

DROP TABLE IF EXISTS `dd_join_column`;

CREATE TABLE `dd_join_column` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `createdBy` int(11) DEFAULT NULL,
  `updatedBy` int(11) DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `joinID` int(11) unsigned NOT NULL,
  `fromColumnID` int(11) unsigned DEFAULT NULL,
  `toColumnID` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `dd_join_column` */

insert  into `dd_join_column`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`joinID`,`fromColumnID`,`toColumnID`) values (1,NULL,NULL,NULL,NULL,1,9,1),(2,NULL,NULL,NULL,NULL,2,15,1),(3,NULL,NULL,NULL,NULL,3,13,4),(4,NULL,NULL,NULL,NULL,4,16,1);

/*Table structure for table `dd_page_section` */

DROP TABLE IF EXISTS `dd_page_section`;

CREATE TABLE `dd_page_section` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `createdBy` int(11) DEFAULT NULL,
  `updatedBy` int(11) DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `pageID` int(11) unsigned NOT NULL,
  `label` varchar(11) DEFAULT NULL,
  `sectionType` char(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `dd_page_section` */

/*Table structure for table `dd_reference` */

DROP TABLE IF EXISTS `dd_reference`;

CREATE TABLE `dd_reference` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `createdBy` int(11) DEFAULT NULL,
  `updatedBy` int(11) DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `viewID` int(11) unsigned NOT NULL,
  `joinID` int(11) unsigned NOT NULL,
  `parentID` int(11) unsigned DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `queryOrder` smallint(6) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `dd_reference` */

insert  into `dd_reference`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`viewID`,`joinID`,`parentID`,`name`,`queryOrder`) values (1,NULL,NULL,NULL,NULL,8,1,NULL,'ColumnToTable',0),(2,NULL,NULL,NULL,NULL,9,3,NULL,'ViewToPage',1),(3,NULL,NULL,NULL,NULL,10,2,NULL,'JoinToParentTable',0),(4,NULL,NULL,NULL,NULL,10,4,NULL,'JoinToChildTable',0);

/*Table structure for table `dd_smart_codes` */

DROP TABLE IF EXISTS `dd_smart_codes`;

CREATE TABLE `dd_smart_codes` (
  `smartType` varchar(50) NOT NULL,
  `smartCode` char(2) NOT NULL,
  `meaning` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `dd_smart_codes` */

insert  into `dd_smart_codes`(`smartType`,`smartCode`,`meaning`) values ('JoinTypes','OO','One to one'),('JoinTypes','OM','One to many'),('DisplayTypes','RB','Radio button'),('DisplayTypes','TF','Text field'),('DisplayTypes','TA','Text area'),('DisplayTypes','SB','Select box');

/*Table structure for table `dd_table` */

DROP TABLE IF EXISTS `dd_table`;

CREATE TABLE `dd_table` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `createdBy` int(11) DEFAULT NULL,
  `updatedBy` int(11) DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `dbName` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `dd_table` */

insert  into `dd_table`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`name`,`dbName`) values (1,NULL,NULL,NULL,NULL,'Table','dd_table'),(2,NULL,NULL,NULL,NULL,'Column','dd_column'),(3,NULL,NULL,NULL,NULL,'Join','dd_join'),(4,NULL,NULL,NULL,NULL,'WebPages','dd_webpage'),(5,NULL,NULL,NULL,NULL,'Field','dd_field'),(6,NULL,NULL,NULL,NULL,'View','dd_view');

/*Table structure for table `dd_view` */

DROP TABLE IF EXISTS `dd_view`;

CREATE TABLE `dd_view` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `createdBy` int(11) DEFAULT NULL,
  `updatedBy` int(11) DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `pageID` int(11) unsigned NOT NULL,
  `resultsPerPage` smallint(6) NOT NULL DEFAULT '0',
  `basisTableID` int(11) unsigned DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `parentID` int(11) unsigned DEFAULT NULL,
  `referenceID` int(11) unsigned DEFAULT NULL,
  `queryOrder` smallint(6) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `dd_view` */

insert  into `dd_view`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`pageID`,`resultsPerPage`,`basisTableID`,`name`,`parentID`,`referenceID`,`queryOrder`) values (1,NULL,NULL,NULL,NULL,1,100,1,'ManageTables',NULL,NULL,0),(2,NULL,NULL,NULL,NULL,2,1,1,'DefineTable',NULL,NULL,0),(3,NULL,NULL,NULL,NULL,3,100,4,'WebpageList',NULL,NULL,0),(4,NULL,NULL,NULL,NULL,4,1,4,'DefineWebpage',NULL,NULL,0),(8,NULL,NULL,NULL,NULL,2,0,2,'DefineTableColumn',2,1,0),(9,NULL,NULL,NULL,NULL,4,0,6,'DefineWebpageView',4,2,0),(10,NULL,NULL,NULL,NULL,2,0,3,'DefineTableJoinParent',2,3,0),(11,NULL,NULL,NULL,NULL,22,0,3,'DefineTableJoinChild',2,3,0);

/*Table structure for table `dd_webpage` */

DROP TABLE IF EXISTS `dd_webpage`;

CREATE TABLE `dd_webpage` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `createdBy` int(11) DEFAULT NULL,
  `updatedBy` int(11) DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `url` varchar(50) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `keyFieldID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `dd_webpage` */

insert  into `dd_webpage`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`name`,`url`,`title`,`keyFieldID`) values (1,NULL,NULL,NULL,NULL,'ManageTables','tables','Manage Tables',1),(2,NULL,NULL,NULL,NULL,'DefineTable','table','Define Table',4),(3,NULL,NULL,NULL,NULL,'WebpageList','webpages','List Webpages',7),(4,NULL,NULL,NULL,NULL,'DefineWebpage','webpage','Define Webpage',10);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
