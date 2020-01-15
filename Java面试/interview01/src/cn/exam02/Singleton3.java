package cn.exam02;

import java.io.IOException;
import java.util.Properties;

public class Singleton3 {

    public static final Singleton3 INSTANCE;
    private String info;

    static {
        Properties pro = new Properties();
        try {
            // 读取src直接目录下的资源
            pro.load(Singleton3.class.getClassLoader().getResourceAsStream("single.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        INSTANCE = new Singleton3(pro.getProperty("info"));
    }

    private Singleton3(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Singleton3 [info=" + info + "]";
    }
}
