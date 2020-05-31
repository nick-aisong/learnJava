#### 第10章 深入理解Session与Cookie

Session与Cookie不管是对JavaWeb的初学者还是熟练使用者来说都是一个令人头疼的问题。在初入职场时恐怕很多程序员在面试时都被问过这个问题。其实这个问题回答起来既简单又复杂，简单是因为它们本身只是HTTP中的一个配置项，在Servlet 规范中也只是对应到一个类而已；说它复杂原因在于当我们的系统大到需要用到很多Cookie 时，我们不得不考虑HTTP对Cookie数量和大小的限制，那么如何才能解决这个瓶颈呢?Session也会有同样的问题，当我们的一个应用系统有几百台服务器时，如何解决Session在多台服务器之间共享的问题?它们还有一些安全问题，如Cookie被盗、Cookie 伪造等问题应如何避免。本章将详细解答这些问题，同时也将分享淘宝在解决这些问题时总结的一些经验。

Session与Cookie的作用都是为了保持访问用户与后端服务器的交互状态。它们有各自的优点，也有各自的缺陷，然而具有讽刺意味的是它们的优点和它们的使用场景又是矛盾的。例如，使用Cookie来传递信息时，随着Cookie个数的增多和访问量的增加，它占用的网络带宽也很大，试想假如Cookie占用200个字节，如果一天的PV有几亿，那么它要占用多少带宽?所以有大访问量时希望用Session，但是Session的致命弱点是不容易在多台服务器之间共享，这也限制了Session 的使用。

##### 10.1 理解Cookie

Cookie的作用我想大家都知道，通俗地说就是当一个用户通过HTTP访问一个服务器时，这个服务器会将一些Key/Value键值对返回给客户端浏览器，并给这些数据加上一些限制条件，在条件符合时这个用户下次访问这个服务器时，数据又被完整地带回给服务器。

这个作用就像你去超市购物时，第一次给你办张购物卡，在这个购物卡里存放了一些你的个人信息，下次你再来这个连锁超市时，超市会识别你的购物卡，下次直接购物就好了。

当初W3C在设计Cookie时实际上考虑的是为了记录用户在一段时间内访问Web应用的行为路径。由于HTTP是一种无状态协议，当用户的一次访问请求结束后，后端服务器就无法知道下一次来访问的还是不是上次访问的用户。在设计应用程序时，我们很容易想到两次访问是同一人访问与不同的两个人访问对程序设计和性能来说有很大的不同。例如，在一个很短的时间内，如果与用户相关的数据被频繁访问，可以针对这个数据做缓存，这样可以大大提高数据的访问性能。Cookie的作用正是如此，由于是同一个客户端发出的请求，每次发出的请求都会带有第一次访问时服务端设置的信息，这样服务端就可以根据Cookie值来划分访问的用户了。

###### 10.1.1 Cookie属性项

当前Cookie有两个版本：Version 0和Version 1，它们有两种设置响应头的标识，分别是“Set-Cookie”和“Set-Cookie2”。这两个版本的属性项有些不同，表10-1和表10-2是对这两个版本的属性介绍。

表10-1，Version0 属性项介绍

| 属性项     | 属性项介绍                                                   |
| ---------- | ------------------------------------------------------ |
| NAME=VALUE | 键值对，可以设置要保存的Key/Value，注意这里的NAME不能和其他属性项的名字一样 |
| Expires    | 过期时间，在设置的某个时间点后该Cookie就会失效，如expires=Wednesday, 09-Nov-99 23:12:40 GMT |
| Domain     | 生成该Cookie的域名，如domain="xulingbo.net"                  |
| Path       | 该Cookie是在当前哪个路径下生成的，如path=/wp-admin/          |
| Secure     | 如果设置了这个属性，那么只会在SSL连接时才会回传该Cookie      |

表10-2，Version1 属性项介绍

