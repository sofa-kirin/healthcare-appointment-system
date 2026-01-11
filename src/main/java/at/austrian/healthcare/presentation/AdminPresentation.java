package at.austrian.healthcare.presentation;

import at.austrian.healthcare.model.Doctor;
import at.austrian.healthcare.model.Patient;
import at.austrian.healthcare.service.DoctorService;
import at.austrian.healthcare.service.PatientService;

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
        switch (choice) {
            case "1":
                addPatient();
                break;
            case "2":
                showAllPatients();
                break;
            case "3":
                addDoctor();
                break;
            case "4":
                showAllDoctors();
                break;
            case "0":
                System.out.println("Back to previous menu...");
                return false;
            default:
                System.out.println("Unknown option. Please enter 0-4.");
        }
        return true;
    }

    private void addPatient() {
        try {
            System.out.print("Enter social security number: ");
            String ssn = scanner.nextLine().trim();

            System.out.print("Enter first name: ");
            String firstName = scanner.nextLine().trim();

            System.out.print("Enter last name: ");
            String lastName = scanner.nextLine().trim();

            Patient patient = new Patient(ssn, firstName, lastName);
            patientService.registerPatient(patient);

            System.out.println("Patient registered successfully.");
        } catch (Exception e) {
            System.out.println("Cannot register patient: " + e.getMessage());
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
        System.out.println();
        System.out.println("---- Add Doctor ----");

        System.out.print("First name: ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Last name: ");
        String lastName = scanner.nextLine().trim();

        System.out.print("Specialization: ");
        String specialization = scanner.nextLine().trim();

        doctorService.addDoctor(firstName, lastName, specialization);

        System.out.println("Doctor added successfully.");
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
