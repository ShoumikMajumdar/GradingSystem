package ui.panel;

import logic.Course;

import javax.swing.*;

public class CreateCoursePanel extends JPanel {

    private JLabel label;
    private JPanel jp;
    private JTextField CourseName;
    private JButton Submit;
    private JButton Back;
    private Course temp;

    public CreateCoursePanel(UIController uiController) {
        super(true);
        initialize();
        addComponent();
        setContent();
        addListener(uiController);
    }

    public void initialize(){

    }

    public void addComponent(){
        jp = new JPanel();
        jp.setLayout(new BoxLayout(jp, BoxLayout.PAGE_AXIS));
        label = new JLabel("Enter name of the course");
        jp.add(label);
        CourseName = new JTextField();
        jp.add(CourseName);
        Submit = new JButton("Create Course");
        add(Submit);
        Back = new JButton("Back");
        add(Back);
        add(jp);
    }

    public void setContent(){

    }

    public void addListener(UIController uiController){
        Submit.addActionListener(e -> {
            String newcourse = CourseName.getText();
            temp = Course.create(newcourse);
            JOptionPane.showMessageDialog(jp,newcourse +" Course added");
            uiController.switchSelectTemplatePanel(temp.id);

        });

        Back.addActionListener(e -> {
            uiController.switchCoursePanel();
        });
    }


}
