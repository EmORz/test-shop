package src.Interfaces;

import java.util.Scanner;

public interface Validator<T> {

    T validate(Scanner scanner, String text);
}
