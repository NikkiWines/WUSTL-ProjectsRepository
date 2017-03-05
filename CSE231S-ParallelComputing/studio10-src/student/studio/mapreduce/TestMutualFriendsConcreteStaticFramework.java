package student.studio.mapreduce;

import static edu.wustl.cse231s.rice.classic.HabaneroClassic.launchHabaneroApp;

import java.util.List;
import java.util.Map;

import edu.wustl.cse231s.mapreduce.apps.friends.Account;
import edu.wustl.cse231s.mapreduce.apps.friends.AccountId;
import edu.wustl.cse231s.mapreduce.apps.friends.DatabaseUtils;
import edu.wustl.cse231s.mapreduce.apps.friends.MutualFriendsPrintUtils;
import edu.wustl.cse231s.util.OrderedPair;
import student.solution.mapreduce.apps.friends.MutualFriendsMapper;
import student.solution.mapreduce.apps.friends.MutualFriendsReducer;
import student.studio.mapreduce.concrete.friends.MutualFriendsConcreteStaticMapReduce;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class TestMutualFriendsConcreteStaticFramework {
	public static void main(String[] args) {
		launchHabaneroApp(() -> {
			Account[] S = DatabaseUtils.createAccounts(
					/* A-> */"BCD", 
					/* B-> */"ACDE",
					/* C-> */"ABDE", 
					/* D-> */"ABCE", 
					/* E-> */"BCD");
			MutualFriendsMapper mapper = new MutualFriendsMapper();
			MutualFriendsReducer reducer = new MutualFriendsReducer();
			MutualFriendsPrintUtils.printS(S);
			Map<OrderedPair<AccountId>, List<AccountId>> g_of_f_of_S = MutualFriendsConcreteStaticMapReduce.mapReduceAll(S, mapper, reducer);
			MutualFriendsPrintUtils.printGofFofS(g_of_f_of_S);
		});
	}

}
