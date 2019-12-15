# 准备镜像(本地镜像服务)

API: <http://registry.test/v2/_catalog>

```bash
./mvnw package
docker build --tag registry.test/api:v1 .
docker push registry.test/api:v1
```

# 部署api服务

API: <http://192.168.2.212:30001>

```bash
kubectl apply --filename k8s/api.config.yml --filename k8s/api.deployment.yml  --filename k8s/api.service.yml
```

# 更新ConfigMap

```bash
kubectl apply --filename k8s/api.config.yml
```
