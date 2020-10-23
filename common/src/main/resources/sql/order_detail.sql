CREATE TABLE `order_detail` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `pay_status` tinyint(4) DEFAULT NULL COMMENT '付款状态（0--未付款，1--已付款）',
  `delivery_status` tinyint(4) DEFAULT NULL COMMENT '物流状态（0--未发货，1--已发货）',
  `order_status` tinyint(4) DEFAULT NULL COMMENT '订单状态（0--未完成，1--已完成）',
  `total_amount` decimal(20,2) DEFAULT NULL COMMENT '订单总金额',
  `discount_amount` decimal(20,2) DEFAULT NULL COMMENT '优惠金额',
  `reserve_amount` decimal(20,2) DEFAULT NULL COMMENT '定金',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;