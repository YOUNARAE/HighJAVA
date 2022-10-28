package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T12ImageServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//이미지라서 인코딩 안해도 됨
		resp.setContentType("image/jpg"); // 컨텐츠 타입 설정
		
		ServletOutputStream out = resp.getOutputStream();
		
		FileInputStream fis = 
				new FileInputStream("d:/D_Other/br5.jpg");
		
		BufferedInputStream bis = new BufferedInputStream(fis);
		
		BufferedOutputStream bos = new BufferedOutputStream(out);
		
		int data = 0;
		
		while((data=bis.read()) != -1) {
			bos.write(data);
		}

		bis.close();
		bos.close();
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