| 属性项     | 属性项介绍                                                   |
| ---------- | ------------------------------------------------------ |
| NAME=VALUE | 与Version 0相同                                              |
| Version    | 通过Set-Cookie2设置的响应头创建必须符合RFC2965规范，如果通过Set-Cookie 响应头设置，则默认值为0；如果要设置为1，则该Cookie要遵循RFC 2109规范 |
| Comment    | 注释项，用户说明该Cookie有何用途                             |
| CommentURL | 服务器为此Cookie提供的URI注释                                |
| Discard    | 是否在会话结束后丢弃该Cookie项，默认为fasle                  |
| Domain     | 类似于Version 0                                              |
| Max-Age    | 最大失效时间，与Version 0不同的是这里设置的是在多少秒后失效  |
| Path       | 类似于Version 0                                              |
| Port       | 该Cookie在什么端口下可以回传服务端，如果有多个端口，则以逗号隔开，如Port="80,81,8080" |
| Secure     | 类似于Version 0                                              |

在以上两个版本的Cookie中设置的Header头的标识符是不同的，我们常用的是Set-Cookie: userName= “junshan”; Domain=“xulingbo.net"，这是Version 0的形式。针对Set-Cookie2是这样设置的: Set-Cookie2: userName=“junshan”; Domain=“xulingbo.net";Max-Age=1000。但是在Java Web的Servlet 规范中并不支持Set-Cookie2响应头，在实际应用中Set-Cookie2的一些属性项却可以设置在Set-Cookie中，如这样设置: Set-Cookie:userName=“junshan" ; Version=“1”;Domain=“xulingbo.net" ;Max-Age= 1000。

###### 10.1.2 Cookie如何工作

当我们用如下方式创建Cookie时：
```java
String getCookie(Cookie[] cookies, String key) {
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(key)) {
                return cookie.getvalue();
            }
        }
    }
    return null;
}

@Override
public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    Cookie[] cookies = request.getCookies();
    String userName = getCookie(cookies, "userName");
    String userAge = getCookie(cookies, "userAge");
    if (userName == null) {
        response.addCookie(new Cookie("userName", "junshan"));
    }
    if (userAge == null) {
        response.addCookie(new Cookie("userAge", "28"));
    }
    response.getHeaders("Set-Cookie");
}
```
Cookie是如何加到HTTP 的Header 中的呢？当我们用Servlet 3.0 规范来创建一个Cookie对象时，该Cookie既支持Version 0又支持Version 1，如果你设置了Version 1中的配置项，即使你没有设置版本号，Tomcat在最后构建HTTP响应头时也会自动将Version的版本设置为1。下面看一下Tomcat是如何调用addCookie方法的，图10-1 是Tomcat创建Set-Cookie响应头的时序图。

<img src="img\图10-1 Tomcat创建Set-Cookie响应头的时序图.png" style="zoom:50%;" />

从图10-1 中可以看出，真正构建Cookie是在org.apache. catalina.connector.Response类中完成的，调用generateCookieString 方法将Cookie 对象构造成一个字符串，构造的字符串的格式如userName=“junshan”;Version=“1”; Domain=“xulingbo.net”; Max-Age= 1000。然后将这个字符串命名为Set-Cookie添加到MimeHeaders中。

在这里有以下几点需要注意。

- 所创建Cookie的NAME不能和Set-Cookie或者Set-Cookie2的属性项值一样，如果一样会抛出IllegalArgumentException异常。

- 所创建Cookie的NAME和VALUE的值不能设置成非ASSIC字符，如果要使用中文，可以通过URLEncoder将其编码，否则会抛出IllegalArgumentException 异常。

- 当NAME和VALUE的值出现一些TOKEN字符(如“\”、 “，”等)时，构建返回头会将该Cookie的Version 自动设置为1。

- 当在该Cookie的属性项中出现Version为1的属性项时，构建HTTP响应头同样会将Version设置为1。

不知道你有没有注意到一个问题，就是当我们通过response.addCookie 创建多个Cookie时，这些Cookie最终是在一个Header项中的还是以独立的Header存在的，通俗地说也就是我们每次创建Cookie时是否都是创建一个以NAME为Set-Cookie的MimeHeaders?答案是肯定的。从上面的时序图中可以看出每次调用addCookie 时，最终都会创建一个Header,但是我们还不知道最终在请求返回时构造的HTTP响应头是否将相同Header标识的Set-Cookie值进行合并。

