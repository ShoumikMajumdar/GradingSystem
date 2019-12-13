package ui.component;

import javax.swing.*;

public class GLabel extends JLabel {
    private int studentId;
    private int sectionId;
    public GLabel(String name){
        super(name);
        this.setComponentPopupMenu(new GRowPopup(studentId, sectionId));
    }

    public GLabel(String name, int studentId, int sectionId){
        super(name);
        this.studentId = studentId;
        this.sectionId = sectionId;
        this.setComponentPopupMenu(new GRowPopup(studentId, sectionId));
    }
}
