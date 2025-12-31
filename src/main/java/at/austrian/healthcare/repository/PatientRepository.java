package at.austrian.healthcare.repository;

import at.austrian.healthcare.model.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientRepository {

    private final List<Patient> patients = new ArrayList<>();

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public Patient findBySocialSecurityNumber(String socialSecurityNumber) {
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i)
                    .getSocialSecurityNumber()
                    .equals(socialSecurityNumber)) {
                return patients.get(i);
            }
        }
        return null;
    }

    public List<Patient> findAll() {
        return new ArrayList<>(patients);
    }
}

