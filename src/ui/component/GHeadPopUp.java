package ui.component;

import ui.panel.UIController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GHeadPopUp extends JPopupMenu{
    private int cid;
    public GHeadPopUp(int cid){
        super();
        this.cid = cid;
        JMenuItem addCol = new JMenuItem("Add Column");
        addCol.addActionListener(new PopUpListener());

        JMenuItem editCol = new JMenuItem("Edit Column");
        editCol.addActionListener(new PopUpListener());

        JMenuItem removeCol = new JMenuItem("Delete Column");
        removeCol.addActionListener(new PopUpListener());

        add(addCol);
        add(editCol);
        add(removeCol);
    }

    public class PopUpListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Add Column")){
                UIController.addCol(cid);
            } else if (e.getActionCommand().equals("Edit Column")){
                UIController.editCol(cid);
            } else if (e.getActionCommand().equals("Delete Column")){
                UIController.removeCol(cid);
            }
        }
    }
}
