package nyu.edu.pqs.shuo.addressbook;

import java.util.regex.Pattern;

/**
 * class for a postal address of an entry postal address consists of street,
 * city, state, country, and ZIP. this class can not be extended.
 * 
 * @author Shuo
 */
final public class PostalAddress {
	private String street;
	private String city;
	private String state;
	private String country;
	private String ZIP;

	/**
	 * Use a builder pattern for a constructor with many parameters
	 */
	public static class AddressBuilder {
		private String street;
		private String city;
		private String state;
		private String country;
		private String ZIP;

		public AddressBuilder() {

		}

		public AddressBuilder setStreet(String street) {
			this.street = street;
			return this;
		}

		public AddressBuilder setCity(String city) {
			this.city = city;
			return this;
		}

		public AddressBuilder setState(String state) {
			this.state = state;
			return this;
		}

		public AddressBuilder setCountry(String country) {
			this.country = country;
			return this;
		}

		public AddressBuilder setZIP(String ZIP) throws IllegalArgumentException {
			if (!isNumeric(ZIP) || ZIP.length() > 20) {
				throw new IllegalArgumentException("Not valid ZIP!");
			}
			this.ZIP = ZIP;
			return this;
		}

		public PostalAddress addressBuilder() {
			return new PostalAddress(this);
		}

	}

	/**
	 * private constructor for postal address
	 * 
	 * @param addressBuilder
	 */
	private PostalAddress(AddressBuilder addressBuilder) {
		street = addressBuilder.street;
		city = addressBuilder.city;
		state = addressBuilder.state;
		country = addressBuilder.country;
		ZIP = addressBuilder.ZIP;
	}

	/**
	 * check if a string consists of numbers
	 * 
	 * @param str
	 * @return true if all are numbers or false
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * return street of a postal address
	 * 
	 * @return street
	 */
	public String getStreet() {
		return this.street;
	}

	/**
	 * return city of a postal address
	 * 
	 * @return city
	 */
	public String getCity() {
		return this.city;
	}

	/**
	 * return state of a postal address
	 * 
	 * @return state
	 */
	public String getState() {
		return this.state;
	}

	/**
	 * return country of a postal address
	 * 
	 * @return country
	 */
	public String getCountry() {
		return this.country;
	}

	/**
	 * return ZIP of a postal address
	 * 
	 * @return ZIP
	 */
	public String getZIP() {
		return this.ZIP;
	}

	/**
	 * Override toString method
	 */
	@Override
	public String toString() {
		return street + "&&" + city + "&&" + state + "&&" + country + "&&" + ZIP;
	}

	/**
	 * Override equals method
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PostalAddress)) {
			return false;
		}
		PostalAddress address = (PostalAddress) obj;
		if (street == null) {
			if (address.street != null) {
				return false;
			}
		} else {
			if (!street.equals(address.street)) {
				return false;
			}
		}
		if (city == null) {
			if (address.city != null) {
				return false;
			}
		} else {
			if (!city.equals(address.city)) {
				return false;
			}
		}
		if (state == null) {
			if (address.state != null) {
				return false;
			}
		} else {
			if (!state.equals(address.state)) {
				return false;
			}
		}
		if (country == null) {
			if (address.country != null) {
				return false;
			}
		} else {
			if (!country.equals(address.country)) {
				return false;
			}
		}
		if (ZIP == null) {
			if (address.ZIP != null) {
				return false;
			}
		} else {
			if (!ZIP.equals(address.ZIP)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Override hashCode method
	 */
	@Override
	public int hashCode() {
		int result = 17;
		final int prime = 31;
		if (street == null) {
			result = prime * result + 0;
		} else {
			result = prime * result + street.hashCode();
		}
		if (city == null) {
			result = prime * result + 0;
		} else {
			result = prime * result + city.hashCode();
		}
		if (state == null) {
			result = prime * result + 0;
		} else {
			result = prime * result + state.hashCode();
		}
		if (country == null) {
			result = prime * result + 0;
		} else {
			result = prime * result + country.hashCode();
		}
		if (ZIP == null) {
			result = prime * result + 0;
		} else {
			result = prime * result + ZIP.hashCode();
		}
		return result;
	}

}
