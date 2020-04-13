package util;

public class PageIndex 
{
	public static String pageList(int page, int totpage, String url, String addtag) 
	{
		String s_pre = "";    // Prev 저장 변수
		String s_idx = "";    // 번호 저장 변수
		String s_next = "";   // Next 저장 변수
		
		
//		String prev_html = "<i class='fa fa-angle-left'></i>";
//		String next_html = "<i class='fa fa-angle-right'></i>";
	
	  	// Prev 표시 부분
		if(page > 1) {
	  	s_pre = "<li><a href='" + ( page > 0 ? url + "?page=" + (page - 1) +"&": "?") + addtag + "'>이전</a></li>";
		}
	  	int maxview = 5;
	  	
	  	int startpage = page - (maxview / 2);

	  	
	  	if (totpage - startpage < maxview)
	  		startpage = totpage - (maxview - 1);
	  	if (startpage < 1)
	  		startpage = 1;
	  	
	  	// 번호 표시부분	
	  	for(int i = 0; i < maxview; i++, startpage++) 
	  	{
	  		if( startpage > totpage ) 
	  			break;
	  		
	  		if( startpage == page )
	  			s_idx += "<li><a>" + startpage + "</a></li>";
	  		else 
	  			s_idx += "<li><a href='" + url + "?page=" + startpage  +"&"+ addtag + "'>" + startpage + "</a></li>";
	  	}

	  	if(page < totpage) {
	  		
	  	s_next = "<li><a href='" + ( page < totpage ? url + "?page=" + (page + 1) +"&": "?") + addtag +  "'>다음</a></li>";
	  	}
	  	
	  	String outHtml = s_pre + s_idx + s_next;  // Html 문 조합
	  	return outHtml;
	}

}
