package at.austrian.healthcare.presentation;

import at.austrian.healthcare.model.Appointment;
import at.austrian.healthcare.service.AppointmentService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class DoctorPresentation {

    private final AppointmentService appointmentService;
    private final Scanner scanner;

    public DoctorPresentation (AppointmentService appointmentService, Scanner scanner){
        this.appointmentService = appointmentService;
        this.scanner = scanner;
    }

    public void start(long doctorId) {
        String choice;

        do {
            printMenu();
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    showAllAppointments(doctorId);
                    break;
                case "2":
                    showAppointmentsByPatient(doctorId);
                    break;
                case "3":
                    showAppointmentsByDate(doctorId);
                    break;
                case "4":
                    showAppointmentsByTime(doctorId);
                    break;
                case "0":
                    break;
            }
        }
        while(!choice.equals("0"));
    }

    public void printMenu(){

        System.out.println("=== Doctor Menu ===");
        System.out.println("1. Show all appointments");
        System.out.println("2. Show appointments by patient");
        System.out.println("3. Show appointments by date");
        System.out.println("4. Show appointments by time");
        System.out.println("0. Back");
        System.out.print("Choose option: ");

    }

    public void showAppointmentsByPatient(long doctorId){
        System.out.println();
        System.out.println("---- Find Appointments by Social Security Number ----");
        try{
            System.out.println("Enter patient social security number: ");
            String svn = scanner.nextLine();
            List<Appointment> appointments = appointmentService.getByDoctorIdAndPatient(doctorId, svn);
            for(int i = 0; i < appointments.size(); i++){
                System.out.println(appointments.get(i));
            }
        }
        catch (Exception e) {
            System.out.println("Not found: " + e.getMessage());
        }
    }

    public void showAppointmentsByDate(long doctorId){
        System.out.println();
        System.out.println("---- Find Appointments by Date ----");

        try{
            System.out.println("Enter date YYYY-MM-DD: ");
            LocalDate date = LocalDate.parse(scanner.nextLine());
            List<Appointment> appointments = appointmentService.getByDoctorIdAndDate(doctorId, date);

            if (appointments.isEmpty()) {
                System.out.println("No appointments found");
                return;
            }

            for(int i = 0; i < appointments.size(); i++){
                System.out.println(appointments.get(i));
            }

        }
        catch (Exception e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
        }
    }

    public void showAppointmentsByTime(long doctorId){
        System.out.println();
        System.out.println("---- Find Appointments by Time ----");

        try{
            System.out.println("Enter time (HH:MM): ");
            LocalTime time = LocalTime.parse(scanner.nextLine());
            List<Appointment> appointments = appointmentService.getByDoctorIdAndTime(doctorId, time);

            if (appointments.isEmpty()) {
                System.out.println("No appointments found");
                return;
            }

            for(int i = 0; i < appointments.size(); i++){
                System.out.println(appointments.get(i));
            }

        }
        catch (Exception e) {
            System.out.println("Invalid date format. Please use (HH:MM).");
        }
    }

    private void showAllAppointments(long doctorId){
        System.out.println();
        System.out.println("---- All Appointments ----");

        List<Appointment> allAppointments =
                appointmentService.getAppointmentsForDoctor(doctorId);

        if(allAppointments.isEmpty()){
            System.out.println("(no appointments registered)");
            return;
        }

        for(int i = 0; i < allAppointments.size(); i++){
            System.out.println(allAppointments.get(i));
        }
    }
}
