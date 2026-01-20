package at.austrian.healthcare.service;

import at.austrian.healthcare.model.Patient;
import at.austrian.healthcare.repository.PatientRepository;

import java.util.List;

public class PatientService {

    private final PatientRepository repository;

    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    public void registerPatient(String ssn, String firstName, String lastName) {
        if (ssn == null || firstName == null || lastName == null) {
            throw new IllegalArgumentException("Invalid patient data");
        }

        Patient patient = new Patient(ssn, firstName, lastName);
        registerPatient(patient);
    }

    public void registerPatient(Patient patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Patient must not be null");
        }

        if (repository.findBySocialSecurityNumber(
                patient.getSocialSecurityNumber()) != null) {
            throw new IllegalArgumentException(
                    "Patient with social security number: "
                            + patient.getSocialSecurityNumber()
                            + " already exists"
            );
        }

        repository.addPatient(patient);
    }

    public void deletePatient(String ssn) {
        Patient patient = findPatientBySsnOrThrow(ssn);
        repository.deleteById(patient.getSocialSecurityNumber());
    }


    private Patient findPatientBySsnOrThrow(String ssn) {
        if (ssn == null) {
            throw new IllegalArgumentException("SSN cannot be null");
        }
        ssn = ssn.trim();
        for (Patient patient : repository.findAll()) {
            if (patient.getSocialSecurityNumber().equalsIgnoreCase(ssn)) {
                return patient;
            }
        }
        throw new IllegalArgumentException("Patient with SSN " + ssn + " does not exist");
    }


    public Patient getPatientBySocialSecurityNumber(String socialSecurityNumber) {
        Patient patient =
                repository.findBySocialSecurityNumber(socialSecurityNumber);

        if (patient == null) {
            throw new IllegalArgumentException(
                    "Patient with social security number: "
                            + socialSecurityNumber
                            + " does not exist"
            );
        }

        return patient;
    }

    public List<Patient> getAllPatients() {
        return repository.findAll();
    }
}
