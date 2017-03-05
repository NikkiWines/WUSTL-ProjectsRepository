package student.solution.mapreduce;

import static edu.wustl.cse231s.rice.classic.HabaneroClassic.launchHabaneroApp;

import java.util.List;
import java.util.Map;

import edu.wustl.cse231s.mapreduce.MapReduceFramework;
import edu.wustl.cse231s.mapreduce.apps.friends.Account;
import edu.wustl.cse231s.mapreduce.apps.friends.AccountId;
import edu.wustl.cse231s.mapreduce.apps.friends.DatabaseUtils;
import edu.wustl.cse231s.mapreduce.apps.friends.MutualFriendsPrintUtils;
import edu.wustl.cse231s.util.OrderedPair;
import student.solution.mapreduce.apps.friends.MutualFriendsMapper;
import student.solution.mapreduce.apps.friends.MutualFriendsReducer;
import student.solution.mapreduce.matrix.MatrixMapReduceFramework;
import student.solution.mapreduce.simple.SimpleMapReduceFramework;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class TestMutualFriendsSolution {
	public static void main(String[] args) {
		Account[] S = DatabaseUtils.createAccounts(
				/* A-> */"BCD", 
				/* B-> */"ACDE",
				/* C-> */"ABDE", 
				/* D-> */"ABCE", 
				/* E-> */"BCD"
		);

		
		int taskCountForMatrixFramework = 2;
		MapReduceFramework<Account, OrderedPair<AccountId>, List<AccountId>> simpleFramework = new SimpleMapReduceFramework<>(); 
		MapReduceFramework<Account, OrderedPair<AccountId>, List<AccountId>> matrixFramework = new MatrixMapReduceFramework.Builder().mapTaskCount(taskCountForMatrixFramework).reduceTaskCount(taskCountForMatrixFramework).arrayListSizeHint(2).build();
		MapReduceFramework<Account, OrderedPair<AccountId>, List<AccountId>>[] mapReduceFrameworks = new MapReduceFramework[] {
				simpleFramework,
				matrixFramework
		};
		MutualFriendsMapper mapper = new MutualFriendsMapper();
		MutualFriendsReducer reducer = new MutualFriendsReducer();
		launchHabaneroApp(() -> {
			for (MapReduceFramework<Account, OrderedPair<AccountId>, List<AccountId>> mapReduceFramework : mapReduceFrameworks) {
				System.out.println(mapReduceFramework);
				MutualFriendsPrintUtils.printS(S);
				Map<OrderedPair<AccountId>, List<AccountId>> g_of_f_of_S = mapReduceFramework.mapReduceAll(S, mapper, reducer);
				MutualFriendsPrintUtils.printGofFofS(g_of_f_of_S);
			}
		});
	}
}
