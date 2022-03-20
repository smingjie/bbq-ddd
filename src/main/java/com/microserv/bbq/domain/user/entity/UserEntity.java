package com.microserv.bbq.domain.user.entity;

import cn.hutool.core.util.StrUtil;
import com.microserv.bbq.domain.common.factory.RepositoryFactory;
import com.microserv.bbq.domain.common.interfaces.IDomainCRUD;
import com.microserv.bbq.domain.common.interfaces.IDomainMetaData;
import com.microserv.bbq.domain.user.repository.UserRepository;
import com.microserv.bbq.infrastructure.general.exception.BusinessException;
import com.microserv.bbq.infrastructure.general.extension.annotation.ddd.DomainEntity;
import com.microserv.bbq.infrastructure.general.toolkit.ModelUtils;
import com.microserv.bbq.infrastructure.general.toolkit.SequenceUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author jockeys
 * @since 2020/4/12
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@DomainEntity
public class UserEntity implements IDomainCRUD<UserEntity>, IDomainMetaData {
    private static final UserRepository userRepository = RepositoryFactory.get(UserRepository.class);

    // field
    private String userId;
    private String username;
    private String name;
    private String mobile;
    private Boolean enabled;
    // private String password;
    // field(meta data)
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

    public UserEntity(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean delete() {
        return userRepository.delete(this);
    }

    @Override
    public UserEntity saveOrUpdate() {
        boolean success = Objects.isNull(this.userId)
                ? userRepository.insert(this.setUserId(SequenceUtils.uuid32()))
                : userRepository.update(this);

        if (success) {
            UserEntity userEntity = userRepository.selectById(this.userId);
            ModelUtils.convert(userEntity, this);
        }

        return this;
    }

    @Override
    public UserEntity get() {
        if (StrUtil.isAllBlank(this.userId, this.username)) {
            throw new BusinessException("获取实体时，用户id和用户名不能同时为空");
        }

        UserEntity item = Objects.nonNull(this.userId)
                ? userRepository.selectById(this.userId)
                : userRepository.selectByUsername(this.username);

        if (Objects.nonNull(item)) {
            ModelUtils.convert(item, this);
        }

        return this;
    }

    public String getDisplayName() {
        if (this.name == null) {
            return this.username;
        }
        if (this.username == null) {
            return this.name;
        }
        return String.join("/", this.name, this.username);
    }

}


