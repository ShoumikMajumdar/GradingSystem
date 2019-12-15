package logic;

import db.StudentReader;

public class Student {

    private static int nextID;

    public final String name;

    public final int id;

    private static final String DATA_KEY = "student_next_id";

    public static Student build(int id, String name) {
        Student s = new Student(id, name);
        return s;
    }

    public static Student create(String name) {
        Student s = null;
        if (GradingSystem.studentRd.createStudent(nextID, name)) {
            s = Student.build(nextID, name);
            ++nextID;
            GradingSystem.infoRd.setData(DATA_KEY, nextID);
        }
        return s;
    }

    public static void restore() {
        nextID = GradingSystem.infoRd.getData(DATA_KEY);
        if (nextID < 0) {
            nextID = 0;
        }
    }

    protected Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}