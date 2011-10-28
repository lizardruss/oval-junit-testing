package net.sf.oval.testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AssertTest {

	private Person person;

	@Before
	public void setUp() throws Exception {
		person = new Person();
		person.setFirstName("Russell");
		person.setLastName("Centanni");
	}

	@Test
	public void testAssertErrorCodesObjectStringArray() {
		person.setFirstName("");
		Assert.assertErrorCodes(person, "person.firstname.empty");
	}

	@Test
	public void testAssertErrorCodesObjectStringStringArray() {
		person.setFirstName(null);
		Assert.assertErrorCodes(person, "person.firstname.null");
	}

	@Test(expected = AssertionError.class)
	public void testAssertErrorCodesObjectStringStringArrayNoMatch() {
		person.setFirstName(null);

		try {
			Assert.assertErrorCodes(person, "person.firstname.pattern");
		} catch (AssertionError exception) {
			assertEquals(
					"None of the following validation errors were found: [person.firstname.pattern]",
					exception.getMessage());
			throw exception;
		}
	}

	@Test
	public void testAssertValidObject() {
		Assert.assertValid(person);
	}

	@Test(expected = AssertionError.class)
	public void testAssertInvalidObject() {
		person.setFirstName(null);

		try {
			Assert.assertValid(person);
		} catch (AssertionError exception) {
			assertEquals(
					"Validation failed with the following constraint violations: [net.sf.oval.ConstraintViolation: net.sf.oval.testing.Person.firstName cannot be null]",
					exception.getMessage());
			throw exception;
		}
	}

	@Test(expected = AssertionError.class)
	public void testAssertValidObjectString() {
		person.setFirstName("");

		try {
			Assert.assertValid(person, "notempty");
		} catch (AssertionError exception) {
			assertEquals(
					"Validation failed with the following constraint violations: [net.sf.oval.ConstraintViolation: net.sf.oval.testing.Person.firstName cannot be empty]",
					exception.getMessage());
			throw exception;
		}
	}

}
