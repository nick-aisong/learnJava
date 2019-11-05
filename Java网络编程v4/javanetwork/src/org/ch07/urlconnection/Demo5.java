package org.ch07.urlconnection;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Demo5 {
    // 7-5 AllHeaders.class
    // 显示整个HTTP首部
    public static void main(String[] args) {

        args = new String[]{"http://www.baidu.com"};

        for (int i = 0; i < args.length; i++) {
            try {
                URL u = new URL(args[i]);
                URLConnection uc = u.openConnection();
                for (int j = 1; ; j++) {
                    String header = uc.getHeaderField(j);
                    if (header == null)
                        break;
                    System.out.println(uc.getHeaderFieldKey(j) + ": " + header);
                }
            } catch (MalformedURLException e) {
                System.err.println(args[i] + " is not a URL I understand.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test() {
        List<Integer> testArr = new ArrayList<>();
        testArr.add(1);
        testArr.add(1);
        // Integer integer = testArr.get(2);
        // java.lang.IndexOutOfBoundsException: Index: 2, Size: 2

        Integer value = getValue(testArr, 1);
        System.out.println(value);
        Integer value2 = getValue(testArr, 2);
        System.out.println(value2);
    }

    public static Integer getValue(List<Integer> array, int index) {
        List<Integer> arrayList = array;
        if (index < 0 || index >= arrayList.size()) {
            return null;
        }
        return arrayList.get(index);
    }
}
