package at.austrian.healthcare.presentation;

import at.austrian.healthcare.model.Patient;
import at.austrian.healthcare.service.PatientService;
import at.austrian.healthcare.util.InputValidator;

import java.util.Scanner;

public class PatientEntryPresentation {

    private final Scanner scanner;
    private final PatientService patientService;

    public PatientEntryPresentation(Scanner scanner,
                                    PatientService patientService) {
        this.scanner = scanner;
        this.patientService = patientService;
    }

    public String start() {
        System.out.println();
        System.out.println("=== Patient Entry ===");

        while (true) {
            System.out.print("Enter your social security number (or 0 to cancel): ");
            String input = scanner.nextLine();

            if (input.trim().equals("0")) {
                return null;
            }

            try {
                String ssn = InputValidator.requireDigitsMinLength(
                        input,
                        "Social security number",
                        8
                );

                Patient patient =
                        patientService.getPatientBySocialSecurityNumber(ssn);

                System.out.println("Welcome, " +
                        patient.getFirstName() + " " +
                        patient.getLastName());

                return patient.getSocialSecurityNumber();

            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
