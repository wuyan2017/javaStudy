package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;
import static com.company.Login.nam;
import static com.company.Record.*;
import static com.company.Repay.money;
/**
 *@Author:吴焰
 *@Date:17:39 2017/9/22
 *@Description:显示还款计划，只展示前5个月的
 */
public class PayPlan extends JPanel {
    JPanel jp,jp1,jp2,jp3,jp4;
    JLabel jl1,jl2,jl3,jl4;
    Connection conn = null;
    Statement statement = null;
    ResultSet res = null;
    static int total,agv,n;//总额，每期应还金额，期数
    static float money1,money2,money3,money4,money5,money6,money7,money8,money9,money10;
    static Date lo_time;
    static float money_guazhang,money_guazhang2,money_guazhang3,
            money_guazhang4,money_guazhang5,money_guazhang6,money_guazhang7,money_guazhang8,money_guazhang9,money_guazhang10;
    static  float record,record2,record3,record4,record5,record6;
    String pay_time1;
    DefaultTableModel model = null;
    public PayPlan(){
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
                total = res.getInt("total");
                n = res.getInt("n");
                agv = res.getInt("agv");
                lo_time = res.getDate("loan_time");
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
                pay_time1=res.getString("pay_time1");
                record=res.getFloat("record");
                record2=res.getFloat("record2");
                record3=res.getFloat("record3");
                record4=res.getFloat("record4");
                record5=res.getFloat("record5");
                System.out.println(pay_time1);
            }
            if(n!=0){

                if(total/n>money_guazhang){
                    money1=total/n;
                    money_record1=money_guazhang;
                }else{
                    money1=0;
                    money_record1=money_guazhang;
                    money_guazhang=0;
                }
                if(total/n>money_guazhang2){
                    money2=total/n;
                    money_record2=money_guazhang2;
                }else{
                    money_record2=money_guazhang2;
                    money2=0;
                    money_guazhang2=0;
                }
                if(total/n>money_guazhang3){
                    money3=total/n;
                    money_record3=money_guazhang3;
                }else{
                    money3=0;
                    money_record3=money_guazhang3;
                    money_record3=0;
                }
                if(total/n>money_guazhang4){
                    money4=total/n;
                    money_record4=money_guazhang4;
                }else{
                    money4=0;
                    money_record4=money_guazhang4;
                    money_guazhang4=0;
                }
                if(total/n>money_guazhang5){
                    money5=total/n;
                    money_record5=money_guazhang5;
                }else{
                    money5=0;
                    money_record5=money_guazhang5;
                    money_guazhang5=0;
                }
                if(total/n>money_guazhang6){
                    money6=total/n;
                    money_record6=money_guazhang6;
                }else{
                    money6=0;
                    money_record6=money_guazhang6;
                    money_guazhang6=0;
                }
                if(total/n>money_guazhang7){
                    money7=total/n;
                    money_record7=money_guazhang7;
                }else{
                    money7=0;
                    money_record7=money_guazhang7;
                    money_guazhang7=0;
                }
                if(total/n>money_guazhang8){
                    money8=total/n;
                    money_record8=money_guazhang8;
                }else{
                    money8=0;
                    money_record8=money_guazhang8;
                    money_guazhang8=0;
                }
                if(total/n>money_guazhang10){
                    money10=total/n;
                    money_record10=money_guazhang10;
                }else{
                    money10=0;
                    money_record10=money_guazhang10;
                    money_guazhang10=0;
                }

            }
            Vector v = new Vector();
           for(int i=0;i<n;i++){
               v.add(lo_time.toLocalDate().getMonthValue()+i+1);
           }
           /**
            *@Author:吴焰
            *@Date:10:37 2017/9/27
            *@Description:在每期后面添加付款按钮
            */
            final Object[] columnNames = {"期数","还款截止日期", "应还本金（元）","已挂账金额（元）"," 还款 "};
            int s=v.size();
            Object[][] rowData = new Object[10][];
            if(n==5||n==9||n==11){
                while(s>4){
                    rowData = new Object[][]{
                            {"1",String.valueOf(lo_time.toLocalDate().getYear())+"年"+v.elementAt(s-5)+"月"+
                                    String.valueOf(lo_time.toLocalDate().getDayOfMonth())+"日", money1,money_guazhang,null},
                            {"2",String.valueOf(lo_time.toLocalDate().getYear())+"年"+v.elementAt(s-4)+"月"+
                                    String.valueOf(lo_time.toLocalDate().getDayOfMonth())+"日",money2,money_guazhang2,null},
                            {"3",String.valueOf(lo_time.toLocalDate().getYear())+"年"+v.elementAt(s-3)+"月"+
                                    String.valueOf(lo_time.toLocalDate().getDayOfMonth())+"日",money3,money_guazhang3,null},
                            {"4",String.valueOf(lo_time.toLocalDate().getYear()+1)+"年"+((Integer)v.elementAt(s-2)-12)+"月"+
                                    String.valueOf(lo_time.toLocalDate().getDayOfMonth())+"日",money4,money_guazhang4,null},
                            {"5",String.valueOf(lo_time.toLocalDate().getYear()+1)+"年"+((Integer)v.elementAt(s-1)-12)+"月"+
                                    String.valueOf(lo_time.toLocalDate().getDayOfMonth())+"日",money5,money_guazhang5,null}
                    };
                    s--;
                }
            }else if(n==6){
                /**
                 *@Author:吴焰
                 *@Date:14:55 2017/9/28
                 *@Description:拓展不同的借款期数
                 *
                 */
                while(s>5){
                    rowData = new Object[][]{
                            {"1",String.valueOf(lo_time.toLocalDate().getYear())+"年"+((Integer)v.elementAt(s-6))+"月"+
                                    String.valueOf(lo_time.toLocalDate().getDayOfMonth())+"日",money1,money_guazhang,null},
                            {"2",String.valueOf(lo_time.toLocalDate().getYear())+"年"+v.elementAt(s-5)+"月"+
                                    String.valueOf(lo_time.toLocalDate().getDayOfMonth())+"日", money2,money_guazhang2,null},
                            {"3",String.valueOf(lo_time.toLocalDate().getYear())+"年"+v.elementAt(s-4)+"月"+
                                    String.valueOf(lo_time.toLocalDate().getDayOfMonth())+"日",money3,money_guazhang3,null},
                            {"4",String.valueOf(lo_time.toLocalDate().getYear()+1)+"年"+((Integer)v.elementAt(s-3)-12)+"月"+
                                    String.valueOf(lo_time.toLocalDate().getDayOfMonth())+"日",money4,money_guazhang4,null},
                            {"5",String.valueOf(lo_time.toLocalDate().getYear()+1)+"年"+((Integer)v.elementAt(s-2)-12)+"月"+
                                    String.valueOf(lo_time.toLocalDate().getDayOfMonth())+"日",money5,money_guazhang5,null},
                            {"6",String.valueOf(lo_time.toLocalDate().getYear()+1)+"年"+((Integer)v.elementAt(s-1)-12)+"月"+
                                    String.valueOf(lo_time.toLocalDate().getDayOfMonth())+"日",money5,money_guazhang6,null}

                    };
                    s--;
                }
            }else if(n==7){
                /**
                 *@Author:吴焰
                 *@Date:14:55 2017/9/28
                 *@Description:
                 * 拓展不同的借款期数
                 *
                 */
                while(s>6){
                    rowData = new Object[][]{
                            {"1",String.valueOf(lo_time.toLocalDate().getYear())+"年"+((Integer)v.elementAt(s-7))+"月"+
                                    String.valueOf(lo_time.toLocalDate().getDayOfMonth())+"日",money1,money_guazhang,null},
                            {"2",String.valueOf(lo_time.toLocalDate().getYear())+"年"+v.elementAt(s-6)+"月"+
                                    String.valueOf(lo_time.toLocalDate().getDayOfMonth())+"日", money2,money_guazhang2,null},
                            {"3",String.valueOf(lo_time.toLocalDate().getYear())+"年"+v.elementAt(s-5)+"月"+
                                    String.valueOf(lo_time.toLocalDate().getDayOfMonth())+"日",money3,money_guazhang3,null},
                            {"4",String.valueOf(lo_time.toLocalDate().getYear()+1)+"年"+((Integer)v.elementAt(s-4)-12)+"月"+
                                    String.valueOf(lo_time.toLocalDate().getDayOfMonth())+"日",money4,money_guazhang4,null},
                            {"5",String.valueOf(lo_time.toLocalDate().getYear()+1)+"年"+((Integer)v.elementAt(s-3)-12)+"月"+
                                    String.valueOf(lo_time.toLocalDate().getDayOfMonth())+"日",money5,money_guazhang5,null},
                            {"6",String.valueOf(lo_time.toLocalDate().getYear()+1)+"年"+((Integer)v.elementAt(s-2)-12)+"月"+
                                    String.valueOf(lo_time.toLocalDate().getDayOfMonth())+"日",money5,money_guazhang6,null},
                            {"7",String.valueOf(lo_time.toLocalDate().getYear()+1)+"年"+((Integer)v.elementAt(s-1)-12)+"月"+
                                    String.valueOf(lo_time.toLocalDate().getDayOfMonth())+"日",money5,money_guazhang7,null}

                    };
                    s--;
                }
            }
            else if(n==8){
                while(s>7){
                    rowData = new Object[][]{
                            {"1",String.valueOf(lo_time.toLocalDate().getYear())+"年"+((Integer)v.elementAt(s-8))+"月"+
                                    String.valueOf(lo_time.toLocalDate().getDayOfMonth())+"日",money1,money_guazhang,null},
                            {"2",String.valueOf(lo_time.toLocalDate().getYear())+"年"+v.elementAt(s-7)+"月"+
                                    String.valueOf(lo_time.toLocalDate().getDayOfMonth())+"日", money2,money_guazhang2,null},
                            {"3",String.valueOf(lo_time.toLocalDate().getYear())+"年"+v.elementAt(s-6)+"月"+
                                    String.valueOf(lo_time.toLocalDate().getDayOfMonth())+"日",money3,money_guazhang3,null},
                            {"4",String.valueOf(lo_time.toLocalDate().getYear())+"年"+v.elementAt(s-5)+"月"+
                                    String.valueOf(lo_time.toLocalDate().getDayOfMonth())+"日",money4,money_guazhang3,null},
                            {"5",String.valueOf(lo_time.toLocalDate().getYear()+1)+"年"+((Integer)v.elementAt(s-4)-12)+"月"+
                                    String.valueOf(lo_time.toLocalDate().getDayOfMonth())+"日",money5,money_guazhang3,null},
                            {"6",String.valueOf(lo_time.toLocalDate().getYear())+1+"年"+((Integer)v.elementAt(s-3)-12)+"月"+
                                    String.valueOf(lo_time.toLocalDate().getDayOfMonth())+"日",money6,money_guazhang4,null},
                            {"7",String.valueOf(lo_time.toLocalDate().getYear()+1)+"年"+((Integer)v.elementAt(s-2)-12)+"月"+
                                    String.valueOf(lo_time.toLocalDate().getDayOfMonth())+"日",money7,money_guazhang5,null},
                            {"8",String.valueOf(lo_time.toLocalDate().getYear()+1)+"年"+((Integer)v.elementAt(s-1)-12)+"月"+
                                    String.valueOf(lo_time.toLocalDate().getDayOfMonth())+"日",money8,money_guazhang6,null}

                    };
                    s--;
                }
            }

            model = new DefaultTableModel(rowData, columnNames);
            JTable jt=new JTable(6,5);
            jt = new JTable(model);
            TableRender render = new TableRender();
            TableEditor editor = new TableEditor(new JTextField());
            jt.getColumnModel().getColumn(4).setCellRenderer(render);
            jt.getColumnModel().getColumn(4).setCellEditor(editor);
            jt.getColumnModel().getColumn(4).setMaxWidth(300);
            editor.setClickCountToStart(0);
            jt.setPreferredScrollableViewportSize(new Dimension(600, 400));//设置表格的大小
            jt.setRowHeight(30);//设置每行的高度为30
            jt.setRowMargin(5);//设置相邻两行单元格的距离
            jt.setRowSelectionAllowed(true);//设置可否被选择.默认为false
            jt.setSelectionBackground(Color.white);//设置所选择行的背景色
            jt.setSelectionForeground(Color.red);//设置所选择行的前景色
            jt.setGridColor(Color.black);//设置网格线的颜色
            jt.setBackground(Color.LIGHT_GRAY);
            JScrollPane pane3 = new JScrollPane(jt);
            JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.setBackground(Color.black);
            panel.add(pane3);
            this.add(panel);

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
