/*
SQLyog Enterprise - MySQL GUI v8.01 
MySQL - 5.1.45-community : Database - tantalum_meta
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`tantalum_meta` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `tantalum_meta`;

/*Table structure for table `tan_button` */

DROP TABLE IF EXISTS `tan_button`;

CREATE TABLE `tan_button` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `createdBy` int(11) unsigned DEFAULT NULL,
  `updatedBy` int(11) unsigned DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updateProcess` varchar(100) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `label` varchar(50) DEFAULT NULL,
  `pageID` int(11) unsigned NOT NULL,
  `buttonType` varchar(20) NOT NULL,
  `onClick` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tableName` (`pageID`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tan_button` */

/*Table structure for table `tan_column` */

DROP TABLE IF EXISTS `tan_column`;

CREATE TABLE `tan_column` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `createdBy` int(11) unsigned DEFAULT NULL,
  `updatedBy` int(11) unsigned DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updateProcess` varchar(100) DEFAULT NULL,
  `tableID` int(11) unsigned NOT NULL,
  `name` varchar(50) NOT NULL,
  `required` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `displayOrder` int(11) NOT NULL DEFAULT '0',
  `columnType` varchar(20) DEFAULT NULL,
  `size` tinyint(4) DEFAULT NULL,
  `precision` tinyint(4) DEFAULT NULL,
  `dbName` varchar(50) NOT NULL,
  `label` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tableName` (`tableID`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8;

/*Data for the table `tan_column` */

insert  into `tan_column`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`updateProcess`,`tableID`,`name`,`required`,`displayOrder`,`columnType`,`size`,`precision`,`dbName`,`label`) values (1,NULL,NULL,NULL,NULL,NULL,1,'TableID',1,10,'AutoIncrement',11,NULL,'id',NULL),(2,NULL,NULL,NULL,NULL,NULL,1,'Name',1,20,'String',50,NULL,'name',NULL),(3,NULL,NULL,NULL,NULL,NULL,1,'DatabaseName',1,30,'String',50,NULL,'dbName',NULL),(4,NULL,NULL,NULL,NULL,NULL,4,'PageID',1,10,'AutoIncrement',11,NULL,'id',''),(5,NULL,NULL,NULL,NULL,NULL,4,'Name',1,20,'String',50,NULL,'name',NULL),(6,NULL,NULL,NULL,NULL,NULL,4,'Title',1,30,'String',50,NULL,'label',NULL),(7,NULL,NULL,NULL,NULL,NULL,2,'Name',1,30,'String',50,NULL,'name',NULL),(8,NULL,NULL,NULL,NULL,NULL,2,'ColumnID',1,10,'AutoIncrement',11,NULL,'id',NULL),(9,NULL,NULL,NULL,NULL,NULL,2,'TableID',1,20,'Integer',11,NULL,'tableID',NULL),(10,NULL,NULL,NULL,NULL,NULL,2,'Required',1,35,'Boolean',NULL,NULL,'required',NULL),(11,NULL,NULL,NULL,NULL,NULL,2,'DisplayOrder',1,40,'Integer',11,NULL,'displayOrder',NULL),(12,NULL,NULL,NULL,NULL,NULL,6,'ViewID',1,10,'AutoIncrement',11,NULL,'id',NULL),(13,NULL,NULL,NULL,NULL,NULL,6,'PageID',1,20,'Integer',11,NULL,'pageID',NULL),(14,NULL,NULL,NULL,NULL,NULL,3,'JoinID',1,10,'AutoIncrement',11,NULL,'id',NULL),(15,NULL,NULL,NULL,NULL,NULL,3,'FromTableID',1,20,'Integer',11,NULL,'fromTableID',NULL),(16,NULL,NULL,NULL,NULL,NULL,3,'ToTableID',1,30,'Integer',11,NULL,'toTableID',NULL),(17,NULL,NULL,NULL,NULL,NULL,6,'ResultsPerPage',0,30,'Integer',NULL,NULL,'resultsPerPage',NULL),(18,NULL,NULL,NULL,NULL,NULL,2,'Database',0,50,'String',50,NULL,'dbName',NULL),(19,NULL,NULL,NULL,NULL,NULL,6,'BasisTableID',0,60,'Integer',11,NULL,'basisTableID',NULL),(20,NULL,NULL,NULL,NULL,NULL,6,'Name',1,70,'String',50,NULL,'name',NULL),(21,NULL,NULL,NULL,NULL,NULL,6,'ParentID',0,80,'Integer',11,NULL,'parentID',NULL),(22,NULL,NULL,NULL,NULL,NULL,6,'ReferenceID',0,90,'Integer',11,NULL,'referenceID',NULL),(26,NULL,NULL,NULL,NULL,NULL,8,'Type',1,10,'String',NULL,NULL,'enumType',NULL),(27,NULL,NULL,NULL,NULL,NULL,1,'CreatedBy',0,240,'CreatedBy',NULL,NULL,'createdBy',NULL),(28,NULL,NULL,NULL,NULL,NULL,1,'UpdatedBy',0,250,'UpdatedBy',NULL,NULL,'updatedBy',NULL),(29,NULL,NULL,NULL,NULL,NULL,1,'CreationDate',0,260,'CreationDate',NULL,NULL,'creationDate',NULL),(30,NULL,NULL,NULL,NULL,NULL,1,'UpdateDate',0,270,'UpdateDate',NULL,NULL,'updateDate',NULL),(31,NULL,NULL,NULL,NULL,NULL,1,'UpdateProcess',0,280,'UpdateProcess',NULL,NULL,'updateProcess',NULL),(32,NULL,NULL,NULL,NULL,NULL,1,'Project',0,90,'Integer',NULL,NULL,'ProjectID',NULL),(33,NULL,NULL,NULL,NULL,NULL,1,'Database',0,100,'Integer',NULL,NULL,'DatabaseID',NULL),(34,NULL,NULL,NULL,NULL,NULL,2,'ColumnType',1,60,'String',NULL,NULL,'columnType',NULL),(35,NULL,NULL,NULL,NULL,NULL,2,'Size',1,70,'Integer',NULL,NULL,'size',NULL),(36,NULL,NULL,NULL,NULL,NULL,2,'Precision',1,80,'Integer',NULL,NULL,'precision',NULL),(37,NULL,NULL,NULL,NULL,NULL,2,'Label',0,90,'String',NULL,NULL,'label',NULL),(38,NULL,NULL,NULL,NULL,NULL,5,'FieldID',1,10,'AutoIncrement',NULL,NULL,'id',NULL),(39,NULL,NULL,NULL,NULL,NULL,8,'Enum',1,20,'String',NULL,NULL,'enumValue',NULL),(40,NULL,NULL,NULL,NULL,NULL,8,'Meaning',0,30,'String',NULL,NULL,'meaning',NULL),(41,NULL,NULL,NULL,NULL,NULL,3,'CreatedBy',0,240,'CreatedBy',NULL,NULL,'createdBy',NULL),(42,NULL,NULL,NULL,NULL,NULL,3,'UpdatedBy',0,250,'UpdatedBy',NULL,NULL,'updatedBy',NULL),(43,NULL,NULL,NULL,NULL,NULL,3,'CreationDate',0,260,'CreationDate',NULL,NULL,'creationDate',NULL),(44,NULL,NULL,NULL,NULL,NULL,3,'UpdateDate',0,270,'UpdateDate',NULL,NULL,'updateDate',NULL),(45,NULL,NULL,NULL,NULL,NULL,3,'UpdateProcess',0,280,'UpdateProcess',NULL,NULL,'updateProcess',NULL),(48,NULL,NULL,NULL,NULL,NULL,4,'CreatedBy',0,240,'CreatedBy',NULL,NULL,'createdBy',NULL),(49,NULL,NULL,NULL,NULL,NULL,4,'UpdatedBy',0,250,'UpdatedBy',NULL,NULL,'updatedBy',NULL),(50,NULL,NULL,NULL,NULL,NULL,4,'CreationDate',0,260,'CreationDate',NULL,NULL,'creationDate',NULL),(51,NULL,NULL,NULL,NULL,NULL,4,'UpdateDate',0,270,'UpdateDate',NULL,NULL,'updateDate',NULL),(52,NULL,NULL,NULL,NULL,NULL,4,'UpdateProcess',0,280,'UpdateProcess',NULL,NULL,'updateProcess',NULL),(55,NULL,NULL,NULL,NULL,NULL,5,'CreatedBy',0,240,'CreatedBy',NULL,NULL,'createdBy',NULL),(56,NULL,NULL,NULL,NULL,NULL,5,'UpdatedBy',0,250,'UpdatedBy',NULL,NULL,'updatedBy',NULL),(57,NULL,NULL,NULL,NULL,NULL,5,'CreationDate',0,260,'CreationDate',NULL,NULL,'creationDate',NULL),(58,NULL,NULL,NULL,NULL,NULL,5,'UpdateDate',0,270,'UpdateDate',NULL,NULL,'updateDate',NULL),(59,NULL,NULL,NULL,NULL,NULL,5,'UpdateProcess',0,280,'UpdateProcess',NULL,NULL,'updateProcess',NULL),(62,NULL,NULL,NULL,NULL,NULL,6,'CreatedBy',0,240,'CreatedBy',NULL,NULL,'createdBy',NULL),(63,NULL,NULL,NULL,NULL,NULL,6,'UpdatedBy',0,250,'UpdatedBy',NULL,NULL,'updatedBy',NULL),(64,NULL,NULL,NULL,NULL,NULL,6,'CreationDate',0,260,'CreationDate',NULL,NULL,'creationDate',NULL),(65,NULL,NULL,NULL,NULL,NULL,6,'UpdateDate',0,270,'UpdateDate',NULL,NULL,'updateDate',NULL),(66,NULL,NULL,NULL,NULL,NULL,6,'UpdateProcess',0,280,'UpdateProcess',NULL,NULL,'updateProcess',NULL),(69,NULL,NULL,NULL,NULL,NULL,9,'CreatedBy',0,240,'CreatedBy',NULL,NULL,'createdBy',NULL),(70,NULL,NULL,NULL,NULL,NULL,9,'UpdatedBy',0,250,'UpdatedBy',NULL,NULL,'updatedBy',NULL),(71,NULL,NULL,NULL,NULL,NULL,9,'CreationDate',0,260,'CreationDate',NULL,NULL,'creationDate',NULL),(72,NULL,NULL,NULL,NULL,NULL,9,'UpdateDate',0,270,'UpdateDate',NULL,NULL,'updateDate',NULL),(73,NULL,NULL,NULL,NULL,NULL,9,'UpdateProcess',0,280,'UpdateProcess',NULL,NULL,'updateProcess',NULL),(74,NULL,NULL,NULL,NULL,NULL,9,'Id',1,10,'AutoIncrement',11,NULL,'id',NULL),(76,NULL,NULL,NULL,NULL,NULL,18,'CreatedBy',0,240,'CreatedBy',NULL,NULL,'createdBy',NULL),(77,NULL,NULL,NULL,NULL,NULL,18,'UpdatedBy',0,250,'UpdatedBy',NULL,NULL,'updatedBy',NULL),(78,NULL,NULL,NULL,NULL,NULL,18,'CreationDate',0,260,'CreationDate',NULL,NULL,'creationDate',NULL),(79,NULL,NULL,NULL,NULL,NULL,18,'UpdateDate',0,270,'UpdateDate',NULL,NULL,'updateDate',NULL),(80,NULL,NULL,NULL,NULL,NULL,18,'UpdateProcess',0,280,'UpdateProcess',NULL,NULL,'updateProcess',NULL),(81,NULL,NULL,NULL,NULL,NULL,18,'Id',1,10,'AutoIncrement',11,NULL,'id',NULL),(83,NULL,NULL,NULL,NULL,NULL,19,'CreatedBy',0,240,'CreatedBy',NULL,NULL,'createdBy',NULL),(84,NULL,NULL,NULL,NULL,NULL,19,'UpdatedBy',0,250,'UpdatedBy',NULL,NULL,'updatedBy',NULL),(85,NULL,NULL,NULL,NULL,NULL,19,'CreationDate',0,260,'CreationDate',NULL,NULL,'creationDate',NULL),(86,NULL,NULL,NULL,NULL,NULL,19,'UpdateDate',0,270,'UpdateDate',NULL,NULL,'updateDate',NULL),(87,NULL,NULL,NULL,NULL,NULL,19,'UpdateProcess',0,280,'UpdateProcess',NULL,NULL,'updateProcess',NULL),(88,NULL,NULL,NULL,NULL,NULL,19,'Id',1,10,'AutoIncrement',11,NULL,'id',NULL),(90,NULL,NULL,NULL,NULL,NULL,22,'CreatedBy',0,240,'CreatedBy',NULL,NULL,'createdBy',NULL),(91,NULL,NULL,NULL,NULL,NULL,22,'UpdatedBy',0,250,'UpdatedBy',NULL,NULL,'updatedBy',NULL),(92,NULL,NULL,NULL,NULL,NULL,22,'CreationDate',0,260,'CreationDate',NULL,NULL,'creationDate',NULL),(93,NULL,NULL,NULL,NULL,NULL,22,'UpdateDate',0,270,'UpdateDate',NULL,NULL,'updateDate',NULL),(94,NULL,NULL,NULL,NULL,NULL,22,'UpdateProcess',0,280,'UpdateProcess',NULL,NULL,'updateProcess',NULL),(95,NULL,NULL,NULL,NULL,NULL,22,'Id',1,10,'AutoIncrement',11,NULL,'id',NULL),(97,NULL,NULL,NULL,NULL,NULL,22,'Join',1,20,'Integer',NULL,NULL,'joinID',NULL),(98,NULL,NULL,NULL,NULL,NULL,22,'FromColumn',0,30,'Integer',NULL,NULL,'fromColumnID',NULL),(99,NULL,NULL,NULL,NULL,NULL,22,'FromConstant',0,40,'String',NULL,NULL,'fromText',NULL),(100,NULL,NULL,NULL,NULL,NULL,22,'ToColumn',1,50,'Integer',NULL,NULL,'toColumn',NULL),(101,NULL,NULL,NULL,NULL,NULL,3,'Name',0,40,'String',NULL,NULL,'name',NULL),(102,NULL,NULL,NULL,NULL,NULL,3,'Type',1,50,'String',NULL,NULL,'joinType',NULL),(103,NULL,NULL,NULL,NULL,NULL,5,'View',1,20,'Integer',NULL,NULL,'viewID',NULL),(104,NULL,NULL,NULL,NULL,NULL,5,'Name',1,30,'String',NULL,NULL,'name',NULL),(105,NULL,NULL,NULL,NULL,NULL,5,'BasisColumn',0,40,'Integer',NULL,NULL,'basisColumnID',NULL),(106,NULL,NULL,NULL,NULL,NULL,5,'Reference',0,50,'Integer',NULL,NULL,'referenceID',NULL),(107,NULL,NULL,NULL,NULL,NULL,5,'Visible',1,60,'Boolean',NULL,NULL,'visible',NULL),(108,NULL,NULL,NULL,NULL,NULL,5,'Label',0,70,'String',NULL,NULL,'label',NULL),(109,NULL,NULL,NULL,NULL,NULL,5,'Region',0,80,'String',NULL,NULL,'regionID',NULL),(110,NULL,NULL,NULL,NULL,NULL,18,'Page',1,70,'Integer',NULL,NULL,'pageID',NULL),(111,NULL,NULL,NULL,NULL,NULL,18,'Label',0,80,'String',NULL,NULL,'label',NULL),(112,NULL,NULL,NULL,NULL,NULL,18,'Parent',0,90,'Integer',NULL,NULL,'parentID',NULL),(113,NULL,NULL,NULL,NULL,NULL,18,'DisplayOrder',1,100,'Integer',NULL,NULL,'displayOrder',NULL),(114,NULL,NULL,NULL,NULL,NULL,18,'View',0,110,'Integer',NULL,NULL,'viewID',NULL),(115,NULL,NULL,NULL,NULL,NULL,18,'Name',1,120,'String',NULL,NULL,'name',NULL);

/*Table structure for table `tan_database` */

DROP TABLE IF EXISTS `tan_database`;

CREATE TABLE `tan_database` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `createdBy` int(11) unsigned DEFAULT NULL,
  `updatedBy` int(11) unsigned DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updateProcess` varchar(100) DEFAULT NULL,
  `server` varchar(50) DEFAULT NULL,
  `database` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tan_database` */

insert  into `tan_database`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`updateProcess`,`server`,`database`,`username`,`password`) values (1,NULL,NULL,NULL,NULL,NULL,'localhost','tantalum_meta','root','');

/*Table structure for table `tan_enum_generic` */

DROP TABLE IF EXISTS `tan_enum_generic`;

CREATE TABLE `tan_enum_generic` (
  `enumType` varchar(50) NOT NULL,
  `value` varchar(50) NOT NULL,
  `meaning` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tan_enum_generic` */

insert  into `tan_enum_generic`(`enumType`,`value`,`meaning`) values ('JoinTypes','OO','One to one'),('JoinTypes','OM','One to many'),('DisplayTypes','RB','Radio button'),('DisplayTypes','TF','Text field'),('DisplayTypes','TA','Text area'),('DisplayTypes','SB','Select box');

/*Table structure for table `tan_field` */

DROP TABLE IF EXISTS `tan_field`;

CREATE TABLE `tan_field` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `createdBy` int(11) unsigned DEFAULT NULL,
  `updatedBy` int(11) unsigned DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updateProcess` varchar(100) DEFAULT NULL,
  `viewID` int(11) unsigned NOT NULL,
  `name` varchar(100) NOT NULL,
  `basisColumnID` int(11) DEFAULT NULL,
  `referenceID` int(11) DEFAULT NULL,
  `visible` tinyint(4) unsigned NOT NULL DEFAULT '1',
  `label` varchar(50) DEFAULT NULL,
  `displayOrder` int(11) NOT NULL DEFAULT '10',
  `displayType` varchar(20) DEFAULT NULL,
  `regionID` int(11) unsigned DEFAULT NULL,
  `size` int(11) NOT NULL DEFAULT '0',
  `addable` tinyint(4) NOT NULL DEFAULT '1',
  `editable` tinyint(4) unsigned NOT NULL DEFAULT '1',
  `searchable` tinyint(4) unsigned NOT NULL DEFAULT '0',
  `defaultActionID` int(11) DEFAULT NULL,
  `forceDefault` tinyint(4) NOT NULL DEFAULT '0',
  `defaultValue` varchar(100) DEFAULT NULL,
  `defaultScript` text,
  `defaultFieldID` int(11) DEFAULT NULL,
  `defaultFieldType` varchar(20) DEFAULT NULL,
  `sortOrder` int(11) DEFAULT NULL,
  `sortDirection` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

/*Data for the table `tan_field` */

insert  into `tan_field`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`updateProcess`,`viewID`,`name`,`basisColumnID`,`referenceID`,`visible`,`label`,`displayOrder`,`displayType`,`regionID`,`size`,`addable`,`editable`,`searchable`,`defaultActionID`,`forceDefault`,`defaultValue`,`defaultScript`,`defaultFieldID`,`defaultFieldType`,`sortOrder`,`sortDirection`) values (1,NULL,NULL,NULL,NULL,NULL,1,'ManageTablesTableID',1,NULL,1,'ID',10,'Text',1,0,1,0,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(2,NULL,NULL,NULL,NULL,NULL,1,'ManageTablesName',2,NULL,1,'Table name',20,'Text',1,0,1,1,1,1,0,NULL,NULL,NULL,NULL,10,NULL),(3,NULL,NULL,NULL,NULL,NULL,1,'ManageTablesDatabaseName',3,NULL,1,'DB name',30,'Text',1,0,1,1,1,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(4,NULL,NULL,NULL,NULL,NULL,2,'DefineTableTableID',1,NULL,1,'ID',10,'Text',2,0,1,0,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(5,NULL,NULL,NULL,NULL,NULL,2,'DefineTableName',2,NULL,1,'Table name',20,'Text',2,0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(6,NULL,NULL,NULL,NULL,NULL,2,'DefineTableDatabaseName',3,NULL,1,'DB name',30,'Text',2,0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(7,NULL,NULL,NULL,NULL,NULL,3,'WebpageListID',4,NULL,1,'ID',10,'Text',3,0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(8,NULL,NULL,NULL,NULL,NULL,3,'WebpageListName',5,NULL,1,'Name',30,'Text',3,0,1,1,1,2,0,NULL,NULL,NULL,NULL,20,NULL),(10,NULL,NULL,NULL,NULL,NULL,4,'DefineWebpagePageID',4,NULL,1,'ID',10,'Text',4,0,1,0,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(11,NULL,NULL,NULL,NULL,NULL,4,'DefineWebpageName',5,NULL,1,'Webpage name',20,'Text',4,0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(12,NULL,NULL,NULL,NULL,NULL,4,'DefineWebpageTitle',6,NULL,1,'Title',30,'Text',4,0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(13,NULL,NULL,NULL,NULL,NULL,8,'DefineTableColumnName',7,NULL,1,'Name',20,'Text',5,0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(14,NULL,NULL,NULL,NULL,NULL,8,'DefineTableColumnID',8,NULL,1,'ID',10,'Text',5,0,1,0,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(15,NULL,NULL,NULL,NULL,NULL,8,'DefineTableColumnRequired',10,NULL,1,'Required',30,'Checkbox',5,0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(16,NULL,NULL,NULL,NULL,NULL,8,'DefineTableColumnDisplayOrder',11,NULL,1,'Order',15,'Text',5,0,1,1,0,NULL,0,NULL,'return (row * 10) + 10;',NULL,NULL,10,NULL),(17,NULL,NULL,NULL,NULL,NULL,10,'JoinFromTableID',15,NULL,0,'From table',10,'Text',6,0,1,1,0,NULL,0,NULL,NULL,4,'Hard',NULL,NULL),(18,NULL,NULL,NULL,NULL,NULL,10,'JoinJoinID',14,NULL,1,'ID',10,'Text',6,0,0,0,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(19,NULL,NULL,NULL,NULL,NULL,9,'ViewViewViewID',12,NULL,1,'ID',10,'Text',9,0,0,0,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(20,NULL,NULL,NULL,NULL,NULL,10,'JoinToTableID',16,NULL,1,'To table',10,'Text',6,0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(21,NULL,NULL,NULL,NULL,NULL,10,'JoinToTableName',2,3,1,'To table',10,'Text',6,0,1,1,0,3,0,NULL,NULL,NULL,NULL,20,NULL),(22,NULL,NULL,NULL,NULL,NULL,8,'DefineTableColumnDbName',18,NULL,1,'Database',40,'Text',5,0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(23,NULL,NULL,NULL,NULL,NULL,9,'ViewViewName',20,NULL,1,'Name',20,'Text',9,0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(24,NULL,NULL,NULL,NULL,NULL,8,'DefineTableColumnTableID',9,NULL,0,'TableID',10,'Text',5,0,1,1,0,NULL,0,NULL,NULL,4,'Hard',NULL,NULL),(26,NULL,NULL,NULL,NULL,NULL,8,'ColumnColumnType',34,NULL,1,'Type',100,'Text',5,0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(27,NULL,NULL,NULL,NULL,NULL,3,'WebpageListTitle',6,NULL,1,'Title',20,'Text',3,0,1,1,0,2,0,NULL,NULL,NULL,NULL,10,NULL),(28,NULL,NULL,NULL,NULL,NULL,9,'ViewViewPageID',13,NULL,0,'Page',30,'Text',9,0,1,1,0,NULL,0,NULL,NULL,10,'Hard',NULL,NULL),(29,NULL,NULL,NULL,NULL,NULL,10,'JoinName',101,NULL,1,'Name',10,'Text',6,0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,10,NULL),(30,NULL,NULL,NULL,NULL,NULL,10,'JoinJoinType',102,NULL,1,'Type',10,'Text',6,0,1,1,0,NULL,0,'0',NULL,NULL,NULL,NULL,NULL),(31,NULL,NULL,NULL,NULL,NULL,12,'FieldFieldID',38,NULL,1,'FieldID',10,'Text',11,0,0,0,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(32,NULL,NULL,NULL,NULL,NULL,12,'FieldViewID',103,NULL,0,'View',10,'Text',11,0,1,1,0,NULL,0,NULL,NULL,19,'Hard',NULL,NULL),(33,NULL,NULL,NULL,NULL,NULL,12,'FieldName',104,NULL,1,'Name',10,'Text',11,0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(34,NULL,NULL,NULL,NULL,NULL,12,'FieldBasisColumn',105,NULL,1,'BasisColumn',10,'Text',11,0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(35,NULL,NULL,NULL,NULL,NULL,13,'RegionPageID',110,NULL,0,'Page',10,'Text',12,0,1,1,0,NULL,0,NULL,NULL,10,'Hard',NULL,NULL),(36,NULL,NULL,NULL,NULL,NULL,13,'RegionRegionID',81,NULL,1,'ID',10,'Text',12,0,0,0,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(37,NULL,NULL,NULL,NULL,NULL,13,'RegionViewID',114,NULL,1,'View',10,'Text',12,0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(38,NULL,NULL,NULL,NULL,NULL,9,'ViewBasisTableID',19,NULL,1,'BasisTable',40,'Text',9,0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(39,NULL,NULL,NULL,NULL,NULL,9,'ViewResultsPerPage',17,NULL,1,'Results per page',100,'Text',9,0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(40,NULL,NULL,NULL,NULL,NULL,9,'ViewParentID',21,NULL,1,'Parent',50,'Text',9,0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,10,NULL),(41,NULL,NULL,NULL,NULL,NULL,9,'ViewReferenceID',22,NULL,1,'Reference',60,'Text',9,0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(42,NULL,NULL,NULL,NULL,NULL,12,'FieldLabel',108,NULL,1,'Label',70,'Text',11,0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `tan_field_action` */

DROP TABLE IF EXISTS `tan_field_action`;

CREATE TABLE `tan_field_action` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `createdBy` int(11) unsigned DEFAULT NULL,
  `updatedBy` int(11) unsigned DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updateProcess` varchar(100) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `label` varchar(50) DEFAULT NULL,
  `fieldID` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tableName` (`fieldID`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `tan_field_action` */

insert  into `tan_field_action`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`updateProcess`,`name`,`label`,`fieldID`) values (1,NULL,NULL,NULL,NULL,NULL,'DefineTable','DefineTable',2),(2,NULL,NULL,NULL,NULL,NULL,'DefinePage','DefinePage',8),(3,NULL,NULL,NULL,NULL,NULL,'DefineTable','DefineTable',21);

/*Table structure for table `tan_field_action_detail` */

DROP TABLE IF EXISTS `tan_field_action_detail`;

CREATE TABLE `tan_field_action_detail` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `createdBy` int(11) unsigned DEFAULT NULL,
  `updatedBy` int(11) unsigned DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updateProcess` varchar(100) DEFAULT NULL,
  `fieldActionID` int(11) unsigned NOT NULL,
  `fromFieldID` int(11) unsigned NOT NULL,
  `toFieldID` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fieldActionID` (`fieldActionID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `tan_field_action_detail` */

insert  into `tan_field_action_detail`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`updateProcess`,`fieldActionID`,`fromFieldID`,`toFieldID`) values (1,NULL,NULL,NULL,NULL,NULL,1,1,4),(2,NULL,NULL,NULL,NULL,NULL,2,7,10),(3,NULL,NULL,NULL,NULL,NULL,3,20,4);

