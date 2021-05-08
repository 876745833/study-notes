# springboot + elasticsearch



## docker安装组件

```centos7
#安装elasticsearch
docker run --name elasticsearch -d -e ES_JAVA_OPTS="-Xms512m -Xmx512m" -e "discovery.type=single-node" -p 9200:9200 -p 9300:9300 elasticsearch:7.6.0

#安装kibana
docker run --name kibana -d --link elasticsearch:es -p 5601:5601 kibana:7.6.0

#安装head

#安装ik分词器
docker exec -it elasticsearch bash
cd /user/elasticsearch/plugins
mkdir ik
cd ik
#下载ik分词器
wget https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v7.6.0/elasticsearch-analysis-ik-7.6.0.zip
#解压
unzip elasticsearch-analysis-ik-7.6.0.zip

exit
#重启docker
docker restart elasticsearch#重启docker



```

