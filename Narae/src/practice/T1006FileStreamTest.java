package practice;

import java.io.FileInputStream;
import java.io.IOException;

public class T1006FileStreamTest {
	public static void main(String[] args) {
		
		FileInputStream fis = null;
		try {
			
			fis = new FileInputStream("d:/D_Other/test2.txt");
			
			int data =0;
			
			while((data = fis.read()) != -1) {
				System.out.print((char) data);
			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