/*Table structure for table `tan_index` */

DROP TABLE IF EXISTS `tan_index`;

CREATE TABLE `tan_index` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `createdBy` int(11) unsigned DEFAULT NULL,
  `updatedBy` int(11) unsigned DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updateProcess` varchar(100) DEFAULT NULL,
  `tableID` int(11) unsigned NOT NULL,
  `displayOrder` tinyint(4) unsigned NOT NULL DEFAULT '1',
  `uniqueIndex` tinyint(4) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `tableID` (`tableID`,`displayOrder`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `tan_index` */

insert  into `tan_index`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`updateProcess`,`tableID`,`displayOrder`,`uniqueIndex`) values (1,NULL,NULL,NULL,NULL,NULL,1,1,1),(2,NULL,NULL,NULL,NULL,NULL,2,1,1),(3,NULL,NULL,NULL,NULL,NULL,3,1,1),(4,NULL,NULL,NULL,NULL,NULL,4,1,1),(5,NULL,NULL,NULL,NULL,NULL,5,1,1),(6,NULL,NULL,NULL,NULL,NULL,6,1,1),(8,NULL,NULL,NULL,NULL,NULL,8,1,1),(9,NULL,NULL,NULL,NULL,NULL,9,1,1);

/*Table structure for table `tan_index_column` */

DROP TABLE IF EXISTS `tan_index_column`;

CREATE TABLE `tan_index_column` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `createdBy` int(11) unsigned DEFAULT NULL,
  `updatedBy` int(11) unsigned DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updateProcess` varchar(100) DEFAULT NULL,
  `indexID` int(11) unsigned NOT NULL,
  `columnID` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `indexID` (`indexID`,`columnID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `tan_index_column` */

insert  into `tan_index_column`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`updateProcess`,`indexID`,`columnID`) values (1,NULL,NULL,NULL,NULL,NULL,1,1),(2,NULL,NULL,NULL,NULL,NULL,2,8),(3,NULL,NULL,NULL,NULL,NULL,3,14),(4,NULL,NULL,NULL,NULL,NULL,4,4),(5,NULL,NULL,NULL,NULL,NULL,5,38),(6,NULL,NULL,NULL,NULL,NULL,6,12);

/*Table structure for table `tan_join` */

DROP TABLE IF EXISTS `tan_join`;

CREATE TABLE `tan_join` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `createdBy` int(11) unsigned DEFAULT NULL,
  `updatedBy` int(11) unsigned DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updateProcess` varchar(100) DEFAULT NULL,
  `joinType` char(2) NOT NULL,
  `fromTableID` int(11) unsigned NOT NULL,
  `toTableID` int(11) unsigned NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `tan_join` */

insert  into `tan_join`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`updateProcess`,`joinType`,`fromTableID`,`toTableID`,`name`) values (1,NULL,NULL,NULL,NULL,NULL,'0',2,1,'Table'),(2,NULL,NULL,NULL,NULL,NULL,'0',3,1,'FromTable'),(3,NULL,NULL,NULL,NULL,NULL,'0',6,4,'Page'),(4,NULL,NULL,NULL,NULL,NULL,'0',5,6,'View'),(5,NULL,NULL,NULL,NULL,NULL,'0',3,1,'ToTable'),(6,NULL,NULL,NULL,NULL,NULL,'0',22,3,'Join'),(7,NULL,NULL,NULL,NULL,NULL,'0',5,2,'BasisColumn'),(9,NULL,NULL,NULL,NULL,NULL,'0',18,4,'Page'),(10,NULL,NULL,NULL,NULL,NULL,'0',18,6,'View');

