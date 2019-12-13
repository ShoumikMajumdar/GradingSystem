package logic;

public class Comment extends Cell {
    public String comment;

    public static Comment build(int crsid, int sid, int cmpid, String comment) {
        return new Comment(crsid, sid, cmpid, comment);
    }

    public static void create(int crsid, int sid, int cmpid, String comment) {
        GradingSystem.commentRd.addComment(crsid, sid, cmpid, comment);
    }

    protected Comment(int crsid, int sid, int cmpid, String comment) {
        super(crsid, sid, cmpid);
        this.comment = comment;
    }
}