import java.util.*;

class UniqueMorseCodeWords {

    // HashSet.
    public int uniqueMorseRepresentations(String[] words) {
        Set<String> find = new HashSet<>();
        for (String word : words) {
            find.add(transform(word));
        }
        return find.size();
    }

    private final String[] morseCode = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-",
            ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--",
            "--.." };

    private String transform(String word) {
        StringBuilder sb = new StringBuilder();
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            sb.append(morseCode[i]);
        }
        return sb.toString();
    }
}