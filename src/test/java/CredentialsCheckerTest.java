import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CredentialsCheckerTest {

    @Test
    void isValidFirstName() {
        assertTrue(CredentialsChecker.isValidFirstName("John"));
        assertTrue(CredentialsChecker.isValidFirstName("Jean-Claude'Li"));
        assertTrue(CredentialsChecker.isValidFirstName("Ay"));
        assertTrue(CredentialsChecker.isValidFirstName("O'Neil"));
        assertTrue(CredentialsChecker.isValidFirstName("na'me"));
        assertTrue(CredentialsChecker.isValidFirstName("n'a"));
        assertTrue(CredentialsChecker.isValidFirstName("oShi"));

        assertFalse(CredentialsChecker.isValidFirstName("n"));
        assertFalse(CredentialsChecker.isValidFirstName("'name"));
        assertFalse(CredentialsChecker.isValidFirstName("-name"));
        assertFalse(CredentialsChecker.isValidFirstName("name-"));
        assertFalse(CredentialsChecker.isValidFirstName("name'"));
        assertFalse(CredentialsChecker.isValidFirstName("nam-'e"));
        assertFalse(CredentialsChecker.isValidFirstName("na'-me"));
        assertFalse(CredentialsChecker.isValidFirstName("na--me"));
        assertFalse(CredentialsChecker.isValidFirstName("na''me"));
        assertFalse(CredentialsChecker.isValidFirstName("námé"));
    }

    @Test
    void isValidLastName() {
        assertTrue(CredentialsChecker.isValidLastName("O'Connor"));
        assertTrue(CredentialsChecker.isValidLastName("Emelianenko"));
        assertTrue(CredentialsChecker.isValidLastName("Jemison Van de Graaff"));
        assertTrue(CredentialsChecker.isValidLastName("s-u"));
        assertTrue(CredentialsChecker.isValidLastName("su aa-b'b"));

        assertFalse(CredentialsChecker.isValidLastName("s"));
        assertFalse(CredentialsChecker.isValidLastName("-surname"));
        assertFalse(CredentialsChecker.isValidLastName("'surname"));
        assertFalse(CredentialsChecker.isValidLastName("surnam''e"));
        assertFalse(CredentialsChecker.isValidLastName("surn--ame"));
        assertFalse(CredentialsChecker.isValidLastName("s'-urname"));
        assertFalse(CredentialsChecker.isValidLastName("su-'rname"));
        assertFalse(CredentialsChecker.isValidLastName("surname-"));
        assertFalse(CredentialsChecker.isValidLastName("surname'"));
        assertFalse(CredentialsChecker.isValidLastName("surnámé"));
        assertFalse(CredentialsChecker.isValidLastName("s"));
    }


    @Test
    void isValidEmail() {
        assertTrue(CredentialsChecker.isValidEmail("ascjke@bk.ru"));
        assertTrue(CredentialsChecker.isValidEmail("12ascjke@bk.ru"));
        assertTrue(CredentialsChecker.isValidEmail("$A12345@example.com"));

        assertFalse(CredentialsChecker.isValidEmail("asc@jke@bk.ru"));
        assertFalse(CredentialsChecker.isValidEmail("ascjke@java8_%&.ru"));
    }
}