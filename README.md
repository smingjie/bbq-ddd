# bbq-ddd

[TOC]

# DDD编码实践

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
│    │    ├─events       领域事件，聚合之间通信通过领域事件来触发
│    │    ├─model        领域模型 
│    │    │    ├─dict    领域划分的模块，可理解为子域划分
│    │    │    │    ├─DictVo.java       领域值对象
│    │    │    │    ├─DictEntity.java   领域实体，充血的领域模型，如本身的CRUD操作在此处
│    │    │    │    ├─DictAgg.java      领域聚合，通常表现为实体的聚合，需要有聚合根
│    │    │    │    └─DictService.java  领域服务，不能归类到上述模型，如分页条件查询等可写在此处
│    │    │    ├─xxx
│    │    │    │    ├─xxxEntity.java      
│    │    │    │    ├─bbbAgg.java     
│    │    │    │    └─cccAgg.java        
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
│    ├─reference  引用层，扩展外部接口包装引用，防止穿插到Domain层腐化领域模型等 
│    │    ├─ 忽略技术实现，此处的RPC、Http等调用，Domain层一般通过DomainEvent关联 
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
- 用户角色权限通用子域 `user-role-menu`
- 工作流配置设计`flow-config`



# DDD分层详解

##  DDD分层与传统三层区别

根据DDD领域驱动设计原则，对应的软件架构也需要做出相应的调整。
我们常用的三层架构模型划分为表现层，业务逻辑层，数据访问层等，在`DDD`分层结构中既有联系又有区别，
个人认为主要有如下异同：

