package db;

import Student.StudentDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentReader extends BaseDBReader{
    public StudentReader(){ initConnection(); }

    // API 1. create Student
    public boolean createStudent(int student_id, String student_name){
        /**
         * create a new student
         * @param id   student id
         * @param name student name
         * For a new student that are not enrolled in any course yet
         * assign section_id to be -1
         */
        try{
            int special_value = -1 ;
            String sql = "INSERT IGNORE " +
                    "INTO student_table " +
                    "(student_id, student_name, section_id ) "+
                    "VALUES (?, ?, ?)";
                    //"ON DUPLICATE KEY UPDATE " +
                    //"student_id = VALUES(student_id), " +
                    //"student_name = VALUES(student_name)," +
                    //"section_id = VALUES(section_id)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, student_id);
            stmt.setString(2, student_name);
            stmt.setInt(3, special_value);
            stmt.execute();
        } catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    // API 2. add a new student
    public boolean addNewStudent(int student_id, int section_id, String student_name){
        /**
         * add a new student to a section
         * @param scid section id
         * @param stid student id
         * @param sname student name
         */
        try{
            String sql = "INSERT " +
                    "INTO student_table " +
                    "(student_id, student_name, section_id) "+
                    "VALUES (?, ?, ?)" +
                    "ON DUPLICATE KEY UPDATE " +
                    "student_id = VALUES(student_id), " +
                    "student_name = VALUES(student_name)," +
                    "section_id = VALUES(section_id)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, student_id);
            stmt.setString(2, student_name);
            stmt.setInt(3, section_id);
            stmt.execute();
            return true;
        } catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    // API 3. delete a student
    public void deleteStudent(int student_id, int section_id){
        /**
         * delete a student from a section
         * @param scid section id
         * @param stid student id
         */
        try {
            String sql = "DELETE " +
                    "FROM student_table " +
                    "WHERE student_id = ? AND section_id = ? ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, student_id);
            stmt.setInt(2, section_id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // API 4. query students
    public ArrayList<Integer> queryStudents(int section_id ){
        /**
         * query all students of a section
         * @param id section id
         * @return id of students
         */
        ArrayList<Integer> students_id = new ArrayList<Integer>();
        try {
            String sql = "SELECT DISTINCT student_id FROM student_table WHERE section_id = ? ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, section_id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                students_id.add( rs.getInt(1) );
            }
            return students_id;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students_id;
    }

    // API 5. return a student object

    public StudentDB queryStudent(int student_id){
        StudentDB.Builder builder = new StudentDB.Builder();
        int studentId = -1;
        String studentName = "None";
        try {
            String sql = "SELECT DISTINCT student_id, student_name  FROM student_table WHERE student_id = ? ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, student_id);
            ResultSet rs = stmt.executeQuery();


            while (rs.next()){
                studentId = rs.getInt(1);
                studentName = rs.getString(2);

            }
            builder.setId(studentId);
            builder.setName(studentName);
            StudentDB returnStudent = builder.build();
            return returnStudent;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Student does not exist");
        builder.setId(studentId);
        builder.setName(studentName);
        return builder.build();

    }










}
