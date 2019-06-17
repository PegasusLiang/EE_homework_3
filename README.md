## SpringBoot JPA Demo
### 四次作业
[第一次作业](https://github.com/PegasusLiang/EE_homework_1_JPA)

[第二次作业](https://github.com/PegasusLiang/EE_homework_2)

[第三次作业](https://github.com/PegasusLiang/EE_homework_3)

[第四次作业](https://github.com/PegasusLiang/EE_homework_4)


第一次作业的基础：
> 实现了基础的注册登录功能，同时展示t_gym跟t_user两表的数据。
> 实现了两表关联，UserEntity跟Gym是多对一的关系，在Gym类中建立关联。
> 实现Spring Cache，多次查询在第一次查询后不再从数据库中查询。
> 实现了分页，但未在前端页面显示。
> 实现了Auditing，创建时间，更新时间自动生成。> 前端的展示我用了thymeleaf来映射数据。

第二次作业基础：
1. Restful Service , restful api的设计。修改了上次的url设计
2. RateLimiting 限流实现，简单的限流。100s内允许10次访问为测试用例。
3. Online API documentation。 Using swagger实现接口文档生成
4. Cache部分保留之前的spring cache
5. Spring Security 尝试配置，但配置尚未完成。

## **本次完成的点**
### Kafka 应用
Kafka消息队列的应用。

先运行zookeeper

```bash
zookeeper-server-start.bat ../../config/zookeeper.properties
```

![1560417069279](https://github.com/PegasusLiang/EE_homework_3/blob/master/%E4%BD%9C%E4%B8%9A%E6%88%AA%E5%9B%BE/1560417069279.png)

接着运行Kafka

```
kafka-server-start.bat ../../config/server.properties
```

![1560417117253](https://github.com/PegasusLiang/EE_homework_3/blob/master/%E4%BD%9C%E4%B8%9A%E6%88%AA%E5%9B%BE/1560417117253.png)

1. ### 新增目录结构

![1560415537502](https://github.com/PegasusLiang/EE_homework_3/blob/master/%E4%BD%9C%E4%B8%9A%E6%88%AA%E5%9B%BE/1560415537502.png)

2. ### 生产者

![1560415967396](https://github.com/PegasusLiang/EE_homework_3/blob/master/%E4%BD%9C%E4%B8%9A%E6%88%AA%E5%9B%BE/1560415967396.png)

Publisher发送message消息。

kafkaTemplate.send(TopicConst.PAY_TOPIC,msg); 参数为 String topic + String msg。



3. ### 消费者

![1560416089880](https://github.com/PegasusLiang/EE_homework_3/blob/master/%E4%BD%9C%E4%B8%9A%E6%88%AA%E5%9B%BE/1560416089880.png)

消费者进行消费。

注解 @KafkaListener (topic)，监听对应topic的消息，后调用logger将消息打印在控制台。



4. ### 定义消息类

![1560416462489](https://github.com/PegasusLiang/EE_homework_3/blob/master/%E4%BD%9C%E4%B8%9A%E6%88%AA%E5%9B%BE/1560416462489.png)





5. ### 自定义工具类产生消息

![1560416486858](https://github.com/PegasusLiang/EE_homework_3/blob/master/%E4%BD%9C%E4%B8%9A%E6%88%AA%E5%9B%BE/1560416486858.png)



6. ### 编写Conrtroller接口进行测试

![1560416508712](https://github.com/PegasusLiang/EE_homework_3/blob/master/%E4%BD%9C%E4%B8%9A%E6%88%AA%E5%9B%BE/1560416508712.png)

写了一个循环，不停地发布消息，前端浏览器每刷新一次页面，发布者发布信息。

之前运行了kafka，现在运行项目

![1560417345941](https://github.com/PegasusLiang/EE_homework_3/blob/master/%E4%BD%9C%E4%B8%9A%E6%88%AA%E5%9B%BE/1560417345941.png)

发布，消费信息记录如图。
