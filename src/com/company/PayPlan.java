package com.company;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

import static com.company.Login.nam;

/**
 * Created by wuyan on 2017/8/24.
 */
public class PayPlan extends JPanel {
    JPanel jp,jp1,jp2,jp3,jp4;
    JLabel jl1,jl2,jl3,jl4;
    Connection conn = null;
    Statement statement = null;
    ResultSet res = null;
    int total,agv,n;//总额，每期应还金额，期数
    double money;
    public PayPlan(){

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
            String sql = "select * from test_wuyan WHERE  NAME ='"+nam+"'";
            res = statement.executeQuery(sql);
//            5.处理查询结果集
            while (res.next()) {
                total=res.getInt("total");
                n=res.getInt("n");
                agv=res.getInt("agv");
            }
            money=(total*n*30*0.003+total)/n;
            jl1=new JLabel("您总共借款"+total+"元");
            jl4=new JLabel("您的借款时间："+agv+"个月");
            jl2=new JLabel("计划还款期数："+n);
            jl3=new JLabel("您每月需还："+money+"元");

            jp=new JPanel();
            jp1=new JPanel();
            jp2=new JPanel();
            jp3=new JPanel();
            jp4 = new JPanel();

            jp1.add(jl1);
            jp4.add(jl4);
            jp2.add(jl2);
            jp3.add(jl3);

            jp.setLayout(new GridLayout(6,10,10,10));
            jp.add(jp1);
            jp.add(jp4);
            jp.add(jp2);
            jp.add(jp3);

            this.add(jp);
            this.setVisible(true);
            this.setSize(600,400);

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
}
