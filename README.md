<!-- TOC -->
- [CI](#ci)
- [准备镜像(本地镜像服务)](#准备镜像本地镜像服务)
- [添加namespace](#添加namespace)
- [部署zipkin](#部署zipkin)
- [部署service1](#部署service1)
- [部署service2](#部署service2)
<!-- TOC -->

# CI

| CI            | Platform | Stauts                                      |
| ------------- | -------- | ------------------------------------------- |
| GitHub Action | Docker   | [![GitHub-Actions-Img]][GitHub-Actions-Url] |

# 准备镜像(本地镜像服务)

registry.test: <http://registry.test/v2/_catalog>
> registry.test 部署方法 : https://github.com/linianhui/docker/tree/master/app

```bash
./mvnw package

docker build --tag registry.test/spring-example-service1:v1 spring-example-service1
docker push registry.test/spring-example-service1:v1

docker build --tag registry.test/spring-example-service2:v1 spring-example-service2
docker push registry.test/spring-example-service2:v1
```

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

service1-actuator: <http://192.168.2.212:30001/actuator>

```bash
kubectl apply --filename k8s/service1.yml
```

# 部署service2

service2: <http://192.168.2.212:30002>

service2-actuator: <http://192.168.2.212:30002/actuator>

```bash
kubectl apply --filename k8s/service2.yml
```

[GitHub-Actions-Img]:https://github.com/linianhui/spring.example/workflows/test/badge.svg
[GitHub-Actions-Url]:https://github.com/linianhui/spring.example/actions
