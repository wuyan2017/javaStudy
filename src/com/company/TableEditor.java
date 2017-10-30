package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;

import static com.company.PayPlan.*;
import static com.company.LoanInfo.*;
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
                    if(no==1){
                        if(row==0&&column==4&&mf1==0){
                            Repay p=new Repay();
                            jp.add(p,"p2");
                            card.show(jp,"p2");
                            if(v_money1==0){
                                benxihe=0;
                            }else{
                                if(time>30){
                                    benxihe=(float) (v_total/v_n * time * 0.005 + v_total/v_n);
                                    benxihe=benxihe-v_money_guazhang;
                                }else{
                                    benxihe=(float) (v_total/v_n * time * 0.003 + v_total/v_n);
                                    benxihe=benxihe-v_money_guazhang;
                                }
                            }
                            v_guazhang=v_money_guazhang;
                            flag=1;
                        }else if(row==1&&column==4&&mf2==0){
                            Repay p=new Repay();
                            jp.add(p,"repay");
                            card.show(jp,"repay");
                            if(v_money2==0){
                                benxihe=0;
                            }else{
                                if(time>60){
                                    benxihe=(float) (v_total/v_n * time * 0.005 + v_total/v_n);
                                    benxihe=benxihe-v_money_guazhang2;
                                }else{
                                    benxihe=(float) (v_total/v_n * time * 0.003 + v_total/v_n);
                                    benxihe=benxihe-v_money_guazhang2;
                                }
                            }
                            v_guazhang=v_money_guazhang2;
                            flag=2;
                        }else if(row==2&&column==4&&mf3==0){
                            Repay p=new Repay();
                            jp.add(p,"p2");
                            card.show(jp,"p2");
                            if(v_money3==0){
                                benxihe=0;
                            }else{
                                benxihe=(float) (v_total/v_n * time * 0.003 + v_total/v_n);
                                benxihe=benxihe-v_money_guazhang3;
                            }
                            v_guazhang=v_money_guazhang3;
                            flag=3;
                        }else if(row==3&&column==4&&mf4==0){
                            Repay p=new Repay();
                            jp.add(p,"p2");
                            card.show(jp,"p2");
                            if(v_money4==0){
                                benxihe=0;
                            }else{
                                benxihe=(float) (v_total/v_n * time * 0.003 + v_total/v_n);
                                benxihe=benxihe-v_money_guazhang4;
                            }
                            v_guazhang=v_money_guazhang4;
                            flag=4;
                        }else if(row==4&&column==4&&mf5==0){
                            Repay p=new Repay();
                            jp.add(p,"p2");
                            card.show(jp,"p2");
                            if(v_money5==0){
                                benxihe=0;
                            }else{
                                benxihe=(float) (v_total/v_n * time * 0.003 + v_total/v_n);
                                benxihe=benxihe-v_money_guazhang5;
                            }
                            v_guazhang=v_money_guazhang5;
                            flag=5;
                        }else if(row==5&&column==4&&v_money6!=0){
                            Repay p=new Repay();
                            jp.add(p,"p2");
                            card.show(jp,"p2");
                            if(v_money6==0){
                                benxihe=0;
                            }else{
                                benxihe=(float) (v_total/v_n * time * 0.003 + v_total/v_n);
                                benxihe=benxihe-v_money_guazhang6;
                            }
                            v_guazhang=v_money_guazhang6;
                            flag=6;
                        }else if(row==6&&column==4&&v_money7!=0){
                            Repay p=new Repay();
                            jp.add(p,"p2");
                            card.show(jp,"p2");
                            if(v_money7==0){
                                benxihe=0;
                            }else{
                                benxihe=(float) (v_total/v_n * time * 0.003 + v_total/v_n);
                                benxihe=benxihe-v_money_guazhang7;
                            }
                            v_guazhang=v_money_guazhang7;
                            flag=7;
                        }else if(row==7&&column==4&&v_money8!=0){
                            Repay p=new Repay();
                            jp.add(p,"p2");
                            card.show(jp,"p2");
                            if(v_money8==0){
                                benxihe=0;
                            }else{
                                benxihe=(float) (v_total/v_n * time * 0.003 + v_total/v_n);
                                benxihe=benxihe-v_money_guazhang8;
                            }
                            v_guazhang=v_money_guazhang8;
                            flag=8;
                        }else if(row==8&&column==4&&v_money9!=0){
                            Repay p=new Repay();
                            jp.add(p,"p2");
                            card.show(jp,"p2");
                            if(v_money9==0){
                                benxihe=0;
                            }else{
                                benxihe=(float) (v_total/v_n * time * 0.003 + v_total/v_n);
                                benxihe=benxihe-v_money_guazhang9;
                            }
                            v_guazhang=v_money_guazhang9;
                            flag=9;
                        }
                    }

                    if(no==2){
                        if(row==0&&column==4&&sc_mf1==0){
                            Repay pp=new Repay();
                            jp.add(pp,"pp");
                            card.show(jp,"pp");
                            if(v_money1==0){
                                benxihe=0;
                            }else{
                                if(time>30){
                                    benxihe=(float) (v_total/v_n * time * 0.005 + v_total/v_n);
                                    benxihe=benxihe-v_money_guazhang;
                                }else{
                                    benxihe=(float) (v_total/v_n * time * 0.003 + v_total/v_n);
                                    benxihe=benxihe-v_money_guazhang;
                                }
                            }
                            v_guazhang=v_money_guazhang;
                            sc_flag=1;
                        }else if(row==1&&column==4&&sc_mf2==0){
                            Repay pp2=new Repay();
                            jp.add(pp2,"pp2");
                            card.show(jp,"pp2");
                            if(v_money2==0){
                                benxihe=0;
                            }else{
                                if(time>60){
                                    benxihe=(float) (v_total/v_n * time * 0.005 + v_total/v_n);
                                    benxihe=benxihe-v_money_guazhang2;
                                }else{
                                    benxihe=(float) (v_total/v_n * time * 0.003 + v_total/v_n);
                                    benxihe=benxihe-v_money_guazhang2;
                                }
                            }
                            v_guazhang=v_money_guazhang2;
                            sc_flag=2;
                        }else if(row==2&&column==4&&sc_mf3==0){
                            Repay pp3=new Repay();
                            jp.add(pp3,"pp3");
                            card.show(jp,"pp3");
                            if(v_money3==0){
                                benxihe=0;
                            }else{
                                benxihe=(float) (v_total/v_n * time * 0.003 + v_total/v_n);
                                benxihe=benxihe-v_money_guazhang3;
                            }
                            v_guazhang=v_money_guazhang3;
                            sc_flag=3;
                        }else if(row==3&&column==4&&sc_mf4==0){
                            Repay p=new Repay();
                            jp.add(p,"p2");
                            card.show(jp,"p2");
                            if(v_money4==0){
                                benxihe=0;
                            }else{
                                benxihe=(float) (v_total/v_n * time * 0.003 + v_total/v_n);
                                benxihe=benxihe-v_money_guazhang4;
                            }
                            v_guazhang=v_money_guazhang4;
                            sc_flag=4;
                        }else if(row==4&&column==4&&sc_mf5==0){
                            Repay p=new Repay();
                            jp.add(p,"p2");
                            card.show(jp,"p2");
                            if(v_money5==0){
                                benxihe=0;
                            }else{
                                benxihe=(float) (v_total/v_n * time * 0.003 + v_total/v_n);
                                benxihe=benxihe-v_money_guazhang5;
                            }
                            v_guazhang=v_money_guazhang5;
                            sc_flag=5;
                        }else if(row==5&&column==4&&v_money6!=0){
                            Repay p=new Repay();
                            jp.add(p,"p2");
                            card.show(jp,"p2");
                            if(v_money6==0){
                                benxihe=0;
                            }else{
                                benxihe=(float) (v_total/v_n * time * 0.003 + v_total/v_n);
                                benxihe=benxihe-v_money_guazhang6;
                            }
                            v_guazhang=v_money_guazhang6;
                            sc_flag=6;
                        }else if(row==6&&column==4&&v_money7!=0){
                            Repay p=new Repay();
                            jp.add(p,"p2");
                            card.show(jp,"p2");
                            if(v_money7==0){
                                benxihe=0;
                            }else{
                                benxihe=(float) (v_total/v_n * time * 0.003 + v_total/v_n);
                                benxihe=benxihe-v_money_guazhang7;
                            }
                            v_guazhang=v_money_guazhang7;
                            sc_flag=7;
                        }else if(row==7&&column==4&&v_money8!=0){
                            Repay p=new Repay();
                            jp.add(p,"p2");
                            card.show(jp,"p2");
                            if(v_money8==0){
                                benxihe=0;
                            }else{
                                benxihe=(float) (v_total/v_n * time * 0.003 + v_total/v_n);
                                benxihe=benxihe-v_money_guazhang8;
                            }
                            v_guazhang=v_money_guazhang8;
                            sc_flag=8;
                        }else if(row==8&&column==4&&v_money9!=0){
                            Repay p=new Repay();
                            jp.add(p,"p2");
                            card.show(jp,"p2");
                            if(v_money9==0){
                                benxihe=0;
                            }else{
                                benxihe=(float) (v_total/v_n * time * 0.003 + v_total/v_n);
                                benxihe=benxihe-v_money_guazhang9;
                            }
                            v_guazhang=v_money_guazhang9;
                            sc_flag=9;
                        }
                    }

                    if(no==3){
                        if(row==0&&column==4&&third_mf1==0){
                            Repay p=new Repay();
                            jp.add(p,"p2");
                            card.show(jp,"p2");
                            if(v_money1==0){
                                benxihe=0;
                            }else{
                                if(time>30){
                                    benxihe=(float) (v_total/v_n * time * 0.005 + v_total/v_n);
                                    benxihe=benxihe-v_money_guazhang;
                                }else{
                                    benxihe=(float) (v_total/v_n * time * 0.003 + v_total/v_n);
                                    benxihe=benxihe-v_money_guazhang;
                                }
                            }
                            v_guazhang=v_money_guazhang;
                            third_flag=1;
                        }else if(row==1&&column==4&&third_mf2==0){
                            Repay p=new Repay();
                            jp.add(p,"repay");
                            card.show(jp,"repay");
                            if(v_money2==0){
                                benxihe=0;
                            }else{
                                if(time>60){
                                    benxihe=(float) (v_total/v_n * time * 0.005 + v_total/v_n);
                                    benxihe=benxihe-v_money_guazhang2;
                                }else{
                                    benxihe=(float) (v_total/v_n * time * 0.003 + v_total/v_n);
                                    benxihe=benxihe-v_money_guazhang2;
                                }
                            }
                            v_guazhang=v_money_guazhang2;
                            third_flag=2;
                        }else if(row==2&&column==4&&third_mf3==0){
                            Repay p=new Repay();
                            jp.add(p,"p2");
                            card.show(jp,"p2");
                            if(v_money3==0){
                                benxihe=0;
                            }else{
                                benxihe=(float) (v_total/v_n * time * 0.003 + v_total/v_n);
                                benxihe=benxihe-v_money_guazhang3;
                            }
                            v_guazhang=v_money_guazhang3;
                            sc_flag=3;
                        }else if(row==3&&column==4&&sc_mf4==0){
                            Repay p=new Repay();
                            jp.add(p,"p2");
                            card.show(jp,"p2");
                            if(v_money4==0){
                                benxihe=0;
                            }else{
                                benxihe=(float) (v_total/v_n * time * 0.003 + v_total/v_n);
                                benxihe=benxihe-v_money_guazhang4;
                            }
                            v_guazhang=v_money_guazhang4;
                            third_flag=4;
                        }else if(row==4&&column==4&&sc_mf5==0){
                            Repay p=new Repay();
                            jp.add(p,"p2");
                            card.show(jp,"p2");
                            if(v_money5==0){
                                benxihe=0;
                            }else{
                                benxihe=(float) (v_total/v_n * time * 0.003 + v_total/v_n);
                                benxihe=benxihe-v_money_guazhang5;
                            }
                            v_guazhang=v_money_guazhang5;
                            third_flag=5;
                        }else if(row==5&&column==4&&v_money6!=0){
                            Repay p=new Repay();
                            jp.add(p,"p2");
                            card.show(jp,"p2");
                            if(v_money6==0){
                                benxihe=0;
                            }else{
                                benxihe=(float) (v_total/v_n * time * 0.003 + v_total/v_n);
                                benxihe=benxihe-v_money_guazhang6;
                            }
                            v_guazhang=v_money_guazhang6;
                            third_flag=6;
                        }else if(row==6&&column==4&&v_money7!=0){
                            Repay p=new Repay();
                            jp.add(p,"p2");
                            card.show(jp,"p2");
                            if(v_money7==0){
                                benxihe=0;
                            }else{
                                benxihe=(float) (v_total/v_n * time * 0.003 + v_total/v_n);
                                benxihe=benxihe-v_money_guazhang7;
                            }
                            v_guazhang=v_money_guazhang7;
                            third_flag=7;
                        }else if(row==7&&column==4&&v_money8!=0){
                            Repay p=new Repay();
                            jp.add(p,"p2");
                            card.show(jp,"p2");
                            if(v_money8==0){
                                benxihe=0;
                            }else{
                                benxihe=(float) (v_total/v_n * time * 0.003 + v_total/v_n);
                                benxihe=benxihe-v_money_guazhang8;
                            }
                            v_guazhang=v_money_guazhang8;
                            third_flag=8;
                        }else if(row==8&&column==4&&v_money9!=0){
                            Repay p=new Repay();
                            jp.add(p,"p2");
                            card.show(jp,"p2");
                            if(v_money9==0){
                                benxihe=0;
                            }else{
                                benxihe=(float) (v_total/v_n * time * 0.003 + v_total/v_n);
                                benxihe=benxihe-v_money_guazhang9;
                            }
                            v_guazhang=v_money_guazhang9;
                            third_flag=9;
                        }
                    }

                }
            });
            label.setPreferredSize(new Dimension(table.getColumnModel().getColumn(column).getPreferredWidth(),25));
            return label;
        }
    }
}