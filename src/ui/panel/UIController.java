package ui.panel;

import ui.UIConsts;

import javax.swing.*;

public class UIController extends JFrame {

    private final CoursePanel coursePanel = new CoursePanel();
    private final TemplatePanel templatePanel = new TemplatePanel();
    private final MainPanel mainPanel = new MainPanel(this);
    private final JFrame frame = new JFrame();
    private final JPanel holderPanel = new JPanel();

    public UIController(){
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

    public void switchTemplatePanel(){
        frame.getContentPane().removeAll();
        holderPanel.removeAll();
        holderPanel.revalidate();
        holderPanel.add(templatePanel);
        frame.add(holderPanel);
        frame.repaint();
        frame.setVisible(true);
    }
}
