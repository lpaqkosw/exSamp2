package servlet.qna;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
@WebServlet("/qna_searchList")
public class SearchListQnaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchListQnaServlet() {
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
		String genre = request.getParameter("genre");
		String enKey = URLEncoder.encode((request.getParameter("key")));
		String deKey = URLDecoder.decode(request.getParameter("key"));
		
		if(request.getParameter("category") != null && !request.getParameter("category").equals("all")) {
			category = request.getParameter("category");
			int currentpage = 1;
			int maxList = 10;
			int totpage = 1;
			int totcount = dao.categorySearchListCountQna(category, genre, deKey);
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
			List<QnaVO> list = dao.categorySearchListQna(category, genre, deKey, startlist, endlist);
			System.out.println("category search");
			String pagenav = PageIndex.pageList(currentpage, totpage, "qna_searchList", "category="+category+"&genre="+genre+"&key="+enKey);
			
			request.setAttribute("category", category);
			request.setAttribute("totcount", totcount);
			request.setAttribute("pagenav", pagenav);
			request.setAttribute("list", list);
			request.setAttribute("listnum", listnum);
			request.setAttribute("totpage", totpage);
			request.setAttribute("genre", genre);
			request.setAttribute("key", deKey);
			
			RequestDispatcher rd = request.getRequestDispatcher("qa/qa.jsp");
			rd.forward(request, response);
			
		}
		else {
			int currentpage = 1;
			int maxList = 10;
			int totpage = 1;
			int totcount = dao.searchCountQna(genre,deKey);
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
			List<QnaVO> list = dao.searchListQna(genre, deKey, startlist, endlist);
			System.out.println("search all");
			String pagenav = PageIndex.pageList(currentpage, totpage, "qna_searchList", "category=all&genre="+genre+"&key="+enKey);
			
			request.setAttribute("category", request.getParameter("category"));
			request.setAttribute("totcount", totcount);
			request.setAttribute("pagenav", pagenav);
			request.setAttribute("list", list);
			request.setAttribute("listnum", listnum);
			request.setAttribute("totpage", totpage);
			request.setAttribute("genre", genre);
			request.setAttribute("key", deKey);
			
			RequestDispatcher rd = request.getRequestDispatcher("qa/qa.jsp");
			rd.forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
