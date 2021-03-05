package com.test.quizexampleinterview;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidatorTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testIsEmpty() {
        Assert.assertTrue(Validator.isEmpty(""));
        Assert.assertTrue(Validator.isEmpty(" "));
        Assert.assertFalse(Validator.isEmpty("   v"));
    }

    @Test
    public void emailValidator_CorrectEmailSimple_ReturnsTrue() {
        Assert.assertTrue(Validator.isValidEmail("name@email.com"));
    }

    @Test
    public void emailValidator_CorrectEmailSubDomain_ReturnsTrue() {
        Assert.assertTrue(Validator.isValidEmail("name@email.co.uk"));
    }

    @Test
    public void emailValidator_InvalidEmailNoTld_ReturnsFalse() {
        Assert.assertFalse(Validator.isValidEmail("name@email"));
    }

    @Test
    public void emailValidator_InvalidEmailDoubleDot_ReturnsFalse() {
        Assert.assertFalse(Validator.isValidEmail("name@email..com"));
    }

    @Test
    public void emailValidator_InvalidEmailNoUsername_ReturnsFalse() {
        Assert.assertFalse(Validator.isValidEmail("@email.com"));
    }

    @Test
    public void emailValidator_EmptyString_ReturnsFalse() {
        Assert.assertFalse(Validator.isValidEmail(""));
    }

    @Test
    public void emailValidator_NullEmail_ReturnsFalse() {
        Assert.assertFalse(Validator.isValidEmail(null));
    }

    @Test
    public void isValidPassword(){
        Assert.assertTrue(Validator.isValidPassword("Ss2nb#"));
    }

    @Test
    public void password_InSufficient_Characters(){
        Assert.assertFalse(Validator.isValidPassword("Ssb#"));
    }

    @Test
    public void password_Contains_WhiteSpaces(){
        Assert.assertFalse(Validator.isValidPassword("Ss2 nb#"));
    }

    @Test
    public void password_NoUpperCase_letter(){
        Assert.assertFalse(Validator.isValidPassword("sss3sb#"));
    }

    @Test
    public void password_NoLowerCase_letter(){
        Assert.assertFalse(Validator.isValidPassword("SS2BFD#"));
    }

    @Test
    public void password_NoDigits(){
        Assert.assertFalse(Validator.isValidPassword("Ssbsdf#"));
    }

    @Test
    public void password_NoSpecialCharacter(){
        Assert.assertFalse(Validator.isValidPassword("Ssjhg65"));
    }

}