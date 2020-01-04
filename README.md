<!-- TOC -->
- [CI](#ci)
- [准备本地镜像服务](#准备本地镜像服务)
- [添加namespace](#添加namespace)
- [部署zipkin](#部署zipkin)
- [部署service1](#部署service1)
- [部署service2](#部署service2)
<!-- TOC -->

# CI

| CI            | Platform | Stauts                                      |
| ------------- | -------- | ------------------------------------------- |
| GitHub Action | Docker   | [![GitHub-Actions-Img]][GitHub-Actions-Url] |

# 准备本地镜像服务

registry.test: <http://registry.test/v2/_catalog>
> registry.test 部署方法 : https://github.com/linianhui/docker/tree/master/app

# 添加namespace

```bash
kubectl apply --filename k8s/namespace.yml
```

# 部署zipkin

zipkin: <http://192.168.2.212:30088>

```bash
kubectl apply --filename k8s/zipkin.yml
```

# 部署service1

service1: <http://192.168.2.212:30001>

service1-actuator: <http://192.168.2.212:30001/.actuator>

service1-doc: <http://192.168.2.212:30001/.doc/index.html>

```bash
./mvnw package dockerfile:build dockerfile:push --projects spring-example-service1

kubectl apply --filename k8s/service1.yml
```

# 部署service2

service2: <http://192.168.2.212:30002>

service2-actuator: <http://192.168.2.212:30002/.actuator>

service2-doc: <http://192.168.2.212:30002/.doc/index.html>

```bash
./mvnw package dockerfile:build dockerfile:push --projects spring-example-service2

kubectl apply --filename k8s/service2.yml
```

[GitHub-Actions-Img]:https://github.com/linianhui/spring.example/workflows/test/badge.svg
[GitHub-Actions-Url]:https://github.com/linianhui/spring.example/actions
