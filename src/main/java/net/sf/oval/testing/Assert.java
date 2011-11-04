package net.sf.oval.testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

public class Assert {
	private static String DEFAULT_PROFILE = "default";

	public static void assertErrorCodes(Object object, String... errorCodes) {
		assertErrorCodes(object, DEFAULT_PROFILE, errorCodes);
	}

	public static void assertErrorCodes(Object object, String profile,
			String[] errorCodes) {
		Validator validator = new Validator();
		validator.disableAllProfiles();
		validator.enableProfile(profile);
		
		List<ConstraintViolation> violations = validator.validate(object);
		List<String> violatedErrorCodes = new ArrayList<String>();
		for (ConstraintViolation violation : violations) {
			for (String errorCode : errorCodes) {
				if (errorCode.equals(violation.getErrorCode())) {
					violatedErrorCodes.add(errorCode);
				}
			}
		}

		if (violatedErrorCodes.size() == 0) {
			fail("None of the following validation errors were found: "
					+ Arrays.toString(errorCodes));
		}
	}

	public static void assertValid(Object object) {
		assertValid(object, DEFAULT_PROFILE);
	}

	public static void assertValid(Object object, String profile) {
		Validator validator = new Validator();
		validator.disableAllProfiles();
		validator.enableProfile(profile);

		List<ConstraintViolation> violations = validator.validate(object);
		if (violations.size() > 0) {
			fail("Validation failed with the following constraint violations: "
					+ Arrays.toString(violations.toArray()));
		}
	}
}
