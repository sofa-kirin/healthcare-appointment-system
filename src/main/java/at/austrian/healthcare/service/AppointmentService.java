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

    public void createAppointment(long patientId,
                                  long doctorId,
                                  LocalDateTime dateTime,
                                  String reason) {

        if (patientRepository.findById(patientId) == null) {
            throw new IllegalArgumentException(
                    "Patient with id " + patientId + " does not exist"
            );
        }

        if (dateTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException(
                    "Appointment time cannot be in the past"
            );
        }

        List<Appointment> patientAppointments =
                appointmentRepository.findByPatientId(patientId);

        for (int i = 0; i < patientAppointments.size(); i++) {
            if (patientAppointments.get(i).getDateTime().equals(dateTime)) {
                throw new IllegalArgumentException(
                        "Patient already has an appointment at " + dateTime
                );
            }
        }

        long id = nextId++;

        Appointment appointment =
                new Appointment(id, patientId, doctorId, dateTime, reason);

        appointmentRepository.addAppointment(appointment);
    }

    public List<Appointment> getAppointmentsByPatientId(long patientId) {

        if (patientRepository.findById(patientId) == null) {
            throw new IllegalArgumentException(
                    "Patient with id " + patientId + " does not exist"
            );
        }

        return appointmentRepository.findByPatientId(patientId);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
}
