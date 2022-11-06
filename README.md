<!-- TOC -->

- [CI](#ci)
- [RUN](#run)
- [INFRA](#infra)
  - [alibaba-sentinel](#alibaba-sentinel)
  - [dubbo](#dubbo)
  - [hbase](#hbase)
  - [mysql](#mysql)
  - [zipkin](#zipkin)
  - [zookeeper](#zookeeper)
- [API](#api)
  - [gateway](#gateway)
  - [cms](#cms)
- [Send http2-prior-knowledge request](#send-http2-prior-knowledge-request)

<!-- TOC -->

# CI

| CI            | Platform | Stauts                                      |
| ------------- | -------- | ------------------------------------------- |
| GitHub Action | Docker   | [![GitHub-Actions-Img]][GitHub-Actions-Url] |

# RUN

```bash
# 创建网络
docker network create example-spring-network
```

```bash
# 启动基础设施服务
docker-compose -f infra/docker-compose.yml up -d --build
```

```bash
# 编译项目
./mvnw clean test package 
```

```bash
# 启动业务服务
docker-compose up -d --build
```

# INFRA

## alibaba-sentinel

alibaba-sentinel : <http://192.168.2.201:10088>

username : sentinel  
password : sentinel

```
ab -c 10 -n 20000 -k http://192.168.2.201:30001/v1/blog?userId=123
```

## dubbo

dubbo-admin : <http://192.168.2.201:18080>

username : root  
password : 1234

## hbase

master : <http://192.168.2.201:16010>

region : <http://192.168.2.201:16030>

```sh

docker exec -it infra-hbase bash

./bin/hbase shell

# 建表
create 'blog',{CONFIGURATION => {'hbase.regionserver.region.split_restriction.type' => 'KeyPrefix','hbase.regionserver.region.split_restriction.prefix_length' => '1'}},{NAME=>'cf'},{SPLITS=>['0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f']}

# 查看表信息
describe 'blog'

# 
scan 'blog'

# 
describe 'hbase:meta'
```

```shell
hbase:002:0> describe 'blog'
Table blog is ENABLED
blog, {TABLE_ATTRIBUTES => {METADATA => {'hbase.regionserver.region.split_restriction.prefix_length' => '1', 'hbase.regi
onserver.region.split_restriction.type' => 'KeyPrefix'}}}
COLUMN FAMILIES DESCRIPTION
{NAME => 'cf', BLOOMFILTER => 'ROW', IN_MEMORY => 'false', VERSIONS => '1', KEEP_DELETED_CELLS => 'FALSE', DATA_BLOCK_EN
CODING => 'NONE', COMPRESSION => 'NONE', TTL => 'FOREVER', MIN_VERSIONS => '0', BLOCKCACHE => 'true', BLOCKSIZE => '6553
6', REPLICATION_SCOPE => '0'}

1 row(s)
Quota is disabled
Took 0.1503 seconds
```

doc: <https://linianhui.github.io/hbase/split-policy/>

## mysql

mysql-admin : <http://192.168.2.201:13306>

server : mysql.test  
username : root  
password : 1234

## zipkin

zipkin: <http://192.168.2.201:19411>

## zookeeper

zookeeper : <http://192.168.2.201:28080/commands>

```sh
./zkCli.sh
ls /
```

# API

[API](api.http)

## gateway

gateway: <http://192.168.2.201:30001>

gateway-start-count: <http://192.168.2.201:30001/.app/start-count>

gateway-actuator: <http://192.168.2.201:30001/.actuator>

gateway-startup: <http://192.168.2.201:30001/.actuator/startup>

gateway-doc: <http://192.168.2.201:30001/.doc/index.html>

## cms

cms: <http://192.168.2.201:30002>

cms-actuator: <http://192.168.2.201:30002/.actuator>

cms-doc: <http://192.168.2.201:30002/.doc/index.html>

# Send http2-prior-knowledge request

```sh
curl -s --http2-prior-knowledge 'http://192.168.2.201:30001'

# or
docker exec -t example-spring-gateway sh -c 'curl -s --http2-prior-knowledge http://gateway.example | jq'
```

输出:

```json
{
  "gateway": {
    "request": {
      "class": "io.undertow.servlet.spec.HttpServletRequestImpl",
      "url": "/",
      "protocol": "HTTP/2.0",
      "headers": {
        "Host": "gateway.example",
        "accept": "*/*",
        "user-agent": "curl/7.64.0"
      }
    },
    "example_properties": {
      "a": "a form application.yml",
      "b": "b form application.yml",
      "c": "java hard code",
      "d": "java hard code"
    }
  },
  "cms": {
    "request": {
      "class": "com.sun.proxy.$Proxy121",
      "url": "/",
      "protocol": "HTTP/2.0",
      "headers": {
        "x-b3-parentspanid": "2758599296977bb4",
        "x-b3-traceid": "d1130797edc07475",
        "x-b3-spanid": "1f9fd9b90f611280",
        "x-b3-sampled": "1",
        "Host": "cms-web.example",
        "accept-encoding": "gzip",
        "accept": "*/*",
        "user-agent": "okhttp/3.14.9"
      }
    },
    "example_properties": {
      "a": "a form application.yml",
      "b": "b form application.yml",
      "c": "java hard code",
      "d": "java hard code"
    }
  }
}
```

[GitHub-Actions-Img]:https://github.com/linianhui/spring.example/workflows/test/badge.svg

[GitHub-Actions-Url]:https://github.com/linianhui/spring.example/actions
