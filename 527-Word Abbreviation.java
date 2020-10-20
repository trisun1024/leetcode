import java.util.*;

class WordAbbreviation {

    // Greedy.
    public List<String> wordsAbbreviation(List<String> dict) {
        int len = dict.size();
        String[] ans = new String[len];
        int[] prefix = new int[len];
        // convert all to abbreviation
        for (int i = 0; i < len; i++) {
            ans[i] = abbrev(dict.get(i), 0);
        }
        // check if there is a match
        for (int i = 0; i < len; i++) {
            while (true) {
                Set<Integer> dupes = new HashSet<>();
                for (int j = i + 1; j < len; j++) {
                    if (ans[i].equals(ans[j])) {
                        dupes.add(j);
                    }
                }
                if (dupes.isEmpty()) {
                    break;
                }
                dupes.add(i);
                for (int k : dupes) {
                    ans[k] = abbrev(dict.get(k), ++prefix[k]);
                }
            }
        }
        return Arrays.asList(ans);
    }

    private String abbrev(String word, int pos) {
        int len = word.length();
        if (len - pos <= 3) {
            return word;
        }
        return word.substring(0, pos + 1) + (len - pos - 2) + word.charAt(len - 1);
    }

    // Group + Least Common Prefix.
    public List<String> wordsAbbreviationI(List<String> dict) {
        int len = dict.size();
        Map<String, List<IndexedWord>> groups = new HashMap<>();
        String[] ans = new String[len];

        int index = 0;
        for (String word : dict) {
            String ab = abbrev(word, 0);
            if (!groups.containsKey(ab)) {
                groups.put(ab, new ArrayList<IndexedWord>());
            }
            groups.get(ab).add(new IndexedWord(word, index));
            index++;
        }

        for (List<IndexedWord> group : groups.values()) {
            Collections.sort(group, (a, b) -> a.word.compareTo(b.word));
            int[] lcp = new int[group.size()];
            for (int i = 1; i < group.size(); i++) {
                int p = longestCommonPrefix(group.get(i - 1).word, group.get(i).word);
                lcp[i] = p;
                lcp[i - 1] = Math.max(lcp[i - 1], p);
            }
            for (int i = 0; i < group.size(); i++) {
                ans[group.get(i).index] = abbrev(group.get(i).word, lcp[i]);
            }
        }
        return Arrays.asList(ans);
    }

    private int longestCommonPrefix(String word1, String word2) {
        int i = 0;
        while (i < word1.length() && i < word2.length() && word1.charAt(i) == word2.charAt(i)) {
            i++;
        }
        return i;
    }

    static class IndexedWord {
        String word;
        int index;

        IndexedWord(String w, int i) {
            word = w;
            index = i;
        }
    }

    // Group + Trie.
    public List<String> wordsAbbreviationII(List<String> dict) {
        Map<String, List<IndexedWord>> groups = new HashMap<>();
        String[] ans = new String[dict.size()];

        int index = 0;
        for (String word : dict) {
            String ab = abbrev(word, 0);
            if (!groups.containsKey(ab)) {
                groups.put(ab, new ArrayList<>());
            }
            groups.get(ab).add(new IndexedWord(word, index));
            index++;
        }

        for (List<IndexedWord> group : groups.values()) {
            TrieNode trie = new TrieNode();
            for (IndexedWord iw : group) {
                TrieNode cur = trie;
                for (char letter : iw.word.substring(1).toCharArray()) {
                    if (cur.children[letter - 'a'] == null) {
                        cur.children[letter - 'a'] = new TrieNode();
                    }
                    cur.count++;
                    cur = cur.children[letter - 'a'];
                }
            }

            for (IndexedWord iw : group) {
                TrieNode cur = trie;
                int i = 1;
                for (char letter : iw.word.substring(1).toCharArray()) {
                    if (cur.count == 1) {
                        break;
                    }
                    cur = cur.children[letter - 'a'];
                    i++;
                }
                ans[iw.index] = abbrev(iw.word, i - 1);
            }
        }

        return Arrays.asList(ans);
    }

    static class TrieNode {
        TrieNode[] children;
        int count;

        TrieNode() {
            children = new TrieNode[26];
            count = 0;
        }
    }

}