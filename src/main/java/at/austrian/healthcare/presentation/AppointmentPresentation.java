package at.austrian.healthcare.presentation;

import at.austrian.healthcare.model.Appointment;
import at.austrian.healthcare.service.AppointmentService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class AppointmentPresentation {

    private final AppointmentService appointmentService;
    private final Scanner scanner;

    public AppointmentPresentation(AppointmentService appointmentService,
                                   Scanner scanner) {
        this.appointmentService = appointmentService;
        this.scanner = scanner;
    }

    public void start() {
        String choice;

        do {
            printMenu();
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createAppointment();
                    break;
                case "2":
                    showAppointmentsByPatientId();
                    break;
                case "3":
                    showAllAppointments();
                    break;
                case "0":
                    System.out.println("Back to main menu...");
                    break;
                default:
                    System.out.println("Unknown option. Please enter 0-3.");
            }
        } while (!choice.equals("0"));
    }

    private void printMenu() {
        System.out.println();
        System.out.println("===== Appointment Management =====");
        System.out.println("1 - Create a new appointment");
        System.out.println("2 - Get appointments by patient id");
        System.out.println("3 - Get all appointments");
        System.out.println("0 - Back / Exit");
        System.out.println("==================================");
        System.out.print("Choose an option: ");
    }

    private void createAppointment() {
        try {
            System.out.print("Enter patient ID: ");
            long patientId = Long.parseLong(scanner.nextLine());

            System.out.print("Enter doctor ID: ");
            long doctorId = Long.parseLong(scanner.nextLine());

            System.out.print("Enter appointment date (YYYY-MM-DD): ");
            LocalDate date = LocalDate.parse(scanner.nextLine());

            System.out.print("Enter appointment time (HH:MM): ");
            LocalTime time = LocalTime.parse(scanner.nextLine());

            LocalDateTime dateTime = LocalDateTime.of(date, time);

            System.out.print("Enter reason for visit: ");
            String reason = scanner.nextLine();

            appointmentService.createAppointment(
                    patientId,
                    doctorId,
                    dateTime,
                    reason
            );

            System.out.println("Appointment created successfully.");

        } catch (Exception e) {
            System.out.println("Cannot create appointment: " + e.getMessage());
        }
    }

    private void showAppointmentsByPatientId() {
        try {
            System.out.print("Enter patient ID: ");
            long patientId = Long.parseLong(scanner.nextLine());

            List<Appointment> appointments =
                    appointmentService.getAppointmentsByPatientId(patientId);

            if (appointments.isEmpty()) {
                System.out.println("No appointments found for this patient.");
                return;
            }

            for (int i = 0; i < appointments.size(); i++) {
                System.out.println(appointments.get(i));
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void showAllAppointments() {
        List<Appointment> appointments =
                appointmentService.getAllAppointments();

        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }

        for (int i = 0; i < appointments.size(); i++) {
            System.out.println(appointments.get(i));
        }
    }
}

