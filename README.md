一、项目配置
- 开发环境配置文件路径为 src/main/resources/application.properties,部署环境路径为config/application.properties

1、修改端口：server.port

2、修改系统上下文：server.servlet.context-path

二、项目启动

1、Linux OS使用start.sh

2、Windows OS使用start.bat

三、相关接口
- 根据Token获取用户信息（GET  JSON）： http://localhost:8081/api/v1/getUserInfoByToken?token=**
- 根据Token获取用户信息（POST JSON）： http://localhost:8081/api/v1/getUserInfoByToken  body:  {"token":"***"}
