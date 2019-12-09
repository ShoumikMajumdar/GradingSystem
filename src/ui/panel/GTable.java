package ui.panel;

import logic.Component;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class GTable extends JPanel{

    public GTable(){
        super(true);
        setLayout(new GridBagLayout());
        update(Component.buildTestComponent());
    }

    public void update(Component root) {
        int xStart = 0;
        int maxHeight = root.getHeight();
        int templateWidth = root.getWidth();
        GridBagConstraints gbcName = new GridBagConstraints(xStart, 0, 1, maxHeight, 0.0, 0.0,
                                                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                            new Insets(0, 0, 0, 0), 0, 0);
        JButton btnName = new JButton("Name");
        add(btnName, gbcName);
        ++xStart;

        buildHeader(root, xStart, 0, templateWidth, maxHeight);
        xStart += templateWidth;

        GridBagConstraints gbcFinal = new GridBagConstraints(xStart, 0, 1, maxHeight, 0.0, 0.0,
                                                             GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                             new Insets(0, 0, 0, 0), 0, 0);
        JButton btnFinal = new JButton("Final");
        add(btnFinal, gbcFinal);
        ++xStart;

        GridBagConstraints gbcBonus = new GridBagConstraints(xStart, 0, 1, maxHeight, 0.0, 0.0,
                                                             GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                             new Insets(0, 0, 0, 0), 0, 0);
        JButton btnBonus = new JButton("Bonus");
        add(btnBonus, gbcBonus);
        ++xStart;
    }

    private void buildHeader(Component root, int x, int y, int w, int max_h){
        if(!root.children.isEmpty()) {
            GridBagConstraints gbc_new = new GridBagConstraints(x, y, w, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0);
            JButton btn = new JButton(root.name);
            add(btn, gbc_new);
            int i = 0;
            int _w = 0;
            for (Map.Entry<Integer, Component> entry: root.children.entrySet()){
                int _x = x + _w;
                int _y = y + 1;
                buildHeader(entry.getValue(), _x, _y, entry.getValue().getWidth(), max_h);
                _w += entry.getValue().getWidth();
                ++i;
            }
        } else {
            GridBagConstraints gbc_new = new GridBagConstraints(x, y, w, max_h - y, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0);
            JButton btn = new JButton(root.name);
            add(btn, gbc_new);
        }
    }
}
