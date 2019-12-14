package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CurveReader extends  BaseDBReader {

    public CurveReader (){initConnection();}


    // insert info
    public void setCurve(int session_id, int points){
        try{
            String sql = "INSERT " +
                    "INTO curve_table" +
                    "(session_id,points) " +
                    "VALUES(?, ?) " +
                    "ON DUPLICATE KEY UPDATE " +
                    "session_id = VALUES(session_id), " +
                    "points = VALUES(points)" ;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,session_id);
            stmt.setInt(2, points);
            stmt.execute();

        } catch (Exception e){
            e.printStackTrace();
        }

    }



    // get curve
    public int getCurve(int session_id){
        int result = -1 ;
        try {
            String sql = "SELECT * FROM curve_table WHERE session_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, session_id);
            ResultSet rs = stmt.executeQuery();
            // concatenate all the strings
            while (rs.next()){
                result = rs.getInt("points");
            }

            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
