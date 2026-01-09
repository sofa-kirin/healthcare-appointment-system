package at.austrian.healthcare.presentation;

import at.austrian.healthcare.model.Patient;
import at.austrian.healthcare.service.PatientService;

import java.util.List;
import java.util.Scanner;

public class PatientPresentation extends AbstractMenuPresentation {

    private final PatientService patientService;

    public PatientPresentation(PatientService patientService, Scanner scanner) {
        super(scanner);
        this.patientService = patientService;
    }

    protected boolean handleChoice(String choice) {

            switch (choice) {
                case "1":
                    registerPatient();
                    break;
                case "2":
                    getPatientBySocialSecurityNumber();
                    break;
                case "3":
                    getAllPatients();
                    break;
                case "0":
                    System.out.println("Back to main menu...");
                    return false;
                default:
                    System.out.println("Unknown option. Please enter 0-3.");
            }
            return true;
    }

    protected void printMenu() {
        System.out.println();
        System.out.println("==== Austrian Healthcare - Patient Menu ====");
        System.out.println("1 - Register a patient");
        System.out.println("2 - Find patient by social security number");
        System.out.println("3 - Show all patients");
        System.out.println("0 - Back");
        System.out.print("Choose option: ");
    }

    private void registerPatient() {
        try {
            System.out.println("Enter social security number: ");
            String ssn = scanner.nextLine();
            System.out.println("Enter first name: ");
            String firstName = scanner.nextLine();
            System.out.println("Enter last name: ");
            String lastName = scanner.nextLine();

            Patient patient = new Patient(ssn, firstName, lastName);
            patientService.registerPatient(patient);

            System.out.println("Patient registered successfully.");
        } catch (Exception e) {
            System.out.println("Cannot register patient: " + e.getMessage());
        }
    }

    private void getPatientBySocialSecurityNumber() {
        System.out.println();
        System.out.println("---- Find Patient by Social Security Number ----");
        try {
            System.out.println("Enter social security number: ");
            String ssn = scanner.nextLine();
            Patient patient =
                    patientService.getPatientBySocialSecurityNumber(ssn);
            printPatient(patient);
        } catch (Exception e) {
            System.out.println("Not found: " + e.getMessage());
        }
    }

    private void printPatient(Patient patient) {
        System.out.println();
        System.out.println("===== Patient Information =====");
        System.out.println("Social security #:  " + patient.getSocialSecurityNumber());
        System.out.println("First name:         " + patient.getFirstName());
        System.out.println("Last name:          " + patient.getLastName());
        System.out.println("================================");
    }

    private void getAllPatients() {
        System.out.println();
        System.out.println("---- All Patients ----");

        List<Patient> allPatients = patientService.getAllPatients();
        if (allPatients.isEmpty()) {
            System.out.println("(no patients registered)");
            return;
        }

        for (int i = 0; i < allPatients.size(); i++) {
            System.out.println(allPatients.get(i));
        }
    }
}
