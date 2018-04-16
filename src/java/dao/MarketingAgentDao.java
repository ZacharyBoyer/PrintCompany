/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Model.MarketingAgent;
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
public class MarketingAgentDao {

    private String url = "jdbc:mysql://mAalhost:3306/printmarketing";
    private String userDB = "root";
    private String passDB = "";

    public MarketingAgentDao() {

    }

    public MarketingAgentDao(String url, String userDB, String passDB) {
        this.url = url;
        this.userDB = userDB;
        this.passDB = passDB;
    }

    protected Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(url, userDB, passDB);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public int addMarketingAgent(MarketingAgent mA) {
        int res = 0;
        String sql = "INSERT INTO `marketingagent` (`id`, `firstName`, `lastName`, `phoneNo`, `email`, `userName`, `password`) VALUES (NULL, ?, ?, ?, ?, ?, ?);";
        try {
            Connection conn = getConnection();
            if (conn != null) {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, mA.getFirstName());
                stmt.setString(2, mA.getLastName());
                stmt.setString(3, mA.getPhoneNumber());
                stmt.setString(4, mA.getEmail());
                stmt.setString(5, mA.getUserName());
                stmt.setString(6, mA.getPassword());

                res = stmt.executeUpdate();
                return res;
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return res;
    }

    public boolean updateMarketingAgent(MarketingAgent mA) throws SQLException {
        boolean res;
        String sql = "UPDATE `marketingagent` SET `firstName` = ?, `lastName` = ?, `phoneNo` = ?, `email` = ?, `userName` = ?, `password` = ? WHERE `marketingagent`.`id` = ?;";
        Connection conn = getConnection();

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, mA.getFirstName());
        stmt.setString(2, mA.getLastName());
        stmt.setString(3, mA.getPhoneNumber());
        stmt.setString(4, mA.getEmail());
        stmt.setString(5, mA.getUserName());
        stmt.setString(6, mA.getPassword());
        stmt.setInt(7, mA.getId());

        res = stmt.executeUpdate() > 0;

        return res;
    }

    public void deleteMarketingAgent(int id) {
        String sql = "DELETE FROM `marketingagent` WHERE id = ?";
        try {
            Connection conn = getConnection();
            if (conn != null) {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public MarketingAgent viewMarketingAgent(int id) throws SQLException {
        MarketingAgent mA = null;

        String sql = "SELECT * FROM `marketingagent` WHERE id = ?;";
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet res = stmt.executeQuery();

        while (res.next()) {
            mA = new MarketingAgent();
            mA.setId(res.getInt("id"));
            mA.setFirstName(res.getString("firstName"));
            mA.setLastName(res.getString("lastName"));
            mA.setPhoneNumber(res.getString("phoneNo"));
            mA.setEmail(res.getString("email"));
            mA.setUserName(res.getString("userName"));
            mA.setPassword(res.getString("password"));
        }
        return mA;
    }

    public ArrayList<MarketingAgent> viewAllMarketingAgents() throws SQLException {
        ArrayList<MarketingAgent> mAList = new ArrayList();

        String sql = "SELECT * FROM `marketingagent`;";
        try {
            Connection conn = getConnection();
            try (Statement stmt = conn.createStatement();
                    ResultSet res = stmt.executeQuery(sql)) {

                while (res.next()) {
                    MarketingAgent mA = new MarketingAgent();

                    mA.setId(res.getInt("id"));
                    mA.setFirstName(res.getString("firstName"));
                    mA.setLastName(res.getString("lastName"));
                    mA.setPhoneNumber(res.getString("phoneNo"));
                    mA.setEmail(res.getString("email"));
                    mA.setUserName(res.getString("userName"));
                    mA.setPassword(res.getString("password"));
                    mAList.add(mA);
                }
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return mAList;
    }
}
