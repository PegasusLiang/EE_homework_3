package com.lhm.jpa13.jpa;

import com.lhm.jpa13.entity.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GymJPA extends JpaRepository<Gym,Long> {
//    //查询大于20岁的用户
//    @Query(value = "select * from t_gym where t_age > ?1",nativeQuery = true)
//    public List<UserEntity> nativeQuery(int age);

    //查询地址的场馆
      @Query(value = "select * from t_gym where t_address = ?1",nativeQuery = true)
      public List<Gym> nativeQuery(String address);


    //根据场馆名名、地址删除一条数据
    @Modifying
    @Query(value = "delete from t_gym where t_name = ?1 and t_address = ?2",nativeQuery = true)
    public void deleteQuery(String name,String address);


    Gym queryByName(String name);
}
