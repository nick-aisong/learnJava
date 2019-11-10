package ch17;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBDemo {

    public static void main(String[] args) {
        try {
            //加载数据库驱动
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=db_database17", "sa", "123456");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
