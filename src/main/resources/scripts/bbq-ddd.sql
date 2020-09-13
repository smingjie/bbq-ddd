
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for flow_config
-- ----------------------------
DROP TABLE IF EXISTS `flow_config`;
CREATE TABLE `flow_config` (
  `flow_id` varchar(36) COLLATE utf8mb4_general_ci NOT NULL COMMENT '工作流配置id',
  `flow_code` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '工作流配置代码',
  `flow_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '工作流配置名称',
  `flow_version` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '工作流配置版本，1.0',
  `module_type` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '业务模块的类别',
  `module_table` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '相关业务模块的主表名',
  `module_key` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '相关业务模块的主表的主键列名',
  `module_sta` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '相关业务模块的主表的状态列名',
  `enabled` tinyint(4) DEFAULT '0' COMMENT '是否启用',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除',
  `create_by` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`flow_id`),
  UNIQUE KEY `flow_code` (`flow_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='工作流配置表';

-- ----------------------------
-- Table structure for flow_config_node
-- ----------------------------
DROP TABLE IF EXISTS `flow_config_node`;
CREATE TABLE `flow_config_node` (
  `flow_node_id` varchar(36) COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置节点id',
  `flow_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '工作流配置id',
  `node_type` tinyint(4) DEFAULT NULL COMMENT '配置节点类型，0首节点 1中间节点 2尾结点',
  `node_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '工作流节点名称',
  `node_last_id` varchar(36) COLLATE utf8mb4_general_ci NOT NULL COMMENT '工作流上一个节点（0为首节点）',
  `node_next_id` varchar(36) COLLATE utf8mb4_general_ci NOT NULL COMMENT '工作流下一个节点（if success）',
  `node_fail_id` varchar(36) COLLATE utf8mb4_general_ci NOT NULL COMMENT '工作流失败跳转节点',
  `sequence` int(11) NOT NULL COMMENT '排序',
  `succ_sta` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '成功状态码',
  `fail_sta` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '失败状态码',
  `create_by` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`flow_node_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='工作流配置的节点信息';

-- ----------------------------
-- Table structure for flow_config_node_handler
-- ----------------------------
DROP TABLE IF EXISTS `flow_config_node_handler`;
CREATE TABLE `flow_config_node_handler` (
  `id` varchar(36) COLLATE utf8mb4_general_ci NOT NULL COMMENT '节点处理者id',
  `flow_node_id` varchar(36) COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置节点id',
  `handler_id` varchar(36) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '处理者id',
  `handler_name` varchar(36) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '处理者名称',
  `enabled` tinyint(4) DEFAULT '0' COMMENT '是否启用',
  `create_by` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='工作流配置的节点处理者';

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '唯一ID',
  `param_key` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'key',
  `param_value` varchar(2000) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `param_key` (`param_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统配置信息表';

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典名称',
  `type` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典类型',
  `code` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典码',
  `value` varchar(1000) COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典值',
  `order_num` int(11) DEFAULT '0' COMMENT '排序',
  `remark` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '删除标记 0：正常 1：已删除',
  `create_by` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`,`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='数据字典表';

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` varchar(36) COLLATE utf8mb4_general_ci NOT NULL COMMENT '唯一Id',
  `parent_id` varchar(36) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单URL',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='菜单管理';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '唯一id',
  `role_code` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色代码',
  `role_name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_type` char(1) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色类型 U自定义 S系统预设',
  `remark` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态  0：禁用  1：正常',
  `create_by` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_code` (`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户角色';

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` varchar(36) COLLATE utf8mb4_general_ci NOT NULL COMMENT '唯一id',
  `role_id` varchar(36) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色ID',
  `menu_id` varchar(36) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '唯一id',
  `username` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密码',
  `email` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) unsigned zerofill DEFAULT NULL COMMENT '状态  0：禁用  1：正常',
  `create_by` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统用户';

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` varchar(36) COLLATE utf8mb4_general_ci NOT NULL COMMENT '唯一id',
  `role_id` varchar(36) COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色id',
  `user_id` varchar(36) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户与角色关系表';
