package at.austrian.healthcare.presentation;

import at.austrian.healthcare.model.Patient;
import at.austrian.healthcare.service.PatientService;

import java.util.Scanner;
import java.util.List;

public class PatientPresentation {

    private final PatientService patientService;
    private final Scanner scanner;

    public PatientPresentation(PatientService patientService, Scanner scanner) {
        this.patientService = patientService;
        this.scanner = scanner;
    }

    public void start() {
        String choice;

        do {
            printMenu();
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    registerPatient();
                    break;
                case "2":
                    getPatientById();
                    break;
                case "3":
                    getAllPatients();
                    break;
                case "0":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Unknown option. Please enter 0-3.");
            }
        }
        while (!choice.equals("0"));

    }

    private void printMenu() {

        System.out.println();
        System.out.println("==== Austrian Healthcare - Patient Menu ====");
        System.out.println("1 - Register a patient");
        System.out.println("2 - Find patient by id");
        System.out.println("3 - Show all patients");
        System.out.println("0 - Exit");
        System.out.print("Your choice: ");

    }

    private void registerPatient() {
        try {
            System.out.println("Enter id: ");
            long id = Long.parseLong(scanner.nextLine());
            System.out.println("Enter first name: ");
            String firstName = scanner.nextLine();
            System.out.println("Enter last name: ");
            String lastName = scanner.nextLine();
            System.out.println("Enter social security number: ");
            String ssn = scanner.nextLine();

            Patient patient = new Patient(id, firstName, lastName, ssn);
            patientService.registerPatient(patient);

            System.out.println("Patient registered successfully.");
        } catch (Exception e) {
            System.out.println("Cannot register patient: " + e.getMessage());
        }
    }

    private void getPatientById() {
        System.out.println();
        System.out.println("---- Find Patient by ID ----");
        try {
            System.out.println("Enter id: ");
            long id = Long.parseLong(scanner.nextLine());
            Patient patient = patientService.getPatientById(id);
            System.out.println("Found: ");
            printPatient(patient);
        } catch (Exception e) {
            System.out.println("Not found: " + e.getMessage());
        }
    }

    private void printPatient(Patient patient) {
        System.out.println();
        System.out.println("===== Patient Information =====");
        System.out.println("ID:                 " + patient.getId());
        System.out.println("First name:         " + patient.getFirstName());
        System.out.println("Last name:          " + patient.getLastName());
        System.out.println("Social security #:  " + patient.getSocialSecurityNumber());
        System.out.println("================================");
    }

    private void getAllPatients() {

        System.out.println();
        System.out.println("---- All Patients ----");

        List<Patient> allPatients = patientService.getAllPatients();
        for (int i = 0; i < allPatients.size(); i++) {
            System.out.println(allPatients.get(i));
        }

        if (allPatients.isEmpty()) {
            System.out.println("(no patients registered)");
            return;
        }
    }

    private String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Value must not be empty.");
        }
    }

    private void pressEnterToContinue() {
        System.out.println();
        System.out.print("Press ENTER to continue...");
        scanner.nextLine();
    }

}
