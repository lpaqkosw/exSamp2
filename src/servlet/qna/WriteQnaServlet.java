package servlet.qna;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.notice.NoticeDAO;
import model.notice.NoticeVO;
import model.qna.QnaDAO;
import model.qna.QnaVO;

/**
 * Servlet implementation class ListQNAServlet
 */
@WebServlet("/qna_write")
public class WriteQnaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteQnaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		RequestDispatcher rd = request.getRequestDispatcher("qa/qawrite.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String savePath = request.getServletContext().getRealPath("\\upload\\qna");
		int sizeLimit = 1024*1024*20;
		MultipartRequest multi = new MultipartRequest(request,savePath,sizeLimit, "utf-8", new DefaultFileRenamePolicy());
		QnaVO vo = new QnaVO();
		String notice = null;
		System.out.println("chk="+multi.getParameter("chK"));
		if(multi.getParameter("chk") != null) {
			notice = multi.getParameter("chk");
			if(notice.equals("chkok")) {
				NoticeDAO ndao = new NoticeDAO();
				NoticeVO vo2 = new NoticeVO();
				vo2.setAuthor(multi.getParameter("author"));
				vo2.setContent(multi.getParameter("content"));
				vo2.setTitle(multi.getParameter("title"));
				vo2.setFilename(multi.getFilesystemName("filename"));
				vo2.setFilepath(savePath+"\\"+multi.getFilesystemName("filename"));
				int row2 = ndao.insertNotice(vo2);
			}
		}
		vo.setCategory(multi.getParameter("category"));
		System.out.println(multi.getParameter("category"));
		vo.setAuthor(multi.getParameter("author"));
		vo.setContent(multi.getParameter("content"));
		vo.setTitle(multi.getParameter("title"));
		vo.setFilename(multi.getFilesystemName("filename"));
		vo.setPhone(multi.getParameter("hp1") + multi.getParameter("hp2") + multi.getParameter("hp3"));
		System.out.println(vo.getFilename());
		vo.setFilepath(savePath+"\\"+multi.getFilesystemName("filename"));
		System.out.println(vo.getFilepath());
		QnaDAO dao = new QnaDAO();
		int row = dao.insertQna(vo);
		if(row == 0) {
			request.setAttribute("result", "failed");
		}
		else {
			request.setAttribute("result", "success");
		}
		RequestDispatcher rd = request.getRequestDispatcher("qa/qawrite.jsp");
		rd.forward(request, response);
	}

}
