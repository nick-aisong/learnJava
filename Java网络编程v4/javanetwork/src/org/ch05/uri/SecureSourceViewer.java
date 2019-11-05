package org.ch05.uri;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.URL;

//5-12
public class SecureSourceViewer {

    public static void main(String[] args) {

        args = new String[]{"http://www.dmoz.org/search/q?q=java"};

        Authenticator.setDefault(new DialogAuthenticator());

        for (String arg : args) {
            try {
                URL u = new URL(arg);
                try (InputStream in = new BufferedInputStream(u.openStream())) {
                    Reader r = new InputStreamReader(in);
                    int c;
                    while ((c = r.read()) != -1) {
                        System.out.print((char) c);
                    }
                }

            } catch (MalformedURLException e) {
                System.out.println(arg + " is not a parseable URL");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println();
        }
        // 由于我们使用了AWT，所以必须显示退出
        System.exit(0);
    }

}
