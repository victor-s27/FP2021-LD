import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class PerfectNumber2 {

	private enum State {
		DEFICIENT, PERFECT, ABUNDANT
	}
	
	public static Set<Integer> divisors(int n) {
		Set<Integer> divisors = new HashSet<>();
		IntStream
			.iterate(1, i -> i + 1)
			.limit(n / 2)
			.filter(i -> n % i == 0)
			.forEach(i -> {divisors.add(i);});
		return divisors;
	}
	
	public static State process(int n) {
		Set<Integer> divisors = divisors(n);
		int sum = divisors.stream().mapToInt(Integer::intValue).sum();
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
		System.out.println("Classification: " + process(6)); //sum = 6 => PERFECT
		System.out.println("Classification: " + process(11)); //sum = 1 => DEFICIENT
		System.out.println("Classification: " + process(12)); //sum = 15 => ABUNDANT
		System.out.println("Classification: " + process(28)); //sum = 28 => PERFECT
	}

}
