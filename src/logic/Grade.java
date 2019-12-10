package logic;

public class Grade extends Cell {
    public int points;

    public static Grade build(int crsid, int sid, int cmpid, int points) {
        return new Grade(crsid, sid, cmpid, points);
    }

    protected Grade(int crsid, int sid, int cmpid, int points) {
        super(crsid, sid, cmpid);
        this.points = points;
    }
}