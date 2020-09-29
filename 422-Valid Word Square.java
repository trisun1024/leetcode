import java.util.*;

class ValidWordSquare {

    // Time = O(N^2);
    public boolean validWordSquare(List<String> words) {
        if (words == null || words.size() == 0) {
            return true;
        }
        int n = words.size();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < words.get(i).length(); j++) {
                if (j >= n || words.get(j).length() <= i || words.get(j).charAt(i) != words.get(i).charAt(j))
                    return false;
            }
        }
        return true;
    }

    // Use 2D matrix store characters
    public boolean validWordSquareI(List<String> words) {
        if (words == null || words.isEmpty()) {
            return false;
        }
        int m = words.size();
        char[][] matrix = new char[m][m];
        for (int i = 0; i < m; i++) {
            String word = words.get(i);
            if (word.length() > m) {
                return false;
            }
            System.arraycopy(word.toCharArray(), 0, matrix[i], 0, word.length());
        }

        for (int row = 0; row < m; row++) {
            for (int col = row; col < m; col++) {
                if (matrix[row][col] != matrix[col][row]) {
                    return false;
                }
            }
        }
        return true;
    }
}