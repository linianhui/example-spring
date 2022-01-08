```sh
# 建表
create 'blog',{NAME=>'cf'},{SPLITS=>['0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f']}

# 查看表信息
describe 'blog'

# 
describe 'hbase:meta'
```