package logic;

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
        Section s = Section.build(nextID);
        ++nextID;
        // db phase
        return s;
    }

    protected Section(int id) {
        this.id = id;
        students = new HashMap<Integer, Student>();
    }

    public static void addNewStudent(int scid, String name) {
        Student s = Student.create(name);
        // db phase
    }

    public static void deleteStudent(int scid, int stid) {
        // db phase
    }
}