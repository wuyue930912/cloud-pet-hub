# 后台代码

### common 公共依赖
### eureka 服务注册中心
### gateway 网关服务 ribbon
### maintain 后台接口服务
### websocket 消息推送服务

#### 启动顺序 -> eureka -> gateway -> maintain(多实例) -> websocket(多实例)

----------------

### 代码规范
 1、controller层上行参数必须为vo类型对象
 
 2、service层上行参数必须为dto类型对象
 
 3、dao层上下行参数必须为po类型对象、po为实体与数据库映射类
 
 4、dto、vo、po转换通过mapstruct定义接口自动转换，不允许手写转换
 
 5、@Async注解禁止使用默认线程池
 
 6、圈复杂度不能超过5
 
 7、不允许使用魔法数

 8、public方法必须加javadoc注释

 ----------------

### 包说明
 
 1、annotation 自定义注解
 
 2、aop 切面
 
 3、config 配置类
 
 4、constant 静态常量
 
 5、convert 转换接口
 
 6、dao 操作数据库
 
 7、dto 数据传输对象
 
 8、event 事件
 
 9、listener 事件监听
 
 10、po 实体
 
 11、utils 工具包
 
 12、vo 视图对象
 
 13、controller 入口
 
 14、service 业务
 
 15、scheduling 定时器

 ----------------

### 配置
 父pom配置profile指定要加载的application配置文件