package ui.panel;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HeaderMouseListener extends MouseAdapter {
    private JTable table;
    private JTableHeader header;

    public HeaderMouseListener(JTable table) {
        this.table = table;
        this.header = table.getTableHeader();
    }

    public void mouseClicked(MouseEvent e) {
        Point point = e.getPoint();

        int currentCol = header.columnAtPoint(point);

        System.out.println("Header#" + currentCol);
        System.out.println("Header#" + e.getSource());
    }
}
