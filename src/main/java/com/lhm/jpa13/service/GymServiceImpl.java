package com.lhm.jpa13.service;

import com.lhm.jpa13.entity.Gym;
import com.lhm.jpa13.jpa.GymJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@CacheConfig(cacheNames = "gym")
public class GymServiceImpl {
    @Autowired
    GymJPA gymJPA;

    @Caching(
            cacheable = {@Cacheable(key="#name")},
            put={ @CachePut(key = "#result.id"),
                    @CachePut(key = "#result.address")
            }
    )
    public Gym queryGymByName(String name){
        System.out.println("查询的名字："+name);
        return gymJPA.queryByName(name);
    }

    @Transactional
    public List<Gym> getAllgyms() {
        return (List<Gym>) gymJPA.findAll();
    }

    @Transactional
    @Cacheable(key = "#address")
    public List<Gym> findByAddress(String address) {
        return gymJPA.nativeQuery(address);
    }

    @Transactional
    @Cacheable(key = "#id")
    public Gym getById(Long id) {
        return gymJPA.findById(id).get();
    }

    @Transactional
    public void deletePerson(Long personId) {
        gymJPA.deleteById(personId);
    }

    @Transactional
    @CacheEvict(cacheNames = "gym",allEntries = true)
    public boolean addGym(Gym gym) {
        return gymJPA.save(gym) != null;
    }

    @Transactional
    @CachePut(key = "#result.id")
    public boolean updatePerson(Gym gym) {
        return gymJPA.save(gym) != null;
    }





}
