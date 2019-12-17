package logic;

import db.*;
import Student.StudentDB;

import java.util.ArrayList;
import java.util.Collections;

public class GradingSystem {

    public static final CourseReader courseRd = new CourseReader();

    public static final SectionReader sectionRd = new SectionReader();

    public static final StudentReader studentRd = new StudentReader();

    public static final TemplateReader templateRd = new TemplateReader();

    public static final ComponentReader componentRd = new ComponentReader();

    public static final ScoresReader scoreRd = new ScoresReader();

    public static final BonusReader bonusRd = new BonusReader();

    public static final CommentReader commentRd = new CommentReader();

    public static final CurveReader curveRd = new CurveReader();

    public static final InfoReader infoRd = new InfoReader();

    public GradingSystem() {
    }

    public static void init() {
        Component.restore();
        Course.restore();
        Section.restore();
        Template.restore();
        Student.restore();
    }

    public static void queryTableData(int cid, int sid, Component root,
                                      ArrayList<Student> students,
                                      ArrayList<ArrayList<Grade>> grades) {
        ArrayList<Integer> studentID = studentRd.queryStudents(sid);

        // first we build the student array
        for (Integer i : studentID) {
            StudentDB s = studentRd.queryStudent(i.intValue());
            students.add(Student.build(s.getStudent_id(), s.getName()));
        }

        // then we fill the grades, bonus and comments
        ArrayList<Component> leafChildren = new ArrayList<Component>();
        Component.getAllLeafChildren(leafChildren, root);

        for (Student s  : students) {
            ArrayList<Grade> g = new ArrayList<Grade>();
            for (Component i : leafChildren) {
                double points = scoreRd.getScore(cid, s.id, i.id);
                g.add(Grade.build(cid, s.id, i.id, (int)points));
            }
            grades.add(g);
        }
    }

    public static double average(ArrayList<Double> data) {
        double sum = 0;
        for (Double d : data) {
            sum += d.doubleValue();
        }
        sum /= data.size();
        return sum;
    }

    public static double mean(ArrayList<Double> data) {
        double mean = 0;
        int n = data.size();
        Collections.sort(data);
        if (data.size() % 2 == 0) {
            mean = (data.get(n / 2) + data.get(n / 2 - 1)) / 2;
        } else {
            mean = data.get(n / 2);
        }
        return mean;
    }

    public static double stddev(ArrayList<Double> data) {
        double avg = average(data);
        double stddev = 0;
        double diff;
        for (Double d : data) {
            diff = avg - d.doubleValue();
            stddev += diff * diff;
        }
        stddev /= data.size();
        stddev = Math.sqrt(stddev);
        return stddev;
    }

    public static void setCurve(int sid, int points) {
        curveRd.setCurve(sid, points);
    }

    public static int getCurve(int sid) {
        return curveRd.getCurve(sid);
    }

    public static String digitToLetter(int i) {
        if (i >= 90) {
            return "A+";
        }
        if (i >= 85) {
            return "A";
        }
        if (i >= 75) {
            return "B+";
        }
        if (i >= 65) {
            return "B-";
        }
        return "F";
    }
}