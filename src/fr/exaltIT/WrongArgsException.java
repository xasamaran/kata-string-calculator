package fr.exaltIT;

public class WrongArgsException extends RuntimeException{
    public WrongArgsException(String numbersInError) {
        super("negatives not allowed : " + numbersInError);
    }
}
