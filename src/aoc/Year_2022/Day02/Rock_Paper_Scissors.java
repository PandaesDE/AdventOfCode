package aoc.Year_2022.Day02;

import java.util.ArrayList;

import aoc.Conveniencer;

public class Rock_Paper_Scissors {

    public static void main(String[] args) {
        String input = Conveniencer.getInput(2022, 2);
        ArrayList<String> rounds = Conveniencer.convertTextToLines(input);
        // 1
        System.out.println(calculateScoreByGuide(rounds));
        // 2
        System.out.println(calculateScoreByTrueGuide(rounds));
    }

    private static int calculateScoreByGuide(ArrayList<String> rounds) {
        int out = 0;
        for (String i : rounds) {
            out += getRoundResult(i.charAt(0), i.charAt(2));
        }
        return out;
    }

    private static int calculateScoreByTrueGuide(ArrayList<String> rounds) {
        int out = 0;
        for (String i : rounds) {
            out += getTrueRoundResult(i.charAt(0), i.charAt(2));
        }
        return out;
    }

    private static int getRoundResult(char a, char b) {
        int shapeScore = 0;
        int outcomeScore = 0;

        // A|X: ROCK, B|Y: PAPER, C|Z: SCISSORS
        switch (b) {
            case 'X':
                shapeScore = 1;
                if (a == 'A')
                    outcomeScore = 3;
                if (a == 'C')
                    outcomeScore = 6;
                break;
            case 'Y':
                shapeScore = 2;
                if (a == 'B')
                    outcomeScore = 3;
                if (a == 'A')
                    outcomeScore = 6;
                break;
            case 'Z':
                shapeScore = 3;
                if (a == 'C')
                    outcomeScore = 3;
                if (a == 'B')
                    outcomeScore = 6;
                break;

            default:
                System.out.println("This should not occur, a: " + a + " b: " + b);
                break;
        }
        return shapeScore + outcomeScore;
    }

    private static int getTrueRoundResult(char a, char b) {
        int shapeScore = 0;
        int outcomeScore = 0;

        // A: ROCK, B: PAPER, C: SCISSORS
        switch (b) {
            case 'X': // LOSE
                outcomeScore = 0;
                if (a == 'A')
                    shapeScore = 3;
                else if (a == 'B')
                    shapeScore = 1;
                else
                    shapeScore = 2;
                break;
            case 'Y': // DRAW
                outcomeScore = 3;
                if (a == 'A')
                    shapeScore = 1;
                else if (a == 'B')
                    shapeScore = 2;
                else
                    shapeScore = 3;
                break;
            case 'Z': // WIN
                outcomeScore = 6;
                if (a == 'A')
                    shapeScore = 2;
                else if (a == 'B')
                    shapeScore = 3;
                else
                    shapeScore = 1;
                break;

            default:
                System.out.println("This should not occur, a: " + a + " b: " + b);
                break;
        }

        return shapeScore + outcomeScore;
    }
}
