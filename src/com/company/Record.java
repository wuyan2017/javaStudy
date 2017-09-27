package com.company;

import javax.swing.*;

import java.awt.*;
import java.sql.*;
import java.util.Vector;

import  static com.company.Login.nam;
/**
 * Created by wuyan on 2017/8/30.
 */
public class Record extends JPanel{
    Connection conn = null;
    Statement statement = null;
    ResultSet r = null;
    int money_record;
    Date p_time1,p_time2,p_time3,p_time4;
    JLabel jl;
    public  Record(){
        try {
            Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
            String url = "jdbc:mysql://192.168.1.214:3306/test";
            String user = "root";
            String password = "chengce214";
            conn = DriverManager.getConnection(url, user, password);
            statement = conn.createStatement();
            String sql = "SELECT * FROM test_wuyan WHERE  NAME ='"+nam+"'";
            r = statement.executeQuery(sql);
            while (r.next()) {
                int money_record0=r.getInt("record");
                int money_record1=r.getInt("guazhang");
                money_record=money_record0+money_record1;
                System.out.println(money_record);
            p_time1=r.getDate("pay_time1");
            p_time2=r.getDate("pay_time2");
            p_time3=r.getDate("pay_time3");
    }
} catch (SQLException e) {
         e.printStackTrace();
        }
        Vector v = new Vector();
        v.add(p_time1.toLocalDate().getMonthValue()+1);

        Object[][] rowData = new Object[3][2];
        rowData = new Object[][]{
        {   String.valueOf(p_time1.toLocalDate().getYear())+"年"+v.elementAt(0)+"月"+
            String.valueOf(p_time1.toLocalDate().getDayOfMonth())+"日", money_record }
        };
final Object[] columnNames = {"还款日期", "已还金额（元）"};
        JTable jt = new JTable(rowData, columnNames);
        jt.setPreferredScrollableViewportSize(new Dimension(600, 200));//设置表格的大小
        jt.setRowHeight(30);//设置每行的高度为30
        jt.setRowHeight(0, 20);//设置第1行的高度为20
        jt.setRowMargin(5);//设置相邻两行单元格的距离
        jt.setRowSelectionAllowed(true);//设置可否被选择.默认为false
        jt.setSelectionBackground(Color.white);//设置所选择行的背景色
        jt.setSelectionForeground(Color.red);//设置所选择行的前景色
        jt.setGridColor(Color.black);//设置网格线的颜色
        jt.setBackground(Color.LIGHT_GRAY);
        JScrollPane pane4 = new JScrollPane(jt);
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.setBackground(Color.black);
        panel.add(pane4);
        this.add(panel);
    }
}
