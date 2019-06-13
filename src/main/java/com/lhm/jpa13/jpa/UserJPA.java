package com.lhm.jpa13.jpa;

import com.lhm.jpa13.entity.Gym;
import com.lhm.jpa13.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface UserJPA extends JpaRepository<UserEntity,Long>
{

    public UserEntity findByUsernameAndPassword(String username,String password);

    //查询大于20岁的用户
    @Query(value = "select * from t_user where t_age > ?1",nativeQuery = true)
    public List<UserEntity> nativeQuery(int age);

    //根据用户名、密码删除一条数据
    @Modifying
    @Query(value = "delete from t_user where t_name = ?1 and t_address = ?2",nativeQuery = true)
    public void deleteQuery(String name,String address);

}
