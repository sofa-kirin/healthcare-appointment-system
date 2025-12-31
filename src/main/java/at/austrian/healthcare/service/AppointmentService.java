package at.austrian.healthcare.service;

import at.austrian.healthcare.model.Appointment;
import at.austrian.healthcare.repository.AppointmentRepository;
import at.austrian.healthcare.repository.PatientRepository;

import java.time.LocalDateTime;
import java.util.List;

public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private long nextId = 1;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
    }

    public void createAppointment(String patientSocialSecurityNumber,
                                  long doctorId,
                                  LocalDateTime dateTime,
                                  String reason) {

        if (patientRepository.findBySocialSecurityNumber(
                patientSocialSecurityNumber) == null) {
            throw new IllegalArgumentException(
                    "Patient with social security number "
                            + patientSocialSecurityNumber
                            + " does not exist"
            );
        }

        if (dateTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException(
                    "Appointment time cannot be in the past"
            );
        }

        List<Appointment> patientAppointments =
                appointmentRepository.findByPatientSocialSecurityNumber(
                        patientSocialSecurityNumber
                );

        for (int i = 0; i < patientAppointments.size(); i++) {
            if (patientAppointments.get(i).getDateTime().equals(dateTime)) {
                throw new IllegalArgumentException(
                        "Patient already has an appointment at " + dateTime
                );
            }
        }

        long id = nextId++;

        Appointment appointment =
                new Appointment(
                        id,
                        patientSocialSecurityNumber,
                        doctorId,
                        dateTime,
                        reason
                );

        appointmentRepository.addAppointment(appointment);
    }

    public List<Appointment> getAppointmentsByPatientSocialSecurityNumber(
            String patientSocialSecurityNumber) {

        if (patientRepository.findBySocialSecurityNumber(
                patientSocialSecurityNumber) == null) {
            throw new IllegalArgumentException(
                    "Patient with social security number "
                            + patientSocialSecurityNumber
                            + " does not exist"
            );
        }

        return appointmentRepository
                .findByPatientSocialSecurityNumber(patientSocialSecurityNumber);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
}

