package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InfoReader extends BaseDBReader {
    public InfoReader(){ initConnection();}


    // insert info
    public void setData(String key, int data){
        try{
            String sql = "INSERT " +
                         "INTO info_table" +
                         "(key_,value_) " +
                         "VALUES(?, ?) " +
                         "ON DUPLICATE KEY UPDATE " +
                         "key_ = VALUES(key_), " +
                         "value_ = VALUES(value_)" ;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, key);
            stmt.setInt(2, data);
            stmt.execute();

        } catch (Exception e){
            e.printStackTrace();
        }


    }


    // get info
    public int getData(String key){
        int result = -1 ;
        try {
            String sql = "SELECT * FROM info_table WHERE key_ = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, key);
            ResultSet rs = stmt.executeQuery();
            // concatenate all the strings
            while (rs.next()){
                result = rs.getInt("value_");
            }

            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
