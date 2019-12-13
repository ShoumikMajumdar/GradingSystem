package logic;

import db.SectionReader;
import Student.StudentDB;

import java.util.*;

public class Section {

    private static int nextID;

    public int id;

    public HashMap<Integer, Student> students;

    public static Section build(int id) {
        Section s = new Section(id);
        return s;
    }

    public static Section create() {
        Section s = null;
        if (GradingSystem.sectionRd.createSection(nextID)) {
            s = Section.build(nextID);
            ++nextID;
        }
        return s;
    }

    public static void delete(int id) {
        GradingSystem.sectionRd.deleteSection(id);
    }

    protected Section(int id) {
        this.id = id;
        students = new HashMap<Integer, Student>();
    }

    public static void addNewStudent(int scid, int stid) {
        StudentDB s = GradingSystem.studentRd.queryStudent(stid);
        GradingSystem.studentRd.addNewStudent(stid, scid, s.getName());
    }

    public static void deleteStudent(int scid, int stid) {
        GradingSystem.studentRd.deleteStudent(stid, scid);
    }
}