/*Table structure for table `tan_join_column` */

DROP TABLE IF EXISTS `tan_join_column`;

CREATE TABLE `tan_join_column` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `createdBy` int(11) unsigned DEFAULT NULL,
  `updatedBy` int(11) unsigned DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updateProcess` varchar(100) DEFAULT NULL,
  `joinID` int(11) unsigned NOT NULL,
  `fromColumnID` int(11) unsigned DEFAULT NULL,
  `fromText` varchar(50) DEFAULT NULL,
  `toColumnID` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `joinColumn` (`joinID`,`toColumnID`),
  KEY `fromColumn` (`fromColumnID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `tan_join_column` */

insert  into `tan_join_column`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`updateProcess`,`joinID`,`fromColumnID`,`fromText`,`toColumnID`) values (1,NULL,NULL,NULL,NULL,NULL,1,9,NULL,1),(2,NULL,NULL,NULL,NULL,NULL,2,15,NULL,1),(3,NULL,NULL,NULL,NULL,NULL,3,13,NULL,4),(4,NULL,NULL,NULL,NULL,NULL,4,103,NULL,12),(5,NULL,NULL,NULL,NULL,NULL,5,16,NULL,1),(6,NULL,NULL,NULL,NULL,NULL,9,110,NULL,4),(7,NULL,NULL,NULL,NULL,NULL,10,114,NULL,12);

