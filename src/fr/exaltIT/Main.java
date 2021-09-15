package fr.exaltIT;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println(add("//,\n1,2"));
            System.out.println(add(""));
            System.out.println(add("1"));
            System.out.println(add("1,2\n3"));
            System.out.println(add("2,1,4,5,6,4,7,8,9,456,4,64,6\n132,132,13,465,4,65,74654"));
            System.out.println(add("//:\n2:1:4:5:6:4:7:8:9:456:4:64:6\n132:132:13:465:4:65:74654"));
            System.out.println(add("//:\n2:1:-4:5:6:4:7:8:9:-456:4:64:6\n132:132:-13:465:4:65:74654"));
        } catch (WrongArgsException e) {
            System.out.println(e.getMessage());
        }
    }

    static int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        String delimiter = getDelimiter(numbers);
        if (!delimiter.isEmpty()) {
            numbers = numbers.substring(delimiter.length() + 3);
        } else {
            delimiter = "\\,";
        }
        List<Integer> numbersArray = splitNumbersString(numbers, delimiter);
        if (numbersArray.stream().anyMatch(integer -> integer < 0)) {
            throw new WrongArgsException(numbersArray
                    .stream()
                    .filter(integer -> integer < 0)
                    .map(Object::toString)
                    .collect(Collectors.joining(", "))
            );
        }
        return numbersArray.stream().reduce(0, Integer::sum);
    }


    private static String getDelimiter(String numbers) {
        String delimiter = "";
        if (numbers.startsWith("//")) {
            delimiter = numbers.substring(2).split("\\n")[0];
        }
        return delimiter;
    }

    private static List<Integer> splitNumbersString(String numbers, String delimiter) {
        return Arrays.stream(numbers.split("[" + delimiter + ",\\n]")).map(Integer::parseInt).collect(Collectors.toList());
    }
}
