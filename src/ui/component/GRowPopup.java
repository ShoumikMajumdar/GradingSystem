package ui.component;

import ui.panel.UIController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GRowPopup extends JPopupMenu{
    private int sid;
    private int sectionId;
    public GRowPopup(int sid, int sectionId){
        super();
        this.sid = sid;
        this.sectionId = sectionId;
        JMenuItem addRow = new JMenuItem("Add Row");
        addRow.addActionListener(new PopUpListener());
        add(addRow);

        JMenuItem deleteRow = new JMenuItem("Delete Row");
        deleteRow.addActionListener(new PopUpListener());
        add(deleteRow);
    }

    public class PopUpListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Add Row")){
                UIController.addRow(sectionId);
            }else if (e.getActionCommand().equals("Delete Row")){
                UIController.removeRow(sid, sectionId);
            }
        }
    }
}
