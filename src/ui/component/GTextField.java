package ui.component;

import javax.swing.*;
import java.awt.event.*;

public class GTextField
    extends JTextField
    implements ActionListener, FocusListener, MouseListener {
    public int studentID;
    public int componentID;

    public GTextField(String s, int sid, int cid) {
        super(s);
        studentID = sid;
        componentID = cid;
        addActionListener(this);
        addFocusListener(this);
        addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("sid " + studentID + " cid " + componentID);
    }

    @Override
    public void focusLost(FocusEvent e) {
        System.out.println("sid " + studentID + " cid " + componentID);
    }

    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("" + e.getButton());
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }
}