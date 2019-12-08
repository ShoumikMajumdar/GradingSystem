package ui.panel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TemplateListPanel extends JPanel {


    private JPanel jp;
    private JPanel templateHeader;
    private JButton CreateTemplate;
    private JButton back;
    private JButton DeleteTemplate;
    private JScrollPane jsp;
    private DefaultListModel listModel;
    private ArrayList<String> ListOfTemplates_test= new ArrayList<>();
    //private ArrayList<Integer> ListOFTemplates;
    private JList list;

    public TemplateListPanel(UIController uiController) {
        super(true);
        initialize();
        addComponent();
        setContent();
        addListener(uiController);
    }

    public void initialize(){
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

        jsp = new JScrollPane(list);
        jsp.setPreferredSize(new Dimension(500, 250));

    }

    public void addComponent(){
        add(jsp);
        jp = new JPanel();
        jp.setLayout(new BoxLayout(jp, BoxLayout.PAGE_AXIS));
        CreateTemplate = new JButton("Create Template");
        jp.add(CreateTemplate);
        DeleteTemplate = new JButton("Delete Template");
        jp.add(DeleteTemplate);
        back = new JButton("Back");
        add(back);
        /*DeleteTemplate = new JButton("Delete Template");
        add(DeleteTemplate);*/
        add(jp);

        templateHeader = new JPanel();
        add(templateHeader);



    }

    public void setContent(){

    }

    public void addListener(UIController uiController){
        back.addActionListener(e -> {
            uiController.switchMainPanel();
        });



    }
}
