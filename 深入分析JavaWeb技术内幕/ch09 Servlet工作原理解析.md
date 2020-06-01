#### 第9章 Servlet工作原理解析

Java Web 技术是当今主流的互联网Web应用技术之一，而 Servlet 是 JavaWeb 技术的核心基础。因而掌握 Servlet 的工作原理是成为一名合格的Java Web技术开发人员的基本要求。本章将带你认识Java Web 技术是如何基于 Servlet 工作的，你将知道：Servlet 容器是如何工作的（以 Tomcat 为例）；一个 Web 工程在 Servlet 容器中是如何启动的；Servlet 容器如何解析你在 web.xml 中定义的 Servlet；用户的请求是如何被分配给指定的 Servlet 的；Servlet 容器如何管理 Servlet 生命周期。你还将了解到最新的 Servlet 的 API 类层次结构，以及如何分析 Servlet 中的一些难点问题。

##### 9.1 从Servlet容器说起

要介绍 Servlet 必须先把 Servlet 容器说清楚，Servlet 与 Servlet 容器的关系有点像枪和子弹的关系，枪是为子弹而生的，而子弹又让枪有了杀伤力。虽然它们是彼此依存的，但是又相互独立发展，这一切都是为了适应工业化生产。从技术角度来说是为了解耦，通过标准化接口来相互协作。既然接口是连接 Servlet 与 Servlet 容器的关键，那我们就从它们的接口说起。

Servlet 容器作为一个独立发展的标准化产品，目前其种类很多，但是它们都有自己的市场定位，各有特点，很难说谁优谁劣。例如，现在比较流行的 Jetty，在定制化和移动领域有不错的发展。我们这里还是以大家最为熟悉的 Tomcat 为例来介绍 Servlet 容器是如何管理 Servlet 的。Tomcat 本身也很复杂，我们从 Servlet 与 Servlet 容器的接口部分开始介绍，关于 Tomcat 的详细介绍可以参考本书相关章节。

在 Tomcat 的容器等级中，Context 容器直接管理 Servlet 在容器中的包装类 Wrapper，所以Context 容器如何运行将直接影响 Servlet 的工作方式。Tomcat 容器模型如图9-1所示。

<img src="img\图9-1 Tomcat容器模型.png" style="zoom:50%;" />

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

<img src="img\图9-2 Tomcat主要类的启动时序图.png" style="zoom:50%;" />

图 9-2 描述了在 Tomcat 的启动过程中主要类之间的时序关系，下面我们将会重点关注添加examples 应用所对应的 StandardContext 容器的启动过程。

当 Context 容器初始化状态设为 init 时，添加到 Context 容器的 Listener 将会被调用。ContextConfig 继承了LifecycleListener 接口，它是在调用 Tamcat.addWebapp 时被加入到 StandardContext 容器中的。ContextConfig 类会负责整个 Web 应用的配置文件的解析工作。

ContextConfig 的 init 方法将会主要完成以下工作。

- 创建用于解析 XML 配置文件的 contextDigester 对象。
- 读取默认的 context.xml 配置文件，如果存在则解析它。
- 读取默认的 Host 配置文件，如果存在则解析它。
- 读取默认的 Context 自身的配置文件，如果存在则解析它。
- 设置 Context 的 DocBase。

ContextConfig 的 init 方法完成后，Context 容器就会执行 startInternal 方法，这个方法的启动逻辑比较复杂，主要包括如下几部分。

- 创建读取资源文件的对象。
- 创建 ClassLoader 对象。
- 设置应用的工作目录。
- 启动相关的辅助类，如 logger、realm、 resources 等。
- 修改启动状态，通知感兴趣的观察者(Web应用的配置)。
- 子容器的初始化。
- 获取 ServletContext 并设置必要的参数。
- 初始化 “load on startup” 的 Servlet。

###### 9.1.2 Web应用的初始化工作

Web 应用的初始化工作是在 ContextConfig 的 configureStart 方法中实现的，应用的初始化主要是解析 web.xml 文件，这个文件描述了一个 Web 应用的关键信息，也是一个 Web 应用的入口。

