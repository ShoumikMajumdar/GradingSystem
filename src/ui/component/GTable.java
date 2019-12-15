package ui.component;

import logic.Component;
import logic.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GTable extends JPanel{

    private Component root;
    private HashMap<Integer, HashMap<Integer, GTextField>> tableMap = new HashMap<>();
    private int courseId;
    private int sectionId;

    public GTable(int courseId, int sectionId){
        super(true);
        this.courseId = courseId;
        this.sectionId = sectionId;
        setLayout(new GridBagLayout());
        update();
    }

    public void update() {
        removeAll();

        root = Course.getRoot(courseId);


        buildTableHeader(root);

        buildTableData(root);
    }

    protected void buildTableHeader(Component root) {
        int xStart = 0;
        int maxHeight = root.getHeight();
        int templateWidth = root.getWidth();
        GridBagConstraints gbcName = getConstraints(xStart, 0, 1, maxHeight);
        JButton btnName = new JButton("Name");
        add(btnName, gbcName);
        ++xStart;

        buildTemplateHeader(root, root.id, xStart, 0, templateWidth, maxHeight);
        xStart += templateWidth;

        GridBagConstraints gbcFinal = getConstraints(xStart, 0, 1, maxHeight);
        JButton btnFinal = new JButton("Final");
        add(btnFinal, gbcFinal);
        ++xStart;

        GridBagConstraints gbcBonus = getConstraints(xStart, 0, 1, maxHeight);
        JButton btnBonus = new JButton("Bonus");
        add(btnBonus, gbcBonus);
        ++xStart;
    }

    private void buildTemplateHeader(Component root, int parentId, int x, int y, int w, int max_h){
        if(!root.children.isEmpty()) {
            GridBagConstraints gbc_new = getConstraints(x, y, w, 1);
            GButton btn = new GButton(root.name, parentId, root.id);
            add(btn, gbc_new);
            int i = 0;
            int _w = 0;
            for (Map.Entry<Integer, Component> entry: root.children.entrySet()){
                int _x = x + _w;
                int _y = y + 1;
                buildTemplateHeader(entry.getValue(), root.id, _x, _y, entry.getValue().getWidth(), max_h);
                _w += entry.getValue().getWidth();
                ++i;
            }
        } else {
            GridBagConstraints gbc_new = getConstraints(x, y, w, max_h - y);
            GButton btn = new GButton(root.name, parentId, root.id);
            add(btn, gbc_new);
        }
    }

    public GTextField getCell(int sid, int cid){
        return tableMap.get(sid).get(cid);
    }

    public void repaint(){

    }

    protected void buildTableData(Component root) {

        ArrayList<Student> students = new ArrayList<Student>();
        ArrayList<ArrayList<Grade>> grades = new ArrayList<ArrayList<Grade>>();
        GradingSystem.queryTableData(courseId, sectionId, root, students, grades);
        if(students.isEmpty()){
            Component.buildTestData(students, grades, root);
        }

        ArrayList<Double> average = new ArrayList<Double>();
        ArrayList<Double> finals = new ArrayList<Double>();

        int xStart = 0;
        int yStart = root.getHeight();
        for (int i = 0; i < students.size(); ++i) {
            xStart = 0;
            GridBagConstraints gbcStudent = getConstraints(xStart, yStart, 1, 1);
            GLabel lblStudent = new GLabel(students.get(i).name, students.get(i).id, sectionId);
            add(lblStudent, gbcStudent);
            ++xStart;

            ArrayList<Grade> grd = grades.get(i);
            HashMap<Integer, GTextField> columns = new HashMap<>();
            for (int j = 0; j < grd.size(); ++j) {
                GridBagConstraints gbcGrade = getConstraints(xStart, yStart, 1, 1);

                GTextField txtGrade = new GTextField(
                    "" + grd.get(j).points,
                    grd.get(j).studentID,
                    grd.get(j).componentID,
                        grd.get(j).courseID,
                        sectionId);
                columns.put(grd.get(j).componentID, txtGrade);
                add(txtGrade, gbcGrade);
                ++xStart;
            }
            tableMap.put(grd.get(0).studentID, columns);

            ArrayList<Grade> grdClone = new ArrayList<Grade>();
            for (int x = 0; x < grd.size(); ++x) {
                grdClone.add(grd.get(x));
            }

            int finalGrade = root.calculateFinalGrade(grdClone);
            finals.add(Double.valueOf(finalGrade));
            GridBagConstraints gbcFinal = getConstraints(xStart, yStart, 1, 1);
            JLabel lblFinalGrade = new JLabel("" + finalGrade);
            add(lblFinalGrade, gbcFinal);
            ++xStart;
            ++yStart;
        }

        xStart = 0;
        GridBagConstraints gbcAvgLbl = getConstraints(xStart, yStart, 1, 1);
        JLabel lblAvgName = new JLabel("Average");
        add(lblAvgName, gbcAvgLbl);
        ++xStart;

        for (int i = 0; i < grades.get(0).size(); ++i) {
            ArrayList<Double> data = new ArrayList<Double>();
            for (int j = 0; j < grades.size(); ++j) {
                data.add(Double.valueOf(grades.get(j).get(i).points));
            }
            double avg = GradingSystem.average(data);
            average.add(Double.valueOf(avg));
            GridBagConstraints gbcAvg = getConstraints(xStart, yStart, 1, 1);
            JLabel lblAvg = new JLabel("" + (int)avg);
            add(lblAvg, gbcAvg);
            ++xStart;
        }

        double finalAvg = GradingSystem.average(finals);
        GridBagConstraints gbcFinAvgLbl = getConstraints(xStart, yStart, 1, 1);
        JLabel lblFinAvg = new JLabel("" + (int)finalAvg);
        add(lblFinAvg, gbcFinAvgLbl);
        ++xStart;

        ++yStart;

        xStart = 0;
        GridBagConstraints gbcMeanLbl = getConstraints(xStart, yStart, 1, 1);
        JLabel lblMeanName = new JLabel("Mean");
        add(lblMeanName, gbcMeanLbl);
        ++xStart;

        for (int i = 0; i < grades.get(0).size(); ++i) {
            ArrayList<Double> data = new ArrayList<Double>();
            for (int j = 0; j < grades.size(); ++j) {
                data.add(Double.valueOf(grades.get(j).get(i).points));
            }
            double mean = GradingSystem.mean(data);
            GridBagConstraints gbcMean = getConstraints(xStart, yStart, 1, 1);
            JLabel lblMean = new JLabel("" + (int)mean);
            add(lblMean, gbcMean);
            ++xStart;
        }

        double finMean = GradingSystem.mean(finals);
        GridBagConstraints gbcFinMeanLbl = getConstraints(xStart, yStart, 1, 1);
        JLabel lblFinMean= new JLabel("" + (int)finMean);
        add(lblFinMean, gbcFinMeanLbl);
        ++xStart;

        ++yStart;

        xStart = 0;
        GridBagConstraints gbcStdLbl = getConstraints(xStart, yStart, 1, 1);
        JLabel lblStdName = new JLabel("Std Dev");
        add(lblStdName, gbcStdLbl);
        ++xStart;

        for (int i = 0; i < grades.get(0).size(); ++i) {
            ArrayList<Double> data = new ArrayList<Double>();
            for (int j = 0; j < grades.size(); ++j) {
                data.add(Double.valueOf(grades.get(j).get(i).points));
            }
            double std = GradingSystem.stddev(data);
            GridBagConstraints gbcStdDev = getConstraints(xStart, yStart, 1, 1);
            JLabel lblStdDev = new JLabel("" + (int)std);
            add(lblStdDev, gbcStdDev);
            ++xStart;
        }

        double finStdDev = GradingSystem.stddev(finals);
        GridBagConstraints gbcFinStdLbl = getConstraints(xStart, yStart, 1, 1);
        JLabel lblFinStdDev = new JLabel("" + (int)finStdDev);
        add(lblFinStdDev, gbcFinStdLbl);
        ++xStart;

        ++yStart;
    }

    protected GridBagConstraints getConstraints(int x, int y, int w, int h) {
        return new GridBagConstraints(
            x, y, w, h, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0);
    }
}
