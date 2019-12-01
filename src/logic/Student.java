package logic;

public class Student {

    private static int nextID;

    public final String name;

    public final int id;

    public static Student build(int id, String name) {
        Student s = new Student(id, name);
        return s;
    }

    public static Student create(String name) {
        Student s = Student.build(nextID, name);
        ++nextID;
        // db phase
        return s;
    }

    protected Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}