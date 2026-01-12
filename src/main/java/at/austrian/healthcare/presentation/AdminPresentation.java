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
        System.out.println("2 - Show all patients");
        System.out.println("3 - Add doctor");
        System.out.println("4 - Show all doctors");
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
                case 1:
                    addPatient();
                    break;
                case 2:
                    showAllPatients();
                    break;
                case 3:
                    addDoctor();
                    break;
                case 4:
                    showAllDoctors();
                    break;
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
        try {
            System.out.print("Enter social security number: ");
            String ssn = InputValidator.requireDigitsMinLength(
                    scanner.nextLine(),
                    "Social security number",
                    8
            );

            System.out.print("Enter first name: ");
            String firstName = InputValidator.requireOnlyLetters(
                    scanner.nextLine(),
                    "First name"
            );

            System.out.print("Enter last name: ");
            String lastName = InputValidator.requireOnlyLetters(
                    scanner.nextLine(),
                    "Last name"
            );

            patientService.registerPatient(
                    new Patient(ssn, firstName, lastName)
            );

            System.out.println("Patient registered successfully.");

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
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

        for (Patient patient : patients) {
            System.out.println(patient);
        }
    }

    private void addDoctor() {
        try {
            System.out.print("Enter first name: ");
            String firstName = InputValidator.requireOnlyLetters(
                    scanner.nextLine(),
                    "First name"
            );

            System.out.print("Enter last name: ");
            String lastName = InputValidator.requireOnlyLetters(
                    scanner.nextLine(),
                    "Last name"
            );

            System.out.print("Enter specialization: ");
            String specialization = InputValidator.requireNonBlank(
                    scanner.nextLine(),
                    "Specialization"
            );

            doctorService.addDoctor(firstName, lastName, specialization);

            System.out.println("Doctor added successfully.");

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
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

        for (Doctor doctor : doctors) {
            System.out.println(doctor);
        }
    }
}
