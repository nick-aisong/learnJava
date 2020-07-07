package ch17;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class BackupRestoreMySQL extends JFrame {

    /**
     * Create the frame
     */
    public BackupRestoreMySQL() {
        super();
        getContentPane().setLayout(null);
        setTitle("备份与恢复MySQL数据库");
        setBounds(100, 100, 319, 163);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JButton btn_backup = new JButton();
        btn_backup.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                String username = "root";
                String password = "root";
                String database = "estore";
                String backupFilePath = "d:/estore.sql";
                String backup = "mysqldump -u" + username + " -p" + password + " " + database;
                System.out.println(backup);
                try {
                    Process p = Runtime.getRuntime().exec("cmd.exe /c " + backup);
                    InputStreamReader inReader = new InputStreamReader(p.getInputStream(), "utf8");
                    BufferedReader br = new BufferedReader(inReader);   //创建BufferedReader对象读取控制台数据
                    StringBuffer sb = new StringBuffer("");             //创建StringBuffer对象
                    String line;
                    while ((line = br.readLine()) != null) {                  //循环读取每一行信息
                        sb.append(line + "\r\n");                             //备份信息添加到StringBuffer对象中
                    }
                    FileOutputStream fs = new FileOutputStream(backupFilePath);   //根据指定路径，创建文件输出流
                    OutputStreamWriter writer = new OutputStreamWriter(fs, "utf8");
                    writer.write(sb.toString());                        //将备份数据写入文件
                    writer.flush();                                     // 保存到磁盘设备
                    inReader.close();                                   // 关闭流
                    br.close();                                         // 关闭流
                    writer.close();                                     // 关闭流
                    fs.close();                                         // 关闭流
                    JOptionPane.showMessageDialog(null, "数据库备份成功");
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "数据库备份失败");
                }
            }
        });
        btn_backup.setText("备份数据库");
        btn_backup.setBounds(31, 42, 106, 28);
        getContentPane().add(btn_backup);

        final JButton btn_restore = new JButton();
        btn_restore.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                String username = "root";
                String password = "root";
                String database = "estore";
                String backupFilePath = "d:/estore.sql";
                String restore = "mysql -u" + username + " -p" + password + " " + database;
                try {
                    Process p = Runtime.getRuntime().exec("cmd.exe /c " + restore);// 执行连接数据库命令
                    OutputStream out = p.getOutputStream();          //控制台输出流
                    FileInputStream fs = new FileInputStream(backupFilePath);  //根据指定备份文件创建文件输入流
                    InputStreamReader ir = new InputStreamReader(fs, "utf8");//将字节流转换为utf8字符流
                    BufferedReader br = new BufferedReader(ir);         //用BufferedReader对象读取备份文件信息
                    StringBuffer sb = new StringBuffer("");             // StringBuffer对象用于动态添加每行信息
                    String outLine;
                    while ((outLine = br.readLine()) != null) {               //循环读取备份文件中的每一行数据
                        sb.append(outLine + "\r\n");                  //将每行数据连接成StringBuffer字符串
                    }
                    Writer writer = new BufferedWriter(new OutputStreamWriter(out, "utf8"));
                    writer.write(sb.toString());                        //将备份数据写入控制台中执行
                    writer.flush(); // 保存到磁盘设备
                    ir.close();// 关闭流
                    br.close();// 关闭流
                    writer.close();// 关闭流
                    fs.close();// 关闭流

                    JOptionPane.showMessageDialog(null, "数据库恢复成功");
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "数据库恢复失败");
                }
            }
        });
        btn_restore.setText("恢复数据库");
        btn_restore.setBounds(160, 42, 106, 28);
        getContentPane().add(btn_restore);
        //
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
                    BackupRestoreMySQL frame = new BackupRestoreMySQL();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
