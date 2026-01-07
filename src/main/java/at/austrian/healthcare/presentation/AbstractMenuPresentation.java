package at.austrian.healthcare.presentation;

import java.util.Scanner;

public abstract class AbstractMenuPresentation {

    protected final Scanner scanner;

    protected AbstractMenuPresentation(Scanner scanner){
        this.scanner = scanner;
    }

    public final void start(){
        boolean running = true;
        while(running){
            printMenu();
            String choice = scanner.nextLine();
            running = handleChoice(choice);
        }
    }

    protected abstract void printMenu();
    protected abstract boolean handleChoice(String choice);

}
