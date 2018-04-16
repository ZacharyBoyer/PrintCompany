/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrintCompanyService;

import Model.MarketingAgent;
import dao.MarketingAgentDao;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Zach
 */
public class MarketingAgentService {

    public ArrayList<MarketingAgent> viewAllMarketingAgents(MarketingAgentDao maDao) throws SQLException {
        ArrayList<MarketingAgent> mAList = new ArrayList();
        mAList = maDao.viewAllMarketingAgents();
        return mAList;
    }

    public MarketingAgent viewMarketingAgent(int id, MarketingAgentDao maDao) throws SQLException {
        MarketingAgent mA = maDao.viewMarketingAgent(id);
        return mA;
    }

    public boolean updateMarketingAgent(MarketingAgent mA, MarketingAgentDao maDao) throws SQLException {
        boolean res = maDao.updateMarketingAgent(mA);
        return res;
    }

    public int addMarketingAgent(String FirstName, String LastName, String Email, String UserName, String Password, String phoneNumber, MarketingAgentDao maDao) {
        int res = 0;
        MarketingAgent mA = new MarketingAgent();

        mA.setFirstName(FirstName);
        mA.setLastName(LastName);
        mA.setEmail(Email);
        mA.setUserName(UserName);
        mA.setPassword(Password);
        mA.setPhoneNumber(phoneNumber);
        res = maDao.addMarketingAgent(mA);

        return res;
    }

    public void deleteMarketingAgent(int id, MarketingAgentDao maDao) throws SQLException {
        maDao.deleteMarketingAgent(id);
    }
}
