package com.lhm.jpa13.controller;


import com.lhm.jpa13.entity.Gym;
import com.lhm.jpa13.entity.UserEntity;
import com.lhm.jpa13.jpa.GymJPA;
import com.lhm.jpa13.service.GymServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.WebParam;
import java.util.Collection;
import java.util.List;

@Controller
public class GymController {

    @Autowired
    private GymServiceImpl gymService;


    @GetMapping("/gyms")
    public String toGymList(Model model){
        Collection<Gym> gyms = gymService.getAllgyms();//获取所有的user，findAll()是自带的
        if(gyms != null)
            model.addAttribute("gyms",gyms);
        return "user/gymlist";
    }

    @GetMapping("/gym/{address}")
    public String toGymName(@PathVariable("address") String address, Model model){
        model.addAttribute("gyms",gymService.findByAddress(address));
        return "user/gymlist";
    }


//    @RequestMapping(value = "gym/list")
//    public List<Gym> list()
//    {
//        return gymJPA.findAll();
//    }
//
//    @RequestMapping(value = "gym/add")
//    public String add()
//    {
//        Gym gym = new Gym();
//        gym.setName("测试");
//        gym.setAddress("测试地址");
//        gymJPA.save(gym);
//        return "Gym信息增加成功";
//    }
//
//    @RequestMapping(value = "gym/delete/{id}")
//    public String delete(@PathVariable("id")Long gymid)
//    {
//        gymJPA.deleteById(gymid);
//        return "gym信息删除成功";
//    }



}


