package at.austrian.healthcare.presentation;

import at.austrian.healthcare.model.Doctor;
import at.austrian.healthcare.service.DoctorService;
import at.austrian.healthcare.util.InputValidator;

import java.util.List;
import java.util.Scanner;

public class DoctorEntryPresentation {

    private final Scanner scanner;
    private final DoctorService doctorService;

    public DoctorEntryPresentation(Scanner scanner,
                                   DoctorService doctorService) {
        this.scanner = scanner;
        this.doctorService = doctorService;
    }

    public Long start() {
        List<String> specializations = doctorService.getAllSpecializations();

        if (specializations.isEmpty()) {
            System.out.println("No doctors available.");
            return null;
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

            if (input.trim().equals("0")) {
                return null;
            }

            try {
                int index = InputValidator.requireValidIndex(
                        input,
                        specializations.size(),
                        "Specialization choice"
                ) - 1;

                String specialization = specializations.get(index);
                return selectDoctorBySpecialization(specialization);

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private Long selectDoctorBySpecialization(String specialization) {
        List<Doctor> doctors =
                doctorService.getDoctorsBySpecialization(specialization);

        if (doctors.isEmpty()) {
            System.out.println("No doctors found for this specialization.");
            return null;
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

            if (input.trim().equals("0")) {
                return null;
            }

            try {
                int index = InputValidator.requireValidIndex(
                        input,
                        doctors.size(),
                        "Doctor choice"
                ) - 1;

                return doctors.get(index).getId();

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}


