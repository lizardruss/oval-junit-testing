package net.sf.oval.testing;

import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

public class Person {
	@NotNull(errorCode = "person.firstname.null")
	@NotEmpty(errorCode = "person.firstname.empty", profiles = "notempty")
	private String firstName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@NotNull(errorCode = "person.lastname.null")
	@NotEmpty(errorCode = "person.lastname.empty", profiles = "notempty")
	private String lastName;

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
