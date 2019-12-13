package ui.panel;

import ui.UIConsts;
import ui.component.GTable;

import javax.swing.*;
import java.awt.*;

public class TablePanel extends JPanel {
    public TablePanel(GTable table){
        super(true);
        JScrollPane jsp = new JScrollPane(table);
        jsp.setPreferredSize(new Dimension(UIConsts.MAIN_WINDOW_WIDTH, UIConsts.MAIN_WINDOW_HEIGHT));
        jsp.setBounds(0,0, UIConsts.MAIN_WINDOW_WIDTH, UIConsts.MAIN_WINDOW_HEIGHT);

        JPanel jp = new JPanel();

        add(jsp);
    }
}
