package ch17;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SavePictureToDatabase extends JFrame {
    private String picturePath = null;

    public SavePictureToDatabase() {
        super();
        getContentPane().setLayout(null);
        setTitle("在数据库中存储图片");
        setBounds(100, 100, 310, 194);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JButton btn_selectPicture = new JButton();
        btn_selectPicture.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                JFileChooser fileChooser = new JFileChooser(); // 创建文件对话框
                // 创建文件过滤
                FileFilter filter = new FileNameExtensionFilter(
                        "图像文件(*.gif;*.jpg;*.jpeg;*.png)", "gif", "jpg", "jpeg",
                        "png");
                fileChooser.setFileFilter(filter); // 为文件对话框设置文件过滤器
                int returnValue = fileChooser.showOpenDialog(null); // 打开文件选择对话框
                if (returnValue == JFileChooser.APPROVE_OPTION) { // 判断是否选择了文件
                    File file = fileChooser.getSelectedFile(); // 获得文件对象
                    if (file.length() / 1024.0 > 50.0) {
                        JOptionPane
                                .showMessageDialog(null, "请选择小于等于50KB的图片文件。");
                        return;
                    }
                    picturePath = file.getAbsolutePath(); // 获得文件的完整路径
                }
            }
        });
        btn_selectPicture.setText("选择图片");
        btn_selectPicture.setBounds(71, 37, 144, 28);
        getContentPane().add(btn_selectPicture);

        final JButton btn_savePicture = new JButton();
        btn_savePicture.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                Connection conn = null;
                try {
                    conn = getConn(); // 获得数据连接
                    String sql = "insert into tb_picture (name,picture) values(?,?)";// 保存照片的SQL语句
                    // 创建PreparedStatement对象，并传递SQL语句
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, "aa"); // 为第1个参数赋值
                    FileInputStream in = null;
                    if (picturePath != null) {
                        File file = new File(picturePath);
                        in = new FileInputStream(file);
                        ps.setBinaryStream(2, in, (int) file.length());// 为照片字段赋值IO流
                    } else {
                        JOptionPane.showMessageDialog(null, "请选择图片文件。");
                        return;
                    }
                    int flag = ps.executeUpdate(); // 执行SQL语句，获得更新记录数
                    if (flag > 0) {
                        JOptionPane.showMessageDialog(null, "添加成功！");
                    } else {
                        JOptionPane.showMessageDialog(null, "添加失败！");
                    }
                    ps.close();
                    if (in != null) {
                        in.close(); // 关闭IO流
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        conn.close();// 关闭连接
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });
        btn_savePicture.setText("保存图片");
        btn_savePicture.setBounds(71, 89, 144, 28);
        getContentPane().add(btn_savePicture);
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
                    SavePictureToDatabase frame = new SavePictureToDatabase();
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
