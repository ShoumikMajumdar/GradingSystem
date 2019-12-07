package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseReader extends BaseDBReader{
    public CourseReader(){ initConnection(); }

    // API 1. create Course
    public boolean createCourse(int id, String name){
        /**
         * create a new course
         * @param id   global unique id for courses
         * @param name descriptive name of the course
         */
        try{
            String sql = "INSERT IGNORE " +
                         "INTO course_table " +
                         "(course_id, course_name) "+
                         "VALUES (?, ?)";
                         //"ON DUPLICATE KEY UPDATE " +
                         //"id = VALUES(id), " +
                         //"name = VALUES(name)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.execute();
            return true;
        } catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    // API 2. deleteCourse
    public void deleteCourse(int course_id){
        /**
         * delete a course
         * @param id global unique id for courses
         */
        try {
            String sql = "DELETE " +
                         "FROM course_table " +
                         "WHERE course_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, course_id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // API 3. queryCourses
    public ArrayList<Integer> queryCourses(){
        /**
         * query all courses
         * @return id of courses
         */
        ArrayList<Integer> courses_id = new ArrayList<Integer>();
        try {
            String sql = "SELECT DISTINCT course_id FROM course_table";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                courses_id.add( rs.getInt(1) );
            }
            return courses_id;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses_id;
    }





}
