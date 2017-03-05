package student.studio.mapreduce;

import static edu.wustl.cse231s.rice.classic.HabaneroClassic.launchHabaneroApp;

import java.util.List;
import java.util.Map;

import edu.wustl.cse231s.mapreduce.MapReduceFramework;
import edu.wustl.cse231s.mapreduce.apps.friends.Account;
import edu.wustl.cse231s.mapreduce.apps.friends.AccountId;
import edu.wustl.cse231s.mapreduce.apps.friends.DatabaseUtils;
import edu.wustl.cse231s.mapreduce.apps.friends.MutualFriendsPrintUtils;
import edu.wustl.cse231s.util.OrderedPair;
import instructor.solution.mapreduce.InstructorUtils;
import student.solution.mapreduce.apps.friends.MutualFriendsMapper;
import student.solution.mapreduce.apps.friends.MutualFriendsReducer;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class TestMutualFriendsApplicationWithInstructorFramework {
	public static void main(String[] args) {
		Account[] S = DatabaseUtils.createAccounts(
				/* A-> */"BCD", 
				/* B-> */"ACDE",
				/* C-> */"ABDE", 
				/* D-> */"ABCE", 
				/* E-> */"BCD"
		);

		MutualFriendsMapper mapper = new MutualFriendsMapper();
		MutualFriendsReducer reducer = new MutualFriendsReducer();
		MapReduceFramework<Account, OrderedPair<AccountId>, List<AccountId>> mapReduceFramework = InstructorUtils.newSimpleMapReduceFramework();
		launchHabaneroApp(() -> {
			System.out.println(mapReduceFramework);
			MutualFriendsPrintUtils.printS(S);
			Map<OrderedPair<AccountId>, List<AccountId>> g_of_f_of_S = mapReduceFramework.mapReduceAll(S, mapper, reducer);
			MutualFriendsPrintUtils.printGofFofS(g_of_f_of_S);
		});
	}
}
