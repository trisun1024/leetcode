import java.util.*;

class CountNumberOfTeams {

    // Brute Force. Time = O(N^3);
    public int numTeamsI(int[] rating) {
        int c = 0;
        for (int i = 0; i < rating.length; i++) {
            for (int j = i + 1; j < rating.length; j++) {
                for (int k = j + 1; k < rating.length; k++) {
                    if (rating[i] < rating[j] && rating[j] < rating[k]) {
                        c++;
                    }
                    if (rating[i] > rating[j] && rating[j] > rating[k]) {
                        c++;
                    }
                }
            }
        }
        return c;
    }

    // Top Left and Top Right. Time = O(N^2);
    public int numTeamsII(int[] rating) {
        int count = 0;
        int len = rating.length;
        for (int i = 0; i < len; i++) {
            int leftSmaller = 0, rightLarger = 0;
            int leftLarger = 0, rightSmaller = 0;
            for (int j = 0; j < i; j++) {
                if (rating[j] < rating[i]) {
                    leftSmaller++;
                } else if (rating[j] > rating[i]) {
                    leftLarger++;
                }
            }
            for (int j = i + 1; j < len; j++) {
                if (rating[j] < rating[i]) {
                    rightSmaller++;
                } else if (rating[j] > rating[i]) {
                    rightLarger++;
                }
            }
            count += leftSmaller * rightLarger + leftLarger * rightSmaller;
        }
        return count;
    }

    // Merge Sort. Time = O(N*log(N));
    public int numTeams(int[] rating) {
        int[] s0 = new int[rating.length];
        int[] b0 = new int[rating.length];
        int[] s1 = new int[rating.length];
        int[] b1 = new int[rating.length];
        // s0 before the element, how many elements smaller than it.
        // s1 after the element, how manay elements bigger than it.

        int[] pos = new int[rating.length];
        for (int i = 0; i < pos.length; ++i) {
            pos[i] = i;
        }

        mergeSort(rating, pos, 0, pos.length, s0, b0, s1, b1);

        int res = 0;
        for (int i = 0; i < rating.length; ++i) {
            res += s0[i] * b1[i] + b0[i] * s1[i];
        }
        return res;
    }

    private void mergeSort(int[] r, int[] p, int s, int e, int[] s0, int[] b0, int[] s1, int[] b1) {
        if (e - s == 1)
            return;
        int m = (e - s) / 2 + s;
        mergeSort(r, p, s, m, s0, b0, s1, b1);
        mergeSort(r, p, m, e, s0, b0, s1, b1);

        int[] tmp = new int[e - s];
        int idx = 0, i = s, j = m;

        while (i < m && j < e) {
            if (r[p[i]] < r[p[j]]) {
                tmp[idx] = p[i];
                s1[p[i]] += j - m;
                b1[p[i]] += e - j;
                i++;
            } else {
                tmp[idx] = p[j];
                s0[p[j]] += i - s;
                b0[p[j]] += m - i;
                j++;
            }
            idx++;
            // 0, 1, 2, 3(i), 4, 5 m(6, 7, 8, 9(j), 10, 11, 12)
        }
        while (i < m) {
            s1[p[i]] += (e - m);
            tmp[idx++] = p[i++];
        }
        while (j < e) {
            s0[p[j]] += (m - s);
            tmp[idx++] = p[j++];
        }
        System.arraycopy(tmp, 0, p, s, tmp.length);
    }

}