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

        JMenuItem editCol = new JMenuItem("Edit Column Name");
        editCol.addActionListener(new PopUpListener());

        JMenuItem changeColPoints = new JMenuItem("Edit Max Points");
        changeColPoints.addActionListener(new PopUpListener());

        add(addCol);
        add(removeCol);
        if(!Component.rebuildComponentTree(parentId).children.isEmpty()){
            add(editR);
        }
        add(editCol);
        add(changeColPoints);
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
            } else if (e.getActionCommand().equals("Edit Column Name")){
                String name = JOptionPane.showInputDialog("Enter a new column name:");
                UIController.editCol(cid, name);
            } else if (e.getActionCommand().equals("Edit Max Points")){
                String points = JOptionPane.showInputDialog("Enter max points:");
                try{
                    double num = Double.parseDouble(points);
                    UIController.changeColPoints(cid, num);
                }catch (NumberFormatException event){
                    JOptionPane.showMessageDialog(null,"Invalid input. Please enter a number!");;
                }
            }
        }
    }
}
