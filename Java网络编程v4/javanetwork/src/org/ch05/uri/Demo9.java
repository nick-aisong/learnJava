package org.ch05.uri;

import java.util.List;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.ArrayList;

import org.junit.Test;

// 5-9 LocalProxySelector.class
public class Demo9 extends ProxySelector {

    public static void main(String[] args) {
        // 每个jvm只有一个ProxySelector
        ProxySelector selector = new Demo9();
        ProxySelector.setDefault(selector);

    }

    private List<URI> failed = new ArrayList<>();

    @Override
    public List<Proxy> select(URI uri) {
        List<Proxy> result = new ArrayList<>();
        if (failed.contains(uri) || "http".equalsIgnoreCase(uri.getScheme())) {
            result.add(Proxy.NO_PROXY);
        } else {
            SocketAddress proxyAddress = new InetSocketAddress("proxy.example.com", 8000);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, proxyAddress);
            result.add(proxy);
        }
        return result;
    }

    @Override
    public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
        failed.add(uri);
    }

    @Test
    public void demo1() {
        // 设置代理
        // java -Dhttp.proxyHost=192.168.242.242 -Dhttp.proxyPort=9000

        System.setProperty("http.proxyHost", "192.168.232.32");
        System.setProperty("http.proxyPort", "9000");
        System.setProperty("http.nonProxyHosts", "java.oreilly.com|xml.oreilly.com|*.baidu.com");

        System.out.println(System.getProperty("http.proxyHost"));
        System.out.println(System.getProperty("http.proxyPort"));
        System.out.println(System.getProperty("http.nonProxyHosts"));

        // Proxy类对不同的url设置不同的代理
        SocketAddress address = new InetSocketAddress("proxy.example.com", 80);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, address);
    }

}
