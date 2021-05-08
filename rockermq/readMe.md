

# rocketMQ

## docker安装

### 安装server

docker run -d -p 9876:9876 --name rmqserver  foxiswho/rocketmq:server-4.5.1

### 安装broker

docker run -d -p 10911:10911 -p 10909:10909\
 --name rmqbroker --link rmqserver:namesrv\
 -e "NAMESRV_ADDR=namesrv:9876" -e "JAVA_OPTS=-Duser.home=/opt"\
 -e "JAVA_OPT_EXT=-server -Xms128m -Xmx128m"\
 foxiswho/rocketmq:broker-4.5.1

### broker需要做配置，否则无法使用
1、进入容器
docker exec -it rmqbroker bash
2、修改配置
vi /etc/rocketmq/broker.conf
3、修改当前ip
在配置最下边增加配置：
brokerIP1 = docker所在容器ip

![image-20210426222541795](https://gitee.com/zhuzhuoneday/images/raw/master/img/image-20210426222541795.png)

### 安装视图工具

docker run -p -d --name rmqconsole -p 8180:8080 --link rmqserver:namesrv\
 -e "JAVA_OPTS=-Drocketmq.namesrv.addr=namesrv:9876\
 -Dcom.rocketmq.sendMessageWithVIPChannel=false"\
 -t styletang/rocketmq-console-ng

访问地址
本机ip:8180

![image-20210426222752137](https://gitee.com/zhuzhuoneday/images/raw/master/img/image-20210426222752137.png)

创建topic

![image-20210426222706789](https://gitee.com/zhuzhuoneday/images/raw/master/img/image-20210426222706789.png)



## 测试

本地启动项目，访问地址

 http://localhost:8080/message

打印日志

![image-20210426222859101](https://gitee.com/zhuzhuoneday/images/raw/master/img/image-20210426222859101.png)