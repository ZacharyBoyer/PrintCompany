package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Model.Employee;

public class EmployeeDao {
    private String url;
    private String userDB;
    private String passDB;
    
    public EmployeeDao(){
    }
    
    public EmployeeDao(String url, String userDB, String passDB){
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
    
    public int addEmployee(Employee userObj){
        int res = 0;
        String sql = "INSERT INTO employees (id,FirstName, LastName, Email, UserName, Password, isAdmin) VALUES (?,?,?,?,?,?,?)";
        try{
            Connection conn = getConnection();
            if(conn!=null){
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1,userObj.getId());
                stmt.setString(2,userObj.getFirstName());
                stmt.setString(3, userObj.getLastName());
                stmt.setString(4, userObj.getEmail());
                stmt.setString(5, userObj.getUserName());
                stmt.setString(6, userObj.getPassword());
                stmt.setBoolean(7, userObj.getIsAdmin());
                
                res = stmt.executeUpdate();
                conn.close();
            }
            
        } catch (SQLException sqlEx){
            sqlEx.printStackTrace();
        }
        return res;
    }
    
   public ArrayList<Employee> viewEmployees(){
       ArrayList<Employee> employeeList = new ArrayList();
       String sql = "SELECT * FROM Student";
    int id;
    String FirstName, LastName, Email, UserName, Password;
    Boolean isAdmin;
       
       try{
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(sql);

        while(resultSet.next()){            
          id = resultSet.getInt("id");
          FirstName = resultSet.getString("FirstName"); 
          LastName = resultSet.getString("LastName"); 
          Email = resultSet.getString("Email"); 
          UserName = resultSet.getString("UserName"); 
          Password = resultSet.getString("Password"); 
          isAdmin = resultSet.getBoolean("isAdmin"); 
            
            Employee userObj = new Employee();
            
            userObj.setId(id);
            userObj.setFirstName(FirstName);
            userObj.setLastName(LastName);
            userObj.setEmail(Email);
            userObj.setUserName(UserName);
            userObj.setPassword(Password);
            userObj.setIsAdmin(isAdmin);
       
            employeeList.add(userObj);
        }
       resultSet.close();
       stmt.close();
        if(conn!=null && !conn.isClosed()){
         conn.close();
        }
       } catch(SQLException sqlEx){
           sqlEx.printStackTrace();
       }
       return employeeList;
   }
   
   public Employee showEmployee(int id) throws SQLException{
       Employee userObj = null;
       String sql = "SELECT * FROM employees ";
       sql += "WHERE id = ?";
       
       Connection con = getConnection();
       PreparedStatement statement = con.prepareStatement(sql);
       statement.setInt(1, id);
       ResultSet result = statement.executeQuery();
       
       while(result.next()){           
           userObj.setId(result.getInt("id"));
           userObj.setFirstName(result.getString("FirstName"));
           userObj.setLastName(result.getString("LastName"));
           userObj.setEmail(result.getString("Email"));
           userObj.setUserName(result.getString("UserName"));
           userObj.setPassword(result.getString("Password"));
           userObj.setIsAdmin(result.getBoolean("isAdmin"));
       }
       return userObj;
   }
   
   public boolean updateEmployee(Employee userObj) throws SQLException{
       boolean res;
       String sql = "UPDATE Student SET employees FirstName = ?, LastName = ?, Email = ?, UserName = ?, Password = ?, isAdmin = ? WHERE id = ?";
       
       Connection con = getConnection();
       PreparedStatement stmt = con.prepareStatement(sql);
       
                
                stmt.setString(1,userObj.getFirstName());
                stmt.setString(2, userObj.getLastName());
                stmt.setString(3, userObj.getEmail());
                stmt.setString(4, userObj.getUserName());
                stmt.setString(5, userObj.getPassword());
                stmt.setBoolean(6, userObj.getIsAdmin());
                stmt.setInt(7,userObj.getId());
                
                res = stmt.executeUpdate() > 0;
       
                return res;
   }
   
   public void deleteEmployee(int id) throws SQLException{
       String sql = "DELETE from employees WHERE id = ?";
       Connection con = getConnection();
       PreparedStatement stmnt = con.prepareStatement(sql);
       
       stmnt.setInt(1, id);
       
       stmnt.executeUpdate();
   }
}
