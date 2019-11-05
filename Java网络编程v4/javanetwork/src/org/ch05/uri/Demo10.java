package org.ch05.uri;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Demo10 {
    // 5-10 DMoz.class
    public static void main(String[] args) {

        args = new String[]{"java"};

        String target = "";
        for (String arg : args) {
            target += arg + " ";
        }
        target = target.trim();
        Demo8 query = new Demo8();
        query.add("q", target);

        try {
            URL u = new URL("http://www.dmoz.org/search/q?" + query);
            try (InputStream in = new BufferedInputStream(u.openStream())) {
                InputStreamReader theHTML = new InputStreamReader(in);
                int c;
                while ((c = theHTML.read()) != -1) {
                    System.out.println((char) c);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
