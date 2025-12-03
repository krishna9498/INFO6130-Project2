package jlin2.examples.localtesting

import jlin2.examples.localtesting.EmailValidator.Companion.isValidEmail
import org.junit.Assert
import org.junit.Test

class EmailValidatorTest {
    @Test
    fun emailValidator_CorrectEmailSimple_ReturnsTrue() {
        Assert.assertTrue(isValidEmail("123@abc.com"))
    }

    @Test
    fun emailValidator_CorrectEmailSubdomain_ReturnsTrue() {
        Assert.assertTrue(isValidEmail("123@abc.co.ca"))
    }

    @Test
    fun emailValidator_InvalidEmailNoDomain_ReturnsFalse() {
        Assert.assertFalse(isValidEmail("123@abc"))
    }

    @Test
    fun emailValidator_InvalidEmailDoubleDots_ReturnsFalse() {
        Assert.assertFalse(isValidEmail("123@abc..com"))
    }

    @Test
    fun emailValidator_InvalidEmailNoUsername_ReturnsFalse() {
        Assert.assertFalse(isValidEmail("@abc.com"))
    }

    @Test
    fun emailValidator_InvalidEmailNoDomainAt_ReturnsFalse() {
        Assert.assertFalse(isValidEmail("testing123"))
    }

    @Test
    fun emailValidator_InvalidEmailEmptyString_ReturnsFalse() {
        Assert.assertFalse(isValidEmail(""))
    }

    @Test
    fun emailValidator_InvalidEmailNull_ReturnsFalse() {
        Assert.assertFalse(isValidEmail(null))
    }
}