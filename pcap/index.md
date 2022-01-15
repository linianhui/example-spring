# 抓包

```sh
docker exec -it example-spring_cms-rpc.example_1 bash

tcpdump tcp -w hbase-client.pcap

docker cp example-spring_cms-rpc.example_1:/app/hbase-client.pcap pcap/hbase-client.pcap
```