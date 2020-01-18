redis 在项目中的使用场景
========

#### 数据类型和使用场景
   
- String
  - 比如说，我想知道什么时候封锁一个IP地址，incrby命令

- Hash
  - 存储用户信息[id, name, age]
  - hset(key, field, value)
  - hset(userKey, id, 101)
  - hset(userKey, name, admin)
  - hset(userKey, age, 23)

修改案例

hget(userKey, id)

hset(userKey, id, 101)

为什么不用String类型类存储

Set(userKey, 用户信息字符串)

Get(userkey)

用户可能只修改几个字段，Get, Set对IO开销比较大

- List
  - 实现最新消息的排行，还可以利用List的push命令，将任务存在list集合中，
同时使用另一个命令pop，将任务从集合中取出

  - Redis的List数据类型可以模拟消息队列，电商中的秒杀就可以采用这种方式来完成一个秒杀活动

- Set
  - 特殊之处：可以自动去重
  - 比如说微博中需要将每个人的好友存在集合Set中，这样求两个人的共同好友操作，只需要求交集即可

- Zet
  - 以某一个条件为权重，进行排序
  - 京东：商品详情的时候，都会有一个综合排序，还可以按照价格进行排序
