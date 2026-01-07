package at.austrian.healthcare.presentation;

import at.austrian.healthcare.model.Appointment;
import at.austrian.healthcare.service.AppointmentService;

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
        switch (choice) {
            case "1":
                showAllAppointments();
                break;
            case "2":
                showAppointmentsByPatient();
                break;
            case "3":
                showAppointmentsByDate();
                break;
            case "4":
                showAppointmentsByTime();
                break;
            case "0":
                System.out.println("Back to previous menu...");
                return false;
            default:
                System.out.println("Unknown option. Please enter 0-4.");
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
            String ssn = scanner.nextLine();

            List<Appointment> appointments =
                    appointmentService.getByDoctorIdAndPatient(doctorId, ssn);

            if (appointments.isEmpty()) {
                System.out.println("No appointments found");
                return;
            }

            for (Appointment appointment : appointments) {
                System.out.println(appointment);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void showAppointmentsByDate() {
        System.out.println();
        System.out.println("---- Find Appointments by Date ----");

        try {
            System.out.print("Enter date (YYYY-MM-DD): ");
            LocalDate date = LocalDate.parse(scanner.nextLine());

            List<Appointment> appointments =
                    appointmentService.getByDoctorIdAndDate(doctorId, date);

            if (appointments.isEmpty()) {
                System.out.println("No appointments found");
                return;
            }

            for (Appointment appointment : appointments) {
                System.out.println(appointment);
            }

        } catch (Exception e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
        }
    }

    private void showAppointmentsByTime() {
        System.out.println();
        System.out.println("---- Find Appointments by Time ----");

        try {
            System.out.print("Enter time (HH:MM): ");
            LocalTime time = LocalTime.parse(scanner.nextLine());

            List<Appointment> appointments =
                    appointmentService.getByDoctorIdAndTime(doctorId, time);

            if (appointments.isEmpty()) {
                System.out.println("No appointments found");
                return;
            }

            for (Appointment appointment : appointments) {
                System.out.println(appointment);
            }

        } catch (Exception e) {
            System.out.println("Invalid time format. Please use HH:MM.");
        }
    }
}
