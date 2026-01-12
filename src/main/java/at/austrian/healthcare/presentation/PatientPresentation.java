package at.austrian.healthcare.presentation;

import at.austrian.healthcare.model.Appointment;
import at.austrian.healthcare.service.AppointmentService;
import at.austrian.healthcare.util.InputValidator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class PatientPresentation extends AbstractMenuPresentation {

    private final AppointmentService appointmentService;
    private final String ssn;
    private final DoctorEntryPresentation doctorEntryPresentation;

    public PatientPresentation(AppointmentService appointmentService,
                               String ssn,
                               Scanner scanner,
                               DoctorEntryPresentation doctorEntryPresentation) {
        super(scanner);
        this.appointmentService = appointmentService;
        this.ssn = ssn;
        this.doctorEntryPresentation = doctorEntryPresentation;
    }

    @Override
    protected boolean handleChoice(String choice) {
        try {
            int option = InputValidator.requireIntInRange(
                    choice,
                    "Menu choice",
                    0,
                    2
            );

            switch (option) {
                case 1 -> showMyAppointments();
                case 2 -> createAppointment();
                case 0 -> {
                    System.out.println("Back to main menu...");
                    return false;
                }
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return true;
    }

    @Override
    protected void printMenu() {
        System.out.println();
        System.out.println("=== Patient Menu ===");
        System.out.println("1 - Show my appointments");
        System.out.println("2 - Create appointment");
        System.out.println("0 - Back");
        System.out.print("Choose option: ");
    }

    private void showMyAppointments() {
        System.out.println();
        System.out.println("---- My Appointments ----");

        List<Appointment> appointments =
                appointmentService.getAppointmentsByPatientSocialSecurityNumber(ssn);

        if (appointments.isEmpty()) {
            System.out.println("(no appointments found)");
            return;
        }

        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }

    private void createAppointment() {
        try {
            System.out.println();
            System.out.println("---- Create Appointment ----");

            Long doctorId = doctorEntryPresentation.start();
            if (doctorId == null) {
                System.out.println("Appointment creation cancelled.");
                return;
            }

            System.out.print("Enter date (YYYY-MM-DD): ");
            LocalDate date = InputValidator.requireDate(
                    scanner.nextLine(),
                    "Date"
            );

            System.out.print("Enter time (HH:MM): ");
            LocalTime time = InputValidator.requireTime(
                    scanner.nextLine(),
                    "Time"
            );

            System.out.print("Enter reason: ");
            String reason = InputValidator.requireNonBlank(
                    scanner.nextLine(),
                    "Reason"
            );

            appointmentService.createAppointment(
                    ssn,
                    doctorId,
                    date,
                    time,
                    reason
            );

            System.out.println("Appointment created successfully.");

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
