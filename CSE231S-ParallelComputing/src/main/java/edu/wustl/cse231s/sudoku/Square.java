package edu.wustl.cse231s.sudoku;

import java.util.List;

import edu.wustl.cse231s.lazy.Lazy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public enum Square {
	A1, A2, A3, A4, A5, A6, A7, A8, A9, 
	B1, B2, B3, B4, B5, B6, B7, B8, B9, 
	C1, C2, C3, C4, C5, C6, C7, C8, C9, 
	D1, D2, D3, D4, D5, D6, D7, D8, D9, 
	E1, E2, E3, E4, E5, E6, E7, E8, E9, 
	F1, F2, F3, F4, F5, F6, F7, F8, F9, 
	G1, G2, G3, G4, G5, G6, G7, G8, G9, 
	H1, H2, H3, H4, H5, H6, H7, H8, H9, 
	I1, I2, I3, I4, I5, I6, I7, I8, I9;

	private static Collection<Square>[][] boxUnitMatrix = new Collection[3][3];
	static {
		int i = 0;
		for (String boxRows : new String[] { "ABC", "DEF", "GHI" }) {
			int j = 0;
			for (String boxCols : new String[] { "123", "456", "789" }) {
				boxUnitMatrix[i][j] = new LinkedList<>();
				for (char cr : boxRows.toCharArray()) {
					for (char cc : boxCols.toCharArray()) {
						StringBuilder sb = new StringBuilder();
						sb.append(cr);
						sb.append(cc);
						boxUnitMatrix[i][j].add(Square.valueOf(sb.toString()));
					}
				}
				j++;
			}
			i++;
		}
	}

	private Square() {
		this.row = this.name().charAt(0) - 'A';
		this.column = this.name().charAt(1) - '1';
	}

	public int getRow() {
		return this.row;
	}

	public int getColumn() {
		return this.column;
	}

	public Square next() {
		return this.next.get();
	}

	public Collection<Square> getPeers() {
		return this.peers.get();
	}

	public static Square valueOf( int row, int column ) {
		StringBuilder sb = new StringBuilder();
		sb.append((char)('A'+row));
		sb.append((char)('1'+column));
		return valueOf(sb.toString());
	}
	private final int row;
	private final int column;
	private final Lazy<Square> next = new Lazy<>(() -> {
		Square[] squares = Square.values();
		int nextOrdinal = this.ordinal() + 1;
		return nextOrdinal < squares.length ? squares[nextOrdinal] : null;
	});
	private final Lazy<Collection<Square>> peers = new Lazy<>(() -> {
		List<Square> update = new ArrayList<>(20);
		String name = this.name();
		char rowChar = name.charAt(0);
		char colChar = name.charAt(1);

		// row unit
		for (char cChar = '1'; cChar <= '9'; cChar++) {
			Square peer = Square.valueOf(Character.toString(rowChar) + cChar);
			if (this == peer) {
				// pass
			} else {
				update.add(peer);
			}
		}

		// column unit
		for (char rChar = 'A'; rChar <= 'I'; rChar++) {
			Square peer = Square.valueOf(Character.toString(rChar) + colChar);
			if (this == peer) {
				// pass
			} else {
				update.add(peer);
			}
		}

		// box unit
		int boxI = this.getRow() / 3;
		int boxJ = this.getColumn() / 3;

		for (Square boxPeer : boxUnitMatrix[boxI][boxJ]) {
			if (this == boxPeer || update.contains(boxPeer)) {
				// pass
			} else {
				update.add(boxPeer);
			}
		}
		return update;
	});
}
