package ui.panel;

import logic.Component;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class GTable extends JPanel{

    public GTable(){
        super(true);
        update();
    }

    public void update(){
        setLayout(new GridBagLayout());
        Component root = Component.buildTestComponent();
        buildHeader(root, 0, 0, root.getWidth(), root.getHeight());
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
