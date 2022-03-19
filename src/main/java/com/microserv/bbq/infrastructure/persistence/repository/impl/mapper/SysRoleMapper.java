package com.microserv.bbq.infrastructure.persistence.repository.impl.mapper;

import com.microserv.bbq.infrastructure.persistence.po.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户角色 Mapper 接口
 * </p>
 *
 * @author mpGenerator
 * @since 2020-04-05
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
	@Select("select sr.* from sys_role sr where sr.role_id in (" +
			" select sur.role_id from sys_user_role sur where sur.user_id=#{userId})")
	List<SysRole> selectRoleListByUserId(@Param("userId") String userId);

}
