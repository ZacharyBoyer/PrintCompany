/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Model.*;
import PrintCompanyService.OrderService;
import PrintCompanyService.*;
import dao.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

/**
 *
 * @author Zach
 */
@WebServlet(name = "PMCServlet", urlPatterns = {"/"})
public class PMCServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    locationService locServc;
    locationDao locDao;
    clientService clientServc;
    ClientDao clientDao;
    MarketingAgentDao mADao;
    MarketingAgentService mAServc;
    orderDao oDao;
    OrderService oServc;
    String jdbcUserName;
    String jdbcPassword;
    String jdbcURL;

    @Override
    public void init() throws ServletException {
        jdbcURL = "jdbc:mysql://localhost:3306/printmarketing";
        jdbcUserName = "root";
        jdbcPassword = "";
        locDao = new locationDao(jdbcURL, jdbcUserName, jdbcPassword);
        locServc = new locationService();
        clientDao = new ClientDao(jdbcURL, jdbcUserName, jdbcPassword);
        clientServc = new clientService();
        mADao = new MarketingAgentDao(jdbcURL, jdbcUserName, jdbcPassword);
        mAServc = new MarketingAgentService();
        oDao = new orderDao(jdbcURL, jdbcUserName, jdbcPassword);
        oServc = new OrderService();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PMCServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PMCServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();
        switch (action) {
            case "/editLoc":
                showEditLocation(request, response);
                break;

            case "/updateLoc":
                updateLocation(request, response);
                break;

            case "/deleteLoc":
                deleteLocation(request, response);
                break;

            case "/newLoc":
                newLocationForm(request, response);
                break;

            case "/addLoc":
                addLocation(request, response);
                break;

            case "/locList":
                viewLocations(request, response);
                break;

            case "/editClient":
                showEditClient(request, response);
                break;

            case "/updateClient":
                updateClient(request, response);
                break;

            case "/newClient":
                newClientForm(request, response);
                break;

            case "/addClient":
                addClient(request, response);
                break;

            case "/deleteClient":
                deleteClient(request, response);
                break;

            case "/clientList":
                viewClients(request, response);

            case "/editAgent":

            case "/updateAgent":

            case "/newAgent":

            case "/addAgent":

            case "/deleteAgent":

            case "/agentList":

            case "/editOrder":

            case "/updateOrder":

            case "/newOrder":

            case "/addOrder":

            case "/deleteOrder":

            case "/orderList":

            default:
                viewClients(request, response);
                break;

        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Location methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the Location <code> methods.
     */
    protected void viewLocations(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ArrayList<Location> locList = new ArrayList();
        try {
            locList = locServc.viewAllLocations(locDao);
        } catch (SQLException ex) {
            Logger.getLogger(PMCServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("locList", locList);
        RequestDispatcher dispt = request.getRequestDispatcher("viewLocations.jsp");
        dispt.forward(request, response);

    }

    private void showEditLocation(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            String Sid = request.getParameter("id");
            int id = Integer.parseInt(Sid);
            System.out.println(id);
            Location loc = locServc.viewLocation(id, locDao);
            request.setAttribute("location", loc);

            RequestDispatcher dispatcher = request.getRequestDispatcher("EditLocation.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    private void updateLocation(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String name = request.getParameter("name");
        int id = Integer.parseInt(request.getParameter("id"));
        int cap = Integer.parseInt(request.getParameter("Capacity"));

        Location loc = new Location(id, name, cap);

        try {
            locServc.updateLocation(loc, locDao);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        response.sendRedirect("locList");
    }

    private void deleteLocation(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));

        locDao.deleteLocation(id);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/");
        dispatcher.forward(request, response);
    }

    private void addLocation(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String name = request.getParameter("name");
        int cap = Integer.parseInt(request.getParameter("Capacity"));

        int res = locServc.addLocation(name, cap, locDao);

        if (res > 0) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("errorjsp.jsp");
        }

    }

    private void newLocationForm(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("addLocation.jsp");
        dispatcher.forward(request, response);
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Client methods. Click on the + sign on the left to edit the code."> 
    protected void viewClients(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ArrayList<Client> clientList = new ArrayList();

        clientList = clientServc.viewClients(clientDao);

        request.setAttribute("clientList", clientList);
        RequestDispatcher dispt = request.getRequestDispatcher("viewClientList.jsp");
        dispt.forward(request, response);
    }

    private void showEditClient(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            System.out.println(id);
            Client client = clientServc.showClient(id, clientDao);
            request.setAttribute("client", client);

            RequestDispatcher dispatcher = request.getRequestDispatcher("EditClient.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    private void updateClient(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        int AgentId = Integer.parseInt(request.getParameter("AgentId"));
        String FirstName = request.getParameter("FName");
        String LastName = request.getParameter("LName");
        String StreetNumber = request.getParameter("SNum");
        String StreetName = request.getParameter("SName");
        String City = request.getParameter("City");
        String Province = request.getParameter("Province");
        String PostalCode = request.getParameter("Postal");
        String TelOffice = request.getParameter("ONum");
        String TelCell = request.getParameter("CNum");
        String Email = request.getParameter("Email");
        String Company = request.getParameter("CName");
        String CompanyType = request.getParameter("CType");

        Client client = new Client(id, AgentId, FirstName, LastName, StreetNumber, StreetName, City, Province, PostalCode, TelOffice, TelCell, Email, Company, CompanyType);

        try {
            clientServc.updateClient(client, clientDao);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        response.sendRedirect("clientList");
    }

    private void deleteClient(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            clientDao.deleteClient(id);
        } catch (SQLException ex) {
            Logger.getLogger(PMCServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/");
        dispatcher.forward(request, response);
    }

    private void addClient(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        int AgentId = Integer.parseInt(request.getParameter("AgentId"));
        String FirstName = request.getParameter("FName");
        String LastName = request.getParameter("LName");
        String StreetNumber = request.getParameter("SNum");
        String StreetName = request.getParameter("SName");
        String City = request.getParameter("City");
        String Province = request.getParameter("Province");
        String PostalCode = request.getParameter("Postal");
        String TelOffice = request.getParameter("ONum");
        String TelCell = request.getParameter("CNum");
        String Email = request.getParameter("Email");
        String Company = request.getParameter("CName");
        String CompanyType = request.getParameter("CType");

        int res = clientServc.addClient(AgentId, FirstName, LastName, StreetNumber, StreetName, City, Province, PostalCode, TelOffice, TelCell, Email, Company, CompanyType, clientDao);

        if (res > 0) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("errorjsp.jsp");
        }

    }

    private void newClientForm(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("AddClient.jsp");
        dispatcher.forward(request, response);
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="MarketingAgent methods. Click on the + sign on the left to edit the code."> 
    protected void viewMarketingAgents(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ArrayList<MarketingAgent> mAList = new ArrayList();

        try {
            mAList = mAServc.viewAllMarketingAgents(mADao);
        } catch (SQLException ex) {
            Logger.getLogger(PMCServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("mAList", mAList);
        RequestDispatcher dispt = request.getRequestDispatcher("viewMarketingAgentList.jsp");
        dispt.forward(request, response);
    }

    private void showEditMarketingAgent(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            System.out.println(id);
            MarketingAgent mA = mAServc.viewMarketingAgent(id, mADao);
            request.setAttribute("mA", mA);

            RequestDispatcher dispatcher = request.getRequestDispatcher("EditMarketingAgent.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    private void updateMarketingAgent(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        int AgentId = Integer.parseInt(request.getParameter("AgentId"));
        String FirstName = request.getParameter("FName");
        String LastName = request.getParameter("LName");
        String Email = request.getParameter("SNum");
        String UserName = request.getParameter("SName");
        String Password = request.getParameter("City");
        String phoneNumber = request.getParameter("Province");

        MarketingAgent mA = new MarketingAgent(id, FirstName, LastName, Email, UserName, Password, phoneNumber);

        try {
            mAServc.updateMarketingAgent(mA, mADao);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        response.sendRedirect("mAList");
    }

    private void deleteMarketingAgent(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));

        mADao.deleteMarketingAgent(id);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/");
        dispatcher.forward(request, response);
    }

    private void addMarketingAgent(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String FirstName = request.getParameter("FName");
        String LastName = request.getParameter("LName");
        String Email = request.getParameter("Email");
        String UserName = request.getParameter("SNum");
        String Password = request.getParameter("SName");
        String phoneNumber = request.getParameter("City");

        int res = mAServc.addMarketingAgent(FirstName, LastName, Email, UserName, Password, phoneNumber, mADao);

        if (res > 0) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("errorjsp.jsp");
        }

    }

    private void newMarketingAgentForm(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("AddMarketingAgent.jsp");
        dispatcher.forward(request, response);
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Order methods. Click on the + sign on the left to edit the code."> 
    protected void viewOrders(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ArrayList<Order> oList = new ArrayList();

        oList = oServc.viewOrders(oDao);

        request.setAttribute("oList", oList);
        RequestDispatcher dispt = request.getRequestDispatcher("viewOrderList.jsp");
        dispt.forward(request, response);
    }

    private void showEditOrder(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            System.out.println(id);
            Order oObj = oServc.showOrder(id, oDao);
            request.setAttribute("oObj", oObj);

            RequestDispatcher dispatcher = request.getRequestDispatcher("EditOrder.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    private void updateOrder(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        int agentId = Integer.parseInt(request.getParameter("AgentId"));
        int clientId = Integer.parseInt(request.getParameter("FName"));
        int flyerQuantity = Integer.parseInt(request.getParameter("LName"));
        int personalCopies = Integer.parseInt(request.getParameter("SNum"));
        String flyerLayout = request.getParameter("SName");
        String paymentInfo = request.getParameter("City");
        String invoiceNum = request.getParameter("Province");
        String comments = request.getParameter("Postal");
        Boolean flyerArtApprovl = Boolean.parseBoolean(request.getParameter("ONum"));
        Boolean paymentRecvd = Boolean.parseBoolean(request.getParameter("CNum"));
        byte[] img = request.getParameter("Email").getBytes();
        Blob flyerImg = new SerialBlob(img);

        Order oObj = new Order(agentId, clientId, flyerQuantity, personalCopies, flyerLayout, paymentInfo, invoiceNum, comments, flyerArtApprovl, paymentRecvd, flyerImg);

        try {
            oServc.updateOrder(oObj, oDao);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        response.sendRedirect("oList");
    }

    private void deleteOrder(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            oDao.deleteOrder(id);
        } catch (SQLException ex) {
            Logger.getLogger(PMCServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/");
        dispatcher.forward(request, response);
    }

    private void addOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int AgentId = Integer.parseInt(request.getParameter("AgentId"));
        String FirstName = request.getParameter("FName");
        String LastName = request.getParameter("LName");
        String StreetNumber = request.getParameter("SNum");
        String StreetName = request.getParameter("SName");
        String City = request.getParameter("City");
        String Province = request.getParameter("Province");
        String PostalCode = request.getParameter("Postal");
        String TelOffice = request.getParameter("ONum");
        String TelCell = request.getParameter("CNum");
        String Email = request.getParameter("Email");
        String Company = request.getParameter("CName");
        String CompanyType = request.getParameter("CType");

//        //uncomment this later
//
//        int res = oServc.addOrder(AgentId, FirstName, LastName,StreetNumber,StreetName,City,Province,PostalCode,TelOffice,TelCell,Email,Company,CompanyType, oDao);
//        
//        if (res > 0) {
//           RequestDispatcher dispatcher = request.getRequestDispatcher("/");
//           dispatcher.forward(request, response);
//        } else {
//           response.sendRedirect("errorjsp.jsp");
//        }
    }

    private void newOrderForm(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("AddOrder.jsp");
        dispatcher.forward(request, response);
    }// </editor-fold>
}
