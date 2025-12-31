package at.austrian.healthcare.service;

import at.austrian.healthcare.model.Doctor;
import at.austrian.healthcare.repository.DoctorRepository;

import java.util.List;

public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository){
        this.doctorRepository = doctorRepository;
    }

    public void addDoctor(long id, String firstName, String lastName, String specialization){
        if(id <= 0 || firstName == null || lastName == null || specialization == null){
            throw new IllegalArgumentException("Invalid doctor data");
        }
        if(doctorRepository.findDoctorById(id) != null){
            throw new IllegalArgumentException("Doctor already exists");
        }
        Doctor doctor = new Doctor(id, firstName, lastName, specialization);
        doctorRepository.addDoctor(doctor);
    }

    public Doctor findDoctorById(long id){
        if(doctorRepository.findDoctorById(id) == null){
            throw new IllegalArgumentException("Doctor with ID: " + id + " does not exist");
        }
        return doctorRepository.findDoctorById(id);
    }

    public List<Doctor> findAllDoctors(){
        return doctorRepository.findAllDoctors();
    }
}
