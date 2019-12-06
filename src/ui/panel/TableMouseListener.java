package ui.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableMouseListener extends MouseAdapter {
    private JTable table;

    public TableMouseListener(JTable table) {
        this.table = table;
    }

    public void mouseClicked(MouseEvent e) {
        Point point = e.getPoint();
        int currentRow = table.rowAtPoint(point);
        int currentCol = table.columnAtPoint(point);

        System.out.println(currentRow + "," + currentCol);
    }
}
