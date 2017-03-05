package student.solution.mapreduce.apps.friends;

import static edu.wustl.cse231s.rice.classic.HabaneroClassic.newReducerFinishAccumulator;

import java.util.List;

import edu.wustl.cse231s.NotYetImplementedException;
import edu.wustl.cse231s.mapreduce.FinishAccumulatorBasedReducer;
import edu.wustl.cse231s.mapreduce.apps.friends.AccountId;
import edu.wustl.cse231s.mapreduce.apps.friends.ListIntersectionReducer;
import edu.wustl.cse231s.rice.classic.contrib.api.FinishAccumulator;
import edu.wustl.cse231s.rice.classic.contrib.api.Reducer;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class MutualFriendsReducer implements FinishAccumulatorBasedReducer<List<AccountId>> {

	@Override
	public FinishAccumulator<List<AccountId>> createAccumulator() {
		//HINT: investigate ListIntersectionReducer
		throw new NotYetImplementedException();
	}
}
