/*用户账号信息*/
CREATE TABLE `user_info` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) NOT NULL,
  `user_pwd` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`user_name`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gbk;

insert into `user_info` (`id`, `user_name`, `user_pwd`) values('2','admin','bd994c1927adad825bd23984fb6c0dde'); #qctest
insert into `user_info` (`id`, `user_name`, `user_pwd`) values('1','release','123fead50246387983ee340507115ef4');

/*角色表*/
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(64) NOT NULL COMMENT '角色名称',
  `role_eng_name` varchar(64) NOT NULL COMMENT '角色英文名称',
  `role_type` smallint(6) NOT NULL COMMENT '角色类型，0系统角色(不可修改)1自定义角色',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gbk COMMENT='角色表';

insert into `role` (`role_id`, `role_name`, `role_eng_name`, `role_type`) values('1','管理员','admin','1');
insert into `role` (`role_id`, `role_name`, `role_eng_name`, `role_type`) values('2','运维人员','operation','2');

/*用户角色关联表*/
CREATE TABLE `user_role_rela` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '权限id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='用户角色关系表';

insert into `user_role_rela` (`user_id`, `role_id`) values('1','2');
insert into `user_role_rela` (`user_id`, `role_id`) values('2','1');

/*权限表*/
CREATE TABLE `permission` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `permission_name` varchar(64) NOT NULL COMMENT '权限名称',
  `permission_eng_name` varchar(64) NOT NULL COMMENT '权限英文名称',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=gbk COMMENT='权限表';

insert into `permission` (`permission_id`, `permission_name`, `permission_eng_name`) values('1','上传','upload');
insert into `permission` (`permission_id`, `permission_name`, `permission_eng_name`) values('2','下载','download');
insert into `permission` (`permission_id`, `permission_name`, `permission_eng_name`) values('3','添加','add');
insert into `permission` (`permission_id`, `permission_name`, `permission_eng_name`) values('4','删除','delete');
insert into `permission` (`permission_id`, `permission_name`, `permission_eng_name`) values('5','修改','update');

/*角色权限关联表*/
CREATE TABLE `role_permission_rela` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `permission_id` int(11) NOT NULL COMMENT '权限id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=gbk COMMENT='权限角色关系表';

insert into `role_permission_rela` (`id`, `permission_id`, `role_id`) values('1','2','2');
insert into `role_permission_rela` (`id`, `permission_id`, `role_id`) values('2','1','1');
insert into `role_permission_rela` (`id`, `permission_id`, `role_id`) values('3','2','1');
insert into `role_permission_rela` (`id`, `permission_id`, `role_id`) values('4','3','1');
insert into `role_permission_rela` (`id`, `permission_id`, `role_id`) values('5','4','1');
insert into `role_permission_rela` (`id`, `permission_id`, `role_id`) values('6','5','1');

/*菜单表*/
CREATE TABLE `menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_name` varchar(64) NOT NULL COMMENT '菜单名称',
  `parent_id` int(11) NOT NULL COMMENT '父id',
  `menu_url` varchar(64) NOT NULL COMMENT '除了前缀的地址 例: /gls/glsAccountActin.do?reqCode=query',
  `menu_order` int(11) NOT NULL COMMENT '菜单排序',
  `menu_host` varchar(64) NOT NULL COMMENT 'url的前缀 例: http://www.baidu.com:8080 目前无用',
  `menu_class` varchar(64) DEFAULT NULL COMMENT '菜单样式图标',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=gbk COMMENT='菜单表';

insert into `menu` (`menu_id`, `menu_name`, `parent_id`, `menu_url`, `menu_order`, `menu_host`, `menu_class`) values('1','基础信息管理','0','/','1','http://127.0.0.1/octupus','icon-home');
insert into `menu` (`menu_id`, `menu_name`, `parent_id`, `menu_url`, `menu_order`, `menu_host`, `menu_class`) values('2','需求管理','0','/','2','http://127.0.0.1/octupus','fa fa-sitemap');
insert into `menu` (`menu_id`, `menu_name`, `parent_id`, `menu_url`, `menu_order`, `menu_host`, `menu_class`) values('3','平台维护','1','platformAdd','1','http://127.0.0.1/octupus','fa fa-cog');
insert into `menu` (`menu_id`, `menu_name`, `parent_id`, `menu_url`, `menu_order`, `menu_host`, `menu_class`) values('4','模块维护','1','artifactAdd','2','http://127.0.0.1/octupus','fa fa-wrench');
insert into `menu` (`menu_id`, `menu_name`, `parent_id`, `menu_url`, `menu_order`, `menu_host`, `menu_class`) values('5','需求增加','2','requirementAdd','1','http://127.0.0.1/octupus','fa fa-plus-circle');
insert into `menu` (`menu_id`, `menu_name`, `parent_id`, `menu_url`, `menu_order`, `menu_host`, `menu_class`) values('6','需求查询','2','requirementLookup','2','http://127.0.0.1/octupus','fa fa-newspaper-o');
insert into `menu` (`menu_id`, `menu_name`, `parent_id`, `menu_url`, `menu_order`, `menu_host`, `menu_class`) values('7','模块查询','2','fileInfoLookup','3','http://127.0.0.1/octupus','icon-bulb');
insert into `menu` (`menu_id`, `menu_name`, `parent_id`, `menu_url`, `menu_order`, `menu_host`, `menu_class`) values('8','模块类型','1','artifactTypeAdd','3','http://127.0.0.1/octupus','fa fa-life-ring');

/*角色菜单关联表*/
CREATE TABLE `role_menu_rela` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `menu_id` int(11) NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=gbk COMMENT='角色菜单关系表';

insert into `role_menu_rela` (`id`, `role_id`, `menu_id`) values('1','1','1');
insert into `role_menu_rela` (`id`, `role_id`, `menu_id`) values('2','1','2');
insert into `role_menu_rela` (`id`, `role_id`, `menu_id`) values('3','1','3');
insert into `role_menu_rela` (`id`, `role_id`, `menu_id`) values('4','1','4');
insert into `role_menu_rela` (`id`, `role_id`, `menu_id`) values('5','1','5');
insert into `role_menu_rela` (`id`, `role_id`, `menu_id`) values('6','1','6');
insert into `role_menu_rela` (`id`, `role_id`, `menu_id`) values('7','1','7');
insert into `role_menu_rela` (`id`, `role_id`, `menu_id`) values('8','2','2');
insert into `role_menu_rela` (`id`, `role_id`, `menu_id`) values('9','2','6');
insert into `role_menu_rela` (`id`, `role_id`, `menu_id`) values('10','2','7');
insert into `role_menu_rela` (`id`, `role_id`, `menu_id`) values('11','1','8');

