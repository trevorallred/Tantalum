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
  `id` char(36) NOT NULL,
  `createdBy` int(11) unsigned DEFAULT NULL,
  `updatedBy` int(11) unsigned DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updateProcess` varchar(100) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `label` varchar(50) DEFAULT NULL,
  `viewID` char(36) NOT NULL,
  `buttonType` varchar(20) NOT NULL,
  `onClick` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tableName` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tan_button` */

/*Table structure for table `tan_column` */

DROP TABLE IF EXISTS `tan_column`;

CREATE TABLE `tan_column` (
  `id` char(36) NOT NULL,
  `createdBy` int(11) unsigned DEFAULT NULL,
  `updatedBy` int(11) unsigned DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updateProcess` varchar(100) DEFAULT NULL,
  `tableID` char(36) NOT NULL,
  `name` varchar(50) NOT NULL,
  `required` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `displayOrder` int(11) NOT NULL DEFAULT '0',
  `columnType` varchar(20) DEFAULT NULL,
  `size` tinyint(4) DEFAULT NULL,
  `precision` tinyint(4) DEFAULT NULL,
  `dbName` varchar(50) NOT NULL,
  `label` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tableName` (`name`,`tableID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tan_column` */

insert  into `tan_column`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`updateProcess`,`tableID`,`name`,`required`,`displayOrder`,`columnType`,`size`,`precision`,`dbName`,`label`) values ('34b8e1dc-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce920bd-9f57-11df-936f-e37ecc873ea2','TableID',1,10,'UUID',11,NULL,'id',NULL),('34b8e6d9-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce920bd-9f57-11df-936f-e37ecc873ea2','Name',1,20,'String',50,NULL,'name',NULL),('34b8e8cf-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce920bd-9f57-11df-936f-e37ecc873ea2','DatabaseName',1,30,'String',50,NULL,'dbName',NULL),('34b8f09b-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce924a9-9f57-11df-936f-e37ecc873ea2','Name',1,30,'String',50,NULL,'name',NULL),('34b8f27a-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce924a9-9f57-11df-936f-e37ecc873ea2','ColumnID',1,10,'UUID',11,NULL,'id',NULL),('34b8f465-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce924a9-9f57-11df-936f-e37ecc873ea2','TableID',1,20,'String',11,NULL,'tableID',NULL),('34b8f638-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce924a9-9f57-11df-936f-e37ecc873ea2','Required',1,35,'Boolean',NULL,NULL,'required',NULL),('34b8f812-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce924a9-9f57-11df-936f-e37ecc873ea2','DisplayOrder',1,40,'Integer',11,NULL,'displayOrder',NULL),('34b8f9e5-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce92fa4-9f57-11df-936f-e37ecc873ea2','ModelID',1,10,'UUID',11,NULL,'id',NULL),('34b8fd81-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce92677-9f57-11df-936f-e37ecc873ea2','JoinID',1,10,'UUID',11,NULL,'id',NULL),('34b8ff5a-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce92677-9f57-11df-936f-e37ecc873ea2','FromTableID',1,20,'String',11,NULL,'fromTableID',NULL),('34b90128-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce92677-9f57-11df-936f-e37ecc873ea2','ToTableID',1,30,'String',11,NULL,'toTableID',NULL),('34b902f6-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce92fa4-9f57-11df-936f-e37ecc873ea2','ResultsPerPage',0,30,'Integer',NULL,NULL,'resultsPerPage',NULL),('34b904c4-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce924a9-9f57-11df-936f-e37ecc873ea2','Database',0,50,'String',50,NULL,'dbName',NULL),('34b90692-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce92fa4-9f57-11df-936f-e37ecc873ea2','BasisTableID',0,60,'String',11,NULL,'basisTableID',NULL),('34b90860-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce92fa4-9f57-11df-936f-e37ecc873ea2','Name',1,70,'String',50,NULL,'name',NULL),('34b90a28-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce92fa4-9f57-11df-936f-e37ecc873ea2','ParentID',0,80,'String',11,NULL,'parentID',NULL),('34b90bf6-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce92fa4-9f57-11df-936f-e37ecc873ea2','ReferenceID',0,90,'String',11,NULL,'referenceID',NULL),('34b92852-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce93150-9f57-11df-936f-e37ecc873ea2','Type',1,10,'String',NULL,NULL,'enumType',NULL),('34b92ac5-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce920bd-9f57-11df-936f-e37ecc873ea2','CreatedBy',0,240,'CreatedBy',NULL,NULL,'createdBy',NULL),('34b92ca5-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce920bd-9f57-11df-936f-e37ecc873ea2','UpdatedBy',0,250,'UpdatedBy',NULL,NULL,'updatedBy',NULL),('34b92e7e-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce920bd-9f57-11df-936f-e37ecc873ea2','CreationDate',0,260,'CreationDate',NULL,NULL,'creationDate',NULL),('34b93057-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce920bd-9f57-11df-936f-e37ecc873ea2','UpdateDate',0,270,'UpdateDate',NULL,NULL,'updateDate',NULL),('34b93231-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce920bd-9f57-11df-936f-e37ecc873ea2','UpdateProcess',0,280,'UpdateProcess',NULL,NULL,'updateProcess',NULL),('34b933f9-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce920bd-9f57-11df-936f-e37ecc873ea2','Project',0,90,'String',NULL,NULL,'ProjectID',NULL),('34b935c1-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce920bd-9f57-11df-936f-e37ecc873ea2','Database',0,100,'String',NULL,NULL,'DatabaseID',NULL),('34b9378f-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce924a9-9f57-11df-936f-e37ecc873ea2','ColumnType',1,60,'String',NULL,NULL,'columnType',NULL),('34b9395d-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce924a9-9f57-11df-936f-e37ecc873ea2','Size',1,70,'Integer',NULL,NULL,'size',NULL),('34b93b25-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce924a9-9f57-11df-936f-e37ecc873ea2','Precision',1,80,'Integer',NULL,NULL,'precision',NULL),('34b93cf3-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce924a9-9f57-11df-936f-e37ecc873ea2','Label',0,90,'String',NULL,NULL,'label',NULL),('34b93ec7-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce929b7-9f57-11df-936f-e37ecc873ea2','FieldID',1,10,'UUID',NULL,NULL,'id',NULL),('34b94095-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce93150-9f57-11df-936f-e37ecc873ea2','Enum',1,20,'String',NULL,NULL,'enumValue',NULL),('34b94285-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce93150-9f57-11df-936f-e37ecc873ea2','Meaning',0,30,'String',NULL,NULL,'meaning',NULL),('34b94453-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce92677-9f57-11df-936f-e37ecc873ea2','CreatedBy',0,240,'CreatedBy',NULL,NULL,'createdBy',NULL),('34b94626-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce92677-9f57-11df-936f-e37ecc873ea2','UpdatedBy',0,250,'UpdatedBy',NULL,NULL,'updatedBy',NULL),('34b94777-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce92677-9f57-11df-936f-e37ecc873ea2','CreationDate',0,260,'CreationDate',NULL,NULL,'creationDate',NULL),('34b94911-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce92677-9f57-11df-936f-e37ecc873ea2','UpdateDate',0,270,'UpdateDate',NULL,NULL,'updateDate',NULL),('34b94a68-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce92677-9f57-11df-936f-e37ecc873ea2','UpdateProcess',0,280,'UpdateProcess',NULL,NULL,'updateProcess',NULL),('34b96e34-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce929b7-9f57-11df-936f-e37ecc873ea2','CreatedBy',0,240,'CreatedBy',NULL,NULL,'createdBy',NULL),('34b96f85-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce929b7-9f57-11df-936f-e37ecc873ea2','UpdatedBy',0,250,'UpdatedBy',NULL,NULL,'updatedBy',NULL),('34b970d0-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce929b7-9f57-11df-936f-e37ecc873ea2','CreationDate',0,260,'CreationDate',NULL,NULL,'creationDate',NULL),('34b9721a-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce929b7-9f57-11df-936f-e37ecc873ea2','UpdateDate',0,270,'UpdateDate',NULL,NULL,'updateDate',NULL),('34b9736b-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce929b7-9f57-11df-936f-e37ecc873ea2','UpdateProcess',0,280,'UpdateProcess',NULL,NULL,'updateProcess',NULL),('34b974b6-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce92fa4-9f57-11df-936f-e37ecc873ea2','CreatedBy',0,240,'CreatedBy',NULL,NULL,'createdBy',NULL),('34b97600-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce92fa4-9f57-11df-936f-e37ecc873ea2','UpdatedBy',0,250,'UpdatedBy',NULL,NULL,'updatedBy',NULL),('34b9774b-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce92fa4-9f57-11df-936f-e37ecc873ea2','CreationDate',0,260,'CreationDate',NULL,NULL,'creationDate',NULL),('34b9789c-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce92fa4-9f57-11df-936f-e37ecc873ea2','UpdateDate',0,270,'UpdateDate',NULL,NULL,'updateDate',NULL),('34b979e6-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce92fa4-9f57-11df-936f-e37ecc873ea2','UpdateProcess',0,280,'UpdateProcess',NULL,NULL,'updateProcess',NULL),('34b97b70-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce932f6-9f57-11df-936f-e37ecc873ea2','CreatedBy',0,240,'CreatedBy',NULL,NULL,'createdBy',NULL),('34b97cbb-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce932f6-9f57-11df-936f-e37ecc873ea2','UpdatedBy',0,250,'UpdatedBy',NULL,NULL,'updatedBy',NULL),('34b97e0b-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce932f6-9f57-11df-936f-e37ecc873ea2','CreationDate',0,260,'CreationDate',NULL,NULL,'creationDate',NULL),('34b97f56-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce932f6-9f57-11df-936f-e37ecc873ea2','UpdateDate',0,270,'UpdateDate',NULL,NULL,'updateDate',NULL),('34b980a6-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce932f6-9f57-11df-936f-e37ecc873ea2','UpdateProcess',0,280,'UpdateProcess',NULL,NULL,'updateProcess',NULL),('34b981f7-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce932f6-9f57-11df-936f-e37ecc873ea2','Id',1,10,'UUID',11,NULL,'id',NULL),('34b98347-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce93485-9f57-11df-936f-e37ecc873ea2','CreatedBy',0,240,'CreatedBy',NULL,NULL,'createdBy',NULL),('34b98492-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce93485-9f57-11df-936f-e37ecc873ea2','UpdatedBy',0,250,'UpdatedBy',NULL,NULL,'updatedBy',NULL),('34b985dd-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce93485-9f57-11df-936f-e37ecc873ea2','CreationDate',0,260,'CreationDate',NULL,NULL,'creationDate',NULL),('34b9873e-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce93485-9f57-11df-936f-e37ecc873ea2','UpdateDate',0,270,'UpdateDate',NULL,NULL,'updateDate',NULL),('34b98895-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce93485-9f57-11df-936f-e37ecc873ea2','UpdateProcess',0,280,'UpdateProcess',NULL,NULL,'updateProcess',NULL),('34b989eb-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce93485-9f57-11df-936f-e37ecc873ea2','Id',1,10,'UUID',11,NULL,'id',NULL),('34b98b41-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce93615-9f57-11df-936f-e37ecc873ea2','CreatedBy',0,240,'CreatedBy',NULL,NULL,'createdBy',NULL),('34b9a5e6-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce93615-9f57-11df-936f-e37ecc873ea2','UpdatedBy',0,250,'UpdatedBy',NULL,NULL,'updatedBy',NULL),('34b9a747-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce93615-9f57-11df-936f-e37ecc873ea2','CreationDate',0,260,'CreationDate',NULL,NULL,'creationDate',NULL),('34b9a8a3-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce93615-9f57-11df-936f-e37ecc873ea2','UpdateDate',0,270,'UpdateDate',NULL,NULL,'updateDate',NULL),('34b9a9f9-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce93615-9f57-11df-936f-e37ecc873ea2','UpdateProcess',0,280,'UpdateProcess',NULL,NULL,'updateProcess',NULL),('34be5a72-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce93615-9f57-11df-936f-e37ecc873ea2','Id',1,10,'UUID',11,NULL,'id',NULL),('34be5cad-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce93af0-9f57-11df-936f-e37ecc873ea2','CreatedBy',0,240,'CreatedBy',NULL,NULL,'createdBy',NULL),('34be5e1f-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce93af0-9f57-11df-936f-e37ecc873ea2','UpdatedBy',0,250,'UpdatedBy',NULL,NULL,'updatedBy',NULL),('34be5fb4-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce93af0-9f57-11df-936f-e37ecc873ea2','CreationDate',0,260,'CreationDate',NULL,NULL,'creationDate',NULL),('34deb994-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce93af0-9f57-11df-936f-e37ecc873ea2','UpdateDate',0,270,'UpdateDate',NULL,NULL,'updateDate',NULL),('34debc74-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce93af0-9f57-11df-936f-e37ecc873ea2','UpdateProcess',0,280,'UpdateProcess',NULL,NULL,'updateProcess',NULL),('34debe14-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce93af0-9f57-11df-936f-e37ecc873ea2','Id',1,10,'UUID',11,NULL,'id',NULL),('34debf98-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce93af0-9f57-11df-936f-e37ecc873ea2','Join',1,20,'String',NULL,NULL,'joinID',NULL),('34dec111-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce93af0-9f57-11df-936f-e37ecc873ea2','FromColumn',0,30,'String',NULL,NULL,'fromColumnID',NULL),('34dec3df-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce93af0-9f57-11df-936f-e37ecc873ea2','FromConstant',0,40,'String',NULL,NULL,'fromText',NULL),('34dec5be-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce93af0-9f57-11df-936f-e37ecc873ea2','ToColumn',1,50,'String',NULL,NULL,'toColumn',NULL),('34dec737-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce92677-9f57-11df-936f-e37ecc873ea2','Name',0,40,'String',NULL,NULL,'name',NULL),('34dec8a4-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce92677-9f57-11df-936f-e37ecc873ea2','Type',1,50,'String',NULL,NULL,'joinType',NULL),('34deca1c-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce929b7-9f57-11df-936f-e37ecc873ea2','ModelID',1,20,'String',NULL,NULL,'modelID',NULL),('34decb83-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce929b7-9f57-11df-936f-e37ecc873ea2','Name',1,30,'String',NULL,NULL,'name',NULL),('34decd73-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce929b7-9f57-11df-936f-e37ecc873ea2','BasisColumn',0,40,'String',NULL,NULL,'basisColumnID',NULL),('34deceec-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce929b7-9f57-11df-936f-e37ecc873ea2','Reference',0,50,'String',NULL,NULL,'referenceID',NULL),('34ded064-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce929b7-9f57-11df-936f-e37ecc873ea2','Visible',1,60,'Boolean',NULL,NULL,'visible',NULL),('34ded20a-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce929b7-9f57-11df-936f-e37ecc873ea2','Label',0,70,'String',NULL,NULL,'label',NULL),('34ded377-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce929b7-9f57-11df-936f-e37ecc873ea2','View',0,80,'String',NULL,NULL,'viewID',NULL),('34defdc5-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce93485-9f57-11df-936f-e37ecc873ea2','Label',0,80,'String',NULL,NULL,'label',NULL),('34deff4f-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce93485-9f57-11df-936f-e37ecc873ea2','Parent',0,90,'String',NULL,NULL,'parentID',NULL),('34df00b0-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce93485-9f57-11df-936f-e37ecc873ea2','DisplayOrder',1,100,'Integer',NULL,NULL,'displayOrder',NULL),('34df0223-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce93485-9f57-11df-936f-e37ecc873ea2','Model',1,20,'String',NULL,NULL,'modelID',NULL),('34df0396-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce93485-9f57-11df-936f-e37ecc873ea2','Name',1,120,'String',NULL,NULL,'name',NULL),('34df0503-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce929b7-9f57-11df-936f-e37ecc873ea2','DisplayOrder',1,140,'Integer',NULL,NULL,'displayOrder',NULL),('34df066a-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce929b7-9f57-11df-936f-e37ecc873ea2','DisplayType',1,150,'String',NULL,NULL,'displayType',NULL),('34df07d7-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce929b7-9f57-11df-936f-e37ecc873ea2','Size',1,160,'Integer',NULL,NULL,'size',NULL),('34df0944-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce929b7-9f57-11df-936f-e37ecc873ea2','Addable',1,170,'Boolean',NULL,NULL,'addable',NULL),('34df0ab1-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce929b7-9f57-11df-936f-e37ecc873ea2','Editable',1,180,'Boolean',NULL,NULL,'editable',NULL),('34df0c18-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce929b7-9f57-11df-936f-e37ecc873ea2','Searchable',1,190,'Boolean',NULL,NULL,'searchable',NULL),('34df0d7f-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce929b7-9f57-11df-936f-e37ecc873ea2','DefaultAction',0,200,'String',NULL,NULL,'defaultActionID',NULL),('34df0f25-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce929b7-9f57-11df-936f-e37ecc873ea2','ForceDefault',1,210,'Boolean',NULL,NULL,'forceDefault',NULL),('34df1070-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce929b7-9f57-11df-936f-e37ecc873ea2','DefaultValue',0,220,'String',NULL,NULL,'defaultValue',NULL);

/*Table structure for table `tan_database` */

DROP TABLE IF EXISTS `tan_database`;

CREATE TABLE `tan_database` (
  `id` char(36) NOT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tan_database` */

