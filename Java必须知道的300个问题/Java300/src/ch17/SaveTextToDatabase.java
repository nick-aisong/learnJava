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

public class SaveTextToDatabase extends JFrame {
    private String filePath = null;

    /**
     * Create the frame
     */
    public SaveTextToDatabase() {
        super();
        getContentPane().setLayout(null);
        setTitle("在数据库中保存大文本文件");
        setBounds(100, 100, 309, 196);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JButton btn_selectFile = new JButton();
        btn_selectFile.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                JFileChooser fileChooser = new JFileChooser(); // 创建文件对话框
                // 创建文件过滤
                FileFilter filter = new FileNameExtensionFilter(
                        "图像文件(*.txt;*.java;*.sql)", "txt", "java", "*.sql");
                fileChooser.setFileFilter(filter); // 为文件对话框设置文件过滤器
                int returnValue = fileChooser.showOpenDialog(null); // 打开文件选择对话框
                if (returnValue == JFileChooser.APPROVE_OPTION) { // 判断是否选择了文件
                    File file = fileChooser.getSelectedFile(); // 获得文件对象
                    filePath = file.getAbsolutePath(); // 获得文件的完整路径
                }
            }
        });
        btn_selectFile.setText("选择文本文件");
        btn_selectFile.setBounds(76, 34, 143, 28);
        getContentPane().add(btn_selectFile);
        final JButton btn_saveFile = new JButton();
        btn_saveFile.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                Connection conn = null;
                try {
                    conn = getConn(); // 获得数据连接
                    String sql = "insert into tb_text ( name , text ) values( ? , ? )";
                    // 创建PreparedStatement对象，并传递SQL语句
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, "小张"); // 通过实体类的实例为第1个参数赋值
                    if (filePath == null) {
                        JOptionPane.showMessageDialog(null, "请选择要保存的文本文件");
                        return;
                    }
                    File file = new File(filePath);
                    FileInputStream in = new FileInputStream(file);
                    ps.setAsciiStream(2, in, (int) file.length());
                    int flag = ps.executeUpdate(); // 执行SQL语句，获得更新记录数
                    if (flag > 0) {
                        JOptionPane.showMessageDialog(null, "添加成功！");
                    } else {
                        JOptionPane.showMessageDialog(null, "添加失败！");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        btn_saveFile.setText("保存文本文件");
        btn_saveFile.setBounds(76, 91, 143, 28);
        getContentPane().add(btn_saveFile);
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
                    SaveTextToDatabase frame = new SaveTextToDatabase();
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