- 在架构设计上，在`DDD`分层结构中将传统三层架构的 `业务逻辑层` 拆解为 `应用层 ` 和 `领域层` 
  其中Application划分为很薄的一层服务，非核心的逻辑放到此层去实现，核心的业务逻辑表现下沉到 `领域层` 去实现，凝练为更为精确的业务规则集合，通过领域对象去阐述说明。

  ![ddd-layer-diff](https://img-blog.csdnimg.cn/20200110163924602.png)

- 在建模方式上，`DDD`分层的建模思维方式有别于传统三层
  传统三层通常是以数据库为起点进行数据库分析设计，而`DDD`则需要以业务领域模型为核心建模（即面向对象建模方式），更能体现对现实世界的抽象。
  故**在DDD分层凸显领域层的重要作用，领域层为系统的核心，包括所有的业务领域模型的抽象表达**。

- 在职责划分上，基础设施层涵盖了2方面内容

  - 持久化功能，其中原三层架构的数据访问层下沉到基础设施层的持久化机制实现

  - 通用技术支持，一些公共通用技术支持也放到基础设施层去实现。

## 四层架构图 &分层作用

![1601096616421](<https://img-blog.csdnimg.cn/20200110164305632.png>)

在该架构中，上层模块可以调用下层模块，反之不行。即

| 层            | 可被其调用的层                            |
| ------------- | ----------------------------------------- |
| `interface`   | —>`application`|`domain`|`infrastructure` |
| `application` | —>`domain`|`infrastructure` |
| `domain`      | —>`infrastructure`                        |

分层作用

| 分层                        | 描述                                                         |
| :-------------------------- | :----------------------------------------------------------- |
| 用户界面层`UI`              | 用户界面层，或者表现层，负责向用户显示解释用户命令           |
| 应用层`Application`         | 定义软件要完成的任务，并且指挥协调领域对象进行不同的操作。该层不包含业务领域知识。 |
| 领域层`Domain `             | 或称为模型层，系统的核心，负责表达业务概念，业务状态信息以及业务规则。即包含了该领域（问题域）所有复杂的业务知识抽象和规则定义。该层主要精力要放在领域对象分析上，可以从实体，值对象，聚合（聚合根），领域服务，领域事件，仓储，工厂等方面入手 |
| 基础设施层`Infrastructure ` | 主要有2方面内容，一是为领域模型提供持久化机制，当软件需要持久化能力时候才需要进行规划；一是对其他层提供通用的技术支持能力，如消息通信，通用工具，配置等的实现； |

## 领域对象

根据战术设计，关注的领域对象主要包括有

| 类型           | 英文           | 描述                                   |
| -------------- | -------------- | -------------------------------------- |
| 值对象         | `value object` | 无唯一标识的简单对象                   |
| 实体           | `entity`       | 充血的领域模型，有唯一标识             |
| 聚合（聚合根） | `aggregate`    | 实体的聚合，拥有聚合根，可为某一个实体 |
| 领域服务       | `service`      | 无法归类到某个具体领域模型的行为       |
| 领域事件       | `event`        | 领域或模块之间通信                     |
| 仓储           | `repository`   | 持久化相关，与基础设施层关联           |
| 工厂           | `factory`      | 负责复杂对象创建                       |
| 模块           | `module`       | 子模块引入，可以理解为子域划分         |



# think about …

除了这个经典四层架构模型，DDD还有五层架构、六边形架构等，所以这里抛出一个问题，

## 项目按上述经典四层架构进行搭建，可以说是DDD架构实践么？

关于这个问题，笔者想引入一对哲学概念，哲学有言形式与内容，现象与本质等辩证关系（当然与本文可能也没啥太大关系啦）；从这两个角度来阐述本人的观点：

- 形式与内容：经典四层架构是一个DDD实现的形式，相当于给我们提供了一个框框来让我们自己去实现；在这个框框里面我们怎么实现是自由发挥的，但也是有约束的，这个约束体现在DDD对每一层的作用的约定，如每个层约定做了什么功能，充当什么角色等。**尤其是对Domain层的约定，才是最重要的**。那么我们按照哲学辩证的套话来说，***形式上满足了DDD架构，但这应该是片面的，具体还要看内容，即具体实现是怎样的。***

- 现象与本质：接着上述观点，如果要看实现，就要具体分析一下现象与本质嘞。上面笔者也有提到，DDD除了四层经典架构，还有五层架构（包括其演化的多层架构）、六边形架构等也都是DDD提供的架构模型（形式），那这些都可以理解DDD架构模式的外显形式，那么又有哪些共性呢？可自行查询，本文直接给结论，即它们都有Domain层，Domain层，Domain层（重要的事情说三遍~~，该结论DDD作者译著有写到…），所以不管架构模式怎么演化，Domain是核心不能变。

  那么如上分析，我们在回到这个问题，我们是不是可以给出一个这样的答案：

  > 形式上符合DDD架构，具体是不是DDD的架构实践，本质上还要看
  >
  > - （1）项目是否包括有Domain层；
  > - （2）Domain层是否满足DDD战术篇的要求（或者可暂时简单理解为充血模型吧）

## Spring与DDD

- Spring框架中，Spring为我们提供了`@Service` `@Repository` 等注解，为我们分离行为和行为（注册为Bean）和属性（数据模型），同时通过`@Autowired`在合适地方进行注入行为，因为行为被注册为Spring容器中的Bean后，减少了频繁创建行为的开销，只有属性的数据模型作为数据的载体来传递数据。提供很大的便捷性。但也阻碍了我们应用DDD编码实践， Spring框架主张分离，DDD思想主张合并，我们在Spring框架中使用DDD则需要在其基础上进行一些权衡取舍，即 ***如何将注册为Bean的行为穿插到原有的贫血模型中来构建充血模型是我们要解决的问题***
- 关于这个问题，笔者使用了Spring框架提供的**获取容器内已经注册的Bean接口**，直接调用接口，在有属性的领域模型中来获取行为；主要还是体现融入领域模型中的部分Service获取仓储接口来实现持久化过程。

# 附在最后的

上述经典四层架构，都是从一个软件开发人员的角度来阐述说明DDD在编码实践阶段的应用。除此之外， `DDD`在业务领域的建模分析过程中也可引入该概念。比如我们现在所倡导的微服务化，如何划分或拆分微服务；如何有效地区分限界上下文，划分子域；如何构建一个有效的聚合，识别聚合根等。。。

> DDD系统建模过程（在需求分析、设计阶段等过程的体现）。`DDD`推荐不割裂系统的需求和设计，是将二者有机地结合起来，以统一的领域通用语言（不一定是UML）进行系统建模，进行系统的分析和设计工作。即该领域专业的业务人员（**领域专家**） 和 **软件开发人员**通过领域模型进行需求沟通，彼此共享领域知识，确立符合真实业务需求的领域模型。我们这里可以合并称作系统建模过程。 



## 领域建模分析过程图示

![ddd-modeling](<https://img-blog.csdnimg.cn/2019120622204112.png>)

* **领域**表示正在处理的现实世界中复杂的业务逻辑或规则构成的问题域；

* **领域模型**是对现实世界真实业务的抽象描述，与任何技术实现都无关；

  在需求阶段表现为分析模型，设计阶段表现为设计模型（UML模型）。

  前期它可以为通过正式或非正式的UML图、草图、文字说明等进行描述，形成领域专家和开发人员都能理解的业务领域模型；后期则需要开发人员对分析模型进行再次凝练提取，成为较为完整的UML模型。

## 子域概念说明

**子域** 是整个业务领域的一部分，通常这个与微服务划分的粒度有些关系。

参考资料 [DDD精粹：运用子域进行战略设计](https://zhuanlan.zhihu.com/p/37710646)

 在一个庞大的复杂系统中，通常大多数的领域模型都过于复杂而划分为若干个子业务系统，这些子业务系统可以成为一个子域；若是子业务系统继续拆分，子域也可继续向下兼容，系统拆分的粒度影响子域的多少。在系统分析设计中，明确清晰的限界上下文无疑是最好的选择，但通常情况下系统边界未必那么明确……
子域类型通常有如下三种：核心域、支撑子域、通用子域。

- **核心域（Sub Domain）**：核心业务，可理解为一个有明确限界上下文的定义明确的业务领域模型，是项目的核心业务的体现，需要团队精心打造重点关注的核心模块。
- **支撑子域（Supporting Subdomain）**：支撑核心域的相关功能，这类通常需要定制开发，根据核心子域的相关内容进行定制
- **通用子域（Generic Subdomain）**：可以理解为现成的解决方案，拿来即用，也是用来辅助核心子域，但不需要像支撑子域那样个性化定制。

**开发人员参与的必要性：** 领域模型分析与软件设计实现通常不是一个等价的映射，构建的模型未必足够精良能够完全适应软件的设计实现，故需要将开发人员来加入领域模型分析过程中，在模型构建时候即开始考虑软件的设计实现。这样有了开发人员反馈就能更好的保证模型的质量，也能够帮助开发者理解业务，保证软件最大程度满足需求结果。

## 领域模型设计过程中通常有两种模式

* 基于数据库的建模：Database Modeling

  通过数据抽象系统关系，即传统数据库分析设计。该种模式实际上就是一种典型的贫血模型，通过数据库映射的实体类只有对应的属性，成为了只有getter和setter方法的数据载体，而没有具体行为，相应的行为要通过Service层去实现，随着业务升级积累，会出现胖服务层和贫血的领域模型，维护起来会越发乏力。即便如此，该种模式仍是广泛应用在软件开发领域。

* 基于对象的建模： Object Modeling

  通过面向对象方式抽象系统关系，也就是面向对象设计。毫无疑问，在面向对象编程环境中，面向对象无疑是领域建模最佳方式。通过面向对象构建的领域模型，因为有类的继承、封装、多态等特性显得生动许多，不仅包含自身属性状态，还包括有方法行为等，即充血的领域模型。一些Service层的行为凝练为领域服务，Service层则变薄了，领域模型则丰富了行为。

***假设内存空间无限***，不考虑持久化，面向对象建模无疑是最佳选择 （伪命题）

