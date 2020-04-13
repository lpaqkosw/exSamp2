package servlet.notice;

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

/**
 * Servlet implementation class WriteNoticeServlet
 */
@WebServlet("/notice_write")
public class WriteNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteNoticeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		RequestDispatcher rd = request.getRequestDispatcher("notice/noticewrite.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String savePath = request.getServletContext().getRealPath("\\upload\\notice");
		int sizeLimit = 1024*1024*20;
		MultipartRequest multi = new MultipartRequest(request,savePath,sizeLimit, "utf-8", new DefaultFileRenamePolicy());
		NoticeVO vo = new NoticeVO();
		vo.setAuthor(multi.getParameter("author"));
		vo.setContent(multi.getParameter("content"));
		vo.setTitle(multi.getParameter("title"));
		vo.setFilename(multi.getFilesystemName("filename"));
		System.out.println(vo.getFilename());
		vo.setFilepath(savePath+"\\"+multi.getFilesystemName("filename"));
		System.out.println(vo.getFilepath());
		NoticeDAO dao = new NoticeDAO();
		int row = dao.insertNotice(vo);
		if(row == 0) {
			request.setAttribute("result", "failed");
		}
		else {
			request.setAttribute("result", "success");
		}
		RequestDispatcher rd = request.getRequestDispatcher("notice/noticewrite.jsp");
		rd.forward(request, response);
	}

}
