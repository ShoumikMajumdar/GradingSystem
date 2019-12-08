package db;

import Component.ComponentDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ComponentReader extends BaseDBReader {
    public ComponentReader(){ initConnection(); }

    // API 1. create Component
    public boolean createComponent(int component_id, String component_name, double percent, double points ) {
        /**
         * create a new component object
         * @param id component id
         * @param name component name
         * @param percent percentage the component takes in the same level
         * @param points points this component has
         * root_id is initialized to be -1
         */
        try {
            int special_value = -1;
            String sql = "INSERT IGNORE " +
                    "INTO component_table " +
                    "(component_id, component_name, percent, points, parent ) " +
                    "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, component_id);
            stmt.setString(2, component_name);
            stmt.setDouble(3, percent);
            stmt.setDouble(4, points);
            stmt.setInt(5, special_value);
            stmt.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // API 2. add a child
    public boolean addChild(int parent_id, int child_id){
        /**
         * add a component as a child of another component
         * @param pid parent component id
         * @param cid child component id
         * for a new child that does not have name, percent, points , assign -1 for those 3 fields
         */
        try {
            String sql = "UPDATE component_table " +
                    "SET  parent  = ? " +
                    "WHERE component_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, parent_id);
            stmt.setInt(2, child_id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // API 3. delete child

    public boolean deleteChild(int parent_id, int child_id){
        /**
         * delete a child component from a parent component
         * which also means the child component should be
         * delete from the database
         * @param pid parent component id
         * @param cid child component id
         */
        try {
            String sql = "DELETE " +
                    "FROM component_table " +
                    "WHERE parent = ? AND component_id = ? ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, parent_id);
            stmt.setInt(2, child_id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // API 4. queryChildren

    public ArrayList<Integer> queryChildren(int parent_id){
        /**
         * return all componenets that are children of given component id
         * @param pid parent component id
         */
        ArrayList<Integer> children_id = new ArrayList<Integer>();
        try {
            String sql = "SELECT DISTINCT component_id FROM component_table WHERE parent = ? ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, parent_id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                children_id.add( rs.getInt(1) );
            }
            return children_id;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return children_id;
    }

    // API 5. delete a component

    public boolean deleteComponent(int component_id){
        /**
         * delete a component
         * @param component_id
         */
        try {
            String sql = "DELETE " +
                    "FROM component_table " +
                    "WHERE component_id = ? ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, component_id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // API 6. queryComponent

    public ComponentDB queryComponent(int component_id){
        ComponentDB.Builder builder = new ComponentDB.Builder();
        int componentID = -1;
        String componentName = "None";
        double percent = -1;
        double points = -1 ;
        try {
            String sql = "SELECT DISTINCT t.component_id, t.component_name, t.percent, t.points "+
                    "FROM component_table AS t " + "WHERE t.component_id = ? ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, component_id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                componentID = rs.getInt(1);
                componentName = rs.getString(2);
                percent = rs.getInt(3);
                points = rs.getInt(4);


            }
            builder.setComponentId(componentID);
            builder.setComponentNmae(componentName);
            builder.setPercent(percent);
            builder.setPoints(points);

            return builder.build();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Component does not exist");
        builder.setComponentId(componentID);
        builder.setComponentNmae(componentName);
        builder.setPercent(percent);
        builder.setPoints(points);

        return builder.build();

    }




}
