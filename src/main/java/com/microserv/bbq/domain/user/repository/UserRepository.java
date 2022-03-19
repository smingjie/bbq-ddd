package com.microserv.bbq.domain.user.repository;

import com.microserv.bbq.domain.user.UserEntity;

/**
 * @author jockeys
 * @date 2020/9/13
 */
public interface UserRepository {

	//--查询

	UserEntity selectById(String id);

	UserEntity selectByUsername(String username);


	//--命令
	boolean insert(UserEntity item);

	boolean update(UserEntity item);

	boolean delete(UserEntity item);

}
