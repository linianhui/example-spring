<!-- TOC -->
- [CI](#ci)
- [准备本地镜像服务](#准备本地镜像服务)
- [添加namespace](#添加namespace)
- [部署zipkin](#部署zipkin)
- [部署service](#部署service)
  - [部署service1](#部署service1)
  - [部署service2](#部署service2)
- [Send http2-prior-knowledge request](#send-http2-prior-knowledge-request)
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

zipkin: <http://127.0.0.1:39411>

```bash
kubectl apply --filename k8s/zipkin.yml

kubectl port-forward service/zipkin -n spring-example 39411:9411
```

# 部署service

编译jar
```bash
./mvnw package
```

添加供spring-kubenetes访问API-Server的权限。
```bash
kubectl apply --filename k8s/rbac.yml
```

## 部署service1

service1: <http://127.0.0.1:30001>

service1-actuator: <http://127.0.0.1:30001/.actuator>

service1-doc: <http://127.0.0.1:30001/.doc/index.html>

```bash
./mvnw dockerfile:build dockerfile:push --projects service1

kubectl apply --filename k8s/service1.yml

kubectl port-forward service/service1 -n spring-example 30001:80 35005:5005
```

## 部署service2

service2: <http://127.0.0.1:30002>

service2-actuator: <http://127.0.0.1:30002/.actuator>

service2-doc: <http://127.0.0.1:30002/.doc/index.html>

```bash
./mvnw dockerfile:build dockerfile:push --projects service2

kubectl apply --filename k8s/service2.yml

kubectl port-forward service/service2 -n spring-example 30002:80 35006:5005
```


# Send http2-prior-knowledge request

```sh
kubectl run tool --namespace spring-example --generator=run-pod/v1 --image=lnhcode/tool --restart=Never --stdin --tty --command --rm -- sh -c 'curl -s --http2-prior-knowledge http://service1.spring-example | jq'
```

输出:
```json
{
  "service1": {
    "request": {
      "protocol": "HTTP/2.0"
    },
    "example_properties": {
      "a": "service1 form k8s configMap application.yml",
      "b": "b form application.yml",
      "c": "java hard code",
      "d": "java hard code"
    }
  },
  "service2": {
    "request": {
      "protocol": "HTTP/1.1"
    },
    "example_properties": {
      "a": "service2 form k8s configMap application.yml",
      "b": "b form application.yml",
      "c": "java hard code",
      "d": "java hard code"
    }
  }
}
```

[GitHub-Actions-Img]:https://github.com/linianhui/spring.example/workflows/test/badge.svg
[GitHub-Actions-Url]:https://github.com/linianhui/spring.example/actions
