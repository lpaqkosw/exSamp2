package servlet.notice;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.notice.NoticeDAO;
import model.notice.NoticeVO;
import util.PageIndex;

/**
 * Servlet implementation class ListNoticeServlet
 */
@WebServlet("/notice_list")
public class ListNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListNoticeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeDAO dao = new NoticeDAO();
		
		int currentpage = 1;
		int maxList = 10;
		int totpage = 1;
		int totcount = dao.countNotice();
		if(totcount%maxList == 0) {
			totpage = totcount/maxList;
		}
		else {
			totpage = totcount/maxList +1;
		}
		
		if(request.getParameter("page") != null) {
			currentpage = Integer.parseInt(request.getParameter("page"));
		}
		
		int startlist = (currentpage-1)*10 +1;
		int endlist = currentpage * 10;
		int listnum = totcount -  (currentpage-1)*10;
		String pagenav = PageIndex.pageList(currentpage, totpage, "notice_list", "");
		
		List<NoticeVO> list = dao.listNotice(startlist, endlist);
		request.setAttribute("totcount", totcount);
		request.setAttribute("pagenav", pagenav);
		request.setAttribute("list", list);
		request.setAttribute("listnum", listnum);
		request.setAttribute("totpage", totpage);
		
		RequestDispatcher rd = request.getRequestDispatcher("notice/notice.jsp");
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
