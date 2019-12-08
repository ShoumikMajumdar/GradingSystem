package ui.panel;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private JButton btnCourse;
    private JButton btnTemplate;
 //   private static UIController uiController;

   /* public static UIController getUiController() {
        return uiController;
    }
*/
    public MainPanel(final UIController uiController) {
        super(true);
        //this.uiController = uiController;
        initialize();
        addComponent();
        setContent();
        addListener(uiController);
    }

    private void initialize() {
        // setBackground();
        setLayout(new BorderLayout());
    }

    private void addComponent() {
        btnCourse = new JButton("Course");
        btnTemplate = new JButton("Template");
        add(btnCourse, BorderLayout.WEST);
        add(btnTemplate, BorderLayout.EAST);
    }

    private void setContent() {

    }

    private void addListener(UIController uiController) {
        btnCourse.addActionListener(e -> {
            uiController.switchCoursePanel();
            });
        btnTemplate.addActionListener(e -> {
            //uiController.switchTemplatePanel();
            uiController.switchTemplateListPanel();
            });
    }
}