Tomcat 首先会找 globalWebXml， 这个文件的搜索路径是 engine 的工作目录下的 org/apache/catalina/startup/NO_DEFAULT_XML 或 conf/web.xml。接着会找 hostWebXml, 这个文件可能会在 System.getProperty("catalina.base")/conf/${EngineName }/${HostName}/web.xml.default 中，接着寻找应用的配置文件 examples/WEB-INF/web.xml。web.xml 文件中的各个配置项将会被解析成相应的属性保存在 WebXml 对象中。如果当前的应用支持 Servlet 3.0，解析还将完成额外的9项工作，这额外的9项工作主要是 Servlet 3.0 新增的特性(包括 jar 包中的 META-INF/web- fragment.xml)的解析及对 annotations 的支持。

接下来会将 WebXml 对象中的属性设置到 Context 容器中，这里包括创建 Servlet 对象、filter、listener 等，这段代码在 WebXml 的 configureContext 方法中。下面是解析 Servlet 的代码片段：
```java
for (ServletDef servlet : servlets.values()) {
    Wrapper wrapper = context.createWrapper();
    String jspFile = servlet.getJspFile();
    if (jspFile != null) {
        wrapper.setJspFile(jspFile);
    }
    if (servlet.getLoadOnStartup() != null) {
        wrapper.setLoadOnStartup(servlet.getLoadOnStartup().intValue());
    }
    if (servlet.getEnabled() != null) {
        wrapper.setEnabled(servlet.getEnabled().booleanValue());
    }
    wrapper.setName(servlet.getServletName());
    Map<String, String> params = servlet.getParameterMap();
    for (Entry<String, String> entry : params.entrySet()) {
        wrapper.addInitParameter(entry.getKey(), entry.getvalue());
    }
    wrapper.setRunAs(servlet.getRunAs());
    Set<SecurityRoleRef> roleRefs = servlet.getSecurityRoleRefs();
    for (SecurityRoleRef roleRef : roleRefs) {
        wrapper.addSecurityReference(roleRef.getName(), roleRef.getLink());
    }
    wrapper.setServletClass(servlet.getServletClass());
    MultipartDef multipartdef = servlet.getMultipartDef();
    if (multipartdef != null) {
        if (multipartdef.getMaxFileSize() != null &&
                multipartdef.getMaxRequestSize()!= null &&
                multipartdef.getFileSizeThreshold() != null) {
            wrapper.setMultipartConfigElement(new MultipartConfigElement(
                multipartdef.getLocation(),
                Long.parseLong(multipartdef.getMaxFileSize()),
                Long.parseLong(multipartdef.getMaxRequestSize()),
                Integer.parseInt(multipartdef.getFilesizeThreshold())));
        } else {
            wrapper.setMultipartConfigElement(new MultipartConfigElement(
                    multipartdef.getLocation()));
        }
    }
    if (servlet.getAsyncSupported() != null) {
        wrapper.setAsyncSupported(servlet.getAsyncSupported().booleanValue());
    }
    context.addChild(wrapper);
}
```
这段代码清楚地描述了如何将 Servlet 包装成 Context 容器中的 StandardWrapper， 这里有个疑问，为什么要将 Servlet 包装成 StandardWrapper 而不直接包装成 Servlet 对象?这里StandardWrapper 是 Tomcat 容器中的一部分，它具有容器的特征，而 Servlet 作为一个独立的Web 开发标准，不应该强耦合在 Tomcat 中。

除了将 Servlet 包装成 StandardWrapper 并作为子容器添加到 Context 中外，其他所有的 web.xml 属性都被解析到 Context 中，所以说 Context 容器才是真正运行 Servlet 的 Servlet 容器。一个 Web 应用对应一个 Context 容器，容器的配置属性由应用的 web.xml 指定，这我们就能理解web.xml 到底起什么作用了。

##### 9.2 创建Servlet实例

前面已经完成了 Servlet 的解析工作，并且被包装成 StandardWrapper 添加在 Context 容器中，但是它仍然不能为我们工作，它还没有被实例化。下面我们将介绍 Servlet 对象是如何创建的，以及是如何被初始化的。

###### 9.2.1 创建Servlet对象

如果 Servlet 的 load-on-startup 配置项大于 0，那么在 Context 容器启动时就会被实例化，前面提到在解析配置文件时会读取默认的 globalWebXml，在 conf 下的 web.xml 文件中定义了一些默认的配置项，其中定义了两个 Servlet， 分别是 org.apache.catalina.servlets.DefaultServlet 和 org. apache.jasper.servlet.JspServlet。它们的 load-on-startup 分别是 1 和 3，也就是当Tomcat启动时这两个 Servlet 就会被启动。

