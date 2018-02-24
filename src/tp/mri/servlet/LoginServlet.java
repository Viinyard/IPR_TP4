package tp.mri.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("pseudo") != null) {
			response.sendRedirect("chat");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.include(request, response);
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("pseudo") != null) {
			response.sendRedirect("chat");
		}
		
		String pseudo = request.getParameter("pseudo");
		if(pseudo != null && pseudo.length() > 0) {
			session.setAttribute("pseudo", pseudo);
			response.sendRedirect("chat");
		} else {
			request.getRequestDispatcher("login.jsp");
		}
	}

}
