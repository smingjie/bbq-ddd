package com.microserv.bbq.domain.model.user;

import lombok.Data;

/**
 * @author jockeys
 * @since 2020/4/12
 */
@Data
public class UserEntity {
    private String userId;
    private String username;
//    private String password;
    private String email;
    private String mobile;
    private Integer status;
}
