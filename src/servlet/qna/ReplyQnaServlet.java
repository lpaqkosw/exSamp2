package servlet.qna;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.qna.QnaDAO;
import model.qna.QnaVO;

/**
 * Servlet implementation class ReplyQnaServlet
 */
@WebServlet("/qna_reply")
public class ReplyQnaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyQnaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		QnaDAO dao = new QnaDAO();
		QnaVO vo = dao.selectQna(Integer.parseInt(request.getParameter("idx")));
		request.setAttribute("vo", vo);
		RequestDispatcher rd = request.getRequestDispatcher("qa/qareply.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String answer = request.getParameter("answer");
		QnaDAO dao = new QnaDAO();
		int row = dao.replyQna(Integer.parseInt(request.getParameter("idx")), answer);
		if(row == 0) {
			request.setAttribute("result", "failed");
		}
		else {
			request.setAttribute("result", "success");
		}
		RequestDispatcher rd = request.getRequestDispatcher("qa/qareply.jsp");
		rd.forward(request, response);
	}

}
