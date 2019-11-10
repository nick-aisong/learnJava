package org.ch05.uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

public class Demo {

    // 5-1
    public static void main(String[] args) {
        // 超文本传输协议
        testProtocol("http://www.baidu.com");
        // 安全http
        testProtocol("https://www.baidu.com");
        // 文件传输协议
        testProtocol("ftp://test");
        // 简单邮件传输协议
        testProtocol("mailto:nick_aisong@163.com");
        // telnet
        testProtocol("telnet://test.com");
        // 本地文件访问
        testProtocol("file://C:");
        // gopher
        testProtocol("gopher://test.com");
        // 轻量级目录访问协议
        testProtocol("ldap://ldap.test");
        // JAR
        testProtocol("jar:http://test.jar.class");
        // NFS， 网络文件系统
        testProtocol("nfs://test/test");
        // JDBC的定制协议
        testProtocol("jdbc:mysql://test");
        // rmi, 远程方法调用的定制协议
        testProtocol("rmi://test");
        // HotJava的定制协议
        testProtocol("doc:/test");
        testProtocol("netdoc:/test");
        testProtocol("systemresource://test");
        testProtocol("verbatim:http://www.baiducom");

    }

    private static void testProtocol(String url) {
        try {
            URL u = new URL(url);
            System.out.println(u.getProtocol() + " is supported");
        } catch (MalformedURLException e) {
            String protocol = url.substring(0, url.indexOf(":"));
            System.err.println(protocol + " is not supported");
            System.err.flush();
            // e.printStackTrace();
        }
    }

    // 构造
    public void demo1() {
        try {
            // 使用默认端口
            URL u = new URL("http", "www.baidu.com", "/f?fr=wwwt&kw=今日新鲜事");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    // 构造
    public void demo2() {
        try {
            URL u = new URL("http", "www.baidu.com", 8080, "/test/test");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    // 相对url
    @Test
    public void demo3() {
        try {
            URL u1 = new URL("http://www.test.com/app/index.html");
            URL u2 = new URL(u1, "test.html");
            System.out.println(u2.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    // 获取数据
    @Test
    public void demo4() {
        URL u;
        InputStream in = null;
        try {
            u = new URL("http://www.baidu.com");
            in = u.openStream();
            int c;
            while ((c = in.read()) != -1) {
                System.out.println(c);
            }
            // in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally { // java6 释放模式
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    // 获取数据
    @Test
    public void demo5() {
        try {
            URL u = new URL("http://www.baidu.com");
            try (InputStream in = u.openStream()) { // java7 释放资源 try-with-resources
                int c;
                while ((c = in.read()) != -1) {
                    System.out.println(c);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
