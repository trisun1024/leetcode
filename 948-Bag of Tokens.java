import java.util.*;

class BagOfTokens {

    // Greedy. Time = O(N*log(N)); Space = O(1);
    public int bagOfTokensScore(int[] tokens, int P) {
        Arrays.sort(tokens);
        int left = 0;
        int right = tokens.length - 1;
        int points = 0;
        while (left <= right) {
            if (P >= tokens[left]) {
                P -= tokens[left++];
                points++;
            } else if (points > 0 && P < tokens[left] && left != right) {
                points--;
                P += tokens[right--];
            } else {
                break;
            }
        }
        return points;
    }
}