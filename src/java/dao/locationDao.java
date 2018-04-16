/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Model.Location;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Zach
 */
public class locationDao {
     private String url ="jdbc:mysql://localhost:3306/printmarketing";
    private String userDB="root";
    private String passDB="";
    
    public locationDao(){
        
    }
        public locationDao(String url, String userDB, String passDB){
        this.url = url;
        this.userDB = userDB;
        this.passDB = passDB;
    }
    protected Connection getConnection(){
       Connection conn = null;
       try{
           Class.forName("com.mysql.jdbc.Driver");
           if(conn==null || conn.isClosed()){
                conn = DriverManager.getConnection(url, userDB, passDB);
           }
       } catch(SQLException sqlEx){
           sqlEx.printStackTrace();
       } catch(ClassNotFoundException ex){
           ex.printStackTrace();
       }
       return conn;
   }
    
    public int addLocation(Location loc){
        int res = 0;
         String sql = "INSERT INTO `location` (`id`, `locationName`, `distributionCapacity`) VALUES (NULL, ?, ?);";
        try{
            Connection conn = getConnection();
            if(conn!=null){
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, loc.getLocationName());
                stmt.setInt(2, loc.getDistrCap());
                
                res = stmt.executeUpdate();
                return res;
                          }
            }catch (SQLException sqlEx){
            sqlEx.printStackTrace();
        }
        return res;
    }
       public boolean updateLocation(Location loc) throws SQLException{
        boolean res;
         String sql = "UPDATE `location` SET `locationName` = ?, `distributionCapacity` = ? WHERE `location`.`id` = ?;";
            Connection conn = getConnection();
            
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, loc.getLocationName());
                stmt.setInt(2, loc.getDistrCap());
                stmt.setInt(3, loc.getId());
                
                res = stmt.executeUpdate() > 0;
 
        return res;
    }
          public void deleteLocation(int id){
            String sql = "DELETE from location WHERE id = ?;";
        try{
            Connection conn = getConnection();
            if(conn!=null){
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, id);
                stmt.executeUpdate();
                          }
            }catch (SQLException sqlEx){
            sqlEx.printStackTrace();
        }
    }
             public Location viewLocation(int id) throws SQLException{
                 Location loc = null;
        
         String sql = "Select * from location WHERE id = ?;";
            Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, id);
                ResultSet res = stmt.executeQuery();
                
                while(res.next()){
                    loc = new Location();
                    loc.setId(res.getInt("id"));
                    loc.setLocationName(res.getString("locationName"));
                    loc.setDistrCap(res.getInt("distributionCapacity"));
                }
                return loc;
    }
             public ArrayList<Location> viewAllLocations() throws SQLException{
                ArrayList<Location> locList = new ArrayList();

                String sql = "SELECT * FROM location;";
                int id=0, dstrCap=0;
                String name ="";
                try{
                Connection conn = getConnection();
                 try (Statement stmt = conn.createStatement();
                         ResultSet res = stmt.executeQuery(sql)) {
        
        while(res.next()){
            id = res.getInt("id");
            name = res.getString("locationName");
            dstrCap = res.getInt("distributionCapacity");
            
            Location loc = new Location();
            
            loc.setId(id);
            loc.setLocationName(name);
            loc.setDistrCap(dstrCap);
            locList.add(loc);
        }
    }
       if(conn!=null && !conn.isClosed()){
         conn.close();
        }
       } catch(SQLException sqlEx){
           sqlEx.printStackTrace();
       }
       return locList;
   }
}