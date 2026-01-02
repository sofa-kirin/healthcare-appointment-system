package at.austrian.healthcare;

import at.austrian.healthcare.presentation.DoctorPresentation;
import at.austrian.healthcare.repository.PatientRepository;
import at.austrian.healthcare.service.DoctorService;
import at.austrian.healthcare.service.PatientService;
import at.austrian.healthcare.presentation.PatientPresentation;
import at.austrian.healthcare.repository.AppointmentRepository;
import at.austrian.healthcare.service.AppointmentService;
import at.austrian.healthcare.presentation.AppointmentPresentation;
import at.austrian.healthcare.repository.DoctorRepository;

import java.util.Scanner;

public class Main { public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    // Repositories
    PatientRepository patientRepository = new PatientRepository();
    AppointmentRepository appointmentRepository = new AppointmentRepository();
    DoctorRepository doctorRepository = new DoctorRepository();

    // Services
    PatientService patientService = new PatientService(patientRepository);
    DoctorService doctorService = new DoctorService(doctorRepository);
    AppointmentService appointmentService =
            new AppointmentService(appointmentRepository, patientRepository, doctorService);

    // Presentations (UI)
    PatientPresentation patientUI = new PatientPresentation(patientService, scanner);
    AppointmentPresentation appointmentUI = new AppointmentPresentation(appointmentService, scanner);
    DoctorPresentation doctorUI = new DoctorPresentation(doctorService, scanner);

    // Main Menu
    String choice;
    do {
        printMainMenu();
        choice = scanner.nextLine().trim();

        switch (choice) {
            case "1":
                patientUI.start();
                break;
            case "2":
                appointmentUI.start();
                break;
            case "3":
                System.out.println("Doctor menu is not implemented yet.");
                // doctorUI.start();
                break;
            case "0":
                System.out.println("Bye!");
                break;
            default:
                System.out.println("Unknown option. Please enter 0-3.");
        }

    } while (!choice.equals("0"));


}

    private static void printMainMenu() {
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