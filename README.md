<!-- TOC -->
- [CI](#ci)
- [RUN](#run)
  - [api](#api)
  - [zipkin](#zipkin)
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
./mvnw test package 

docker-compose up -d --build
```

## api
[API](api.http)

## zipkin

zipkin: <http://192.168.2.201:9411>

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
