package ui.panel;

import ui.UIConsts;
import ui.component.GTable;
import logic.GradingSystem;

import javax.swing.*;
import java.awt.*;

public class TablePanel extends JPanel {

    private JButton Back = new JButton("Back");
    private JButton Curve = new JButton("Curve");
    private JTextField txtSearch = new JTextField();
    private int cid;
    private int sid;

    public TablePanel(UIController uiController, GTable table, int cid, int sid){
        super(true);
        setLayout(new BorderLayout());
        this.cid = cid;
        this.sid = sid;
        JScrollPane jsp = new JScrollPane(table);
        jsp.setPreferredSize(new Dimension(UIConsts.MAIN_WINDOW_WIDTH, UIConsts.MAIN_WINDOW_HEIGHT));
        jsp.setBounds(0,0, UIConsts.MAIN_WINDOW_WIDTH, UIConsts.MAIN_WINDOW_HEIGHT);
        add(jsp, BorderLayout.CENTER);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(Back);
        panel.add(Curve);
        panel.add(txtSearch);
        add(panel, BorderLayout.SOUTH);
        addListener(uiController);
    }

    public void addListener(UIController uiController){
        Back.addActionListener(e -> {
                uiController.switchSectionList(cid);
        });

        Curve.addActionListener(e -> {
                String num = JOptionPane.showInputDialog("Enter the points to curve: ");
                int x = 0;

                try {
                    x = Integer.parseInt(num);
                } catch (Throwable ex) {
                    System.out.println("Error " + ex.getMessage());
                    ex.printStackTrace();
                }

                GradingSystem.setCurve(sid, x);
                uiController.refreshTable();
            });

        txtSearch.addActionListener(e -> {
                String keyword = txtSearch.getText();
                uiController.refreshTable(keyword);
            });
    }
}
