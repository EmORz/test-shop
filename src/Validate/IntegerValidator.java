package src.Validate;

import src.Interfaces.Validator;

import java.util.Scanner;

public class IntegerValidator implements Validator<Integer> {
    @Override
    public Integer validate(Scanner scanner, String text) {
        while (true) {
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Невалиднo "+text+". Моля, въведете валидно "+text+".");
                scanner.next(); // Изчистване на буфера от грешни данни
            }
        }
    }
}
