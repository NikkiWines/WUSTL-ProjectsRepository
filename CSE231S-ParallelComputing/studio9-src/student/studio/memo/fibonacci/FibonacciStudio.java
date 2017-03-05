package student.studio.memo.fibonacci;

import static edu.wustl.cse231s.rice.classic.HabaneroClassic.dumpStatistics;
import static edu.wustl.cse231s.rice.classic.HabaneroClassic.launchHabaneroApp;

import edu.rice.hj.runtime.config.HjSystemProperty;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class FibonacciStudio {
	private static long iterativeDynamicFibonacci(int n) {
		if (n == 0) {
			return 0L;
		} else {
			long prev = 0;
			long curr = 1;
			for (int i = 1; i < n; i++) {
				long next = prev + curr;
				prev = curr;
				curr = next;
			}
			return curr;
		}
	}
	
	public static void main(String[] args) throws Exception {
		int n = 35;
		
		long truthAndBeautyValue = iterativeDynamicFibonacci(n);
		HjSystemProperty.abstractMetrics.set(true);
		launchHabaneroApp(() -> {
			long value = FibonacciCalculator.FUTURE_MEMOS.fibonacci(n);
			if( value != truthAndBeautyValue ) {
				throw new RuntimeException( value + "!="+ truthAndBeautyValue );
			} else {
				System.out.println( "SUCCESS: " + value + "==" + truthAndBeautyValue);
			}
		}, () -> {
			dumpStatistics();
		});
	}
}
