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
| ---------- | ------------------------------------------------------------ |
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






###### 10.1.3 使用Cookie的限制




##### 10.2 理解Session




###### 10.2.1 Session与Cookie




###### 10.2.2 Session如何工作




##### 10.3 Cookie安全问题




##### 10.4 分布式Session框架




###### 10.4.1 存在哪些问题




###### 10.4.2 可以解决哪些问题




###### 10.4.3 总体实现思路




##### 10.5 Cookie压缩




###### 10.6 表单重复提交问题




###### 10.7 多终端Session统一




##### 10.8 总结



