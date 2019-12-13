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


        for(int i=0;i<ListOFTemplates.size();i++) {
            listModel.addElement(GradingSystem.templateRd.queryTemplate(i).getTemplateName());          //tr.queryCourse return object of type CourseDB
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
            boolean canDelete = Template.delete(index);
            if(canDelete==true) {
                JOptionPane.showMessageDialog(jp,listModel.get(index) + " Removed");
                ListOFTemplates.remove(index);
                listModel.removeElementAt(index);
            }
            else{
                JOptionPane.showMessageDialog(jp,"Cannot Delete Template as a course is associated with it");
            }

        });

        bttnCreateTemplate.addActionListener(e -> {
            uiController.switchCreateTemplatePanel(0,0);
        });



    }
}
