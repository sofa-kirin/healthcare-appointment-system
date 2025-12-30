package at.austrian.healthcare.model;

public class Patient {

    private final long id;
    private final String firstName;
    private final String lastName;
    private final String socialSecurityNumber;

    public Patient(long id, String firstName, String lastName, String socialSecurityNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id: " + id +
                ", firstName: " + firstName +
                ", lastName: " + lastName +
                ", socialSecurityNumber: " + socialSecurityNumber +
                '}';
    }
}
