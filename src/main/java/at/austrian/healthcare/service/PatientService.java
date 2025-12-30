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
        if (repository.findById(patient.getId()) != null) {
            throw new IllegalArgumentException("Patient with id: " + patient.getId() + " already exist");
        }
        repository.addPatient(patient);
    }

    public Patient getPatientById(long id) {
        Patient patient = repository.findById(id);

        if (patient == null) {
            throw new IllegalArgumentException("Patient with id: " + id + " does not exist");
        }

        return patient;
    }

    public List<Patient> getAllPatients() {
        return repository.findAll();
    }
}
