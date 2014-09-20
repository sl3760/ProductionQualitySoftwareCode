package nyu.edu.pqs.shuo.addressbook;

import java.util.regex.Pattern;

/**
 * Entry Class represents the address entry. Address entry consists of a name,
 * postal address, phone number, email address, and a note. This class can not
 * be extended.
 * 
 * @author Shuo
 */
final public class Entry {
	private Name name;
	private PostalAddress postalAddress;
	private String phoneNumber;
	private String emailAddress;
	private String note;

	/**
	 * Use a builder pattern for a constructor with many parameters
	 */
	public static class Builder {
		private Name name;
		private PostalAddress postalAddress;
		private String phoneNumber;
		private String emailAddress;
		private String note;

		public Builder() {
		}

		/**
		 * set name of entry
		 * 
		 * @param firstName
		 * @param lastName
		 * @return Builder Object
		 */
		public Builder setName(String firstName, String lastName) {
			this.name = Name.setName(firstName, lastName);
			return this;
		}

		/**
		 * set postal address of entry
		 * 
		 * @param street
		 * @param city
		 * @param state
		 * @param country
		 * @param ZIP
		 * @return Builder Object
		 */
		public Builder setPostalAddress(PostalAddress postalAddress) {
			this.postalAddress = postalAddress;
			return this;
		}

		/**
		 * set phone number of entry
		 * 
		 * @param phoneNumber
		 * @return Builder Object
		 */
		public Builder setPhoneNumber(String phoneNumber) {
			if (!isPhoneNumber(phoneNumber) || phoneNumber.length() > 20) {
				throw new IllegalArgumentException("Not valid phone number!");
			}
			this.phoneNumber = phoneNumber;
			return this;
		}

		/**
		 * set email address of entry
		 * 
		 * @param emailAddress
		 * @return Builder Object
		 */
		public Builder setEmailAddress(String emailAddress) {
			if (!isEmail(emailAddress)) {
				throw new IllegalArgumentException("Not valid Email!");
			}
			this.emailAddress = emailAddress;
			return this;
		}

		/**
		 * set note of entry
		 * 
		 * @param note
		 * @return Builder Object
		 */
		public Builder setNode(String note) {
			this.note = note;
			return this;
		}

		/**
		 * return Entry Object
		 * 
		 * @return Entry Object
		 */
		public Entry build() {
			return new Entry(this);
		}
	}

	/**
	 * private constructor for entry
	 * 
	 * @param builder
	 */
	private Entry(Builder builder) {
		name = builder.name;
		postalAddress = builder.postalAddress;
		phoneNumber = builder.phoneNumber;
		emailAddress = builder.emailAddress;
		note = builder.note;
	}

	/**
	 * check if a string consists of numbers
	 * 
	 * @param str
	 * @return true if all are numbers or false
	 */
	public static boolean isPhoneNumber(String phoneNumber) {
		Pattern pattern = Pattern.compile("[0-9-\\(\\)\\+ ]*");
		return pattern.matcher(phoneNumber).matches();
	}

	public static boolean isEmail(String email) {
		Pattern pattern = Pattern
				.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
		return pattern.matcher(email).matches();
	}

	/**
	 * return name of entry
	 * 
	 * @return name
	 */
	public Name getName() {
		return this.name;
	}

	/**
	 * return postal address of entry
	 * 
	 * @return postalAddress
	 */
	public PostalAddress getPostalAddress() {
		return this.postalAddress;
	}

	/**
	 * return phone number of entry
	 * 
	 * @return phoneNumber
	 */
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * return email address of entry
	 * 
	 * @return emailAddress
	 */
	public String getEmailAddress() {
		return this.emailAddress;
	}

	/**
	 * return note of entry
	 * 
	 * @return note
	 */
	public String getNote() {
		return this.note;
	}

	/**
	 * Override toString() method
	 */
	@Override
	public String toString() {
		return getName().toString() + "##" + getPostalAddress().toString() + "##"
				+ getPhoneNumber() + "##" + getEmailAddress() + "##" + getNote();
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
		if (!(obj instanceof Entry)) {
			return false;
		}
		Entry entry = (Entry) obj;
		if (name == null) {
			if (entry.name != null) {
				return false;
			}
		} else {
			if (!name.equals(entry.name)) {
				return false;
			}
		}
		if (postalAddress == null) {
			if (entry.postalAddress != null) {
				return false;
			}
		} else {
			if (!postalAddress.equals(entry.postalAddress)) {
				return false;
			}
		}
		if (phoneNumber == null) {
			if (entry.phoneNumber != null) {
				return false;
			}
		} else {
			if (!phoneNumber.equals(entry.phoneNumber)) {
				return false;
			}
		}
		if (emailAddress == null) {
			if (entry.emailAddress != null) {
				return false;
			}
		} else {
			if (!emailAddress.equals(entry.emailAddress)) {
				return false;
			}
		}
		if (note == null) {
			if (entry.note != null) {
				return false;
			}
		} else {
			if (!note.equals(entry.note)) {
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
		final int prime = 31;
		int result = 17;
		if (name == null) {
			result = prime * result + 0;
		} else {
			result = prime * result + name.hashCode();
		}
		if (postalAddress == null) {
			result = prime * result + 0;
		} else {
			result = prime * result + postalAddress.hashCode();
		}
		if (phoneNumber == null) {
			result = prime * result + 0;
		} else {
			result = prime * result + phoneNumber.hashCode();
		}
		if (emailAddress == null) {
			result = prime * result + 0;
		} else {
			result = prime * result + emailAddress.hashCode();
		}
		if (note == null) {
			result = prime * result + 0;
		} else {
			result = prime * result + note.hashCode();
		}
		return result;
	}

}
