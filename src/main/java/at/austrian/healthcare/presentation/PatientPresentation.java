package at.austrian.healthcare.presentation;

import at.austrian.healthcare.model.Appointment;
import at.austrian.healthcare.service.AppointmentService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class PatientPresentation extends AbstractMenuPresentation {

    private final AppointmentService appointmentService;
    private final String ssn;
    private final DoctorEntryPresentation doctorEntryPresentation;

    public PatientPresentation(AppointmentService appointmentService,
                               String ssn, Scanner scanner,
                               DoctorEntryPresentation doctorEntryPresentation) {
        super(scanner);
        this.appointmentService = appointmentService;
        this.ssn = ssn;
        this.doctorEntryPresentation = doctorEntryPresentation;
    }

    protected boolean handleChoice(String choice) {

            switch (choice) {
                case "1":
                    showMyAppointments();
                    break;
                case "2":
                    createAppointment();
                    break;
                case "0":
                    System.out.println("Back to main menu...");
                    return false;
                default:
                    System.out.println("Unknown option. Please enter 0-2.");
            }
            return true;
    }

    protected void printMenu() {
        System.out.println();
        System.out.println("=== Patient Menu ===");
        System.out.println("1 - Show my appointments");
        System.out.println("2 - Create appointment");
        System.out.println("0 - Back");
        System.out.print("Choose option: ");
    }

    private void showMyAppointments(){
        System.out.println();
        System.out.println("---- My Appointments ----");

        List<Appointment> appointments = appointmentService.getAppointmentsByPatientSocialSecurityNumber(ssn);

        if(appointments.isEmpty()){
            System.out.println("(no appointments found)");
            return;
        }

        for(Appointment appointment: appointments){
            System.out.println(appointment);
        }
    }

    private void createAppointment(){
        try {
            System.out.println();
            System.out.println("---- Create Appointment ----");

            Long doctorId = doctorEntryPresentation.start();
            if (doctorId == null) {
                System.out.println("Appointment creation cancelled.");
                return;
            }

            System.out.print("Enter date (YYYY-MM-DD): ");
            LocalDate date = LocalDate.parse(scanner.nextLine().trim());

            System.out.print("Enter time (HH:MM): ");
            LocalTime time = LocalTime.parse(scanner.nextLine().trim());

            System.out.print("Enter reason: ");
            String reason = scanner.nextLine().trim();

            appointmentService.createAppointment(
                    ssn,
                    doctorId,
                    date,
                    time,
                    reason
            );

            System.out.println("Appointment created successfully.");

        } catch (Exception e) {
            System.out.println("Cannot create appointment: " + e.getMessage());
        }
    }

}
