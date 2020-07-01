import java.util.*;

class RepeatedDNASequences {

    // HashSet Solution. Time = O((N-L)*L); Space = O((N-L)*L)
    public List<String> findRepeatedDnaSequences(String s) {
        int len = 10;
        int n = s.length();
        Set<String> seen = new HashSet<>();
        Set<String> res = new HashSet<>();
        for (int i = 0; i < n - len + 1; i++) {
            String tmp = s.substring(i, i + len);
            if (seen.contains(tmp)) {
                res.add(tmp);
            }
            seen.add(tmp);
        }
        return new ArrayList<>(res);
    }

    public List<String> findRepeatedDnaSequencesII(String s) {
        List<String> ans = new ArrayList<>();
        if (s.length() < 10) {
            return ans;
        }

        int a = 1 << 20;
        a--;
        char str[] = s.toCharArray();
        byte hashset[] = new byte[a];
        byte map[] = new byte['T' + 1];
        map['A'] = 0;
        map['C'] = 1;
        map['G'] = 2;
        map['T'] = 3;

        int hash = 0;
        for (int i = 0; i < 10; i++)
            hash = hash << 2 | map[str[i]];

        hashset[hash] = 1;

        for (int i = 10; i < s.length(); i++) {
            hash = hash << 2 & a | map[str[i]];
            if (hashset[hash] == 0)
                hashset[hash]++;
            else if (hashset[hash] == 1) {
                hashset[hash]++;
                ans.add(s.substring(i - 9, i + 1));
            }
        }
        return ans;
    }
}