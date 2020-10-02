import java.util.*;

class AssignCookies {

    //
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int index = 0;
        int count = 0;
        for (int i = 0; i < g.length; i++) {
            for (int j = index; j < s.length; j++) {
                if (g[i] <= s[j]) {
                    count++;
                    index = j + 1;
                    break;
                }
            }
        }
        return count;
    }
}