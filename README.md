项目介绍：<br>
        这是一款为餐饮类企业定制的软件产品，分为管理端和用户端。实现用户端点单，管理端处理订单的简易外卖软件 <br> 
        
业务板块介绍：
![image](https://github.com/mark32123/sky-take-out/blob/master/sky-common/src/main/resources/image/d29c5ba41100516748c2f92b16473458.png
)  <br>



应用层： 
Spring boot：

        基于Spring的开源框架，简化各项配置，使得开发人员专注于业务功能的实现

Spring MVC：
       基于 MVC（Model-View-Controller）模式的 Web 应用程序开发框架。它提供了一种结构清晰、模块化的方式来构建可扩展的 Web 应用程序

Spring Task：
        定时任务依赖，完成相关配置，后端可以定时完成任务

Httpclient：

        HTTP开源的通信库，使得后端可以发送和处理后端请求，常用于后端请求各种接口时用到

Spring Cache:

        缓存依赖，把数据存储到缓存中，如果前端再次请求相同数据，使得后端可以从缓存中拿数据，减少了对数据库的IO操作，降低了后端服务器的压力。

JWT：

        令牌技术，校验用户身份。

阿里云OSS：

        第三方云存储技术，后端调用阿里云OSS来存储菜品等图片。

Swagger：

        开源框架，实现设计、构建和测试RESTful API的开源框架，但在本项目中，我们使用的是Swagger的优化版本Knife4j，它基于注解的方式注解在启动类上，在后端启动之后，在网页输入localhost 8080/doc.html就可以打开相关接口管理界面。

POI：

        读取和写入Microsoft Office格式文件，如Word文档、Excel电子表格和PowerPoint演示文稿。

WebSocket：

        一种通信协议，允许客户端与服务器进行持久化连接，并且区别于请求-响应模式，WebSocket协议实现了客户端与服务器的双向通信。


        数据层：
MySQL：

        关系型存储系统，基于表的形式对数据进行存储，直接存储数据到磁盘当中

Redis：

        键值型存储系统，基于键值对的形式对数据进行存储，数据会被存储到缓存当中

Mybatis：

        开源的持久层框架，简化了java与关系型数据的之间的操作

pagehelper：<br>
        分页框架，以注解的形式使用，简化分页查询中的分页查询操作

spring data redis：

        spring框架提供的与Redis数据库进行交互的模块，简化在java中使用Redis的操作步骤。 

        
网关层：
Nginx：

        使用Nginx来部署前端。是一款高性能Web开源服务器，在大型项目中，我们可以使用Nginx的负载均衡来合理请求到多台服务器，减小后端服务器压力
