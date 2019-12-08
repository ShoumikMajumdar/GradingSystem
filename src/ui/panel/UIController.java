package ui.panel;

import ui.UIConsts;

import javax.swing.*;

public class UIController extends JFrame {

    private final CoursePanel coursePanel = new CoursePanel();
    private TemplatePanel templatePanel;
    private TablePanel tablePanel;
    private final MainPanel mainPanel = new MainPanel(this);
    private final JFrame frame = new JFrame();
    private final JPanel holderPanel = new JPanel();

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
