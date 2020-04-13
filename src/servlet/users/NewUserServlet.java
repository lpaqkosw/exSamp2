package servlet.users;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import KISA.SHA256;
import model.users.UsersDAO;
import model.users.UsersVO;

/**
 * Servlet implementation class InsertUserServlet
 */
@WebServlet("/users_new")
public class NewUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("member/member.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		UsersDAO dao = new UsersDAO();
		UsersVO vo = new UsersVO();
		
		vo.setUserid(request.getParameter("userid"));
		vo.setUsername(request.getParameter("username"));
		vo.setEmail(request.getParameter("email1")+"@"+request.getParameter("email2"));
		String userpw = request.getParameter("pass2");
		SHA256 hash = new SHA256(userpw.getBytes());
		String hashpw = Base64.getEncoder().encodeToString(hash.GetHashCode());
		vo.setUserpw(hashpw);
		
		int row =dao.insertUsers(vo);
		if(row == 1) {
			request.setAttribute("result", "success");
		}
		else {
			request.setAttribute("result", "failed");
		}
		RequestDispatcher rd = request.getRequestDispatcher("member/member.jsp");
		rd.forward(request, response);
	}

}
