package at.austrian.healthcare.service;

import at.austrian.healthcare.model.Doctor;
import at.austrian.healthcare.repository.DoctorRepository;

import java.util.List;

public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public void addDoctor(long id, String firstName, String lastName, String specialization) {
        if (id <= 0 || firstName == null || lastName == null || specialization == null) {
            throw new IllegalArgumentException("Invalid doctor data");
        }

        if (doctorRepository.existsById(id)) {
            throw new IllegalArgumentException("Doctor already exists");
        }

        Doctor doctor = new Doctor(id, firstName, lastName, specialization);
        doctorRepository.addDoctor(doctor);
    }

    public Doctor findDoctorById(long id) {
        Doctor doctor = doctorRepository.findDoctorById(id);

        if (doctor == null) {
            throw new IllegalArgumentException(
                    "Doctor with ID: " + id + " does not exist"
            );
        }

        return doctor;
    }

    public boolean existsById(long id) {
        return doctorRepository.existsById(id);
    }

    public List<Doctor> findAllDoctors() {
        return doctorRepository.findAllDoctors();
    }
}