我们找到Tomcat最终构造Http响应头的代码，这段代码位于org.apache.coyote.httpll.Http11 Processor类的prepareResponse方法中，如下所示：
```java
int size = headers.size();
for (int i =0; i< size; i++) {
    outputBuffer.sendHeader(headers.getName(i), headers.getValue(i));
}
```
这段代码清楚地表示，在构建HTTP返回字节流时是将Header中所有的项顺序地写出，而没有进行任何修改。所以可以想象浏览器在接收HTTP返回的数据时是分别解析每一个Header项的。

另外，目前很多工具都可以观察甚至可以修改浏览器中的Cookie数据。例如，在Firefox中可以通过HttpFox插件来查看返回的Cookie数据，如图10-2所示。

<img src="img\图10-2 HttpFox插件展示的Header数据.png" style="zoom:67%;" />

在Cookie项中可以详细查看Cookie属性项，如图10-3 所示。

<img src="img\图10-3 HttpFox插件展示的Cookie数据.png" style="zoom:67%;" />


前面主要介绍了在服务端如何创建Cookie，下面看一下如何从客户端获取Cookie。

当我们请求某个URL路径时，浏览器会根据这个URL路径将符合条件的Cookie放在Request请求头中传回给服务端，服务端通过request. getCookies()来取得所有Cookie。

###### 10.1.3 使用Cookie的限制

Cookie是HTTP头中的一个字段，虽然HTTP本身对这个字段并没有多少限制，但是Cookie最终还是存储在浏览器里，所以不同的浏览器对Cookie的存储都有一-些限制，表10-3是一些通常的浏览器对Cookie的大小和数量的限制。

表10-3 浏览器对Cookie的大小和数量的限制

|浏览器版本| Cookie的数量限制| Cookie的总大小限制|
| ---- | ---- | ---- |
| IE6 | 20个/每个域名 | 4095个字节 |
| IE7 | 50个/每个域名 | 4095个字节 |
| IE8 | 50个/每个域名 | 4095个字节 |
| IE9 | 50个/每个域名 | 4095个字节 |
| Chrome | 50个/每个域名 | 大于80000 |
| FireFox | 50个/每个域名 | 4097个字 |

##### 10.2 理解Session

前面已经介绍了Cookie 可以让服务端程序跟踪每个客户端的访问，但是每次客户端的访问都必须传回这些Cookie，如果Cookie很多，则无形地增加了客户端与服务端的数据传输量，而Session的出现正是为了解决这个问题。

同一个客户端每次和服务端交互时，不需要每次都传回所有的Cookie 值，而是只要传回一个ID，这个ID是客户端第一次访问服务器时生成的，而且每个客户端是唯一的。这样每个客户端就有了一个唯一的ID，客户端只要传回这个ID就行了，这个ID通常是NANE为JSESIONID的一个Cookie。

###### 10.2.1 Session与Cookie

下面详细讲一下Session是如何基于Cookie来工作的。实际上有以下三种方式可以让Session正常工作。

- 基于URL Path Parameter，默认支持。
- 基于Cookie，如果没有修改Context容器的Cookies标识，则默认也是支持的。
- 基于SSL，默认不支持，只有connector.getAttribute("SSLEnabled")为TRUE时才支持。

在第一种情况下，当浏览器不支持Cookie功能时，浏览器会将用户的SessionCookieName重写到用户请求的URL参数中，它的传递格式如/path/Servlet;name=value;name2=value2?name3=value3，其中“Servlet;” 后面的K-V就是要传递的Path Parameters， 服务器会从这个Path Parameters中拿到用户配置的SessionCookieName。关于这个SessionCookieName，如果在web.xml中配置session-config配置项，其cookie-config下的name属性就是这个SessionCookieName的值。如果没有配置session-config配置项,默认的SessionCookieName就是大家熟悉的“JSESSIONID"。需要说明的一一点是，与Session关联的Cookie 与其他Cookie没有什么不同。接着Request 根据这个SessionCookieName 到Parameters 中拿到Session ID并设置到request.setRequestedSessionId 中。

请注意，如果客户端也支持Cookie，则Tomcat仍然会解析Cookie中的SessionID，并会覆盖URL中的Session ID。

