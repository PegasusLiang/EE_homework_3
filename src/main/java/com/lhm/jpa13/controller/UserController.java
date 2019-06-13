package com.lhm.jpa13.controller;

import com.lhm.jpa13.entity.Gym;
import com.lhm.jpa13.entity.UserEntity;
import com.lhm.jpa13.jpa.UserJPA;
import com.lhm.jpa13.service.UserQueryService;
import com.lhm.jpa13.service.UserQueryServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController{

    @Autowired
    private UserQueryServiceImpl userQueryServiceImpl;
    @Autowired
    private  UserJPA userJPA;


    //分页尝试失败
//    @GetMapping("/users")
//    public String listCategory(Model m,@RequestParam(value = "start", defaultValue = "0") int start,@RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
//        start = start<0?0:start;
//        Sort sort = new Sort(Sort.Direction.DESC, "id");
//        Pageable pageable = PageRequest.of(start, size, sort);
//        Page<UserEntity> datas =userJPA.findAll(pageable);
//        m.addAttribute("datas", datas);
//        return "user/userlist";
//    }

    @ApiOperation(value="获取用户列表", notes="获取用户列表")
    @RequestMapping(value = "users", method = RequestMethod.GET)
    @GetMapping("/users")
    public String toUserList(Model model){
        Collection<UserEntity> users = userQueryServiceImpl.getAllusers();//获取所有的user，findAll()是自带的
        if(users != null)
            model.addAttribute("users",users);
        return "user/userlist";
    }

    //显示需要修改的信息
    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
    @GetMapping("/user/{id}")
    public String toEditUser(Long id,Model model){
        UserEntity user = userQueryServiceImpl.getById(id);//根据id查找用户
        if(user != null) {
            model.addAttribute("user", user);
        }
        return "user/user_add";
    }

    //显示添加页面
    @GetMapping("/user")
    public String toAddPage(){
        return "user/user_add";
    }


    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @PostMapping("/user")
    public String updateUser(UserEntity user){
        userQueryServiceImpl.addUser(user);
        return "redirect:/users";
    }


    @ApiOperation(value="删除用户", notes="根据url的id来指定删除用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping("/user/{id}")
    public String toDeleteUser(Long id, Model model){
        userQueryServiceImpl.deletePerson(id);
        return "redirect:/users";
    }


//    @RequestMapping(value = "/user/add")
//    public String add()
//    {
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUsername("测试");
//        userEntity.setPassword("123456");
//        userEntity.setAge(21);
//        userQueryServiceImpl.addUser(userEntity);
//        return "用户信息添加成功";
//    }
//    @RequestMapping(value = "user/delete/{id}")
//    public String delete(@PathVariable("id")Long userId)
//    {
//        userJPA.deleteById(userId);
//        return "用户信息删除成功";
//    }
//
//    @RequestMapping(value = "/age")
//    public List<UserEntity> age(){
//        return userJPA.nativeQuery(20);
//    }
//
//    /**
//     * 根据条件自定。义编写删除SQL
//     * @return
//     */
//    @RequestMapping(value = "/deleteWhere")
//    public String deleteWhere()
//    {
//        userJPA.deleteQuery("测试","测试地址");
//        return "自定义SQL删除数据成功";
//    }

//    /**
//     * 分页查询测试
//     * @param page 传入页码，从1开始
//     * @return
//     */
//    @RequestMapping(value = "/cutpage")
//    public List<UserEntity> cutPage(int page)
//    {
//        UserEntity user = new UserEntity();
//        user.setSize(2);
//        user.setSord("desc");
//        user.setPage(page);
//
//        //获取排序对象
//        Sort.Direction sort_direction = Sort.Direction.ASC.toString().equalsIgnoreCase(user.getSord()) ? Sort.Direction.ASC : Sort.Direction.DESC;
//        //设置排序对象参数
//        Sort sort = new Sort(sort_direction, user.getSidx());
//        //创建分页对象
//        PageRequest pageRequest = PageRequest.of(user.getPage() - 1,user.getSize(),sort);
//        //执行分页查询
//        return userJPA.findAll(pageRequest).getContent();
//    }
}

