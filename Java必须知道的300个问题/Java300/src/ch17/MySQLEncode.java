package ch17;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLEncode {
    private Connection conn = null;
    private Statement st = null;
    private ResultSet rs = null;

    // (4)在主方法中进行测试
    public static void main(String[] args) {
        String sql = "insert into tb_test(name,price,amount) values('冰箱',3800.0,3)";
        MySQLEncode encode = new MySQLEncode();
        encode.saveData(sql);                 // 调用方法保存数据记录
        String qsql = "select * from tb_test";
        encode.queryData(qsql);               // 调用方法查询数据记录
    }

    // (1)获得数据库连接对象的方法
    public Connection getConn() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");  // 加载数据库驱动类
        Connection conn = null;                // 定义数据库连接
        // 声明数据库的URL，并指定编码
        String url = "jdbc:mysql://localhost:3306/db_test?useUnicode=true&characterEncoding=utf-8";
        String user = "root";                   // 数据库用户
        String password = "111";               // 数据库密码
        conn = DriverManager.getConnection(url, user, password); // 建立数据库连接
        return conn;
    }

    // (2)保存数据的方法
    public void saveData(String sql) {
        try {
            conn = getConn();
            st = conn.createStatement();
            st.executeUpdate(sql);           // 执行SQL语句
            System.out.println("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("保存失败");
        } finally {
            try {
                conn.close();               // 关闭数据库连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // (3)查询数据的方法
    public void queryData(String sql) {
        try {
            conn = getConn();               // 获得数据库连接对象
            st = conn.createStatement();       // 创建Statement对象
            rs = st.executeQuery(sql);         // 通过指定的SQL语句获得结果集对象
            while (rs.next()) {
                System.out.println(rs.getInt(1));          // 输出查询结果
                System.out.println(rs.getString(2).trim());  // 输出查询结果
                System.out.println(rs.getFloat(3));        // 输出查询结果
                System.out.println(rs.getInt(4));         // 输出查询结果
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                conn.close();                // 关闭数据库连接对象
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}