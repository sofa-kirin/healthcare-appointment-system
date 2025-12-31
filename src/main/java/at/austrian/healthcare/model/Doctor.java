package at.austrian.healthcare.model;

public class Doctor {

    private final long id;
    private final String firstName;
    private final String lastName;
    private final String specialization;

    public Doctor(long id,
                  String firstName, String lastName,
                  String specialization) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
    }

    public long getId(){
        return id;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getSpecialization(){
        return specialization;
    }

    @Override
    public String toString() {
        return "Doctor{id='" + id +
                "', firstName='" + firstName +
                "', lastName='" + lastName +
                "', specialization='" + specialization +
                "'}";
    }

}
