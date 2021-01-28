# pethub-back
## 代码规范
 1、controller层上行参数必须为vo类型对象
 
 2、service层上行参数必须为dto类型对象
 
 3、dao层上下行参数必须为po类型对象、po为实体与数据库映射类
 
 4、dto、vo、po转换通过mapstruct定义接口自动转换，不允许手写转换