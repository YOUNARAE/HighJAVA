package practice;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class T1006FileWriterTest {
	public static void main(String[] args) {
		
		InputStreamReader isr = new InputStreamReader(System.in);
		
		FileWriter fw = null;
		
		try {
			
			fw = new FileWriter("d:/D_Other/testChar2.txt");
			
			int data = 0;
			
			System.out.println("아무거나 입력하세요");
			
			// 콘솔에서 입할 때 입력의 끝을 나타내는 표시는 Ctrl + z키를 누르면 된다.
			while((data = isr.read()) != -1) {
				fw.write(data);
			}
			System.out.println("작업 끝...");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {			
			try {
				isr.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
}
