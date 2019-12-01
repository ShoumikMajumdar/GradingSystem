package logic;

import java.util.*;

public class Course {

    private static int nextID;

    public final int id;

    public final String name;

    public HashMap<Integer, Section> sections;

    public int tid; // template id

    public static Course build(int id, String name) {
        Course c = new Course(id, name);
        return c;
    }

    public static Course build(int id, String name, int tid) {
        Course c = new Course(id, name, tid);
        return c;
    }

    public static Course create(String name) {
        // db phase
        Course c = Course.build(nextID, name);
        ++nextID;
        return c;
    }

    public static void delete(int id) {
        // db phase
    }

    protected Course(int id, String name, int tid) {
        this.id = id;
        this.name = name;
        this.tid = tid;
        sections = new HashMap<Integer, Section>();
    }

    protected Course(int id, String name) {
        this(id, name, -1);
    }

    public static void addNewSection(int cid) {
        Section s = Section.create();
        // db phase
    }

    public static void deleteSection(int cid, int sid) {
        // db phase
    }

    public static void adaptTemplate(int cid, int tid) {
        // db phase
    }
}