package edu.wustl.cse231s.mapreduce.apps.friends;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public final class AccountId implements Comparable<AccountId> {
	public AccountId(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public int compareTo(AccountId other) {
		return this.name.compareTo(other.name);
	}

	@Override
	public int hashCode() {
		return this.name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AccountId) {
			AccountId other = (AccountId) obj;
			return this.name.equals(other.name);
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return this.name;
	}

	private final String name;
}
