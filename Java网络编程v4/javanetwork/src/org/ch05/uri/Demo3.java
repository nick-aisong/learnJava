package org.ch05.uri;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

public class Demo3 {
    // 5-3 ContentGetter.class
    public static void main(String[] args) {

        // args = new String[] { "https://www.baidu.com" };
        // sun.net.www.protocol.http.HttpURLConnection$HttpInputStream

        args = new String[]{"https://www.baidu.com/img/bd_logo1.png"};
        // sun.awt.image.URLImageSource

        if (args.length > 0) {
            URL u;
            try {
                u = new URL(args[0]);
                Object o = u.getContent();
                System.out.println("I got a " + o.getClass().getName());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 重载 getContent
    @Test
    public void demo1() {

        try {
            URL u = new URL("http://www.baidu.com");
            Class<?>[] types = new Class[3];
            types[0] = String.class;
            types[1] = Reader.class;
            types[2] = InputStream.class;
            Object o = u.getContent(types);

            if (o instanceof String) {
                System.out.println(o);
            } else if (o instanceof Reader) {
                int c;
                Reader r = (Reader) o;
                while ((c = r.read()) != -1) {
                    System.out.print((char) c);
                }
                r.close();
            } else if (o instanceof InputStream) {
                int c;
                InputStream in = (InputStream) o;
                while ((c = in.read()) != -1) {
                    System.out.write(c);
                }
                in.close();
            } else {
                System.out.println("Error: unexpected type " + o.getClass());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
