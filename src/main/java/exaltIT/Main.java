package main.java.exaltIT;

import main.java.exaltIT.stringCaluclator.StringCalculator;
import main.java.exaltIT.stringCaluclator.exception.NegativeNumbersException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        StringCalculator stringCalculator = new StringCalculator();
        try {
            System.out.println(stringCalculator.add(""));
        } catch (NegativeNumbersException e) {
            System.out.println(e.getMessage());
        }
    }


}
