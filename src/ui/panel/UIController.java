package ui.panel;

import logic.*;
import logic.Component;
import ui.UIConsts;
import ui.component.GTable;
import ui.component.GTextField;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class UIController extends JFrame {

    private  CoursePanel coursePanel;
    private SelectTemplatePanel selectTemplatePanel;
    private TemplatePanel templatePanel;
    private static TablePanel tablePanel;
    private final LoginPanel loginPanel = new LoginPanel(this);
    //private final MainPanel mainPanel = new MainPanel(this);
    private MainPanel mainPanel;
    private final JFrame frame = new JFrame();
    private final JPanel holderPanel = new JPanel();
    private UIController uiController;
    private  CreateCoursePanel createCoursePanel;
    private TemplateListPanel templateListPanel;
    private CreateTemplatePanel createTemplatePanel;
    private SectionsList sectionsList;

    private static GTable table;
    private static Component root;

    public UIController() {
        frame.setTitle(UIConsts.APP_NAME);
        frame.setBounds(UIConsts.MAIN_WINDOW_X, UIConsts.MAIN_WINDOW_Y,
                UIConsts.MAIN_WINDOW_WIDTH, UIConsts.MAIN_WINDOW_HEIGHT);
        holderPanel.add(loginPanel);
        frame.add(holderPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void switchMainPanel(){
        frame.getContentPane().removeAll();
        holderPanel.removeAll();
        holderPanel.revalidate();
        mainPanel = new MainPanel(this);
        holderPanel.add(mainPanel);
        frame.add(holderPanel);
        frame.repaint();
        frame.setVisible(true);
    }

    public void switchCoursePanel(){
        frame.getContentPane().removeAll();
        holderPanel.removeAll();
        holderPanel.revalidate();
        coursePanel = new CoursePanel(this);
        holderPanel.add(coursePanel);
        frame.add(holderPanel);
        frame.repaint();
        frame.setVisible(true);
    }

    public void switchTablePanel(int cid, int GTable){
        frame.getContentPane().removeAll();
        holderPanel.removeAll();
        holderPanel.revalidate();
        table = new GTable(cid, GTable);
        System.out.println("course id: "+ cid +"section id: " + GTable);
        tablePanel = new TablePanel(this, table, cid);
        holderPanel.add(tablePanel);
        frame.add(holderPanel);
        frame.repaint();
        frame.setVisible(true);
    }

    public void switchSelectTemplatePanel(int c_id){
        frame.getContentPane().removeAll();
        holderPanel.removeAll();
        holderPanel.revalidate();
        selectTemplatePanel = new SelectTemplatePanel(this, c_id);
        holderPanel.add(selectTemplatePanel);
        frame.add(holderPanel);
        frame.repaint();
        frame.setVisible(true);
    }

    public void switchNewTemplatePanel(){

    }

    public void switchEditCoursePanel(){

    }

    public void switchCreateCoursePanel() {
        frame.getContentPane().removeAll();
        holderPanel.removeAll();
        holderPanel.revalidate();
        createCoursePanel = new CreateCoursePanel(this);
        holderPanel.add(createCoursePanel);
        frame.add(holderPanel);
        frame.repaint();
        frame.setVisible(true);
    }

    public void switchTemplateListPanel() {
        frame.getContentPane().removeAll();
        holderPanel.removeAll();
        holderPanel.revalidate();
        templateListPanel = new TemplateListPanel(this);
        holderPanel.add(templateListPanel);
        frame.add(holderPanel);
        frame.repaint();
        frame.setVisible(true);
    }

    public void switchCreateTemplatePanel(int flag, int cid) {    //flag = 0 mean call made from TemplateListPanel || flag = 1 means call made after creating course
        frame.getContentPane().removeAll();
        holderPanel.removeAll();
        holderPanel.revalidate();
        createTemplatePanel = new CreateTemplatePanel(this,flag, cid);
        holderPanel.add(createTemplatePanel, BorderLayout.NORTH);
        frame.add(holderPanel);
        frame.repaint();
        frame.setVisible(true);
    }

    public void switchSectionList(int cid) {
        frame.getContentPane().removeAll();
        holderPanel.removeAll();
        holderPanel.revalidate();
        sectionsList = new SectionsList(this,cid);
        holderPanel.add(sectionsList);
        frame.add(holderPanel);
        frame.repaint();
        frame.setVisible(true);
    }

    public static void addComments(int sid, int cid, int sectionId){
        GTextField cell = table.getCell(sid, cid);
        String comments = JOptionPane.showInputDialog("Current comment: "+ cell.getComments() + ".\n Change comment to:");
        cell.setComments(comments);
        Comment.create(sectionId, sid, cid, comments);
        table.update();
        tablePanel.revalidate();
        tablePanel.repaint();
    }

    public static void addBonus(int sid, int cid, int sectionId){
        GTextField cell = table.getCell(sid, cid);
        String bonus = JOptionPane.showInputDialog("Current bonus: "+ cell.getBonus() + ".\n Change bonus to:");
        try {
            if(bonus != null){
                int num = 0;
                num = Integer.parseInt(bonus);
                cell.setBonus(num);
                Bonus.create(sectionId, sid, cid, num);
            }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(table, "Please enter a number!");
        }
        table.update();
        tablePanel.revalidate();
        tablePanel.repaint();
    }

    public static void addRow(int sectionId){
        System.out.println("Add Row" + " " + sectionId);
        String name = JOptionPane.showInputDialog("Enter a student's name:");
        Student s = Student.create(name);
        Section.addNewStudent(sectionId, s.id);
        table.update();
        tablePanel.revalidate();
        tablePanel.repaint();
    }

    public static void removeRow(int sid, int sectionId){
        System.out.println("Delete Row");
        Section.deleteStudent(sectionId, sid);
        table.update();
        tablePanel.revalidate();
        tablePanel.repaint();
    }

    public static void addCol(int ComponentId){
        System.out.println("Add Column");

        JTextField tf_name = new JTextField(5);
        JTextField tf_maxPoints = new JTextField(5);
        JTextField tf_percent = new JTextField(5);
        JPanel tmp = new JPanel();
        tmp.add(new JLabel("Column Name: "));
        tmp.add(tf_name);
        tmp.add(new JLabel("Max Points: "));
        tmp.add(tf_maxPoints);
        tmp.add(Box.createHorizontalStrut(15));
        tmp.add(new JLabel("Percentage: "));
        tmp.add(tf_percent);

        int result = JOptionPane.showConfirmDialog(
                null,
                tmp,
                "Please Enter the max points and rubrics",
                JOptionPane.OK_CANCEL_OPTION
                );

        if(result == JOptionPane.OK_OPTION){
            try{
                String name = tf_name.getText();
                int maxPoints = Integer.parseInt(tf_maxPoints.getText());
                float percentage = Float.parseFloat(tf_maxPoints.getText());
                Component child = Component.create(name, maxPoints, percentage);
                Component.addChild(ComponentId, child.id);
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(table, "Please enter a number and a percentile!");
            }
            table.update();
            tablePanel.revalidate();
            tablePanel.repaint();
        }
    }

    public static void editRubric(int parentID, int ComponentId){
        System.out.println("Edit Rubrics");
        Component parent = Component.rebuildComponentTree(parentID);
        JPanel tmp = new JPanel();
        Object[] arr = new Object[parent.children.size()]; // store JTextfileds
        for (Map.Entry<Integer, Component> entry : parent.children.entrySet()){
            tmp.add(new JLabel(entry.getValue().name));
            tmp.add(new JTextField(5));
        }

        int result = JOptionPane.showConfirmDialog(
                null,
                tmp,
                "Please Enter the rubrics",
                JOptionPane.OK_CANCEL_OPTION
        );

        if(result == JOptionPane.OK_OPTION){
            try{
                for (Map.Entry<Integer, Component> entry : parent.children.entrySet()){
                    // TODO: 12/15/19 update component rubrics
                }
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(table, "Invalid input");
            }
            table.update();
            tablePanel.revalidate();
            tablePanel.repaint();
        }
    }

    public static void removeCol(int parentID, int ComponentId){
        System.out.println("Remove Column");
        Component.deleteChild(parentID, ComponentId);
        table.update();
        tablePanel.revalidate();
        tablePanel.repaint();
    }
}
