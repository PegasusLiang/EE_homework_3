//package com.lhm.jpa13.entity;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.Locale;
//
//@Entity
//@Table(name = "t_gym_user")
//public class GymUser implements Serializable {
//
//    private static final long serialVersionUID = -5792339556223731978L;
//
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private UserEntity userEntity;
//
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "gym_id")
//    private Gym gym;
//
//    public UserEntity getUserEntity() {
//        return userEntity;
//    }
//
//    public void setUserEntity(UserEntity userEntity) {
//        this.userEntity = userEntity;
//    }
//
//    public Gym getGym() {
//        return gym;
//    }
//
//    public void setGym(Gym gym) {
//        this.gym = gym;
//    }
//
//
//}