如果是第三种情况，则会根据javax.servlet.request.ssl_session 属性值设置Session ID。

###### 10.2.2 Session如何工作

有了Session ID，服务端就可以创建HttpSession对象了，第一次触发通过request.getSession()方法。如果当前的Session ID还没有对应的HttpSession 对象，那么就创建一个新的，并将这个对象加到org.apache.catalina.Manager的sessions容器中保存。Manager类将管理所有Session 的生命周期，Session 过期将被回收，服务器关闭，Session将被序列化到磁盘等。只要这个HttpSession对象存在，用户就可以根据SessionID来获取这个对象，也就做到了对状态的保持。

与Session相关的类图如图10-4所示。

<img src="img\图10-4 Session相关类图.png" style="zoom:50%;" />

从图10-4 中可以看出，从request.getSession 中获取的HttpSession 对象实际上是StandardSession对象的门面对象，这与前面的Request 和Servlet 是一样的原理。图10-5是Session工作的时序图。

<img src="img\图10-5 Session工作的时序图.png" style="zoom:50%;" />

从时序图中可以看出，从Request中获取的Session对象保存在org.apache.catalina.Manager类中，它的实现类是org.apache.catalina.session. StandardManager，通过requestedSessionId从StandardManager的sessions集合中取出StandardSession对象。由于一个requestedSessionld 对应一个访问的客户端，所以一个客户端也就对应一个StandardSession对象，这个对象正是保存我们创建的Session 值的。下面我们看一下StandardManager这个类是如何管理StandardSession的生命周期的。

图10-6是 StandardManager 与 StandardSession 的类关系图。

<img src="img\图10-6 StandardManager与StandardSession 的类关系图.png" style="zoom:50%;" />

StandardManager类负责Servlet容器中所有的StandardSession对象的生命周期管理。当Servlet容器重启或关闭时，StandardManager负责持久化没有过期的StandardSession对象，它会将所有的StandardSession对象持久化到一个以“SESSIONS.ser"为文件名的文件中。到Servlet容器重启时，也就是StandardManager初始化时，它会重新读取这个文件，解析出所有Session 对象，重新保存在StandardManager的sessions 集合中。Session 的恢复状态图如图10-7 所示。

<img src="img\图10-7 Session恢复状态图.png" style="zoom:50%;" />

当Servlet 容器关闭时 StandardManager 类会调用unload 方法将sessions集合中的StandardSession对象写到“SESSIONS.ser”文件中，然后在启动时再按照上面的状态图重新恢复，注意要持久化保存Servlet 容器中的Session对象，必须调用Servlet 容器的stop和start命令，而不能直接结束(kill) Servlet 容器的进程。因为直接结束进程，Servlet 容器没有机会调用unload方法来持久化这些Session对象。

另外，在 StandardManager 的 sessions 集合中的 StandardSession 对象并不是永远保存的，否则Servlet容器的内存将很容易被消耗尽，所以必须给每个Session对象定义一个有效时间，超过这个时间则 Session 对象将被清除。在Tomcat 中这个有效时间是60s(maxInactiveInterval属性控制)，超过60s该Session 将会过期。检查每个 Session 是否失效是在Tomcat的一个后台线程中完成的( backgroundProcess()方法中)。过期Session 的状态图如图10-8 所示。

<img src="img\图10-8 过期Session状态图.png" style="zoom:50%;" />

除了后台进程检查 Session 是否失效外，当调用request.getSession()时也会检查该Session是否过期。值得注意的是，request.getSession() 方法调用的 StandardSession 永远都会存在，即使与这个客户端关联的 Session 对象已经过期。如果过期，则又会重新创建一个全新的StandardSession对象，但是以前设置的Session值将会丢失。如果你取到了Session 对象，但是通过session.getAttribute取不到前面设置的Session值，请不要奇怪，因为很可能它已经失效了，请检查一下\<Manager pathname="" maxInactiveInterval="60" /\> 中maxInactiveInterval配置项的值，如果不想让Session过期则可以设置为-1。但是你要仔细评估一下，网站的访问量和设置的Session的大小，防止将你的Servlet容器内存撑爆。如果不想自动创建Session对象，也可以通过request. getSession(boolean create)方法来判断与该客户端关联的Session对象是否存在。

