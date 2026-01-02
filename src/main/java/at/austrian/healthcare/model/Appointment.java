package at.austrian.healthcare.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {

    private final long id;
    private final String patientSocialSecurityNumber;
    private final long doctorId;
    private final LocalDate date;
    private final LocalTime time;
    private final String reason;

    public Appointment(long id,
                       String patientSocialSecurityNumber,
                       long doctorId,
                       LocalDate date, LocalTime time,
                       String reason) {
        this.id = id;
        this.patientSocialSecurityNumber = patientSocialSecurityNumber;
        this.doctorId = doctorId;
        this.date = date;
        this.time = time;
        this.reason = reason;
    }

    public long getId() {
        return id;
    }

    public String getPatientSocialSecurityNumber() {
        return patientSocialSecurityNumber;
    }

    public long getDoctorId() {
        return doctorId;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime(){
        return time;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", patientSocialSecurityNumber=" + patientSocialSecurityNumber +
                ", doctorId=" + doctorId +
                ", date=" + date +
                ", time=" + time +
                ", reason='" + reason + '\'' +
                '}';
    }
}

