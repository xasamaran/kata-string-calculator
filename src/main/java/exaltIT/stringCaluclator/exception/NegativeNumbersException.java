package main.java.exaltIT.stringCaluclator.exception;

public class NegativeNumbersException extends RuntimeException{
    public NegativeNumbersException(String numbersInError) {
        super("negatives not allowed : " + numbersInError);
    }
}
