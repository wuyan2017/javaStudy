package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

import static com.company.LoanInfo.*;
import static com.company.PayPlan.*;

public class TableRender extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        if (row<0||column!=4)
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        else if(row==0){
            JButton label = new JButton("还款");
            label.setFont(new Font("隶书", Font.PLAIN, 14));
            if(v_mf1!=0){
                label.setBackground(Color.LIGHT_GRAY);

            }else{
                label.setBackground(Color.YELLOW);
            }
            label.setPreferredSize(new Dimension(table.getColumnModel().getColumn(column).getPreferredWidth(),20));
            return label;
        }else if(row==1){
            JButton label = new JButton("还款");
            label.setFont(new Font("隶书", Font.PLAIN, 14));
            if(v_mf2!=0){
                label.setBackground(Color.LIGHT_GRAY);
            }else{
                label.setBackground(Color.YELLOW);
            }
            label.setPreferredSize(new Dimension(table.getColumnModel().getColumn(column).getPreferredWidth(),20));
            return label;
        }else if(row==2){
            JButton label = new JButton("还款");
            label.setFont(new Font("隶书", Font.PLAIN, 14));
            if(v_mf3!=0){
                label.setBackground(Color.LIGHT_GRAY);
            }else{
                label.setBackground(Color.YELLOW);
            }
            label.setPreferredSize(new Dimension(table.getColumnModel().getColumn(column).getPreferredWidth(),20));
            return label;
        }else if(row==3){
            JButton label = new JButton("还款");
            label.setFont(new Font("隶书", Font.PLAIN, 14));
            if(v_mf4!=0){
                label.setBackground(Color.LIGHT_GRAY);
            }else{
                label.setBackground(Color.YELLOW);
            }
            label.setPreferredSize(new Dimension(table.getColumnModel().getColumn(column).getPreferredWidth(),20));
            return label;
        }else if(row==4){
            JButton label = new JButton("还款");
            label.setFont(new Font("隶书", Font.PLAIN, 14));
            if(v_mf5!=0){
                label.setBackground(Color.LIGHT_GRAY);
            }else{
                label.setBackground(Color.YELLOW);
            }
            label.setPreferredSize(new Dimension(table.getColumnModel().getColumn(column).getPreferredWidth(),20));
            return label;
        }else{
            JButton label = new JButton("还款");
            label.setFont(new Font("隶书", Font.PLAIN, 14));
            /*if(v_money6==0){
                label.setBackground(Color.LIGHT_GRAY);
            }else{
                label.setBackground(Color.YELLOW);
            }*/
            label.setBackground(Color.YELLOW);
            label.setPreferredSize(new Dimension(table.getColumnModel().getColumn(column).getPreferredWidth(),20));
            return label;
        }
    }
}
