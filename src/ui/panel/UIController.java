package ui.panel;

import logic.*;
import logic.Component;
import ui.UIConsts;
import ui.component.GTable;
import ui.component.GTextField;

import javax.swing.*;
import java.awt.*;

public class UIController extends JFrame {

    private  CoursePanel coursePanel;
    private SelectTemplatePanel selectTemplatePanel;
    private TemplatePanel templatePanel;
    private TablePanel tablePanel;
    private final MainPanel mainPanel = new MainPanel(this);
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
        holderPanel.add(mainPanel);
        frame.add(holderPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void switchMainPanel(){
        frame.getContentPane().removeAll();
        holderPanel.removeAll();
        holderPanel.revalidate();
        holderPanel.add(mainPanel);
        frame.add(holderPanel);
        frame.repaint();
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

    public void switchTablePanel(int cid, int sid){
        frame.getContentPane().removeAll();
        holderPanel.removeAll();
        holderPanel.revalidate();
        table = new GTable(cid, sid);
        System.out.println("course id: "+ cid +"section id: " + sid);
        holderPanel.add(new TablePanel(table));
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

    public static void addComments(int sid, int cid){
        GTextField cell = table.getCell(sid, cid);
        String comments = JOptionPane.showInputDialog("Current comment: "+ cell.getComments() + ".\n Change comment to:");
        cell.setComments(comments);
        Comment.create(0, sid, cid, comments);
        table.repaint();
    }

    public static void addBonus(int sid, int cid){
        GTextField cell = table.getCell(sid, cid);
        String bonus = JOptionPane.showInputDialog("Current bonus: "+ cell.getBonus() + ".\n Change bonus to:");
        try {
            if(bonus != null){
                int num = 0;
                num = Integer.parseInt(bonus);
                cell.setBonus(num);
                Bonus.create(0, sid, cid, num);
            }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(table, "Please enter a number!");
        }
        table.update();
    }

    public static void addRow(int sectionId){
        System.out.println("Add Row" + " " + sectionId);
        String name = JOptionPane.showInputDialog("Enter a student's name:");
        Student s = Student.create(name);
        Section.addNewStudent(sectionId, s.id);
        table.repaint();
    }

    public static void removeRow(int sid, int sectionId){
        System.out.println("Delete Row");
        Section.deleteStudent(sectionId, sid);
        table.repaint();
    }

    public static void addCol(int ComponentId){
        System.out.println("Add Column");
        String name = JOptionPane.showInputDialog("Enter column name:");
        Component child = Component.create(name, 0, 0);
        Component.addChild(ComponentId, child.id);
        table.repaint();
    }

    public static void removeCol(int parentID, int ComponentId){
        System.out.println("Remove Column");
        Component.deleteChild(parentID, ComponentId);
        table.repaint();
    }
}
