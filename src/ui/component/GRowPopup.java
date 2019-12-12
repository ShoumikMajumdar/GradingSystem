package ui.component;

import ui.panel.UIController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GRowPopup extends JPopupMenu{
    public GRowPopup(int rid){
        super();
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
                UIController.addRow();
            }else if (e.getActionCommand().equals("Delete Row")){
                UIController.removeRow();
            }
        }
    }
}
