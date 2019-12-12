package ui.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GTextField
    extends JTextField
    implements ActionListener, FocusListener, MouseListener {
    private int studentID;
    private int componentID;
    private int bonus;
    private String comments;

    public GTextField(String s, int sid, int cid) {
        super(s);
        studentID = sid;
        componentID = cid;
        bonus = 0;
        comments = "";
        addActionListener(this);
        addFocusListener(this);
        addMouseListener(this);
    }

    public GTextField(String s, int sid, int cid, int bonus, String comments) {
        new GTextField(s, sid, cid);
        this.bonus = bonus;
        this.comments = comments;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("GT " + studentID + " " + componentID);
        if(e.getButton() == 3){
            showPopUp(e);
        }
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

    private void showPopUp(MouseEvent e){
        GCellPopUp Popup = new GCellPopUp(this.studentID, this.componentID);
        Popup.show(e.getComponent(), e.getX(), e.getY());
    }

    public void setBonus(int bonus){
        this.bonus = bonus;
        if(bonus > 0){
            this.setBackground(Color.YELLOW);
        }else{
            this.setBackground(null);
        }
    }

    public int getBonus(){
        return this.bonus;
    }

    public void setComments(String comments){
        this.comments = comments;
    }

    public String getComments(){
        return this.comments;
    }
}