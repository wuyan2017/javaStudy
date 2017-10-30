package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import java.sql.Date;
import java.util.Vector;

import static com.company.LoanInfo.*;
import static com.company.LoanInfo.third_mf5;
import static com.company.Record.*;
import static com.company.Repay.*;

/**
 *@Author:吴焰
 *@Date:17:39 2017/9/22
 *@Description:显示还款计划，只展示前5个月的
 */
public class PayPlan extends JPanel {
    DefaultTableModel model = null;
    static int v_total,v_n;
    static float v_money_guazhang,v_money_guazhang2,v_money_guazhang3,v_money_guazhang4,v_money_guazhang5,
            v_money_guazhang6,v_money_guazhang7,v_money_guazhang8,v_money_guazhang9,v_money_guazhang10,
            v_money1,v_money2,v_money3,v_money4,v_money5,v_money6,v_money7,v_money8,v_money9,v_money10,
            v_money_record1,v_money_record2,v_money_record3,v_money_record4,v_money_record5,v_money_record6,
            v_money_record7,v_money_record8,v_money_record9,v_money_record10;
    static Date v_lotime;
    static int v_money_flag=1;
    static  int v_mf1,v_mf2,v_mf3,v_mf4,v_mf5;
    public PayPlan(){
            if(v_n!=0){
                /**
                 *@Author:吴焰
                 *@Date:17:56 2017/9/30
                 *@Description:
                 *
                 * 重点修改：mf1=1，表示第一期已经还完
                 * v_mf 表示：还款的期数
                 * 当v_mf=mf时，是第一次借款的还款的标示
                 * 当v_mf=sc_mf时，表示第二次借款还款的标示
                 */
                if(no==1){
                    v_mf1=mf1;
                    v_mf2=mf2;
                    v_mf3=mf3;
                    v_mf4=mf4;
                    v_mf5=mf5;
                    v_flag=flag;
                }else if(no==2){
                    v_mf1=sc_mf1;
                    v_mf2=sc_mf2;
                    v_mf3=sc_mf3;
                    v_mf4=sc_mf4;
                    v_mf5=sc_mf5;
                    v_flag=sc_flag;

                    v_n=n2;
                }else if(no==3){
                    v_mf1=third_mf1;
                    v_mf2=third_mf2;
                    v_mf3=third_mf3;
                    v_mf4=third_mf4;
                    v_mf5=third_mf5;
                    v_flag=third_flag;
                }
                if(v_mf1==0){
                    v_money1=v_total/v_n;
                    money_record1=v_money_guazhang;
                }else{
                    v_money1=0;
                    money_record1=v_money_guazhang;
                    v_money_guazhang=0;
                }
                if(v_mf2==0){
                    v_money2=v_total/v_n;
                    money_record2=v_money_guazhang2;
                }else{
                    v_money_record2=v_money_guazhang2;
                    v_money2=0;
                    v_money_guazhang2=0;
                }
                if(v_mf3==0){
                    v_money3=v_total/v_n;
                    money_record3=v_money_guazhang3;
                }else{
                    v_money_record3=v_money_guazhang3;
                    v_money3=0;
                    v_money_guazhang3=0;
                }
                if(v_mf4==0){
                    v_money4=v_total/v_n;
                    money_record4=v_money_guazhang4;
                }else{
                    v_money_record4=v_money_guazhang4;
                    v_money4=0;
                    v_money_guazhang4=0;
                }
                if(v_mf5==0){
                    v_money5=v_total/v_n;
                    money_record5=v_money_guazhang5;
                }else{
                    v_money_record5=v_money_guazhang5;
                    v_money5=0;
                    v_money_guazhang5=0;
                }
            }
            Vector v = new Vector();
           for(int i=0;i<n;i++){
               v.add(v_lotime.toLocalDate().getMonthValue()+i+1);
           }
           /**
            *@Author:吴焰
            *@Date:10:37 2017/9/27
            *@Description:在每期后面添加付款按钮
            */
            final Object[] columnNames = {"期数","还款截止日期", "应还本金（元）","已挂账金额（元）"," 还款 "};
            int s=v.size();
            Object[][] rowData = new Object[10][];
            if(v_n==5||v_n==9||v_n==11){
                while(s>4){
                    rowData = new Object[][]{
                            {"1",String.valueOf(v_lotime.toLocalDate().getYear())+"年"+v.elementAt(s-5)+"月"+
                                    String.valueOf(v_lotime.toLocalDate().getDayOfMonth())+"日", String.format("%.2f",v_money1),String.format("%.2f",v_money_guazhang),null},
                            {"2",String.valueOf(v_lotime.toLocalDate().getYear())+"年"+v.elementAt(s-4)+"月"+
                                    String.valueOf(v_lotime.toLocalDate().getDayOfMonth())+"日",String.format("%.2f",v_money2),String.format("%.2f",v_money_guazhang2),null},
                            {"3",String.valueOf(v_lotime.toLocalDate().getYear()+1)+"年"+((Integer)v.elementAt(s-3)-12)+"月"+
                                    String.valueOf(v_lotime.toLocalDate().getDayOfMonth())+"日",String.format("%.2f",v_money3),String.format("%.2f",v_money_guazhang3),null},
                            {"4",String.valueOf(v_lotime.toLocalDate().getYear()+1)+"年"+((Integer)v.elementAt(s-2)-12)+"月"+
                                    String.valueOf(v_lotime.toLocalDate().getDayOfMonth())+"日",String.format("%.2f",v_money4),String.format("%.2f",v_money_guazhang4),null},
                            {"5",String.valueOf(v_lotime.toLocalDate().getYear()+1)+"年"+((Integer)v.elementAt(s-1)-12)+"月"+
                                    String.valueOf(v_lotime.toLocalDate().getDayOfMonth())+"日",String.format("%.2f",v_money5),String.format("%.2f",v_money_guazhang5),null}
                    };
                    s--;
                }
            }else if(v_n==6){
                /**
                 *@Author:吴焰
                 *@Date:14:55 2017/9/28
                 *@Description:拓展不同的借款期数
                 *
                 */
                while(s>5){
                    rowData = new Object[][]{
                            {"1",String.valueOf(v_lotime.toLocalDate().getYear())+"年"+(v.elementAt(s-6))+"月"+
                                    String.valueOf(v_lotime.toLocalDate().getDayOfMonth())+"日",String.format("%.2f",v_money1),String.format("%.2f",v_money_guazhang),null},
                            {"2",String.valueOf(v_lotime.toLocalDate().getYear())+"年"+v.elementAt(s-5)+"月"+
                                    String.valueOf(v_lotime.toLocalDate().getDayOfMonth())+"日", String.format("%.2f",v_money2),String.format("%.2f",v_money_guazhang2),null},
                            {"3",String.valueOf(v_lotime.toLocalDate().getYear()+1)+"年"+((Integer)v.elementAt(s-4)-12)+"月"+
                                    String.valueOf(v_lotime.toLocalDate().getDayOfMonth())+"日",String.format("%.2f",v_money3),String.format("%.2f",v_money_guazhang3),null},
                            {"4",String.valueOf(v_lotime.toLocalDate().getYear()+1)+"年"+((Integer)v.elementAt(s-3)-12)+"月"+
                                    String.valueOf(v_lotime.toLocalDate().getDayOfMonth())+"日",String.format("%.2f",v_money4),String.format("%.2f",v_money_guazhang4),null},
                            {"5",String.valueOf(v_lotime.toLocalDate().getYear()+1)+"年"+((Integer)v.elementAt(s-2)-12)+"月"+
                                    String.valueOf(v_lotime.toLocalDate().getDayOfMonth())+"日",String.format("%.2f",v_money5),String.format("%.2f",v_money_guazhang5),null},
                            {"6",String.valueOf(v_lotime.toLocalDate().getYear()+1)+"年"+((Integer)v.elementAt(s-1)-12)+"月"+
                                    String.valueOf(v_lotime.toLocalDate().getDayOfMonth())+"日",String.format("%.2f",v_money5),String.format("%.2f",v_money_guazhang6),null}

                    };
                    s--;
                }
            }else if(v_n==7){
                /**
                 *@Author:吴焰
                 *@Date:14:55 2017/9/28
                 *@Description:
                 * 拓展不同的借款期数
                 *
                 */
                while(s>6){
                    rowData = new Object[][]{
                            {"1",String.valueOf(v_lotime.toLocalDate().getYear())+"年"+((Integer)v.elementAt(s-7))+"月"+
                                    String.valueOf(v_lotime.toLocalDate().getDayOfMonth())+"日",v_money1,money_guazhang,null},
                            {"2",String.valueOf(v_lotime.toLocalDate().getYear())+"年"+v.elementAt(s-6)+"月"+
                                    String.valueOf(v_lotime.toLocalDate().getDayOfMonth())+"日", v_money2,money_guazhang2,null},
                            {"3",String.valueOf(v_lotime.toLocalDate().getYear()+1)+"年"+((Integer)v.elementAt(s-5)-12)+"月"+
                                    String.valueOf(v_lotime.toLocalDate().getDayOfMonth())+"日",v_money3,v_money_guazhang3,null},
                            {"4",String.valueOf(v_lotime.toLocalDate().getYear()+1)+"年"+((Integer)v.elementAt(s-4)-12)+"月"+
                                    String.valueOf(v_lotime.toLocalDate().getDayOfMonth())+"日",v_money4,v_money_guazhang4,null},
                            {"5",String.valueOf(v_lotime.toLocalDate().getYear()+1)+"年"+((Integer)v.elementAt(s-3)-12)+"月"+
                                    String.valueOf(v_lotime.toLocalDate().getDayOfMonth())+"日",v_money5,v_money_guazhang5,null},
                            {"6",String.valueOf(v_lotime.toLocalDate().getYear()+1)+"年"+((Integer)v.elementAt(s-2)-12)+"月"+
                                    String.valueOf(v_lotime.toLocalDate().getDayOfMonth())+"日",v_money5,v_money_guazhang6,null},
                            {"7",String.valueOf(v_lotime.toLocalDate().getYear()+1)+"年"+((Integer)v.elementAt(s-1)-12)+"月"+
                                    String.valueOf(v_lotime.toLocalDate().getDayOfMonth())+"日",v_money5,v_money_guazhang7,null}

                    };
                    s--;
                }
            }
            else if(v_n==8){
                while(s>7){
                    rowData = new Object[][]{
                            {"1",String.valueOf(v_lotime.toLocalDate().getYear())+"年"+((Integer)v.elementAt(s-8))+"月"+
                                    String.valueOf(v_lotime.toLocalDate().getDayOfMonth())+"日",v_money1,money_guazhang,null},
                            {"2",String.valueOf(v_lotime.toLocalDate().getYear())+"年"+v.elementAt(s-7)+"月"+
                                    String.valueOf(v_lotime.toLocalDate().getDayOfMonth())+"日", v_money2,money_guazhang2,null},
                            {"3",String.valueOf(v_lotime.toLocalDate().getYear())+"年"+((Integer)v.elementAt(s-6)-12)+"月"+
                                    String.valueOf(v_lotime.toLocalDate().getDayOfMonth())+"日",v_money3,v_money_guazhang3,null},
                            {"4",String.valueOf(v_lotime.toLocalDate().getYear())+"年"+((Integer)v.elementAt(s-5)-12)+"月"+
                                    String.valueOf(v_lotime.toLocalDate().getDayOfMonth())+"日",v_money4,v_money_guazhang3,null},
                            {"5",String.valueOf(v_lotime.toLocalDate().getYear()+1)+"年"+((Integer)v.elementAt(s-4)-12)+"月"+
                                    String.valueOf(v_lotime.toLocalDate().getDayOfMonth())+"日",v_money5,v_money_guazhang3,null},
                            {"6",String.valueOf(v_lotime.toLocalDate().getYear()+1)+"年"+((Integer)v.elementAt(s-3)-12)+"月"+
                                    String.valueOf(v_lotime.toLocalDate().getDayOfMonth())+"日",v_money5,v_money_guazhang4,null},
                            {"7",String.valueOf(v_lotime.toLocalDate().getYear()+1)+"年"+((Integer)v.elementAt(s-2)-12)+"月"+
                                    String.valueOf(v_lotime.toLocalDate().getDayOfMonth())+"日",v_money5,v_money_guazhang5,null},
                            {"8",String.valueOf(v_lotime.toLocalDate().getYear()+1)+"年"+((Integer)v.elementAt(s-1)-12)+"月"+
                                    String.valueOf(v_lotime.toLocalDate().getDayOfMonth())+"日",v_money5,v_money_guazhang6,null}

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
        }
    }

