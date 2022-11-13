-- 文件存储表结构
CREATE TABLE `file_store`
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
    `created_by`     bigint                           DEFAULT NULL,
    `updated_by`    bigint                           DEFAULT NULL,
    `updated_time`  datetime                         DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='文件存储表';
