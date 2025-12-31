package at.austrian.healthcare.repository;

import at.austrian.healthcare.model.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorRepository {

    private List<Doctor> doctors = new ArrayList<>();

    public void addDoctor (Doctor doctor){
        doctors.add(doctor);
    }

    public Doctor findDoctorById(long id){
        for(int i = 0; i < doctors.size(); i++){
            if(doctors.get(i).getId() == id){
                return doctors.get(i);
            }
        }
        return null;
    }

    public List<Doctor> findAllDoctors(){
        return new ArrayList<>(doctors);
    }
}
