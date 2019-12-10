package ui.panel;

import javax.swing.*;

public class TablePanel extends JPanel {

    public TablePanel(){
        super(true);
        addTable();
    }

    public void addTable(){
        JPanel jp = new JPanel();
        jp.add(new GTable());
        JScrollPane jsp = new JScrollPane(new GTable());
//        jsp.setHorizontalScrollBar(new JScrollBar(JScrollBar.HORIZONTAL));
//        JViewport jv = new JViewport();
//        jv.setSize(UIConsts.MAIN_WINDOW_WIDTH, UIConsts.MAIN_WINDOW_HEIGHT);
//        jsp.setViewport(jv);
        add(jsp);
    }
}
