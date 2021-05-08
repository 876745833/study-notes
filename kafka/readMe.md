# KAFKA

## docker安装组件

### 安装zookeeper

```
docker run -d --name zookeeper -p 2181:2181  wurstmeister/zookeeper
```

### 安装kafka

（注意后边ip配置成docker所在服务器ip）

```
docker run -d --name kafka -p 9092:9092 --link zookeeper -e KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 -e KAFKA_ADVERTISED_HOST_NAME=192.168.10.105 --env KAFKA_ADVERTISED_PORT=9092 wurstmeister/kafka
```

## 2、测试

调用controller层的接口发送请求
localhost:8080/kafka/send



![image-20210426223235023](https://gitee.com/zhuzhuoneday/images/raw/master/img/image-20210426223235023.png)