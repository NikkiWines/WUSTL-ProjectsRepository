package student.studio.quicksort;

import java.util.Random;

import static edu.rice.hj.Module0.newPoint;
import edu.rice.hj.api.HjPoint;

/**
 * modified from
 * http://www.cs.rice.edu/~vs3/hjlib/code/course-materials/demo-files/Quicksort.java
 * 
 * Parallel Quicksort program. partition() has not been parallelized.
 *
 * @author Vivek Sarkar (vsarkar@rice.edu)
 */
public class PartitionUtils {
	;
    private static HjPoint partition(final int[] A, final int M, final int N) {
        int I;
        int storeIndex = M;

        final Random rand = new Random();
        final int pivot = M + rand.nextInt(N - M + 1);
        final int pivotValue = A[pivot];
        exchange(A, pivot, N);

        for (I = M; I < N; I++) {
            // Only count comparison with pivot value in abstract execution metrics
            if (A[I] <= pivotValue) {
                exchange(A, I, storeIndex);
                storeIndex++;
            }
        }

        exchange(A, storeIndex, N);

        if (storeIndex == N) {
            return newPoint(N, storeIndex - 1);
        } else if (storeIndex == M) {
            return newPoint(storeIndex + 1, M);
        }
        return newPoint(storeIndex + 1, storeIndex - 1);
    }

    private static void exchange(final int[] A, final int x, final int y) {
        int temp = A[x];
        A[x] = A[y];
        A[y] = temp;
    }


    public static PartitionLocation partitionSubArray(int[] array, int minInclusive, int maxInclusive) {
    	HjPoint pt = partition(array, minInclusive, maxInclusive);
		return new PartitionLocation(pt);
	}
}
