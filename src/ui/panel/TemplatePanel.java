package ui.panel;

import ui.UIConsts;

import javax.swing.*;
import java.awt.*;

public class TemplatePanel extends JPanel {
    public TemplatePanel() {
        super(true);
        initialize();
        addComponent();
        setContent();
        addListener();
    }

    private void initialize() {

    }

    private void addComponent() {
        add(getMainTable(), BorderLayout.CENTER);
        add(getActionButtons(), BorderLayout.CENTER);
    }

    private void setContent() {
        
    }

    private void addListener() {
        
    }

    private JPanel getMainTable(){
        JPanel jp = new JPanel();
        String data[][] = {{"Fuqing Wang", "99","98","96","54","20"},
                {"Xiaoduan Chang", "96","94","98","54","20"},
                {"Zhezhong Jiang", "97","98","99","54","20"},
                {"Shoumik", "99","98","96","51","21"}};
        String column[] = {"Name", "Assignment 1", "Assignment2", "Assignment 3", "Midterm", "Final"};

        JTable jt = new JTable(data, column);
        jt.setBounds(UIConsts.MAIN_WINDOW_X, UIConsts.MAIN_WINDOW_Y,
                UIConsts.MAIN_WINDOW_WIDTH, UIConsts.MAIN_WINDOW_HEIGHT);

        JScrollPane jsp = new JScrollPane(jt);
        jp.add(jsp);
        jp.setSize(300,400);
        return jp;
    }

    private JPanel getActionButtons(){
        JPanel jp = new JPanel();
        JButton btn_stats = new JButton("Statistics");
        JButton btn_curve = new JButton("Curve");
        JLabel l_search = new JLabel("Search");
        JTextField tf_search = new JTextField("", 8);
        jp.add(btn_stats);
        jp.add(btn_curve);
        jp.add(l_search);
        jp.add(tf_search);
        return jp;
    }

    private JPanel getSomeTable(){
        JPanel jp = new JPanel();
        JButton btn = new JButton("test button");
        jp.add(btn);
        return jp;
    }
}