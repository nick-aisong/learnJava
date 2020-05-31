#### 第9章 Servlet工作原理解析

Java Web 技术是当今主流的互联网Web应用技术之一，而 Servlet 是 JavaWeb 技术的核心基础。因而掌握 Servlet 的工作原理是成为一名合格的Java Web技术开发人员的基本要求。本章将带你认识Java Web 技术是如何基于 Servlet 工作的，你将知道：Servlet 容器是如何工作的（以 Tomcat 为例）；一个 Web 工程在 Servlet 容器中是如何启动的；Servlet 容器如何解析你在 web.xml 中定义的 Servlet；用户的请求是如何被分配给指定的 Servlet 的；Servlet 容器如何管理 Servlet 生命周期。你还将了解到最新的 Servlet 的 API 类层次结构，以及如何分析 Servlet 中的一些难点问题。

##### 9.1 从Servlet容器说起

要介绍 Servlet 必须先把 Servlet 容器说清楚，Servlet 与 Servlet 容器的关系有点像枪和子弹的关系，枪是为子弹而生的，而子弹又让枪有了杀伤力。虽然它们是彼此依存的，但是又相互独立发展，这一切都是为了适应工业化生产。从技术角度来说是为了解耦，通过标准化接口来相互协作。既然接口是连接 Servlet 与 Servlet 容器的关键，那我们就从它们的接口说起。

Servlet 容器作为一个独立发展的标准化产品，目前其种类很多，但是它们都有自己的市场定位，各有特点，很难说谁优谁劣。例如，现在比较流行的 Jetty，在定制化和移动领域有不错的发展。我们这里还是以大家最为熟悉的 Tomcat 为例来介绍 Servlet 容器是如何管理 Servlet 的。Tomcat 本身也很复杂，我们从 Servlet 与 Servlet 容器的接口部分开始介绍，关于 Tomcat 的详细介绍可以参考本书相关章节。

在 Tomcat 的容器等级中，Context 容器直接管理 Servlet 在容器中的包装类 Wrapper，所以Context 容器如何运行将直接影响 Servlet 的工作方式。Tomcat 容器模型如图9-1所示。




从图9-1可以看出，Tomcat 的容器分为4个等级，真正管理 Servlet 的容器是 Context 容器，一个Context 对应一个 Web 工程，在 Tomcat 的配置文件中可以很容易地发现这一点，如下所示：
```xml
<Context path="/projectOne" docBase="D:\projects\projectOne" reloadable= "true" />
```
下面详细介绍 Tomcat 解析 Context 容器的过程，包括如何构建 Servlet。

###### 9.1.1 Servlet容器的启动过程

Tomcat 7 也开始支持嵌入式功能，增加了一个启动类 org.apache.catalina.startup.Tomcat。创建一个实例对象并调用 start 方法就可以很容易地启动 Tomcat。我们还可以通过这个对象来增加和修改 Tomcat 的配置参数，如可以动态增加 Context、Servlet 等。下面我们就利用这个 Tomeat 类来管理一个新增的 Context 容器，选择 Tomcat7 自带的 examples Web工程，并看看它是如何加到这个Context容器中的。
```java
Tomcat tomcat = getTomcatInstance();
File appDir = new File(getBuildDirectory(), "webapps/examples");
tomcat.addWebapp(null, "/examples", appDir.getAbsolutePath());
tomcat.start();
ByteChunk res = geturl("http://localhost:" + getPort() + "/examples/servlets/servlet/HelloWorldExample");
assertTrue(res.toString().indexof("<h1>Hello World!</h1>") > 0);
```
这段代码创建了一个 Tomcat 实例并新增了一个 Web 应用，然后启动 Tomcat 并调用其中的一个HelloWorldExampleServlet，看看有没有正确返回预期的数据。

Tomcat 的 addWebapp 方法的代码如下：
```java
public Context addWebapp(Host host, String url, String path) {
    silence(url);
    Context ctx = new StandardContext();
    ctx.setPath(url);
    ctx.setDocBase(path);
    if (defaultRealm == null) {
        initSimpleAuth();
    }
    ctx.setRealm(defaultRealm);
    ctx.addLifecycleListener(new DefaultWebXmlListener());
    ContextConfig ctxCfg = new ContextConfig();
    ctx.addLifecycleListener(ctxCfg);
    ctxCfg.setDefaultwebXml("org/apache/catalin/startup/NO_DEFAULT_XML");
    if (host == null)
        getHost().addChild(ctx);
    } else {
        host.addChild(ctx);
    }
    return ctx;
}
```
前面已经介绍了一个 Web 应用对应一个 Context 容器，也就是 Servlet 运行时的 Servlet 容器。添加一个 Web 应用时将会创建一个 StandardContext 容器，并且给这个 Context 容器设置必要的参数，url 和 path 分别代表这个应用在 Tomcat 中的访问路径和这个应用实际的物理路径，这两个参数与 Tomcat 配置中的两个参数是一致的。其中最重要的一个配置是 ContextConfig，这个类将会负责整个 Web 应用配置的解析工作，后面将会对其进行详细介绍。最后将这个 Context 容器加到父容器Host中。

接下来将会调用 Tomcat 的 start 方法启动 Tomcat。如果你清楚 Tomcat 的系统架构，那么会很容易理解 Tomcat 的启动逻辑。Tomcat 的启动逻辑是基于观察者模式设计的，所有的容器都会继承Lifecycle 接口，它管理着容器的整个生命周期，所有容器的修改和状态的改变都会由它去通知已经注册的观察者(Listener)。Tomcat 启动的时序图如图9-2表示。







###### 9.1.2 Web应用的初始化工作



##### 9.2 创建Servlet实例



###### 9.2.1 创建Servlet对象



###### 9.2.2 初始化Servlet



##### 9.3 Servlet体系结构



##### 9.4 Servlet如何工作



##### 9.5 Servlet中的Listener



##### 9.6 Filter如何工作



##### 9.7 Servlet中的url-pattern



##### 9.8 总结


