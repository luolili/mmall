分支开发，主干发布

1. git的命令
- git branch , git branch -r : 查看分支
- git checkout -b v1.0 mmall/master:开分支：v1.0

- git push mmall HEAD -u : push代码
- git status : 文件的变更change
- git add .项目的所有文件添加到缓存中
- git commit -am 'project init'
- git log : 看提交记录
下载：idea plugin : mybatis plugin

fe：chrome插件； restlet client/postman

2.MappingJackson2HttpMessageConverter:spring5在配置的时候，
会出现faster jackson的类找不到，
加入相关依赖，重启。

3. dao--pojo ; service-- bo:business object ;
 web--vo:value 
 
 4.mybatis  There is no getter for property named 'userId' 
 in 'class java.lang.Integer
 
 解决方法：最好不要在xml里面进行if判断。
 
 5. column id cannot be null:数据库设置主键自增。
 
 6.maven 环境隔离：
 多个开发环境的分离：dev, beta,test,product:线上；
 
 差异：数据库配置；服务器.
 
 配置step:
 
 - 在build节点里面，新建resources节点
 - 在build 同级，新建profiles节点
 - 为不同的化境，新建不同的配置文件目录。
 - 先取消灰色选中的dev,development,然后点击dev.
 - 环境隔离的编译打包：
 mvn clean package -Dmaven.test.skip=true -Pdev
 
 - idea 右侧所选的是发布到tomcat里面的环境
 
 7. tomcat 集群/单机部署多应用/多机部署多应用
 
 8.在github 上下载win redis 解压运行：redis-server.exe;
 运行命令行：redis_cli.exe,以admin身份运行
 
 redis.config:
 
 -  port : 端口
 - requirepass : 密码
 - masterauth: 主从之间的密码
 
 命令：
 - info : 如keyspace:db0
 - dbsize
 - flushdb/flushall
 - save : 手动的保存
 - quit ： 退出cli
 
 - set a b: put
 - get a : get
 - del a : delete
 - exists a
 - expire a 20 : 设置a的过期时间为20s
 - ttl :剩余时间
 - type a : a的类型：hset city 2 cd
 - randomkey
 - rename oldKeyName newKeyName
 