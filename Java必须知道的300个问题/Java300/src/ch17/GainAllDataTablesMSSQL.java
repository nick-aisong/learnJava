package ch17;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class GainAllDataTablesMSSQL {
    public static Connection getConn() throws Exception {
        Class.forName("net.sourceforge.jtds.jdbc.Driver"); // 加载数据库驱动类
        Connection conn = null; // 定义数据库连接
        String url = "jdbc:jtds:sqlserver://localhost:1433/db_database17"; // 声明数据库的URL
        String user = "sa"; // 数据库用户
        String password = "123456"; // 数据库密码
        conn = DriverManager.getConnection(url, user, password); // 建立数据库连接
        return conn;
    }

    public static void main(String[] args) {
        GainAllDataTablesMSSQL allTables = new GainAllDataTablesMSSQL();
        allTables.printAllUserTables();
    }

    public void printAllUserTables() {
        Connection conn = null;
        try {
            conn = getConn(); // 获得数据库连接对象
            DatabaseMetaData meta = conn.getMetaData();
            String[] types = {"TABLE"}; // 声明要获得的类型为用户表
            ResultSet rs = meta.getTables(null, null, "%", types); // 获得所有用户表的结果集对象
            while (rs.next()) {
                System.out.println(rs.getString(3));  // 输出用户表的名称
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                conn.close(); // 关闭数据库连接
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

/*
tb_picture
tb_user
 */