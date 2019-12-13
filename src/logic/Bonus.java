package logic;

public class Bonus extends Cell {
    public int points;

    public static Bonus build(int crsid, int sid, int cmpid, int points) {
        return new Bonus(crsid, sid, cmpid, points);
    }

    protected Bonus(int crsid, int sid, int cmpid, int points) {
        super(crsid, sid, cmpid);
        this.points = points;
    }
}