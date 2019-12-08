package ui.panel;

import logic.Component;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class GTable extends JPanel{
    private Component header;
    private Object[][] table;
    private GridBagConstraints gbc = new GridBagConstraints();

    public GTable(Component header, Object[][] table){
        super(true);
        this.header = header;
        this.table = table;
        update();
    }

    public void update(){
        setLayout(new GridBagLayout());
        Component root = Component.buildTestComponent();
        buildHeader(root, 0, 0, 10, 1);

//        gbc.gridx = 0;
//        gbc.weightx = 1;
//        gbc.gridheight = 1;
//        gbc.gridy = 0;
//        gbc.fill = GridBagConstraints.VERTICAL;
//        for(int i = 0; i < 6; i++){
//            gbc.gridy ++;
//            add(new Button("button " + i), gbc);
//        }
//
//        gbc.gridx++;
//        gbc.weightx = 0;
//        gbc.gridy = 0;
//        gbc.weighty = 1;
//        gbc.gridheight = GridBagConstraints.REMAINDER;
//        gbc.gridheight = 6;
//        gbc.fill = GridBagConstraints.VERTICAL;
//        add(new Button("X"), gbc);

    }

    private void buildHeader(Component root, int x, int y, int w, int h){
        System.out.println(root.name + " " + x + " " + y + " " + w + " " + h);
        if(!root.children.isEmpty()) {
            gbc.gridx = x;
            gbc.gridy = y;
//            gbc.gridwidth = w;
//            gbc.gridheight = h;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            JButton btn = new JButton(root.name);
            btn.setPreferredSize(new Dimension(30,30));
            add(btn, gbc);
            for (Map.Entry<Integer, Component> entry: root.children.entrySet()){
                int _x = entry.getKey() - 1;
                int _y = y + 1;
                int _w = w / root.children.entrySet().size();
                int _h = h;
                buildHeader(root.children.get(entry.getKey()), _x, _y, _w, _h);
            }
        } else {
            gbc.gridx = x;
            gbc.gridy = y;
//            gbc.gridwidth = w;
//            gbc.gridheight = h;
            gbc.fill = GridBagConstraints.VERTICAL;
            JButton btn = new JButton(root.name);
            btn.setPreferredSize(new Dimension(10,10));
            add(btn, gbc);
        }
    }


//    protected static void buildColumnGroupStructure(Component root,
//                                                GroupableTableHeader header,
//                                                ColumnGroup parentGroup,
//                                                TableColumnModel columnModel,
//                                                Int idx) {
//        if (!root.children.isEmpty()) {
//            ColumnGroup group = new ColumnGroup(root.name);
//            if (parentGroup == null) {
//                header.addColumnGroup(group);
//            } else {
//                parentGroup.add(group);
//            }
//            for (Entry<Integer, Component> entry : root.children.entrySet()) {
//                buildColumnGroupStructure(entry.getValue(), header, group, columnModel, idx);
//            }
//        } else {
//            parentGroup.add(columnModel.getColumn(idx.i++));
//        }
//    }
}
