SpringMVC中如何解决POST请求中文乱码问题
========

对于POST请求，可以设置一个filter (CharacterEncodingFilter)，配置web.xml文件

对GET请求，可以在Tomcat的server.xml的Connector里添加URIEncoding="UTF-8"

