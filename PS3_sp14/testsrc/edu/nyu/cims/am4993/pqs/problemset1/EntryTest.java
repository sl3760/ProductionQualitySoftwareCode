package edu.nyu.cims.am4993.pqs.problemset1;

import static org.junit.Assert.*;

import org.junit.Test;

public class EntryTest {

	@Test
	public void testGetName() {
		Entry nullEntry = new Entry.Builder(null,
				"123,45 Street, Brooklyn, 11990, New York, US").build();
		assertNull(nullEntry.getName());

		Entry entry = new Entry.Builder("Shuo Li",
				"123,45 Street, Brooklyn, 11990, New York, US").build();
		String expected = "Shuo Li";
		String actual = entry.getName();
		assertEquals(expected, actual);
	}

	@Test
	public void testGetAddress() {
		Entry nullEntry = new Entry.Builder("Shuo Li", null).build();
		assertNull(nullEntry.getAddress());

		Entry entry = new Entry.Builder("Shuo Li",
				"123,45 Street, Brooklyn, 11990, New York, US").build();
		String expected = "123,45 Street, Brooklyn, 11990, New York, US";
		String actual = entry.getAddress();
		assertEquals(expected, actual);
	}

	@Test
	public void testGetPhoneNum() {
		Entry nullEntry = new Entry.Builder("Shuo Li", "New York").phoneNum(null)
				.build();
		assertNull(nullEntry.getPhoneNum());

		Entry entry = new Entry.Builder("Shuo Li", "New York").phoneNum(
				"123-456-7899").build();
		String expected = "123-456-7899";
		String actual = entry.getPhoneNum();
		assertEquals(expected, actual);
	}

	@Test
	public void testGetEmail() {
		Entry nullEntry = new Entry.Builder("Shuo Li", "New York").email(null)
				.build();
		assertNull(nullEntry.getEmail());

		Entry entry = new Entry.Builder("Shuo Li", "New York").email(
				"abc123@nyu.edu").build();
		String expected = "abc123@nyu.edu";
		String actual = entry.getEmail();
		assertEquals(expected, actual);
	}

	@Test
	public void testGetNote() {
		Entry nullEntry = new Entry.Builder("Shuo Li", "New York").note(null)
				.build();
		assertNull(nullEntry.getNote());

		Entry entry = new Entry.Builder("Shuo Li", "New York").note(
				"This is a test.").build();
		String expected = "This is a test.";
		String actual = entry.getNote();
		assertEquals(expected, actual);
	}

	@Test(expected = InvalidUpdateStringException.class)
	public void testEditFieldWithInvalidUpdateName()
			throws InvalidUpdateStringException {
		Entry entry = new Entry.Builder("Shuo Li", "New York").build();
		entry.editField(Properties.NAME, null);
	}

	@Test(expected = InvalidUpdateStringException.class)
	public void testEditFieldWithInvalidUpdateAddress()
			throws InvalidUpdateStringException {
		Entry entry = new Entry.Builder("Shuo Li", "New York").build();
		entry.editField(Properties.ADDRESS, null);
	}

	@Test
	public void testEditFieldWithName() throws InvalidUpdateStringException {
		Entry entry = new Entry.Builder("Shuo Li", "New York").build();
		entry.editField(Properties.NAME, "Andrew Lee");
		String expected = "Andrew Lee";
		String actual = entry.getName();
		assertEquals(expected, actual);
	}

	@Test
	public void testEditFieldWithAddress() throws InvalidUpdateStringException {
		Entry entry = new Entry.Builder("Shuo Li", "New York").build();
		entry.editField(Properties.ADDRESS, "New Jersy");
		String expected = "New Jersy";
		String actual = entry.getAddress();
		assertEquals(expected, actual);
	}

	@Test
	public void testEditFieldWithPhoneNum() throws InvalidUpdateStringException {
		Entry entry = new Entry.Builder("Shuo Li", "New York").phoneNum(
				"123-456-7899").build();
		entry.editField(Properties.PHONENUM, null);
		assertNull(entry.getPhoneNum());

		entry.editField(Properties.PHONENUM, "9987-654-321");
		String expected = "9987-654-321";
		String actual = entry.getPhoneNum();
		assertEquals(expected, actual);
	}

	@Test
	public void testEditFieldWithEmail() throws InvalidUpdateStringException {
		Entry entry = new Entry.Builder("Shuo Li", "New York").email(
				"abc123@nyu.edu").build();
		entry.editField(Properties.EMAIL, null);
		assertNull(entry.getEmail());

		entry.editField(Properties.EMAIL, "123abc@nyu.edu");
		String expected = "123abc@nyu.edu";
		String actual = entry.getEmail();
		assertEquals(expected, actual);
	}

	@Test
	public void testEditFieldWithNote() throws InvalidUpdateStringException {
		Entry entry = new Entry.Builder("Shuo Li", "New York").note(
				"This is a test.").build();
		entry.editField(Properties.NOTE, null);
		assertNull(entry.getNote());

		entry.editField(Properties.NOTE, "It is edited.");
		String expected = "It is edited.";
		String actual = entry.getNote();
		assertEquals(expected, actual);
	}

	@Test
	public void testObjectsEquals() {
		Entry entry = new Entry.Builder("Shuo Li", "New York").build();
		Entry mockEntry = new Entry.Builder("Shuo Li", "New York").build();
		assertEquals(entry, mockEntry);
		assertEquals(entry, entry);
		assertFalse(entry.equals(null));
		assertFalse(entry.equals(new String("This is a test.")));
		assertTrue(entry.equals(mockEntry));
	}

