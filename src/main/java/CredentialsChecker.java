

public class CredentialsChecker {

    private static final String FIRST_NAME_REGEX = "^(([A-Za-z]{2,})|([A-Za-z]+[\\-'][A-Za-z]+))+$";
    private static final String LAST_NAME_REGEX = "^(([A-Za-z]{2,})|([A-Za-z]+[\\-'\\s][A-Za-z]+)[\\s-]*)+$";
    private static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z0-9]{1,20}$";

    public static boolean isValidCredentials(String input) {
        String[] inputParts = input.split(" ");

        if (inputParts.length < 3) {
            System.out.println("Incorrect credentials.");
            return false;
        }

        String firstName = inputParts[0];
        if (!isValidFirstName(firstName)) {
            System.out.println("Incorrect first name.");
            return false;
        }

        String email = inputParts[inputParts.length - 1];
        if (!isValidEmail(email)) {
            System.out.println("Incorrect email.");
            return false;
        }

        String lastName = "";
        for (int i = 1; i < inputParts.length - 1; i++) {
            lastName += inputParts[i] + " ";
        }
        lastName = lastName.substring(0, lastName.length() - 1);
        if (!isValidLastName(lastName)) {
            System.out.println("Incorrect last name.");
            return false;
        }

        return true;
    }

    public static boolean isValidFirstName(String firstName) {
        return firstName.matches(FIRST_NAME_REGEX);
    }

    public static boolean isValidLastName(String lastName) {
        return lastName.matches(LAST_NAME_REGEX);
    }

    public static boolean isValidEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }
}
