/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import PrintCompanyService.clientService;
import dao.ClientDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Zach
 */
@WebServlet(name = "PMCServlet", urlPatterns = {"/PMCServlet"})
public class PMCServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     clientService clientSrvc;
    ClientDao clientDao;
    String jdbcUserName;
    String jdbcPassword;
    String jdbcURL;
    
    @Override
    public void init() throws ServletException {
        jdbcURL = getServletContext().getInitParameter("jdbcURL");
        jdbcUserName = getServletContext().getInitParameter("jdbcUserName");
        jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
        
        clientDao = new ClientDao(jdbcURL, jdbcUserName, jdbcPassword);
        clientSrvc = new clientService();
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
        String action=request.getServletPath();
     switch(action){
            case "/insert":
                showNewClientForm(request, response);
                break;
                 default:
                //viewUsers(request, response);
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
        processRequest(request, response);
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

    private void showNewClientForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        
        int res = clientSrvc.addClient(AgentId,  FirstName,  LastName,  StreetNumber,  StreetName, 
                          City,   Province,   PostalCode,   TelOffice,  TelCell,
                         Email,  Company, CompanyType, clientDao);
       
        if(res>0){
           RequestDispatcher dispatcher = request.getRequestDispatcher("sucess.jsp");
           dispatcher.forward(request, response);
        } else {
           response.sendRedirect("errorjsp.jsp");
        }
    }

}
