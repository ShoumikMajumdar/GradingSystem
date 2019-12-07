package ui.panel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HeaderMouseListener extends MouseAdapter {
    private GroupableTableHeader header;

    public HeaderMouseListener(GroupableTableHeader header) {
        this.header = header;
    }

    public void mouseClicked(MouseEvent e) {
        Point point = e.getPoint();
        int currentCol = header.columnAtPoint(point);
        System.out.println("Header#" + currentCol);
        System.out.println("Header#" + e.getSource());
    }
}
