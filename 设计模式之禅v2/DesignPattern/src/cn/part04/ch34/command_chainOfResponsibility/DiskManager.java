package cn.part04.ch34.command_chainOfResponsibility;

//代码清单34-16 磁盘管理
public class DiskManager {
    //默认的计算大小
    public static String df() {
        return "/\t10485760\n/usr\t104857600\n/home\t1048576000\n";
    }

    //按照kb来计算
    public static String df_k() {
        return "/\t10240\n/usr\t102400\n/home\tt10240000\n";
    }

    //按照gb计算
    public static String df_g() {
        return "/\t10\n/usr\t100\n/home\tt10000\n";
    }
}