-- 创建文件存储表
CREATE TABLE `file_store`
(
    `id`            BIGINT       NOT NULL,
    `file_name`     VARCHAR(255) NOT NULL COMMENT '文件名',
    `file_size`     VARCHAR(64) NULL COMMENT '文件大小',
    `file_location` VARCHAR(255) NOT NULL COMMENT '真实文件路径',
    `parent_id`     VARCHAR(45)  NOT NULL COMMENT '父id',
    `state`         TINYINT      NOT NULL COMMENT '1、正常启用 2、移入回收站 0、逻辑删除',
    `created_time`  DATETIME NULL,
    `creatd_by`     BIGINT NULL,
    `updated_by`    BIGINT NULL,
    `updated_time`  DATETIME NULL,
    PRIMARY KEY (`id`)
) COMMENT = '文件存储表';
