import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class PerfectNumber {

	private enum State {
		DEFICIENT, PERFECT, ABUNDANT
	}
	
	public static Set<Integer> divisors(int n) {
		Set<Integer> divisors = new HashSet<>();
		for(int i = 1; i < n; i++) {
			int div = n % i;
			if(div == 0) {
				divisors.add(i);
			}
		}
		return divisors;
	}
	
	public static State process(int n) {
		Set<Integer> divisors = divisors(n);
		int sum = 0;
		for(int i : divisors) {
			sum = sum + i;
		}
		
		if(n == sum) {
			return State.PERFECT;
		} else if(n > sum) {
			return State.DEFICIENT;
		}  else {
			return State.ABUNDANT;
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Viktors Stepičevs 4. grupa 191RDB100\n");
		int n;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("Enter a positive integer: ");
			while(!sc.hasNextInt()) {
				System.out.println("Input error! Only numbers are eligible for input!");
				sc.next();
			}
			n = sc.nextInt();
		} while(n <= 0);
		System.out.println("Classification: " + process(n));
	
		sc.close();
	}

}
