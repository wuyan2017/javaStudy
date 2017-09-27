package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import java.util.Date;

import static com.company.Login.nam;
import static com.company.PayPlan.money1;
import static com.company.client.card;
import static com.company.client.jp;

/**
 * Created by wuyan on 2017/8/22.
 */
public class Repay extends JPanel{
    JPanel jp0,jp1,jp2;
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
    Date loan_time,loan_time1,loan_time2;
    int guazhang=0;
    public Repay(){
        jl1=new JLabel("还款金额：");
        jl2=new JLabel("元");

        jtf=new JTextField(5);

        jp0=new JPanel();
        jp1=new JPanel();
        jp2=new JPanel();

        jb1=new JButton("确定");
        jb2=new JButton("返回");

        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null,"确定返回？");
            PayPlan p=new PayPlan();
            jp.add(p,"p");
            card.show(jp,"p");
            }
        });
        jp1.add(jl1);
        jp1.add(jtf);
        jp1.add(jl2);
        jp2.add(jb1);
        jp2.add(jb2);
        jp0.setLayout(new GridLayout(6,10,10,10));
        jp0.add(jp1);
        jp0.add(jp2);

        this.add(jp0);
        this.setVisible(true);
        this.setSize(600,400);
        try{
            Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
            String url = "jdbc:mysql://192.168.1.214:3306/test";
            String user = "root";
            String password = "chengce214";
            conn = DriverManager.getConnection(url, user, password);
            statement = conn.createStatement();
            String sql = "SELECT * FROM test_wuyan WHERE  NAME ='"+nam+"'";
            res = statement.executeQuery(sql);
            while (res.next()) {
                total=res.getInt("total");
                loan_time=res.getTime("loan_time");
                guazhang=guazhang+res.getInt("guazhang");
            }

            jb1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        s=jtf.getText();
                        money=Integer.parseInt(s);
                        if(money<money1){
                            money=guazhang+money;
                            JOptionPane.showMessageDialog(null,"还款金额不够,已挂账...");
                            String sql2="update test_wuyan set guazhang='"+money+"' WHERE NAME ='"+nam+"'";
                            re = statement.executeUpdate(sql2);
                        }else{
                            JOptionPane.showMessageDialog(null,"还款成功！");
                            String sql3="update test_wuyan set record='"+money+"',pay_time1=now()  WHERE NAME ='"+nam+"'";
                            re = statement.executeUpdate(sql3);
                        }
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
