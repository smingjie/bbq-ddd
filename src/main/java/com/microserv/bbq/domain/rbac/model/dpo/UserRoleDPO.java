package com.microserv.bbq.domain.rbac.model.dpo;

import com.microserv.bbq.domain.rbac.model.RoleEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 聚合 -用户拥有角色
 *
 * @author mingjie
 * @date 2022/3/27
 */
@Data
@NoArgsConstructor
public class UserRoleDPO implements Serializable {
    private String userId;
    private String userName;
    private List<RoleEntity> roles;
}
