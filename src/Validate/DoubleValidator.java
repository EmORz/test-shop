package src.Validate;

import src.Interfaces.Validator;

import java.util.Scanner;

public class DoubleValidator implements Validator<Double> {

    @Override
    public Double validate(Scanner scanner, String text) {
        while (true) {
            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            } else {
                System.out.println("Невалидна "+text+". Моля, въведете валидно "+text+".");
                scanner.next(); // Изчистване на буфера от грешни данни
            }
        }
    }
}
