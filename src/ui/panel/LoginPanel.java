package ui.panel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class LoginPanel extends JPanel {

    JLabel login;
    JLabel name;
    JLabel password;
    JTextField NameField;
    JPasswordField passwordField;
    JPanel jp;
    JButton submit;



    public LoginPanel(UIController uiController) {
        super(true);
        //this.uiController = uiController;
        initialize();
        addComponent();
        setContent();
        addListener(uiController);
    }

    public void initialize(){
        login = new JLabel("LOGIN");
        name = new JLabel("Enter Username");
        password = new JLabel("Enter Password");
        NameField = new JTextField();
        passwordField = new JPasswordField();
        submit = new JButton("Submit");
    }
    public void addComponent(){
        jp = new JPanel(new GridLayout());

        jp.add(login);
        jp.add(name);
        jp.add(NameField);
        jp.add(password);
        jp.add(passwordField);
        jp.setBorder(new LineBorder(Color.GRAY));
        JPanel bp = new JPanel();
        bp.add(submit);

        add(jp, BorderLayout.CENTER);
        add(bp, BorderLayout.PAGE_END);

    }
    public void setContent(){

    }
    public void addListener(UIController uiController){


        submit.addActionListener(e -> {

            String uname  = NameField.getText();
            String pass = passwordField.getText();
            System.out.println(pass);
            if(uname.equals("admin")) {
                if (pass.equals("admin")) {
                    JOptionPane.showMessageDialog(jp, "Welcome");
                    uiController.switchCoursePanel();
                }
                else
                {
                    JOptionPane.showMessageDialog(jp,"Invalid Credentials");
                }
            }
            else{
                JOptionPane.showMessageDialog(jp,"Invalid Credentials");
            }

        });

    }

}
