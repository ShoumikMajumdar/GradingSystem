package db;

import db.MySQLDBUtil;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
public class MySQLTableCreation {
    // Run this as Java application to reset db schema.
    public static void main(String[] args) {
        try {
            // This is java.sql.Connection. Not com.mysql.jdbc.Connection.
            Connection conn = null;

            // Step 1 Connect to MySQL.
            try {
                System.out.println("Connecting to " + MySQLDBUtil.URL);
                Class.forName("com.mysql.jdbc.Driver").getConstructor().newInstance();
                conn = DriverManager.getConnection(MySQLDBUtil.URL);
            } catch (SQLException e) {
                e.printStackTrace();
            }


            if (conn == null) {
                return;
            }


            // Step 2 Drop tables in case they exist.
            Statement stmt = conn.createStatement();

            String sql = "DROP TABLE IF EXISTS course_table";
            stmt.executeUpdate(sql);

            sql = "DROP TABLE IF EXISTS section_table";
            stmt.executeUpdate(sql);

            sql = "DROP TABLE IF EXISTS student_table";
            stmt.executeUpdate(sql);

            sql = "DROP TABLE IF EXISTS template_table";
            stmt.executeUpdate(sql);

            sql = "DROP TABLE IF EXISTS component_table";
            stmt.executeUpdate(sql);

            sql = "DROP TABLE IF EXISTS scores_table";
            stmt.executeUpdate(sql);

            sql = "DROP TABLE IF EXISTS bonus_table";
            stmt.executeUpdate(sql);

            sql = "DROP TABLE IF EXISTS comment_table";
            stmt.executeUpdate(sql);


            // Step 3 Create new tables

            // course table
            sql = "CREATE TABLE course_table ("
                    + "course_id INTEGER,"
                    + "course_name VARCHAR(255), "
                    + "PRIMARY KEY (course_id, course_name) )";
            stmt.executeUpdate(sql);

            // section_table
            sql = "CREATE TABLE section_table ("
                    + "course_id INTEGER,"
                    + "section_id INTEGER, "
                    + "PRIMARY KEY (course_id, section_id) )";
            stmt.executeUpdate(sql);

            // student table
            sql = "CREATE TABLE student_table ("
                    + "student_id INTEGER,"
                    + "student_name varchar(255),"
                    + "section_id INTEGER, "
                    + "PRIMARY KEY (student_id,student_name, section_id) )";
            stmt.executeUpdate(sql);

            // template table
            sql = "CREATE TABLE template_table ("
                    + "course_id INTEGER,"
                    + "template_id INTEGER,"
                    + "template_name varchar(255), "
                    + "root_id INTEGER, "
                    //+ "PRIMARY KEY (template_id, template_name, root_id, course_id) )";
                    //+ "PRIMARY KEY (course_id, template_id, template_name, root_id) )";
                    + "PRIMARY KEY (course_id, template_id) )";
            stmt.executeUpdate(sql);

            // component table
            sql = "CREATE TABLE component_table ("
                    + "component_id INTEGER,"
                    + "component_name varchar(255),"
                    + "percent DOUBLE, "
                    + "points DOUBLE, "
                    + "parent INTEGER, "
                    + "PRIMARY KEY (component_id, component_name) )";
            stmt.executeUpdate(sql);

            // scores table
            sql = "CREATE TABLE scores_table ("
                    + "course_id INTEGER,"
                    + "student_id INTEGER ,"
                    + "component_id INTEGER , "
                    + "points DOUBLE, "
                    + "PRIMARY KEY (course_id, student_id, component_id) )";
            stmt.executeUpdate(sql);

            // bonus table
            sql = "CREATE TABLE bonus_table ("
                    + "course_id INTEGER,"
                    + "student_id INTEGER ,"
                    + "component_id INTEGER , "
                    + "bonus DOUBLE, "
                    + "PRIMARY KEY (course_id, student_id, component_id) )";
            stmt.executeUpdate(sql);


            // comment table
            sql = "CREATE TABLE comment_table ("
                    + "course_id INTEGER,"
                    + "student_id INTEGER ,"
                    + "component_id INTEGER , "
                    + "comment varchar(255), "
                    + "PRIMARY KEY (course_id, student_id, component_id) )";
            stmt.executeUpdate(sql);

            System.out.println("Import is done successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
