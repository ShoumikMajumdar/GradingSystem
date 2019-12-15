package ui.component;

import logic.Component;
import ui.panel.UIController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GHeadPopUp extends JPopupMenu{
    private int parentId;
    private int cid;
    public GHeadPopUp(int parentId, int cid){
        super();
        this.parentId = parentId;
        this.cid = cid;
        JMenuItem addCol = new JMenuItem("Add Column");
        addCol.addActionListener(new PopUpListener());


        JMenuItem removeCol = new JMenuItem("Delete Column");
        removeCol.addActionListener(new PopUpListener());

        JMenuItem editR = new JMenuItem("Edit Rubric");
        editR.addActionListener(new PopUpListener());

        add(addCol);
        add(removeCol);
        if(!Component.rebuildComponentTree(parentId).children.isEmpty()){
            add(editR);
        }
    }

    public class PopUpListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Add Column")){
                UIController.addCol(cid);
            } else if (e.getActionCommand().equals("Edit Rubric")){
                UIController.editRubric(parentId, cid);
            } else if (e.getActionCommand().equals("Delete Column")){
                UIController.removeCol(parentId, cid);
            }
        }
    }
}
