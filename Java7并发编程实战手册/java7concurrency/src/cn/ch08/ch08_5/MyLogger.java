package cn.ch08.ch08_5;

import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

// 2.创建一个名为MyLogger的类
public class MyLogger {
    // 3.声明一个名为handler的私有静态Handler属性
    private static Handler handler;

    // 4.实现公开的静态getLogger()方法，创建用来输出日志消息的Logger对象
    // 它接收一个名为name的字符串参数
    public static Logger getLogger(String name) {
        // 5.调用Logger类的getLogger()方法，传递参数name，得到与之关联的java.util.logging.Logger日志器
        Logger logger = Logger.getLogger(name);
        // 6.使用setLevel()方法设置日志级别，这里输出所有的日志消息
        logger.setLevel(Level.ALL);
        // 7.如果handler属性值为null，就创建一个新的FileHandler对象，用来输出日志消息到recipe8.log文件中
        // 调用setFormatter()方法，为handler指定一个MyFormatter对象作为一个格式化器
        try {
            if (handler == null) {
                // handler = new ConsoleHandler();
                handler = new FileHandler("recipe8.log");
                Formatter formatter = new MyFormatter();
                handler.setFormatter(formatter);
            }
            // 8.如果没有一个处理程序与Logger对象相关联，就调用addHandler()方法增加之
            if (logger.getHandlers().length == 0) {
                logger.addHandler(handler);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 9.返回已创建的Logger对象
        return logger;
    }
}

