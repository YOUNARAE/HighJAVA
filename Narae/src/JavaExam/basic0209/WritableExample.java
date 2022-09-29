package JavaExam.basic0209;

public class WritableExample {
	public static void main(String[] args) {
		//여기에 들어갈 내용
		Writable writable = new Writable() {
			
			@Override
			public void write(String text) {
				System.out.println(text);
			}
		};
		writable.write("이 글을 콘솔에 출력합니다.");
	}
}

