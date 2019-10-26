package cn.ch08.ch08_5;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

// 1.创建一个名为MyFormatter的类，继承java.util.logging.Formatter类
// 然后，实现抽象format()方法。它以LogRecord对象为参数，返回一个带有日志消息的String对象
public class MyFormatter extends Formatter {

    @Override
    public String format(LogRecord record) {
        StringBuilder sb = new StringBuilder();
        sb.append("[" + record.getLevel() + "] - ");
        sb.append(new Date(record.getMillis()) + " : ");
        sb.append(record.getSourceClassName() + "." + record.getSourceMethodName() + " : ");
        sb.append(record.getMessage() + "\n");
        return sb.toString();
    }
}