##### 10.3 Cookie安全问题

虽然 Cookie 和 Session 都可以跟踪客户端的访问记录，但是它们的工作方式显然是不同的，Cookie 通过把所有要保存的数据通过HTTP的头部从客户端传递到服务端，又从服务端再传回到客户端，所有的数据都存储在客户端的浏览器里，所以这些Cookie 数据可以被访问到，就像我们前面通过Firefox的插件HttpFox可以看到所有的Cookie值。不仅可以查看Cookie，甚至可以通过Firecookie插件添加、修改 Cookie，所以 Cookie 的安全性受到了很大的挑战。

相比较而言Session的安全性要高很多，因为 Session 是将数据保存在服务端，只是通过Cookie传递一个SessionID而已，所以Session更适合存储用户隐私和重要的数据。

##### 10.4 分布式Session框架

从前面的分析可知，Session 和 Cookie 各自有优点和缺点。在大型互联网系统中，单独使用 Cookie 和 Session 都是不可行的，原因很简单。因为如果使用 Cookie，则可以很好地解决应用的分布式部署问题，大型互联网应用系统的一个应用有上百台机器，而且有很多不同的应用系统协同工作，由于 Cookie 是将值存储在客户端的浏览器里，用户每次访问都会将最新的值带回给处理该请求的服务器，所以也就解决了同一个用户的请求可能不在同一台服务器处理而导致的Cookie不一致的问题。

###### 10.4.1 存在哪些问题

这种“谁家的孩子谁抱走”的处理方式的确是大型互联网的一个比较简单但的确可以解决问题的处理方式，但是这种处理方式也会带来了很多其他问题，如下所述。

- 客户端 Cookie 存储限制。随着应用系统的增多，Cookie 数量也快速增加，但浏览器对于用户Cookie的存储是有限制的。例如，对IE7之前的IE浏览器，Cookie 个数的限制是20个；而对后续的版本，包括Firefox等，Cookie个数的限制都是50个，总大小不超过4KB，超过限制就会出现丢弃Cookie的现象，这会严重影响应用系统的正常使用。

- Cookie 管理的混乱。在大型互联网应用系统中，如果每个应用系统都自己管理每个应用使用的Cookie， 则会导致混乱，由于通常应用系统都在同一个域名下，Cookie又有上面一条提到的限制，所以没有统一管理很容易出现Cookie超出限制的情况。

- 安全令人担忧。虽然可以通过设置HttpOnly属性防止一些私密Cookie被客户端访问，但是仍然不能保证Cookie无法被篡改。为了保证Cookie的私密性通常会对Cookie进行加密，但是维护这个加密Key也是一件麻烦的事情，无法保证定期更新加密Key也是带来安全性问题的一个重要因素。

当我们对以上问题不能再容忍下去时，就不得不想其他办法处理了。

###### 10.4.2 可以解决哪些问题

既然 Cookie 有以上问题，Session 也有它的好处，那么为何不结合使用 Session 和 Cookie 呢?下面是分布式 Session 框架可以解决的问题。

- Session 配置的统一管理。
- Cookie 使用的监控和统一规范管理。
- Session 存储的多元化。
- Session 配置的动态修改。
- Session 加密 key 的定期修改。
- 充分的容灾机制，保持框架的使用稳定性。
- Session 各种存储的监控和报警支持。
- Session 框架的可扩展性，兼容更多的 Session 机制如 wapSession。
- 跨域名 Session 与 Cookie 如何共享的问题。现在同一个网站可能存在多个域名，如何将 Session 和 Cookie 在不同的域名之间共享是一个具有挑战性的问题。

###### 10.4.3 总体实现思路

分布式Session框架的架构图如图10-9 所示。

<img src="img\图10-9 Session框架的架构图.png" style="zoom:50%;" />

