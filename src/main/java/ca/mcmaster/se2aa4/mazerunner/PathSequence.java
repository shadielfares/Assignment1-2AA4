package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PathSequence {
    private static String sequence = "";

    // Sequence Methods
    public void appendSequence(String newValue) {
        sequence += newValue;
    }

    public void printSequence() {
        System.out.println(convertToFactorize());
    }

    public String getSequence() {
        return sequence;
    }

    public String convertToFactorize() {
        StringBuilder factorizedSequence = new StringBuilder();

        int length = sequence.length();
        int count = 1;

        for (int i = 1; i <= length; i++) {
            if (i < length && sequence.charAt(i) == sequence.charAt(i - 1)) {
                // Increment count if the current character is the same as the previous one
                count++;
            } else {
                // Append the count and character to the result
                if (count > 1) {
                    factorizedSequence.append(count).append(sequence.charAt(i - 1)).append(" ");
                } else {
                    factorizedSequence.append(sequence.charAt(i - 1)).append(" ");
                }
                count = 1; // Reset count for the next character
            }
        }

        return factorizedSequence.toString().trim(); // Remove the trailing space
    }

    public ArrayList<String> parseSequence(String sequence) {
        ArrayList<String> commands = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\d*[FRL]"); // Match commands for F, R, L with optional numeric prefix
        Matcher matcher = pattern.matcher(sequence);

        // Extract commands from the input sequence
        while (matcher.find()) {
            commands.add(matcher.group());
        }

        return commands;
    }
}