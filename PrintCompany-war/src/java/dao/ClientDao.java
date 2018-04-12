package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Model.Client;

public class ClientDao {
    private String url;
    private String userDB;
    private String passDB;
    
    public ClientDao(){
    }
    
    public ClientDao(String url, String userDB, String passDB){
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
    
    public int addClient(Client userObj){
        int res = 0;
        String sql = "INSERT INTO clients (id, agentid, firstName, lastName, streetNumber, streetName, city, province, postalCode, telOffice, telCell, email, company, companyType) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            Connection conn = getConnection();
            if(conn!=null){
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1,userObj.getId());
                stmt.setInt(2,userObj.getAgentId());
                stmt.setString(3, userObj.getFirstName());
                stmt.setString(4, userObj.getLastName());
                stmt.setString(5, userObj.getStreetNumber());
                stmt.setString(6, userObj.getStreetName());
                stmt.setString(7, userObj.getCity());
                stmt.setString(8, userObj.getProvince());
                stmt.setString(9, userObj.getPostalCode());
                stmt.setString(10, userObj.getTelOffice());
                stmt.setString(11, userObj.getTelCell());
                stmt.setString(12, userObj.getEmail());
                stmt.setString(13, userObj.getCompany());
                stmt.setString(14, userObj.getCompanyType());
                
                res = stmt.executeUpdate();
                conn.close();
            }
            
        } catch (SQLException sqlEx){
            sqlEx.printStackTrace();
        }
        return res;
    }
    
   public ArrayList<Client> viewClients(){
       ArrayList<Client> clientList = new ArrayList();
       String sql = "SELECT * FROM Student";
    int id, AgentId;
    String FirstName, LastName, StreetNumber, StreetName, City, Province, PostalCode, TelOffice, TelCell, Email, Company, CompanyType;
    
       
       try{
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(sql);

        while(resultSet.next()){            
          id = resultSet.getInt("id");
          AgentId = resultSet.getInt("AgentId");
          FirstName = resultSet.getString("FirstName"); 
          LastName = resultSet.getString("LastName"); 
          StreetNumber = resultSet.getString("StreetNumber"); 
          StreetName = resultSet.getString("StreetName"); 
          City = resultSet.getString("City"); 
          Province = resultSet.getString("Province"); 
          PostalCode = resultSet.getString("PostalCode"); 
          TelOffice = resultSet.getString("TelOffice");  
          TelCell = resultSet.getString("TelCell"); 
          Email = resultSet.getString("Email"); 
          Company = resultSet.getString("Company"); 
          CompanyType = resultSet.getString("CompanyType"); 
            
            Client userObj = new Client();
            
            userObj.setId(id);
            userObj.setAgentId(AgentId);
            userObj.setFirstName(FirstName);
            userObj.setLastName(LastName);
            userObj.setStreetNumber(StreetNumber);
            userObj.setStreetName(StreetName);
            userObj.setCity(City);
            userObj.setProvince(Province);
            userObj.setPostalCode(PostalCode);
            userObj.setTelOffice(TelOffice);
            userObj.setTelCell(TelCell);
            userObj.setEmail(Email);
            userObj.setCompany(Company);
            userObj.setCompanyType(CompanyType);
            clientList.add(userObj);
        }
       resultSet.close();
       stmt.close();
        if(conn!=null && !conn.isClosed()){
         conn.close();
        }
       } catch(SQLException sqlEx){
           sqlEx.printStackTrace();
       }
       return clientList;
   }
   
   public Client showClient(int id) throws SQLException{
       Client userObj = null;
       String sql = "SELECT * FROM clients WHERE id = ?";
       
       Connection con = getConnection();
       PreparedStatement statement = con.prepareStatement(sql);
       statement.setInt(1, id);
       ResultSet result = statement.executeQuery();
       
       while(result.next()){           
           userObj.setId(result.getInt("id"));
            userObj.setAgentId(result.getInt("AgentId"));
            userObj.setFirstName(result.getString("FirstName"));
            userObj.setLastName(result.getString("LastName"));
            userObj.setStreetNumber(result.getString("StreetNumber"));
            userObj.setStreetName(result.getString("StreetName"));
            userObj.setCity(result.getString("City"));
            userObj.setProvince(result.getString("Province"));
            userObj.setPostalCode(result.getString("PostalCode"));
            userObj.setTelOffice(result.getString("TelOffice"));
            userObj.setTelCell(result.getString("TelCell"));
            userObj.setEmail(result.getString("Email"));
            userObj.setCompany(result.getString("Company"));
            userObj.setCompanyType(result.getString("CompanyType"));
       }
       return userObj;
   }
   
   public boolean updateClient(Client userObj) throws SQLException{
       boolean res;
       String sql = "UPDATE Student SET clients agentid = ?, firstName = ?, lastName = ?, streetNumber = ?, streetName = ?, city = ?, province = ?, postalCode = ?, telOffice = ?, telCell = ?, email = ?, company = ?, companyType = ? WHERE id = ?";
       
       Connection con = getConnection();
       PreparedStatement stmt = con.prepareStatement(sql);
       
                
                stmt.setInt(1,userObj.getAgentId());
                stmt.setString(2, userObj.getFirstName());
                stmt.setString(3, userObj.getLastName());
                stmt.setString(4, userObj.getStreetNumber());
                stmt.setString(5, userObj.getStreetName());
                stmt.setString(6, userObj.getCity());
                stmt.setString(7, userObj.getProvince());
                stmt.setString(8, userObj.getPostalCode());
                stmt.setString(9, userObj.getTelOffice());
                stmt.setString(10, userObj.getTelCell());
                stmt.setString(11, userObj.getEmail());
                stmt.setString(12, userObj.getCompany());
                stmt.setString(13, userObj.getCompanyType());
                stmt.setInt(14,userObj.getId());
                
                res = stmt.executeUpdate() > 0;
       
                return res;
   }
   
   public void deleteClient(int id) throws SQLException{
       String sql = "DELETE from clients WHERE id = ?";
       Connection con = getConnection();
       PreparedStatement stmnt = con.prepareStatement(sql);
       
       stmnt.setInt(1, id);
       
       stmnt.executeUpdate();
   }
}
