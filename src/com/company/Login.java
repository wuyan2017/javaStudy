package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * Created by wuyan on 2017/8/22.
 */
public  class Login extends JFrame{
    JButton jb1,jb2;
    JTextField jtf;
    JPasswordField jpwd;
    JLabel jl1,jl2;
    JPanel jp1,jp2,jp3;

    Connection conn = null;
    Statement statement = null;
    ResultSet res = null;
    String InName;
    String InPwd;
    public  static String nam,ID,pw;


    public Login() {
        jb1 = new JButton("确认");
        jb2 = new JButton("取消");

        jtf = new JTextField(10);
        jpwd = new JPasswordField(10);

        jl1 = new JLabel("账号：");
        jl2 = new JLabel("密码：");

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        this.setLayout(new GridLayout(3, 1, 5, 5));

        jp1.add(jl1);
        jp1.add(jtf);

        jp2.add(jl2);
        jp2.add(jpwd);

        jp3.add(jb1);
        jp3.add(jb2);

        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        //设置窗体属性
        this.setTitle("登录界面");
        this.setSize(280, 160);
        this.setLocation(800, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //添加按钮事件
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InName=jtf.getText().trim();
                    InPwd=String.valueOf(jpwd.getPassword());
                    System.out.println(InName);
                    System.out.println(InPwd);
                    Driver driver = new com.mysql.jdbc.Driver();
                    DriverManager.registerDriver(driver);
//            2.获取数据库连接
                    String url = "jdbc:mysql://192.168.1.214:3306/test";
                    String user = "root";
                    String password = "chengce214";
                    conn = DriverManager.getConnection(url, user, password);
//            3.获取数据操作的对象
                    statement = conn.createStatement();
//            4.执行SQL语句，DML语句
                    String sql = "select * from test_wuyan where NAME='"+InName+"'";
                    res = statement.executeQuery(sql);
                    while(res.next()) {
                        ID = res.getString(1);
                        nam = res.getString(2);
                        pw=res.getString(6);
                        System.out.println("name:" + nam+",password:"+pw);
                    }
                    if (InName.equals(nam)&&InPwd.equals(pw)){
                        new client();
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"账号或密码错误");
                    }

                } catch (SQLException ee) {
                    ee.printStackTrace();
                } finally {
                    if (res != null) {
                        try {
                            res.close();
                        } catch (SQLException ee) {
                            ee.printStackTrace();
                        }
                    }
                }
            }
        });
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
