package studio8;

import java.util.Random;

public class Syrup {

	public double density;
	public String brand;
	Random rand = new Random();

	public Syrup(String brand, double density) {

		this.density = density;
		this.brand = brand;
	}

	public int hashCode(){
		int stringval = 0; 
		for(int i  = 0; i < brand.length(); i++){
			int b = (int) brand.charAt(i)*7;
			stringval = stringval + b;
		}
		int hash = (int) density + stringval;


		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Syrup other = (Syrup) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (Double.doubleToLongBits(density) != Double
				.doubleToLongBits(other.density))
			return false;
		return true;
	}



}

