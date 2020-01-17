MyBatis中当实体类中的属性名和表中的字段不一致
========

#### 解决方法

1. 写sql语句时起别名

2. 在MyBatis的全局配置文件中开启驼峰命名规则
- 只能支持dept_id -> deptId 这种形式

3. 在Mapper映射文件中使用resultMap来自定义映射规则
