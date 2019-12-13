package ui.component;

import javax.swing.*;

public class GCellPopUp extends JPopupMenu {
    public GCellPopUp(int sid, int cid){
        super();
        System.out.println("student id: " + sid + " component id: " + cid);
        add(new JMenuItem("Add Comments"));
        add(new JMenuItem("Add Bonus"));
    }
}
