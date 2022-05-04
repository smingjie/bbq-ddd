package com.microserv.bbq.domain.user.repository;

import com.microserv.bbq.domain.user.model.UserEntity;
import com.microserv.bbq.domain.user.model.part.UserContactVObj;
import com.microserv.bbq.domain.user.model.part.UserDictVObj;

import java.util.List;

/**
 * 领域仓储接口定义-用户仓储
 *
 * @author mingjie
 * @date 2022/03/20
 */
public interface UserRepository {

    //--查询

    UserEntity selectById(String id);

    UserEntity selectByUsername(String username);

    String selectDisplayNameByUserId(String userId);

    List<UserDictVObj> searchDictBy(String searchKey);

    List<UserContactVObj> selectContactByUserIds(List<String> userIds);

    //--命令
    boolean insert(UserEntity item);

    boolean update(UserEntity item);

    boolean delete(UserEntity item);

    boolean isMatchUsernamePassword(String username, String password);

}
