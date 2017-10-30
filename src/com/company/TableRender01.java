package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TableRender01 extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable table01, Object value,
                                                   boolean isSelected, boolean hasFocus, int row01, int column01) {
        if (row01 < 0 || column01 != 3)
            return super.getTableCellRendererComponent(table01, value, isSelected, hasFocus, row01, column01);
        else {
            JButton label = new JButton("查询");
            label.setFont(new Font("隶书", Font.PLAIN, 14));
            label.setPreferredSize(new Dimension(table01.getColumnModel().getColumn(column01).getPreferredWidth(), 20));
            return label;
        }
    }
}
