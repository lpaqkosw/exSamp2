package servlet.notice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.notice.NoticeDAO;
import model.notice.NoticeVO;

/**
 * Servlet implementation class ViewNoticeServlet
 */
@WebServlet("/notice_view")
public class ViewNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewNoticeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = Integer.parseInt(request.getParameter("idx"));
		NoticeDAO dao = new NoticeDAO();
		NoticeVO vo = dao.selectNotice(idx);
		Cookie cook = null;
		Cookie[] cookies = request.getCookies();
		boolean flag = false;
		for(int i = 0 ; i < cookies.length;i++) {
			if(cookies[i].getValue().equals("notice"+idx)) {
				flag = true;
				break;
			}
		}
		if(!flag) {
			int row = dao.incrementReadcnt(idx);
			cook = new Cookie("notice", "notice"+idx);
			cook.setMaxAge(3600);
			response.addCookie(cook);
		}
		request.setAttribute("vo", vo);
		RequestDispatcher rd = request.getRequestDispatcher("notice/noticeview.jsp");
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
