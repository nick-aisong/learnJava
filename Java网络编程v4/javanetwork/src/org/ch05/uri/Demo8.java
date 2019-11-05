package org.ch05.uri;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Demo8 {
    // 5-8 QueryString.class

    public static void main(String[] args) {
        Demo8 demo8 = new Demo8();
        demo8.add("hl", "en");
        demo8.add("as_q", "Java");
        demo8.add("as_epq", "I/O");
        String url = "http://www.google.com/search?" + demo8;
        System.out.println(url);
        // http://www.google.com/search?&hl=en&as_q=Java&as_epq=I%2FO

        // decode
        try {
            String output = URLDecoder.decode(url, "UTF-8");
            System.out.println(output);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private StringBuilder query = new StringBuilder();

    public Demo8() {
    }

    public synchronized void add(String name, String value) {
        query.append('&');
        encoder(name, value);
    }

    private synchronized void encoder(String name, String value) {
        try {
            query.append(URLEncoder.encode(name, "UTF-8"));
            query.append('=');
            query.append(URLEncoder.encode(value, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public synchronized String getQuery() {
        return query.toString();
    }

    @Override
    public String toString() {
        return getQuery();
    }

}
