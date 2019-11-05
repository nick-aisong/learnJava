package org.ch05.uri;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import org.junit.Test;

public class Demo5 {
    // 5-5 URLEquality.class
    public static void main(String[] args) throws UnknownHostException {

        try {
            URL www = new URL("http://www.ibiblio.org/");
            URL ibiblio = new URL("http://ibiblio.org/");

            if (ibiblio.equals(www)) {
                System.out.println(ibiblio + " is the same as " + www);

                System.out.println(ibiblio.getHost() + " host ip: " + InetAddress.getByName(ibiblio.getHost()));
                System.out.println(www.getHost() + " host ip: " + InetAddress.getByName(ibiblio.getHost()));
            } else {
                System.err.println(ibiblio + "is not the same as " + www);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void demo() throws UnknownHostException {
        try {
            URL baidus = new URL("http://www.baidu.com/");
            URL baidu = new URL("https://www.baidu.com/");

            if (baidus.equals(baidu)) {
                System.out.println(baidus + " is the same as " + baidu);

            } else {
                System.err.println(baidus + "is not the same as " + baidu);
                System.err.flush();
                ;

                System.out.println(baidu + " protocol: " + baidu.getProtocol());
                System.out.println(baidus + " protocol: " + baidus.getProtocol());

                System.out.println(baidu + " ip: " + InetAddress.getByName(baidu.getHost()));
                System.out.println(baidus + " ip: " + InetAddress.getByName(baidus.getHost()));
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    // sameFile()不比较 片段标识符的内容 #xxx
    @Test
    public void demo2() throws Exception {
        URL u1 = new URL("http://www.ncsa.uiuc.edu/HTMLPrimer.html#GS");
        URL u2 = new URL("http://www.ncsa.uiuc.edu/HTMLPrimer.html#HD");
        if (u1.sameFile(u2)) {
            System.out.println(u1 + " is the same file as \n" + u2);

            System.out.println();
            System.out.println(u1 + " file: " + u1.getFile());
            System.out.println(u2 + " file: " + u2.getFile());
        } else {
            System.err.println(u1 + " is not the same file as \n" + u2);
            System.err.flush();
        }
    }
}
