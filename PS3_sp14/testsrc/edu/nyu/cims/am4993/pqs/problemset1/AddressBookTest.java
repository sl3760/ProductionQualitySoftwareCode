package edu.nyu.cims.am4993.pqs.problemset1;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

public class AddressBookTest {

	@Test(expected = InvalidUserNameException.class)
	public void testNewInstanceWithNull() throws InvalidUserNameException {
		AddressBook.newInstance(null);
	}

	@Test
	public void testNewInstance() throws InvalidUserNameException {
		AddressBook newAddressBook = AddressBook.newInstance("Shuo");
		String expected = "Shuo";
		String actual = newAddressBook.getUserName();
		assertEquals(expected, actual);
	}

	@Test(expected = InvalidEntryException.class)
	public void testAddEntryWithEntryNull() throws InvalidUserNameException,
			InvalidEntryException {
		AddressBook newAddressBook = AddressBook.newInstance("Shuo");
		newAddressBook.addEntry(null);
	}

	@Test(expected = InvalidEntryException.class)
	public void testAddEntryWithEntryNameNull() throws InvalidUserNameException,
			InvalidEntryException {
		AddressBook newAddressBook = AddressBook.newInstance("Shuo");
		Entry entry = new Entry.Builder(null, "New York").build();
		newAddressBook.addEntry(entry);
	}

	@Test(expected = InvalidEntryException.class)
	public void testAddEntryWithEntryAddressNull()
			throws InvalidUserNameException, InvalidEntryException {
		AddressBook newAddressBook = AddressBook.newInstance("Shuo");
		Entry entry = new Entry.Builder("Andrew", null).build();
		newAddressBook.addEntry(entry);
	}

	@Test
	public void testAddandGetEntries() throws InvalidUserNameException,
			InvalidEntryException {
		AddressBook newAddressBook = AddressBook.newInstance("Shuo");
		Entry entry1 = new Entry.Builder("Andrew", "New York").build();
		newAddressBook.addEntry(entry1);
		Entry entry2 = new Entry.Builder("John", "New Jersy").build();
		newAddressBook.addEntry(entry2);
		int expectedSize = 2;
		int actualSize = newAddressBook.getEntries().size();
		assertEquals(expectedSize, actualSize);
		assertEquals(entry1, newAddressBook.getEntries().get(0));
		assertEquals(entry2, newAddressBook.getEntries().get(1));
	}

	@Test(expected = InvalidEntryException.class)
	public void testRemoveEntryWithNull() throws InvalidUserNameException,
			InvalidEntryException {
		AddressBook newAddressBook = AddressBook.newInstance("Shuo");
		newAddressBook.removeEntry(null);
	}

	@Test
	public void testRemoveEntry() throws InvalidUserNameException,
			InvalidEntryException {
		AddressBook newAddressBook = AddressBook.newInstance("Shuo");
		Entry entry1 = new Entry.Builder("Andrew", "New York").build();
		newAddressBook.addEntry(entry1);
		Entry entry2 = new Entry.Builder("John", "New Jersy").build();
		newAddressBook.addEntry(entry2);
		boolean expected = true;
		boolean actual = newAddressBook.removeEntry(entry2);
		assertEquals(expected, actual);

		int expectedSize = 1;
		int actualSize = newAddressBook.getEntries().size();
		assertEquals(expectedSize, actualSize);
		assertEquals(entry1, newAddressBook.getEntries().get(0));
	}

	@Test(expected = InvalidSearchStringException.class)
	public void testSearchWithNull() throws InvalidEntryException,
			InvalidUserNameException, InvalidSearchStringException {
		AddressBook newAddressBook = AddressBook.newInstance("Shuo");
		Entry entry1 = new Entry.Builder("Andrew", "New York").build();
		newAddressBook.addEntry(entry1);
		newAddressBook.search(Properties.NAME, null);
	}

