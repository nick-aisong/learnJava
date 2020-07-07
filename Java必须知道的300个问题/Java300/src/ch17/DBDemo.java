package ch17;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DBDemo {

    public static void main(String[] args) {
        demo4();
    }

    // 问题283 Java程序如何实现调用存储过程?
    public static void demo1() {
        try {
            //加载数据库驱动
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //获取数据库连接
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=db_database17", "sa", "123456");
            //调用存储过程
            CallableStatement stmt = con.prepareCall("{call insertProcedure()}");
            //执行存储过程
            stmt.execute();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 问题283 Java程序如何实现调用存储过程?
    public static void demo2() {
        try {
            //加载数据库驱动
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //获取数据库连接
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=db_database17", "sa", "123456");
            String sql = "{call validateSelect('mr','mrsoft')}";
            //调用存储过程
            CallableStatement cs = con.prepareCall(sql);
            //获取结果集
            ResultSet rest = cs.executeQuery();
            //循环遍历结果集对象
            while (rest.next()) {
                System.out.println("验证成功");
            }
            //执行存储过程
            cs.execute();
            cs.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 问题287 如何通过JDBC控制数据库事务?
    public static void demo3() {
        Connection con = null;
        try {
            //加载数据库驱动
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //获取数据库连接
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=db_database17", "sa", "123456");
            //创建Statement对象
            Statement stmt = con.createStatement();
            //定义更新数据语句
            stmt.execute("update tb_user set name = 'mr' where id = 1");
            stmt.execute("update tb_order set sname = 'mr' where id = 1");
            //数据提交
            con.commit();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                //出现异常， 回滚事务
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    // 问题288 Java程序如何获得表结构?
    public static void demo4() {
        tableStructInfo();
//        列名: id	数据类型: varchar
//        列名: name	数据类型: varchar
//        列名: password	数据类型: varchar
    }


    //(1)连接数据库的方法。
    public static Connection getConn() throws Exception {
        Class.forName("net.sourceforge.jtds.jdbc.Driver"); //加载数据库驱动类
        Connection conn = null; //定义数据库连接
        String url = "jdbc:jtds:sqlserver://localhost:1433/db_database17"; //声明数据库的URL
        String user = "sa"; //数据库用户
        String password = "123456"; //数据库密码
        conn = DriverManager.getConnection(url, user, password); //建立数据库连接
        return conn;
    }

    //(2)获得表结果信息方法。
    public static void tableStructInfo() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = getConn(); //获得数据库连接对象
            st = conn.createStatement(); //创建Statement对象
            String sql = "select * from tb_user"; //查询的SQL语句
            rs = st.executeQuery(sql); //获得查询结果集
            ResultSetMetaData rsmd = rs.getMetaData();
            int colCounts = rsmd.getColumnCount(); //获得字段总数
            for (int col = 1; col <= colCounts; col++) {
                //输出表结构中的列名和数据类型
                System.out.println("列名: " + rsmd.getColumnName(col) + "\t数据类型: " + rsmd.getColumnTypeName(col));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                // 这样关闭不太好，这里只是做演示
                rs.close();
                st.close();
                conn.close(); //关闭数据库连接
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