/*Table structure for table `tan_menu` */

DROP TABLE IF EXISTS `tan_menu`;

CREATE TABLE `tan_menu` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `createdBy` int(11) unsigned DEFAULT NULL,
  `updatedBy` int(11) unsigned DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updateProcess` varchar(100) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `label` varchar(50) DEFAULT NULL,
  `databaseID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tan_menu` */

/*Table structure for table `tan_page` */

DROP TABLE IF EXISTS `tan_page`;

CREATE TABLE `tan_page` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `createdBy` int(11) unsigned DEFAULT NULL,
  `updatedBy` int(11) unsigned DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updateProcess` varchar(100) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `label` varchar(100) DEFAULT NULL,
  `keyFieldID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `tan_page` */

insert  into `tan_page`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`updateProcess`,`name`,`label`,`keyFieldID`) values (1,NULL,NULL,NULL,NULL,NULL,'ManageTables','Manage Tables',1),(2,NULL,NULL,NULL,NULL,NULL,'DefineTable','Define Table',4),(3,NULL,NULL,NULL,NULL,NULL,'WebpageList','List Webpages',7),(4,NULL,NULL,NULL,NULL,NULL,'DefineWebpage','Define Webpage',10);

/*Table structure for table `tan_project` */

DROP TABLE IF EXISTS `tan_project`;

CREATE TABLE `tan_project` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `createdBy` int(11) unsigned DEFAULT NULL,
  `updatedBy` int(11) unsigned DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updateProcess` varchar(100) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `userTableID` int(11) DEFAULT NULL,
  `usernameColumnID` int(11) DEFAULT NULL,
  `passwordColumnID` int(11) DEFAULT NULL,
  `projectCode` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tan_project` */

insert  into `tan_project`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`updateProcess`,`name`,`userTableID`,`usernameColumnID`,`passwordColumnID`,`projectCode`) values (1,NULL,NULL,NULL,NULL,NULL,'Tantalum Builder',NULL,NULL,NULL,'TB'),(2,NULL,NULL,NULL,NULL,NULL,'Northwind Demo',NULL,NULL,NULL,'NW');

/*Table structure for table `tan_reference` */

DROP TABLE IF EXISTS `tan_reference`;

CREATE TABLE `tan_reference` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `createdBy` int(11) unsigned DEFAULT NULL,
  `updatedBy` int(11) unsigned DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updateProcess` varchar(100) DEFAULT NULL,
  `viewID` int(11) unsigned NOT NULL,
  `joinID` int(11) unsigned NOT NULL,
  `parentID` int(11) unsigned DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `queryOrder` smallint(6) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `tan_reference` */

