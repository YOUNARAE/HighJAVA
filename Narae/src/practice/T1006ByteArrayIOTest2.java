package practice;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T1006ByteArrayIOTest2 {
	public static void main(String[] args) throws IOException {
	
		
			byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
			byte[] outSrc = null;
			byte[] temp = new byte[4]; //자료 읽을 때 사용할 배열
			
			ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			int readBytes = 0; //파라미터로 넣어준 데이터의 갯수를 읽은 값을 리턴한다.
			
			while((readBytes = bais.read(temp)) != -1) {
				baos.write(temp, 0, readBytes);
			
				System.out.println("temp : " + Arrays.toString(temp));
			}
			
			outSrc = baos.toByteArray();
			
			System.out.println("inSrc => " + Arrays.toString(inSrc));
			System.out.println("inSrc => " + Arrays.toString(outSrc));
			
	}
}
