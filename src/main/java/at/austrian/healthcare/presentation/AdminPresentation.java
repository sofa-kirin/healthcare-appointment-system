package at.austrian.healthcare.presentation;

import at.austrian.healthcare.model.Doctor;
import at.austrian.healthcare.model.Patient;
import at.austrian.healthcare.service.DoctorService;
import at.austrian.healthcare.service.PatientService;
import at.austrian.healthcare.util.InputValidator;

import java.util.List;
import java.util.Scanner;

public class AdminPresentation extends AbstractMenuPresentation {

    private final PatientService patientService;
    private final DoctorService doctorService;

    public AdminPresentation(PatientService patientService,
                             DoctorService doctorService,
                             Scanner scanner) {
        super(scanner);
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    @Override
    protected void printMenu() {
        System.out.println();
        System.out.println("=== Admin Menu ===");
        System.out.println("1 - Add patient");
        System.out.println("2 - Delete patient");
        System.out.println("3 - Show all patients");
        System.out.println("4 - Add doctor");
        System.out.println("5 - Delete doctor");
        System.out.println("6 - Show all doctors");
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
                    5
            );
            switch (option) {
                case 1:
                    addPatient();
                    break;
                case 2:
                    deletePatient();
                    break;
                case 3:
                    showAllPatients();
                    break;
                case 4:
                    addDoctor();
                    break;
                case 5:
                    deleteDoctor();
                    break;
                case 6:
                    showAllDoctors();
                case 0:
                    System.out.println("Back to previous menu...");
                    return false;
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return true;
    }

    private void addPatient() {
        while (true) {
            try {
                System.out.print("Enter social security number (0 to cancel): ");
                String ssnInput = scanner.nextLine();

                if (ssnInput.equals("0")) {
                    System.out.println("Cancelled.");
                    return;
                }

                String ssn = InputValidator.requireDigitsMinLength(
                        ssnInput,
                        "Social security number",
                        8
                );

                System.out.print("Enter first name (0 to cancel): ");
                String firstNameInput = scanner.nextLine();

                if (firstNameInput.equals("0")) {
                    System.out.println("Cancelled.");
                    return;
                }

                String firstName = InputValidator.requireOnlyLetters(
                        firstNameInput,
                        "First name"
                );

                System.out.print("Enter last name (0 to cancel): ");
                String lastNameInput = scanner.nextLine();

                if (lastNameInput.equals("0")) {
                    System.out.println("Cancelled.");
                    return;
                }

                String lastName = InputValidator.requireOnlyLetters(
                        lastNameInput,
                        "Last name"
                );

                patientService.registerPatient(
                        new Patient(ssn, firstName, lastName)
                );

                System.out.println("Patient registered successfully.");
                return;

            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please try again.\n");
            }
        }
    }

    private void showAllPatients() {
        System.out.println();
        System.out.println("---- All Patients ----");

        List<Patient> patients = patientService.getAllPatients();

        if (patients.isEmpty()) {
            System.out.println("(no patients found)");
            return;
        }

        System.out.println("--------------------------------------------------");
        System.out.println("SSN          | Name");
        System.out.println("--------------------------------------------------");

        for (Patient patient : patients) {
            System.out.printf(
                    "%-12s | %s %s%n",
                    patient.getSocialSecurityNumber(),
                    patient.getFirstName(),
                    patient.getLastName()
            );
        }

        System.out.println("--------------------------------------------------");
    }

    private void addDoctor() {
        while (true) {
            try {
                System.out.print("Enter first name (0 to cancel): ");
                String firstNameInput = scanner.nextLine();

                if (firstNameInput.equals("0")) {
                    System.out.println("Cancelled.");
                    return;
                }

                String firstName = InputValidator.requireOnlyLetters(
                        firstNameInput,
                        "First name"
                );

                System.out.print("Enter last name (0 to cancel): ");
                String lastNameInput = scanner.nextLine();

                if (lastNameInput.equals("0")) {
                    System.out.println("Cancelled.");
                    return;
                }

                String lastName = InputValidator.requireOnlyLetters(
                        lastNameInput,
                        "Last name"
                );

                System.out.print("Enter specialization (0 to cancel): ");
                String specializationInput = scanner.nextLine();

                if (specializationInput.equals("0")) {
                    System.out.println("Cancelled.");
                    return;
                }

                String specialization = InputValidator.requireNonBlank(
                        specializationInput,
                        "Specialization"
                );

                doctorService.addDoctor(firstName, lastName, specialization);

                System.out.println("Doctor added successfully.");
                return;

            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please try again.\n");
            }
        }
    }

    private void deletePatient() {
        while (true) {
            try {
                if (patientService.getAllPatients().isEmpty()) {
                    System.out.println("(no patients found)");
                    return;
                }

                System.out.print("Enter Social Security Number (0 to cancel): ");
                String ssnInput = scanner.nextLine();

                if (ssnInput.equals("0")) {
                    System.out.println("Cancelled.");
                    return;
                }

                patientService.deletePatient(ssnInput);

                System.out.println("Patient deleted successfully.");
                return;

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please try again.\n");
            }
        }
    }

    private void deleteDoctor() {
        while (true) {
            try {
                if (doctorService.findAllDoctors().isEmpty()) {
                    System.out.println("(no doctors found)");
                    return;
                }

                System.out.print("Enter first name (0 to cancel): ");
                String firstName = scanner.nextLine().trim();

                if (firstName.equals("0")) {
                    System.out.println("Cancelled.");
                    return;
                }

                doctorService.validateFirstNameOrThrow(firstName);

                System.out.print("Enter last name (0 to cancel): ");
                String lastName = scanner.nextLine().trim();

                if (lastName.equals("0")) {
                    System.out.println("Cancelled.");
                    return;
                }

                doctorService.validateFullNameOrThrow(firstName, lastName);

                System.out.print("Enter specialization (0 to cancel): ");
                String specialization = scanner.nextLine().trim();

                if (specialization.equals("0")) {
                    System.out.println("Cancelled.");
                    return;
                }

                doctorService.deleteDoctor(firstName, lastName, specialization);

                System.out.println("Doctor deleted successfully.");
                return;

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please try again.\n");
            }
        }
    }


    private void showAllDoctors() {
        System.out.println();
        System.out.println("---- All Doctors ----");

        List<Doctor> doctors = doctorService.findAllDoctors();

        if (doctors.isEmpty()) {
            System.out.println("(no doctors found)");
            return;
        }

        System.out.println("--------------------------------------------------");
        System.out.println("ID | Name              | Specialization");
        System.out.println("--------------------------------------------------");

        for (Doctor doctor : doctors) {
            System.out.printf(
                    "%-2d | %-17s | %s%n",
                    doctor.getId(),
                    doctor.getFirstName() + " " + doctor.getLastName(),
                    doctor.getSpecialization()
            );
        }

        System.out.println("--------------------------------------------------");

    }
}
