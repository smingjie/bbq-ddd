package com.microserv.bbq.infrastructure.persistence.repository.impl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.microserv.bbq.infrastructure.persistence.po.SysUser;
import com.microserv.bbq.infrastructure.general.module.user.UserSearchParam;
import com.microserv.bbq.infrastructure.general.module.user.UserWithRoleItemCO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author mpGenerator
 * @since 2020-04-05
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    @Select({"<script>" +
            " select " +
            "      su.user_id , " +
            "      concat_ws('/',ifnull(su.name,'-') ,ifnull(su.username,'-' )) as user_name, " +
            "      if(su.status=1,1,0) as enabled , " +
            "      su.create_time , " +
            "      t.roles " +
            " from " +
            "      ( " +
            "      select " +
            "           sur.user_id , group_concat(sr.role_name, '/', sr.role_code separator ';' ) as roles " +
            "      from " +
            "           sys_user_role sur " +
            "      left join sys_role sr on " +
            "           sur.role_id = sr.role_id " +
            "      <where>" +
            "      <if test='roleName!=null and roleName!=\"\"'> " +
            "        and  sr.role_name like concat(#{roleName}, '%') " +
            "      </if>" +
            "      </where>" +
            "      group by " +
            "           sur.user_id ) t " +
            " inner join sys_user su on " +
            "      su.user_id = t.user_id " +
            " <where>" +
            " <if test='userName!=null and userName!=\"\"'> " +
            "    and ( su.name like concat(#{userName}, '%') or su.username like concat(#{userName}, '%') )" +
            " </if>" +
            " </where>" +
            " order by su.create_time desc" +
            "</script>"})
    List<UserWithRoleItemCO> selectListBySearchParam(UserSearchParam userSearchParam);
}