insert  into `tan_database`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`updateProcess`,`server`,`database`,`username`,`password`) values ('4fb1a314-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'localhost','tantalum_meta','root','');

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
  `id` char(36) NOT NULL,
  `createdBy` int(11) unsigned DEFAULT NULL,
  `updatedBy` int(11) unsigned DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updateProcess` varchar(100) DEFAULT NULL,
  `modelID` char(36) NOT NULL,
  `name` varchar(100) NOT NULL,
  `basisColumnID` char(36) DEFAULT NULL,
  `referenceID` char(36) DEFAULT NULL,
  `visible` tinyint(4) unsigned NOT NULL DEFAULT '1',
  `label` varchar(50) DEFAULT NULL,
  `displayOrder` int(11) NOT NULL DEFAULT '10',
  `displayType` varchar(20) DEFAULT NULL,
  `viewID` char(36) NOT NULL,
  `size` int(11) NOT NULL DEFAULT '0',
  `addable` tinyint(4) NOT NULL DEFAULT '1',
  `editable` tinyint(4) unsigned NOT NULL DEFAULT '1',
  `searchable` tinyint(4) unsigned NOT NULL DEFAULT '0',
  `defaultActionID` char(36) DEFAULT NULL,
  `forceDefault` tinyint(4) NOT NULL DEFAULT '0',
  `defaultValue` varchar(100) DEFAULT NULL,
  `defaultScript` text,
  `defaultFieldID` char(36) DEFAULT NULL,
  `defaultFieldType` varchar(20) DEFAULT NULL,
  `sortOrder` int(11) DEFAULT NULL,
  `sortDirection` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tan_field` */

insert  into `tan_field`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`updateProcess`,`modelID`,`name`,`basisColumnID`,`referenceID`,`visible`,`label`,`displayOrder`,`displayType`,`viewID`,`size`,`addable`,`editable`,`searchable`,`defaultActionID`,`forceDefault`,`defaultValue`,`defaultScript`,`defaultFieldID`,`defaultFieldType`,`sortOrder`,`sortDirection`) values ('55142ce5-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e0687-9f58-11df-936f-e37ecc873ea2','ManageTablesTableID','34b8e1dc-9f56-11df-936f-e37ecc873ea2',NULL,1,'ID',10,'Text','',0,1,0,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),('551431ba-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e0687-9f58-11df-936f-e37ecc873ea2','ManageTablesName','34b8e6d9-9f56-11df-936f-e37ecc873ea2',NULL,1,'Table name',20,'Text','e88065f5-9f57-11df-936f-e37ecc873ea2',0,1,1,1,'5ba14a0d-9f56-11df-936f-e37ecc873ea2',0,NULL,NULL,NULL,NULL,10,NULL),('551435fc-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e0687-9f58-11df-936f-e37ecc873ea2','ManageTablesDatabaseName','34b8e8cf-9f56-11df-936f-e37ecc873ea2',NULL,1,'DB name',30,'Text','e88065f5-9f57-11df-936f-e37ecc873ea2',0,1,1,1,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),('5514388b-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e0978-9f58-11df-936f-e37ecc873ea2','DefineTableTableID','34b8e1dc-9f56-11df-936f-e37ecc873ea2',NULL,1,'ID',10,'Text','',0,1,0,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),('55143aee-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e0978-9f58-11df-936f-e37ecc873ea2','DefineTableName','34b8e6d9-9f56-11df-936f-e37ecc873ea2',NULL,1,'Table name',20,'Text','e88068d5-9f57-11df-936f-e37ecc873ea2',0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),('55143d50-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e0978-9f58-11df-936f-e37ecc873ea2','DefineTableDatabaseName','34b8e8cf-9f56-11df-936f-e37ecc873ea2',NULL,1,'DB name',30,'Text','e88068d5-9f57-11df-936f-e37ecc873ea2',0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),('55144b97-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e0f48-9f58-11df-936f-e37ecc873ea2','DefineTableColumnName','34b8f09b-9f56-11df-936f-e37ecc873ea2',NULL,1,'Name',20,'Text','e8806e17-9f57-11df-936f-e37ecc873ea2',0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),('55144de3-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e0f48-9f58-11df-936f-e37ecc873ea2','DefineTableColumnID','34b8f27a-9f56-11df-936f-e37ecc873ea2',NULL,1,'ID',10,'Text','',0,1,0,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),('551454e2-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e0f48-9f58-11df-936f-e37ecc873ea2','DefineTableColumnRequired','34b8f638-9f56-11df-936f-e37ecc873ea2',NULL,1,'Required',30,'Checkbox','e8806e17-9f57-11df-936f-e37ecc873ea2',0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),('55145749-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e0f48-9f58-11df-936f-e37ecc873ea2','DefineTableColumnDisplayOrder','34b8f812-9f56-11df-936f-e37ecc873ea2',NULL,1,'Order',15,'Text','e8806e17-9f57-11df-936f-e37ecc873ea2',0,1,1,0,NULL,0,NULL,'return (row * 10) + 10;',NULL,NULL,10,NULL),('551459b1-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e12fb-9f58-11df-936f-e37ecc873ea2','JoinFromTableID','34b8ff5a-9f56-11df-936f-e37ecc873ea2',NULL,0,'From table',10,'Text','e8806fce-9f57-11df-936f-e37ecc873ea2',0,1,1,0,NULL,0,NULL,NULL,'5514388b-9f56-11df-936f-e37ecc873ea2','Hard',NULL,NULL),('55145c3c-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e12fb-9f58-11df-936f-e37ecc873ea2','JoinJoinID','34b8fd81-9f56-11df-936f-e37ecc873ea2',NULL,1,'ID',10,'Text','',0,0,0,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),('55145e8d-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e1122-9f58-11df-936f-e37ecc873ea2','ViewViewViewID','34b8f9e5-9f56-11df-936f-e37ecc873ea2',NULL,1,'ID',10,'Text','e88076f4-9f57-11df-936f-e37ecc873ea2',0,0,0,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),('551460de-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e12fb-9f58-11df-936f-e37ecc873ea2','JoinToTableID','34b90128-9f56-11df-936f-e37ecc873ea2',NULL,1,'To table',10,'Text','e8806fce-9f57-11df-936f-e37ecc873ea2',0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),('55146329-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e12fb-9f58-11df-936f-e37ecc873ea2','JoinToTableName','34b8e6d9-9f56-11df-936f-e37ecc873ea2','e3258b49-9f57-11df-936f-e37ecc873ea2',1,'To table',10,'Text','e8806fce-9f57-11df-936f-e37ecc873ea2',0,1,1,0,'5ba14e54-9f56-11df-936f-e37ecc873ea2',0,NULL,NULL,NULL,NULL,20,NULL),('5514657a-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e0f48-9f58-11df-936f-e37ecc873ea2','DefineTableColumnDbName','34b904c4-9f56-11df-936f-e37ecc873ea2',NULL,1,'Database',40,'Text','e8806e17-9f57-11df-936f-e37ecc873ea2',0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),('551467c6-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e1122-9f58-11df-936f-e37ecc873ea2','ViewViewName','34b90860-9f56-11df-936f-e37ecc873ea2',NULL,1,'Name',20,'Text','e88076f4-9f57-11df-936f-e37ecc873ea2',0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),('55146a11-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e0f48-9f58-11df-936f-e37ecc873ea2','DefineTableColumnTableID','34b8f465-9f56-11df-936f-e37ecc873ea2',NULL,0,'TableID',10,'Text','e8806e17-9f57-11df-936f-e37ecc873ea2',0,1,1,0,NULL,0,NULL,NULL,'5514388b-9f56-11df-936f-e37ecc873ea2','Hard',NULL,NULL),('55146ca7-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e0f48-9f58-11df-936f-e37ecc873ea2','ColumnColumnType','34b9378f-9f56-11df-936f-e37ecc873ea2',NULL,1,'Type',100,'Text','e8806e17-9f57-11df-936f-e37ecc873ea2',0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),('5514735b-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e12fb-9f58-11df-936f-e37ecc873ea2','JoinName','34dec737-9f56-11df-936f-e37ecc873ea2',NULL,1,'Name',10,'Text','e8806fce-9f57-11df-936f-e37ecc873ea2',0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,10,NULL),('55147562-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e12fb-9f58-11df-936f-e37ecc873ea2','JoinJoinType','34dec8a4-9f56-11df-936f-e37ecc873ea2',NULL,1,'Type',10,'Text','e8806fce-9f57-11df-936f-e37ecc873ea2',0,1,1,0,NULL,0,'0',NULL,NULL,NULL,NULL,NULL),('55147769-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e14d4-9f58-11df-936f-e37ecc873ea2','FieldFieldID','34b93ec7-9f56-11df-936f-e37ecc873ea2',NULL,1,'FieldID',10,'Text','e8807a52-9f57-11df-936f-e37ecc873ea2',0,0,0,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),('55147970-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e14d4-9f58-11df-936f-e37ecc873ea2','FieldViewID','34deca1c-9f56-11df-936f-e37ecc873ea2',NULL,0,'View',10,'Text','e8807a52-9f57-11df-936f-e37ecc873ea2',0,1,1,0,NULL,0,NULL,NULL,'55145e8d-9f56-11df-936f-e37ecc873ea2','Hard',NULL,NULL),('55147b82-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e14d4-9f58-11df-936f-e37ecc873ea2','FieldName','34decb83-9f56-11df-936f-e37ecc873ea2',NULL,1,'Name',10,'Text','e8807a52-9f57-11df-936f-e37ecc873ea2',0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),('55147d89-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e14d4-9f58-11df-936f-e37ecc873ea2','FieldBasisColumn','34decd73-9f56-11df-936f-e37ecc873ea2',NULL,1,'BasisColumn',10,'Text','e8807a52-9f57-11df-936f-e37ecc873ea2',0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),('551486f0-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e16ae-9f58-11df-936f-e37ecc873ea2','ViewViewID','34b989eb-9f56-11df-936f-e37ecc873ea2',NULL,1,'ID',10,'Text','e8807c09-9f57-11df-936f-e37ecc873ea2',0,0,0,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),('55148947-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e16ae-9f58-11df-936f-e37ecc873ea2','ViewModelID','34df0223-9f56-11df-936f-e37ecc873ea2',NULL,1,'View',10,'Text','e8807c09-9f57-11df-936f-e37ecc873ea2',0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),('55148ba3-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e1122-9f58-11df-936f-e37ecc873ea2','ModelBasisTableID','34b90692-9f56-11df-936f-e37ecc873ea2',NULL,1,'BasisTable',40,'Text','e88076f4-9f57-11df-936f-e37ecc873ea2',0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),('55148da5-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e1122-9f58-11df-936f-e37ecc873ea2','ModelResultsPerPage','34b902f6-9f56-11df-936f-e37ecc873ea2',NULL,1,'Results per page',100,'Text','e88076f4-9f57-11df-936f-e37ecc873ea2',0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),('55148fdf-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e1122-9f58-11df-936f-e37ecc873ea2','ModelParentID','34b90a28-9f56-11df-936f-e37ecc873ea2',NULL,1,'Parent',50,'Text','e88076f4-9f57-11df-936f-e37ecc873ea2',0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,10,NULL),('551491e6-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e1122-9f58-11df-936f-e37ecc873ea2','ModelReferenceID','34b90bf6-9f56-11df-936f-e37ecc873ea2',NULL,1,'Reference',60,'Text','e88076f4-9f57-11df-936f-e37ecc873ea2',0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),('551493ed-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e14d4-9f58-11df-936f-e37ecc873ea2','FieldLabel','34ded20a-9f56-11df-936f-e37ecc873ea2',NULL,1,'Label',70,'Text','e8807a52-9f57-11df-936f-e37ecc873ea2',0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),('551495ee-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e16ae-9f58-11df-936f-e37ecc873ea2','RegionLabel','34defdc5-9f56-11df-936f-e37ecc873ea2',NULL,1,'Label',10,NULL,'e8807c09-9f57-11df-936f-e37ecc873ea2',0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),('551497f5-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e16ae-9f58-11df-936f-e37ecc873ea2','RegionParent','34deff4f-9f56-11df-936f-e37ecc873ea2',NULL,1,'Parent',10,NULL,'e8807c09-9f57-11df-936f-e37ecc873ea2',0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,10,NULL),('55149a0d-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e16ae-9f58-11df-936f-e37ecc873ea2','RegionDisplayOrder','34df00b0-9f56-11df-936f-e37ecc873ea2',NULL,1,'Order',10,NULL,'e8807c09-9f57-11df-936f-e37ecc873ea2',0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),('55149c1a-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e14d4-9f58-11df-936f-e37ecc873ea2','FieldVisible','34ded064-9f56-11df-936f-e37ecc873ea2',NULL,1,'Visible',10,NULL,'e8807a52-9f57-11df-936f-e37ecc873ea2',0,1,1,0,NULL,0,'1',NULL,NULL,NULL,NULL,NULL),('55149e32-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e14d4-9f58-11df-936f-e37ecc873ea2','FieldRegion','34ded377-9f56-11df-936f-e37ecc873ea2',NULL,1,'Region',10,NULL,'e8807a52-9f57-11df-936f-e37ecc873ea2',0,1,1,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `tan_field_action` */

DROP TABLE IF EXISTS `tan_field_action`;

CREATE TABLE `tan_field_action` (
  `id` char(36) NOT NULL,
  `createdBy` int(11) unsigned DEFAULT NULL,
  `updatedBy` int(11) unsigned DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updateProcess` varchar(100) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `label` varchar(50) DEFAULT NULL,
  `fieldID` char(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tableName` (`name`,`fieldID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tan_field_action` */

insert  into `tan_field_action`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`updateProcess`,`name`,`label`,`fieldID`) values ('5ba14a0d-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'DefineTable','DefineTable','551431ba-9f56-11df-936f-e37ecc873ea2'),('5ba14e54-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'DefineTable','DefineTable','55146329-9f56-11df-936f-e37ecc873ea2');

/*Table structure for table `tan_field_action_detail` */

DROP TABLE IF EXISTS `tan_field_action_detail`;

CREATE TABLE `tan_field_action_detail` (
  `id` char(36) NOT NULL,
  `createdBy` int(11) unsigned DEFAULT NULL,
  `updatedBy` int(11) unsigned DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updateProcess` varchar(100) DEFAULT NULL,
  `fieldActionID` char(36) DEFAULT NULL,
  `fromFieldID` char(36) DEFAULT NULL,
  `toFieldID` char(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tan_field_action_detail` */

insert  into `tan_field_action_detail`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`updateProcess`,`fieldActionID`,`fromFieldID`,`toFieldID`) values ('61eae37b-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'5ba14a0d-9f56-11df-936f-e37ecc873ea2','55142ce5-9f56-11df-936f-e37ecc873ea2','5514388b-9f56-11df-936f-e37ecc873ea2'),('61eae806-9f56-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'5ba14e54-9f56-11df-936f-e37ecc873ea2','551460de-9f56-11df-936f-e37ecc873ea2','5514388b-9f56-11df-936f-e37ecc873ea2');

/*Table structure for table `tan_index` */

DROP TABLE IF EXISTS `tan_index`;

CREATE TABLE `tan_index` (
  `id` char(36) NOT NULL,
  `createdBy` int(11) unsigned DEFAULT NULL,
  `updatedBy` int(11) unsigned DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updateProcess` varchar(100) DEFAULT NULL,
  `tableID` char(36) DEFAULT NULL,
  `displayOrder` tinyint(4) unsigned NOT NULL DEFAULT '1',
  `uniqueIndex` tinyint(4) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `tableID` (`displayOrder`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tan_index` */

insert  into `tan_index`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`updateProcess`,`tableID`,`displayOrder`,`uniqueIndex`) values ('7c145395-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce920bd-9f57-11df-936f-e37ecc873ea2',1,1),('7c145675-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce924a9-9f57-11df-936f-e37ecc873ea2',1,1),('7c145815-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce92677-9f57-11df-936f-e37ecc873ea2',1,1),('7c145b2e-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce929b7-9f57-11df-936f-e37ecc873ea2',1,1),('7c145cb7-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce92fa4-9f57-11df-936f-e37ecc873ea2',1,1),('7c145e3b-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce93150-9f57-11df-936f-e37ecc873ea2',1,1),('7c145fc5-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'fce932f6-9f57-11df-936f-e37ecc873ea2',1,1);

/*Table structure for table `tan_index_column` */

DROP TABLE IF EXISTS `tan_index_column`;

CREATE TABLE `tan_index_column` (
  `id` char(36) NOT NULL,
  `createdBy` int(11) unsigned DEFAULT NULL,
  `updatedBy` int(11) unsigned DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updateProcess` varchar(100) DEFAULT NULL,
  `indexID` char(36) DEFAULT NULL,
  `columnID` char(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tan_index_column` */

insert  into `tan_index_column`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`updateProcess`,`indexID`,`columnID`) values ('65df4dba-9f32-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'7c145395-9f57-11df-936f-e37ecc873ea2','34b8e1dc-9f56-11df-936f-e37ecc873ea2'),('65df5055-9f32-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'7c145675-9f57-11df-936f-e37ecc873ea2','34b8f27a-9f56-11df-936f-e37ecc873ea2'),('65df51ea-9f32-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'7c145815-9f57-11df-936f-e37ecc873ea2','34b8fd81-9f56-11df-936f-e37ecc873ea2'),('65df54ec-9f32-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'7c145b2e-9f57-11df-936f-e37ecc873ea2','34b93ec7-9f56-11df-936f-e37ecc873ea2'),('65df566a-9f32-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'7c145cb7-9f57-11df-936f-e37ecc873ea2','34b8f9e5-9f56-11df-936f-e37ecc873ea2');

/*Table structure for table `tan_join` */

DROP TABLE IF EXISTS `tan_join`;

CREATE TABLE `tan_join` (
  `id` char(36) NOT NULL,
  `createdBy` int(11) unsigned DEFAULT NULL,
  `updatedBy` int(11) unsigned DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updateProcess` varchar(100) DEFAULT NULL,
  `joinType` char(2) NOT NULL,
  `fromTableID` char(36) DEFAULT NULL,
  `toTableID` char(36) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tan_join` */

insert  into `tan_join`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`updateProcess`,`joinType`,`fromTableID`,`toTableID`,`name`) values ('6a26db53-786f-4e76-9bd4-c0634592aba7',NULL,NULL,NULL,NULL,NULL,'0','fce93485-9f57-11df-936f-e37ecc873ea2','fce92fa4-9f57-11df-936f-e37ecc873ea2','ViewToModel'),('8db38e11-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'0','fce924a9-9f57-11df-936f-e37ecc873ea2','fce920bd-9f57-11df-936f-e37ecc873ea2','Table'),('8db390c3-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'0','fce92677-9f57-11df-936f-e37ecc873ea2','fce920bd-9f57-11df-936f-e37ecc873ea2','FromTable'),('8db39414-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'0','fce929b7-9f57-11df-936f-e37ecc873ea2','fce92fa4-9f57-11df-936f-e37ecc873ea2','View'),('8db395af-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'0','fce92677-9f57-11df-936f-e37ecc873ea2','fce920bd-9f57-11df-936f-e37ecc873ea2','ToTable'),('8db39744-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'0','fce93af0-9f57-11df-936f-e37ecc873ea2','fce92677-9f57-11df-936f-e37ecc873ea2','Join'),('8db398d3-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'0','fce929b7-9f57-11df-936f-e37ecc873ea2','fce924a9-9f57-11df-936f-e37ecc873ea2','BasisColumn'),('8db39bfd-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'0','fce93485-9f57-11df-936f-e37ecc873ea2','fce92fa4-9f57-11df-936f-e37ecc873ea2','View');

/*Table structure for table `tan_join_column` */

DROP TABLE IF EXISTS `tan_join_column`;

CREATE TABLE `tan_join_column` (
  `id` char(36) NOT NULL,
  `createdBy` int(11) unsigned DEFAULT NULL,
  `updatedBy` int(11) unsigned DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updateProcess` varchar(100) DEFAULT NULL,
  `joinID` char(36) DEFAULT NULL,
  `fromColumnID` char(36) DEFAULT NULL,
  `fromText` varchar(50) DEFAULT NULL,
  `toColumnID` char(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tan_join_column` */

insert  into `tan_join_column`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`updateProcess`,`joinID`,`fromColumnID`,`fromText`,`toColumnID`) values ('8220be6d-9f31-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'8db38e11-9f57-11df-936f-e37ecc873ea2','34b8f465-9f56-11df-936f-e37ecc873ea2',NULL,'34b8e1dc-9f56-11df-936f-e37ecc873ea2'),('8220bfeb-9f31-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'8db390c3-9f57-11df-936f-e37ecc873ea2','34b8ff5a-9f56-11df-936f-e37ecc873ea2',NULL,'34b8e1dc-9f56-11df-936f-e37ecc873ea2'),('8220c0e0-9f31-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'8db3927f-9f57-11df-936f-e37ecc873ea2','34b8fbb3-9f56-11df-936f-e37ecc873ea2',NULL,'34b8eab4-9f56-11df-936f-e37ecc873ea2'),('8220c180-9f31-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'8db39414-9f57-11df-936f-e37ecc873ea2','34deca1c-9f56-11df-936f-e37ecc873ea2',NULL,'34b8f9e5-9f56-11df-936f-e37ecc873ea2'),('8220c214-9f31-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'8db395af-9f57-11df-936f-e37ecc873ea2','34b90128-9f56-11df-936f-e37ecc873ea2',NULL,'34b8e1dc-9f56-11df-936f-e37ecc873ea2'),('8220c2a9-9f31-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'8db39a68-9f57-11df-936f-e37ecc873ea2','34deed6b-9f56-11df-936f-e37ecc873ea2',NULL,'34b8eab4-9f56-11df-936f-e37ecc873ea2'),('8220c33d-9f31-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'8db39bfd-9f57-11df-936f-e37ecc873ea2','34df0223-9f56-11df-936f-e37ecc873ea2',NULL,'34b8f9e5-9f56-11df-936f-e37ecc873ea2');

/*Table structure for table `tan_menu` */

DROP TABLE IF EXISTS `tan_menu`;

CREATE TABLE `tan_menu` (
  `id` char(36) NOT NULL,
  `createdBy` int(11) unsigned DEFAULT NULL,
  `updatedBy` int(11) unsigned DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updateProcess` varchar(100) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `label` varchar(50) DEFAULT NULL,
  `databaseID` char(36) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tan_menu` */

/*Table structure for table `tan_model` */

DROP TABLE IF EXISTS `tan_model`;

CREATE TABLE `tan_model` (
  `id` char(36) NOT NULL,
  `createdBy` int(11) unsigned DEFAULT NULL,
  `updatedBy` int(11) unsigned DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updateProcess` varchar(100) DEFAULT NULL,
  `resultsPerPage` smallint(6) NOT NULL DEFAULT '0',
  `basisTableID` char(36) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `parentID` char(36) DEFAULT NULL,
  `referenceID` char(36) DEFAULT NULL,
  `queryOrder` smallint(6) NOT NULL DEFAULT '0',
  `label` varchar(100) DEFAULT NULL,
  `allowAdd` tinyint(3) unsigned NOT NULL DEFAULT '1',
  `allowEdit` tinyint(3) unsigned NOT NULL DEFAULT '1',
  `allowDelete` tinyint(3) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pageName` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tan_model` */

insert  into `tan_model`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`updateProcess`,`resultsPerPage`,`basisTableID`,`name`,`parentID`,`referenceID`,`queryOrder`,`label`,`allowAdd`,`allowEdit`,`allowDelete`) values ('095e0687-9f58-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,100,'fce920bd-9f57-11df-936f-e37ecc873ea2','ManageTables',NULL,NULL,0,'Manage Tables',1,1,0),('095e0978-9f58-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,10,'fce920bd-9f57-11df-936f-e37ecc873ea2','DefineTable',NULL,NULL,0,'Define Table',1,1,1),('095e0b79-9f58-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,100,'fce92fa4-9f57-11df-936f-e37ecc873ea2','WebpageList',NULL,NULL,0,'List Webpages',1,0,0),('095e0f48-9f58-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,0,'fce924a9-9f57-11df-936f-e37ecc873ea2','Column','095e0978-9f58-11df-936f-e37ecc873ea2','e325875d-9f57-11df-936f-e37ecc873ea2',0,NULL,1,1,1),('095e1122-9f58-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,0,'fce92fa4-9f57-11df-936f-e37ecc873ea2','DefineWebpage',NULL,NULL,0,'Define Webpage',1,1,1),('095e12fb-9f58-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,0,'fce92677-9f57-11df-936f-e37ecc873ea2','JoinParent','095e0978-9f58-11df-936f-e37ecc873ea2','e3258cbc-9f57-11df-936f-e37ecc873ea2',0,NULL,1,1,1),('095e14d4-9f58-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,0,'fce929b7-9f57-11df-936f-e37ecc873ea2','Field','095e1122-9f58-11df-936f-e37ecc873ea2','e3258e29-9f57-11df-936f-e37ecc873ea2',0,NULL,1,1,1),('095e16ae-9f58-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,0,'fce93485-9f57-11df-936f-e37ecc873ea2','Views','095e1122-9f58-11df-936f-e37ecc873ea2','e3258f9b-9f57-11df-936f-e37ecc873ea2',0,NULL,1,1,1),('123',NULL,NULL,NULL,NULL,NULL,0,'fce937aa-9f57-11df-936f-e37ecc873ea2','Indexes',NULL,NULL,0,NULL,1,1,1);

/*Table structure for table `tan_project` */

DROP TABLE IF EXISTS `tan_project`;

CREATE TABLE `tan_project` (
  `id` char(36) NOT NULL,
  `createdBy` int(11) unsigned DEFAULT NULL,
  `updatedBy` int(11) unsigned DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updateProcess` varchar(100) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `userTableID` char(36) DEFAULT NULL,
  `usernameColumnID` char(36) DEFAULT NULL,
  `passwordColumnID` char(36) DEFAULT NULL,
  `projectCode` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tan_project` */

insert  into `tan_project`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`updateProcess`,`name`,`userTableID`,`usernameColumnID`,`passwordColumnID`,`projectCode`) values ('d91b225e-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'Tantalum Builder',NULL,NULL,NULL,'TB'),('d91b2527-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'Northwind Demo',NULL,NULL,NULL,'NW');

/*Table structure for table `tan_reference` */

DROP TABLE IF EXISTS `tan_reference`;

CREATE TABLE `tan_reference` (
  `id` char(36) NOT NULL,
  `createdBy` int(11) unsigned DEFAULT NULL,
  `updatedBy` int(11) unsigned DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updateProcess` varchar(100) DEFAULT NULL,
  `modelID` char(36) NOT NULL,
  `joinID` char(36) NOT NULL,
  `parentID` char(36) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tan_reference` */

insert  into `tan_reference`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`updateProcess`,`modelID`,`joinID`,`parentID`,`name`) values ('e325875d-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e0f48-9f58-11df-936f-e37ecc873ea2','8db38e11-9f57-11df-936f-e37ecc873ea2',NULL,'ColumnToTable'),('e3258b49-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e12fb-9f58-11df-936f-e37ecc873ea2','8db395af-9f57-11df-936f-e37ecc873ea2',NULL,'JoinToParentTable'),('e3258cbc-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e12fb-9f58-11df-936f-e37ecc873ea2','8db390c3-9f57-11df-936f-e37ecc873ea2',NULL,'JoinToChildTable'),('e3258e29-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e14d4-9f58-11df-936f-e37ecc873ea2','8db39414-9f57-11df-936f-e37ecc873ea2',NULL,'FieldToModel'),('e3258f9b-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'095e16ae-9f58-11df-936f-e37ecc873ea2','8db39bfd-9f57-11df-936f-e37ecc873ea2',NULL,'ViewToModel');

/*Table structure for table `tan_table` */

DROP TABLE IF EXISTS `tan_table`;

CREATE TABLE `tan_table` (
  `id` char(36) NOT NULL,
  `createdBy` int(11) unsigned DEFAULT NULL,
  `updatedBy` int(11) unsigned DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updateProcess` varchar(100) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `dbName` varchar(50) NOT NULL,
  `projectID` char(36) DEFAULT NULL,
  `databaseID` char(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`,`projectID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tan_table` */

insert  into `tan_table`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`updateProcess`,`name`,`dbName`,`projectID`,`databaseID`) values ('fce920bd-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'Table','tan_table',NULL,NULL),('fce924a9-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'Column','tan_column',NULL,NULL),('fce92677-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'Join','tan_join',NULL,NULL),('fce929b7-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'Field','tan_field',NULL,NULL),('fce92fa4-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'Model','tan_model',NULL,NULL),('fce93150-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'Enum','tan_enum',NULL,NULL),('fce932f6-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'User','tan_user',NULL,NULL),('fce93485-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'View','tan_view',NULL,NULL),('fce93615-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'Reference','tan_reference',NULL,NULL),('fce937aa-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'Index','tan_index',NULL,NULL),('fce9394a-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'IndexColumn','tan_index_column',NULL,NULL),('fce93af0-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'JoinColumn','tan_join_column',NULL,NULL);

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
  `id` char(36) NOT NULL,
  `createdBy` int(11) unsigned DEFAULT NULL,
  `updatedBy` int(11) unsigned DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updateProcess` varchar(100) DEFAULT NULL,
  `viewType` varchar(50) DEFAULT NULL,
  `modelID` char(36) DEFAULT NULL,
  `parentID` char(36) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `label` varchar(100) DEFAULT NULL,
  `displayOrder` int(11) DEFAULT '10',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tan_view` */

insert  into `tan_view`(`id`,`createdBy`,`updatedBy`,`creationDate`,`updateDate`,`updateProcess`,`viewType`,`modelID`,`parentID`,`name`,`label`,`displayOrder`) values ('e88065f5-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'BasicTable','095e0687-9f58-11df-936f-e37ecc873ea2',NULL,'Tables','Tables1',1),('e88068d5-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'FormRegion','095e0978-9f58-11df-936f-e37ecc873ea2',NULL,'Tables','Table',10),('e8806a9d-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'BasicTable','095e0b79-9f58-11df-936f-e37ecc873ea2',NULL,'Page','Page',10),('e8806c5f-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'FormRegion','095e0b79-9f58-11df-936f-e37ecc873ea2',NULL,'Page','Page',10),('e8806e17-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'BasicTable','095e0f48-9f58-11df-936f-e37ecc873ea2','e880753d-9f57-11df-936f-e37ecc873ea2','Columns','Columns',10),('e8806fce-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'BasicTable','095e12fb-9f58-11df-936f-e37ecc873ea2','e880753d-9f57-11df-936f-e37ecc873ea2','Join','Join to parents',10),('e880737b-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'VerticalContainer',NULL,NULL,'ManageTables','ManageTables',10),('e880753d-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'HorizontalContainer',NULL,'e88068d5-9f57-11df-936f-e37ecc873ea2','JoinColumnContainer','Table Detail',20),('e88076f4-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'FormRegion','095e1122-9f58-11df-936f-e37ecc873ea2','e880789a-9f57-11df-936f-e37ecc873ea2','Models','Model',10),('e880789a-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'HorizontalContainer',NULL,NULL,'Top','Top',10),('e8807a52-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'BasicTable','095e14d4-9f58-11df-936f-e37ecc873ea2','e88076f4-9f57-11df-936f-e37ecc873ea2','Fields','Fields',20),('e8807c09-9f57-11df-936f-e37ecc873ea2',NULL,NULL,NULL,NULL,NULL,'BasicTable','095e16ae-9f58-11df-936f-e37ecc873ea2','e88076f4-9f57-11df-936f-e37ecc873ea2','Views','Views',30);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
