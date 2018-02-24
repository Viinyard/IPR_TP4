package tp.mri.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ChatServlet
 */
@WebServlet("/chat")
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StringBuffer chatContent;
	private SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

	@Override
	public void init() throws ServletException {
		super.init();
		ServletContext context = getServletContext();
		chatContent = new StringBuffer();
		
		try {
			InputStream is = new FileInputStream(context.getRealPath("/chat.txt"));
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String ligne;

			while ((ligne = br.readLine()) != null) {
				chatContent.append(ligne).append("\n");
			}

			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//chatContent.append(context.getInitParameter("welcome")).append("\n");
	}

	@Override
	public void destroy() {
		super.destroy();
		ServletContext context = getServletContext();
		
		File file = new File(context.getRealPath("/chat.txt"));
		
		try {
			if(!file.exists()) {
					file.createNewFile();
			}
			
			FileWriter fw = new FileWriter(file);

			fw.write(chatContent.toString(), 0, chatContent.length());
			
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChatServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		ServletContext context = getServletContext();

		if (session.getAttribute("pseudo") == null) {
			response.sendRedirect("login");
		}

		RequestDispatcher rd = request.getRequestDispatcher("chat.jsp");
		request.setAttribute("content", chatContent.toString());
		rd.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String pseudo = (String) session.getAttribute("pseudo");
		if (pseudo == null) {
			response.sendRedirect("login");
		}

		String action = request.getParameter("action");
		switch (action) {
		case "submit":
			this.chatContent.append("[" + this.formatter.format(new Date()) + "] ").append(pseudo).append(" > ")
					.append(request.getParameter("ligne")).append("\n");
			break;
		case "refresh":
			// do nothing, just refresh
			break;
		}
		doGet(request, response);
	}

}
