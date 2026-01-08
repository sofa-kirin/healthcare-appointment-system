package at.austrian.healthcare.presentation;

import at.austrian.healthcare.model.Doctor;
import at.austrian.healthcare.service.AppointmentService;
import at.austrian.healthcare.service.DoctorService;

import java.util.List;
import java.util.Scanner;

public class MainPresentation {

    private final Scanner scanner;
    private final PatientPresentation patientPresentation;
    private final AppointmentPresentation appointmentPresentation;
    private final DoctorService doctorService;
    private final AppointmentService appointmentService;

    public MainPresentation(Scanner scanner,
                            PatientPresentation patientPresentation,
                            AppointmentPresentation appointmentPresentation,
                            DoctorService doctorService,
                            AppointmentService appointmentService) {

        this.scanner = scanner;
        this.patientPresentation = patientPresentation;
        this.appointmentPresentation = appointmentPresentation;
        this.doctorService = doctorService;
        this.appointmentService = appointmentService;
    }

    public void start() {
        boolean running = true;

        while (running) {
            printMainMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    patientPresentation.start();
                    break;

                case "2":
                    appointmentPresentation.start();
                    break;

                case "3":
                    handleDoctorMode();
                    break;

                case "0":
                    System.out.println("Bye!");
                    running = false;
                    break;

                default:
                    System.out.println("Unknown option. Please enter 0-3.");
            }
        }
    }

    // Doctor Flow

    private void handleDoctorMode() {
        List<String> specializations = doctorService.getAllSpecializations();

        if (specializations.isEmpty()) {
            System.out.println("No doctors available.");
            return;
        }

        while (true) {
            System.out.println();
            System.out.println("=== Choose specialization ===");

            for (int i = 0; i < specializations.size(); i++) {
                System.out.println((i + 1) + " - " + specializations.get(i));
            }
            System.out.println("0 - Back");
            System.out.print("Your choice: ");

            String input = scanner.nextLine();

            if (input.equals("0")) {
                return;
            }

            try {
                int index = Integer.parseInt(input) - 1;

                if (index < 0 || index >= specializations.size()) {
                    System.out.println("Invalid choice.");
                    continue;
                }

                String specialization = specializations.get(index);
                handleDoctorSelectionBySpecialization(specialization);
                return;

            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    private void handleDoctorSelectionBySpecialization(String specialization) {
        List<Doctor> doctors =
                doctorService.getDoctorsBySpecialization(specialization);

        if (doctors.isEmpty()) {
            System.out.println("No doctors found for this specialization.");
            return;
        }

        while (true) {
            System.out.println();
            System.out.println("=== Doctors (" + specialization + ") ===");

            for (int i = 0; i < doctors.size(); i++) {
                Doctor doctor = doctors.get(i);
                System.out.println(
                        (i + 1) + " - " +
                                doctor.getFirstName() + " " +
                                doctor.getLastName()
                );
            }

            System.out.println("0 - Back");
            System.out.print("Choose doctor: ");

            String input = scanner.nextLine();

            if (input.equals("0")) {
                return;
            }

            try {
                int index = Integer.parseInt(input) - 1;

                if (index < 0 || index >= doctors.size()) {
                    System.out.println("Invalid choice.");
                    continue;
                }

                Doctor selectedDoctor = doctors.get(index);

                DoctorPresentation doctorPresentation =
                        new DoctorPresentation(
                                appointmentService,
                                scanner,
                                selectedDoctor.getId()
                        );

                doctorPresentation.start();
                return;

            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    // Main Menu

    private void printMainMenu() {
        System.out.println();
        System.out.println("===== Healthcare System =====");
        System.out.println("1 - Patients");
        System.out.println("2 - Appointments");
        System.out.println("3 - Doctors");
        System.out.println("0 - Exit");
        System.out.println("=============================");
        System.out.print("Choose an option: ");
    }
}

