package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static com.company.LoanInfo.loanNumber;
import static com.company.LoanInfo.sum;
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
    int Max=10000;

    public Loan() {
        jl1 = new JLabel("贷款金额（单位:元）");
        JComboBox comboBox_money=new JComboBox();
        for (int i = 1; i <6 ; i++) {
            comboBox_money.addItem(1000*i);
        }
        comboBox_money.setPreferredSize(new Dimension(70,20));
        comboBox_money.setBackground(Color.white);
        jl4 = new JLabel("计划还款期数（单位:期）");
        JComboBox comboBox=new JComboBox();
        for (int i = 5; i <13 ; i++) {
            comboBox.addItem(i);  //期数选框
        }
        comboBox.setPreferredSize(new Dimension(50,20));
        comboBox.setBackground(Color.white);
        jb1 = new JButton("确认");
        jb2 = new JButton("取消");
        jp1.add(jl1);
        jp1.add(comboBox_money);
        jp4.add(jl4);
        jp4.add(comboBox);
        jp3.add(jb1);
        jp3.add(jb2);
        jp.setLayout(new GridLayout(4,1));
        jp.add(jp1);
        jp.add(jp4);
        jp.add(jp3);
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    String str = comboBox_money.getSelectedItem().toString();
                    in1= Integer.parseInt(str);//贷款金额
                    String str3=comboBox.getSelectedItem().toString();
                    in3=Integer.parseInt(str3);//贷款期数

                    try {
                        Driver driver = new com.mysql.jdbc.Driver();
                        DriverManager.registerDriver(driver);
                        String url = "jdbc:mysql://192.168.1.214:3306/test";
                        String user = "root";
                        String password = "chengce214";
                        conn = DriverManager.getConnection(url, user, password);
                        statement = conn.createStatement();
                        /**
                         *@Author:吴焰
                         *@Date:9:44 2017/9/30
                         *@Description:
                         *
                         * 添加功能：增加可贷款次数，当小于额度时可继续贷款
                         */
                        if(sum+in1<=Max){
                            if(loanNumber==0){
                                sum+=in1;
                                String sql = "UPDATE test_wuyan SET loanNumber='1',sum='"+sum+"',total='"+in1+"',n='"+in3+"',loan_time=now()," +
                                        "guazhang='0',guazhang2='0',guazhang3='0',guazhang4='0',guazhang5='0' WHERE  NAME ='"+nam+"'";
                                statement.executeUpdate(sql);
                                JOptionPane.showMessageDialog(null,"贷款成功！");

                            }else if(loanNumber==1){
                                sum+=in1;
                                String sql = "UPDATE test_wuyan SET loanNumber='2',sum='"+sum+"',total2='"+in1+"',n2='"+in3+"',loan_time2=now() WHERE  NAME ='"+nam+"'";
                                statement.executeUpdate(sql);
                                JOptionPane.showMessageDialog(null,"贷款成功！");
                            }else if(loanNumber==2){
                                sum+=in1;
                                String sql = "UPDATE test_wuyan SET loanNumber='3',sum='"+sum+"',total3='"+in1+"',n3='"+in3+"',loan_time3=now() WHERE  NAME ='"+nam+"'";
                                statement.executeUpdate(sql);
                                JOptionPane.showMessageDialog(null,"贷款成功！");
                            }else if(loanNumber==3){
                                sum+=in1;
                                String sql = "UPDATE test_wuyan SET loanNumber='4',sum='"+sum+"',total4='"+in1+"',n4='"+in3+"',loan_time4=now() WHERE  NAME ='"+nam+"'";
                                statement.executeUpdate(sql);
                                JOptionPane.showMessageDialog(null,"贷款成功！");
                            }else if(loanNumber==4){
                                sum+=in1;
                                String sql = "UPDATE test_wuyan SET loanNumber='5',sum='"+sum+"',total5='"+in1+"',n5='"+in3+"',loan_time5=now() WHERE  NAME ='"+nam+"'";
                                statement.executeUpdate(sql);
                                JOptionPane.showMessageDialog(null,"贷款成功！");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,"已超出可贷总额度(壹万)，贷款失败！");
                        }
                    }
                    catch (SQLException ee) {
                        ee.printStackTrace();
                    }finally {
                        try {
                            if(conn!=null) {
                                conn.close();
                            }
                            statement.close();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
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