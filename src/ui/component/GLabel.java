package ui.component;

import javax.swing.*;

public class GLabel extends JLabel {
    private int rowId;
    public GLabel(String name){
        super(name);
        this.setComponentPopupMenu(new GRowPopup(rowId));
    }

    public GLabel(String name, int rowId){
        super(name);
        this.rowId = rowId;
        this.setComponentPopupMenu(new GRowPopup(rowId));
    }
}
