package ui.component;

import ui.panel.UIController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GCellPopUp extends JPopupMenu {
    private int sid;
    private int cid;
    public GCellPopUp(int sid, int cid){
        super();
        this.sid = sid;
        this.cid = cid;
        JMenuItem addComments = new JMenuItem("Add Comments");
        addComments.addActionListener(new PopUpListener());
        add(addComments);

        JMenuItem addBonus = new JMenuItem("Add Bonus");
        addBonus.addActionListener(new PopUpListener());
        add(addBonus);
    }

    public class PopUpListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Add Bonus")){
                System.out.println("GCellPopUp: " + sid + " " + cid);
                UIController.addBonus(sid, cid);
            }else if (e.getActionCommand().equals("Add Comments")){
                UIController.addComments(sid, cid);
            }
        }
    }
}
