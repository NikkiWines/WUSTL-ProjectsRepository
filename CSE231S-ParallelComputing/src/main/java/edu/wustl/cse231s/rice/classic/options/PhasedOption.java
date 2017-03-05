package edu.wustl.cse231s.rice.classic.options;

import java.util.ArrayList;
import java.util.List;

import edu.rice.hj.api.HjPhaserPair;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public final class PhasedOption {
	public PhasedOption(HjPhaserPair phaserPairA, HjPhaserPair[] phaserPairsBtoZ) {
		this.phaserPairs = new ArrayList<>(1 + phaserPairsBtoZ.length);
		this.phaserPairs.add(phaserPairA);
		for (HjPhaserPair phaserPair : phaserPairsBtoZ) {
			this.phaserPairs.add(phaserPair);
		}
	}

	public List<HjPhaserPair> getPhaserPairs() {
		return this.phaserPairs;
	}

	private final List<HjPhaserPair> phaserPairs;
}
