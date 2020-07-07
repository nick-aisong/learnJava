package ch17;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class GetDatetimeData {
    public GetDatetimeData() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver"); // 加载数据库驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        GetDatetimeData gdd = new GetDatetimeData();
        gdd.getDatetime();
    }

    public void getDatetime() {
        String url = "jdbc:jtds:sqlserver://localhost:1433/db_database17"; // 数据库db_test的URL
        String username = "sa"; // 数据库的用户名
        String password = "123456"; // 数据库密码
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password); // 建立连接
            // 创建Statement对象
            Statement ps = conn.createStatement();
            ResultSet rs = ps.executeQuery("select datetime, datetime2 from tb_datetime");       // 执行SQL语句，获得查询结果集
            while (rs.next() && rs.getRow() > 0) {                 // 遍历结果集
                for (int col = 1; col <= rs.getMetaData().getColumnCount(); col++) {
                    // 获取数据库中的datetime列的数据并转换为字符串，然后去掉时间部分，使其只显示日期
                    String dateString = new Timestamp(rs.getDate(col).getTime()).toString().substring(0, 10);
                    System.out.println("dateString：" + dateString);
                }
            }
            rs.close();
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
