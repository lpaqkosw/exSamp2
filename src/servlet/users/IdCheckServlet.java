package servlet.users;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.users.UsersDAO;
import model.users.UsersVO;

/**
 * Servlet implementation class IdCheckServlet
 */
@WebServlet("/users_idcheck")
public class IdCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String inputid = request.getParameter("userid");
		UsersDAO dao = new UsersDAO();
		int row = dao.searchUserId(inputid);
		if(row == 1) {
			request.setAttribute("result", "failed");
		}
		else {
			request.setAttribute("result", "ok");
			
		}
		request.setAttribute("userid", inputid);
		
		RequestDispatcher rd = request.getRequestDispatcher("member/user_idcheck.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
