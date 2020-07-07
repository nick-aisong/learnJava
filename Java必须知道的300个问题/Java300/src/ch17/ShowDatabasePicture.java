package ch17;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowDatabasePicture extends JFrame {
    private String picturePath = null;
    private JLabel lbl_picture = null;

    public ShowDatabasePicture() {
        super();
        getContentPane().setLayout(null);
        setTitle("从数据库中读取图片");
        setBounds(100, 100, 252, 210);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JButton btn_showPicture = new JButton();
        btn_showPicture.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                Connection conn = null;
                try {
                    conn = getConn(); // 获得数据连接
                    // 创建PreparedStatement对象ps，并指定要执行的SQL语句，其中conn是数据库连接对象
                    PreparedStatement ps = conn.prepareStatement("select picture from tb_picture where name = 'aa'");
                    ResultSet rs = ps.executeQuery();     // 执行SQL语句，获得查询结果集
                    if (rs.next()) {
                        Blob img = (Blob) rs.getBlob("picture");            // 从结果集中获得图片的Blob对象
                        if (img != null) {
                            // 通过Blob对象全部内容的字节数组创建图标对象icon
                            ImageIcon icon = new ImageIcon(img.getBytes(1, (int) img.length()));
                            // 设置标签上显示的图标为图标对象icon
                            lbl_picture.setIcon(icon);
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        conn.close();// 关闭连接
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        btn_showPicture.setText("显示图片");
        btn_showPicture.setBounds(41, 128, 144, 28);
        getContentPane().add(btn_showPicture);

        lbl_picture = new JLabel();
        lbl_picture.setOpaque(true);
        lbl_picture.setBackground(SystemColor.inactiveCaption);
        lbl_picture.setBounds(41, 10, 144, 102);
        getContentPane().add(lbl_picture);
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
                    ShowDatabasePicture frame = new ShowDatabasePicture();
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
        String url = "jdbc:jtds:sqlserver://localhost:1433/db_database17"; // 声明数据库的URL
        String user = "sa"; // 数据库用户
        String password = "123456"; // 数据库密码
        conn = DriverManager.getConnection(url, user, password); // 建立数据库连接
        return conn;
    }
}
