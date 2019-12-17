package ui.panel;

import Course.CourseDB;
import logic.Course;
import logic.GradingSystem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CoursePanel extends JPanel {
    private JButton btnCreateCourse;
    private JButton btnDeleteCourse;
    private JButton btnEditCourse;
    private JButton back;
    private JScrollPane jsp;
    private JList list;
    private DefaultListModel listModel;
    private JLabel label;
    JPanel jp;
    private ArrayList<Integer> ListOfCourses;


    public CoursePanel(UIController uiController) {
        super(true);
        initialize();
        addComponent();
        setContent();
        addListener(uiController);
    }

    private void initialize() {

        ListOfCourses = new ArrayList<>(GradingSystem.courseRd.queryCourses());                           //ArrayList of course Ids
        listModel = new DefaultListModel();


        for(int i=0;i<ListOfCourses.size();i++){
            CourseDB c = GradingSystem.templateRd.queryCourse(i);
            listModel.addElement(c.getCourseName());          //tr.queryCourse return object of type CourseDB
            System.out.println(listModel.toString());
        }

        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        //list.setPreferredSize(new Dimension(250,80));

        jsp = new JScrollPane(list);
        jsp.setPreferredSize(new Dimension(250, 250));
    }

    private void addComponent() {
        add(jsp);
        jp = new JPanel();
        jp.setLayout(new BoxLayout(jp, BoxLayout.PAGE_AXIS));
        label = new JLabel("Select Course");
        jp.add(label);
        back = new JButton("Back");
        // jp.add(back);
        btnCreateCourse = new JButton("Create Course");
        jp.add(btnCreateCourse);
        btnDeleteCourse = new JButton("Delete Course");
        jp.add(btnDeleteCourse);
        btnEditCourse = new JButton("Edit");
        jp.add(btnEditCourse);
        add(jp);
    }


    private void setContent() {
        btnCreateCourse.addActionListener(e -> {

            });
    }

    private void addListener(UIController uiController) {
        btnCreateCourse.addActionListener(e -> {
            uiController.switchCreateCoursePanel();
        });
        btnDeleteCourse.addActionListener(e -> {
            int index = list.getSelectedIndex();
            Course.delete(index);                                        //Removes course from database based on course_id called from logic.Course
            JOptionPane.showMessageDialog(jp,listModel.get(index) + " removed");
            ListOfCourses.remove(index);                                //Uncomment both
            listModel.removeElementAt(index);
        });

        btnEditCourse.addActionListener(e -> {                          //Work on this when Fuqing is done with the Template
            int index = list.getSelectedIndex();
            uiController.switchSectionList(ListOfCourses.get(index));

        });

        back.addActionListener(e -> {
            uiController.switchMainPanel();
        });

    }

}