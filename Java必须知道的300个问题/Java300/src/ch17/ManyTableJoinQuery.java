package ch17;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ManyTableJoinQuery {
    public static Connection getConn() throws Exception {
        Class.forName("net.sourceforge.jtds.jdbc.Driver"); // 加载数据库驱动类
        Connection conn = null; // 定义数据库连接
        String url = "jdbc:jtds:sqlserver://localhost:1433/db_test"; // 声明数据库的URL
        String user = "sa"; // 数据库用户
        String password = ""; // 数据库密码
        conn = DriverManager.getConnection(url, user, password); // 建立数据库连接
        return conn;
    }

    public static void main(String[] args) {
        ManyTableJoinQuery joinQuery = new ManyTableJoinQuery();
        joinQuery.manyTablesQuery();
    }

    public void manyTablesQuery() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = getConn(); // 获得数据库连接对象
            st = conn.createStatement();// 创建Statement对象
            // 进行多表连接查询的SQL语句
            String sql = "select tb_employee.id,tb_employee.name,tb_employee.sex,tb_employee.age,tb_special.memo from tb_employee,tb_special where tb_employee.id = tb_special.id";
            rs = st.executeQuery(sql); // 获得查询结果集
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  "
                        + rs.getString(3) + "  " + rs.getInt(4) + "  "
                        + rs.getString(5));
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
