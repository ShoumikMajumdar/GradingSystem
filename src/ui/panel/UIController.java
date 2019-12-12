package ui.panel;

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

    private Object[][] tableData = new Object[][]{
            {"Fuqing Wang", "99","98","96","54","20","98","96","54","20","1"},
            {"Xiaoduan Chang", "96","94","98","54","20","98","96","54","20","2"},
            {"Zhezhong Jiang", "97","98","99","54","20","98","96","54","20","3"},
            {"Shoumik", "99","98","96","51","21","98","96","54","20","4"}};
    private Object[] tableHeader = new Object[]{"Name","TTT-I", "TTT-II", "BlackJack-I", "BlackJack-II", "Trianta-ena", "Cave Adventure", "Midterm-Written", "Midterm-Code", "Final"};

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

    public void switchTablePanel(){
        frame.getContentPane().removeAll();
        holderPanel.removeAll();
        holderPanel.revalidate();
        root = Component.buildTestComponent();
        table = new GTable(root);
        holderPanel.add(new TablePanel(table));
        frame.add(holderPanel);
        frame.repaint();
        frame.setVisible(true);
    }

    public void switchSelectTemplatePanel(int c_id){
        frame.getContentPane().removeAll();
        holderPanel.removeAll();
        holderPanel.revalidate();
        selectTemplatePanel = new SelectTemplatePanel(this,c_id);
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
        //todo: call to update comment on backend needs to be added
        GTextField cell = table.getCell(sid, cid);
        String comments = JOptionPane.showInputDialog("Current comment: "+ cell.getComments() + ".\n Change comment to:");
        cell.setComments(comments);
    }

    public static void addBonus(int sid, int cid){
        //todo: call to update bonus on backend needs to be added
        GTextField cell = table.getCell(sid, cid);
        String bonus = JOptionPane.showInputDialog("Current bonus: "+ cell.getBonus() + ".\n Change bonus to:");
        try {
            if(bonus != null){
                int num = 0;
                num = Integer.parseInt(bonus);
                cell.setBonus(num);
            }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(table, "Please enter a number!");
        }
    }

    public static void addRow(){
        System.out.println("Add Row");
        String name = JOptionPane.showInputDialog("Enter a student's name:");
        //todo: call to update database on backend needs to be added
    }

    public static void removeRow(){
        System.out.println("Delete Row");
    }

    public static void addCol(int ComponentId){
//        Component.addChild(1,1);
        Component child = root.getComponent(ComponentId);
//        root.addChild(child);
        System.out.println("Add Column");
    }

    public static void removeCol(int ComponentId){
        Component child = root.getComponent(ComponentId);
//        root.removeChild(child);
        System.out.println("Remove Column");
    }

    public static void editCol(int ComponentId){
//        System.out.println("Edit Column");
    }
}
