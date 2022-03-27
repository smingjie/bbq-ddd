/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : bbq-ddd

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2022-03-27 11:19:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dict_map
-- ----------------------------
DROP TABLE IF EXISTS `dict_map`;
CREATE TABLE `dict_map` (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '字典分类，外键',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典码',
  `value` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典值',
  `order_num` int(11) DEFAULT '0' COMMENT '排序',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标记 0：正常 1：已删除',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `type-code` (`type`,`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='数据字典表';

-- ----------------------------
-- Table structure for dict_type
-- ----------------------------
DROP TABLE IF EXISTS `dict_type`;
CREATE TABLE `dict_type` (
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典类型',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典名称',
  `sequence` int(11) DEFAULT '0' COMMENT '排序',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标记 0：正常 1：已删除',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`type`),
  UNIQUE KEY `type` (`type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='数据字典表';

-- ----------------------------
-- Table structure for flow_config
-- ----------------------------
DROP TABLE IF EXISTS `flow_config`;
CREATE TABLE `flow_config` (
  `flow_id` varchar(36) COLLATE utf8mb4_general_ci NOT NULL COMMENT '工作流配置id',
  `flow_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '工作流配置代码',
  `flow_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '工作流配置名称',
  `version` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '工作流配置版本，1.0',
  `business_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '业务模块的类别',
  `business_call` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '业务模块的回调状态更新地址',
  `enabled` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否启用',
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
  `node_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置节点id',
  `flow_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '工作流配置id',
  `node_type` tinyint(4) NOT NULL COMMENT '配置节点类型，0首节点 1中间节点 2尾结点',
  `node_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '工作流节点名称',
  `node_last_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '工作流上一个节点（0为首节点）',
  `node_next_success_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '工作流下一个节点（if success）',
  `node_next_failure_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '工作流失败跳转节点',
  `sequence` int(11) DEFAULT NULL COMMENT '排序',
  `success_sta` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '成功状态码',
  `failure_sta` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '失败状态码',
  `create_by` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`node_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='工作流配置的节点信息';

-- ----------------------------
-- Table structure for flow_config_node_handler
-- ----------------------------
DROP TABLE IF EXISTS `flow_config_node_handler`;
CREATE TABLE `flow_config_node_handler` (
  `handler_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '节点处理者id',
  `node_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置节点id',
  `user_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '处理者id',
  `user_name` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '处理者名称',
  `enabled` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否启用',
  `create_by` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`handler_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='工作流配置的节点处理者';

-- ----------------------------
-- Table structure for flow_ins
-- ----------------------------
DROP TABLE IF EXISTS `flow_ins`;
CREATE TABLE `flow_ins` (
  `ins_id` varchar(36) COLLATE utf8mb4_general_ci NOT NULL COMMENT '工作流实例id',
  `flow_id` varchar(36) COLLATE utf8mb4_general_ci NOT NULL COMMENT '工作流配置id',
  `module_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '相关业务模块的主键id',
  `module_type` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '业务模块的类别',
  `module_table` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '相关业务模块的主表名',
  `module_key` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '相关业务模块的主表的主键列名',
  `module_sta` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '相关业务模块的主表的状态列名',
  `module_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '工作流实例名称',
  `node_curr` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '当前节点',
  `node_pre` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '上一个节点',
  `node_next` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '下一个节点',
  `succeed` tinyint(4) DEFAULT NULL COMMENT '是否执行成功',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除',
  `finished` tinyint(4) DEFAULT '0' COMMENT '是否执行完毕',
  `create_by` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ins_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='工作流实例表';

-- ----------------------------
-- Table structure for flow_ins_history
-- ----------------------------
DROP TABLE IF EXISTS `flow_ins_history`;
CREATE TABLE `flow_ins_history` (
  `id` varchar(36) COLLATE utf8mb4_general_ci NOT NULL COMMENT '历史记录id',
  `ins_id` varchar(36) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '工作流实例id',
  `ins_node_id` varchar(36) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '工作流实例节点id',
  `module_id` varchar(36) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '具体业务模块id',
  `record_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '记录名称',
  `record_sta` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '记录状态',
  `record_sta_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '记录状态名称',
  `succeed` tinyint(4) DEFAULT '0' COMMENT '是否执行成功',
  `handle_status` varchar(10) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '处理状态',
  `handle_suggestion` varchar(200) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '处理结果建议',
  `handle_time` datetime DEFAULT NULL COMMENT '处理时间',
  `handler_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '处理者id',
  `handler_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '处理者姓名',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除',
  `create_by` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='实例历史记录表';

-- ----------------------------
-- Table structure for flow_ins_node
-- ----------------------------
DROP TABLE IF EXISTS `flow_ins_node`;
CREATE TABLE `flow_ins_node` (
  `ins_node_id` varchar(36) COLLATE utf8mb4_general_ci NOT NULL COMMENT '实例节点id',
  `ins_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '工作流实例id',
  `node_type` tinyint(4) DEFAULT NULL COMMENT '实例节点类型，0首节点 1中间节点 2尾结点',
  `node_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '工作流节点名称',
  `node_next_id` varchar(36) COLLATE utf8mb4_general_ci NOT NULL COMMENT '工作流下一个节点（if success）',
  `node_fail_id` varchar(36) COLLATE utf8mb4_general_ci NOT NULL COMMENT '工作流失败跳转节点',
  `sequence` int(11) NOT NULL COMMENT '排序',
  `succ_sta` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '成功状态码',
  `fail_sta` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '失败状态码',
  `executed` tinyint(4) DEFAULT NULL COMMENT '是否已经执行',
  `successed` tinyint(4) DEFAULT NULL COMMENT '是否执行成功',
  `create_by` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ins_node_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='工作流实例的节点信息';

-- ----------------------------
-- Table structure for flow_ins_node_handler
-- ----------------------------
DROP TABLE IF EXISTS `flow_ins_node_handler`;
CREATE TABLE `flow_ins_node_handler` (
  `id` varchar(36) COLLATE utf8mb4_general_ci NOT NULL COMMENT '节点处理者id',
  `ins_node_id` varchar(36) COLLATE utf8mb4_general_ci NOT NULL COMMENT '实例节点id',
  `handler_id` varchar(36) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '处理者id',
  `handler_name` varchar(36) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '处理者名称',
  `enabled` tinyint(4) DEFAULT '0' COMMENT '是否启用',
  `create_by` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='工作流实例的节点处理者';

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
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号',
  `status` tinyint(1) unsigned zerofill DEFAULT NULL COMMENT '状态  0：禁用  1：正常',
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
