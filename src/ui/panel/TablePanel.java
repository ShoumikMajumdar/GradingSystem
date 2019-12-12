package ui.panel;

import ui.UIConsts;
import ui.component.GTable;

import javax.swing.*;
import java.awt.*;

public class TablePanel extends JPanel {

    private GTable table;

    public TablePanel(GTable table){
        super(true);
        this.table = table;
        setLayout(new BorderLayout());
        addTable(table);
    }

    public void addTable(GTable table){
        JPanel jp = new JPanel();
        JScrollPane jsp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setPreferredSize(new Dimension(UIConsts.MAIN_WINDOW_WIDTH, UIConsts.MAIN_WINDOW_HEIGHT));
        jp.add(jsp, BorderLayout.NORTH);
        add(jp);
    }
}
