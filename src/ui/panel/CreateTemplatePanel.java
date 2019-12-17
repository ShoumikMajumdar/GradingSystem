package ui.panel;

import logic.Course;
import logic.Template;

import javax.swing.*;

public class CreateTemplatePanel extends JPanel {

    private JLabel label;
    private JPanel jp;
    private JTextField TemplateName;
    private JButton Submit;
    private JButton Back;
    private int flag;
    private int cid;



    public CreateTemplatePanel(UIController uiController, int flag, int cid) {
        super(true);
        initialize(flag,cid);
        addComponent();
        setContent();
        addListener(uiController);
    }

    public void initialize(int flag, int cid){
        this.flag=flag;
        this.cid = cid;
    }

    public void addComponent(){
        jp = new JPanel();
        jp.setLayout(new BoxLayout(jp, BoxLayout.PAGE_AXIS));
        label = new JLabel("Enter name of the Template");
        jp.add(label);
        TemplateName = new JTextField();
        jp.add(TemplateName);
        Submit = new JButton("Create Template");
        add(Submit);
        Back = new JButton("Back");
        add(Back);
        add(jp);
    }

    public void setContent(){

    }

    public void addListener(UIController uiController){
        Submit.addActionListener(e -> {
            String newTemplate = TemplateName.getText();
            JOptionPane.showMessageDialog(jp,"Template " + newTemplate + " Added");
            if(flag==0){                //Flag 0  mean call made from TemplatesList panel
                Template.create(newTemplate);
                uiController.switchTemplateListPanel();
            }
            else{                       //Flag 1 means call made from Course panel
                Template t = Template.create(newTemplate);
                Course.adaptTemplate(cid,t.id);
                System.out.println(cid);
                uiController.switchSectionList(cid);

            }

        });

        Back.addActionListener(e -> {
            if(flag==0) {                           //Call  made from template pages
                uiController.switchTemplateListPanel();
            }
            else{                                   // call made from course page
                uiController.switchSelectTemplatePanel(cid);
            }
        });
    }
}

