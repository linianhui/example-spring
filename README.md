<!-- TOC -->
- [CI](#ci)
- [准备本地镜像服务](#准备本地镜像服务)
- [添加namespace](#添加namespace)
- [部署zipkin](#部署zipkin)
- [部署service](#部署service)
  - [部署gateway](#部署gateway)
  - [部署cms](#部署cms)
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

kubectl port-forward service/zipkin -n example 39411:9411
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

## 部署gateway

gateway: <http://127.0.0.1:30001>

gateway-actuator: <http://127.0.0.1:30001/.actuator>

gateway-doc: <http://127.0.0.1:30001/.doc/index.html>

```bash
./mvnw dockerfile:build dockerfile:push --projects gateway

kubectl apply --filename k8s/gateway.yml

kubectl port-forward service/gateway -n example 30001:80 35005:5005
```

## 部署cms

cms: <http://127.0.0.1:30002>

cms-actuator: <http://127.0.0.1:30002/.actuator>

cms-doc: <http://127.0.0.1:30002/.doc/index.html>

```bash
./mvnw dockerfile:build dockerfile:push --projects cms

kubectl apply --filename k8s/cms.yml

kubectl port-forward service/cms -n example 30002:80 35006:5005
```


# Send http2-prior-knowledge request

```sh
kubectl run tool --namespace example --generator=run-pod/v1 --image=lnhcode/tool --restart=Never --stdin --tty --command --rm -- sh -c 'curl -s --http2-prior-knowledge http://gateway.example | jq'
```

输出:
```json
{
  "gateway": {
    "request": {
      "protocol": "HTTP/2.0"
    },
    "example_properties": {
      "a": "gateway form k8s configMap application.yml",
      "b": "b form application.yml",
      "c": "java hard code",
      "d": "java hard code"
    }
  },
  "cms": {
    "request": {
      "protocol": "HTTP/1.1"
    },
    "example_properties": {
      "a": "cms form k8s configMap application.yml",
      "b": "b form application.yml",
      "c": "java hard code",
      "d": "java hard code"
    }
  }
}
```

[GitHub-Actions-Img]:https://github.com/linianhui/spring.example/workflows/test/badge.svg
[GitHub-Actions-Url]:https://github.com/linianhui/spring.example/actions
