package kr.or.ddit.basic.homework;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap.KeySetView;

public class PhoneBook {
   private Scanner scan;
   private Map<String, Phone> phoneBookMap; //키 값에 맞는 밸류값을 폰에서 가져온다
   
   public PhoneBook() {
      scan = new Scanner(System.in);
      phoneBookMap = new HashMap<String, Phone>();
   }

   // 메뉴를 출력하는 메서드
   public void displayMenu(){
      System.out.println();
      System.out.println("메뉴를 선택하세요.");
      System.out.println(" 1. 전화번호 등록");
      System.out.println(" 2. 전화번호 수정");
      System.out.println(" 3. 전화번호 삭제");
      System.out.println(" 4. 전화번호 검색");
      System.out.println(" 5. 전화번호 전체 출력");
      System.out.println(" 0. 프로그램 종료");
      System.out.print(" 번호입력 >> ");      
   }
   
   // 프로그램을 시작하는 메서드
   public void phoneBookStart(){
      System.out.println("===============================================");
      System.out.println("                                    전화번호 관리 프로그램");
      System.out.println("===============================================");
      
      while(true){
         
         displayMenu();  // 메뉴 출력
         
         int menuNum = scan.nextInt();   // 메뉴 번호 입력
         
         switch(menuNum){
            case 1 : insert();      // 등록
               break;
            case 2 : update();      // 수정
               break;
            case 3 : delete();      // 삭제
               break;
            case 4 : search();      // 검색
               break;
            case 5 : displayAll();   // 전체 출력
               break;
            case 0 :
               System.out.println("프로그램을 종료합니다...");
               return;
            default :
               System.out.println("잘못 입력했습니다. 다시입력하세요.");
         } // switch문
      } // while문
   }
   
   /**
    * 이름을 이용한 전화번호 정보 검색하기
    */
   private void search() {
      
      System.out.println();
      System.out.println("전화번호 정보를 검색할 이름을 입력하세요");
      
      System.out.println("이름 >> ");
      String name = scan.next();
      
      Phone p = phoneBookMap.get(name);
      if(p == null) {
         System.out.println(name + "씨의 전화번호 정보는 존재하지 않습니다");
      } else {
         System.out.println( name + "씨의 전화번호 정보" );
         System.out.println( "이    름 : " + p.getName() );
         System.out.println( "전화번호 : " + p.getTel() );
         System.out.println( "주    소 : " + p.getAddr() );
      }
      
   }