为了达成上面所说的几个目标，我们需要一个服务订阅服务器，在应用启动时可以从这个订阅服务器订阅这个应用需要的可写 Session 项和可写 Cookie 项，这些配置的 Session 和 Cookie 可以限制这个应用能够使用哪些 Session 和 Cookie，甚至可以控制 Session 和 Cookie 可读或者可写。这样可以精确地控制哪些应用可以操作哪些 Session 和 Cookie，可以有效控制Session的安全性和Cookie的数量。

如Session的配置项可以为如下形式：
```xml
<session>
    <key>sessionID</key>
    <cookiekey>sessionID</cookiekey>
    <lifeCycle>9000</1ifeCycle>
    <base64>true</base64>
</session>
```
Cookie的配置可以为如下形式：
```xml
<cookie>
    <key>cookie</key>
    <lifeCycle></lifeCycle>
    <type>1</type>
    <path>/wp</path>
    <domain>xulingbo.net</domain>
    <decrypt>false</decrypt>
    <httpOnly>false</httpOnly>
</cookie>
```
统一通过订阅服务器推送配置可以有效地集中管理资源，所以可以省去每个应用都来配置 Cookie，简化Cookie的管理。如果应用要使用一个新增的Cookie，则可以通过一个统一的平台来申请，申请通过才将这个配置项增加到订阅服务器。如果是一个所有应用都要使用的全局Cookie，那么只需将这个Cookie通过订阅服务器统一推送过去就行了， 省去了要在每个应用中手动增加Cookie的配置。

关于这个订阅服务器现在有很多开源的配置服务器，如Zookeeper集群管理服务器，可以统一管理所有服各器的配置文件。

由于应用是一个集群，所以不可能将创建的 Session 都保存在每台应用服务器的内存中，因为如果每台服务器有几十万的访问用户，那么服务器的内存肯定不够用，即使内存够用，这些 Session 也无法同步到这个应用的所有服务器中。所以要共享这些 Session 必须将它们存储在一个分布式缓存中，可以随时写入和读取，而且性能要很好才能满足要求。当前能满足这个要求的系统有很多，如MemCache 或者淘宝的开源分布式缓存系统 Tair 都是很好的选择。

解决了配置和存储问题，下 面看一下如何存取 Session 和 Cookie。

既然是一个分布式 Session 的处理框架，必然会重新实现 HttpSession 的操作接口，使得应用操作Session 的对象都是我们实现的 InnerHttpSession 对象，这个操作必须在进入应用之前完成，所以可以配置一个 filter 拦截用户的请求。

先看一下如何封装 HttpSession 对象和拦截请求，图10-10是时序图。

<img src="img\图10-10 HttpSession拦截请求时序图.png" style="zoom:50%;" />

我们可以在应用的 web.xml 中配置一个 SessionFilter，用于在请求到达MVC框架之前封装HttpServletRequest 和HttpServletResponse 对象，并创建我们自己的 InnerHttpSession 对象，把它设置到 request 和 response 对象中。这样应用系统通过 request.getHttpSession() 返回的就是我们创建的 InnerHttpSession 对象了，我们可以拦截 response 的 addCookies 设置的 Cookie。

在时序图中，应用创建的所有 Session 对象都会保存在 InnerHttpSession 对象中，当用户的这次访问请求完成时，Session 框架将会把这个 InnerHttpSession 的所有内容再更新到分布式缓存中，以便于这个用户通过其他服务器再次访问这个应用系统。另外，为了保证一些应用对 Session 稳定性的特殊要求，可以将一些非常关键的 Session 再存储到 Cookie 中，如当分布式缓存存在问题时，可以将部分 Session 存储到 Cookie 中，这样即使分布式缓存出现问题也不会影响关键业务的正常运行。

<img src="img\图10-11 跨域名同步session.png" style="zoom:50%;" />

还有一个非常重要的问题就是如何处理跨域名来共享Cookie的问题。我们知道Cookie是有域名限制的，也就是在一个域名下的 Cookie 不能被另一个域名访问，所以如果在一个域名下已经登录成功，如何访问到另外一个域名的应用且保证登录状态仍然有效，对这个问题大型网站应该经常会遇到。如何解决这个问题呢?下面介绍一种处理方式，如图10-11所示。

