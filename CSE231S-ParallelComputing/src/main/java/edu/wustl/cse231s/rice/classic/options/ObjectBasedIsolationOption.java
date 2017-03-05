package edu.wustl.cse231s.rice.classic.options;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public final class ObjectBasedIsolationOption {
	public ObjectBasedIsolationOption(Object participant) {
		this.participants = new Object[] { participant };
	}
	
	public ObjectBasedIsolationOption(Object participantA, Object participantB, Object[] participantsCtoZ) {
		this.participants = new Object[2 + participantsCtoZ.length];
		this.participants[0] = participantA;
		this.participants[1] = participantB;
		System.arraycopy(participantsCtoZ, 0, participants, 2, participantsCtoZ.length);
	}
	
	public Object[] getParticipants() {
		return this.participants;
	}

	private final Object[] participants;
}
