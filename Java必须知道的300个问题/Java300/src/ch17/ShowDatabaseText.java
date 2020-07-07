package ch17;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowDatabaseText extends JFrame {
    private JTextArea ta_showText;

    /**
     * Create the frame
     */
    public ShowDatabaseText() {
        super();
        getContentPane().setLayout(null);
        setTitle("从数据库中读取大文本信息");
        setBounds(100, 100, 439, 308);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JButton btn_showText = new JButton();
        btn_showText.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                Connection conn = null;
                try {
                    conn = getConn();
                    PreparedStatement ps = conn
                            .prepareStatement("select text from tb_text where name = '小张' ");
                    ResultSet rs = ps.executeQuery(); // 执行SQL语句，获得查询结果集
                    StringBuffer sb = new StringBuffer();
                    if (rs.next()) {
                        InputStream in = rs.getAsciiStream(1);
                        BufferedReader read = new BufferedReader(
                                new InputStreamReader(in));
                        String text = read.readLine();
                        while (text != null) {
                            sb.append(text + "\n");// 读取的内容
                            text = read.readLine();
                        }
                        ta_showText.setText(sb.toString());
                        read.close();
                        in.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        btn_showText.setText("显示文本信息");
        btn_showText.setBounds(138, 229, 143, 28);
        getContentPane().add(btn_showText);

        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(22, 10, 376, 213);
        getContentPane().add(scrollPane);

        ta_showText = new JTextArea();
        scrollPane.setViewportView(ta_showText);
    }

    /**
     * Launch the application
     *
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ShowDatabaseText frame = new ShowDatabaseText();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 获得数据库连接对象的方法
     *
     * @return 返回值为数据库连接对象
     * @throws Exception 抛出异常
     */
    public static Connection getConn() throws Exception {
        Class.forName("net.sourceforge.jtds.jdbc.Driver"); // 加载数据库驱动类
        Connection conn = null; // 定义数据库连接
        String url = "jdbc:jtds:sqlserver://localhost:1433/db_test"; // 声明数据库的URL
        String user = "sa"; // 数据库用户
        String password = ""; // 数据库密码
        conn = DriverManager.getConnection(url, user, password); // 建立数据库连接
        return conn;
    }
}
