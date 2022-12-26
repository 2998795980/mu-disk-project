create database if not exists mu_disk character set utf8mb4;

-- 文件存储表结构
CREATE TABLE mu_disk.`file_store`
(
    `id`            bigint                           NOT NULL AUTO_INCREMENT,
    `file_name`     varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '文件名',
    `file_size`     varchar(64) COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '文件大小',
    `file_location` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '真实文件路径',
    `sort`          int                              DEFAULT NULL COMMENT '排序',
    `parent_id`     varchar(45) COLLATE utf8mb4_bin  NOT NULL COMMENT '父id',
    `file_type`     tinyint                          NOT NULL COMMENT '文件类型 1-文件夹 ,2-文件',
    `state`         tinyint                          NOT NULL COMMENT '1、正常启用 2、移入回收站 0、逻辑删除',
    `created_time`  datetime                         DEFAULT NULL,
    `created_by`    bigint                           DEFAULT NULL,
    `updated_by`    bigint                           DEFAULT NULL,
    `updated_time`  datetime                         DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='文件存储表';

-- 账户表结构
create table mu_disk.`user`
(
    `id`           int                 NOT NULL AUTO_INCREMENT,
    `username`     varchar(64)         NOT NULL COMMENT '用户名',
    `account`      varchar(64) UNICODE NOT NULL COMMENT '账号',
    `password`     varchar(64)         NOT NULL COMMENT '密码',
    `salt`         varchar(64)         NOT NULL COMMENT '盐 用来拼接密码加密',
    `phone`        varchar(11)         NULL COMMENT '手机号',
    `state`        bigint              NOT NULL COMMENT '用户状态 -1:删除 0：封禁 1：正常',
    `created_time` datetime DEFAULT NULL,
    `created_by`   bigint   DEFAULT NULL,
    `updated_by`   bigint   DEFAULT NULL,
    `updated_time` datetime DEFAULT NULL,
    PRIMARY KEY (`id`)
);

insert into mu_disk.`user`
values (null, 'root', 'root', 'b92f25fc103ea86674f0f7175911fde2', 'root', '13333333333', 1, null, null, null, null);

-- 角色表结构
create table mu_disk.`role`
(
    `id`       int         NOT NULL AUTO_INCREMENT,
    `name`     varchar(64) NOT NULL COMMENT '角色名',
    `describe` varchar(64) NOT NULL COMMENT '详细说明',
    PRIMARY KEY (`id`)
);

insert into mu_disk.`role`
values (null, 'user', '普通用户 需要登录'),
       (null, 'admin', '管理用户 需要登录'),
       (null, 'visitor', '访客 不需要登录');

-- 用户&角色表结构
create table mu_disk.`user_role`
(
    `id`      int NOT NULL AUTO_INCREMENT,
    `user_id` int NOT NULL COMMENT '用户编号',
    `role_id` int NOT NULL COMMENT '角色编号',
    PRIMARY KEY (`id`)
);

insert into mu_disk.`user_role`
values (null, 1, 1);

-- 权限表结构
create table mu_disk.`permission`
(
    `id`         int         NOT NULL AUTO_INCREMENT,
    `permission` varchar(64) NOT NULL COMMENT '权限',
    `describe`   varchar(64) NOT NULL COMMENT '详细说明',
    PRIMARY KEY (`id`)
);

insert into mu_disk.`permission`
values (null, 'user:update', '用户信息更新'),
       (null, 'user:delete', '删除用户'),
       (null, 'user:update', '用户信息更新');

-- 角色-权限表结构
create table mu_disk.`role_permission`
(
    `id`            int NOT NULL AUTO_INCREMENT,
    `role_id`       int NOT NULL COMMENT '角色编号',
    `permission_id` int NOT NULL COMMENT '权限编号',
    PRIMARY KEY (`id`)
);

insert into mu_disk.`role_permission`
values (null, 1, 1),
       (null, 1, 2),
       (null, 1, 3);


-- 个人信息表
create table mu_disk.`person`
(
    `id`           int         NOT NULL AUTO_INCREMENT,
    `account`      varchar(64) NOT NULL UNIQUE COMMENT '账户',
    `username`     varchar(64) NOT NULL COMMENT '用户名',
    `email`        varchar(64) NULL COMMENT '邮箱',
    `phone`        varchar(11) NULL COMMENT '手机号',
    `sex`          int         NULL DEFAULT 0 COMMENT '性别 0：未知，1：男，2：女',
    `pictrue`      varchar(64)      DEFAULT '/picture/default.png' NULL COMMENT '头像 保存在磁盘',
    `created_time` datetime         DEFAULT NULL,
    `created_by`   bigint           DEFAULT NULL,
    `updated_by`   bigint           DEFAULT NULL,
    `updated_time` datetime         DEFAULT NULL,
    PRIMARY KEY (`id`)
);

insert into mu_disk.`person`
values (null, 'root', 'root', 'root@qq.com', '13333333333', 1, null, null, null, null, null);