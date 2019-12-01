package logic;

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
        Template t = build(nextID, name, root);
        ++nextID;
        // db phase
        return t;
    }

    public static boolean delete(int id) {
        // query courses using this template
        // if no courses using this template
        // remove the template from db
        return true;
    }
}