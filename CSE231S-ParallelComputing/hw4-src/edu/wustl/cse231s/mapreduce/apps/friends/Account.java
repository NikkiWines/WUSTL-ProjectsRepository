package edu.wustl.cse231s.mapreduce.apps.friends;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class Account {
	public Account( char c, String textRepresentingFriendList ) {
		this.id = createAccountId(c);
		this.friendIds = createAccountIds( textRepresentingFriendList.toCharArray() );
	}
	
	public AccountId getId() {
		return this.id;
	}
	
	public List<AccountId> getFriendIds() {
		return this.friendIds;
	}
	
	private final AccountId id;
	private final List<AccountId> friendIds;


	private static AccountId createAccountId(char c) {
		return new AccountId(Character.toString(c));
	}

	private static List<AccountId> createAccountIds(char[] cs) {
		List<AccountId> result = new ArrayList<>(cs.length);
		for (char c : cs) {
			result.add( createAccountId(c) );
		}
		return Collections.unmodifiableList(result);
	}
}
