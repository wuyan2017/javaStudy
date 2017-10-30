package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.company.LoanInfo.*;
import static com.company.PayPlan.*;
import static com.company.client.card;
import static com.company.client.jp;
import static com.company.Repay.no;
/**
 * Created by wuyan on 2017/9/27.
 */
public class TableEditor01 extends DefaultCellEditor {
    public TableEditor01(JCheckBox checkBox) {
        super(checkBox);
    }
    public TableEditor01(JComboBox comboBox) {
        super(comboBox);
    }
    public TableEditor01(JTextField textField) {
        super(textField);
    }

    public Component getTableCellEditorComponent(JTable table01, Object value,
                                                 boolean isSelected, int row01, int column01) {
        if (row01<0||column01!=3)
            return super.getTableCellEditorComponent(table01, value, isSelected,  row01, column01);
        else {
            JButton label = new JButton("查询");
            label.setFont(new Font("隶书", Font.PLAIN, 14));
            label.setBackground(Color.yellow);
            row01=table01.getSelectedRow();
            column01=table01.getSelectedColumn();
            if(row01==0&&column01==3){
                no=1;
                v_flag=flag;
                v_total=total;
                v_n=n;
                v_lotime=lo_time;
                v_money_guazhang= money_guazhang;  v_money_guazhang2=money_guazhang2;
                v_money_guazhang3=money_guazhang3; v_money_guazhang4= money_guazhang4;
                v_money_guazhang5= money_guazhang5;
                PayPlan payPlan=new PayPlan();
                jp.add(payPlan,"playPlan");
                card.show(jp,"playPlan");
            }else if(row01==1&&column01==3){
                no=2;
                v_total=total2;
                v_n=n2;
                v_lotime=lo_time2;
                v_money_guazhang= Second_money_guazhang01;  v_money_guazhang2=Second_money_guazhang02;
                v_money_guazhang3=Second_money_guazhang03; v_money_guazhang4= Second_money_guazhang04;v_money_guazhang5= Second_money_guazhang05;
                v_money_guazhang6= Second_money_guazhang06;
                PayPlan payPlan02=new PayPlan();
                jp.add(payPlan02,"playPlan02");
                card.show(jp,"playPlan02");
            }else if(row01==2&&column01==3){
                no=3;
                v_total=total3;
                v_n=n3;
                v_lotime=lo_time3;
                v_money_guazhang= Third_money_guazhang01;  v_money_guazhang2=Third_money_guazhang02;
                v_money_guazhang3=Third_money_guazhang03;
                PayPlan payPlan03=new PayPlan();
                jp.add(payPlan03,"playPlan03");
                card.show(jp,"playPlan03");
            }else if(row01==3&&column01==3){
                no=4;
                v_total=total4;
                v_n=n3;
                v_lotime=lo_time4;
                v_money_guazhang= Fourth_monney_guazhang01;  v_money_guazhang2=Fourth_monney_guazhang02;
                v_money_guazhang3=Fourth_monney_guazhang03;
                PayPlan payPlan04=new PayPlan();
                jp.add(payPlan04,"playPlan04");
                card.show(jp,"playPlan04");
            }
            label.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                }
            });
            label.setPreferredSize(new Dimension(table01.getColumnModel().getColumn(column01).getPreferredWidth(),25));
            return label;
        }
    }
}