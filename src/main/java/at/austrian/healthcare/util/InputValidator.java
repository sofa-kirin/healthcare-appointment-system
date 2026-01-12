package at.austrian.healthcare.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class InputValidator {

    public static String requireNonBlank(String input, String fieldName){
        if(input == null || input.trim().isEmpty()){
            throw new IllegalArgumentException(fieldName + " must not be empty");
        }
        return input.trim();
    }

    public static String requireMinLength(String input, String fieldName, int minLength){
        String value = requireNonBlank(input, fieldName);
        if(value.length() < minLength){
            throw new IllegalArgumentException(fieldName + " must be at least " + minLength + " characters long");
        }
        return value;
    }

    public static String requireOnlyLetters(String input, String fieldName){
        String value = requireNonBlank(input, fieldName);
        for(char c : value.toCharArray()){
            if(!Character.isLetter(c)){
                throw new IllegalArgumentException(fieldName + " must contain only letters");
            }
        }
        return value;
    }

    public static String requireOnlyDigits(String input, String fieldName){
        String value = requireNonBlank(input, fieldName);
        for(char c : value.toCharArray()){
            if(!Character.isDigit(c)){
                throw new IllegalArgumentException(fieldName + " must contain only digits");
            }
        }
        return value;
    }

    public static int requirePositiveInt(String input, String fieldName){
        try {
            int value = Integer.parseInt(input.trim());
            if(value <= 0){
                throw new IllegalArgumentException(fieldName + " must be a positive number");
            }
            return value;
        }
        catch(NumberFormatException e){
            throw new IllegalArgumentException(fieldName + " must be a number");
        }
    }

    public static int requireIntInRange(String input, String fieldName, int min, int max){
        int value = requirePositiveInt(input, fieldName);
        if (value < min || value > max) {
            throw new IllegalArgumentException(
                    fieldName + " must be between " + min + " and " + max
            );
        }
        return value;
    }

    public static LocalDate requireDate(String input, String fieldName){
        try{
            return LocalDate.parse(input.trim());
        }
        catch(DateTimeParseException e){
            throw new IllegalArgumentException(fieldName + " must be in format yyyy-MM-dd");
        }
    }

    public static LocalTime requireTime(String input, String fieldName) {
        try {
            return LocalTime.parse(input.trim());
        }
        catch (DateTimeParseException e) {
            throw new IllegalArgumentException(fieldName + " must be in format HH:mm");
        }
    }

    public static LocalDate requireFutureDate(String input, String fieldName){
        LocalDate date = requireDate(input, fieldName);
        if (date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(
                    fieldName + " must not be in the past"
            );
        }
        return date;
    }

    public static int requireValidIndex(String input, int listSize, String fieldName){
        int index = requirePositiveInt(input, fieldName);
        if(index < 1 || index > listSize){
            throw new IllegalArgumentException(fieldName + " must be between 1 and " + listSize);
        }
        return index;
    }

    public static String requireDigitsMaxLength(
            String input,
            String fieldName,
            int maxLength
    ) {
        String value = requireOnlyDigits(input, fieldName);

        if (value.length() > maxLength) {
            throw new IllegalArgumentException(
                    fieldName + " must not exceed " + maxLength + " digits"
            );
        }

        return value;
    }

    public static String requireDigitsMinLength(
            String input,
            String fieldName,
            int minLength
    ) {
        String value = requireOnlyDigits(input, fieldName);

        if (value.length() < minLength) {
            throw new IllegalArgumentException(
                    fieldName + " must contain at least " + minLength + " digits"
            );
        }

        return value;
    }
}
