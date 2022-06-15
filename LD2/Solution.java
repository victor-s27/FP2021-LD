
public class Solution {

	static boolean palindrome(String s) {
		String text = s.toLowerCase();
		if (text.isEmpty() || text.length() == 1) {
			return true;
		} else if (text.charAt(0) == text.charAt(text.length() - 1)) {
			return palindrome(text.substring(1, text.length() - 1));
		}
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println("Viktors Stepičevs 4. grupa 191RDB100\n"); // Task 0 
		System.out.println("Classification: " + palindrome("abcba"));
		System.out.println("Classification: " + palindrome("argentinamanitnegra"));
		System.out.println("Classification: " + palindrome("Sapalsarītadēdatīraslapas"));
		System.out.println("Classification: " + palindrome("abccb"));
		System.out.println("Classification: " + palindrome("stirna"));
	}

}
