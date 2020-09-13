package com.microserv.bbq.domain.model.user;

import cn.hutool.core.util.StrUtil;
import com.microserv.bbq.domain.common.ICrud;
import com.microserv.bbq.domain.factory.RepoFactory;
import com.microserv.bbq.domain.repository.UserRepo;
import com.microserv.bbq.infrastructure.general.toolkit.ModelUtils;
import com.microserv.bbq.infrastructure.general.toolkit.SequenceUtils;
import lombok.Data;

import java.util.Objects;

/**
 * @author jockeys
 * @since 2020/4/12
 */
@Data
public class UserEntity implements ICrud<UserEntity> {
    private static UserRepo userRepo = RepoFactory.get(UserRepo.class);

    // field
    private String userId;
    private String username;

    // private String password;
    private String  email;
    private String  mobile;
    private Integer status;

    @Override
    public boolean delete() {
        return userRepo.delete(this);
    }

    @Override
    public UserEntity saveOrUpdate() {
        if (Objects.nonNull(this.userId)) {
            this.userId = SequenceUtils.uuid32();
            userRepo.insert(this);
        } else {
            userRepo.update(this);
        }

        return this;
    }

    @Override
    public UserEntity get() {
        if (!StrUtil.isAllBlank(this.userId, this.username)) {
            UserEntity item = Objects.nonNull(this.userId)
                              ? userRepo.selectById(this.userId)
                              : userRepo.selectByUsername(this.username);

            ModelUtils.convert(item, this);
        }

        return this;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
