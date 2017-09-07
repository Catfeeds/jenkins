# 无人机服务微信平台

### 使用技术

* 使用技术：[react](https://facebook.github.io/react/docs/hello-world.html)
* UI框架：[react-weui](https://github.com/weui/react-weui)
* 打包框架：[webpack](https://webpack.js.org/)

### 编译
`npm run build`

### 调试
`npm start`

### 环境配置
* `webapp/config`有两个配置文件`development.js`和`production.js`，里面有如下字段
    * serverPath 服务器域名（获取微信ID回跳使用）
    * ajaxPath 后台接口域名（访问后台接口使用）
    * basePath 项目名称（获取微信ID回跳使用）
    * appid 微信开发者ID（获取微信ID回跳使用）

### 注意事项
1. 在部署项目的时候，`MP_verify_4N2nSVmaDilf7Qy6.txt`在项目根目录必须能够访问到
2. 因为服务器的特殊性，`/build/chunkes`文件需要移动到项目根目录，测试成功或者不成功的方法是访问使用调用了二维码页面（如：加群交流页面）
3. 在本地调试的时候，需要注意两点：
	1. `dev-server-webpack`的代理选项需要匹配拦截，把匹配的URL通过代理服务器转发。但是由于后端API项目没有项目名，所以就没有固定的匹配选项，这样将无法通过代理服务器转发，会导致跨域。解决方法是让后端人员配合添加项目名，然后在`webpack.config.dev.js -> devServer -> proxy`添加匹配选项`'/项目名':{ target : "http://192.168.x.xxx:8080" }`[参考链接](https://webpack.js.org/configuration/dev-server/#devserver-proxy)
	2. 在本地调试的时候很多接口都需要登录，所有会跳转自动登录。这时候需要后台人员注释登录拦截，还有一种方法就是修改微信公众平台`设置 -> 公众号测试 -> 功能设置 -> 网页授权域名`修改成你的调试域名，且域名下必须能访问`MP_verify_4N2nSVmaDilf7Qy6.txt`[参考链接](https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140842)

### 其他
[微信公众平台](https://mp.weixin.qq.com/)
账号：`csr@jeatcs.com`
密码：`Jeatcs82258167`
