CREATE DATABASE `group2`;

use `group2;

CREATE TABLE  `users` (  
   `id` int(11) AUTO_INCREMENT COMMENT '主键Id',
   `username`   varchar(20) NOT NULL  COMMENT '用户名',  
   `password`   varchar(255) NOT NULL COMMENT '密码',
   `nickname`   varchar(20) NOT NULL DEFAULT '师大彭于晏' COMMENT '昵称',  
   `head_image` varchar(255)NOT NULL DEFAULT 'default.jpg' COMMENT '头像地址',
   `phone`  varchar(20)  COMMENT '手机号',  
   `email` varchar(20)  COMMENT '邮箱',
   `reg_time`  datetime NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
   `update_time`  datetime NOT NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
   PRIMARY KEY (`id`),
   UNIQUE KEY `sku_users_username` (`username`) USING BTREE,
   UNIQUE KEY `sku_users_phone` (`phone`) USING BTREE,
   UNIQUE KEY `sku_users_email` (`email`) USING BTREE
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=REDUNDANT COMMENT='用户表';

CREATE TABLE  `group` (  
   `id` int(11) AUTO_INCREMENT COMMENT '主键Id',
   `name`   varchar(20) NOT NULL  COMMENT '群组名',  
   `founder_id`   int(11) NOT NULL  COMMENT '创始人Id',  
   `code`  varchar(20)COMMENT '邀请码',  
   `data`   blob  COMMENT '群组数据',
   `reg_time`  datetime NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   `update_time`  datetime NOT NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
   PRIMARY KEY (`id`),
   CONSTRAINT `fk_emp_group_founderid` FOREIGN KEY(`founder_id`) REFERENCES `users`(`id`) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=REDUNDANT COMMENT='群组表';


CREATE TABLE  `share` (  
   `id` int(11) AUTO_INCREMENT COMMENT '主键Id',
   `share_file_name`   varchar(255) NOT NULL  COMMENT '分享文件名',  
   `share_user_id`   int(11) NOT NULL  COMMENT '分享用户Id',  
   `code` varchar(20) COMMENT '分享码',
   `reg_time`  datetime NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '分享时间',
   `overdue_time`  datetime NOT NULL  DEFAULT CURRENT_TIMESTAMP  COMMENT '过期时间',
   PRIMARY KEY (`id`),
   CONSTRAINT `fk_emp_share_share_user_id` FOREIGN KEY(`share_user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=REDUNDANT COMMENT='分享表';


CREATE TABLE  `recyclebin` (  
   `id` int(11) AUTO_INCREMENT COMMENT '主键Id',
   `delete_file_name`   varchar(255) NOT NULL DEFAULT ''  COMMENT '删除文件名',  
   `original_address`   varchar(255) NOT NULL  COMMENT '原始路径',  
   `size`     bigint   NOT NULL  DEFAULT 0 COMMENT '文件大小',
   `delete_user_id`   int(11) NOT NULL  COMMENT '删除用户Id',  
   `delete_time`  datetime NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '删除时间',
   PRIMARY KEY (`id`),
   CONSTRAINT `fk_emp_recyclebin_delete_user_id` FOREIGN KEY(`delete_user_id`) REFERENCES `users`(`id`)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=REDUNDANT COMMENT='回收站';

CREATE TABLE  `sign` (  
   `id` int(11) AUTO_INCREMENT COMMENT '主键Id',
   `initiate_group_id`   int(11) NOT NULL COMMENT '发起群组Id',  
   `data`   blob  COMMENT '签到数据',
   `start_time`  datetime NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
   `end_time`   datetime NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
   `update_time`  datetime NOT NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
   PRIMARY KEY (`id`),
   CONSTRAINT `fk_emp_sign_initiate_group_id` FOREIGN KEY(`initiate_group_id`) REFERENCES `group`(`id`) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=REDUNDANT COMMENT='签到记录';