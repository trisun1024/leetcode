import java.util.*;

class ZigZag {
    public String convert(String s, int numRows) {
        if (numRows == 1)
            return s;

        StringBuilder sb = new StringBuilder();
        int len = s.length();
        int clen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < len; j += clen) {
                sb.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + clen - i < len)
                    sb.append(s.charAt(j + clen - i));
            }
        }

        return sb.toString();
    }
}