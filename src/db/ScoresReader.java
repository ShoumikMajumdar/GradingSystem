package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScoresReader extends BaseDBReader {
    public ScoresReader(){ initConnection(); }

    public boolean addScore(int course_id, int student_id, int component_id, double points ){
        /**
         * add a new score for a student
         * @param course_id course id
         * @param student_id section id
         * @param component_id
         * @param points
         */
        try{
            String sql = "INSERT " +
                    "INTO scores_table " +
                    "(course_id, student_id, component_id, points) "+
                    "VALUES (?, ?, ?, ?)" +
                    "ON DUPLICATE KEY UPDATE " +
                    "course_id = VALUES(course_id), " +
                    "student_id = VALUES(student_id), " +
                    "component_id = VALUES(component_id)," +
                    "points = VALUES(points)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, course_id);
            stmt.setInt(2, student_id);
            stmt.setInt(3, component_id);
            stmt.setDouble(4, points);
            stmt.execute();
        } catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    // API 2. delete a score
    public void deleteScore(int student_id, int course_id, int component_id ){
        /**
         * delete a score for a student
         * @param student_id
         * @param course_id
         * @param component_id
         */
        try {
            String sql = "DELETE " +
                    "FROM scores_table " +
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
                    "FROM scores_table " +
                    "WHERE component_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, component_id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }





}
