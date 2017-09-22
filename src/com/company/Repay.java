package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static com.company.Login.nam;
import static com.company.PayPlan.money1;

/**
 * Created by wuyan on 2017/8/22.
 */
public class Repay extends JPanel{
    JPanel jp,jp1,jp2;
    JLabel jl1,jl2,jl3;
    JTextField jtf;
    JButton jb1,jb2;

    Connection conn = null;
    Statement statement = null;
    ResultSet res = null;
    ResultSet res2=null;
    int total,n;//总额，每期应还金额，期数
    int record;
    String s;
    static int money;//还款金额
    int tol;//剩余金额
    int re;//sql更新金额update
    public Repay(){
        jl1=new JLabel("还款金额：");
        jl2=new JLabel("元");

        jtf=new JTextField(5);

        jp=new JPanel();
        jp1=new JPanel();
        jp2=new JPanel();

        jb1=new JButton("确定");
        jb2=new JButton("取消");

        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null,"您已取消还款");
            }
        });
        jp1.add(jl1);
        jp1.add(jtf);
        jp1.add(jl2);
        jp2.add(jb1);
        jp2.add(jb2);
        jp.setLayout(new GridLayout(6,10,10,10));
        jp.add(jp1);
        jp.add(jp2);

        this.add(jp);
        this.setVisible(true);
        this.setSize(600,400);
        try{
            Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
//            2.获取数据库连接
            String url = "jdbc:mysql://192.168.1.214:3306/test";
            String user = "root";
            String password = "chengce214";
            conn = DriverManager.getConnection(url, user, password);
//            3.获取数据操作的对象
            statement = conn.createStatement();
//            4.执行SQL语句
            String sql = "SELECT * FROM test_wuyan WHERE  NAME ='"+nam+"'";
            res = statement.executeQuery(sql);
//            5.处理查询结果集
            while (res.next()) {
                total=res.getInt("total");
                //record=res.getInt("record");不能加这个
            }

            jb1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        s=jtf.getText();
                        money=Integer.parseInt(s);
                        if(money<money1){
                            JOptionPane.showMessageDialog(null,"还款金额不够,挂账。...");
                        }
                        tol=total-money;
                        String sql2="update test_wuyan set total='"+tol+"',record=record+'"+money+"',pay_time=now()  WHERE NAME ='"+nam+"'";
                        re = statement.executeUpdate(sql2);
                        System.out.println(record);
                        System.out.println(money);
                        JOptionPane.showMessageDialog(null,"还款成功！");
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            });
        } catch (SQLException ee) {
            ee.printStackTrace();
        } /*finally {
            if (res != null) {
                try {
                    res.close();
                } catch (SQLException ee) {
                    ee.printStackTrace();
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }*/
    }
}
