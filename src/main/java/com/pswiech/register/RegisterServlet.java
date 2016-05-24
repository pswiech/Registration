package com.pswiech.register;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pswiech.dao.RegisterDao;

/**
 * Servlet implementation class Aaa
 */
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String n = request.getParameter("username");
        String p = request.getParameter("userpass");

        HttpSession session = request.getSession(false);
        if (session != null)
            session.setAttribute("name", n);

        if (!RegisterDao.isValidUserName(n)) {
            out.print("<p style=\"color:red\">" + "Username: " + n + " is invalid." + "<br>"
                    + "Username must contain only alpha-numeric characters and be at least 5 characters long."
                    + "</p>");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.include(request, response);
        } else if (RegisterDao.isAlreadyRegistered(n)) {
            out.print("<p style=\"color:red\">" + "Username: " + n
                    + " is already exists in database." + "</p>");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.include(request, response);
        } else if (!RegisterDao.isValidPassword(p)) {
            out.print("<p style=\"color:red\">" + "Password does not meet security requirements!"
                    + "<br>"
                    + "Password must be minimum 8 characters long and must contains at least 1 number, 1 uppercase, and 1 lowercase character."
                    + "</p>");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.include(request, response);
        } else {
            RegisterDao.perstoreUser(n, p);
            RequestDispatcher rd = request.getRequestDispatcher("registered.jsp");
            rd.forward(request, response);
        }
        out.close();
    }
}
