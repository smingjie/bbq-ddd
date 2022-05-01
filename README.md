# bbq-ddd (DDD编码实践)


springboot+ddd分层示例工程

## 代码目录结构

`eg.`后端Java代码工程为例:

```java
├─com.company.microservice
│    │ 
│    ├─apis   API接口层 
│    │    └─controller       控制器，对外提供（Restful）接口
│    │ 
│    ├─application           应用层
│    │    ├─model            数据传输对象模型及其装配器（含校验）
│    │    │    ├─assembler   装配器
│    │    │    └─dto         模型定义（含校验规则）
│    │    ├─assembler        装配器，实现模型转换eg. apiModel<=> domainModel        
│    │    ├─service          应用服务，非核心服务，跨领域的协作、复杂分页查询等
│    │    ├─task             任务定义，协调领域模型
│    │    ├─listener         事件监听定义
│    │    └─***              others
│    │ 
│    ├─domain   领域层
│    │    ├─common           模块0-公共代码抽取，限于领域层有效  
│    │    ├─module-xxx       模块1-xxx，领域划分的模块，可理解为子域划分     
│    │    ├─module-user      模块2-用户子域（领域划分的模块，可理解为子域划分）
│    │    │    ├─action      行为定义
│    │    │    │    ├─UserDomainService.java        领域服务,用户领域服务
│    │    │    │    ├─UserPermissionChecker.java    其他行为，用户权限检查器
│    │    │    │    ├─WhenUserCreatedEventPublisher.java     领域事件，当用户创建完成时的事件 
│    │    │    ├─model       领域聚合内模型 
│    │    │    │    ├─UserEntity.java                领域实体，有唯一标识的充血模型，如本身的CRUD操作在此处
│    │    │    │    ├─UserDictVObj.java              领域值对象，用户字典kv定义       
│    │    │    |    ├─UserDPO.java                   领域负载对象    
│    │    │    ├─repostiory  领域仓储接口
│    │    │    │    ├─UserRepository.java
│    │    │    ├─reference   领域适配接口
│    │    │    │    ├─UserEmailSenderFacade.java
│    │    │    └─factory     领域工厂  
│    │ 
│    ├─infrastructure  基础设施层
│    │    ├─persistence      持久化机制
│    │    │    ├─converter   持久化模型转换器
│    │    │    ├─po          持久化对象定义 
│    │    │    └─repository.impl  仓储类，持久化接口&实现，可与ORM映射框架结合
│    │    ├─general          通用技术支持，向其他层输出通用服务
│    │    │    ├─config      配置类
│    │    │    ├─toolkit     工具类  
│    │    │    ├─extension   扩展定义  
│    │    │    └─common      基础公共模块等 
│    │    ├─reference        引用层，扩展外部接口包装引用，防止穿插到Domain层腐化领域模型等
│    │    │    ├─dto         传输模型定义
│    │    │    ├─converter   传输模型转换器       
│    │    │    └─facade.impl 适配器具体实现，此处的RPC、Http等调用
│    │ 
│    └─resources  
│        ├─statics  静态资源
│        ├─template 系统页面 
│        └─application.yml   全局配置文件
```

## 目录层级描述说明

- `apis`接口层

  该层在代码结构中为对外暴露接口的最上层，与四层架构中的`表现层`相对应

- `reference`引用层

  扩展引入，为了解耦系统外部引用接口，类似防腐层设计。

  - 举个栗子A：假设本服务A购买会员，系统要调用支付pay服务B，并调用通知服务C来发送通知。

    （1）调用B支付服务接口和C通知服务接口应当在reference层进行一个适配封装；（2）定义领域事件来触发reference层中对B和C服务适配的接口；（3）最后是我们的主要业务逻辑：A服务需要结合B和C的接口，在完成购买会员一系列操作之后（比如购买会员后可能要续会员时长和积分等等）其中可能会涉及到分布式事务如何处理，本文不赘述。。。

  - 举个栗子B：假设有一个旧系统改造或者对接任务。

    对于旧系统内部的实现不想做过多的涉猎情况下，可以只关注其接口，针对新系统可以通过reference层进行接口适配，即门面模式，基于此可以防止旧系统腐化新系统的领域模型。

