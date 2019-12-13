package ui.component;

import javax.swing.*;

public class GHeadPopUp extends JPopupMenu{
    public GHeadPopUp(int cid){
        super();
        System.out.println(" component id: " + cid);
        add(new JMenuItem("Add Column"));
        add(new JMenuItem("Delete Column"));
    }
}
