# bbq-ddd

springboot+ddd分层示例工程

## 代码目录结构

`eg.`后端Java代码工程为例:

```java
├─com.company.microservice
│    │ 
│    ├─apis   API接口层
│    │    ├─model     视图模型,数据模型定义 vo/dto（大多数情況是一样的）
│    │    ├─assembler    装配器，实现模型转换eg. apiModel<=> domainModel
│    │    └─controller   控制器，对外提供（Restful）接口
│    │ 
│    ├─application   应用层
│    │    ├─service  应用服务，非核心服务
│    │    ├─task     任务定义，协调领域模型 
│    │    └─***      others
│    │ 
│    ├─domain   领域层
│    │    ├─common       公共代码抽取，限于领域层有效 
│    │    ├─model        领域模型 
│    │    │    ├─xxxVO.java     值对象
│    │    │    ├─xxxEntity.java 实体类，充血的领域模型
│    │    │    └─xxxAgg.java    聚合类，通常表现为实体的聚合，需要有聚合根
│    │    ├─service      领域服务类，一些不能归属某个具体领域模型的行为
│    │    ├─repository   仓储接口
│    │    └─factory      工厂类，负责复杂领域对象创建，封装细节 
│    │ 
│    ├─infrastructure  基础设施层
│    │    ├─persistent   持久化机制
│    │    │    ├─po           持久化对象 
│    │    │    └─repository   仓储类，持久化接口&实现，可与ORM映射框架结合
│    │    ├─general      通用技术支持，向其他层输出通用服务
│    │    │    ├─config       配置类
│    │    │    ├─toolkit      工具类  
│    │    │    └─common       基础公共模块等
│    │ 
│    └─resources  
│        ├─statics  静态资源
│        ├─template 系统页面 
│        └─application.yml   全局配置文件
```

* `表现层`在此代码结构中表现为`api层`，对外暴露接口的最上层

* 注意在领域模型中如何调用仓储接口，在编程实践中已经完全取代`@Autowired`和`@Resource`注入方式。引入了`RepoFactory`在领域层，用来从spring的容器中获取对应的仓储实现Bean，如下所示

  ```java
  	public static <T> T get(Class<? extends T> tClass) {
  
  		Map<String, ? extends T> map = ApplicationUtils.getApplicationContext().getBeansOfType(tClass);
  		Collection<? extends T> collection = map.values();
  		if (collection.isEmpty()) {
  			throw new PersistException("未找到仓储接口或其指定的实现:" + tClass.getSimpleName() );
  		}
  		return collection.stream().findFirst().get();
  	}
  ```

  * 可指定具体实现类来获取，则直接指定实现类为选择的实现
  * 若不指定具体实现类，选取spring容器中最先注册的一个Bean实现

  

------

## 补充

本例程提供单表和多表情况下的的三个子域来进行编程实践的，分别是

* 数据字典子域`dict`
* 用户角色权限通用子域 `user-role-menu`
* 工作流配置设计`flow-config`

