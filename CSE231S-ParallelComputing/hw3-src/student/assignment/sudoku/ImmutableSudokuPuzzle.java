package student.assignment.sudoku;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import edu.wustl.cse231s.NotYetImplementedException;
import edu.wustl.cse231s.sudoku.Square;
import edu.wustl.cse231s.sudoku.SudokuPuzzle;

public final class ImmutableSudokuPuzzle implements SudokuPuzzle {
	private static final List<Integer> ALL_OPTIONS = Collections.unmodifiableList(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

	public ImmutableSudokuPuzzle(String givens) {
		this.values = new int[9 * 9];
		int index = 0;
		for (char c : givens.toCharArray()) {
			int value;
			if (c == '.') {
				value = 0;
			} else {
				value = c - '0';
			}
			this.values[index] = value;
			index++;
		}
	}

	private ImmutableSudokuPuzzle(ImmutableSudokuPuzzle other, Square square, int value) {
		this.values = other.values.clone();
		int row = square.getRow();
		int column = square.getColumn();
		this.values[row * 9 + column] = value;
	}

	public ImmutableSudokuPuzzle createNext(Square square, int value) {
		return new ImmutableSudokuPuzzle(this, square, value);// call above fxn
	}

	private void removePeerValueFromList(List<Integer> list, Square peer) {
		int peerValue = this.getValue(peer);
		if (peerValue != 0) {
			ListIterator<Integer> listIterator = list.listIterator();
			while (listIterator.hasNext()) {
				int value = listIterator.next();
				if (value == peerValue) {
					listIterator.remove();
				} else if (value > peerValue) {
					break;
				}
			}
		}
	}

	@Override
	public List<Integer> getOptions(Square square) {
		int value = this.getValue(square);
		if (value != 0) {
			// NOTE: this should never be called
			return Arrays.asList(value);
		} else {
			//  look through the neighboring peers and remove their values from the list of potentials
			List<Integer> ops = new LinkedList<>(ALL_OPTIONS); // get a list of options
			Collection<Square> peers = square.getPeers(); //get surrounding squares
			
			for (Square s: peers) { // for each peer
				removePeerValueFromList(ops, s); // remove options from peer
			}
			return ops; // return options
		}
	}

	public int getValue(Square square) {
		int row = square.getRow();
		int column = square.getColumn();
		return this.values[row * 9 + column];
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int value : this.values) {
			if (value != 0) {
				sb.append(value);
			} else {
				sb.append('.');
			}
		}
		return sb.toString();
	}

	private final int[] values;
}
