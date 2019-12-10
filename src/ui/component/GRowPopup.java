package ui.component;

import javax.swing.*;

public class GRowPopup extends JPopupMenu{
    public GRowPopup(int rid){
        super();
        System.out.println(" row id: " + rid);
        add(new JMenuItem("Add Row"));
        add(new JMenuItem("Delete Row"));
    }
}
