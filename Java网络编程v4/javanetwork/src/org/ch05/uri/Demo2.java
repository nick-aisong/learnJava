package org.ch05.uri;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import org.junit.Test;

public class Demo2 {

    // 5-2 SourceView.class
    public static void main(String[] args) {

        // args = new String[]{"https://www.oreilly.com"};
        args = new String[]{"https://www.baidu.com"};

        if (args.length > 0) {
            InputStream in = null;
            try {
                // 打开URL进行读取
                URL u = new URL(args[0]);
                in = u.openStream();
                // 缓冲输入以提高性能
                in = new BufferedInputStream(in);
                // 将InputStream串链到一个Reader
                Reader r = new InputStreamReader(in, "ASCII");
                int c;
                while ((c = r.read()) != -1) {
                    System.out.print((char) c);
                }
            } catch (MalformedURLException e) {
                System.err.println(args[0] + " is not a parseable URL");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // getURLConnection可以获取http协议的元数据
    @Test
    public void demo1() throws IOException {
        URL u = new URL("http://www.baidu.com/aaa/bbb/a b c啊/");
        URLConnection urlCon = u.openConnection();
    }

}
