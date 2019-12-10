package ui.panel;

import ui.component.*;

import javax.swing.*;

public class TablePanel extends JPanel {

    public TablePanel(){
        super(true);
        addTable();
    }

    public void addTable(){
        add(new GTable());
    }
}
