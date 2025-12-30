package at.austrian.healthcare.model;

import java.time.LocalDateTime;

public class Appointment {

    private final long id;
    private final long patientId;
    private final long doctorId;
    private final LocalDateTime dateTime;
    private final String reason;

    public Appointment(long id,
                       long patientId,
                       long doctorId,
                       LocalDateTime dateTime,
                       String reason) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.dateTime = dateTime;
        this.reason = reason;
    }

    public long getId() {
        return id;
    }

    public long getPatientId() {
        return patientId;
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
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                ", dateTime=" + dateTime +
                ", reason='" + reason + '\'' +
                '}';
    }
}

