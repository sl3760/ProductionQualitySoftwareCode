package nyu.edu.pqs.shuo.addressbook;

/**
 * class for a name of an entry name consists of first name and last name name
 * can not be extended.
 * 
 * @author Shuo
 */
final public class Name {
	private String firstName;
	private String lastName;

	/**
	 * check if name string is too long
	 */
	public static void validate(String name) {
		if (name.length() > 50) {
			throw new IllegalArgumentException("Name is too long!");
		}
	}

	/**
	 * private constructor
	 * 
	 * @param firstName
	 * @param lastName
	 */
	private Name(String firstName, String lastName) {
		validate(firstName);
		validate(lastName);
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * static factory method
	 * 
	 * @param firstName
	 * @param lastName
	 * @return Object of Name
	 */
	public static Name setName(String firstName, String lastName) {
		return new Name(firstName, lastName);
	}

	/**
	 * return first name of a name
	 * 
	 * @return firstName
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * return last name of a name
	 * 
	 * @return lastName
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Override toString method
	 */
	@Override
	public String toString() {
		return firstName + "&&" + lastName;
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
		if (!(obj instanceof Name)) {
			return false;
		}
		Name name = (Name) obj;
		if (this.firstName == null) {
			if (name.firstName != null) {
				return false;
			}
		} else {
			if (!this.firstName.equals(name.firstName)) {
				return false;
			}
		}
		if (this.lastName == null) {
			if (name.lastName != null) {
				return false;
			}
		} else {
			if (!this.lastName.equals(name.lastName)) {
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
		if (firstName == null) {
			result = prime * result + 0;
		} else {
			result = prime * result + firstName.hashCode();
		}
		if (lastName == null) {
			result = prime * result + 0;
		} else {
			result = prime * result + lastName.hashCode();
		}
		return result;
	}

}
