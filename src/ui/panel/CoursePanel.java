package ui.panel;

import db.CourseReader;
import db.TemplateReader;
import logic.Course;
import logic.GradingSystem;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.*;

public class CoursePanel extends JPanel {
    private JButton btnCreateCourse;
    private JButton btnDeleteCourse;
    private JButton btnEditCourse;
    private JButton back;
    private JScrollPane jsp;
    private JList list;
    private DefaultListModel listModel;
    private JLabel label;
    //private ArrayList<Integer> ListOfCourses_test = new ArrayList<Integer>();
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


        /**
         * Hard Coded List of courses.
         */

        /*ListOfCourses_test.add(0);
        ListOfCourses_test.add(1);
        ListOfCourses_test.add(2);
        ListOfCourses_test.add(3);
        ListOfCourses_test.add(4);
        ListOfCourses_test.add(5);*/



        listModel = new DefaultListModel();
        for(int i=0;i<ListOfCourses.size();i++){
            listModel.addElement(GradingSystem.templateRd.queryCourse(i).getCourseName());          //tr.queryCourse return object of type CourseDB
            System.out.println(listModel.toString());
        }
        /*for(int i=0;i<ListOfCourses_test.size();i++){
            listModel.addElement("Course " + ListOfCourses_test.get(i));
        }*/

        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        //list.setPreferredSize(new Dimension(250,80));

        jsp = new JScrollPane(list);
        jsp.setPreferredSize(new Dimension(250, 250));
    }

    private void addComponent() {
        JPanel jp = new JPanel();
        jp.setLayout(new BorderLayout());
        jp.add(jsp);
        label = new JLabel("Select Course");
        add(label);
        back = new JButton("Back");
        add(back);
        btnCreateCourse = new JButton("Create Course");
        add(btnCreateCourse);
        btnDeleteCourse = new JButton("Delete Course");
        add(btnDeleteCourse);
        btnEditCourse = new JButton("Edit");
        add(btnEditCourse);
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
            ListOfCourses.remove(index);                                  //Uncomment both
            //ListOfCourses_test.remove(index);
            listModel.removeElementAt(index);
        });

        btnEditCourse.addActionListener(e -> {                          //Work on this when Fuqing is done with the Template
            int index = list.getSelectedIndex();
            System.out.println(index);
            //System.out.println(ListOfCourses_test.get(index));
            uiController.switchSectionList(ListOfCourses.get(index));

        });

        back.addActionListener(e -> {
            uiController.switchMainPanel();
        });

    }

}