CREATE TABLE `goods_info` (
                              `id` bigint(20) NOT NULL COMMENT '商品主键id',
                              `name` varchar(255) DEFAULT NULL COMMENT '商品名称',
                              `desc` varchar(255) DEFAULT NULL COMMENT '商品描述',
                              `color` varchar(255) DEFAULT NULL COMMENT '商品颜色',
                              `size` varchar(255) DEFAULT NULL COMMENT '商品尺寸',
                              `tag` varchar(255) DEFAULT NULL COMMENT '商品标签',
                              `share_url` varchar(255) DEFAULT NULL COMMENT '分享链接',
                              `images` varchar(255) DEFAULT NULL COMMENT '商品图片',
                              `price` decimal(20,2) DEFAULT NULL COMMENT '商品价格',
                              `sales` bigint(20) DEFAULT NULL COMMENT '商品销量',
                              `is_recommended` tinyint(4) DEFAULT NULL COMMENT '是否推荐',
                              `guarantee` varchar(255) DEFAULT NULL COMMENT '保障',
                              `streamer` varchar(255) DEFAULT NULL COMMENT '飘带',
                              `inventory_count` bigint(20) DEFAULT NULL COMMENT '库存数量',
                              `status` tinyint(4) DEFAULT NULL COMMENT '商品状态，0--正常，1--下架',
                              `is_deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除，0--否，1--是',
                              `created_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                              `updated_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='商品详细信息';

CREATE TABLE `inventory_detail` (
                                    `id` bigint(20) NOT NULL COMMENT '主键id',
                                    `goods_id` bigint(20) DEFAULT NULL COMMENT '商品id',
                                    `goods_count` bigint(20) DEFAULT NULL COMMENT '商品数量',
                                    `operator_person_id` bigint(20) DEFAULT NULL COMMENT '操作人员id',
                                    `is_deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除，0--否，1--是',
                                    `created_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                    `updated_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='货物库存信息';

CREATE TABLE `order` (
                         `id` bigint(20) NOT NULL COMMENT '主键id',
                         `order_id` bigint(20) NOT NULL COMMENT '订单id',
                         `goods_id` bigint(20) NOT NULL COMMENT '商品id',
                         `goods_count` bigint(20) NOT NULL COMMENT '商品数量',
                         `is_deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除，0--否，1--是',
                         `created_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                         `updated_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='订单表';

CREATE TABLE `order_detail` (
                                `id` bigint(20) NOT NULL COMMENT '主键id',
                                `pay_status` tinyint(4) DEFAULT NULL COMMENT '支付状态，0--待支付，1--已支付',
                                `order_status` tinyint(4) DEFAULT NULL COMMENT '订单状态',
                                `total_amount` decimal(20,2) DEFAULT NULL COMMENT '订单总价格',
                                `discount_amount` decimal(20,2) DEFAULT NULL COMMENT '折扣后的价格',
                                `reserve_amount` decimal(20,2) DEFAULT NULL COMMENT '定金',
                                `goods_count` bigint(20) DEFAULT NULL COMMENT '商品数量',
                                `is_deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除，0--否，1--是',
                                `created_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                `updated_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='订单详细信息';

CREATE TABLE `role_permission_relation` (
                                            `relation_id` bigint(20) NOT NULL COMMENT '主键id',
                                            `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
                                            `permission_id` bigint(20) DEFAULT NULL COMMENT '权限id',
                                            `is_deleted` tinyint(20) DEFAULT '0' COMMENT '是否删除，0--否，1--是',
                                            `created_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                            `updated_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                            PRIMARY KEY (`relation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_permission` (
                                   `permission_id` bigint(20) NOT NULL COMMENT '主键id',
                                   `permission` tinyint(255) DEFAULT NULL COMMENT '权限值',
                                   `name` varchar(255) DEFAULT NULL COMMENT '权限名称',
                                   `desc` varchar(255) DEFAULT NULL COMMENT '描述',
                                   `url` varchar(255) DEFAULT NULL COMMENT '前端地址',
                                   `type` tinyint(4) DEFAULT NULL COMMENT '类型，0--目录，1--菜单，2--按钮',
                                   `is_deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除',
                                   `created_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                   `updated_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                   PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_role` (
                             `role_id` bigint(20) NOT NULL COMMENT '角色主键',
                             `role` tinyint(4) DEFAULT NULL COMMENT '角色，0--普通用户，1--管理员',
                             `is_deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除，0--否，1--是',
                             `created_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                             `updated_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_role_relation` (
                                      `id` bigint(20) NOT NULL COMMENT '关联id',
                                      `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
                                      `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
                                      `is_deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除，0--否，1--是',
                                      `created_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                      `updated_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                      PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `users` (
                         `user_id` bigint(20) NOT NULL COMMENT '用户id',
                         `username` varchar(255) NOT NULL COMMENT '用户名称',
                         `password` varchar(255) NOT NULL COMMENT '用户密码',
                         `sex` tinyint(4) DEFAULT NULL COMMENT '性别，1--男，2--女',
                         `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
                         `mail` varchar(255) DEFAULT NULL COMMENT '邮箱',
                         `role` tinyint(4) DEFAULT NULL COMMENT '角色',
                         `status` tinyint(4) DEFAULT '0' COMMENT '账户状态，0--正常，1--锁定，2--禁用',
                         `is_deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除，0--否，1--是',
                         `created_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                         `updated_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                         PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `token_record` (
                                `record_id` bigint(20) NOT NULL,
                                `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
                                `token` varchar(1024) DEFAULT NULL COMMENT 'token',
                                `status` tinyint(4) DEFAULT '0' COMMENT 'token状态，0--正常，1--失效',
                                `created_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                                `updated_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;