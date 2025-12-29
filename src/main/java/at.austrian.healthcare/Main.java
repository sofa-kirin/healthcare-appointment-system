package at.austrian.healthcare;

import at.austrian.healthcare.repository.PatientRepository;
import at.austrian.healthcare.service.PatientService;
import at.austrian.healthcare.presentation.PatientPresentation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Data Tier
        PatientRepository patientRepository = new PatientRepository();

        // Application Tier
        PatientService patientService = new PatientService(patientRepository);

        // Scanner
        Scanner scanner = new Scanner(System.in);

        // Presentation Tier
        PatientPresentation patientPresentation = new PatientPresentation(patientService, scanner);

        patientPresentation.start();
    }
}