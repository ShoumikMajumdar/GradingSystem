package db;
import Course.CourseDB;
import db.CourseReader;
import db.ScoresReader;

import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class TemplateReader extends BaseDBReader {
    public TemplateReader(){ initConnection(); }

    // API 1. create Template
    public boolean createTemplate(int template_id, String template_name, int root_id){
        /**
         * cereate a new template
         * @param tid template id
         * @param name template name
         * @param rid root component id
         */
        try{
            int special_value = -1 ;
            String sql = "INSERT IGNORE " +
                    "INTO template_table " +
                    "(course_id, template_id, template_name, root_id ) "+
                    "VALUES (?, ?, ?, ?)";
                    //"ON DUPLICATE KEY UPDATE " +
                    //"course_id = VALUES(course_id), " +
                    //"template_id = VALUES(template_id)," +
                    //"template_name = VALUES(template_name),"+
                    //"root_id = VALUES(root_id)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, special_value);
            stmt.setInt(2, template_id);
            stmt.setString(3, template_name);
            stmt.setInt(4, root_id);
            stmt.execute();
            return true;
        } catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    // API 2. adapt Template
    public boolean adaptTemplate(int course_id, int template_id, String template_name, int root_id){
        /**
         * adapt a grading template for a course
         * @param cid course id
         * @param tid template id
         * For a template that has not beed assigned to a course and root_component id
         * assign -1 to those two fields
         * repeatedly call for different unqiue course id
         */
        try{
            String sql = "INSERT " +
                    "INTO template_table " +
                    "(course_id, template_id, template_name, root_id) "+
                    "VALUES (?, ?, ?, ?)" +
                    "ON DUPLICATE KEY UPDATE " +
                    "course_id = VALUES(course_id), " +
                    "template_id = VALUES(template_id)," +
                    "template_name = VALUES(template_name), " +
                    "root_id = VALUES(root_id) ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, course_id);
            stmt.setInt(2, template_id);
            stmt.setString(3, template_name);
            stmt.setInt(4, root_id);
            stmt.execute();
            return true;
        } catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    // API 3.  delete Template
    public void deleteTemplate(int template_id){
        /**
         * delete an existing template
         * @param id template id
         */
        try {
            String sql = "DELETE " +
                    "FROM template_table " +
                    "WHERE template_id = ? ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, template_id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // API 4. delete all component

    public void deleteAllComponent(int component_id){
        /**
         * delete all component information , specifically in Component id and scores Table
         * @param component_id
         */
        try {
            String sql = "DELETE " +
                    "FROM template_table " +
                    "WHERE root_id = ? ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, component_id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ComponentReader cd = new ComponentReader();
        ScoresReader sd = new ScoresReader();
        cd.deleteComponent(component_id);
        sd.deleteComponent(component_id);

    }


    // API 5. queryCoursesUsingTemplate
    public ArrayList<Integer> queryCoursesUsingTemplate(int template_id){
        /**
         * query courses using a certain template
         * @param tid
         * @return id of courses which is using this template
         */
        ArrayList<Integer> course_id = new ArrayList<Integer>();
        try {
            String sql = "SELECT DISTINCT course_id FROM template_table WHERE template_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, template_id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                course_id.add( rs.getInt(1) );
            }
            return course_id;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course_id;
    }

    // API 6. queryCourse

    public CourseDB queryCourse(int course_id){
        CourseDB.Builder builder = new CourseDB.Builder();
        int courseID = -1;
        String courseName = "None";
        int templateId = -1 ;
        try {
            String sql = "SELECT DISTINCT t.course_id, t.template_id, c.course_name "+
                         "FROM template_table AS t "+
                         "INNER JOIN 591database.course_table AS c " + " ON t.course_id = c.course_id " +
                         "WHERE t.course_id = ? ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, course_id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                course_id = rs.getInt(1);
                templateId = rs.getInt(2);
                courseName = rs.getString(3);

            }
            builder.setCourseId(course_id);
            builder.setName(courseName);
            builder.setTemplateId(templateId);

            return builder.build();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Student does not exist");
        builder.setCourseId(course_id);
        builder.setName(courseName);
        builder.setTemplateId(templateId);

        return builder.build();

    }





}
