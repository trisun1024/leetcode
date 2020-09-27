import java.util.*;

class SentenceScreenFitting {

    // Greedy
    public int wordsTyping(String[] sentence, int rows, int cols) {
        String sen = String.join(" ", sentence) + " ";
        int i = 0;
        int len = sen.length();

        for (int row = 0; row < rows; row++) {
            i += cols;
            while (i > -1 && sen.charAt(i % len) != ' ') {
                i--;
            }
            i++; // For accomodating space
        }

        return i / len;
    }

    public int wordsTypingI(String[] sentence, int rows, int cols) {
        int count = 0;
        int ans = 0;
        int n = sentence.length;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                String word = sentence[count];
                int wordLen = word.length();
                if (wordLen > cols - c) {
                    break;
                }

                count++;
                if (count == n) {
                    count = 0;
                    ans++;
                }
                c = c + wordLen;
            }
        }
        return ans;
    }

    public int wordsTypingII(String[] sentence, int rows, int cols) {
        int[] next = new int[sentence.length];
        int[] len = new int[sentence.length];
        for (int i = 0; i < len.length; i++) {
            len[i] = sentence[i].length();
            if (len[i] > cols) {
                return 0;
            }
        }
        int[] times = new int[next.length];
        Arrays.fill(next, -1);

        int r = 0;
        int c = 0;
        int index = 0;
        int count = 0;

        while (r < rows) {

            if (next[index] != -1) {
                count += times[index];
                index = next[index];
            } else {
                int startIndex = index;
                while (c < cols) {
                    while (index < sentence.length && c + len[index] <= cols) {
                        c += len[index++] + 1;
                    }
                    if (index == sentence.length) {
                        count++;
                        times[startIndex]++;
                        index = 0;
                    } else {
                        break;
                    }
                }
                next[startIndex] = index;
            }
            r++;
            c = 0;
        }
        return count;
    }
}