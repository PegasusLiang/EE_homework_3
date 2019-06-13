package com.lhm.jpa13.service;

import com.lhm.jpa13.entity.UserEntity;
import org.springframework.data.domain.Page;

import javax.transaction.Transactional;

public interface UserQueryService {
    Page<UserEntity> findUser(Integer page, Integer size);

}
