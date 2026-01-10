package at.austrian.healthcare.presentation;

import at.austrian.healthcare.model.Patient;
import at.austrian.healthcare.service.PatientService;

import java.util.Scanner;

public class PatientEntryPresentation {

    private final Scanner scanner;
    private final PatientService patientService;

    public PatientEntryPresentation(Scanner scanner,
                                    PatientService patientService){
        this.scanner = scanner;
        this.patientService = patientService;
    }

    public String start(){
        System.out.println();
        System.out.println("=== Patient Entry ===");

        System.out.println("Enter your social security number (or 0 to cancel): ");
        String ssn = scanner.nextLine().trim();

        if(ssn.equals("0")){
            return null;
        }

        try{
            Patient patient = patientService.getPatientBySocialSecurityNumber(ssn);
            System.out.println("Welcome, " +
                    patient.getFirstName() + " " +
                    patient.getLastName());

            return patient.getSocialSecurityNumber();

        }
        catch(Exception e){
            System.out.println("Patient not found");
            return null;
        }
    }
}
