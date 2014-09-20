package nyu.edu.pqs.shuo.addressbook;

import java.util.ArrayList;
import com.google.gson.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * class to represent a book address library It allows to create an empty
 * address book, add an entry,remove and entry and search for an entry by any of
 * the contact properties. It can save the address book to a file and read the
 * address book from a file.
 * 
 * @author Shuo
 * 
 */
public class AddressBook {
	private ArrayList<Entry> addressBookItems;

	/**
	 * private constructor
	 */
	private AddressBook() {
		addressBookItems = new ArrayList<Entry>();
	}

	/**
	 * static factory method that returns an AddresBook Object
	 * 
	 * @return AddresBook Object
	 */
	public static AddressBook createAddressBook() {
		return new AddressBook();
	}

	/**
	 * static factory method that returns an Entry Object
	 * 
	 * @return Entry Object
	 */
	public static Entry createEntry() {
		return new Entry.Builder().build();
	}

	/**
	 * This method allows to add an address entry to the address book.
	 * 
	 * @param entry
	 */
	public void addEntry(Entry entry) {
		addressBookItems.add(entry);
	}

	/**
	 * This method allows to remove an entry from the address book If the entry
	 * exists in the address book, it removes the entry and returns true; If the
	 * entry dose not exist in the address book, it return false;
	 * 
	 * @param entry
	 * @return boolean
	 */
	public boolean remove(Entry removeEntry) {
		for (Entry entry : addressBookItems) {
			if (entry.equals(removeEntry)) {
				addressBookItems.remove(entry);
				return true;
			}
		}
		return false;
	}

	/**
	 * This method allows to search for an entry by any of the contact properties.
	 * 
	 * @param searchInfo
	 * @return a list of entry
	 */
	public ArrayList<Entry> searchEntry(String searchInfo) {
		ArrayList<Entry> searchResult = new ArrayList<Entry>();
		for (Entry entry : addressBookItems) {
			if (entry.toString().toLowerCase().contains(searchInfo.toLowerCase())) {
				searchResult.add(entry);
			}
		}
		return searchResult;
	}

	/**
	 * This method saves an address book to a file with provided name.
	 * 
	 * @param filePath
	 */
	public void saveAddressBook(String filePath) {
		try {
			FileWriter writer = new FileWriter(filePath);
			Gson gson = new GsonBuilder().create();
			gson.toJson(this, writer);
			writer.close();
		} catch (IOException e) {
			System.err.println("IOException caught: " + e.getMessage());
		}
	}

	/**
	 * This method reads an address book from a file with provided name.
	 * 
	 * @param fileName
	 * @return an address book
	 */
	public AddressBook readAddressBook(String filePath) {
		AddressBook addressBook = AddressBook.createAddressBook();
		try {
			FileReader reader = new FileReader(filePath);
			Gson gson = new GsonBuilder().create();
			addressBook = gson.fromJson(reader, AddressBook.class);
			reader.close();
		} catch (IOException e) {
			System.err.println("IOException caught: " + e.getMessage());
		}
		return addressBook;
	}

	/**
	 * Override toString method
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Entry entry : addressBookItems) {
			sb.append("{");
			String[] entryItems = entry.toString().split("##");
			String[] nameItems = entryItems[0].split("&&");
			String[] postalAddressItems = entryItems[1].split("&&");
			sb.append(" \"First Name\": " + nameItems[0]);
			sb.append(" \"Last Name\": " + nameItems[1]);
			sb.append(" \"Street\": " + postalAddressItems[0]);
			sb.append(" \"City\": " + postalAddressItems[1]);
			sb.append(" \"State:\" " + postalAddressItems[2]);
			sb.append(" \"Country:\" " + postalAddressItems[3]);
			sb.append(" \"ZIP:\" " + postalAddressItems[4]);
			sb.append(" \"Phone Number:\" " + entryItems[2]);
			sb.append(" \"Email Address:\" " + entryItems[3]);
			sb.append(" \"Note:\" " + entryItems[4]);
			sb.append("}");
		}
		return sb.toString();
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
		if (!(obj instanceof AddressBook)) {
			return false;
		}
		AddressBook other = (AddressBook) obj;
		if (addressBookItems == null) {
			if (other.addressBookItems != null) {
				return false;
			}
		} else {
			if (addressBookItems.size() != other.addressBookItems.size()) {
				return false;
			} else {
				for (int i = 0; i < addressBookItems.size(); i++) {
					if (!addressBookItems.get(i).equals(other.addressBookItems.get(i))) {
						return false;
					}
				}
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
		if (addressBookItems == null) {
			result = prime * result + 0;
		} else {
			for (int i = 0; i < addressBookItems.size(); i++) {
				result = prime * result + addressBookItems.get(i).hashCode();
			}
		}
		return result;
	}

	public static void main(String[] args) {
		// create 2 entries
		PostalAddress postalAddress1 = new PostalAddress.AddressBuilder()
				.setStreet("62nd Street").setCity("Brooklyn").setState("NY")
				.setCountry("US").setZIP("11220").addressBuilder();
		PostalAddress postalAddress2 = new PostalAddress.AddressBuilder()
				.setStreet("36 Street").setCity("New York City").setState("NY")
				.setCountry("US").setZIP("13320").addressBuilder();
		Entry entry1 = new Entry.Builder().setName("Shuo", "Li")
				.setPostalAddress(postalAddress1).setPhoneNumber("+1 (201)-240-1507")
				.setEmailAddress("sl3760@nyu.edu").build();
		Entry entry2 = new Entry.Builder().setName("Andrew", "Lee")
				.setPostalAddress(postalAddress2).setPhoneNumber("+1 (200)-345-5677")
				.setEmailAddress("anl240@nyu.edu").build();
		AddressBook addressBook = AddressBook.createAddressBook();
		System.out.println("Function Test");
		// test add method
		addressBook.addEntry(entry1);
		addressBook.addEntry(entry2);
		System.out.println(addressBook.toString());
		// test remove method
		addressBook.remove(entry2);
		System.out.println(addressBook.toString());
		addressBook.addEntry(entry2);
		System.out.println(addressBook.toString());
		// test research method
		System.out.println(addressBook.searchEntry("SHUO").toString());
		// test save to file method
		addressBook.saveAddressBook("./output.json");
		// test read from file method
		AddressBook other = addressBook.readAddressBook("./output.json");
		System.out.println(other.toString());
	}
}