- `domain`领域层，系统核心业务逻辑，主要是DDD战术设计篇关注的领域对象类型

  一个问题：为何Repository接口分析要归属在领域模型分析中？

  我们系统数据不可能一直内存中持有，内存空间不是无限的（如果内存空间无限大，那我们完全不用考虑存储持久化，当然是个伪命题），不可避免的要进行转移到一些数据存储介质中（数据库，磁盘文件等），所以我们在分析领域模型中，仓储接口也应当算到我们的领域模型内，DDD划分的是业务维度，所以这里也只分析了接口，对其存储持久化实现并不关注。

## 扩展定义注解和接口声明

在使用DDD中自定义了标记的注解( `@DDDAnnotation`)和其衍生子注解，分别是

`@DomainAggregate`,

`@DomainAggregateRoot`,

`@DomainEntity`,

`@DomainValueObject`,

`@DomainService`,

`ApplicationService`,

`DomainRepository`,

`@DomainEvent`,

`@DomainAssembler`

等注解,详见代码的reference.general.extension.ddd.annotation.**。

其中有些注解继承了spring的 `@Component`,将会自动注册为spring bean，有些注解为了标记；

引入了 Assembler装配器，通过组合模式解耦继承关系，在api层和持久化层都有Assembler相应的实现。

## 领域模型注入仓储类的问题

区别于传统的分层后，在domain中更多关注业务逻辑，考虑到要与spring框架集成，需要注意一个领域模型中注入仓储类的问题。

> 在传统分层中，controller，service，repository均注册为spring管理的bean，
> 但是在domain层中，service一部分的业务逻辑划分到了具体的领域对象中去实现了，显然这些对象却不能注册为单例bean，
> 因此在此处不能沿用与原来分层结构中service层中通过`@Autowired` or `@Resource`等注入仓储接口

**关于这个问题，此处建议使用ApplicationContext实现**

> 即通过一个工具类 `ApplicationContextUtils` 实现 `ApplicationContextAware`获取bean的方法，即 `getBean()`方法，
> 然后我们就可以在我们的领域模型中直接应用该工具类来获取Spring托管的singleton对象，即`xxxRepo=ApplicationContextUtils.getBean(“xxxRepository”)`

```java
@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    public static ApplicationContext appctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtil.appctx=applicationContext;
    } 

    /**
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return appctx;
    }

    /**
     * 获取对象
     *
     * @param name spring配置文件中配置的bean名或注解的名称
     * @return 一个以所给名字注册的bean的实例
     * @throws BeansException 抛出spring异常
     */ 
    public static <T> T getBean(String name) throws BeansException {
        return (T) appctx.getBean(name);
    }

    /**
     * 获取类型为requiredType的对象
     *
     * @param clazz 需要获取的bean的类型
     * @return 该类型的一个在ioc容器中的bean
     * @throws BeansException 抛出spring异常
     */
    public static <T> T getBean(Class<T> clazz) throws BeansException {
        return appctx.getBean(clazz);
    }

    /**
     * 如果ioc容器中包含一个与所给名称匹配的bean定义，则返回true否则返回false
     *
     * @param name ioc容器中注册的bean名称
     * @return 存在返回true否则返回false
     */
    public static boolean containsBean(String name) {
        return appctx.containsBean(name);
    }
} 
```

考虑到代码结构简洁性，还可以封装一层仓储工厂 `RepoFactory`，在领域层（理解为一个获取仓储实现Bean的工具类即可），用来从spring的容器中获取对应的仓储实现Bean，方法如下所示

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

- 可指定具体实现类来获取，则直接指定实现类为选择的实现
- 若不指定具体实现类，选取spring容器中最先注册的一个Bean实现

然后在领域模型中就可以直接调用该工厂方法来获取仓储接口的实现，比如`DictRepo`为定义的仓储接口，`DictDao`为该接口的准实现类

```java
//直接指定实现
DictRepo repo= RepoFactory.get(DictDao.class);
//不指定实现取Spring容器中默认注册第1位的Bean
DictRepo repo= RepoFactory.get(DictRepo.class);
```

## 补充子域分析 

本例程提供单表和多表情况下的的三个子域来进行编程实践的，分别是

- 数据字典子域`dict`
- 用户角色权限通用子域 `rbac`
- 工作流设计`flow`

例程已完成，详见代码

