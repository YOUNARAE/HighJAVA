package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 자카르타 프로젝트의 fileupload 모듈을 이용한 파일업로드 예제
 */
@WebServlet("/test/upload.do")
public class UploadController extends HttpServlet{

	private static final String UPLOAD_DIR = "upload_files";
	// 메모리 임계크기(이 크기가 넘어가면 레파지토리)
	private static final int MEMORY_THRESHOLD = 1024*1024*3; 
	// 파일 1개당 최대 크기 
	private static final long MAX_FILE_SIZE = 1024*1024*40;
	// 요청 파일 최대 크기
	private static final long MAX_REQUEST_SIZE = 1024*1024*50;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/*System.out.println("===========================================================");

		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		
		String readLine = "";
		while ((readLine = br.readLine()) != null) {
			System.out.println(readLine);
		}
		
		System.out.println("===========================================================");
		*/
		// Multipart Parsing 전에 파라미터 정보 조회해보기
		System.out.println("Multipart Parsing 전 => " + req.getParameter("sender"));
		
		if(ServletFileUpload.isMultipartContent(req)) {
			
			//폼필드 데이터 저장용
			Map<String, String> formMap = new HashMap<String, String>();
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(MEMORY_THRESHOLD);
			factory.setRepository(new File("d:/D_Other/"));

			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			
			fileUpload.setFileSizeMax(MAX_FILE_SIZE);
			fileUpload.setSizeMax(MAX_REQUEST_SIZE);
			
			// 웹애플리케이션 루트 디렉토리 기준으로 업로드 경로 설정하기
			String uploadPath = getServletContext().getRealPath("/")
					    +File.separator + UPLOAD_DIR;
			
			File uploadDir = new File(uploadPath);
			if(!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			
			try {
				List<FileItem> itemList = fileUpload.parseRequest(req);
				
				if(itemList != null && itemList.size() > 0) {
					for(FileItem item : itemList) {
						if(!item.isFormField() ) { // 파일인 경우
							String fileName = item.getName();
							String filePath = uploadPath + File.separator + fileName;
							
							System.out.println(filePath);
							
							File storeFile = new File(filePath);
							
							item.write(storeFile); // 업로드 파일 저장
							System.out.println("업로드된 파일명 : " + fileName);
						} else { // 폼필드 인 경우...
							formMap.put(item.getFieldName(), item.getString("UTF-8"));
						}
					}
				}
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			// Multipart Parsing 후에 파라미터 정보 조회해보기
			System.out.println("Multipart Parsing 후 =>" + formMap.get("sender"));
			
		}
	}
	
}
