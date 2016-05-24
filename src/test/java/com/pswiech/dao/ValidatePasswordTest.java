package com.pswiech.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class ValidatePasswordTest {

	@Test
	public void shouldHaveMinimalLenghtOfEightCharacters() throws Exception {
		boolean result;
		
		String testString = null;
		result = RegisterDao.isEightOrMoreCharsLong(testString);
		assertFalse(result);
		
		testString = "";
		result = RegisterDao.isEightOrMoreCharsLong(testString);
		assertFalse(result);
		
		testString = "1234567";
		result = RegisterDao.isEightOrMoreCharsLong(testString);
		assertFalse(result);
		
		testString = "12345678";
		result = RegisterDao.isEightOrMoreCharsLong(testString);
		assertTrue(result);
		
		testString = "123456789";
		result = RegisterDao.isEightOrMoreCharsLong(testString);
		assertTrue(result);
	}

    @Test
    public void shouldContainNumber() throws Exception {
    	boolean result;

    	String testString = null;
    	result = RegisterDao.containsNumber(testString);
    	assertFalse(result);

    	testString = "";
    	result = RegisterDao.containsNumber(testString);
    	assertFalse(result);

    	testString = "abcdef";
    	result = RegisterDao.containsNumber(testString);
    	assertFalse(result);

    	testString = "p4ssw0rd";
    	result = RegisterDao.containsNumber(testString);
    	assertTrue(result);
    	
    	testString = "2TeSt";
    	result = RegisterDao.containsNumber(testString);
    	assertTrue(result);
    	
    	testString = "tesT3";
    	result = RegisterDao.containsNumber(testString);
    	assertTrue(result);

    	testString = "12345";
    	result = RegisterDao.containsNumber(testString);
    	assertTrue(result);
    }
    
    @Test
    public void shouldContainUpperCaseChar() throws Exception {
    	boolean result;
    	String testString = null;

    	testString = "";
    	result = RegisterDao.containsUpperCase(testString);
    	assertFalse(result);

    	testString = "abcd";
    	result = RegisterDao.containsUpperCase(testString);
    	assertFalse(result);

    	testString = "aBcd";
    	result = RegisterDao.containsUpperCase(testString);
    	assertTrue(result);
    	
    	testString = "ABCD";
    	result = RegisterDao.containsUpperCase(testString);
    	assertTrue(result);
    }

    @Test
    public void shouldContainLowerCaseChar() throws Exception {
    	boolean result;
    	String testString = null;

    	testString = "";
    	result = RegisterDao.containsLowerCase(testString);
    	assertFalse(result);

    	testString = "ABCD";
    	result = RegisterDao.containsLowerCase(testString);
    	assertFalse(result);

    	testString = "AbCD";
    	result = RegisterDao.containsLowerCase(testString);
    	assertTrue(result);
    	
    	testString = "abcs";
    	result = RegisterDao.containsLowerCase(testString);
    	assertTrue(result);
    }
}
