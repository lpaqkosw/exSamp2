package servlet.users;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import KISA.SHA256;
import model.users.UsersDAO;
import model.users.UsersVO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/users_login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("member/login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String userid = request.getParameter("userid");
		String userpw = request.getParameter("userpw");
		SHA256 hash = new SHA256(userpw.getBytes());
		String hashpw = Base64.getEncoder().encodeToString(hash.GetHashCode());
		UsersDAO dao = new UsersDAO();
		UsersVO vo  = dao.selectUsers(userid, hashpw);
		if(vo.getUserpw().equals(hashpw)) {
			request.setAttribute("result", "success");
			HttpSession session = request.getSession();
			session.setAttribute("username", vo.getUsername());
			System.out.println(vo.getUsername());
			session.setAttribute("userid", vo.getUserid());
			session.setAttribute("idx", vo.getIdx());
			session.setAttribute("admin", vo.getAdmin());
			session.setMaxInactiveInterval(10800);
		}
		else {
			request.setAttribute("result", "failed");
		}
		System.out.println(hashpw);
		
		RequestDispatcher rd = request.getRequestDispatcher("member/login.jsp");
		rd.forward(request, response);
	}

}
