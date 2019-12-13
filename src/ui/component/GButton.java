package ui.component;

import javax.swing.*;
import java.awt.event.*;

public class GButton extends JButton
    implements ActionListener {
    public int parentID;
    public int componentID;

    public GButton(String name, int parentID, int componentID) {
        super(name);
        this.parentID = parentID;
        this.componentID = componentID;
        this.addActionListener(this);
        this.setComponentPopupMenu(new GHeadPopUp(this.parentID, this.componentID));
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Show Popup Menu for componentID " + componentID);
    }

}