package aoc.Year_2017.Day_1;

import aoc.Conveniencer;

public class Inverse_Captcha {

    public static void main(String[] args) {
        String input = Conveniencer.getInput(2017, 1);
        // Exercise 1
        System.out.println(getCaptchaAtNextIndex(input));
        // Exercise 2
        System.out.println(getCaptchaAtHalfwayCircle(input));

    }

    private static int getCaptchaAtHalfwayCircle(String input) {
        if (input.charAt(input.length() - 1) == '\n')
            input = input.substring(0, input.length() - 1);

        int sum = 0;
        for (int i = 0; i < input.length(); i++) {
            int adder = Integer.parseInt(String.valueOf(input.charAt(i)));
            if (input.charAt(i) == input.charAt((i + (input.length() / 2)) % input.length())) {
                sum += adder;
            }
        }
        return sum;
    }

    private static int getCaptchaAtNextIndex(String input) {
        // remove last char if it's \n
        if (input.charAt(input.length() - 1) == '\n')
            input = input.substring(0, input.length() - 1);

        int sum = 0;
        for (int i = 0; i < input.length(); i++) {
            int adder = Integer.parseInt(String.valueOf(input.charAt(i)));
            if (i != input.length() - 1) {
                if (input.charAt(i) == input.charAt(i + 1))
                    sum += adder;
            } else if (input.charAt(i) == input.charAt(0))
                sum += Integer.parseInt(String.valueOf(input.charAt(i)));
        }
        return sum;
    }
}
