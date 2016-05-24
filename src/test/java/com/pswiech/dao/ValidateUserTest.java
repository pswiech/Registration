package com.pswiech.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class ValidateUserTest {

	@Test
	public void shouldHaveMinimalLenghtOfFiveCharacters() throws Exception {
		boolean result;
		
		String testString = null;
		result = RegisterDao.isFiveOrMoreCharsLong(testString);
		assertFalse(result);
		
		testString = "";
		result = RegisterDao.isFiveOrMoreCharsLong(testString);
		assertFalse(result);
		
		testString = "1234";
		result = RegisterDao.isFiveOrMoreCharsLong(testString);
		assertFalse(result);
		
		testString = "12345";
		result = RegisterDao.isFiveOrMoreCharsLong(testString);
		assertTrue(result);
		
		testString = "123456";
		result = RegisterDao.isFiveOrMoreCharsLong(testString);
		assertTrue(result);
	}

    @Test
    public void shouldBeAlphaNumeric() throws Exception {
    	boolean result;

    	String testString = "";
		result = RegisterDao.isAlphaNumeric(testString);
		assertFalse(result);

		testString = "!";
		result = RegisterDao.isAlphaNumeric(testString);
		assertFalse(result);

		testString = "notA!ph4NumeR1C";
		result = RegisterDao.isAlphaNumeric(testString);
		assertFalse(result);

		testString = "notA!ph4NumeR1C";
		result = RegisterDao.isAlphaNumeric(testString);
		assertFalse(result);

		testString = "not alpha";
		result = RegisterDao.isAlphaNumeric(testString);
		assertFalse(result);
		
		testString = "notAlpha.";
		result = RegisterDao.isAlphaNumeric(testString);
		assertFalse(result);

		testString = "alpha1num3R1C2";
		result = RegisterDao.isAlphaNumeric(testString);
		assertTrue(result);
    }
}
