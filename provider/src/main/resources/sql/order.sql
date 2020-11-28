CREATE TABLE `order`
(
    `id`          bigint(20) NOT NULL COMMENT '主键id',
    `order_id`    bigint(20) NOT NULL COMMENT '订单id',
    `goods_id`    bigint(20) NOT NULL COMMENT '商品id',
    `goods_count` bigint(20) NOT NULL COMMENT '商品数量',
    `create_time` timestamp  NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` timestamp  NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = latin1 COMMENT ='订单表';