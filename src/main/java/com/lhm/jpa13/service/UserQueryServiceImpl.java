package com.lhm.jpa13.service;

import com.lhm.jpa13.entity.Gym;
import com.lhm.jpa13.entity.UserEntity;
import com.lhm.jpa13.jpa.UserJPA;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
@CacheConfig(cacheNames = "user")
public class UserQueryServiceImpl implements UserQueryService
{
    @Resource
    UserJPA userJPA;

    @Override
    public Page<UserEntity> findUser(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "id");
        return userJPA.findAll(pageable);
    }


    @Caching(
            cacheable = {@Cacheable(key="#username")},
            put={ @CachePut(key = "#result.id"),
                    @CachePut(key = "#result.age"),
                    @CachePut(key = "#result.gym")
            }
    )
    //查询大于21岁的用户
    public List<UserEntity> queryGymById(Integer id){
        System.out.println("查询的id："+id);
        return userJPA.nativeQuery(id);
    }

    @Transactional
    public List<UserEntity> getAllusers() {
        return (List<UserEntity>) userJPA.findAll();
    }


    @Transactional
    @Cacheable(key = "#id")
    public UserEntity getById(@RequestBody Long id) {
        return userJPA.findById(id).get();
    }

    @Transactional
    public void deletePerson(Long personId) {
        userJPA.deleteById(personId);
    }

    @Transactional
    @CacheEvict(cacheNames = "user",allEntries = true)
    public boolean addUser(UserEntity user) {
        return userJPA.save(user) != null;
    }

    @Transactional
    @CachePut(key = "#result.id")
    public boolean updatePerson(UserEntity user) {
        return userJPA.save(user) != null;
    }


}
