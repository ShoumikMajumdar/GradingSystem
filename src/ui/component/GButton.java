package ui.component;

import javax.swing.*;
import java.awt.event.*;

public class GButton extends JButton
    implements ActionListener {

    public int componentID;

    public GButton(String name, int componentID) {
        super(name);
        this.componentID = componentID;
        this.addActionListener(this);
        this.setComponentPopupMenu(new GHeadPopUp(this.componentID));
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Show Popup Menu for componentID " + componentID);
    }

}