package at.austrian.healthcare.presentation;

import at.austrian.healthcare.service.AppointmentService;

import java.util.Scanner;

public class MainPresentation {

    private final Scanner scanner;
    private final PatientPresentation patientPresentation;
    private final DoctorEntryPresentation doctorEntryPresentation;
    private final AppointmentService appointmentService;
    private final AdminPresentation adminPresentation;

    public MainPresentation(Scanner scanner,
                            PatientPresentation patientPresentation,
                            AppointmentService appointmentService,
                            AdminPresentation adminPresentation,
                            DoctorEntryPresentation doctorEntryPresentation) {

        this.scanner = scanner;
        this.patientPresentation = patientPresentation;
        this.appointmentService = appointmentService;
        this.adminPresentation = adminPresentation;
        this.doctorEntryPresentation = doctorEntryPresentation;
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
                    handleDoctorMode();
                    break;
                case "3":
                    adminPresentation.start();
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

    private void handleDoctorMode(){
        Long doctorId = doctorEntryPresentation.start();

        if(doctorId == null){
            return;
        }
        DoctorPresentation doctorPresentation =  new DoctorPresentation(
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
        System.out.println("=============================");
        System.out.print("Choose an option: ");
    }
}

