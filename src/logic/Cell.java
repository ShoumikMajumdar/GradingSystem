package logic;

abstract public class Cell {
    public int courseID;
    public int studentID;
    public int componentID;

    protected Cell(int crsid, int sid, int cmpid) {
        this.courseID = crsid;
        this.studentID = sid;
        this.componentID = cmpid;
    }
}
