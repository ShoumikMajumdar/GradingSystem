package ui.panel;


import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class SelectTemplatePanel extends JPanel {


    private JPanel jp;
    private JButton CreateTemplate;
    private JButton SelectTemplate;
    private JButton back;
    private JButton DeleteTemplate;
    private JScrollPane jsp;
    private DefaultListModel listModel;
    private ArrayList<String> ListOfTemplates_test= new ArrayList<>();
    //private ArrayList<Integer> ListOFTemplates;
    private JList list;


    public SelectTemplatePanel(UIController uiController) {
        super(true);
        initialize();
        addComponent();
        setContent();
        addListener(uiController);
    }

    private void initialize() {

        //ListOfTemplate = new ArrayList<>(GradingSystem.TemplateRd.queryTemplate());                           //ArrayList of TemplateIds

        /**
         * Hard Coded List of Templates.
         */

        ListOfTemplates_test.add("Template 1");
        ListOfTemplates_test.add("Template 2");
        ListOfTemplates_test.add("Template 3");
        ListOfTemplates_test.add("Template 4");
        ListOfTemplates_test.add("Template 5");
        ListOfTemplates_test.add("Template 6");


        listModel = new DefaultListModel();

        /***
         *              Code to query list of templates using API that returns an array list of Template Ids as integers.
         */
        //for(int i=0;i<ListOfTemplate.size();i++){
         //   listModel.addElement(GradingSystem.templateRd.queryCourse(i).getCourseName());          //tr.queryCourse return object of type CourseDB

        for(int i=0;i<ListOfTemplates_test.size();i++){
            listModel.addElement(ListOfTemplates_test.get(i));              //Hard coded list of templates.
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
        CreateTemplate = new JButton("Create New Template");
        jp.add(CreateTemplate);
        SelectTemplate = new JButton("Select Existing Template");
        jp.add(SelectTemplate);
        back = new JButton("Back");
        add(back);
        /*DeleteTemplate = new JButton("Delete Template");
        add(DeleteTemplate);*/
        add(jp);
    }


    private void setContent(){


    }


    private void addListener(UIController uiController){
        back.addActionListener(e -> {
            uiController.switchCreateCoursePanel();
        });
        /**
         *          Need to change this after we have the template query API
         */
        /*CreateTemplate.addActionListener(e -> {
            uiController.switchCoursePanel();                   //Work on this when fuqing is done with the template.
        });

        SelectTemplate.addActionListener(e -> {
            uiController.switchCoursePanel();                   //Work on this when fuqing is done with the template.
        });


         */
    }


}

