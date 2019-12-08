package logic;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class Component {
    private static int nextID = 0;

    public static Component create(String name, int points, double percent) {
        Component c = null;
        if (GradingSystem.componentRd.createComponent(nextID, name, percent, points)) {
            c = new Component(nextID, name, points, percent);
            ++nextID;
        }
        return c;
    }

    public final int id;

    // how much percent this component takes among its siblings
    public final double percent;

    // name of this component
    public final String name;

    // how many points this component is assigned
    private final int points;

    // children of this component
    public LinkedHashMap<Integer, Component> children = new LinkedHashMap<Integer, Component>();

    protected Component(int id, String name, int points, double percent) {
        this.id = id;
        this.name = name;
        this.points = points;
        this.percent = percent;
    }

    private Component() {
        this(0, "", 0, 0);
    }

    public int getPoints() {
        if (children.isEmpty()) {
            return points;
        }
        int sum = 0;
        for (Entry<Integer, Component> e : children.entrySet()) {
            sum += e.getValue().getPoints();
        }
        return sum;
    }

    public void addChild(Component child) {
        children.put(Integer.valueOf(child.id), child);
    }

    public static void addChild(int parentID, int childID) {
        GradingSystem.componentRd.addChild(parentID, childID);
    }

    public void removeChild(Component child) {
        children.remove(Integer.valueOf(child.id));
    }

    public static void deleteChild(int parentID, int childID) {
        GradingSystem.componentRd.deleteChild(parentID, childID);
    }

    public String toString() {
        String s = ("Name: " + name + " Points: " + points + " Percent: " + percent);
        return s;
    }

    public int getHeight() {
        int height = 1;
        int maxChildrenHeight = 0;
        for (Entry<Integer, Component> entry : children.entrySet()) {
            maxChildrenHeight = Math.max(maxChildrenHeight, entry.getValue().getHeight());
        }
        return height + maxChildrenHeight;
    }

    public int getWidth() {
        if (children.isEmpty()) {
            return 1;
        }
        int width = 0;
        for (Entry<Integer, Component> entry : children.entrySet()) {
            width += entry.getValue().getWidth();
        }
        return width;
    }

    public static void printRoot(Component root) {
        System.out.println(root);
        for (Entry<Integer, Component> e : root.children.entrySet()) {
            printRoot(e.getValue());
        }
    }

    /**
     * Build a test component
     * |---------------------------------------------------------------------------------------------------|
     * |                                                CS 591                                             |
     * |---------------------------------------------------------------------------------------------------|
     * |              |              Independent Assignment    | Midterm Exam  |            |              |
     * |              |----------------------------------------|---------------|            |              |
     * |              | Tic Tac Toe |  Card   |                |       |       |            |              |
     * |              |-------------|---------|                |       |       |            |              |
     * |Participation | TTT1 | TTT2 | BJ | TE | Cave Adventure | Mid W | Mid P | Final Exam | Final Project|
     * |---------------------------------------------------------------------------------------------------|
     */
    public static Component buildTestComponent() {

        Component root = Component.create("CS591", 0, 1.0);

        Component participation = Component.create("Participation", 0, 0.05);
        root.addChild(participation);

        Component assignments = Component.create("Independent Assignments", 0, 0.25);
        root.addChild(assignments);

        Component midtermExam = Component.create("Midterm Exam", 0, 0.35);
        root.addChild(midtermExam);

        Component finalExam = Component.create("Final Exam", 0, 0.15);
        root.addChild(finalExam);

        Component finalProject = Component.create("Final Project", 0, 0.20);
        root.addChild(finalProject);

        Component tictactoe = Component.create("Tic Tac Toe", 0, 0.2);
        Component ttt1 = Component.create("Tic Tac Toe V1", 50, 0.5);
        Component ttt2 = Component.create("Tic Tac Toe V2", 50, 0.5);
        tictactoe.addChild(ttt1);
        tictactoe.addChild(ttt2);
        assignments.addChild(tictactoe);

        Component cardGame = Component.create("Card Game", 0, 0.2);
        Component bj = Component.create("Black Jack", 100, 0.5);
        Component te = Component.create("Trianta Ena", 100, 0.5);
        cardGame.addChild(bj);
        cardGame.addChild(te);
        assignments.addChild(cardGame);

        Component caveAdventure = Component.create("Cave Adeventure", 100, 0.1);
        assignments.addChild(caveAdventure);

        Component midtermWritten = Component.create("Midterm Written", 0, 0.6);
        Component midtermPracticum = Component.create("Midterm Practicum", 0, 0.4);
        midtermExam.addChild(midtermWritten);
        midtermExam.addChild(midtermPracticum);

        return root;
    }

    public static void main(String[] args) {
        System.out.println("Testing Components...");

        Component root = buildTestComponent();

        Component.printRoot(root);

        System.out.println("Height " + root.getHeight());
        System.out.println("Width " + root.getWidth());
    }
}