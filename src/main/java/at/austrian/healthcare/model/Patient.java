package at.austrian.healthcare.model;

public class Patient {

    private final String socialSecurityNumber;
    private final String firstName;
    private final String lastName;

    public Patient(String socialSecurityNumber,
                   String firstName,
                   String lastName) {

        this.socialSecurityNumber = socialSecurityNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "socialSecurityNumber: " + socialSecurityNumber +
                ", firstName: " + firstName +
                ", lastName: " + lastName +
                '}';
    }
}