创建 Servlet 实例的方法是从 Wrapper.loadServlet 开始的。loadServlet 方法要完成的就是获取servletClass，然后把它交给 InstanceManager 去创建一个基于 servletClass.class 的对象。如果这个 Servlet 配置了 jsp-file， 那么这个 servletClass 就是在 conf/web.xml 中定义的org.apache.jasper.servlet.JspServlet 了。

创建 Servlet 对象的相关类结构如图 9-3 所示。

<img src="img\图9-3 创建Servlet对象的相关类结构.png" style="zoom:50%;" />

###### 9.2.2 初始化Servlet

初始化 Servlet 在 StandardWrapper 的 initServlet 方法中，这个方法很简单，就是调用 Servlet 的 init() 方法，同时把包装了 StandardWrapper 对象的 StandardWrapperFacade 作为 ServletConfig  传给 Servlet。对于 Tomcat 容器为何要传 StandardWrapperFacade 给 Servlet 对象将在后面做详细解析。

如果该 Servlet 关联的是一个 JSP 文件，那么前面初始化的就是 JspServlet，接下来会模拟一次简单请求，请求调用这个 JSP 文件，以便编译这个 JSP 文件为类，并初始化这个类。

这样 Servlet 对象就初始化完成了，事实上 Servlet 从被 web.xml 解析到完成初始化，这个过程非常复杂，中间有很多过程，包括各种容器状态的转化引起的监听事件的触发、各种访问权限的控制和一些不可预料的错误发生的判断行为等。我们在这里只抓了一些关键环节进行阐述，以便于让大家有个总体脉络。

图 9-4 是这个过程的一个完整的时序图，在其中也省略了一些细节。

<img src="img\图9-4 初始化Servlet的时序图.png" style="zoom:50%;" />

##### 9.3 Servlet体系结构

我们知道 JavaWeb 应用是基于 Servlet 规范运转的，那么 Servlet 本身又是如何运转的呢?为何要设计这样的体系结构呢? Servlet 顶层类的关联图如图 9-5 所示。

<img src="img\图9-5 Servlet顶层类关联图.png" style="zoom:50%;" />


从图 9-5 可以看出，Servlet 规范就是基于这几个类运转的，与 Servlet 主动关联的是三个类，分别是 ServletConfig、ServletRequest 和 ServletResponse。 这三个类都是通过容器传递给 Servlet的，其中 ServletConfig 在 Servlet 初始化时就传给 Servlet 了，而后两个是在请求达到时调用Servlet 传递过来的。我们很清楚 ServletRequest 和 ServletResponse 在 Servlet 运行时的意义，但是 ServletConfig 和 ServletContext 对 Servlet 有何价值?仔细查看 ServletConfig 接口中声明的方法会发现，这些方法都是为了获取这个 Servlet 的一些配置属性，而这些配置属性可能在 Servlet 运行时被用到。ServletContext 又是干什么的呢?Servlet 的运行模式是一个典型的“握手型的交互式”运行模式。所谓“握手型的交互式”就是两个模块为了交换数据通常都会准备一个交易场景，这个场景一直跟随这个交易过程直到这个交易完成为止。这个交易场景的初始化是根据这次交易对象指定的参数来定制的，这些指定参数通常就是一个配置类。所以对号入座，交易场景就由 ServletContext 来描述，而定制的参数集合就由 ServletConfig 来描述。而 ServletRequest 和 ServletResponse 就是要交互的具体对象，它们通常都作为运输工具来传递交互结果。

ServletConfig 是在 Servlet init 时由容器传过来的，那么 ServletConfig 到底是个什么对象呢?

图 9-6 是 ServletConfig 和 ServletContext 在 Tomcat 容器中的类关系图。

<img src="img\图9-6 ServletConfig 在容器中的类关联图.png" style="zoom:50%;" />

可以看出，StandardWrapper 和 StandardWrapperFacade 都实现了 ServletConfig 接口，而StandardWrapperFacade 是 StandardWrapper 门面类。所以传给 Servlet 的是StandardWrapperFacade 对象，这个类能够保证从 StandardWrapper 中拿到 ServletConfig 所规定的数据，而又不把 ServletConfig 不关心的数据暴露给 Servlet。

