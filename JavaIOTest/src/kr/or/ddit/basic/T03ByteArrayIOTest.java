package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T03ByteArrayIOTest {
	
	public static void main(String[] args) throws IOException {
		
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		// 직접 복사하는 방법
//		outSrc = new byte[inSrc.length]; // 공간확보
//		for (int i = 0; i < inSrc.length; i++) {
//			outSrc[i] = inSrc[i];
//		}
		
		
		// arraycopy를 이용한 배열 복사하기
//		outSrc = new byte[inSrc.length];
//		System.arraycopy(inSrc, 0, outSrc, 0, inSrc.length);

		
		// 스트림 클래스 이용하기
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int data = 0; //읽어온 바이트 데이터를 저장할 변수
		
		// read() => byte단위로 데이터를 읽어와 int형으로 반환한다.
		//           더이상 읽어올 자료가 없으면 -1을 반환한다.
		while((data = bais.read()) != -1) {
			baos.write(data); // 데이터 출력하기
		}
		
		outSrc = baos.toByteArray();
		
		bais.close();
		baos.close();
		
		System.out.println("inSrc => " + Arrays.toString(inSrc));
		System.out.println("outSrc => " + Arrays.toString(outSrc));
		
		
	}
}
