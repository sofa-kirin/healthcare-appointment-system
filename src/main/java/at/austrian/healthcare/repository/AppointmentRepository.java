package at.austrian.healthcare.repository;

import at.austrian.healthcare.model.Appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentRepository {

    private final List<Appointment> appointments = new ArrayList<>();

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public Appointment findById(long id) {
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getId() == id) {
                return appointments.get(i);
            }
        }
        return null;
    }

    public List<Appointment> findByPatientSocialSecurityNumber(String socialSecurityNumber) {
        List<Appointment> result = new ArrayList<>();

        for (int i = 0; i < appointments.size(); i++) {
            if (socialSecurityNumber.equals(
                    appointments.get(i).getPatientSocialSecurityNumber()))
            {
                result.add(appointments.get(i));
            }
        }

        return result;
    }

    public List<Appointment> findByDoctorId(long doctorId){
        List<Appointment> result = new ArrayList<>();

        for(int i = 0; i < appointments.size(); i++){
            if (appointments.get(i).getDoctorId() == doctorId ){
                result.add(appointments.get(i));
            }
        }

        return result;
    }

    public List<Appointment> findByDate(LocalDate date){
        List<Appointment> result = new ArrayList<>();

        for(int i = 0; i < appointments.size(); i++){
            if(appointments.get(i).getDate().equals(date)){
                result.add(appointments.get(i));
            }
        }

        return result;
    }

    public List<Appointment> findByTime(LocalTime time){
        List<Appointment> result = new ArrayList<>();

        for(int i = 0; i < appointments.size(); i++){
            if(appointments.get(i).getTime().equals(time)){
                result.add(appointments.get(i));
            }
        }

        return result;
    }

    public List<Appointment> findAll() {
        return new ArrayList<>(appointments);
    }
}