同样 ServletContext 也与 ServletConfig 有类似的结构，在 Servlet 中能拿到的 ServletContext 的实际对象也是 ApplicationContextFacade 对象。ApplicationContextFacade 同样保证ServletContext 只能从容器中拿到它该拿的数据，它们都起到对数据的封装作用，它们使用的都是门面设计模式。

通过 ServletContext 可以拿到 Context 容器中的一些必要信息，如应用的工作路径、容器支持的Servlet 最小版本等。

在 Servlet 中定义的两个 ServletRequest 和 ServletResponse 实际的对象又是什么呢?我们在创建自己的 Servlet 类时通常使用的都是 HttpServletRequest 和 HttpServletResponse，它们继承了ServletRequest 和 ServletResponse。为何 Context 容器传过来的 ServletRequest、ServletResponse 可以被转化为 HttpServletRequest 和 HttpServletResponse 呢?

<img src="img\图9-7 与Request相关的类结构图.png" style="zoom:50%;" />

图 9-7 是 Tomcat 创建的 Request 和 Response 的类结构图。Tomcat 接到请求首先将会创建 org.apache.coyote.Request 和 org.apache.coyote.Response，这两个类是 Tomcat 内部使用的描述一次请求和相应的信息类，它们是一个轻量级的类，作用就是在服务器接收到请求后，经过简单解析将这个请求快速分配给后续线程去处理，所以它们的对象很小，很容易被 JVM 回收。接下来当交给一个用户线程去处理这个请求时又创建 org.apache.catalina.connector.Reques t和org.apache.catalina.connector.Response 对象。这两个对象一直贯穿整个 Servlet 容器直到要传给Servlet，传给 Servlet 的是 Request 和 Response 的门面类 RequestFacade 和 ResponsetFacade，这里使用门面模式与前面一样都是基于同样的目的一封装容器中的数据。一次请求对应的 Request 和 Response 的类转化如图 9-8 所示。

<img src="img\图9-8 Request和Response的转变过程.png" style="zoom:50%;" />

##### 9.4 Servlet如何工作

我们已经清楚了 Servlet 是如何被加载的、如何被初始化的，以及 Servlet 的体系结构，现在的问题就是它是如何被调用的?

用户从浏览器向服务器发起的一个请求通常会包含如下信息：http://hostname: port/contextpath/servletpath，hostname 和 port 用来与服务器建立 TCP 连接，后面的 URL 才用来选择在服务器中哪个子容器服务用户的请求。服务器是如何根据这个 URL 来到达正确的 Servlet 容器中的呢?

在 Tomcat7 中这件事很容易解决，因为这种映射工作由专门一个的类来完成，这个类就是org.apache.tomcat.util.http.mapper， 这个类保存了 Tomcat 的 Container 容器中的所有子容器的信息，org.apache.catalina.connector.Request 类在进入 Container 容器之前，Mapper 将会根据这次请求的 hostnane 和 contextpath 将 host 和 context 容器设置到 Request 的 mappingData属性中，如图 9-9 所示。所以当 Request 进入 Container 容器之前，对于它要访问哪个子容器就已经确定了。

<img src="img\图9-9 Request的Mapper类关系图.png" style="zoom:50%;" />

可能你有疑问，在 Mapper 中怎么会有容器的完整关系?这要回到图 9-2 中第 19 步 MapperListener 类的初始化过程，下面是 MapperListener 的 init 方法的代码：
```java
public void init() {
    findDefaultHost();
    Engine engine = (Engine)connector.getService().getContainer();
    engine.addContainerListener(this);
    Container[] conHosts = engine.findChildren();
    for (Container conHost : conHosts) {
        Host host = (Host)conHost;
        if (!LifecycleState.NEW.equals(host.getState())) {
            host.addLifecycleListener(this);
            registerHost(host);
        }
    }
}
```
这段代码的作用就是将 MapperListener 类作为一个监听者加到整个 Container 容器的每个子容器中，这样只要任何一个容器发生变化，MapperListener 都将会被通知到，相应的保存容器关系的MapperListener 的 mapper 属性也会被修改。在 for 循环中就是将 host 及下面的子容器注册到mapper 中。

图 9-10 描述了一次 Request 请求是如何到达最终的 Wrapper 容器的，我们现在知道了请求如何到达正确的 Wrapper 容器，但是在请求达到最终的 Servlet 前还要完成一些步骤，必须要执行 Filter 链，以及通知你在 web.xml 中定义的 listener。

