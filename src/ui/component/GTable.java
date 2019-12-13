package ui.component;

import logic.Component;
import logic.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class GTable extends JPanel{

    public GTable(){
        super(true);
        setLayout(new GridBagLayout());
        update(Component.buildTestComponent());
    }

    public void update(Component root) {
        buildTableHeader(root);
        ArrayList<Student> students = new ArrayList<Student>();
        ArrayList<ArrayList<Grade>> grades = new ArrayList<ArrayList<Grade>>();
        ArrayList<Bonus> bonus = new ArrayList<Bonus>();
        ArrayList<Comment> comments = new ArrayList<Comment>();
        Component.buildTestData(students, grades, bonus, comments, root);
        int xStart = 0;
        int yStart = root.getHeight();
        for (int i = 0; i < students.size(); ++i) {
            GridBagConstraints gbcStudent = new GridBagConstraints(
                xStart, yStart + i, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0);
            GLabel lblStudent = new GLabel(students.get(i).name, i+1);
            add(lblStudent, gbcStudent);

            ArrayList<Grade> grd = grades.get(i);
            for (int j = 0; j < grd.size(); ++j) {
                GridBagConstraints gbcGrade = new GridBagConstraints(
                    xStart + 1 + j, yStart + i, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0);

                GTextField txtGrade = new GTextField(
                    "" + grd.get(j).points,
                    grd.get(j).studentID,
                    grd.get(j).componentID);
                add(txtGrade, gbcGrade);
            }
        }
    }

    protected void buildTableHeader(Component root) {
        int xStart = 0;
        int maxHeight = root.getHeight();
        int templateWidth = root.getWidth();
        GridBagConstraints gbcName = new GridBagConstraints(
            xStart, 0, 1, maxHeight, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0);
        JButton btnName = new JButton("Name");
        add(btnName, gbcName);
        ++xStart;

        buildTemplateHeader(root, xStart, 0, templateWidth, maxHeight);
        xStart += templateWidth;

        GridBagConstraints gbcFinal = new GridBagConstraints(
            xStart, 0, 1, maxHeight, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0);
        JButton btnFinal = new JButton("Final");
        add(btnFinal, gbcFinal);
        ++xStart;

        GridBagConstraints gbcBonus = new GridBagConstraints(
            xStart, 0, 1, maxHeight, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0);
        JButton btnBonus = new JButton("Bonus");
        add(btnBonus, gbcBonus);
        ++xStart;
    }

    private void buildTemplateHeader(Component root, int x, int y, int w, int max_h){
        if(!root.children.isEmpty()) {
            GridBagConstraints gbc_new = new GridBagConstraints(
                x, y, w, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0);
            GButton btn = new GButton(root.name, root.id);
            add(btn, gbc_new);
            int i = 0;
            int _w = 0;
            for (Map.Entry<Integer, Component> entry: root.children.entrySet()){
                int _x = x + _w;
                int _y = y + 1;
                buildTemplateHeader(entry.getValue(), _x, _y, entry.getValue().getWidth(), max_h);
                _w += entry.getValue().getWidth();
                ++i;
            }
        } else {
            GridBagConstraints gbc_new = new GridBagConstraints(
                x, y, w, max_h - y, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0);
            GButton btn = new GButton(root.name, root.id);
            add(btn, gbc_new);
        }
    }
}
