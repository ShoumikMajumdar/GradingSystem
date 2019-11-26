package ui.panel;

import java.awt.*;
import javax.swing.*;

public class MainPanel extends JPanel {
    private JButton btnCourse;
    private JButton btnTemplate;

    public MainPanel() {
        super(true);
        initialize();
        addComponent();
        setContent();
        addListener();
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

    private void addListener() {
        btnCourse.addActionListener(e -> {

            });
        btnTemplate.addActionListener(e -> {

            });
    }
}