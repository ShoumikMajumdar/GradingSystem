package ui.panel;

import ui.UIConsts;
import ui.component.GTable;

import javax.swing.*;
import java.awt.*;

public class TablePanel extends JPanel {

    private JButton Back = new JButton("Back");
    private JButton Curve = new JButton("Curve");
    private int cid;

    public TablePanel(UIController uiController, GTable table, int cid){
        super(true);
        this.cid = cid;
        JScrollPane jsp = new JScrollPane(table);
        jsp.setPreferredSize(new Dimension(UIConsts.MAIN_WINDOW_WIDTH, UIConsts.MAIN_WINDOW_HEIGHT));
        jsp.setBounds(0,0, UIConsts.MAIN_WINDOW_WIDTH, UIConsts.MAIN_WINDOW_HEIGHT);
        add(jsp, BorderLayout.NORTH);
        add(Back, BorderLayout.SOUTH);
        addListener(uiController);
    }

    public void addListener(UIController uiController){
        Back.addActionListener(e -> {
                uiController.switchSectionList(cid);
        });
    }
}
