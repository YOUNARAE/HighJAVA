package JavaExam;

public class HandleException {
	public static void main(String[] argv) {
		new HandleException().test();
	}

	private void test() {
		try {
			new Person("Kim", -10);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("execute finally block");
		}
	}

	public class Person {
		private String name;
		private int age;

		public Person(String name, int age) throws Exception {
			if (age < 0) {
				throw new IllegalParamException("Invalid input age : " + age);
			}
		}

		public String getName() {
			return name;
		}

		public int getAge() {
			return age;
		}
	}

	public class IllegalParamException extends RuntimeException {
		public IllegalParamException(String msg) {
			super(msg);
		}
	}
}
