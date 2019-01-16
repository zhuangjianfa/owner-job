# job-demo
一.引用job核心包
<dependency>
	<groupId>com.dtxd</groupId>
	<artifactId>dtxd-job</artifactId>
	<version>0.0.1</version>
</dependency>
二.普通需要使用定时任务的类继承SimpleJobAbstract添加注解@ElasticSimpleJob
三.数据流式定时任务继承DataflowJobAbstract,添加注解@ElasticDataflowJob，fetchData抓取数据，processData处理数据
注解对象值说明：
corn:配置定时任务执行时间表达式
disabled:是否启动时禁止启动
shardingTotalCount：分片数
shardingItemParameters：分片参数
overwrite：每次启动是否覆盖配置
streamingProcess：是否流式线性执行,为ElasticDataflowJob特有
四.配置：
4.1 数据库配置,用于保存JOB工作状态轨迹与执行历史记录
#数据库驱动
event.trace.rdb.driver: com.mysql.jdbc.Driver
#数据库连接
event.trace.rdb.url: jdbc:mysql://localhost:3306/dtxd_job?characterEncoding=utf-8&autoReconnect=true&allowMultiQueries=true&useSSL=true
#用户名
event.trace.rdb.username: root
#密码
event.trace.rdb.password: root
4.2 job配置
#ZK配置中心
elastic.job.serverList: 127.0.0.1:2181
#job在zk下的命名空间
elastic.job.namespace:  job-demo

五.为确保定时任务的执行，所有的定时任务需要在控制台启动才能运行，本地开发时，可独自运行elastic-job-lite-console,zk可以配置为本地ZK
开发环境：192.168.1.200::2181
测试环境：zk-test.doupaiapp.com