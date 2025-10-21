package io.github.ta21multiverse.fincorecli;

import java.util.regex.Pattern;

public class User {
    private String name;
    private String emailAddress;
    private String phoneNumber;

    public User(String name, String emailAddress, String phoneNumber) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        // Use a regular expression to check whether the email is valid
        final String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!Pattern.compile(emailRegex).matcher(emailAddress).matches()) {
            throw new IllegalArgumentException("Email address in incorrect format");
        }

        // Else the email address is valid
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws IllegalArgumentException {
        // Validate phone number
        if (!(phoneNumber.startsWith("+") || Character.isDigit(phoneNumber.charAt(0)))) {
            throw new IllegalArgumentException("Invalid phone number");
        }
        for (char c : phoneNumber.substring(1).toCharArray()) {
            if (!Character.isDigit(c)) throw new IllegalArgumentException("Invalid phone number");
        }

        // Else the phone number is valid
        this.phoneNumber = phoneNumber;
    }
}
