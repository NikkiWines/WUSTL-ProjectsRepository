package studio12;

import dh.utils.Caesared;

public class BreakCaesar {

	private Caesared[] message;
	private char[] chars;

	/**
	 * Capture the message and break it into characters too.
	 * Do not change this method.
	 * @param message
	 */
	public BreakCaesar(Caesared[] message) {
		this.message = message;
		this.chars = new char[message.length];
		for (int i=0; i < message.length; ++i) {
			chars[i] = message[i].toString().toCharArray()[0];
		}
	}

	/**
	 * Determine the rotation value (between 0 and 25)
	 * and return that value.  For example, if a becomes b, b becomes c, etc, the rotation value is 1
	 * 
	 * This is the ONLY METHOD you should change in this class
	 * 
	 * @return the rotation factor that created the encrypted text
	 */
	public int getRotation() {
		int rotation = 0;
		for (int i=0; i < chars.length; ++i) {
			if ((chars[i]) == 87) { 
				rotation = (chars[i+1]) - 101;
				if ( rotation < 0) { 
					rotation = rotation + 26;
				}
			}

		}
		return rotation;
	}


	/**
	 * Generate the clear text by unrotating based on your computation.
	 * Do not change this method.
	 * @return
	 */
	public String getClearText() {
		String ans = "";
		int rotate = getRotation();
		for (int i=0; i < message.length; ++i) {
			ans = ans + message[i].unRotate(rotate);
		}
		return ans;
	}

}
