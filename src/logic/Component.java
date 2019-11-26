package logic;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class Component {
    private static int nextID = 0;

    public static Component createComponent(String name, int points, double percent) {
        Component c = new Component(nextID, name, points, percent);
        ++nextID;
        return c;
    }

    private int id;

    // how much percent this component takes among its siblings
    private double percent;

    // name of this component
    private String name;

    // how many points this component is assigned
    private int points;

    // children of this component
    private LinkedHashMap<Integer, Component> children = new LinkedHashMap<Integer, Component>();

    private Component(int id, String name, int points, double percent) {
        this.id = id;
        this.name = name;
        this.points = points;
        this.percent = percent;
    }

    private Component() {
        this(0, "", 0, 0);
    }

    public double getPercen() { return percent; }

    public void setPercent(double percent) { this.percent = percent; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getID() { return id; }

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

    public void setPoints(int points) { this.points = points; }

    public void addChild(Component child) {
        children.put(Integer.valueOf(child.getID()), child);
    }

    public void removeChild(Component child) {
        children.remove(Integer.valueOf(child.getID()));
    }

    public String toString() {
        String s = ("Name: " + name + " Points: " + points + " Percent: " + percent);
        return s;
    }

    public static void printRoot(Component root) {
        System.out.println(root);
        for (Entry<Integer, Component> e : root.children.entrySet()) {
            printRoot(e.getValue());
        }
    }

    public static void main(String[] args) {
        System.out.println("Testing Components...");

        Component root = Component.createComponent("CS591", 0, 1.0);

        Component participation = Component.createComponent("Participation", 0, 0.05);
        root.addChild(participation);

        Component assignments = Component.createComponent("Independent Assignments", 0, 0.25);
        root.addChild(assignments);

        Component midtermExam = Component.createComponent("Midterm Exam", 0, 0.35);
        root.addChild(midtermExam);

        Component finalExam = Component.createComponent("Final Exam", 0, 0.15);
        root.addChild(finalExam);

        Component finalProject = Component.createComponent("Final Project", 0, 0.20);
        root.addChild(finalProject);

        Component tictactoe = Component.createComponent("Tic Tac Toe", 0, 0.2);
        Component ttt1 = Component.createComponent("Tic Tac Toe V1", 50, 0.5);
        Component ttt2 = Component.createComponent("Tic Tac Toe V2", 50, 0.5);
        tictactoe.addChild(ttt1);
        tictactoe.addChild(ttt2);
        assignments.addChild(tictactoe);

        Component cardGame = Component.createComponent("Card Game", 0, 0.2);
        Component bj = Component.createComponent("Black Jack", 100, 0.5);
        Component te = Component.createComponent("Trianta Ena", 100, 0.5);
        cardGame.addChild(bj);
        cardGame.addChild(te);
        assignments.addChild(cardGame);

        Component caveAdventure = Component.createComponent("Cave Adeventure", 100, 0.1);
        assignments.addChild(caveAdventure);

        Component midtermWritten = Component.createComponent("Midterm Written", 0, 0.6);
        Component midtermPracticum = Component.createComponent("Midterm Practicum", 0, 0.4);
        midtermExam.addChild(midtermWritten);
        midtermExam.addChild(midtermPracticum);

        Component.printRoot(root);
    }
}