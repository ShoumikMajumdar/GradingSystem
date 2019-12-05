package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SectionReader extends BaseDBReader {
    public SectionReader(){ initConnection(); }

    // API 1. create Section
    public boolean createSection(int section_id){
        /**
         * create a new section object
         * @param id section id
         * for section that does not have a course , assign -1
         */
        try{
            int special_value = -1 ;
            String sql = "INSERT IGNORE " +
                    "INTO section_table " +
                    "(course_id, section_id ) "+
                    "VALUES (?, ?)";
                    //"ON DUPLICATE KEY UPDATE " +
                    //"course_id = VALUES(course_id), " +
                    //"section_id = VALUES(section_id)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, special_value);
            stmt.setInt(2, section_id);
            stmt.execute();
            return true;
        } catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }


    // API 2. add a new section
    public boolean addNewSection(int course_id, int section_id){
        /**
         * add a new section to a course
         * @param cid course id
         * @param sid section id
         */
        try{
            String sql = "INSERT " +
                    "INTO section_table " +
                    "(course_id, section_id) "+
                    "VALUES (?, ?)" +
                    "ON DUPLICATE KEY UPDATE " +
                    "course_id = VALUES(course_id), " +
                    "section_id = VALUES(section_id)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, course_id);
            stmt.setInt(2, section_id);
            stmt.execute();
        } catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    // API 3. delete a section
    public void deleteSection(int section_id){
        /**
         * delete a section from a course
         * @param sid section id
         */
        try {
            String sql = "DELETE " +
                    "FROM section_table " +
                    "WHERE section_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, section_id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // API 4. querySections
    public ArrayList<Integer> querySections(int course_id){
        /**
         * query all sections
         * @return id of all sections
         */
        ArrayList<Integer> sections_id = new ArrayList<Integer>();
        try {
            String sql = "SELECT DISTINCT section_id FROM section_table WHERE course_id = ? ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, course_id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                sections_id.add( rs.getInt(1) );
            }
            return sections_id;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sections_id;
    }

    // API 5.



}
