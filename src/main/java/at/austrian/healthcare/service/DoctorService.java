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

    public void deleteDoctor(String firstName,
                             String lastName,
                             String specialization) {

        Doctor doctor = validateAndFindDoctor(
                firstName, lastName, specialization
        );

        doctorRepository.deleteById(doctor.getId());
    }

    public void validateFirstNameOrThrow(String firstName) {
        firstName = firstName.trim();

        for (Doctor d : doctorRepository.findAllDoctors()) {
            if (d.getFirstName().equalsIgnoreCase(firstName)) {
                return;
            }
        }

        throw new IllegalArgumentException(
                "No doctor with first name: " + firstName
        );
    }

    public void validateFullNameOrThrow(String firstName, String lastName) {
        firstName = firstName.trim();
        lastName = lastName.trim();

        for (Doctor d : doctorRepository.findAllDoctors()) {
            if (d.getFirstName().equalsIgnoreCase(firstName)
                    && d.getLastName().equalsIgnoreCase(lastName)) {
                return;
            }
        }

        throw new IllegalArgumentException(
                "No doctor " + firstName + " " + lastName
        );
    }

    private Doctor validateAndFindDoctor(
            String firstName,
            String lastName,
            String specialization) {

        firstName = firstName.trim();
        lastName = lastName.trim();
        specialization = specialization.trim();

        boolean firstNameExists = false;
        boolean fullNameExists = false;

        for (Doctor doctor : doctorRepository.findAllDoctors()) {

            if (doctor.getFirstName().equalsIgnoreCase(firstName)) {
                firstNameExists = true;
            }

            if (doctor.getFirstName().equalsIgnoreCase(firstName)
                    && doctor.getLastName().equalsIgnoreCase(lastName)) {
                fullNameExists = true;
            }

            if (doctor.getFirstName().equalsIgnoreCase(firstName)
                    && doctor.getLastName().equalsIgnoreCase(lastName)
                    && doctor.getSpecialization().equalsIgnoreCase(specialization)) {
                return doctor;
            }
        }

        if (!firstNameExists) {
            throw new IllegalArgumentException(
                    "No doctor with first name: " + firstName
            );
        }

        if (!fullNameExists) {
            throw new IllegalArgumentException(
                    "No doctor " + firstName + " " + lastName
            );
        }

        throw new IllegalArgumentException(
                "Doctor " + firstName + " " + lastName +
                        " is not specialized in " + specialization
        );
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