insert  into `tan_reference`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`updateProcess`,`viewID`,`joinID`,`parentID`,`name`,`queryOrder`) values (1,NULL,NULL,NULL,NULL,NULL,8,1,NULL,'ColumnToTable',0),(2,NULL,NULL,NULL,NULL,NULL,9,3,NULL,'ViewToPage',1),(3,NULL,NULL,NULL,NULL,NULL,10,5,NULL,'JoinToParentTable',0),(4,NULL,NULL,NULL,NULL,NULL,10,2,NULL,'JoinToChildTable',0),(5,NULL,NULL,NULL,NULL,NULL,12,4,NULL,'FieldToView',0),(6,NULL,NULL,NULL,NULL,NULL,13,9,NULL,'RegionToPage',0);

/*Table structure for table `tan_region` */

DROP TABLE IF EXISTS `tan_region`;

CREATE TABLE `tan_region` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `createdBy` int(11) unsigned DEFAULT NULL,
  `updatedBy` int(11) unsigned DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updateProcess` varchar(100) DEFAULT NULL,
  `pageID` int(11) unsigned NOT NULL,
  `label` varchar(100) DEFAULT NULL,
  `regionType` varchar(50) DEFAULT NULL,
  `parentID` int(11) unsigned DEFAULT NULL,
  `displayOrder` int(11) NOT NULL,
  `viewID` int(11) unsigned DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `tan_region` */

insert  into `tan_region`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`updateProcess`,`pageID`,`label`,`regionType`,`parentID`,`displayOrder`,`viewID`,`name`) values (1,NULL,NULL,NULL,NULL,NULL,1,'Tables','BasicTable',NULL,1,1,'Tables'),(2,NULL,NULL,NULL,NULL,NULL,2,'Table','FormRegion',7,10,2,'Tables'),(3,NULL,NULL,NULL,NULL,NULL,3,'Web Pages','BasicTable',NULL,1,3,'Page'),(4,NULL,NULL,NULL,NULL,NULL,4,'Page','FormRegion',10,1,4,'Page'),(5,NULL,NULL,NULL,NULL,NULL,2,'Columns','BasicTable',8,1,8,'Columns'),(6,NULL,NULL,NULL,NULL,NULL,2,'Joins to parents','BasicTable',8,1,10,'Join'),(7,NULL,NULL,NULL,NULL,NULL,2,NULL,'VerticalContainer',NULL,1,NULL,'ManageTables'),(8,NULL,NULL,NULL,NULL,NULL,2,NULL,'HorizontalContainer',7,20,NULL,'JoinColumnContainer'),(9,NULL,NULL,NULL,NULL,NULL,4,'View','BasicTable',10,10,9,'Views'),(10,NULL,NULL,NULL,NULL,NULL,4,'Top','HorizontalContainer',NULL,10,NULL,'Top'),(11,NULL,NULL,NULL,NULL,NULL,4,'Field','BasicTable',NULL,20,12,'Fields'),(12,NULL,NULL,NULL,NULL,NULL,4,'Region','BasicTable',NULL,30,13,'Regions');

/*Table structure for table `tan_sequence` */

DROP TABLE IF EXISTS `tan_sequence`;

CREATE TABLE `tan_sequence` (
  `tableID` int(11) NOT NULL,
  `nextID` int(11) NOT NULL DEFAULT '1',
  `countBy` tinyint(4) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tan_sequence` */

