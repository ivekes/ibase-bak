# mybatis 总纲
## jdbc存在的问题
1. 硬编码
2. 连接管理
3. 代码重复
4. 提供api



## 核心对象
1. 配置类
2. 执行器
3. 应用层api
4. 代理对象

# mybatis 面试题
## mybatis 解决了什么问题
1. 资源管理问题（数据库的连接和释放）
2. 结果集映射问题（jdbc需遍历）
3. 参数解析问题（动态传参）
4. sql语句跟代码的耦合问题（实现了sql的分离）

## mybatis 编程式开发中的核心对象及其作用？
1. SqlSessionFactoryBuilder
	- 作用
	通过XMLConfigBuilder对象，解析配置文件，构建SqlSessionFactory
	- 生命周期和作用域
	局部变量，用过即丢
2. SqlSessionFactory
	- 作用
	创建 SqlSession 实例的工厂，可以通过 SqlSessionFactory 提供的 openSession() 方法来获取 SqlSession 实例
	- 生命周期和作用域
	SqlSessionFactory 对象一旦创建，就会在整个应用运行过程中始终存在。作用域：Application
3. SQLSession
	- 作用
	用于执行持久化操作的对象，类似于 JDBC 中的 Connection
	- 生命周期和作用域
	SqlSession 对应着一次数据库会话。作用域：session

## mybatis java 类型和数据库类型是怎么实现相互映射的？
通过各种 xxxTypeHandler实现映射,如果我们想要自己自定义一个 TypeHandler 可以实现 TypeHandler 接口，也可以继承 BaseTypeHandler 类

## mybatis simple、reuse、batch 三种执行器的区别？
simple 就是普通的执行器
reuse 执行器会重用预处理语句（prepared statements）
batch 执行器将重用语句并执行批量更新

## mybatis 一级缓存和二级缓存的区别？
缓存|作用域|默认开启|缓存策略
-|-
一级缓存|session|是|SqlSession执行了DML操作（增删改），会清除一级缓存
二级缓存|namespace|否| LRU 最近最少使用策略、FIFO先进先出策略、SOFT软引用策略、WEAK弱引用策略

二级缓存需配置开启，`<cache  eviction="LRU"  flushInterval="60000"  size="512"  readOnly="true"/>`默认策略是LRU，并每隔60秒刷新，最大存储512个对象，而且返回的对象被认为是只读的，evicition指定缓存策略。