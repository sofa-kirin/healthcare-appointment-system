package at.austrian.healthcare;

import at.austrian.healthcare.presentation.*;
import at.austrian.healthcare.repository.AppointmentRepository;
import at.austrian.healthcare.repository.DoctorRepository;
import at.austrian.healthcare.repository.PatientRepository;
import at.austrian.healthcare.service.AppointmentService;
import at.austrian.healthcare.service.DoctorService;
import at.austrian.healthcare.service.PatientService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Shared input
        Scanner scanner = new Scanner(System.in);

        // Repositories
        PatientRepository patientRepository = new PatientRepository();
        DoctorRepository doctorRepository = new DoctorRepository();
        AppointmentRepository appointmentRepository = new AppointmentRepository();

        // Services
        PatientService patientService = new PatientService(patientRepository);
        DoctorService doctorService = new DoctorService(doctorRepository);
        AppointmentService appointmentService =
                new AppointmentService(
                        appointmentRepository,
                        patientRepository,
                        doctorService
                );

        // Presentations (stateless)
        PatientPresentation patientPresentation =
                new PatientPresentation(patientService, scanner);

        AdminPresentation adminPresentation =
                new AdminPresentation(patientService, doctorService, scanner);

        DoctorEntryPresentation doctorEntryPresentation =
                new DoctorEntryPresentation(scanner, doctorService);

        // Main navigation
        MainPresentation mainPresentation =
                new MainPresentation(
                        scanner,
                        patientPresentation,
                        appointmentService,
                        adminPresentation,
                        doctorEntryPresentation
                );

        // Start application
        mainPresentation.start();
    }
}

