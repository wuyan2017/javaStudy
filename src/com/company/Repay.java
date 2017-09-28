package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.util.Date;

import static com.company.Login.nam;
import static com.company.PayPlan.*;
import static com.company.client.card;
import static com.company.client.jp;

/**
 * Created by wuyan on 2017/8/22.
 */
public class Repay extends JPanel{
    JPanel jp0,jp1,jp2,jp3;
    JLabel jl1,jl2,jl3,jl0;
    JTextField jtf;
    JButton jb1,jb2;

    Connection conn = null;
    Statement statement = null;
    ResultSet res = null;
    ResultSet res2=null;
    //int total;//总额，每期应还金额，期数
    int record;
    String s;
    static float money=0;//还款金额
    int tol;//剩余金额
    int re;//sql更新金额update
    Date loan_time,loan_time1,loan_time2;
    static float benxihe;
    static  int time;
    static String sql2;
    static float v_guazhang;
    static  int flag;
    public Repay(){
        /**
         *@Author:吴焰
         *@Date:15:58 2017/9/27
         *@Description:获取本地时间，即付款时间
         */
        Date date=new Date();
        DateFormat d1 = DateFormat.getDateInstance();
        String now=d1.format(date);
        String nowArray[]=now.split("-");
        String loanTime=d1.format(lo_time);
        String loanTimeArray[]=loanTime.split("-");
        int yearDiff=Integer.valueOf(nowArray[0])-Integer.valueOf(loanTimeArray[0]);
        int monthDiff=Integer.valueOf(nowArray[1])-Integer.valueOf(loanTimeArray[1])+12*yearDiff;
        int dayDiff=Integer.valueOf(nowArray[2])-Integer.valueOf(loanTimeArray[2]);
         time=30*monthDiff+dayDiff;
        jl0=new JLabel("应还本金+利息（元）："+benxihe);
        jl1=new JLabel("还款金额（元）：");
        jtf=new JTextField(5);
        jp0=new JPanel();
        jp1=new JPanel();
        jp2=new JPanel();
        jp3=new JPanel();
        jb1=new JButton("还款");
        jb2=new JButton("返回");
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            PayPlan p=new PayPlan();
            jp.add(p,"p");
            card.show(jp,"p");
            }
        });
        jp1.add(jl1);
        jp1.add(jtf);//显示：还款金额（）元
        jp3.add(jl0);
        jp2.add(jb1);
        jp2.add(jb2);
        jp0.setLayout(new GridLayout(5,2,10,10));
        jp0.add(jp3);
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

            }
            float finalBenxihe = benxihe;
            jb1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        s=jtf.getText();
                        money=Float.parseFloat(s);
                        if(money< finalBenxihe){
                            money= v_guazhang+money;
                            JOptionPane.showMessageDialog(null,"还款金额不够,已挂账...");
                            if(flag==1){
                                sql2="update test_wuyan set guazhang='"+money+"' WHERE NAME ='"+nam+"'";
                            }else if(flag==2){
                                sql2="update test_wuyan set guazhang2='"+money+"',pay_time2='"+now+"' WHERE NAME ='"+nam+"'";
                            }else if(flag==3){
                                sql2="update test_wuyan set guazhang3='"+money+"',pay_time3='"+now+"' WHERE NAME ='"+nam+"'";
                            }else if(flag==4){
                                sql2="update test_wuyan set guazhang4='"+money+"',pay_time4='"+now+"' WHERE NAME ='"+nam+"'";
                            }else {
                                sql2="update test_wuyan set guazhang5='"+money+"',pay_time5='"+now+"' WHERE NAME ='"+nam+"'";
                            }
                            re = statement.executeUpdate(sql2);
                        }else{
                            /**
                             *@Author:吴焰
                             *@Date:17:57 2017/9/27
                             *@Description:这里还有问题
                             */
                            JOptionPane.showMessageDialog(null,"还款成功！");
                            money= v_guazhang+money;
                            if(flag==1){
                                String sql3="update test_wuyan set guazhang='"+money+"',pay_time1='"+now+"'  WHERE NAME ='"+nam+"'";
                                re = statement.executeUpdate(sql3);
                            }else if(flag==2){
                                String sql3="update test_wuyan set guazhang2='"+money+"',pay_time2='"+now+"'  WHERE NAME ='"+nam+"'";
                                re = statement.executeUpdate(sql3);
                            }else if(flag==3){
                                String sql3="update test_wuyan set guazhang3='"+money+"',pay_time3='"+now+"'  WHERE NAME ='"+nam+"'";
                                re = statement.executeUpdate(sql3);
                            }else if(flag==4){
                                String sql3="update test_wuyan set guazhang4='"+money+"',pay_time4='"+now+"'  WHERE NAME ='"+nam+"'";
                                re = statement.executeUpdate(sql3);
                            }else {
                                String sql3="update test_wuyan set guazhang5='"+money+"',pay_time5='"+now+"'  WHERE NAME ='"+nam+"'";
                                re = statement.executeUpdate(sql3);
                            }
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
