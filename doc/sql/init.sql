--DROP TABLE IF EXISTS `snapshot`;
--CREATE TABLE `snapshot` (
--    `id` int  NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
--    `snapshot_date` DATE NOT NULL COMMENT '快照日期',
--    `symbol` VARCHAR (16) NOT NULL DEFAULT '' COMMENT '交易对',
--    `file_name` VARCHAR(255) NOT NULL COMMENT '快照文件名称',
--    `url` VARCHAR(512) NOT NULL COMMENT '快照文件地址',
--    `count` BIGINT NOT NULL COMMENT '快照中交易数量',
--    `max_request_seq` BIGINT NOT NULL COMMENT '最大的请求序号',
--    `max_event_seq` BIGINT NOT NULL DEFAULT 0 COMMENT '最大的产生事件序号',
--    `max_match_seq` BIGINT NOT NULL DEFAULT 0 COMMENT '最大的成交记录序号',
--    `md5sum` CHAR(32) NOT NULL COMMENT 'md5',
--    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
--     PRIMARY KEY (`id`),
--     UNIQUE KEY `uniq_snapshot_date_symbol`(`snapshot_date`, `symbol`)
--) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='事件快照表';

--    `update_time` TIMESTAMP DEFAULT NULL COMMENT '更新时间',


DROP TABLE IF EXISTS `car`;

CREATE TABLE `car` (
    `id` int  NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
    `car_model` VARCHAR (256) NOT NULL DEFAULT '' COMMENT 'car model',
    `car_stock` int NOT NULL COMMENT 'car stock number',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
     PRIMARY KEY (`id`),
     UNIQUE KEY `car_model`(`car_model`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='car info';


insert into car(id, car_model, car_stock) values (1, 'Toyota Camey', 100);
insert into car(id, car_model, car_stock) values (2, 'BMW 650', 200);
insert into car(id, car_model, car_stock) values (3, 'Benz GLC 300', 500);
insert into car(id, car_model, car_stock) values (4, 'BMW 3', 300);
insert into car(id, car_model, car_stock) values (5, 'Benz GLC 260', 400);
insert into car(id, car_model, car_stock) values (6, 'BMW x5', 250);
insert into car(id, car_model, car_stock) values (7, 'Audi A8', 600);



