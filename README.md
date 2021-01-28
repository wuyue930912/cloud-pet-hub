# 后台代码
## 代码规范
 1、controller层上行参数必须为vo类型对象
 
 2、service层上行参数必须为dto类型对象
 
 3、dao层上下行参数必须为po类型对象、po为实体与数据库映射类
 
 4、dto、vo、po转换通过mapstruct定义接口自动转换，不允许手写转换
 

 ## 包说明
 
 1、annotation 自定义注解
 
 2、aop 切面
 
 3、config XSS拦截配置、Druid连接池配置
 
 4、constant 静态常量
 
 5、convert 转换接口
 
 6、dao 操作数据库
 
 7、dto 数据传输对象
 
 8、event 事件
 
 9、listener 事件监听
 
 10、po 持久化对象
 
 11、utils 工具包
 
 12、vo 视图对象
 
 13、controller 入口
 
 14、service 业务
 
 ##配置
 父pom配置profile指定要加载的application配置文件