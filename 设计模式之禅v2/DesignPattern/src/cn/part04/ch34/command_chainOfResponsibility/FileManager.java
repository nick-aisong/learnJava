package cn.part04.ch34.command_chainOfResponsibility;

//代码清单34-6 文件管理类
public class FileManager {
    //ls命令
    public static String ls(String path) {
        return "file1\nfile2\nfile3\nfile4";
    }

    //ls -l命令
    public static String ls_l(String path) {
        String str = "drw-rw-rw root system 1024 2009-8-20 10:23 file1\n";
        str = str + "drw-rw-rw root system 1024 2009-8-20 10:23 file2\n";
        str = str + "drw-rw-rw root system 1024 2009-8-20 10:23 file3";
        return str;
    }

    //ls -a命令
    public static String ls_a(String path) {
        String str = ".\n..\nfile1\nfile2\nfile3";
        return str;
    }
}

//这3个实现类都关联到了FileManager，这个类有什么用呢？它是负责与操作系统交互
//的。要把UNIX的命令迁移到Windows上运行，就需要调用Windows的低层函数，实现起来较
//复杂，而且和我们本章要讲的内容没有太大关系，所以这里采用示例性代码代替

//以上都是比较简单的方法，大家有兴趣可以自己实现一下，以下提供3种思路：
//· 通过java.io.File类自己封装出类似UNIX的返回格式。
//· 通过java.lang.Runtime类的exec方法执行dos的dir命令，产生类似的ls结果。
//· 通过JNI（Java NativeInterface）来调用与操作系统有关的动态链接库，当然前提是需
//要自己写一个动态链接库文件