package main.java.exaltIT.stringCaluclator;

import main.java.exaltIT.stringCaluclator.exception.NegativeNumbersException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {

    private final String DEFAULT_DELIMITER = ",";
    private final String LINE_BREAK = "\n";
    private final String CUSTOM_DELIMITER_IDENTIFIER = "//";

    public int add(String numbers) {
        String delimiter = getDelimeter(numbers.split(LINE_BREAK)[0]);

        if (!delimiter.equals(DEFAULT_DELIMITER)) {
            numbers = numbers.replaceFirst((CUSTOM_DELIMITER_IDENTIFIER + delimiter + LINE_BREAK), "");
        }

        if (numbers.isEmpty()) {
            return 0;
        }

        List<Integer> numbersArray = getNumbers(numbers, delimiter);

        if (hasNegatifNumbers(numbersArray)) {
            throw new NegativeNumbersException(getNegativeNumbers(numbersArray)
            );
        }

        return numbersArray.stream().reduce(0, Integer::sum);
    }

    private String getDelimeter(String s) {
        return "[" +
                (s.contains(CUSTOM_DELIMITER_IDENTIFIER) ? s.replace(CUSTOM_DELIMITER_IDENTIFIER, "") : DEFAULT_DELIMITER) +
                LINE_BREAK +
                "]";
    }

    private String getNegativeNumbers(List<Integer> numbersArray) {
        return numbersArray
                .stream()
                .filter(integer -> integer < 0)
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }

    private boolean hasNegatifNumbers(List<Integer> numbersArray) {
        return numbersArray.stream()
                .anyMatch(integer -> integer < 0);
    }

    private List<Integer> getNumbers(String numbers, String delimiter) {
        return Arrays.stream(numbers.split(delimiter))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

}
