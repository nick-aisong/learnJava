package ch17;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

//nick：这里是直接保存原样字符串，如果遇到有关时区相关的日期处理，要注意
public class SaveDateToDatabase {
    public SaveDateToDatabase() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver"); // 加载数据库驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SaveDateToDatabase saveDate = new SaveDateToDatabase();
        saveDate.saveDate("1982-10-25");
    }

    public void saveDate(String dateValue) {
        String textDate = dateValue; // 通过参数获得文本格式的日期
        String url = "jdbc:jtds:sqlserver://localhost:1433/db_database17"; // 数据库db_test的URL
        String username = "sa"; // 数据库的用户名
        String password = "123456"; // 数据库密码
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password); // 建立连接
            // 创建PreparedStatement对象，并传递SQL语句
            PreparedStatement ps = conn
                    .prepareStatement("insert into tb_datetime (name, datetime, datetime2) values(?,?,?)");
            ps.setString(1, "zhangzhenkun");
            // 创建日期格式化对象，并指定格式
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(textDate); // 将文本格式的日期转换为Date类型
            ps.setTimestamp(2, new Timestamp(date.getTime())); // 通过实例类的实例为第2个参数赋值，对应类型datetime
            ps.setTimestamp(3, new Timestamp(date.getTime())); // 通过实例类的实例为第3个参数赋值，对应类型datetime2
            int flag = ps.executeUpdate(); // 执行SQL语句，获得更新记录数
            if (flag > 0) {
                JOptionPane.showMessageDialog(null, "添加成功！");
            } else {
                JOptionPane.showMessageDialog(null, "添加失败！");
            }
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                conn.close(); // 关闭数据库连接对象
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
//name          datetime                datetime2
//zhangzhenkun	1982-10-25 00:00:00.000	1982-10-25 00:00:00