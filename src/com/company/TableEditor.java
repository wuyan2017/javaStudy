package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        if (row<0||column!=2)
            return super.getTableCellEditorComponent(table, value, isSelected,  row, column);
        else {
            JButton label = new JButton("点击还款");
            label.setFont(new Font("隶书", Font.PLAIN, 10));
            label.setBackground(Color.LIGHT_GRAY);

            label.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    /**
                     *@Author:吴焰
                     *@Date:10:11 2017/9/27
                     *@Description:重点在这修改
                     */
                    //JOptionPane.showMessageDialog(null, "test");
                    Repay p=new Repay();
                    jp.add(p,"p2");
                    card.show(jp,"p2");
                }
            });
            label.setPreferredSize(new Dimension(table.getColumnModel().getColumn(column).getPreferredWidth(),25));
            return label;
        }
    }
}