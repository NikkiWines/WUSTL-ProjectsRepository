package student.solution.mapreduce.apps.friends;

import java.util.List;

import edu.wustl.cse231s.NotYetImplementedException;
import edu.wustl.cse231s.mapreduce.MapContext;
import edu.wustl.cse231s.mapreduce.Mapper;
import edu.wustl.cse231s.mapreduce.apps.friends.Account;
import edu.wustl.cse231s.mapreduce.apps.friends.AccountId;
import edu.wustl.cse231s.util.OrderedPair;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class MutualFriendsMapper implements Mapper<Account, OrderedPair<AccountId>, List<AccountId>> {
	@Override
	public void map(MapContext<OrderedPair<AccountId>, List<AccountId>> context, Account account) {
		throw new NotYetImplementedException();
	}
}
