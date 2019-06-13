package com.lhm.jpa13.entity;

import com.lhm.jpa13.base.BaseEntity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "t_user")
public class UserEntity extends BaseEntity implements Serializable
{
    @Id
    @GeneratedValue
    @Column(name = "t_id")
    private Long id;

//    @JoinTable(name = "t_gym_user",
//            joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "t_id")},
//            inverseJoinColumns = {@JoinColumn(name="gym_id",referencedColumnName = "t_id")}
//    )
//    @ManyToMany
//    private List<Gym> gyms;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)//可选属性optional=false,表示gym不能为空。删除文章，不影响用户
    @JoinColumn(name="gym_id")//设置在t_gym表中的关联字段(外键)
    private Gym gym;//所属场馆


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "t_name")
    private String username;

    @Column(name = "t_age")
    private int age;

    @Column(name = "t_password")
    private String password;

    @CreatedDate
    private Date createAt;

    @LastModifiedDate
    private Date updateAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}
