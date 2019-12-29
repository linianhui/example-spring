# 准备镜像(本地镜像服务)

api: <http://registry.test/v2/_catalog>

```bash
./mvnw package
docker build --tag registry.test/spring-example-service1:v1 .
docker push registry.test/spring-example-service1:v1
```

# 部署api服务

service1: <http://192.168.2.212:30001>

service1-actuator: <http://192.168.2.212:30001/actuator>

```bash
kubectl apply --filename k8s/service1.yml
```
