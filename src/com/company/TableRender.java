package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TableRender extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        if (row<0||column!=2)
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        else {
            JButton label = new JButton("点击还款");
            label.setFont(new Font("隶书", Font.PLAIN, 10));
            label.setBackground(Color.YELLOW);
            label.setPreferredSize(new Dimension(table.getColumnModel().getColumn(column).getPreferredWidth(),20));
            return label;
        }
    }
}
