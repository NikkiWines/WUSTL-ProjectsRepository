package student.studio.memo.fibonacci;

import static edu.wustl.cse231s.rice.classic.HabaneroClassic.doWork;
import static edu.wustl.cse231s.rice.classic.HabaneroClassic.future;

import edu.rice.hj.api.HjFuture;
import edu.rice.hj.api.SuspendableException;
import edu.wustl.cse231s.NotYetImplementedException;
import edu.wustl.cse231s.goldenratio.GoldenRatioUtils;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public enum FibonacciCalculator {
	SEQUENTIAL_RECURSIVE { // WORK: O(Phi^n)
		@Override
		public long fibonacci(int n) {
			doWork(1);
			if (n <= 1) {
				return (long)n;
			} else {
				return fibonacci(n - 1) + fibonacci(n - 2);
			}
		}
	},
	
	PARALLEL_RECURSIVE { // WORK: O(2^n), CPL: O(n)
		@Override
		public long fibonacci(int n) throws SuspendableException {
			doWork(1);
			if (n <= 1) {
				return (long)n;
			} else {				
				HjFuture<Long> nMinus1Future = future(() -> {
					return fibonacci(n - 1);
				});
				HjFuture<Long> nMinus2Future = future(() -> {
					return fibonacci(n - 2);
				});
				
				return nMinus1Future.get() + nMinus2Future.get();
			}
		}
	},

	PARALLEL_RECURSIVE_WITH_THRESHOLD {
		private long fibonacciWithThreshold(int n, int threshold) throws SuspendableException {
			if( n <= threshold ) {
				return SEQUENTIAL_RECURSIVE.fibonacci(n);
			} else {
				doWork(1);
				HjFuture<Long> nMinus1Future = future(() -> {
					return fibonacciWithThreshold(n - 1, threshold);
				});
				HjFuture<Long> nMinus2Future = future(() -> {
					return fibonacciWithThreshold(n - 2, threshold);
				});
				
				return nMinus1Future.get() + nMinus2Future.get();
			}
		}
		@Override
		public long fibonacci(int n) throws SuspendableException {
			return fibonacciWithThreshold(n, Math.max(n-4, 1));
		}
	},
	
	VALUE_MEMOS { // WORK: O(n)
		private long fibonacciMemo(long[] memos, int n) {
			if (n <= 1) {
				return (long)n;
			} else {
				if( memos[n] > 0L ) {
					//pass
				} else {
					doWork(1);
					memos[n-1] = fibonacciMemo(memos, n - 1);
					memos[n-2] = fibonacciMemo(memos, n - 2);
					memos[n] = memos[n-1] + memos[n-2];
				}
				return memos[n];
			}
		}
		@Override
		public long fibonacci(int n) {
			long[] memos = new long[n+1];
			return fibonacciMemo(memos, n);
		}
	},
	
	FUTURE_MEMOS {
		@Override
		public long fibonacci(int n) throws SuspendableException {
			// TODO: implement fibonacci with HjFuture memos 
			// check out PascalsTriangleWithFuture for an example
			throw new NotYetImplementedException();
		}
	},

	ITERATIVE_DYNAMIC { // WORK: O(n)
		@Override
		public long fibonacci(int n) {
			doWork(1);
			if (n == 0) {
				return 0L;
			} else {
				long prev = 0;
				long curr = 1;
				for (int i = 1; i < n; i++) {
					doWork(1);
					long next = prev + curr;
					prev = curr;
					curr = next;
				}
				return curr;
			}
		}
	},
	
	RECURSIVE_DYNAMIC { // WORK: O(n)
		private long fibonacciKernel(long a, long b, int n) {
			doWork(1);
			if (n == 0) {
				return a;
			} else {
				return fibonacciKernel(b, a + b, n - 1);
			}
		}

		@Override
		public long fibonacci(int n) {
			return fibonacciKernel(0L, 1L, n);
		}
	},

	LINEAR_RECURRENCE {
		@Override
		public long fibonacci(int n) {
			doWork(1);
			if (n == 0) {
				return 0L;
			} else if (n == 1) {
				return 1L;
			} else if (n == 2) {
				return 1L;
			} else {
				boolean isOdd = (n & 0x1) == 1;
				if (isOdd) {
					int k = (n + 1) / 2;
					long fib_k = fibonacci(k);
					long fib_k_minus_1 = fibonacci(k - 1);
					return (fib_k * fib_k) + (fib_k_minus_1 * fib_k_minus_1);
				} else {
					int k = n / 2;
					long fib_k = fibonacci(k);
					long fib_k_minus_1 = fibonacci(k - 1);
					return ((2 * fib_k_minus_1) + fib_k) * fib_k;
				}
			}
		}
	},

	ROUND_PHI_TO_THE_N_OVER_SQRT_5 {
		@Override
		public long fibonacci(int n) {
			doWork(1); // what is the implementation of Math.pow()?
			return (long) Math.round(Math.pow(GoldenRatioUtils.PHI, n) / Math.sqrt(5));
		}
	};

	public abstract long fibonacci(int n) throws SuspendableException;
}
