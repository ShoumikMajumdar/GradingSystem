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
        Course c = null;
        if (GradingSystem.courseRd.createCourse(nextID, name)) {
            c = Course.build(nextID, name);
            ++nextID;
        }
        return c;
    }

    public static void delete(int id) {
        GradingSystem.courseRd.deleteCourse(id);
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
        GradingSystem.sectionRd.addNewSection(cid, s.id);
    }

    public static void deleteSection(int cid, int sid) {
        GradingSystem.sectionRd.deleteSection(sid);
    }

    public static void adaptTemplate(int cid, int tid) {
        // Template = // TODO query template
        // GradingSystem.templateRd.adaptTemplate(cid, tid, );
    }
}