# bean-copier-better
##CGLIB bean-copier class生成修改版：
```
1.支持非标准setter使用(返回值不是void)
2.修改context为属性名
3.重新定义Convert接口,暴露target对象的属性值
```



# 待更新
```text
@TODO
由于jdk标准库不支持非void setter方法识别，目前暂时用spring-beans内部方法 
public static PropertyDescriptor[] getPropertyDescriptors(Class<?> clazz)
后续计划去除spring-beans依赖,手动获取bean property
```

