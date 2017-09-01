package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import  static com.company.Login.nam;

/**
 * Created by wuyan on 2017/8/22.
 */
public class Loan extends JPanel {
    JPanel jp = new JPanel();
    JPanel jp1 = new JPanel();
    JPanel jp2 = new JPanel();
    JPanel jp3 = new JPanel();
    JPanel jp4=new JPanel();
    JLabel jl1, jl2, jl3, jl4;
    JButton jb1, jb2;
    JTextField jf1, jf2,jf3;

    Connection conn = null;
    Statement statement = null;
    int in1,in2,in3;

    public Loan() {
        jl1 = new JLabel("贷款金额");
        jl2 = new JLabel("贷款时间");
        jl3 = new JLabel("月");
        jl4 = new JLabel("计划还款期数");
        jf1 = new JTextField(5);//金额
        jf2 = new JTextField(5);//时间
        jf3 = new JTextField(5);//期数
        jb1 = new JButton("确认");
        jb2 = new JButton("取消");
        jp1.add(jl1);
        jp1.add(jf1);
        jp2.add(jl2);
        jp2.add(jf2);
        jp2.add(jl3);
        jp4.add(jl4);
        jp4.add(jf3);
        jp3.add(jb1);
        jp3.add(jb2);
        jp.setLayout(new GridLayout(6, 10, 10, 20));
        jp.add(jp1);
        jp.add(jp2);
        jp.add(jp4);
        jp.add(jp3);

        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jf1.getText().equals("")||jf2.getText().equals("")||jf3.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"输入错误");
                }
                else {
                    String str = jf1.getText().trim();
                    in1= Integer.parseInt(str);
                    String str2=jf2.getText().trim();
                    in2=Integer.parseInt(str2);
                    String str3=jf3.getText().trim();
                    in3=Integer.parseInt(str3);
                    double num=(in1*in2*30*0.003+in1)/in3;

                    System.out.println(in1);
                    System.out.println(in2);
                    System.out.println(in3);
                    System.out.println(num);
                    JOptionPane.showMessageDialog(null,"您每月需还"+num+"元");
                    try {
                        Driver driver = new com.mysql.jdbc.Driver();
                        DriverManager.registerDriver(driver);
                /* 2.获取数据库连接 */
                        String url = "jdbc:mysql://192.168.1.214:3306/test";
                        String user = "root";
                        String password = "chengce214";
                        conn = DriverManager.getConnection(url, user, password);
                //            3.获取数据操作的对象
                        statement = conn.createStatement();
                        //            4.执行SQL语句
                        String sql = "UPDATE test_wuyan SET  total='"+in1+"',n='"+in2+"',agv='"+in3+"'WHERE  NAME ='"+ nam+"'";
                        statement.executeUpdate(sql);
                    }
                    catch (SQLException ee) {
                        ee.printStackTrace();
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
        this.add(jp);
        this.setVisible(true);
        this.setSize(600, 400);
    }

}