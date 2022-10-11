package kr.or.ddit.basic.homework;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TulipsCopy2 {
   public static void main(String[] args) {
      
      
      FileInputStream fis = null;
      FileOutputStream fos = null;
      BufferedOutputStream bos = null; 
      
      try {
         fis = new FileInputStream("d:/D_Other/tulips.jpg");
         fos = new FileOutputStream("d:/D_Other/tulipsCopy.jpg");
         
         bos = new BufferedOutputStream(fos);
         
         int copy = 0;
         long start = System.currentTimeMillis();
         while((copy = fis.read())!=-1) {
            bos.write(copy);
         }
         long end = System.currentTimeMillis();
         
         bos.flush(); // 작업을 종료하기전에 버퍼에 남아있는 데이터를 모두 출력시킨다.
                  // (close시 자동호W출됨)
         System.out.println("작업 끝 ! 복사에 걸리는 시간 : " + (end-start));
         
      } catch (IOException ex) {
         ex.printStackTrace();
      } finally {
         
         try {
            fis.close();
            fos.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
      
   }
}