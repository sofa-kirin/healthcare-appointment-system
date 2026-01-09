package at.austrian.healthcare.service;

import at.austrian.healthcare.model.Doctor;
import at.austrian.healthcare.repository.DoctorRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DoctorService {

    private final DoctorRepository doctorRepository;
    private long nextId = 1;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor addDoctor(String firstName, String lastName, String specialization) {
        if (firstName == null || lastName == null || specialization == null) {
            throw new IllegalArgumentException("Invalid doctor data");
        }

        Doctor doctor = new Doctor(nextId++, firstName, lastName, specialization);
        doctorRepository.addDoctor(doctor);
        return doctor;
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

    public List<String> getAllSpecializations(){

        Set<String> specializations = new HashSet<>();
        for(Doctor doctor : doctorRepository.findAllDoctors()){
            specializations.add(doctor.getSpecialization());
        }
        return new ArrayList<>(specializations);

    }

    public List<Doctor> getDoctorsBySpecialization(String specialization){

        List<Doctor> result = new ArrayList<>();

        for(Doctor doctor : doctorRepository.findAllDoctors()){
            if(doctor.getSpecialization().equalsIgnoreCase(specialization)){
                result.add(doctor);
            }
        }
        return new ArrayList<>(result);

    }

    public boolean existsById(long id) {
        return doctorRepository.existsById(id);
    }

    public List<Doctor> findAllDoctors() {
        return doctorRepository.findAllDoctors();
    }
}

