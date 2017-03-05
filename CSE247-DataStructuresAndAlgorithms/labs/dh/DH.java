package dh;

import dh.utils.MExp;
import timing.Ticker;

public class DH {
	
	private final long privKey;
	public  final long base, modulus;
	private final MExp modexp;
	private Ticker ticker;

	
	public DH(long base, long privKey, long modulus) {
		this.privKey = privKey;
		this.modulus = modulus;
		this.base    = base;
		this.modexp  = new MExp(base, modulus);
	}
	
	/**
	 * Compute the public key for the contained private key.
	 * As shown in lecture this is base to the privKey power mod modulus
	 * @return
	 */
	public long getPubKey() {
		MExp mexp = this.modexp; // new MExp with this.base and this.modulus
		long ans = mexp.toThePower(this.privKey); // returns base^(privKey) % modulus as explained in MExp description
//		ticker.tick(2);
		return ans;
				
		
	}
	
	/**
	 * Compute Diffie--Hellman agreement:  raise the other agent's public key
	 *   to this private key power, mod the common modulus.
	 * @param otherPubKey
	 * @return
	 */
	public long getAgreedNum(long otherPubKey) {
		MExp mexp = new MExp (otherPubKey, this.modulus); // new MExp with otherPubKey and this.modulus
		long ans = mexp.toThePower(this.privKey); // returns otherPubKey^(privKey) % modulus 
//		ticker.tick(2);
		return ans;
		
	}
	
}
