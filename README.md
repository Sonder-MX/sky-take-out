# 苍穹外卖

本项目是使用 Spring Boot 框架开发的一个在线外卖订购系统。

## 技术栈

- 后端框架
  - SpringBoot (3.1.2)
  - mybatis
- 数据库
  - MySql
  - Redis
- 前端框架
  - Vue
  - Uniapp
  - ElementUI
- 前后端通信
  - RESTful API

## Windows 开发环境搭建

1. 安装 Java JDK 17 并配置环境变量
2. 安装 MySQL、Redis 数据库并创建相应数据库

   - 创建 MySQL 数据库与表: 运行 [mysql.sql](./demo/mysql.sql)

3. 安装 Maven 构建工具
4. 下载安装 Nginx 并完成以下配置

   ```
   # 在 http 这一项下配置以下内容

   map $http_upgrade $connection_upgrade{
   	default upgrade;
   	'' close;
   }

   upstream webservers{
     server 127.0.0.1:8080 weight=90 ;
     #server 127.0.0.1:8088 weight=10 ;
   }

   server {
       listen       80;
       server_name  localhost;

       location / {
           root   html/sky;
           index  index.html index.htm;
       }

       # 反向代理,处理管理端发送的请求
       location /api/ {
   		proxy_pass   http://localhost:8080/admin/;
           #proxy_pass   http://webservers/admin/;
       }

   	# 反向代理,处理用户端发送的请求
       location /user/ {
           proxy_pass   http://webservers/user/;
       }

   	# WebSocket
   	location /ws/ {
           proxy_pass   http://webservers/ws/;
   		proxy_http_version 1.1;
   		proxy_read_timeout 3600s;
   		proxy_set_header Upgrade $http_upgrade;
   		proxy_set_header Connection "$connection_upgrade";
       }

       location /media {
           root 配置媒体文件位置; # eg: D:/static
           # 注：在 D:/static 目录下创建 media 文件夹
       }
   }
   ```

5. 克隆项目到本地 `git clone https://github.com/Sonder-MX/sky-take-out.git `
6. 修改配置文件 [application.yml](./sky-server/src/main/resources/application.yml)

   ```yml
   spring:
     datasource:
       url: jdbc:mysql://url
       username: root
       password: 数据库密码
     data:
       redis:
         password: redis数据库密码
   ```

7. 在 [resources](./sky-server/src/main/resources/) 目录下新建 `application-env.yml` 文件，写入以下配置

   ```yml
   sky:
     wechat:
       appid: 申请微信小程序可获得
       secret: 申请微信小程序可获得
       mchid: 商户号
       mchSerialNo:
       privateKeyFilePath:
       apiV3Key:
       weChatPayCertFilePath:
       notifyUrl:
       refundNotifyUrl:
   ```

8. 运行项目