	@Test
	public void testNameEquals() throws InvalidUpdateStringException {
		Entry entry = new Entry.Builder("Shuo Li", "New York").build();
		Entry mockEntry = new Entry.Builder("Andrew Lee", "New York").build();
		assertFalse(entry.equals(mockEntry));

		mockEntry.editField(Properties.NAME, "Shuo Li");
		assertTrue(entry.equals(mockEntry));
	}

	@Test
	public void testAddressEquals() throws InvalidUpdateStringException {
		Entry entry = new Entry.Builder("Shuo Li", "New York").build();
		Entry mockEntry = new Entry.Builder("Shuo Li", "New Jersy").build();
		assertFalse(entry.equals(mockEntry));

		mockEntry.editField(Properties.ADDRESS, "New York");
		assertTrue(entry.equals(mockEntry));
	}

	@Test
	public void testPhoneNumEquals() throws InvalidUpdateStringException {
		Entry entry = new Entry.Builder("Shuo Li", "New York").phoneNum(
				"123-456-7899").build();
		Entry mockEntry = new Entry.Builder("Shuo Li", "New York").build();
		assertFalse(entry.equals(mockEntry));

		mockEntry.editField(Properties.PHONENUM, "123-456-7890");
		assertFalse(entry.equals(mockEntry));

		mockEntry.editField(Properties.PHONENUM, "123-456-7899");
		assertTrue(entry.equals(mockEntry));
	}

	@Test
	public void testEmailEquals() throws InvalidUpdateStringException {
		Entry entry = new Entry.Builder("Shuo Li", "New York").email(
				"abc123@nyu.edu").build();
		Entry mockEntry = new Entry.Builder("Shuo Li", "New York").build();
		assertFalse(entry.equals(mockEntry));

		mockEntry.editField(Properties.EMAIL, "123abc@nyu.edu");
		assertFalse(entry.equals(mockEntry));

		mockEntry.editField(Properties.EMAIL, "abc123@nyu.edu");
		assertTrue(entry.equals(mockEntry));
	}

	@Test
	public void testNoteEquals() throws InvalidUpdateStringException {
		Entry entry = new Entry.Builder("Shuo Li", "New York").note(
				"This is a test.").build();
		Entry mockEntry = new Entry.Builder("Shuo Li", "New York").build();
		assertFalse(entry.equals(mockEntry));

		mockEntry.editField(Properties.NOTE, "This is not a test.");
		assertFalse(entry.equals(mockEntry));

		mockEntry.editField(Properties.NOTE, "This is a test.");
		assertTrue(entry.equals(mockEntry));
	}

	@Test
	public void testHashCodeWithNull() {
		Entry entry = new Entry.Builder(null, null).build();
		Entry mockEntry = new Entry.Builder(null, null).build();
		int expectedHashCode = entry.hashCode();
		int actualHashCode = mockEntry.hashCode();
		assertEquals(expectedHashCode, actualHashCode);
	}

	@Test
	public void testHashCode() {
		Entry entry = new Entry.Builder("Shuo Li", "New York").build();
		Entry mockEntry = new Entry.Builder("Shuo Li", "New York").build();
		int expectedHashCode = entry.hashCode();
		int actualHashCode = mockEntry.hashCode();
		assertEquals(expectedHashCode, actualHashCode);
	}

	public void testHashCodeWithPhoneNumHelper(String phoneNum) {
		Entry entry = new Entry.Builder("Shuo Li", "New York").phoneNum(phoneNum)
				.build();
		Entry mockEntry = new Entry.Builder("Shuo Li", "New York").phoneNum(
				phoneNum).build();
		int expectedHashCode = entry.hashCode();
		int actualHashCode = mockEntry.hashCode();
		assertEquals(expectedHashCode, actualHashCode);
	}

	@Test
	public void testHashCodeWithPhoneNum() {
		testHashCodeWithPhoneNumHelper(null);
		testHashCodeWithPhoneNumHelper("123-456-7899");
	}

	public void testHashCodeWithEmailHelper(String email) {
		Entry entry = new Entry.Builder("Shuo Li", "New York").email(email).build();
		Entry mockEntry = new Entry.Builder("Shuo Li", "New York").email(email)
				.build();
		int expectedHashCode = entry.hashCode();
		int actualHashCode = mockEntry.hashCode();
		assertEquals(expectedHashCode, actualHashCode);
	}

	@Test
	public void testHashCodeWithEmail() {
		testHashCodeWithEmailHelper(null);
		testHashCodeWithEmailHelper("abc123@nyu.edu");
	}

	public void testHashCodeWithNoteHelper(String note) {
		Entry entry = new Entry.Builder("Shuo Li", "New York").note(note).build();
		Entry mockEntry = new Entry.Builder("Shuo Li", "New York").note(note)
				.build();
		int expectedHashCode = entry.hashCode();
		int actualHashCode = mockEntry.hashCode();
		assertEquals(expectedHashCode, actualHashCode);
	}

	@Test
	public void testHashCodeWithNote() {
		testHashCodeWithNoteHelper(null);
		testHashCodeWithNoteHelper("This is a test.");
	}

	@Test
	public void testToString() {
		Entry entry = new Entry.Builder("Shuo Li", "New York")
				.phoneNum("123-456-7899").email("abc123@nyu.edu")
				.note("This is a test.").build();
		String expectedString = "Shuo Li~#~New York~#~123-456-7899~#~abc123@nyu.edu~#~This is a test.";
		String actualString = entry.toString();
		assertEquals(expectedString, actualString);
	}
}
