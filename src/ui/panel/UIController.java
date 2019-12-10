package ui.panel;

import logic.Course;
import ui.UIConsts;

import javax.swing.*;

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
        holderPanel.add(new TablePanel());
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

    public void  switchNewTemplatePanel(){

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
        holderPanel.add(createTemplatePanel);
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

//    private Object generateData() throws FileNotFoundException {
//        Object json = null;
//        try {
//            FileInputStream fileIn = new FileInputStream("./sampleJson.json");
//            ObjectInputStream in = new ObjectInputStream(fileIn);
//            json = in.readObject();
//            in.close();
//            fileIn.close();
//        } catch (IOException | ClassNotFoundException i){
//            i.printStackTrace();
//            return null;
//        }
//        return json;
//    }
}
