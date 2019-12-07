package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BonusReader extends BaseDBReader {
    public BonusReader(){ initConnection(); }

    public boolean addBonus(int course_id, int student_id, int component_id, double bonus ){
        /**
         * add a new bonus for a student
         * @param course_id course id
         * @param student_id section id
         * @param component_id
         * @param points
         */
        try{
            String sql = "INSERT " +
                    "INTO bonus_table " +
                    "(course_id, student_id, component_id, bonus) "+
                    "VALUES (?, ?, ?, ?)" +
                    "ON DUPLICATE KEY UPDATE " +
                    "course_id = VALUES(course_id), " +
                    "student_id = VALUES(student_id), " +
                    "component_id = VALUES(component_id)," +
                    "bonus = VALUES(bonus)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, course_id);
            stmt.setInt(2, student_id);
            stmt.setInt(3, component_id);
            stmt.setDouble(4, bonus);
            stmt.execute();
        } catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    // API 2. delete a score
    public void deleteScore(int student_id, int course_id, int component_id ){
        /**
         * delete a bonus score for a student
         * @param student_id
         * @param course_id
         * @param component_id
         */
        try {
            String sql = "DELETE " +
                    "FROM bonus_table " +
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
                    "FROM bonus_table " +
                    "WHERE component_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, component_id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // API 4 get a Bonus
    public double getBonus(int course_id, int student_id, int component_id){
        double score = 0.0;
        try {
            String sql = "SELECT bonus FROM bonus_table WHERE course_id = ? AND student_id = ? AND component_id = ? ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, course_id);
            stmt.setInt(2, student_id);
            stmt.setInt(3, component_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                score = rs.getDouble(1);
            }
            return score;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return score;

    }
}