<img src="img\图9-10 Request在容器中的路由图.png" style="zoom:50%;" />

接下来就要执行 Servlet 的 service 方法了。通常情况下，我们自已定义的 servlet 并不直接去实现 javax.servlet.servlet 接口，而是去继承更简单的 HttpServlet 类或者 GenericServlet 类，我们可以有选择地覆盖相应的方法去实现要完成的工作。

Servlet 的确已经能够帮我们完成所有的工作了，但是现在的 Web 应用很少直接将交互的全部页面用 Servlet 来实现，而是采用更加高效的 MVC 框架来实现。这些 MVC 框架的基本原理是将所有的请求都映射到一个 Servlet，然后去实现 service 方法，这个方法也就是 MVC 框架的入口。

当 Servlet 从 Servlet 容器中移除时，也就表明该 Servlet 的生命周期结束了，这时 Servlet 的destroy 方法将被调用，做一些扫尾工作。

##### 9.5 Servlet中的Listener

在整个 Tomcat 服务器中，Listener 使用得非常广泛，它是基于观察者模式设计的，Listener 的设计为开发 Servlet 应用程序提供了一种快捷的手段，能够方便地从另一个纵向维度控制程序和数据。目前在 Servlet 中提供了 6 种两类事件的观察者接口，它们分别是：EventListeners 类型的ServletContextAttributeListener 、ServletRequestAttributeListener、ServletRequestListener、HttpSessionAttributeListener 和 LifecycleListeners 类型的 ServletContextListener、HttpSessionListener， 如图9-11 所示。

<img src="img\图9-11 Servlet中的Listener.png" style="zoom:50%;" />

实际上，这 6 个 Listener 都继承了 EventListener 接口，每个 Listener 各自定义了需要实现的接口，这些接口如表 9-1 所示。

表 9-1 Listener 需要实现的接口及说明

<img src="img\表9-1 Listener需要实现的接口及说明.png" style="zoom:50%;" />

它们基本上涵盖了整个 Servlet 生命周期中你感兴趣的每种事件。这些 Listener 的实现类可以配置在 `web.xml<listener>` 标签中。当然也可以在应用程序中动态添加 Listener，需要注意的是ServletContextListener 在容器启动之后就不能再添加新的，因为它所监听的事件已经不会再出现了。掌握这些 Listener 的使用方法，能够让我们的程序设计得更加灵活。

如 Spring 的 org.springframework.web.context.ContextLoaderListener 就实现了一个 ServletContextListener，当容器加载时启动 Spring 容器。ContextLoaderListener 在 contextInitialized 方法中初始化 Spring 容器，有几种办法可以加载 Spring 容器，通过在 web.xml的`<context-param>`标签中配置 Spring 的 applicationContext.xml 路径，文件名可以任意取，如果没有配置，将在 /WEB-INF/ 路径下查找默认的 applicationContext.xml 文件。ContextLoaderListener 的 contextInitialized 方法代码如下：
```java
public void contextInitialized(ServletContextEvent event) {
    this.contextLoader = createContextLoader();
    if (this.contextLoader == null) {
        this.contextLoader = this;
    }
    this.contextLoader.initWebApplicationContext(event.getServletContext());
}
```

##### 9.6 Filter如何工作

Filter 也是在 web.xml 中另外一常用的配置项，可以通过`<filter>`和`<filter-mapping>`组合来使用 Filter。实际上 Filter 可以完成与 Servlet 同样的工作，甚至比 Servlet 使用起来更加灵活，因为它除了提供了 request 和 response 对象外，还提供了一个 FilterChain 对象，这个对象可以让我们更加灵活地控制请求的流转。下面看一下与 Filter 相关的类结构图，如图 9-12 所示。

<img src="img\图9-12 Filter相关的类结构图.png" style="zoom:50%;" />

在 Tomcat 容器中，FilterConfig 和 FilterChain 的实现类分别是 ApplicationFilterConfig 和ApplicationFilterChain，而Filter的实现类由用户自定义，只要实现 Filter 接口中定义的三个接口就行，这三个接口与在 Servlet 中的类似。只不过还有一个 ApplicationFilterChain 类，这个类可以将多个 Filter 串联起来，组成一个链，这个链与 Jetty 中的 Handler 链有异曲同工之妙。下面详细看一下 Filter 类中的三个接口方法。

