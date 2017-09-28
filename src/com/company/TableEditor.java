package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;

import static com.company.PayPlan.*;
import static com.company.PayPlan.money_guazhang3;
import static com.company.Record.p_time2;
import static com.company.Repay.*;
import static com.company.client.card;
import static com.company.client.jp;
/**
 * Created by wuyan on 2017/9/27.
 */
public class TableEditor extends DefaultCellEditor {
    public TableEditor(JCheckBox checkBox) {
        super(checkBox);
    }
    public TableEditor(JComboBox comboBox) {
        super(comboBox);
    }
    public TableEditor(JTextField textField) {
        super(textField);
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        if (row<0||column!=4)
            return super.getTableCellEditorComponent(table, value, isSelected,  row, column);
        else {
            JButton label = new JButton("还款");
            label.setFont(new Font("隶书", Font.PLAIN, 14));
            label.setBackground(Color.LIGHT_GRAY);

            label.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    /**
                     *@Author:吴焰
                     *@Date:10:11 2017/9/27
                     *@Description:
                     * 点击每期还款按钮，弹出相应的还款界面
                     * 每期超出相应期限时，利息上调0.5%，即罚息
                     */
                    int  row=table.getSelectedRow();
                    int column=table.getSelectedColumn();
                    DateFormat d1 = DateFormat.getDateInstance();
                    if(row==0&&column==4){
                        Repay p=new Repay();
                        jp.add(p,"p2");
                        card.show(jp,"p2");
                        if(money1==0){
                            benxihe=0;
                        }else{
                            if(time>30){
                                benxihe=(float) (total/n * time * 0.005 + total/n);
                                benxihe=benxihe-money_guazhang;
                            }else{
                                benxihe=(float) (total/n * time * 0.003 + total/n);
                                benxihe=benxihe-money_guazhang;
                            }
                        }
                        v_guazhang=money_guazhang;
                        flag=1;
                    }else if(row==1&&column==4){
                        Repay p=new Repay();
                        jp.add(p,"repay");
                        card.show(jp,"repay");
                        if(money2==0){
                            benxihe=0;
                        }else{
                            if(time>60){
                                benxihe=(float) (total/n * time * 0.005 + total/n);
                                benxihe=benxihe-money_guazhang2;
                            }else{
                                benxihe=(float) (total/n * time * 0.003 + total/n);
                                benxihe=benxihe-money_guazhang2;
                            }
                        }
                        v_guazhang=money_guazhang2;
                        flag=2;
                    }else if(row==2&&column==4){
                        Repay p=new Repay();
                        jp.add(p,"p2");
                        card.show(jp,"p2");
                        if(money3==0){
                            benxihe=0;
                        }else{
                            benxihe=(float) (total/n * time * 0.003 + total/n);
                            benxihe=benxihe-money_guazhang3;
                        }
                        v_guazhang=money_guazhang3;
                        flag=3;
                    }else if(row==3&&column==4){
                        Repay p=new Repay();
                        jp.add(p,"p2");
                        card.show(jp,"p2");
                        if(money4==0){
                            benxihe=0;
                        }else{
                            benxihe=(float) (total/n * time * 0.003 + total/n);
                            benxihe=benxihe-money_guazhang4;
                        }
                        v_guazhang=money_guazhang4;
                        flag=4;
                    }else if(row==4&&column==4){
                        Repay p=new Repay();
                        jp.add(p,"p2");
                        card.show(jp,"p2");
                        if(money5==0){
                            benxihe=0;
                        }else{
                            benxihe=(float) (total/n * time * 0.003 + total/n);
                            benxihe=benxihe-money_guazhang5;
                        }
                        v_guazhang=money_guazhang5;
                        flag=5;
                    }else if(row==5&&column==4){
                        Repay p=new Repay();
                        jp.add(p,"p2");
                        card.show(jp,"p2");
                        if(money6==0){
                            benxihe=0;
                        }else{
                            benxihe=(float) (total/n * time * 0.003 + total/n);
                            benxihe=benxihe-money_guazhang6;
                        }
                        v_guazhang=money_guazhang6;
                        flag=6;
                    }else if(row==6&&column==4){
                        Repay p=new Repay();
                        jp.add(p,"p2");
                        card.show(jp,"p2");
                        if(money7==0){
                            benxihe=0;
                        }else{
                            benxihe=(float) (total/n * time * 0.003 + total/n);
                            benxihe=benxihe-money_guazhang7;
                        }
                        v_guazhang=money_guazhang7;
                        flag=7;
                    }else if(row==7&&column==4){
                        Repay p=new Repay();
                        jp.add(p,"p2");
                        card.show(jp,"p2");
                        if(money8==0){
                            benxihe=0;
                        }else{
                            benxihe=(float) (total/n * time * 0.003 + total/n);
                            benxihe=benxihe-money_guazhang8;
                        }
                        v_guazhang=money_guazhang8;
                        flag=8;
                    }else if(row==8&&column==4){
                        Repay p=new Repay();
                        jp.add(p,"p2");
                        card.show(jp,"p2");
                        if(money9==0){
                            benxihe=0;
                        }else{
                            benxihe=(float) (total/n * time * 0.003 + total/n);
                            benxihe=benxihe-money_guazhang9;
                        }
                        v_guazhang=money_guazhang9;
                        flag=9;
                    }

                }
            });
            label.setPreferredSize(new Dimension(table.getColumnModel().getColumn(column).getPreferredWidth(),25));
            return label;
        }
    }
}