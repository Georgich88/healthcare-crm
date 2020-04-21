package domain.people;

/**
 * Patients and observed people.
 */
public class Patient extends Person {

    public Patient() {
    }

    public Patient(String firstName, String middleName, String lastName) {
        this.setFirstName(firstName);
        this.setMiddleName(middleName);
        this.setLastName(lastName);
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getMiddleName() + " " + getLastName();
    }

}
