package servlet.qna;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.qna.QnaDAO;
import model.qna.QnaVO;
import util.PageIndex;

/**
 * Servlet implementation class ListQNAServlet
 */
@WebServlet("/qna_list")
public class ListQnaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListQnaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		QnaDAO dao = new QnaDAO();
		String category = null;
		if(request.getParameter("category") != null && !request.getParameter("category").equals("all")) {
			category = request.getParameter("category");
			int currentpage = 1;
			int maxList = 10;
			int totpage = 1;
			int totcount = dao.categoryListCountQna(category);
			if(totcount%maxList == 0) {
				totpage = totcount/maxList;
			}
			else {
				totpage = totcount/maxList +1;
			}
			
			if(request.getParameter("page") != null) {
				currentpage = Integer.parseInt(request.getParameter("page"));
				System.out.println("current page =  "+ currentpage);
			}
			
			int startlist = (currentpage-1)*10 +1;
			int endlist = currentpage * 10;
			String pagenav = PageIndex.pageList(currentpage, totpage, "qna_list", "category="+category);
			
			List<QnaVO> list = dao.categoryListQna(category, startlist, endlist);
			int listnum = totcount -  (currentpage-1)*10;
			System.out.println("category list");
			request.setAttribute("category", category);
			request.setAttribute("totcount", totcount);
			request.setAttribute("pagenav", pagenav);
			request.setAttribute("list", list);
			request.setAttribute("listnum", listnum);
			request.setAttribute("totpage", totpage);
			
			RequestDispatcher rd = request.getRequestDispatcher("qa/qa.jsp");
			rd.forward(request, response);
		}
		else {
			category = "all";
			int currentpage = 1;
			int maxList = 10;
			int totpage = 1;
			int totcount = dao.countQna();
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
			String pagenav = PageIndex.pageList(currentpage, totpage, "qna_list", "");
			
			List<QnaVO> list = dao.listQna(startlist, endlist);
			int listnum = totcount -  (currentpage-1)*10;
			request.setAttribute("category", category);
			request.setAttribute("totcount", totcount);
			request.setAttribute("pagenav", pagenav);
			request.setAttribute("list", list);
			request.setAttribute("listnum", listnum);
			request.setAttribute("totpage", totpage);
			
			RequestDispatcher rd = request.getRequestDispatcher("qa/qa.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
