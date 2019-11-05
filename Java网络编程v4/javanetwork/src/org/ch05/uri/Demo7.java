package org.ch05.uri;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.Test;

public class Demo7 {
    // 5-7 EncoderTest
    // x-www-form-urlencoded字符串
    public static void main(String[] args) {
        try {
            System.out.println(URLEncoder.encode("This string has spaces", "UTF-8"));
            System.out.println(URLEncoder.encode("This*string*has*asterisks", "UTF-8"));
            System.out.println(URLEncoder.encode("This%string%has%percentt%signs", "UTF-8"));
            System.out.println(URLEncoder.encode("This+string+has+pluses", "UTF-8"));
            System.out.println(URLEncoder.encode("This/string/has/slashes", "UTF-8"));
            System.out.println(URLEncoder.encode("This\"string\"has\"quote\"marks", "UTF-8"));
            System.out.println(URLEncoder.encode("This:string:has:colons", "UTF-8"));
            System.out.println(URLEncoder.encode("This~string~has~tildes", "UTF-8"));
            System.out.println(URLEncoder.encode("This(string)has(parentheses)", "UTF-8"));
            System.out.println(URLEncoder.encode("This.string.has.periods", "UTF-8"));
            System.out.println(URLEncoder.encode("This=string=has=equals=signs", "UTF-8"));
            System.out.println(URLEncoder.encode("This&string&has&ampersands", "UTF-8"));
            System.out.println(URLEncoder.encode("Thisēstringあhas·non-ASCII characters", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void demo1() throws Exception {
        String query = URLEncoder.encode("https://www.google.com/search?hl=en&as_q=Java&as_epq=I/O", "UTF-8");
        System.out.println(query);
        // 不是想要的结果
        // https%3A%2F%2Fwww.google.com%2Fsearch%3Fhl%3Den%26as_q%3DJava%26as_epq%3DI%2FO

        System.out.println();
        String url = "https://www.google.com/search?";
        url += URLEncoder.encode("hl", "UTF-8");
        url += "=";
        url += URLEncoder.encode("en", "UTF-8");
        url += "&";
        url += URLEncoder.encode("as_q", "UTF-8");
        url += "=";
        url += URLEncoder.encode("Java", "UTF-8");
        url += "&";
        url += URLEncoder.encode("as_epq", "UTF-8");
        url += "=";
        url += URLEncoder.encode("I/O", "UTF-8");
        System.out.println(url);
        // https://www.google.com/search?hl=en&as_q=Java&as_epq=I%2FO
    }

}
