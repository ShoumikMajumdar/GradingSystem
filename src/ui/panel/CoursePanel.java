package ui.panel;

import java.awt.*;
import javax.swing.*;

public class CoursePanel extends JPanel {
    private JButton btnCreateCourse;

    public CoursePanel() {
        super(true);
        initialize();
        addComponent();
        setContent();
        addListener();
    }

    private void initialize() {
        // setBackground();
    }

    private void addComponent() {
        btnCreateCourse = new JButton("Create Course");
        add(btnCreateCourse);
    }

    private void setContent() {
        btnCreateCourse.addActionListener(e -> {

            });
    }

    private void addListener() {

    }
}