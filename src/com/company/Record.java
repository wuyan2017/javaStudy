package com.company;

import javax.swing.*;

import java.awt.*;
import java.sql.*;
import java.util.Vector;

import static com.company.LoanInfo.*;
import  static com.company.Login.nam;
import static com.company.PayPlan.*;//引入静态变量：monney_guazhang...
import static com.company.LoanInfo.benxihe;
import static com.company.LoanInfo.flag;
import static com.company.LoanInfo.number;

/**
 * Created by wuyan on 2017/8/30.
 */
public class Record extends JPanel{
    Connection conn = null;
    Statement statement = null;
    ResultSet r = null;
    static  float money_record1,money_record2,money_record3,money_record4,
            money_record5,money_record6,money_record7,money_record8,money_record10;
    static String p_time,p_time2,p_time3,p_time4,p_time5;
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
                p_time=r.getString("pay_time");
                p_time2=r.getString("pay_time2");
                p_time3=r.getString("pay_time3");
                p_time4=r.getString("pay_time4");
                p_time5=r.getString("pay_time5");
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Object[][] rowData = new Object[10][2];
        if(number==0){
            rowData=new Object[][]{
                    {"无","无"}
            };
        } else if(number==1){
            rowData=new Object[][]{
                    {pay_time,record }
            };
        }else if(number==2){
            rowData=new Object[][]{
                    {p_time,record },
                    {pay_time02,record2 }
            };
        }else if(number==3){
            rowData=new Object[][]{
                    {p_time,record },
                    {pay_time02,record2 },
                    {pay_time03,record3 }
            };
        }else if(number==4){
            rowData=new Object[][]{
                    {p_time,record },
                    {pay_time02,record2 },
                    {pay_time03,record3 },
                    {pay_time04,record4 },

            };
        }else if(number==5){
            rowData=new Object[][]{
                    {p_time,record },
                    {pay_time02,record2 },
                    {pay_time03,record3 },
                    {pay_time04,record4 },
                    {pay_time05,record5 },
            };
        } else if(number==6){
            rowData=new Object[][]{
                    {p_time,record },
                    {pay_time02,record2 },
                    {pay_time03,record3 },
                    {pay_time04,record4 },
                    {pay_time05,record5 },
                    {pay_time06,record6 },
            };
        }
        else if(number==7){
            rowData=new Object[][]{
                    {p_time, record },
                    {pay_time02,record2 },
                    {pay_time03,record3 },
                    {pay_time04,record4 },
                    {pay_time05,record5 },
                    {pay_time06,record6 },
                    {pay_time07,record7 },
            };
        }

            final Object[] columnNames = { "还款日期", "已还金额（元）"};
            JTable jt = new JTable(rowData, columnNames);
            jt.setPreferredScrollableViewportSize(new Dimension(600, 300));//设置表格的大小
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
