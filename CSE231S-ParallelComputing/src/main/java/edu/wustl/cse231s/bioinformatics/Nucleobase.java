package edu.wustl.cse231s.bioinformatics;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public enum Nucleobase {
	ADENINE('A'), THYMINE('T'), CYTOSINE('C'), GUANINE('G'), URACIL('U'), NONSPECIFIC('N');

	private Nucleobase(char charRepresentation) {
		this.byteRepresentation = (byte) charRepresentation;
	}

	/**
	 * Gets the byte representation of this nucleobase. This is the numerical
	 * value (ASCII) of the char representation.
	 * 
	 * @return The byte representation of this nucleobase.
	 */
	public byte toByte() {
		return this.byteRepresentation;
	}

	/**
	 * Gets the char representation of this nucleobase. This will be A, T, C, G,
	 * U, or N.
	 * 
	 * @return The letter that represents this nucleobase.
	 */
	public char toChar() {
		return (char) this.byteRepresentation;
	}

	private final byte byteRepresentation;

}
