-- 在MySQL中创建数据库与表
CREATE DATABASE  IF NOT EXISTS `sky_take_out` ;

CREATE TABLE `address_book` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id` bigint NOT NULL COMMENT '用户id',
    `consignee` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '收货人',
    `sex` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '性别',
    `phone` varchar(11) COLLATE utf8_bin NOT NULL COMMENT '手机号',
    `province_code` varchar(12) CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '省级区划编号',
    `province_name` varchar(32) CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '省级名称',
    `city_code` varchar(12) CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '市级区划编号',
    `city_name` varchar(32) CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '市级名称',
    `district_code` varchar(12) CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '区级区划编号',
    `district_name` varchar(32) CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '区级名称',
    `detail` varchar(200) CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '详细地址',
    `label` varchar(100) CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '标签',
    `is_default` tinyint(1) NOT NULL DEFAULT '0' COMMENT '默认 0 否 1是',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='地址簿';

CREATE TABLE `category` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    `type` int DEFAULT NULL COMMENT '类型   1 菜品分类 2 套餐分类',
    `name` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '分类名称',
    `sort` int NOT NULL DEFAULT '0' COMMENT '顺序',
    `status` int DEFAULT NULL COMMENT '分类状态 0:禁用，1:启用',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `create_user` bigint DEFAULT NULL COMMENT '创建人',
    `update_user` bigint DEFAULT NULL COMMENT '修改人',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_category_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='菜品及套餐分类';

CREATE TABLE `dish` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '菜品名称',
    `category_id` bigint NOT NULL COMMENT '菜品分类id',
    `price` decimal(10,2) DEFAULT NULL COMMENT '菜品价格',
    `image` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '图片',
    `description` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '描述信息',
    `status` int DEFAULT '1' COMMENT '0 停售 1 起售',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `create_user` bigint DEFAULT NULL COMMENT '创建人',
    `update_user` bigint DEFAULT NULL COMMENT '修改人',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_dish_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='菜品';

CREATE TABLE `dish_flavor` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    `dish_id` bigint NOT NULL COMMENT '菜品',
    `name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '口味名称',
    `value` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '口味数据list',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='菜品口味关系表';

CREATE TABLE `employee` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '姓名',
    `username` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '用户名',
    `password` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '密码',
    `phone` varchar(11) COLLATE utf8_bin NOT NULL COMMENT '手机号',
    `sex` varchar(2) COLLATE utf8_bin NOT NULL COMMENT '性别',
    `id_number` varchar(18) COLLATE utf8_bin NOT NULL COMMENT '身份证号',
    `status` int NOT NULL DEFAULT '1' COMMENT '状态 0:禁用，1:启用',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `create_user` bigint DEFAULT NULL COMMENT '创建人',
    `update_user` bigint DEFAULT NULL COMMENT '修改人',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='员工信息';

CREATE TABLE `order_detail` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '名字',
    `image` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '图片',
    `order_id` bigint NOT NULL COMMENT '订单id',
    `dish_id` bigint DEFAULT NULL COMMENT '菜品id',
    `setmeal_id` bigint DEFAULT NULL COMMENT '套餐id',
    `dish_flavor` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '口味',
    `number` int NOT NULL DEFAULT '1' COMMENT '数量',
    `amount` decimal(10,2) NOT NULL COMMENT '金额',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='订单明细表';

CREATE TABLE `orders` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    `number` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '订单号',
    `status` int NOT NULL DEFAULT '1' COMMENT '订单状态 1待付款 2待接单 3已接单 4派送中 5已完成 6已取消 7退款',
    `user_id` bigint NOT NULL COMMENT '下单用户',
    `address_book_id` bigint NOT NULL COMMENT '地址id',
    `order_time` datetime NOT NULL COMMENT '下单时间',
    `checkout_time` datetime DEFAULT NULL COMMENT '结账时间',
    `pay_method` int NOT NULL DEFAULT '1' COMMENT '支付方式 1微信,2支付宝',
    `pay_status` tinyint NOT NULL DEFAULT '0' COMMENT '支付状态 0未支付 1已支付 2退款',
    `amount` decimal(10,2) NOT NULL COMMENT '实收金额',
    `remark` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
    `phone` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号',
    `address` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '地址',
    `user_name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名称',
    `consignee` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '收货人',
    `cancel_reason` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '订单取消原因',
    `rejection_reason` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '订单拒绝原因',
    `cancel_time` datetime DEFAULT NULL COMMENT '订单取消时间',
    `estimated_delivery_time` datetime DEFAULT NULL COMMENT '预计送达时间',
    `delivery_status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '配送状态  1立即送出  0选择具体时间',
    `delivery_time` datetime DEFAULT NULL COMMENT '送达时间',
    `pack_amount` int DEFAULT NULL COMMENT '打包费',
    `tableware_number` int DEFAULT NULL COMMENT '餐具数量',
    `tableware_status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '餐具数量状态  1按餐量提供  0选择具体数量',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='订单表';

CREATE TABLE `setmeal` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    `category_id` bigint NOT NULL COMMENT '菜品分类id',
    `name` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '套餐名称',
    `price` decimal(10,2) NOT NULL COMMENT '套餐价格',
    `status` int DEFAULT '1' COMMENT '售卖状态 0:停售 1:起售',
    `description` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '描述信息',
    `image` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '图片',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `create_user` bigint DEFAULT NULL COMMENT '创建人',
    `update_user` bigint DEFAULT NULL COMMENT '修改人',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_setmeal_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='套餐';

CREATE TABLE `setmeal_dish` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    `setmeal_id` bigint DEFAULT NULL COMMENT '套餐id',
    `dish_id` bigint DEFAULT NULL COMMENT '菜品id',
    `name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '菜品名称 （冗余字段）',
    `price` decimal(10,2) DEFAULT NULL COMMENT '菜品单价（冗余字段）',
    `copies` int DEFAULT NULL COMMENT '菜品份数',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='套餐菜品关系';

CREATE TABLE `shopping_cart` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '商品名称',
    `image` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '图片',
    `user_id` bigint NOT NULL COMMENT '主键',
    `dish_id` bigint DEFAULT NULL COMMENT '菜品id',
    `setmeal_id` bigint DEFAULT NULL COMMENT '套餐id',
    `dish_flavor` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '口味',
    `number` int NOT NULL DEFAULT '1' COMMENT '数量',
    `amount` decimal(10,2) NOT NULL COMMENT '金额',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='购物车';

CREATE TABLE `user` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    `openid` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '微信用户唯一标识',
    `name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '姓名',
    `phone` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号',
    `sex` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '性别',
    `id_number` varchar(18) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证号',
    `avatar` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '头像',
    `create_time` datetime DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='用户信息';