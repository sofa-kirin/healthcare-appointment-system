package at.austrian.healthcare;

import at.austrian.healthcare.presentation.AppointmentPresentation;
import at.austrian.healthcare.presentation.MainPresentation;
import at.austrian.healthcare.presentation.PatientPresentation;
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

        // Seed data (demo)
        doctorService.addDoctor("John", "Smith", "Cardiology");
        doctorService.addDoctor("Anna", "Müller", "Dermatology");
        doctorService.addDoctor("Ivan", "Novak", "Neurology");

        // Presentations (stateless)
        PatientPresentation patientPresentation =
                new PatientPresentation(patientService, scanner);

        AppointmentPresentation appointmentPresentation =
                new AppointmentPresentation(appointmentService, scanner);

        // Main navigation
        MainPresentation mainPresentation =
                new MainPresentation(
                        scanner,
                        patientPresentation,
                        appointmentPresentation,
                        doctorService,
                        appointmentService
                );

        // Start application
        mainPresentation.start();
    }
}