insert  into `tan_sequence`(`tableID`,`nextID`,`countBy`) values (1,1,1),(2,1,1),(3,1,1),(4,1,1),(5,1,1),(6,1,1),(8,1,1);

/*Table structure for table `tan_table` */

DROP TABLE IF EXISTS `tan_table`;

CREATE TABLE `tan_table` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `createdBy` int(11) unsigned DEFAULT NULL,
  `updatedBy` int(11) unsigned DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updateProcess` varchar(100) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `dbName` varchar(50) NOT NULL,
  `projectID` int(11) unsigned DEFAULT NULL,
  `databaseID` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Data for the table `tan_table` */

insert  into `tan_table`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`updateProcess`,`name`,`dbName`,`projectID`,`databaseID`) values (1,NULL,NULL,NULL,NULL,NULL,'Table','tan_table',NULL,NULL),(2,NULL,NULL,NULL,NULL,NULL,'Column','tan_column',NULL,NULL),(3,NULL,NULL,NULL,NULL,NULL,'Join','tan_join',NULL,NULL),(4,NULL,NULL,NULL,NULL,NULL,'WebPage','tan_page',NULL,NULL),(5,NULL,NULL,NULL,NULL,NULL,'Field','tan_field',NULL,NULL),(6,NULL,NULL,NULL,NULL,NULL,'View','tan_view',NULL,NULL),(8,NULL,NULL,NULL,NULL,NULL,'Enum','tan_enum',NULL,NULL),(9,NULL,NULL,NULL,NULL,NULL,'User','tan_user',NULL,NULL),(18,NULL,NULL,NULL,NULL,NULL,'Region','tan_region',NULL,NULL),(19,NULL,NULL,NULL,NULL,NULL,'Reference','tan_reference',NULL,NULL),(20,NULL,NULL,NULL,NULL,NULL,'Index','tan_index',NULL,NULL),(21,NULL,NULL,NULL,NULL,NULL,'IndexColumn','tan_index_column',NULL,NULL),(22,NULL,NULL,NULL,NULL,NULL,'JoinColumn','tan_join_column',NULL,NULL);

/*Table structure for table `tan_user` */

DROP TABLE IF EXISTS `tan_user`;

CREATE TABLE `tan_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `createdBy` int(11) unsigned DEFAULT NULL,
  `updatedBy` int(11) unsigned DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updateProcess` varchar(100) DEFAULT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tan_user` */

