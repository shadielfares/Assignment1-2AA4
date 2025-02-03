package ca.mcmaster.se2aa4.mazerunner;

public class PathSequence{
    private static String sequence = "";

    //Sequence Methods
    public void setSequence(String newValue){
        sequence += newValue;
    }

    public void printSequence(){
        System.out.println(convertToFactorize());
    }

    public String normalizeSequence(String sequence) {
        StringBuilder normalizedSequence = new StringBuilder();

        int length = sequence.length();
        for (int i = 0; i < length; i++) {
            char currentChar = sequence.charAt(i);

            // Check if the current character is a digit
            if (Character.isDigit(currentChar)) {
                int repeatCount = Character.getNumericValue(currentChar);
                if (i + 1 < length && Character.isLetter(sequence.charAt(i + 1))) {
                    char direction = sequence.charAt(i + 1);
                    normalizedSequence.append(String.valueOf(direction).repeat(repeatCount));
                    i++; // Skip the direction character after processing
                }
            } else if (Character.isLetter(currentChar)) {
                // Add single directions directly
                normalizedSequence.append(currentChar);
            }
        }
        return normalizedSequence.toString();
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
                factorizedSequence.append(count).append(sequence.charAt(i - 1)).append(" ");
                count = 1; // Reset count for the next character
            }
        }

        return factorizedSequence.toString().trim(); // Remove the trailing space
    }
}