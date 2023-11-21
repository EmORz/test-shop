package src.Validate;

import src.Interfaces.Validator;

import java.util.Scanner;

public class PositiveDoubleValidator implements Validator<Double> {
    @Override
    public Double validate(Scanner scanner, String text) {

        while (true){
            Double value = new DoubleValidator().validate(scanner, text);
            if (value>=0){
                return value;
            }else {
                System.out.println("Въведената "+text+" трябва да е положителна. Опитайте отново!");

            }
        }
    }
}
