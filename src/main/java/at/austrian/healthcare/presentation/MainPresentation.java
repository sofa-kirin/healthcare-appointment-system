package at.austrian.healthcare.presentation;

import at.austrian.healthcare.service.AppointmentService;
import at.austrian.healthcare.util.InputValidator;

import java.util.Scanner;

public class MainPresentation {

    private final Scanner scanner;
    private final AppointmentService appointmentService;
    private final AdminPresentation adminPresentation;
    private final DoctorEntryPresentation doctorEntryPresentation;
    private final PatientEntryPresentation patientEntryPresentation;

    public MainPresentation(Scanner scanner,
                            AppointmentService appointmentService,
                            AdminPresentation adminPresentation,
                            DoctorEntryPresentation doctorEntryPresentation,
                            PatientEntryPresentation patientEntryPresentation) {

        this.scanner = scanner;
        this.appointmentService = appointmentService;
        this.adminPresentation = adminPresentation;
        this.doctorEntryPresentation = doctorEntryPresentation;
        this.patientEntryPresentation = patientEntryPresentation;
    }

    public void start() {
        boolean running = true;

        while (running) {
            printMainMenu();
            String input = scanner.nextLine();

            try {
                int choice = InputValidator.requireIntInRange(
                        input,
                        "Menu choice",
                        0,
                        3
                );

                switch (choice) {
                    case 1 -> handlePatientMode();
                    case 2 -> handleDoctorMode();
                    case 3 -> adminPresentation.start();
                    case 0 -> {
                        System.out.println("Bye!");
                        running = false;
                    }
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void handlePatientMode() {
        String ssn = patientEntryPresentation.start();
        if (ssn == null) {
            return;
        }

        PatientPresentation patientPresentation =
                new PatientPresentation(
                        appointmentService,
                        ssn,
                        scanner,
                        doctorEntryPresentation
                );

        patientPresentation.start();
    }

    private void handleDoctorMode() {
        Long doctorId = doctorEntryPresentation.start();
        if (doctorId == null) {
            return;
        }

        DoctorPresentation doctorPresentation =
                new DoctorPresentation(
                        appointmentService,
                        scanner,
                        doctorId
                );

        doctorPresentation.start();
    }

    private void printMainMenu() {
        System.out.println();
        System.out.println("===== Healthcare System =====");
        System.out.println("1 - Patient mode");
        System.out.println("2 - Doctor mode");
        System.out.println("3 - Administrator mode");
        System.out.println("0 - Exit");
        System.out.print("Choose an option: ");
    }
}


