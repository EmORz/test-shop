package Validate;

import Interfaces.Validator;

import java.util.Scanner;

public class PositiveIntegerValidator implements Validator<Integer> {

    @Override
    public Integer validate(Scanner scanner, String text) {
        while (true){
            int value = new IntegerValidator().validate(scanner, text);
            if (value > 0) {
                return value;
            }else {
                System.out.println("Въведенето "+text+" трябва да е положително. Опитайте отново!");
            }
        }
    }
}
