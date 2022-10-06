package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T04ByteArrayIOTest {
	
	public static void main(String[] args) throws IOException {
		
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		byte[] temp = new byte[4]; // 자료를 읽을 때 사용할 배열
		
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int readBytes = 0;
		
		while((readBytes = bais.read(temp)) != -1) { //임시 이용공간을 이용해 데이터를 넣을때에는 임시 저장공간이 몇 바이트를 읽었는지 바이트 수를 반환받는 것이다
			baos.write(temp, 0, readBytes);

			System.out.println("temp : " + Arrays.toString(temp));
		}
		
		outSrc = baos.toByteArray(); //아웃소스 공간에 저장된 
	
		System.out.println("inSrc => " + Arrays.toString(inSrc));
		System.out.println("outSrc => " + Arrays.toString(outSrc));
		
	}
}
