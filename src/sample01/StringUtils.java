package sample01;

public class StringUtils {

	public static String changeText(String text, int position, char ch) {
		if (text == null) {
			return "";
		}
		if (text.isBlank()) {
			return "";
		}
		if (position > text.length() - 1) {
			return text;
		}
		
		char[] array = text.toCharArray();
		array[position] = ch;
		
		return String.valueOf(array);
	}
	
	public static void main(String[] args) {
		
		System.out.println(changeText("홍길동", 0, '*'));
		System.out.println(changeText("홍길동", 1, '*'));
		System.out.println(changeText("홍길동", 2, '*'));
		System.out.println(changeText("홍길동", 3, '*'));
		
	}
}
