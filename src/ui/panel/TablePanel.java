package ui.panel;

import ui.UIConsts;
import ui.component.GTable;

import javax.swing.*;
import java.awt.*;

public class TablePanel extends JPanel {

    JButton curve;

    public TablePanel(UIController uiController, int cid,int sid){
        super(true);
        setLayout(new BorderLayout());
        addTable();
    }

    public void addTable(){
        JPanel jp = new JPanel();
        jp.setLayout(new BorderLayout());
        curve = new JButton("Curve");
        jp.add(curve,BorderLayout.WEST);
        JScrollPane jsp = new JScrollPane(new GTable(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setPreferredSize(new Dimension(UIConsts.MAIN_WINDOW_WIDTH, UIConsts.MAIN_WINDOW_HEIGHT));
        jsp.setBorder(null);

//        jsp.setHorizontalScrollBar(new JScrollBar(JScrollBar.HORIZONTAL));
//        JViewport jv = new JViewport();
//        jv.setSize(UIConsts.MAIN_WINDOW_WIDTH, UIConsts.MAIN_WINDOW_HEIGHT);
//        jsp.setViewport(jv);
        jp.add(jsp, BorderLayout.NORTH);
        add(jp);
    }
}
