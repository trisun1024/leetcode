import java.util.*;

class RearrangeSpacesBetweenWords {

    // Time = O(N);
    public String reorderSpaces(String text) {
        int length = text.length();
        int spaceCount = 0;
        List<String> words = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            char c = text.charAt(i);
            if (c == ' ') {
                spaceCount++;
                if (sb.length() > 0) {
                    words.add(sb.toString());
                    sb.setLength(0);
                }
            } else {
                sb.append(c);
            }
        }

        if (sb.length() > 0) {
            words.add(sb.toString());
            sb.setLength(0);
        }

        int wordCount = words.size();
        for (int i = 0; i < wordCount - 1; i++) {
            sb.append(words.get(i));
            for (int j = 0; j < spaceCount / (wordCount - 1); j++) {
                sb.append(" ");
            }
        }

        sb.append(words.get(wordCount - 1));
        for (int i = wordCount > 1 ? spaceCount % (wordCount - 1) : spaceCount; i > 0; i--) {
            sb.append(" ");
        }

        return sb.toString();
    }
}