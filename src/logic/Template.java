package logic;

import java.util.ArrayList;

public class Template {

    private static int nextID;

    public int id;

    public String name;

    public Component root;

    protected Template(int id, String name, Component root) {
        this.id = id;
        this.name = name;
        this.root = root;
    }

    public static Template build(int id, String name, Component root) {
        Template t = new Template(id, name, root);
        return t;
    }

    public static Template create(String name) {
        Component root = Component.create(name, 0, 1.0);
        Template t = null;
        if (GradingSystem.templateRd.createTemplate(nextID, name, root.id)) {
            t = build(nextID, name, root);
            ++nextID;
        }
        return t;
    }

    public static boolean delete(int id) {
        // query courses using this template
        // if no courses using this template
        // remove the template from db
        ArrayList<Integer> courses = GradingSystem.templateRd.queryCoursesUsingTemplate(id);
        if (!courses.isEmpty()) {
            return false;
        }
        GradingSystem.templateRd.deleteTemplate(id);
        return true;
    }
}