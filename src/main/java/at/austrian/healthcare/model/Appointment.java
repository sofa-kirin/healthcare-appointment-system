package at.austrian.healthcare.model;

import java.time.LocalDateTime;

public class Appointment {

    private final long id;
    private final String patientSocialSecurityNumber;
    private final long doctorId;
    private final LocalDateTime dateTime;
    private final String reason;

    public Appointment(long id,
                       String patientSocialSecurityNumber,
                       long doctorId,
                       LocalDateTime dateTime,
                       String reason) {
        this.id = id;
        this.patientSocialSecurityNumber = patientSocialSecurityNumber;
        this.doctorId = doctorId;
        this.dateTime = dateTime;
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

    public LocalDateTime getDateTime() {
        return dateTime;
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
                ", dateTime=" + dateTime +
                ", reason='" + reason + '\'' +
                '}';
    }
}

