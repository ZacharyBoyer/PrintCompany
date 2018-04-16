package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Model.Order;
import java.sql.Blob;

public class orderDao {
    private String url ="jdbc:mysql://localhost:3306/printmarketing";
    private String userDB="root";
    private String passDB="";
    
    public orderDao(){
    }
    
    public orderDao(String url, String userDB, String passDB){
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
                System.out.println("connected                                                                                                          ");
           }
       } catch(SQLException sqlEx){
           sqlEx.printStackTrace();
       } catch(ClassNotFoundException ex){
           ex.printStackTrace();
       }
       return conn;
   }
    
    public int addOrder(Order userObj){
        int res = 0;
        String sql = "INSERT INTO `orders` (`id`, `agentId`, `clientId`, `flyerQty`, `flyerLayout`, `personalCopy`, `paymentInformation`, `invoiceNumber`, `comments`, `isFlyerArtApproved`, `isPaymentReceived`) VALUES (NULL,?,?,?,?,?,?,?,?,?,?);";
        try{
            Connection conn = getConnection();
            if(conn!=null){
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1,userObj.getAgentId());
                stmt.setInt(2, userObj.getClientId());
                stmt.setInt(3, userObj.getFlyerQuantity());
                stmt.setString(4, userObj.getFlyerLayout());
                stmt.setInt(5, userObj.getPersonalCopies());
                stmt.setString(6, userObj.getPaymentInfo());
                stmt.setString(7, userObj.getInvoiceNum());
                stmt.setString(8, userObj.getComments());
                stmt.setBoolean(9, userObj.getFlyerArtApprovl());
                stmt.setBoolean(10, userObj.getPaymentRecvd());                
                res = stmt.executeUpdate();
                conn.close();
            }
            
        } catch (SQLException sqlEx){
            sqlEx.printStackTrace();
        }
        return res;
    }
    
   public ArrayList<Order> viewOrders(){
       ArrayList<Order> orderList = new ArrayList();
       String sql = "SELECT * FROM orders";
    int id, agentId, clientId, flyerQuantity, personalCopies;
    String flyerLayout, paymentInfo, invoiceNum, comments;
    Boolean flyerArtApprovl, paymentRecvd;
    Blob flyerImg;
       
       try{
        Connection conn = getConnection();
           try (Statement stmt = conn.createStatement()) {
               ResultSet resultSet = stmt.executeQuery(sql);
               
               while(resultSet.next()){
          id = resultSet.getInt("id");
          agentId = resultSet.getInt("agentId");
          clientId = resultSet.getInt("clientId"); 
          flyerQuantity = resultSet.getInt("flyerQty"); 
          personalCopies = resultSet.getInt("personalCopy"); 
          flyerLayout = resultSet.getString("flyerLayout"); 
          paymentInfo = resultSet.getString("paymentInformation"); 
          invoiceNum = resultSet.getString("invoiceNumber"); 
          comments = resultSet.getString("comments"); 
          flyerArtApprovl = resultSet.getBoolean("isFlyerArtApproved");  
          paymentRecvd = resultSet.getBoolean("ispaymentReceived"); 
          flyerImg = resultSet.getBlob("flyerImg");
          
                   Order userObj = new Order();
                   
                   userObj.setId(id);
                   userObj.setAgentId(agentId);
                   userObj.setClientId(clientId);
                   userObj.setFlyerQuantity(flyerQuantity);
                   userObj.setPersonalCopies(personalCopies);
                   userObj.setFlyerLayout(flyerLayout);
                   userObj.setPaymentInfo(paymentInfo);
                   userObj.setInvoiceNum(invoiceNum);
                   userObj.setComments(comments);
                   userObj.setFlyerArtApprovl(flyerArtApprovl);
                   userObj.setPaymentRecvd(paymentRecvd);
                   userObj.setFlyerImg(flyerImg);

                   orderList.add(userObj);
               }
               resultSet.close();
           }
        if(conn!=null && !conn.isClosed()){
         conn.close();
        }
       } catch(SQLException sqlEx){
           sqlEx.printStackTrace();
       }
       return orderList;
   }
   
   public Order showOrder(int id) throws SQLException{
       Order userObj = null;
       String sql = "SELECT * FROM orders WHERE id = ?";
       
       Connection conn = getConnection();
       PreparedStatement statement = conn.prepareStatement(sql);
       statement.setInt(1, id);
       ResultSet resultSet = statement.executeQuery();
       
       while(resultSet.next()){         
           userObj = new Order();
                    userObj.setId(resultSet.getInt("id"));
                   userObj.setAgentId(resultSet.getInt("agentId"));
                   userObj.setClientId(resultSet.getInt("clientId"));
                   userObj.setFlyerQuantity(resultSet.getInt("flyerQty"));
                   userObj.setPersonalCopies(resultSet.getInt("personalCopy"));
                   userObj.setFlyerLayout(resultSet.getString("flyerLayout"));
                   userObj.setPaymentInfo(resultSet.getString("paymentInformation"));
                   userObj.setInvoiceNum(resultSet.getString("invoiceNumber"));
                   userObj.setComments(resultSet.getString("comments"));
                   userObj.setFlyerArtApprovl(resultSet.getBoolean("isFlyerArtApproved"));
                   userObj.setPaymentRecvd(resultSet.getBoolean("ispaymentReceived"));
                   userObj.setFlyerImg(resultSet.getBlob("flyerImg"));

       }
       resultSet.close();
            if(conn!=null && !conn.isClosed()){
         conn.close();
        }
       return userObj;
   }
   
   public boolean updateOrder(Order userObj) throws SQLException{
       boolean res;
       String sql = "UPDATE `orders` SET `agentId` = ?, `clientId` = ?, `flyerQty` = ?, `flyerLayout` = ?, `personalCopy` = ?, `paymentInformation` = ?, `invoiceNumber` = ?, `comments` = ?, `isFlyerArtApproved` = ?, `isPaymentReceived` = ? WHERE `orders`.`id` = ?;";
       
       Connection conn = getConnection();
       PreparedStatement stmt = conn.prepareStatement(sql);
       
                
                stmt.setInt(1,userObj.getAgentId());
                stmt.setInt(2, userObj.getClientId());
                stmt.setInt(3, userObj.getFlyerQuantity());
                stmt.setString(4, userObj.getFlyerLayout());
                stmt.setInt(5, userObj.getPersonalCopies());
                stmt.setString(6, userObj.getPaymentInfo());
                stmt.setString(7, userObj.getInvoiceNum());
                stmt.setString(8, userObj.getComments());
                stmt.setBoolean(9, userObj.getFlyerArtApprovl());
                stmt.setBoolean(10, userObj.getPaymentRecvd());
                stmt.setInt(14,userObj.getId());
                
                res = stmt.executeUpdate() > 0;
     
            if(conn!=null && !conn.isClosed()){
         conn.close();
        }
                return res;
   }
   
   public void deleteOrder(int id) throws SQLException{
       String sql = "DELETE from orders WHERE id = ?";
       Connection conn = getConnection();
       PreparedStatement stmnt = conn.prepareStatement(sql);
       
       stmnt.setInt(1, id);
       
       stmnt.executeUpdate();
        if(conn!=null && !conn.isClosed()){
         conn.close();
        }
   }
}
