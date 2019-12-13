package ui.panel;

import logic.Course;
import logic.GradingSystem;
import logic.Section;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SectionsList extends JPanel {

    private ArrayList<Integer> ListOfSections;
    private ArrayList<Integer> ListOfSections_test= new ArrayList<Integer>();
    private JScrollPane jsp;
    private JPanel jp;
    private JList list;
    private DefaultListModel listModel;
    private JButton btnCreateSection;
    private JButton btnDeleteSection;
    private JButton back;
    private JButton edit;
    private int cid;

    public SectionsList(UIController uiController, int cid) {
        super(true);
        initialize(cid);
        addComponent();
        setContent();
        addListener(uiController);
    }

    public void initialize(int cid){
        this.cid = cid;
        ListOfSections = new ArrayList<>(GradingSystem.sectionRd.querySections(cid));
        listModel = new DefaultListModel();


        for(int i=0;i<ListOfSections.size();i++) {
            listModel.addElement("Section " + ListOfSections.get(i));
        }


        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);

        jsp = new JScrollPane(list);
        jsp.setPreferredSize(new Dimension(500, 250));


    }

    private void setContent() {
    }

    private void addComponent() {
        add(jsp);
        jp = new JPanel();
        btnCreateSection = new JButton("Add Section");
        add(btnCreateSection);
        btnDeleteSection = new JButton("Remove Section");
        add(btnDeleteSection);
        edit = new JButton("Edit");
        add(edit);
        back = new JButton("Back");
        add(back);
        add(jp);

    }


    public void addListener(UIController uiController){

        btnCreateSection.addActionListener(e -> {
            Course.addNewSection(cid);
            JOptionPane.showMessageDialog(jp,"Section added");
            uiController.switchSectionList(cid);

        });

        btnDeleteSection.addActionListener(e -> {
            int index = list.getSelectedIndex();
            Course.deleteSection(cid,index);                                        //Removes course from database based on course_id called from logic.Course
            JOptionPane.showMessageDialog(jp,listModel.get(index) + " Removed");
            ListOfSections.remove(index);
            listModel.removeElementAt(index);

        });

        back.addActionListener(e -> {
            uiController.switchCoursePanel();
        });


        edit.addActionListener(e -> {
            int index = list.getSelectedIndex();
            uiController.switchTablePanel(cid,index);
        });

    }
}
