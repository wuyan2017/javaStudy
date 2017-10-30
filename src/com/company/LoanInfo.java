package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.*;

import static com.company.Login.nam;
import static com.company.PayPlan.v_n;
import static com.company.PayPlan.v_total;
import static com.company.Repay.time;


/**
 * Created by wuyan on 2017/9/30.
 */
public class LoanInfo extends JPanel{
    Connection conn = null;
    Statement statement = null;
    ResultSet res = null;
    static int loanNumber,sum,total,total2,total3,total4,total5,agv,n,n2,n3,n4,n5;//总额，每期应还金额，期数
    static float money1,money2,money3,money4,money5,money6,money7,money8,money9,money10;
    static Date lo_time,lo_time2,lo_time3,lo_time4,lo_time5;
    static float money_guazhang,money_guazhang2,money_guazhang3,
            money_guazhang4,money_guazhang5,money_guazhang6,money_guazhang7,money_guazhang8,money_guazhang9,money_guazhang10,
            Second_money_guazhang01,Second_money_guazhang02,Second_money_guazhang03,Second_money_guazhang04,Second_money_guazhang05,Second_money_guazhang06,
            Third_money_guazhang01,Third_money_guazhang02,Third_money_guazhang03,Third_money_guazhang04,Third_money_guazhang05,
            Fourth_monney_guazhang01,Fourth_monney_guazhang02,Fourth_monney_guazhang03,Fourth_monney_guazhang04,Fourth_monney_guazhang05,
            Fifth_money_guazhang;
    static  float record,record2,record3,record4,record5,record6,record7,record8,record9;
    String pay_time1;
    DefaultTableModel model = null;
    static  int mf1,mf2,mf3,mf4,mf5;
    static  int sc_mf1,sc_mf2,sc_mf3,sc_mf4,sc_mf5;
    static  int third_mf1,third_mf2,third_mf3,third_mf4,third_mf5;
    static Date pay_time,pay_time02,pay_time03,pay_time04,pay_time05,pay_time06,pay_time07,pay_time08,pay_time09;
    static  int number=0;//表示还款的次数
    static float benxihe;
    static  int v_flag,flag,sc_flag,third_flag;
    public LoanInfo(){
        try {
            Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
            String url = "jdbc:mysql://192.168.1.214:3306/test";
            String user = "root";
            String password = "chengce214";
            conn = DriverManager.getConnection(url, user, password);
            statement = conn.createStatement();
            String sql = "select * from test_wuyan WHERE  NAME ='" + nam + "'";
            res = statement.executeQuery(sql);
            while (res.next()) {
                sum=res.getInt("sum");
                total = res.getInt("total");
                total2=res.getInt("total2");
                total3=res.getInt("total3");
                total4=res.getInt("total4");
                total5=res.getInt("total5");
                n= res.getInt("n");
                n2=res.getInt("n2");
                n3= res.getInt("n3");
                n4= res.getInt("n4");
                n5= res.getInt("n5");
                agv = res.getInt("agv");
                lo_time = res.getDate("loan_time");
                lo_time2=res.getDate("loan_time2");
                lo_time3=res.getDate("loan_time3");
                lo_time4=res.getDate("loan_time4");
                lo_time5=res.getDate("loan_time5");
                money_guazhang=res.getFloat("guazhang");
                money_guazhang2=res.getFloat("guazhang2");
                money_guazhang3=res.getFloat("guazhang3");
                money_guazhang4=res.getFloat("guazhang4");
                money_guazhang5=res.getFloat("guazhang5");
                money_guazhang6=res.getFloat("guazhang6");
                money_guazhang7=res.getFloat("guazhang7");
                money_guazhang8=res.getFloat("guazhang8");
                money_guazhang9=res.getFloat("guazhang9");
                money_guazhang10=res.getFloat("guazhang10");

                /**
                 *@Author:吴焰
                 *@Date:14:06 2017/9/30
                 *@Description:
                 */
                Second_money_guazhang01=res.getFloat("sc_guazhang01");
                Second_money_guazhang02=res.getFloat("sc_guazhang02");
                Second_money_guazhang03=res.getFloat("sc_guazhang03");
                Second_money_guazhang04=res.getFloat("sc_guazhang04");
                Second_money_guazhang05=res.getFloat("sc_guazhang05");


                Third_money_guazhang01=res.getFloat("third_guazhang01");
                Third_money_guazhang02=res.getFloat("third_guazhang02");
                Third_money_guazhang03=res.getFloat("third_guazhang03");

                Fourth_monney_guazhang01=res.getFloat("four_guazhang01");
                Fourth_monney_guazhang02=res.getFloat("four_guazhang02");
                Fourth_monney_guazhang03=res.getFloat("four_guazhang03");
                Fourth_monney_guazhang04=res.getFloat("four_guazhang04");
                Fourth_monney_guazhang05=res.getFloat("four_guazhang05");
                Fifth_money_guazhang=res.getFloat("five_guazhang");

                pay_time1=res.getString("pay_time1");

                pay_time=res.getDate("pay_time");
                pay_time02=res.getDate("pay_time02");
                pay_time03=res.getDate("pay_time03");
                pay_time04=res.getDate("pay_time04");
                pay_time05=res.getDate("pay_time05");
                pay_time06=res.getDate("pay_time06");
                pay_time07=res.getDate("pay_time07");
                pay_time08=res.getDate("pay_time08");
                pay_time09=res.getDate("pay_time09");

                record=res.getFloat("record");
                record2=res.getFloat("record2");
                record3=res.getFloat("record3");
                record4=res.getFloat("record4");
                record5=res.getFloat("record5");
                record6=res.getFloat("record6");
                record7=res.getFloat("record7");
                record8=res.getFloat("record8");
                record9=res.getFloat("record9");
                loanNumber=res.getInt("loanNumber");
                mf1=res.getInt("mf1");
                mf2=res.getInt("mf2");
                mf3=res.getInt("mf3");
                mf4=res.getInt("mf4");
                mf5=res.getInt("mf5");
                sc_mf1=res.getInt("sc_mf1");
                sc_mf2=res.getInt("sc_mf2");
                sc_mf3=res.getInt("sc_mf3");
                sc_mf4=res.getInt("sc_mf4");
                sc_mf5=res.getInt("sc_mf5");
                third_mf1=res.getInt("third_mf1");
                third_mf2=res.getInt("third_mf2");
                third_mf3=res.getInt("third_mf3");
                third_mf4=res.getInt("third_mf4");
                third_mf5=res.getInt("third_mf5");

                number=res.getInt("number");
            }
            Object[][] rowData = new Object[10][];
            if(loanNumber==0){
                rowData = new Object[][]{
                        {  "无","无", "无","无"},
                };
            }
            else if(loanNumber==1){
                rowData = new Object[][]{
                        {  "1",lo_time, total,null},

                };
            }else if(loanNumber==2){
                rowData = new Object[][]{
                        {  "1",lo_time, total,null},
                        {  "2", lo_time2,total2,null},
                };
            }else if(loanNumber==3){
                rowData = new Object[][]{
                        {  "1",lo_time, total,null},
                        {  "2", lo_time2,total2,null},
                        {  "3",lo_time3, total3,null},
                };
            }else if(loanNumber==4){
                rowData = new Object[][]{
                        {  "1",lo_time, total,null},
                        {  "2", lo_time2,total2,null},
                        {  "3",lo_time3, total3,null},
                        {  "4",lo_time4, total4,null},
                };
            }else if(loanNumber==5){
                rowData = new Object[][]{
                        {  "1",lo_time, total,null},
                        {  "2", lo_time2,total2,null},
                        {  "3",lo_time3, total3,null},
                        {  "4",lo_time4, total4,null},
                        {  "5", lo_time5, total5,null}
                };
            }

            final Object[] columnNames = {" 借款次序 ", "借款日期", "借款金额（元）","还款计划"};
            model = new DefaultTableModel(rowData, columnNames);
            JTable jt = new JTable(5, 4);
            jt = new JTable(model);
            TableRender01 render01 = new TableRender01();
            TableEditor01 editor01 = new TableEditor01(new JTextField());
            jt.getColumnModel().getColumn(3).setCellRenderer(render01);
            jt.getColumnModel().getColumn(3).setCellEditor(editor01);
            jt.getColumnModel().getColumn(3).setMaxWidth(400);
            jt.setPreferredScrollableViewportSize(new Dimension(600, 300));//设置表格的大小
            jt.setRowHeight(30);//设置每行的高度为30
            //jt.setRowHeight(0, 20);//设置第1行的高度为20
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

