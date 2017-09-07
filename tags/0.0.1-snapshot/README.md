#项目说明
本项目为一个无人机飞行计划和空域申请系统，包括三个子系统:
- 系统门户
- 微信公众号
- 后台管理

##项目目录结构
- uavportal
  - uav-core 核心功能通用依赖包
  - auth 通用权限组件
  - auth 文档
  - uav-console 后台管理系统
  - uav-weixin 微信公众号服务
  - uav-web 门户
  - pom.xml Maven项目构建文件

#项目技术架构
##后端
- **主框架**
  Spring Boot
  
- **展现层**
  thymeleaf  
  
- **持久层**
  Hibernate
  
- **数据库**
  MySql 5+
  
- **构建器**
  Maven
  
##前端
- **主框架**
      前端：VUE.js
      后台：EXT.js

- **构建器**


- **其他组件**

  
#项目构建
##后端
- **外部jar**
  由于maven无法导入一些私有的jar包，需要手工导入到 maven仓库中，以下是一些需要手工导入的包：
mvn install:install-file -Dfile={jar包根路径}/lib/alidayu/taobao-sdk-java-auto_1455552377940-20170623.jar -DgroupId=alidayu -DartifactId=alidayu -Dversion=1.0 -Dpackaging=jar

- **安装**
  ```mvn eclipse:eclipse```
  
- **发布**
  ```mvn package```
  *如果想去掉单元测试，在后面加上-Dmaven.test.skip=true*
  
##前端
- **安装**
1. 使用shell脚本，进入src/main/webapp目录
2. 输入命令
```npm install cnpm -g --registry=https://registry.npm.taobao.org```， 
```cnpm install```

- **本地测试**
```npm run dev```

- **发布**
```npm run build```

#项目参考文档
- Spring boot
https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/
- jpa
https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
- hibernate
http://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html

#注意事项
- 不要上传.project .classpath target等IDE和编译生成的文件
- 如果要测试某一个类,dao,service，请在test目录内建相关的单元测试类，尽量不要在主类代码中加入测试代码
