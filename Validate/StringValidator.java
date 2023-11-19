package Validate;

import Interfaces.Validator;

import java.util.Scanner;

public class StringValidator implements Validator<String> {
    @Override
    public String validate(Scanner scanner, String text) {
        while (true){
            if (scanner.hasNextLine()) {
                String newName = scanner.nextLine();
                if (newName.length()>=3) {
                    return newName;
                }else {
                    System.out.println("Наименованието на продукта "+newName+" трябва да е поне 3 символа./"+newName.length());
                }
            }
        }
    }
}
