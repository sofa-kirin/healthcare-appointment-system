package at.austrian.healthcare.service;

import at.austrian.healthcare.model.Patient;
import at.austrian.healthcare.repository.PatientRepository;

import java.util.List;

public class PatientService {

    private final PatientRepository repository;

    public PatientService(PatientRepository repository) {
        this.repository = repository;
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
