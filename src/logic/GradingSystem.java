package logic;

import db.*;
import Student.StudentDB;

import java.util.ArrayList;

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
                                      ArrayList<ArrayList<Grade>> grades,
                                      ArrayList<Bonus> bonus,
                                      ArrayList<Comment> comments) {
        ArrayList<Integer> studentID = studentRd.queryStudents(sid);

        // first we build the student array
        for (Integer i : studentID) {
            StudentDB s = studentRd.queryStudent(i.intValue());
            students.add(Student.build(s.getStudent_id(), s.getName()));
        }

        // then we fill the grades, bonus and comments
        ArrayList<Integer> leafChildrenID = Component.getAllLeafChildrenID(root.id);
        for (Student s  : students) {
            ArrayList<Grade> g = new ArrayList<Grade>();
            for (Integer i : leafChildrenID) {
                double points = scoreRd.getScore(cid, s.id, i.intValue());
                g.add(Grade.build(cid, s.id, i.intValue(), (int)points));

                double b = bonusRd.getBonus(cid, s.id, i.intValue());
                if (b != 0) {
                    bonus.add(Bonus.build(cid, s.id, i.intValue(), (int)b));
                }

                String comment = commentRd.getComment(cid, s.id, i.intValue());
                if (!comment.isEmpty()) {
                    comments.add(Comment.build(cid, s.id, i.intValue(), comment));
                }
            }
            grades.add(g);
        }
    }
}