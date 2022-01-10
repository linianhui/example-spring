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

# 启动基础设施服务
docker-compose -f infra/docker-compose.yml up -d --build

# 编译项目
./mvnw clean test package 

# 启动业务服务
docker-compose up -d --build
```

# INFRA


## alibaba-sentinel

alibaba-sentinel : <http://192.168.2.201:10088>

username : sentinel  
password : sentinel

## dubbo

dubbo-admin : <http://192.168.2.201:18080>

username : root  
password : 1234

## hbase

master : <http://192.168.2.201:16010>

region : <http://192.168.2.201:16030>

```sh
./hbase shell

# 建表
create 'blog',{NAME=>'cf'},{SPLITS=>['0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f']}

# 查看表信息
describe 'blog'

# 
describe 'hbase:meta'
```

## mysql

mysql-admin : <http://192.168.2.201:13306>

server : mysql.infra  
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

gateway-actuator: <http://192.168.2.201:30001/.actuator>

gateway-doc: <http://192.168.2.201:30001/.doc/index.html>

## cms

cms: <http://192.168.2.201:30002>

cms-actuator: <http://192.168.2.201:30002/.actuator>

cms-doc: <http://192.168.2.201:30002/.doc/index.html>

# Send http2-prior-knowledge request

```sh
curl -s --http2-prior-knowledge 'http://192.168.2.201:30001'

# or
docker exec -t example-spring_gateway.example_1 sh -c 'curl -s --http2-prior-knowledge http://gateway.example | jq'
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
