package kr.or.ddit.basic;

import java.util.Random;
import javax.swing.JOptionPane;

public class randomGame {

	public static boolean inputCheck = false;
	
	public static void main(String[] args) {
		
		Thread th1 = new DataPut();
		th1.start();
		
		Thread th2 = new CountTime();
		th2.start();
	}
}

class DataPut extends Thread{
	
	@Override
	public void run() {
		
		Random random = new Random();
		String[] aiArray = {"가위","바위","보"};
		int num = random.nextInt(3);
		
		String str = JOptionPane.showInputDialog("가위 바위 보 중에 한 가지를 입력하세요");
		
		System.out.println("입력한 것은 " + str + "입니다");
		String result = "";
		
		if(str.equals(aiArray[num])) {
			result = "둘다 비겼음";
		} else if(str.equals("가위")&&aiArray[num].equals("바위")||
				str.equals("보")&&aiArray[num].equals("가위")||
				str.equals("바위")&&aiArray[num].equals("보")) 
		{
			result = "당신은 졌습니다";
		} else {
			result = "당신이 이겼습니다";
		}
		System.out.println(result);
		
		randomGame.inputCheck = true;
	}
	
}

/**
 * 5초 카운드 다운하는 쓰레드, 5초안에 입력 안하면 강제 종료시켜야한다.
 */
class CountTime extends Thread{
	
	@Override
	public void run() {
		
		for(int i=5; i>=1; i--) {
			
			if(randomGame.inputCheck == true){
				return; //메소드를 빠져나오게 해서 run메소드를 종료시키는 것임
			}
				
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("5초가 지났습니다. 당신은 게임에서 졌다. 프로그램을 종료합니다");
		System.exit(0);
		
	}
}
