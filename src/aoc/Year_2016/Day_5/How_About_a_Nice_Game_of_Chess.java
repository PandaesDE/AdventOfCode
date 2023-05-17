package aoc.Year_2016.Day_5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class How_About_a_Nice_Game_of_Chess {
    public static void main(String[] args) {
        String input = "uqwqemis";
        // Exercise 1
        System.out.println(getXCharPasswordByXZeroHashes(input, 5, 8));
        // Exercise 2
        System.out.println(getNewXCharPasswordByXZeroHashes(input, 5, 8));
    }

    private static String getNewXCharPasswordByXZeroHashes(String input, int zeros, int passwordLength) {
        int counter = 1;
        String password = "";
        String passwordPositions = "";
        String compareZeros = "";
        for (int i = 0; i < zeros; i++)
            compareZeros += 0;
        String MD5 = "";
        int[] positionOrder = new int[passwordLength];
        int arrayIndex = 0;
        for (int i = 0; i < passwordLength; i++)
            positionOrder[i] = -1;
        while (true) {
            if (password.length() == passwordLength)
                break;
            MD5 = getMD5Hash(input + counter);
            if (MD5.substring(0, zeros).equals(compareZeros)) { // same number of zeros
                char position = MD5.charAt(zeros);
                if (position >= '0' && position <= '9') // making sure it's a number
                    if (Character.getNumericValue(position) <= passwordLength) { // position out of range detection
                        if (notIncludedInIntArray(positionOrder, Character.getNumericValue(position))) {
                            positionOrder[arrayIndex] = Character.getNumericValue(position);
                            arrayIndex++;
                            passwordPositions += position;
                            password += MD5.charAt(zeros + 1);
                        }
                    }
            }
            counter++;
        }
        password = (rearangePasswordByOrder(password, passwordPositions));
        return password;
    }

    private static String rearangePasswordByOrder(String password, String order) {
        String oldPassword = password;
        StringBuilder sb = new StringBuilder(password);
        for (int i = 0; i < password.length(); i++) {
            sb.setCharAt(Character.getNumericValue(order.charAt(i)), oldPassword.charAt(i));
        }
        return sb.toString();
    }

    private static boolean notIncludedInIntArray(int[] arr, int n) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == n)
                return false;
        }
        return true;
    }

    private static String getXCharPasswordByXZeroHashes(String input, int zeros, int passwordLength) {
        int counter = 1;
        String password = "";
        String compareZeros = "";
        for (int i = 0; i < zeros; i++)
            compareZeros += 0;
        String MD5 = "";
        while (true) {
            if (password.length() == passwordLength)
                break;
            MD5 = getMD5Hash(input + counter);
            if (MD5.substring(0, zeros).equals(compareZeros))
                password += MD5.charAt(zeros);
            counter++;

        }
        return password;
    }

    private static String getMD5Hash(String input) {
        String MD5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] digest = md.digest();
            MD5 += DatatypeConverter.printHexBinary(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return MD5;
    }
}
