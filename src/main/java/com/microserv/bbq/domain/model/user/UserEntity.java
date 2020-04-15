package com.microserv.bbq.domain.model.user;

import lombok.Data;

/**
 * @author jockeys
 * @since 2020/4/12
 */
@Data
public class UserEntity {
    private String id;
    private String name;
    private String spell;
    private String wechat;
    private String phone;
    private Boolean gender;
}
