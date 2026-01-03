package at.austrian.healthcare.service;

import at.austrian.healthcare.model.Appointment;
import at.austrian.healthcare.repository.AppointmentRepository;
import at.austrian.healthcare.repository.PatientRepository;
import at.austrian.healthcare.service.DoctorService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;

public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorService doctorService;
    private long nextId = 1;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              PatientRepository patientRepository, DoctorService doctorService) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorService = doctorService;
    }

    public void createAppointment(String patientSocialSecurityNumber,
                                  long doctorId,
                                  LocalDate date,
                                  LocalTime time,
                                  String reason) {

        doctorService.findDoctorById(doctorId);

        if (patientRepository.findBySocialSecurityNumber(patientSocialSecurityNumber) == null) {
            throw new IllegalArgumentException(
                    "Patient with social security number " +
                            patientSocialSecurityNumber + " does not exist"
            );
        }

        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();

        if (date.isBefore(today) ||
                (date.equals(today) && time.isBefore(now))) {
            throw new IllegalArgumentException(
                    "Appointment time cannot be in the past"
            );
        }

        List<Appointment> patientAppointments =
                appointmentRepository.findByPatientSocialSecurityNumber(
                        patientSocialSecurityNumber
                );

        for (int i = 0; i < patientAppointments.size(); i++) {
            Appointment a = patientAppointments.get(i);

            if (a.getDate().equals(date) && a.getTime().equals(time)) {
                throw new IllegalArgumentException(
                        "Patient already has an appointment at " +
                                date + " " + time
                );
            }
        }

        long id = nextId++;

        Appointment appointment =
                new Appointment(
                        id,
                        patientSocialSecurityNumber,
                        doctorId,
                        date,
                        time,
                        reason
                );

        appointmentRepository.addAppointment(appointment);
    }

    public List<Appointment> getByDoctorIdAndPatient(
            long doctorId,
            String patientSocialSecurityNumber) {

        if (!doctorService.existsById(doctorId)) {
            throw new IllegalArgumentException(
                    "Doctor with ID: " + doctorId + " does not exist"
            );
        }

        if (patientRepository.findBySocialSecurityNumber(
                patientSocialSecurityNumber) == null) {
            throw new IllegalArgumentException(
                    "Patient with social security number "
                            + patientSocialSecurityNumber
                            + " does not exist"
            );
        }

        List<Appointment> doctorAppointments =
                appointmentRepository.findByDoctorId(doctorId);

        List<Appointment> result = new ArrayList<>();

        for (int i = 0; i < doctorAppointments.size(); i++) {
            Appointment a = doctorAppointments.get(i);

            if (a.getPatientSocialSecurityNumber()
                    .equals(patientSocialSecurityNumber)) {
                result.add(a);
            }
        }
        return result;
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

    public List<Appointment> getAppointmentsForDoctor(long doctorId) {
        if (!doctorService.existsById(doctorId)) {
            throw new IllegalArgumentException(
                    "Doctor with ID: " + doctorId + " does not exist"
            );
        }
        return appointmentRepository.findByDoctorId(doctorId);
    }

    public List<Appointment> getByDoctorIdAndDate(
            long doctorId,
            LocalDate date
    ) {
        if (!doctorService.existsById(doctorId)) {
            throw new IllegalArgumentException(
                    "Doctor with ID: " + doctorId + " does not exist"
            );
        }

        List<Appointment> doctorAppointments =
                appointmentRepository.findByDoctorId(doctorId);
        List<Appointment> result = new ArrayList<>();

        for (int i = 0; i < doctorAppointments.size(); i++) {
            Appointment a = doctorAppointments.get(i);

            if (a.getDate().equals(date)) {
                result.add(a);
            }
        }

        return result;
    }

    public List<Appointment> getByDoctorIdAndTime(long doctorId, LocalTime time){
        if(!doctorService.existsById(doctorId)){
            throw new IllegalArgumentException("Doctor with ID: " + doctorId + " does not exist");
        }

        List<Appointment> doctorAppointments =
                appointmentRepository.findByDoctorId(doctorId);
        List<Appointment> result = new ArrayList<>();

        for (int i = 0; i < doctorAppointments.size(); i++){
            Appointment a = doctorAppointments.get(i);

            if(a.getTime().equals(time)){
                result.add(a);
            }
        }
        return result;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
}

