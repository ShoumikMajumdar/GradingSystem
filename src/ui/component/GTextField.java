package ui.component;

import logic.GradingSystem;
import ui.panel.UIController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GTextField
    extends JTextField
    implements ActionListener, FocusListener, MouseListener {
    private int studentID;
    private int componentID;
    private double bonus;
    private String comments;
    private int courseID;
    private int sectionId;


    public GTextField(String s, int sid, int cid, int courseID, int sectionId) {
        super(s);
        studentID = sid;
        componentID = cid;
        this.courseID = courseID;
        this.sectionId = sectionId;
        bonus = 0;
        comments = "";
        addActionListener(this);
        addFocusListener(this);
        addMouseListener(this);
    }

    public GTextField(String s, int sid, int cid, int courseID, int sectionId, double bonus, String comments) {
        super(s);
        studentID = sid;
        componentID = cid;
        this.courseID = courseID;
        this.sectionId = sectionId;
        this.bonus = bonus;
        this.comments = comments;

        if(!comments.equals("") && bonus > 0){
            this.setBackground(Color.GREEN);
        }else if(!comments.equals("")){
            this.setBackground(Color.BLUE.brighter());
        }else if(bonus > 0){
            this.setBackground(Color.YELLOW);
        }
        addActionListener(this);
        addFocusListener(this);
        addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        addScore();
    }

    @Override
    public void focusLost(FocusEvent e) {
        addScore();
    }

    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
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
        GCellPopUp Popup = new GCellPopUp(this.studentID, this.componentID, this.sectionId);
        Popup.show(e.getComponent(), e.getX(), e.getY());
    }

    public void setBonus(int bonus){
        this.bonus = bonus;
        if(bonus > 0){
            if(!comments.equals("")){
                this.setBackground(Color.GREEN);
            }else{
                this.setBackground(Color.YELLOW);
            }
        }else{
            if(!comments.equals("")){
                this.setBackground(Color.BLUE.brighter());
            }else{
                this.setBackground(null);
            }
        }
    }

    public double getBonus(){
        return this.bonus;
    }

    public void setComments(String comments){
        this.comments = comments;
        if(!comments.equals("")){
            if(bonus > 0){
                this.setBackground(Color.GREEN);
            }else{
                this.setBackground(Color.BLUE.brighter());
            }
        }else{
            if(bonus > 0){
                this.setBackground(Color.YELLOW);
            }else{
                this.setBackground(null);
            }
        }
    }

    public String getComments(){
        return this.comments;
    }

    public void addScore(){
        double temp = Double.parseDouble(getText());
        double max_score = GradingSystem.componentRd.queryComponent(componentID).getPoints();
        if(temp>max_score){
            GradingSystem.scoreRd.addScore(courseID,studentID,componentID,max_score);  
        }
        else if(temp<0){
            GradingSystem.scoreRd.addScore(courseID,studentID,componentID,(max_score+temp));
        }
        else
        {
            GradingSystem.scoreRd.addScore(courseID,studentID,componentID,temp);
        }
        UIController.refreshTable();
    }
}