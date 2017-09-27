package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

import static com.company.Login.nam;
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
    int total,agv,n;//总额，每期应还金额，期数
    static float money1,money2,money3,money4,money5;
    Date lo_time;
    float money_guazhang;
    int record;
    Date pay_time1;
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
                money_guazhang=res.getInt("guazhang");
                record=res.getInt("record");
                pay_time1=res.getDate("pay_time1");
            }
            if (pay_time1.toLocalDate().getMonthValue()>lo_time.toLocalDate().getMonthValue()+1){
                money2=(float) (total  * 30 * 0.005+((total-total/n)*30*0.003+total/n));
            } else if(n!=0){
                money1 = (float) (total  * 30 * 0.003 + total/n-money_guazhang-record);
                money2= (float) ((total-total/n)*30*0.003+total/n);
                money3= (float) ((total-total/n*2)*30*0.003+total/n);
                money4= (float) ((total-total/n*3)*30*0.003+total/n);
                money5= (float) ((total-total/n*4)*30*0.003+total/n);
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
            final Object[] columnNames = {"应还款日期", "应还金额（元）"," 还款 "};
            int s=v.size();
            Object[][] rowData = new Object[5][];
            while(s>4){
                rowData = new Object[][]{
                        {String.valueOf(lo_time.toLocalDate().getYear())+"年"+v.elementAt(s-5)+"月"+
                                String.valueOf(lo_time.toLocalDate().getDayOfMonth())+"日", money1,null},
                        {String.valueOf(lo_time.toLocalDate().getYear())+"年"+v.elementAt(s-4)+"月"+
                        String.valueOf(lo_time.toLocalDate().getDayOfMonth())+"日",money2,null},
                        {String.valueOf(lo_time.toLocalDate().getYear())+"年"+v.elementAt(s-3)+"月"+
                                String.valueOf(lo_time.toLocalDate().getDayOfMonth())+"日",money3,null},
                        {String.valueOf(lo_time.toLocalDate().getYear()+1)+"年"+((Integer)v.elementAt(s-2)-12)+"月"+
                                String.valueOf(lo_time.toLocalDate().getDayOfMonth())+"日",money4,null},
                        {String.valueOf(lo_time.toLocalDate().getYear()+1)+"年"+((Integer)v.elementAt(s-1)-12)+"月"+
                                String.valueOf(lo_time.toLocalDate().getDayOfMonth())+"日",money5,null}
                };
                s--;
            }
            //JTable jt = new JTable(rowData, columnNames);
            model = new DefaultTableModel(rowData, columnNames);
            JTable jt=new JTable(6,3);
            jt = new JTable(model);
            TableRender render = new TableRender();
            TableEditor editor = new TableEditor(new JTextField());
            jt.getColumnModel().getColumn(2).setCellRenderer(render);
            jt.getColumnModel().getColumn(2).setCellEditor(editor);
            jt.getColumnModel().getColumn(2).setMaxWidth(300);
            editor.setClickCountToStart(0);
            jt.setPreferredScrollableViewportSize(new Dimension(600, 200));//设置表格的大小
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
