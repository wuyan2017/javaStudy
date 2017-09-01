package com.company;

import javax.swing.*;

import java.sql.*;

import  static com.company.Login.nam;
/**
 * Created by wuyan on 2017/8/30.
 */
public class Record extends JPanel{
    Connection conn = null;
    Statement statement = null;
    ResultSet r = null;
    int money_record;
    JLabel jl;
    public  Record(){
        try {
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
            r = statement.executeQuery(sql);
//            5.处理查询结果集
            while (r.next()) {
                money_record=r.getInt("record");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jl=new JLabel("已还款："+money_record+"元");
        this.add(jl);
        this.setVisible(true);
    }
}