   /**
    * 전체 전화번호 정보 출력하는 메서드
    */
   private void displayAll() {
      System.out.println("======================================");
      System.out.println("이름\t전화번호\t\t주소");
      System.out.println("======================================");
      

      ObjectInputStream ois = null;
      
      try {
         
         ois = new ObjectInputStream(
               new BufferedInputStream(
                  new FileInputStream("d:/D_Other/phoneBook.bin")));
         
         Set<String> keySet = phoneBookMap.keySet();
         
//         if(keySet.size() == 0 ) {
//            System.out.println("등록된 전화번호가 존재하지 않습니다.");
//         } else {
//            Iterator<String> it = keySet.iterator();
//            
//            int cnt = 0;
//            while(it.hasNext()) {
//               cnt++;
//               String name = it.next();
//               Phone p = phoneBookMap.get(name);
//               
//               System.out.println(" " + cnt + "\t" + p.getName() 
//                                  + "\t" + p.getTel() + "\t" + p.getAddr());
//            }
//         }
         
         // 읽기 작업
         Object obj = null;
         while((obj = ois.readObject()) != null) {
            // 읽어온 데이터를 원래의 객체형으로 변환 후 사용한다.
            Phone p = (Phone) obj;            
            for (int i=0;i<=keySet.size();i++) {
            	System.out.println(p.getName() + "\t" + p.getTel() + "\t" + p.getAddr());
			}
         }
         
      } catch (IOException ex) {
         // 더 이상 읽어올 객체가 없으면 예외발생함.
          System.out.println("--------------------------------------");
         //ex.printStackTrace();
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      } finally {
         try {
            ois.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
      
   }
   
   /**
    * 전화번호 정보를 삭제하는 메서드
    */
   private void delete() {
      
      System.out.println();
      System.out.println("삭제할 전화번호 정보를 입력하세요");
      
      System.out.println("이름 >> ");
      String name = scan.next(); 
      
      // remove(key) => 삭제 성공하면 삭제된 value값을 반환하고 실패하면 null을 리턴함.
      if(phoneBookMap.remove(name) == null ) {
         System.out.println(name + "씨는 등록된 사람이 아닙니다.");
      } else {
         System.out.println(name + "씨 정보를 삭제했습니다.");
      }
      System.out.println("삭제 작업 완료...");
   }

   /**
    * 기존 전화번호 정보를 수정하는 메서드
    */
   private void update() {
      
      System.out.println();
      System.out.println("변경할 전화번호 정보를 입력하세요");
      System.out.println("이름 >> ");
      String name = scan.next();
      
      // 이미 등록된 사람인지 체크하기
      // get()메서드로 값을 가져올 때 가져올 자료가 없으면 null을 리턴한다.
      if( phoneBookMap.get(name) == null ) { //인서트와 반대로 자료가 있어야 변경할 수 있기때문에 널값이어야한다
         System.out.println(name + "씨는 등록되지 않은 사람입니다.");
         return; // 메서드 종료.
      }
      
      System.out.println("전화번호 >> ");
      String tel = scan.next(); 
      
      System.out.println("주소 >> ");
      scan.nextLine(); 
   
      String addr = scan.nextLine(); //공백까지 포함해서 한 줄을 다 받아오는 것이 nextLine
      
      Phone p = new Phone(name, tel, addr);
      
      phoneBookMap.put(name, p); //name이 이미 존재하는 키값이라서 기존에 있는 소스를 그대로 쓰면서 수정이 가능한 것이다
      System.out.println(name + "씨 정보 수정 완료 ...");
   }

   /**
    * 새로운 전화번호 정보를 등록하는 메서드
    * (이미 등록된 사람은 등록되지 않는다.)
    */
   private void insert() {
      System.out.println();
      System.out.println("새롭게 등록할 전화번호 정보를 입력하세요");
      System.out.println("이름 >> ");
      String name = scan.next(); //이름을 스캔으로 받고 나서 이름이 중복체크 한번 확인해야함
      
      // 이미 등록된 사람인지 체크하기
      // get()메서드로 값을 가져올 때 가져올 자료가 없으면 null을 리턴한다.
      if( phoneBookMap.get(name) != null ) { //널이면 이미 넣으려고 하는 데이터와 같은 데이터 넣어진 자료가 없다는 것이다
         System.out.println(name + "씨는 이미 등록된 사람입니다.");
         return; // 메서드 종료.
      }
      
      System.out.println("전화번호 >> ");
      String tel = scan.next(); //공백,탭,스페이스 구분으로 뜯어서 받아올 수 있는 것이 next
      
      System.out.println("주소 >> ");
      scan.nextLine(); //버그(오류를) 입력 버터에 남아있는 엔터키값까지를 읽어와버리는 역할을 수행한다. 그래서 넣어줌
   
      String addr = scan.nextLine(); //공백까지 포함해서 한 줄을 다 받아오는 것이 nextLine
      
      Phone p = new Phone(name, tel, addr);
      
      phoneBookMap.put(name, p);
      System.out.println(name + "씨 정보 등록 완료 ...");
      
      ObjectOutputStream oos = null;
      
      try {
         oos = new ObjectOutputStream(
               new BufferedOutputStream(
                  new FileOutputStream("d:/D_Other/phoneBook.bin")));
         
         Set<String> keySet = phoneBookMap.keySet();
         Iterator<String> it = keySet.iterator();
         
         while(it.hasNext()) {
            String strName = it.next();
            Phone tmp = phoneBookMap.get(strName);
            oos.writeObject(tmp);
         }
         // 쓰기 작업
         // oos.writeObject(p); // 직렬화
         
      } catch (IOException ex) {
         ex.printStackTrace();
      } finally {
         try {
            oos.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
      
   }

   public static void main(String[] args) {
      new PhoneBook().phoneBookStart();
   }
}

/**
 * 전화번호 정보를 저장하기 위한 VO 클래스 , VO(Value Object) : 정보나 값을 저장하기 위한 객체들
 */
class Phone implements Serializable { 
   private String name; //이름
   private String tel; //전화번호
   private String addr; //주소
   private int as;
   
   public Phone(String name, String tel, String addr) {
      super();
      this.name = name;
      this.tel = tel;
      this.addr = addr;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getTel() {
      return tel;
   }

   public void setTel(String tel) {
      this.tel = tel;
   }

   public String getAddr() {
      return addr;
   }

   public void setAddr(String addr) {
      this.addr = addr;
   }

   @Override
   public String toString() {
      return "Phone [name=" + name + ", tel=" + tel + ", addr=" + addr + "]";
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((addr == null) ? 0 : addr.hashCode());
      result = prime * result + ((name == null) ? 0 : name.hashCode());
      result = prime * result + ((tel == null) ? 0 : tel.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      Phone other = (Phone) obj;
      if (addr == null) {
         if (other.addr != null)
            return false;
      } else if (!addr.equals(other.addr))
         return false;
      if (name == null) {
         if (other.name != null)
            return false;
      } else if (!name.equals(other.name))
         return false;
      if (tel == null) {
         if (other.tel != null)
            return false;
      } else if (!tel.equals(other.tel))
         return false;
      return true;
   }
}

