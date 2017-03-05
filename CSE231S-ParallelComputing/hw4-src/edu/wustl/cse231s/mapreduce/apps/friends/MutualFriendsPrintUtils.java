package edu.wustl.cse231s.mapreduce.apps.friends;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import edu.wustl.cse231s.util.OrderedPair;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public final class MutualFriendsPrintUtils {
	private MutualFriendsPrintUtils() {
		throw new Error();
	}

	public static void printS(Account[] S) {
		System.out.println("S");
		System.out.println("-");
		Arrays.asList(S).forEach((account) -> {
			System.out.println(account.getId() + "->" + account.getFriendIds());
		});
		System.out.println();
	}

	public static void printGofFofS(Map<OrderedPair<AccountId>, List<AccountId>> g_of_f_of_S) {
		System.out.println("g(f(S))");
		System.out.println("-------");
		new TreeMap<>(g_of_f_of_S).forEach((k,v) -> {
			System.out.println(k + "->" + v);
		});
		System.out.println();
	}
}