	@Test
	public void testSearchName() throws InvalidEntryException,
			InvalidUserNameException, InvalidSearchStringException {
		AddressBook newAddressBook = AddressBook.newInstance("Shuo");
		Entry entry1 = new Entry.Builder("Andrew Lee", "New York").build();
		newAddressBook.addEntry(entry1);
		Entry entry2 = new Entry.Builder("John Green", "New Jersy").build();
		newAddressBook.addEntry(entry2);
		Entry entry3 = new Entry.Builder("Andrew Nash", "New Jersy").build();
		newAddressBook.addEntry(entry3);
		ArrayList<Entry> expectedResult = new ArrayList<Entry>();
		expectedResult.add(entry1);
		expectedResult.add(entry3);
		ArrayList<Entry> actualResult = newAddressBook.search(Properties.NAME,
				"Andrew");
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testSearchAddress() throws InvalidEntryException,
			InvalidUserNameException, InvalidSearchStringException {
		AddressBook newAddressBook = AddressBook.newInstance("Shuo");
		Entry entry1 = new Entry.Builder("Andrew Lee",
				"123, 45 Street, Brooklyn, New York, US").build();
		newAddressBook.addEntry(entry1);
		Entry entry2 = new Entry.Builder("John Green",
				"678, 90 Street, New Port, New Jersy ,US").build();
		newAddressBook.addEntry(entry2);
		Entry entry3 = new Entry.Builder("Andrew Nash",
				"456, 45 Street, Union City, New Jersy, US").build();
		newAddressBook.addEntry(entry3);
		ArrayList<Entry> expectedResult = new ArrayList<Entry>();
		expectedResult.add(entry2);
		expectedResult.add(entry3);
		ArrayList<Entry> actualResult = newAddressBook.search(Properties.ADDRESS,
				"New Jersy");
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testSearchPhoneNum() throws InvalidEntryException,
			InvalidUserNameException, InvalidSearchStringException {
		AddressBook newAddressBook = AddressBook.newInstance("Shuo");
		Entry entry1 = new Entry.Builder("Andrew Lee", "New York").phoneNum(
				"123-456-7890").build();
		newAddressBook.addEntry(entry1);
		Entry entry2 = new Entry.Builder("John Green", "New Port").phoneNum(
				"122-455-7899").build();
		newAddressBook.addEntry(entry2);
		Entry entry3 = new Entry.Builder("Andrew Nash", "New Jersy").phoneNum(
				"102-405-7099").build();
		newAddressBook.addEntry(entry3);
		ArrayList<Entry> expectedResult = new ArrayList<Entry>();
		expectedResult.add(entry2);
		ArrayList<Entry> actualResult = newAddressBook.search(Properties.PHONENUM,
				"122-455-7899");
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testSearchPhoneNumWithNull() throws InvalidEntryException,
			InvalidUserNameException, InvalidSearchStringException {
		AddressBook newAddressBook = AddressBook.newInstance("Shuo");
		Entry entry1 = new Entry.Builder("Andrew Lee", "New York").phoneNum(null)
				.build();
		newAddressBook.addEntry(entry1);
		Entry entry2 = new Entry.Builder("John Green", "New Port").phoneNum(
				"122-455-7899").build();
		newAddressBook.addEntry(entry2);
		Entry entry3 = new Entry.Builder("Andrew Nash", "New Jersy").phoneNum(
				"102-405-7099").build();
		newAddressBook.addEntry(entry3);
		ArrayList<Entry> expectedResult = new ArrayList<Entry>();
		expectedResult.add(entry2);
		ArrayList<Entry> actualResult = newAddressBook.search(Properties.PHONENUM,
				"122-455-7899");
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testSearchEmail() throws InvalidEntryException,
			InvalidUserNameException, InvalidSearchStringException {
		AddressBook newAddressBook = AddressBook.newInstance("Shuo");
		Entry entry1 = new Entry.Builder("Andrew Lee", "New York").email(
				"abc123@nyu.edu").build();
		newAddressBook.addEntry(entry1);
		Entry entry2 = new Entry.Builder("John Green", "New Port").email(
				"123abc@nyu.edu").build();
		newAddressBook.addEntry(entry2);
		Entry entry3 = new Entry.Builder("Andrew Nash", "New Jersy").email(
				"abc123@gmail.com").build();
		newAddressBook.addEntry(entry3);
		ArrayList<Entry> expectedResult = new ArrayList<Entry>();
		expectedResult.add(entry1);
		expectedResult.add(entry3);
		ArrayList<Entry> actualResult = newAddressBook.search(Properties.EMAIL,
				"abc123");
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testSearchEmailWithNull() throws InvalidEntryException,
			InvalidUserNameException, InvalidSearchStringException {
		AddressBook newAddressBook = AddressBook.newInstance("Shuo");
		Entry entry1 = new Entry.Builder("Andrew Lee", "New York").email(null)
				.build();
		newAddressBook.addEntry(entry1);
		Entry entry2 = new Entry.Builder("John Green", "New Port").email(
				"123abc@nyu.edu").build();
		newAddressBook.addEntry(entry2);
		Entry entry3 = new Entry.Builder("Andrew Nash", "New Jersy").email(
				"abc123@gmail.com").build();
		newAddressBook.addEntry(entry3);
		ArrayList<Entry> expectedResult = new ArrayList<Entry>();
		expectedResult.add(entry3);
		ArrayList<Entry> actualResult = newAddressBook.search(Properties.EMAIL,
				"abc123");
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testSearchNote() throws InvalidEntryException,
			InvalidUserNameException, InvalidSearchStringException {
		AddressBook newAddressBook = AddressBook.newInstance("Shuo");
		Entry entry1 = new Entry.Builder("Andrew Lee", "New York").note(
				"This is a test.").build();
		newAddressBook.addEntry(entry1);
		Entry entry2 = new Entry.Builder("John Green", "New Port").note(
				"Hello, world!").build();
		newAddressBook.addEntry(entry2);
		Entry entry3 = new Entry.Builder("Andrew Nash", "New Jersy").note(
				"This is another test.").build();
		newAddressBook.addEntry(entry3);
		ArrayList<Entry> expectedResult = new ArrayList<Entry>();
		expectedResult.add(entry1);
		expectedResult.add(entry3);
		ArrayList<Entry> actualResult = newAddressBook.search(Properties.NOTE,
				"test");
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testSearchNoteWithNull() throws InvalidEntryException,
			InvalidUserNameException, InvalidSearchStringException {
		AddressBook newAddressBook = AddressBook.newInstance("Shuo");
		Entry entry1 = new Entry.Builder("Andrew Lee", "New York").note(null)
				.build();
		newAddressBook.addEntry(entry1);
		Entry entry2 = new Entry.Builder("John Green", "New Port").note(
				"Hello, world!").build();
		newAddressBook.addEntry(entry2);
		Entry entry3 = new Entry.Builder("Andrew Nash", "New Jersy").note(
				"This is another test.").build();
		newAddressBook.addEntry(entry3);
		ArrayList<Entry> expectedResult = new ArrayList<Entry>();
		expectedResult.add(entry3);
		ArrayList<Entry> actualResult = newAddressBook.search(Properties.NOTE,
				"test");
		assertEquals(expectedResult, actualResult);
	}

	@Test(expected = InvalidAddressBookException.class)
	public void testSaveWithNull() throws InvalidAddressBookException {
		AddressBook.save(null);
	}

	@Test
	public void testSave() throws InvalidUserNameException,
			InvalidAddressBookException, InvalidEntryException {
		AddressBook newAddressBook = AddressBook.newInstance("Shuo");
		Entry entry = new Entry.Builder("Andrew Lee", "New York").build();
		newAddressBook.addEntry(entry);
		AddressBook.save(newAddressBook);
		File file = new File(newAddressBook.getUserName() + "_"
				+ newAddressBook.getId() + ".ser");
		assertTrue(file.exists());
		file.delete();
	}

	@Test(expected = InvalidFileNameException.class)
	public void testReadNull() throws InvalidFileNameException {
		AddressBook.read(null);
	}

	@Test(expected = InvalidFileNameException.class)
	public void testReadWithFileNotFoundException()
			throws InvalidUserNameException, InvalidEntryException,
			InvalidAddressBookException, InvalidFileNameException,
			InvalidSearchStringException {
		AddressBook newAddressBook = AddressBook.newInstance("Shuo");
		Entry entry = new Entry.Builder("Andrew Lee", "New York").build();
		newAddressBook.addEntry(entry);
		AddressBook.save(newAddressBook);
		String fileName = newAddressBook.getUserName() + ".ser";
		AddressBook.read(fileName);
	}

	@Test
	public void testRead() throws InvalidUserNameException,
			InvalidEntryException, InvalidAddressBookException,
			InvalidFileNameException, InvalidSearchStringException {
		AddressBook newAddressBook = AddressBook.newInstance("Shuo");
		Entry entry = new Entry.Builder("Andrew Lee", "New York").build();
		newAddressBook.addEntry(entry);
		AddressBook.save(newAddressBook);
		String fileName = newAddressBook.getUserName() + "_"
				+ newAddressBook.getId() + ".ser";
		AddressBook addressBook = AddressBook.read(fileName);
		assertNotNull(addressBook.search(Properties.NAME, "Andrew"));
	}

	@Test
	public void testHashCodeNotEqual() throws InvalidEntryException,
			InvalidUserNameException {
		AddressBook addressBook = AddressBook.newInstance("Shuo");
		Entry entry = new Entry.Builder("Shuo Li", "New York").build();
		addressBook.addEntry(entry);
		AddressBook mockAddressBook = AddressBook.newInstance("Li");
		mockAddressBook.addEntry(entry);
		int expectedHashCode = addressBook.hashCode();
		int actualHashCode = mockAddressBook.hashCode();
		assertNotEquals(expectedHashCode, actualHashCode);
	}

	@Test
	public void testHashCodeEqual() throws InvalidEntryException,
			InvalidUserNameException {
		AddressBook addressBook = AddressBook.newInstance("Shuo");
		Entry entry = new Entry.Builder("Shuo Li", "New York").build();
		addressBook.addEntry(entry);
		AddressBook mockAddressBook = AddressBook.newInstance("Shuo");
		mockAddressBook.addEntry(entry);
		int expectedHashCode = addressBook.hashCode();
		int actualHashCode = mockAddressBook.hashCode();
		assertEquals(expectedHashCode, actualHashCode);
	}

	@Test
	public void testObjectEquals() throws InvalidUserNameException,
			InvalidEntryException {
		AddressBook addressBook = AddressBook.newInstance("Shuo");
		Entry entry = new Entry.Builder("Shuo Li", "New York").build();
		addressBook.addEntry(entry);
		AddressBook mockAddressBook = AddressBook.newInstance("Shuo");
		mockAddressBook.addEntry(entry);
		assertEquals(addressBook, mockAddressBook);
		assertEquals(addressBook, addressBook);
		assertFalse(addressBook.equals(null));
		assertFalse(addressBook.equals(new String("This is a test.")));
		assertTrue(addressBook.equals(mockAddressBook));
	}

	@Test
	public void testUserNameEquals() throws InvalidUserNameException {
		AddressBook addressBook = AddressBook.newInstance("Shuo");
		AddressBook mockAddressBook1 = AddressBook.newInstance("Shuo");
		AddressBook mockAddressBook2 = AddressBook.newInstance("Li");
		assertTrue(addressBook.equals(mockAddressBook1));
		assertFalse(addressBook.equals(mockAddressBook2));
	}

	@Test
	public void testNotEqualsWithSize() throws InvalidUserNameException,
			InvalidEntryException {
		AddressBook addressBook = AddressBook.newInstance("Shuo");
		AddressBook mockAddressBook1 = AddressBook.newInstance("Shuo");
		Entry entry = new Entry.Builder("John", "New York").build();
		Entry entry1 = new Entry.Builder("Anderw", "New York").build();
		addressBook.addEntry(entry);
		addressBook.addEntry(entry1);
		mockAddressBook1.addEntry(entry);
		assertFalse(addressBook.equals(mockAddressBook1));
		assertFalse(mockAddressBook1.equals(addressBook));
	}

	@Test
	public void testNotEquals() throws InvalidUserNameException,
			InvalidEntryException {
		AddressBook addressBook = AddressBook.newInstance("Shuo");
		AddressBook mockAddressBook1 = AddressBook.newInstance("Shuo");
		Entry entry = new Entry.Builder("John", "New York").build();
		Entry entry1 = new Entry.Builder("Anderw", "New York").build();
		Entry entry2 = new Entry.Builder("Bob", "New York").build();
		addressBook.addEntry(entry);
		addressBook.addEntry(entry1);
		mockAddressBook1.addEntry(entry);
		mockAddressBook1.addEntry(entry2);
		assertFalse(addressBook.equals(mockAddressBook1));
		assertFalse(mockAddressBook1.equals(addressBook));
	}

	/**
	 * There is a bug on equals method.
	 * 
	 * @throws InvalidUserNameException
	 * @throws InvalidEntryException
	 */
	@Test
	public void testNotEqualsWithContains() throws InvalidUserNameException,
			InvalidEntryException {
		AddressBook addressBook = AddressBook.newInstance("Shuo");
		AddressBook mockAddressBook1 = AddressBook.newInstance("Shuo");
		Entry entry = new Entry.Builder("John", "New York").build();
		Entry entry1 = new Entry.Builder("Anderw", "New York").build();
		addressBook.addEntry(entry);
		addressBook.addEntry(entry1);
		mockAddressBook1.addEntry(entry);
		mockAddressBook1.addEntry(entry);
		assertFalse(addressBook.equals(mockAddressBook1));
		assertFalse(mockAddressBook1.equals(addressBook));
	}

	@Test
	public void testToString() throws InvalidUserNameException,
			InvalidEntryException {
		AddressBook newAddressBook = AddressBook.newInstance("Shuo");
		Entry entry = new Entry.Builder("Andrew Lee", "New York").build();
		newAddressBook.addEntry(entry);
		String expectedString = "Shuo~##~Andrew Lee~#~New York~#~null~#~null~#~null";
		String actualString = newAddressBook.toString();
		assertEquals(expectedString, actualString);
	}

}
