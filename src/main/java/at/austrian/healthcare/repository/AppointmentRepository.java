package at.austrian.healthcare.repository;

import at.austrian.healthcare.model.Appointment;

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
            if (appointments.get(i)
                    .getPatientSocialSecurityNumber()
                    .equals(socialSecurityNumber)) {
                result.add(appointments.get(i));
            }
        }

        return result;
    }

    public List<Appointment> findAll() {
        return new ArrayList<>(appointments);
    }
}

