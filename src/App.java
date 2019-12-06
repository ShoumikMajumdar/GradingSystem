import ui.panel.MainPanel;
import ui.panel.UIController;

import javax.swing.*;
import java.awt.*;

public class App {
    private JFrame frame;

    private JPanel holderPanel;

    private MainPanel mainPanel;

    public JPanel getHolderPanel() {
        return holderPanel;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
                try {
                    App window = new App();
//                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
    }

    public App() {
        initialize();
    }

    private void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException |
                 InstantiationException |
                 IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        UIController uiController = new UIController();
//        frame = new JFrame();
//        frame.setBounds(UIConsts.MAIN_WINDOW_X, UIConsts.MAIN_WINDOW_Y,
//                        UIConsts.MAIN_WINDOW_WIDTH, UIConsts.MAIN_WINDOW_HEIGHT);
//        frame.setTitle(UIConsts.APP_NAME);
//        // frame.setIconImage();
//        // frame.setBackground();
//
//        mainPanel = new MainPanel();
//
//        holderPanel = new JPanel(true);
//        holderPanel.add(mainPanel);
//        frame.add(holderPanel);
//
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}