- init(FilterConfig)：初始化接口，在用户自定义的 Filter 初始化时被调用，它与 Servlet 的 init 方法的作用是一样的，FilterConfig 与 ServletConfig 也类似，除了都能取到容器的环境类ServletContext 对象之外，还能获取在`<filter>`下配置的`<init-param>`参数值。

- doFilter (ServletRequest, ServletResponse, FilterChain)：在每个用户的请求进来时这个方法都会被调用，并在 Servlet 的 service 方法之前被调用。而 FilterChain 就代表当前的整个请求链，所以通过调用 FilterChain.doFilter 可以将请求继续传递下去。如果想拦截这个请求，可以不调用FilterChain.doFilter， 那么这个请求就直接返回了。所以 Filter 是一种责任链设计模式。

- destroy：当 Filter 对象被销毁时，这个方法被调用。注意，当 Web 容器调用这个方法之后，容器会再调用一次 doFilter 方法。

Filter 类的核心还是传递的 FilterChain 对象，这个对象保存了到最终 Servlet 对象的所有 Filter 对象，这些对象都保存在 ApplicationFilterChain 对象的 filters 数组中。在 FilterChain 链上每执行一个 Filter 对象，数组的当前计数都会加 1，直到计数等于数组的长度，当 FilterChain 上所有的 Filter 对象执行完成后，就会执行最终的 Servlet。所以在 ApplicationFilterChain 对象中会持有 Servlet 对象的引用。图 9-13 是 Filter 对象的执行时序图。

<img src="img\图9-13 Filter执行时序图.png" style="zoom:50%;" />

Filter 存在的意义就好比是你要去北京，它是你的目的地，但是提供一个机制让你在去的途中可以做一些拦截工作，如可以将你的一些行李包存放在某个“存放处”，当你返回时你可以再从这个地方取回。总之它可以在你的途中增加一些东西，或者减少一些东西。

##### 9.7 Servlet中的url-pattern

在 web.xml 中`<servlet-mapping>`和`<filter-mapping>`都有`<url-pattern>`配置项，它们的作用都是匹配一次请求是否会执行这个 Servlet 或者 Filter， 那么这个 URL 是怎么匹配的，又是何时匹配的呢?

先看看 Servlet 是何时匹配的。在 9.4 节中介绍了一个请求最终被分配到一个 Servlet 中是通过org.apache.tomat.util.http.Mapper 类完成的，这个类会根据请求的 URL 来匹配在每个 Servlet 中配置的`<url-pattern>`，所以它在一个请求被创建时就已经匹配了。

`<url-pattern>`的解析规则，对 Servlet 和 Filter 是一样的，匹配的规则有如下三种。

- 精确匹配：如`/foo.htm`只会匹配`foo.htm`这个 URL。
- 路径匹配：如`/foo/*`会匹配以`foo`为前缀的 URL。
- 后缀匹配：如`*.htm`会匹配所有以`.htm`为后缀的URL。

Servlet 的匹配规则在 org.apache.tomcat.util.http.mapper.Mapper. internalMapWrapper 中定义，对 Servlet 的匹配来说如果同时定义了多个`<url-pattern>`，那么到底匹配那个 Servlet 呢?这个匹配顺序是：首先精确匹配，如定义了两个 Servlet，Servlet1 为`/foo.htm`, Servlet2 是`/*`，请求URL为`http://localhost/foo.htm`，那么只有 Servlet1 匹配成功；如果精确匹配不成功，那么会使用第二个原则“最长路径匹配”，如 Servlet1 为`/foo/*`， Servlet2 为`/*`，这时请求的URL为`http://localhost/foo/foo.htm`，那么Servlet1 匹配成功；最后根据后缀进行匹配，但是一次请求只会成功匹配到一个Servlet。

Filter 的匹配规则在 ApplicationFilterFactory.matchFiltersURL 方法中定义。Filter 的匹配原则和Servlet 有些不同，只要匹配成功，这些 Filter 都会在请求链上被调用。`<url-pattern>`的其他写法( 如`/foo/`、`/*.htm`和`*/foo`) 都是不对的。

##### 9.8 总结

本章从 Servlet 容器的启动、Servlet 的初始化及 Servlet 的体系结构等内容中选出一些重点来讲述，目的是让读者有一个总体的完整结构图，同时本章也详细分析了其中的一些难点问题，希望对大家有所帮助。
