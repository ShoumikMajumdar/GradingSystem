package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentReader extends BaseDBReader {
    public CommentReader(){ initConnection(); }

    public boolean addComment(int course_id, int student_id, int component_id, String comment ){
        /**
         * add a new bonus for a student
         * @param course_id course id
         * @param student_id section id
         * @param component_id
         * @param comment
         */
        try{
            String sql = "INSERT " +
                    "INTO comment_table " +
                    "(course_id, student_id, component_id, comment) "+
                    "VALUES (?, ?, ?, ?)" +
                    "ON DUPLICATE KEY UPDATE " +
                    "course_id = VALUES(course_id), " +
                    "student_id = VALUES(student_id), " +
                    "component_id = VALUES(component_id)," +
                    "comment = VALUES(comment)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, course_id);
            stmt.setInt(2, student_id);
            stmt.setInt(3, component_id);
            stmt.setString(4, comment);
            stmt.execute();
        } catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    // API 2. delete a comment
    public void deleteComment(int student_id, int course_id, int component_id ){
        /**
         * delete a bonus score for a student
         * @param student_id
         * @param course_id
         * @param component_id
         */
        try {
            String sql = "DELETE " +
                    "FROM comment_table " +
                    "WHERE student_id = ? AND course_id = ? AND component_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, student_id);
            stmt.setInt(2, course_id);
            stmt.setInt(3, component_id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // API 3. delete a component
    public void deleteComponent(int component_id ){
        /**
         * delete a component
         * @param component_id
         */
        try {
            String sql = "DELETE " +
                    "FROM comment_table " +
                    "WHERE component_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, component_id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // API 4 get a comment
    public String getComment(int course_id, int student_id, int component_id){
        String comment  = "None";
        try {
            String sql = "SELECT comment FROM comment_table WHERE course_id = ? AND student_id = ? AND component_id = ? ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, course_id);
            stmt.setInt(2, student_id);
            stmt.setInt(3, component_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                comment = rs.getString(1);
            }
            return comment;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comment;

    }
}
