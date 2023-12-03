package src.Validate;

import src.Interfaces.Validator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class IntegerValidator implements Validator<Integer> {


    @Override
    public Integer validate(Scanner scanner, String text) {

        while (true) {
            if (scanner.hasNextInt()) {
                    return scanner.nextInt();
            } else {
                System.out.println("Невалиднo "+text+". Моля, въведете валидно "+text+".");
                scanner.nextLine(); // Изчистване на буфера от грешни данни
            }
        }
        //throw new IllegalArgumentException("Превишено максималното количество опити за въвеждане на валидно " + text + ".");
    }
}

