package cn.part04.ch34.command_chainOfResponsibility;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

//代码清单34-8 根据父类获得子类
public class ClassUtils {
    //根据父类查找到所有的子类，默认情况是子类和父类都在同一个包名下
    public static List<Class> getSonClass(Class fatherClass) {
        //定义一个返回值
        List<Class> returnClassList = new ArrayList<>();
        //获得包名称
        String packageName = fatherClass.getPackage().getName();
        //获得包中的所有类
        List<Class> packClasses = getClasses(packageName);
        //判断是否是子类
        for (Class c : packClasses) {
            if (fatherClass.isAssignableFrom(c) && !fatherClass.equals(c)) {
                returnClassList.add(c);
            }
        }
        return returnClassList;
    }

    //从一个包中查找出所有的类，在jar包中不能查找
    private static List<Class> getClasses(String packageName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = null;
        try {
            resources = classLoader.getResources(path);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            // nick:这里如果遇到中文目录，调试的时候看见是URL encode 过的字符串，会导致找不到目录
            String filePath = "";
            try {
                filePath = URLDecoder.decode(resource.getPath(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            dirs.add(new File(filePath));
        }
        ArrayList<Class> classes = new ArrayList<Class>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }

    private static List<Class> findClasses(File directory, String packageName) {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                try {
                    classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return classes;
    }
}

//这个类请大家谨慎使用，在核心的应用中尽量不要使用该工具，它会严重影响性能