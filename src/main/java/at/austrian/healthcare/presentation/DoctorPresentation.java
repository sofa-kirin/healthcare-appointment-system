package at.austrian.healthcare.presentation;

import at.austrian.healthcare.model.Appointment;
import at.austrian.healthcare.service.AppointmentService;
import at.austrian.healthcare.util.InputValidator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class DoctorPresentation extends AbstractMenuPresentation {

    private final AppointmentService appointmentService;
    private final long doctorId;

    public DoctorPresentation(AppointmentService appointmentService,
                              Scanner scanner,
                              long doctorId) {
        super(scanner);
        this.appointmentService = appointmentService;
        this.doctorId = doctorId;
    }

    @Override
    protected void printMenu() {
        System.out.println();
        System.out.println("=== Doctor Menu ===");
        System.out.println("1 - Show all appointments");
        System.out.println("2 - Show appointments by patient");
        System.out.println("3 - Show appointments by date");
        System.out.println("4 - Show appointments by time");
        System.out.println("0 - Back");
        System.out.print("Choose option: ");
    }

    @Override
    protected boolean handleChoice(String choice) {
        try {
            int option = InputValidator.requireIntInRange(
                    choice,
                    "Menu choice",
                    0,
                    4
            );

            switch (option) {
                case 1 -> showAllAppointments();
                case 2 -> showAppointmentsByPatient();
                case 3 -> showAppointmentsByDate();
                case 4 -> showAppointmentsByTime();
                case 0 -> {
                    System.out.println("Back to previous menu...");
                    return false;
                }
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return true;
    }

    private void showAllAppointments() {
        System.out.println();
        System.out.println("---- All Appointments ----");

        List<Appointment> appointments =
                appointmentService.getAppointmentsForDoctor(doctorId);

        if (appointments.isEmpty()) {
            System.out.println("(no appointments found)");
            return;
        }

        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }

    private void showAppointmentsByPatient() {
        System.out.println();
        System.out.println("---- Find Appointments by Patient ----");

        try {
            System.out.print("Enter patient social security number: ");
            String ssn = InputValidator.requireDigitsMinLength(
                    scanner.nextLine(),
                    "Social security number",
                    12
            );

            List<Appointment> appointments =
                    appointmentService.getByDoctorIdAndPatient(doctorId, ssn);

            if (appointments.isEmpty()) {
                System.out.println("No appointments found");
                return;
            }

            for (Appointment appointment : appointments) {
                System.out.println(appointment);
            }

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void showAppointmentsByDate() {
        System.out.println();
        System.out.println("---- Find Appointments by Date ----");

        try {
            System.out.print("Enter date (YYYY-MM-DD): ");
            LocalDate date = InputValidator.requireDate(
                    scanner.nextLine(),
                    "Date"
            );

            List<Appointment> appointments =
                    appointmentService.getByDoctorIdAndDate(doctorId, date);

            if (appointments.isEmpty()) {
                System.out.println("No appointments found");
                return;
            }

            for (Appointment appointment : appointments) {
                System.out.println(appointment);
            }

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void showAppointmentsByTime() {
        System.out.println();
        System.out.println("---- Find Appointments by Time ----");

        try {
            System.out.print("Enter time (HH:MM): ");
            LocalTime time = InputValidator.requireTime(
                    scanner.nextLine(),
                    "Time"
            );

            List<Appointment> appointments =
                    appointmentService.getByDoctorIdAndTime(doctorId, time);

            if (appointments.isEmpty()) {
                System.out.println("No appointments found");
                return;
            }

            for (Appointment appointment : appointments) {
                System.out.println(appointment);
            }

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
