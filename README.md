## SpringBoot JPA Demo
第一次作业的基础：

> 实现了基础的注册登录功能，同时展示t_gym跟t_user两表的数据。
>
> 实现了两表关联，UserEntity跟Gym是多对一的关系，在Gym类中建立关联。
>
> 实现Spring Cache，多次查询在第一次查询后不再从数据库中查询。
>
> 实现了分页，但未在前端页面显示。
>
> 实现了Auditing，创建时间，更新时间自动生成。
>
> 前端的展示我用了thymeleaf来映射数据。

## **本次完成的点**

1. Restful Service , restful api的设计。修改了上次的url设计
2. RateLimiting 限流实现，简单的限流。100s内允许10次访问为测试用例。
3. Online API documentation。 Using swagger实现接口文档生成
4. Cache部分保留之前的spring cache
5. Spring Security 尝试配置，但配置尚未完成。



### 注册登录

注册登录为第一次作业内容，这儿放出截图

![1556506128732](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1556506128732.png)

![1556506151019](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1556506151019.png)



![1556506189869](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1556506189869.png)





## Restful API

![1558020856056](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1558020856056.png)



API的设计在路径上不能包含有动词。

url地址中只包含名词表示资源，使用http动词表示动作进行操作资源

```
GET /user/toUserList --> GET /users  获取所有用户
GET /user/addUser --> POST /user  添加一个用户
GET /user/toEditUser --> PUT /user/{id}  修改一个用户 
GET /rest/api/deleteUser?id=1 --> DELETE /user/users/1  删除一个用户
```





## Swagger 在线API文档生成

![1558021926412](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1558021926412.png)



![1558021969528](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1558021969528.png)

在Controller中添加注解 @ApiOperation 注解，加上value跟notes说明。





## RateLimiting 限流实现

本部分按照别人例子更改应用

![1558021157083](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1558021157083.png)



![1558021177467](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1558021177467.png)

1. 创建一个Limit注解
2. 

![1558021253503](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1558021253503.png)

3. 在 **LimiterIntercepeter** 中调用 `execute` 方法传入我们的 Lua 脚本内容，然后通过返回值判断是否超出我们预期的范围，超出则给出错误提示。

4. 在控制层中添加@Limit()注解，在Redis中生成过期时间为100s的 key = test的记录。

   定义了一个`AtomicInteger` 来测试。



![1558022111737](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1558022111737.png)

![1558022144114](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1558022144114.png)

在超过十次访问后，出现自定义的500错误。因为达到了自己设定的阈值，系统出现错误。

![1558022192283](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1558022192283.png)



## Spring Security



![1558021580187](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1558021580187.png)



在POM中加入了 spring-boot-starter-security 依赖后，未经配置，系统会自动拦截。初始会进入验证界面

![1558021706921](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1558021706921.png)

系统自动生成一串密码 Using generated security password

用户名：user 密码：7cd0db22-cfde-40e5-846b-492d9aa9fcf6

这样登陆后才进入系统