从图中可以看出，要实现 Session 同步，需要另外一个跳转应用，这个应用可以被一个或者多个域名访问，它的主要功能是从一个域名下取得 sessionID，然后将这个 sessionID 同步到另外一个域名下。这个 sessionID 其实就是一个 Cookie，相当于我们经常遇到的 JSESSIONID，所以要实现两个域名下的 Session 同步，必须要将同一个 sessionID 作为Cookie写到两个域名下。

总共12步，一个域名不用登录就取到了另外一个域名下的 Session，当然这中间有些步骤还可以简化，也可以做一些额外的工作，如可以写一些需要的 Cookie，而不仅仅是传一个 sessionID。

除此之外，该框架还能处理 Cookie 被盗取的问题。如你的密码没有丢失，但是你的账号却有可能被别人登录的情况，这种情况很可能就是因为你登录成功后，你的 Cookie 被别人盗取了，盗取你的 Cookie 的人将你的 Cookie 加入到他的浏览器，然后他就可以通过你的 Cookie 正常访问你的个人信息了，这是一个非常严重的问题。在这个框架中我们可以设置一个 Session 签名，当用户登录成功后我们根据用户的私密信息生成的一个签名，以表示当前这个唯一的合法登录状态，然后将这个签名作为一个 Cookie 在当前这个用户的浏览器进程中和服务器传递，用户每次访问服务器都会检查这个签名和从服务端分布式缓存中取得的 Session 重新生成的签名是否一致，如果不一致，则显然这个用户的登录状态不合法，服务端将清除这个 sessionID 在分布式缓存中的 Session 信息，让用户重新登录。

##### 10.5 Cookie压缩

Cookie 在HTTP的头部，所以通常的 gzip 和 deflate 针对 HTTP Body 的压缩不能压缩 Cookie，如果 Cookie 的量非常大，则可以考虑将 Cookie 也做压缩，压缩方式是将 Cookie 的多个k/v对看成普通的文本，做文本压缩。压缩算法同样可以使用 gzip 和 deflate 算法，但是需要注意的一点是，根据 Cookie 的规范，在 Cookie 中不能包含控制字符，仅能包含ASCII码为34~126 的可见字符。所以要将压缩后的结果再进行转码，可以进行 Base32 或者 Base64 编码。

可以配置一个 Filter 在页面输出时对 Cookie 进行全部或者部分压缩，如下代码所示：
```java
private void compressCookie(Cookie c, HttpServletResponse res) {
    try {
        ByteArrayOutputStream bos = null;
        bos = new ByteArrayOutputStream();
        DeflaterOutputStream dos = new DeflaterOutputStream(bos);
        dos.write(c.getValue().getBytes());
        dos.close();
        System.out.println("before compress length:" + c.getValue().getBytes().length);
        String compress = sun.misc.BASE64Encoder().encode(bos.toByteArray());
        res.addCookie(new Cookie ("compress", compress));
        System.out.println("after compress length:" + compress.getBytes().length);
        } catch (IOException e) {
            e. printStackTrace();
        }
}
```
上面的代码是用 DeflaterOutputStream 对 Cookie 进行压缩的，Deflater 压缩后再进行BASE64编码，相应地用 InflaterInputStream 进行解压。
```java
private void unCompressCookie(Cookie c) {
    try {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] compress = new sun.misc.BASE64Decoder().decodeBuffer(new String(c.getValue().getBytes()));
        ByteArrayInputStream bis = new ByteArrayInputStream(compress);
        InflaterInputStream inflater = new InflaterInputStream(bis);
        byte[] b = new byte[1024];
        int count;
        while((count = inflater.read(b)) >= 0) {
            out.write(b, 0, count);
        }
        inflater.close();
        System.out.println(out.toByteArray());
    } catch (Exception e) {
        e.printstackTrace();
    }
}
```
2KB大小的 Cookie 在压缩前与压缩后的字节数相差20%左右，如果你的网站的 Cookie 在2~3KB左右，一天有1亿的PV，那么一天就能够产生4TB的带宽流量了，从节省带宽成本来说压缩还是很有必要的。

###### 10.6 表单重复提交问题

