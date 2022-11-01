package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
/**
 * 서블릿 3부터 지원하는 Part 인터페이스를 이용한 파일업로드 예제
 */
@MultipartConfig
@WebServlet("/test/upload2.do")
public class UploadController2 extends HttpServlet{
	private static final String UPLOAD_DIR = "upload_files";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Multipart Parsing 전에 파라미터 정보 조회해보기
		System.out.println("Multipart Parsing 전 => " + req.getParameter("sender"));
		
		// 웹애플리케이션 루트 디렉토리 기준으로 업로드 경로 설정하기
		String uploadPath = "d:/D_Other/" + UPLOAD_DIR;
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		try {
			String fileName = "";
			for(Part part : req.getParts()) {
				
				System.out.println(part.getHeader("content-disposition"));
				
				fileName = getFileName(part);
				
				if(fileName !=  null && !fileName.equals("")) {
					// 폼필드가 아니거나 파일이 비어있지 않은 경우
					part.write(uploadPath + File.separator + fileName); // 파일 저장
					System.out.println(uploadPath + File.separator + fileName + " => 저장완료!!!");
				}
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

	/**
	 * Part 객체를 파싱하여 파일이름 추출하기
	 * @param part 파싱할 Part객체
	 * @return 파일명 : 존재하지 않으면 NULL 리턴함(폼필드인 경우)
	 */
	private String getFileName(Part part) {
		
		/*
		 * Content-Disposition 헤더에 대하여...
		 * 
		 * multipart body를 위해 사용되는 경우 ex)파일 업로드
		 * 
		 * Content-Disposition : form-data
		 * Content-Disposition : form-data; name="fieldName"
		 * Content-Disposition : form-data; name="fieldName"; 
		 *                       filename="a.jpg"
		 *                       
		 */
		for(String content : part.getHeader("Content-Disposition").split(";")) {
			if(content.trim().startsWith("filename")) {
				return content.substring(content.indexOf("=")+1).trim().replace("\"", "");
			}
		}
		return null; // filename이 존재하지 않을 경우...(폼필드)
	}
}
