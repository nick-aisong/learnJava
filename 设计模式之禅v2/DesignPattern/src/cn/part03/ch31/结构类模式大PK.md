结构类模式大PK
========

结构类模式包括
1. 适配器模式
2. 桥梁模式
3. 组合模式
4. 装饰模式
5. 门面模式
6. 享元模式
7. 代理模式

为什么叫结构类模式呢？因为它们都是通过组合类或对象产生更大结构以适应更
高层次的逻辑需求。我们来分析以下几个模式的相似点和不同点

#### 代理模式VS装饰模式

对于两个模式，首先要说的是，装饰模式就是代理模式的一个特殊应用，两者的共同点
是都具有相同的接口，不同点则是代理模式着重对代理过程的控制，而装饰模式则是对类的
功能进行加强或减弱，它着重类的功能变化，我们举例来说明它们的区别

代理模式

详见：proxy

一个著名的短跑运动员有自己的代理人。如果你很仰慕他，你找运动员说“你跑个我看
看”，运动员肯定不搭理你，不过你找到他的代理人就不一样了，你可能和代理人比较熟，
可以称兄道弟，这个忙代理人还是可以帮的，于是代理人同意让你欣赏运动员的练习赛，这
对你来说已经是莫大的荣耀了

装饰模式

详见：decorator

如果使用装饰模式，我们该怎么实现这个过程呢？装饰模式是对类功能的加强，怎么加
强呢？增强跑步速度！在屁股后面安装一个喷气动力装置，类似火箭的喷气装置，那速度变
得很快，《蜘蛛侠》中的那个反面角色不就是这样的吗？

很惊讶？这个代理模式完全一样的类图？是的，完全一样！不过其实现的意图却不同

最佳实践

通过例子，我们可以看出代理模式和装饰模式有非常相似的地方，甚至代码实现都非常
相似，特别是装饰模式中省略抽象装饰角色后，两者代码基本上相同，但是还是有细微的差别

代理模式是把当前的行为或功能委托给其他对象执行，代理类负责接口限定：是否可以
调用真实角色，以及是否对发送到真实角色的消息进行变形处理，它不对被主题角色（也就
是被代理类）的功能做任何处理，保证原汁原味的调用。代理模式使用到极致开发就是
AOP，这是各位采用Spring架构开发必然要使用到的技术，它就是使用了代理和反射的技术

装饰模式是在要保证接口不变的情况下加强类的功能，它保证的是被修饰的对象功能比
原始对象丰富（当然，也可以减弱），但不做准入条件判断和准入参数过滤，如是否可以执
行类的功能，过滤输入参数是否合规等，这不是装饰模式关心的

代理模式在Java的开发中俯拾皆是，是大家非常熟悉的模式，应用非常广泛，而装饰模
式是一个比较拘谨的模式，在实际应用中接触比较少，但是也有不少框架项目使用了装饰模
式，例如在JDK的java.io.*包中就大量使用装饰模式，类似如下的代码：
```java
OutputStream out = new DataOutputStream(new FileOutputStream("test.txt"))
```
这是装饰模式的一个典型应用，使用DataOutputStream封装了一个FileOutputStream，以方
便进行输出流处理

#### 装饰模式VS适配器模式

装饰模式和适配器模式在通用类图上没有太多的相似点，差别比较大，但是它们的功能
有相似的地方：都是包装作用，都是通过委托方式实现其功能。不同点是：装饰模式包装的
是自己的兄弟类，隶属于同一个家族（相同接口或父类），适配器模式则修饰非血缘关系
类，把一个非本家族的对象伪装成本家族的对象，注意是伪装，因此它的本质还是非相同接
口的对象

大家都应该听过丑小鸭的故事吧，我们今天就用这两种模式分别讲述丑小鸭的故事。话
说鸭妈妈有5个孩子，其中4个孩子都是黄白相间的颜色，而最小的那只也就是叫做丑小鸭的
那只，是纯白色的，与兄弟姐妹不相同，在遭受了诸多的嘲讽和讥笑后，最终丑小鸭变成了
一只美丽的天鹅。那我们如何用两种不同模式来描述这一故事呢？

用装饰模式描述丑小鸭

详见：decorator

用装饰模式来描述丑小鸭，首先就要肯定丑小鸭是一只天鹅，只是因为她小或者是鸭妈
妈的无知才没有被认出是天鹅，经过一段时间后，它逐步变成一个漂亮、自信、优美的白天
鹅。根据分析我们可以这样设计，先设计一个丑小鸭，然后根据时间先后来进行不同的美化
处理，怎么美化呢？先长出漂亮的羽毛，然后逐步展现出异于鸭子的不同行为，如飞行，最
终在具备了所有的行为后，它就成为一只纯粹的白天鹅了

用适配器模式实现丑小鸭

采用适配器模式实现丑小鸭变成白天鹅的过程要从鸭妈妈的角度来分析，鸭妈妈有5个
孩子，它认为这5个孩子都是她的后代，都是鸭类，但是实际上是有一只（也就是丑小鸭）
不是真正的鸭类，她是一只小白天鹅，就像《木兰辞》中说的“雄兔脚扑朔，雌兔眼迷离。
双兔傍地走，安能辨我是雄雌？”同样，因为太小，差别太细微，很难分辨，导致鸭妈妈认
为她是一只鸭子，从鸭子的审美观来看，丑小鸭是丑陋的。通过分析，我们要做的就是要设
计两个对象：鸭和天鹅，然后鸭妈妈把一只天鹅看成了小鸭子，最终时间到来的时候丑小鸭
变成了白天鹅

可怜的小天鹅被认为是一只丑陋的小鸭子，造物弄人呀！采用适配器模式讲述丑小鸭的
故事，我们首先观察到的是鸭与天鹅的不同点，建立了不同的接口以实现不同的物种，然后
在需要的时候（根据故事情节）把一个物种伪装成另外一个物种，实现不同物种的相同处理
过程，这就是适配器模式的设计意图。

最佳实践

我们用两个模式实现了丑小鸭的美丽蜕变。我们发现：这两个模式有较多的不同点

- 意图不同

装饰模式的意图是加强对象的功能，例子中就是把一个怯弱的小天鹅强化成了一个美
丽、自信的白天鹅，它不改变类的行为和属性，只是增加（当然了，减弱类的功能也是可能
存在的）功能，使美丽更加美丽，强壮更加强壮，安全更加安全；而适配器模式关注的则是
转化，它的主要意图是两个不同对象之间的转化，它可以把一个天鹅转化为一个小鸭子看
待，也可以把一只小鸭子看成是一只天鹅(那估计要在小鸭子的背上装个螺旋桨了)，它关注
转换

- 施与对象不同

装饰模式装饰的对象必须是自己的同宗，也就是相同的接口或父类，只要在具有相同的
属性和行为的情况下，才能比较行为是增加还是减弱；适配器模式则必须是两个不同的对
象，因为它着重于转换，只有两个不同的对象才有转换的必要，如果是相同对象还转换什
么？！

- 场景不同

装饰模式在任何时候都可以使用，只要是想增强类的功能，而适配器模式则是一个补救
模式，一般出现在系统成熟或已经构建完毕的项目中，作为一个紧急处理手段采用

- 扩展性不同

装饰模式很容易扩展！今天不用这个修饰，好，去掉；明天想再使用，好，加上。这都
没有问题。而且装饰类可以继续扩展下去；但是适配器模式就不同了，它在两个不同对象之
间架起了一座沟通的桥梁，建立容易，去掉就比较困难了，需要从系统整体考虑是否能够撤
销
