package ui.panel;


import db.TemplateReader;
import logic.Course;
import logic.GradingSystem;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class SelectTemplatePanel extends JPanel {


    private JPanel jp;
    private JButton CreateTemplate;
    private JButton SelectTemplate;
    private JScrollPane jsp;
    private DefaultListModel listModel;
    private ArrayList<Integer> ListOFTemplates;
    private JList list;
    private int cid;


    public SelectTemplatePanel(UIController uiController, int cid) {
        super(true);
        initialize(cid);
        addComponent();
        setContent();
        addListener(uiController);
    }

    private void initialize(int cid) {
        this.cid = cid;
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
        /*back = new JButton("Back");
        add(back);*/
        add(jp);
    }


    private void setContent(){


    }


    private void addListener(UIController uiController){

        CreateTemplate.addActionListener(e -> {
            uiController.switchCreateTemplatePanel(1,cid);
        });


        SelectTemplate.addActionListener(e -> {
            int index = list.getSelectedIndex();
            int tid = ListOFTemplates.get(index);
            Course.adaptTemplate(cid,tid);
            uiController.switchSectionList(cid);
        });



    }


}