在网站中有很多地方都存在表单重复提交的问题，如用户在网速慢的情况下可能会重复提交表单，又如恶意用户通过程序来发送恶意请求等，这时都需要设计一个防止表单重复提交的机制。

要防止表单重复提交，就要标识用户的每一次访问请求，使得每一次访问对服务端来说都是唯一确定的。为了标识用户的每次访问请求，可以在用户请求一个表单域时增加一个隐藏表单项，这个表单项的值每次都是唯一的token，如：
```xml
<form id="form" method="post">
<input type=hidden name="crsf token" value="xxxx"/>
</form>
```
当用户在请求时生成这个唯一的 token 时，同时将这个 token 保存在用户的 Session 中，等用户提交请求时检查这个 token 和当前的 Session 中保存的 token 是否一致。 如果一致，则说明没有重复提交，否则用户提交上来的 token 已经不是当前这个请求的合法 token。其工作过程如图10-12 所示。

<img src="img\图10-12 工作过程.png" style="zoom:50%;" />

图10-12是用户发起的对表单页面的请求过程，生成唯一的 token 需要一个算法，最简单的就是可以根据一个种子作为 key 生成一个随机数，并保存在 Session 中，等下次用户提交表单时做验证。验证表单的过程如图10-13 所示。

<img src="img\图10-13 验证表单的过程.png" style="zoom:50%;" />

当用户提交表单时会将请求时生成的 token 带回来，这样就可以和在 Session 中保存的 token 做对比，从而确认这次表单验证是否合法。


###### 10.7 多终端Session统一

当前大部分网站都有了无线端，对无线端的 Cookie 如何处理也是很多程序员必须考虑的问题。

在无线端发展初期，后端的服务系统未必和PC的服务系统是统一的，这样就涉及在一端调用多个系统时如何做到服务端 Session 共享的问题了。有两个明显的例子：一个是在无线端可能会通过手机访问无线服务端系统，同时也会访问 PC 端的服务系统，如果它们两个的登录系统没有统一的话，将会非常麻烦，可能会出现二次登录的情况：另一个是在手机上登录以后再在PC上同样访问服务端数据，Session能否共享就决定了客户端是否要再次登录。

针对这两种情况，目前都有理想的解决方案。

1) 多端共享Session

多端共享 Session 必须要做的工作是不管是无线端还是PC端，后端的服务系统必须统一会话架构，也就是两边的登录系统必须要基于一致的会员数据结构、Cookie 与 Session的统一。也就是不管是PC端登录还是无线端登录，后面对应的数据结构和存储要统一，写到客户端的 Cookie 也必须一样，这是前提条件。

那么如何做到这一点？就是要按照我们在前面所说的实现分布式的Session框架。如下图10-14所示。

<img src="img\图10-14 服务端统一Session示意图.png" style="zoom:50%;" />

上面服务端统一 Session 后，在同一个终端上不管是访问哪个服务端都能做到登录状态统一。例如不管是 Native 还是内嵌 Webview，都可以拿统一的 SessionID 去服务端验证登录状态。

2) 多终端登录

目前很多网站都会出现无线端和 PC 端多端登录的情况，例如可以通过扫码登录等。这些是如何实现的呢？其实比较简单，如图10-15 所示。

<img src="img\图10-15 多终端登录示意图.png" style="zoom:50%;" />

这里手机端在扫码之前必须是已经登录的状态，因为这样才能获取到底是谁将要登录的信息，同时扫码的二维码也带有一个特定的标识，标识是这个客户端通过手机端登录了。当手机端扫码成功后，会在服务端设置这个二维码对应的标识为已经登录成功，这时 PC 客户端会通过将“心跳”请求发送到服务端，来验证是否已经登录成功，这样就成为一种便捷的登录方式。

##### 10.8 总结

Cookie和Session都是为了保持用户访问的连续状态，之所以要保持这种状态，一方面是为了方便业务实现，另一方面就是简化服务端的程序设计，提高访问性能，但是这也带来了另外一些挑战，例如安全问题、应用的分布式部署带来的Session的同步问题及跨域名Session的同步问题等。本章分析了Cookie 和Session的工作原理，并介绍了一种分布式Session的解决方案。
