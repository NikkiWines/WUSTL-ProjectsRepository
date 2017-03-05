package edu.wustl.cse231s.mapreduce.apps.friends;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class DatabaseUtils {
	private DatabaseUtils() {
		throw new Error();
	}

	public static Account[] createAccounts(String... textsRepresentingFriendLists) {
		Account[] result = new Account[textsRepresentingFriendLists.length];
		char ch = 'A';
		for (String textRepresentingFriendList : textsRepresentingFriendLists) {
			result[ch - 'A'] = new Account(ch, textRepresentingFriendList);
			ch++;
		}
		return result;
	}
}
