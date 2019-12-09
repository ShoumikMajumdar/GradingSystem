package ui.panel;

import logic.GradingSystem;
import logic.Template;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TemplateListPanel extends JPanel {


    private JPanel jp;
    private JPanel templateHeader;
    private JButton bttnCreateTemplate;
    private JButton back;
    private JButton bttnDeleteTemplate;
    private JScrollPane jsp;
    private DefaultListModel listModel;
    //private ArrayList<String> ListOfTemplates_test= new ArrayList<>();
    private ArrayList<Integer> ListOFTemplates;
    private JList list;

    public TemplateListPanel(UIController uiController) {
        super(true);
        initialize();
        addComponent();
        setContent();
        addListener(uiController);
    }

    public void initialize(){

        ListOFTemplates = new ArrayList<>(GradingSystem.templateRd.queryTemplates());                          //ArrayList of TemplateIds
        listModel = new DefaultListModel();

        /**
         * Hard Coded List of Templates.
         */

        /*ListOfTemplates_test.add("Template 1");
        ListOfTemplates_test.add("Template 2");
        ListOfTemplates_test.add("Template 3");
        ListOfTemplates_test.add("Template 4");
        ListOfTemplates_test.add("Template 5");
        ListOfTemplates_test.add("Template 6");
*/



        for(int i=0;i<ListOFTemplates.size();i++) {
            listModel.addElement(GradingSystem.templateRd.queryTemplate(i).getTemplateName());          //tr.queryCourse return object of type CourseDB
        }


/*
        for(int i=0;i<ListOfTemplates_test.size();i++){
            listModel.addElement(ListOfTemplates_test.get(i));
        }
*/

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
        //jp.setLayout(new BoxLayout(jp, BoxLayout.PAGE_AXIS));
        bttnCreateTemplate = new JButton("Create Template");
        add(bttnCreateTemplate);
        bttnDeleteTemplate = new JButton("Delete Template");
        add(bttnDeleteTemplate);
        back = new JButton("Back");
        add(back);
        add(jp);

        templateHeader = new JPanel();          //Add Fuqing's Panel
        add(templateHeader);



    }

    public void setContent(){

    }

    public void addListener(UIController uiController){
        back.addActionListener(e -> {
            uiController.switchMainPanel();
        });

        bttnDeleteTemplate.addActionListener(e -> {
            int index = list.getSelectedIndex();
            Template.delete(index);
            ListOFTemplates.remove(index);
            //ListOfTemplates_test.remove(index);
            listModel.removeElementAt(index);
        });

        bttnCreateTemplate.addActionListener(e -> {
            uiController.switchCreateTemplatePanel(0,0);
        });



    }
}