insert  into `tan_user`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`updateProcess`,`username`,`password`) values (1,NULL,NULL,NULL,NULL,NULL,'tallred',NULL);

/*Table structure for table `tan_view` */

DROP TABLE IF EXISTS `tan_view`;

CREATE TABLE `tan_view` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `createdBy` int(11) unsigned DEFAULT NULL,
  `updatedBy` int(11) unsigned DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updateProcess` varchar(100) DEFAULT NULL,
  `pageID` int(11) unsigned NOT NULL,
  `resultsPerPage` smallint(6) NOT NULL DEFAULT '0',
  `basisTableID` int(11) unsigned DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `parentID` int(11) unsigned DEFAULT NULL,
  `referenceID` int(11) unsigned DEFAULT NULL,
  `queryOrder` smallint(6) NOT NULL DEFAULT '0',
  `label` varchar(100) DEFAULT NULL,
  `allowAdd` tinyint(3) unsigned NOT NULL DEFAULT '1',
  `allowEdit` tinyint(3) unsigned NOT NULL DEFAULT '1',
  `allowDelete` tinyint(3) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pageName` (`pageID`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `tan_view` */

insert  into `tan_view`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`updateProcess`,`pageID`,`resultsPerPage`,`basisTableID`,`name`,`parentID`,`referenceID`,`queryOrder`,`label`,`allowAdd`,`allowEdit`,`allowDelete`) values (1,NULL,NULL,NULL,NULL,NULL,1,100,1,'Table',NULL,NULL,0,NULL,1,1,0),(2,NULL,NULL,NULL,NULL,NULL,2,10,1,'Table',NULL,NULL,0,NULL,1,1,1),(3,NULL,NULL,NULL,NULL,NULL,3,100,4,'Webpage',NULL,NULL,0,NULL,1,0,0),(4,NULL,NULL,NULL,NULL,NULL,4,10,4,'Webpage',NULL,NULL,0,NULL,1,1,1),(8,NULL,NULL,NULL,NULL,NULL,2,0,2,'DefineTableColumn',2,1,0,NULL,1,1,1),(9,NULL,NULL,NULL,NULL,NULL,4,0,6,'Views',4,2,0,NULL,1,1,1),(10,NULL,NULL,NULL,NULL,NULL,2,0,3,'DefineTableJoinParent',2,4,0,NULL,1,1,1),(12,NULL,NULL,NULL,NULL,NULL,4,0,5,'Field',9,5,0,NULL,1,1,1),(13,NULL,NULL,NULL,NULL,NULL,4,0,18,'Region',4,6,0,NULL,1,1,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
