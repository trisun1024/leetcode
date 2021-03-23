import java.util.*;

class VowelSpellchecker {

    // HashMap + HashSet.
    public String[] spellcheckerI(String[] wordlist, String[] queries) {
        Set<String> wordsPerfect = new HashSet<>();
        Map<String, String> wordsCap = new HashMap<>();
        Map<String, String> wordsVow = new HashMap<>();

        for (String word : wordlist) {
            wordsPerfect.add(word);
            String wordlow = word.toLowerCase();
            wordsCap.putIfAbsent(wordlow, word);
            String wordlowDV = devowel(wordlow);
            wordsVow.putIfAbsent(wordlowDV, word);
        }
        // get answers
        String[] ans = new String[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = solve(queries[i], wordsPerfect, wordsCap, wordsVow);
        }
        return ans;
    }

    public String solve(String query, Set<String> wordsPerfect, Map<String, String> wordsCap,
            Map<String, String> wordsVow) {
        if (wordsPerfect.contains(query))
            return query;

        String queryL = query.toLowerCase();
        if (wordsCap.containsKey(queryL))
            return wordsCap.get(queryL);

        String queryLV = devowel(queryL);
        if (wordsVow.containsKey(queryLV))
            return wordsVow.get(queryLV);

        return "";
    }

    public String devowel(String word) {
        StringBuilder ans = new StringBuilder();
        for (char c : word.toCharArray()) {
            ans.append(isVowel(c) ? '*' : c);
        }
        return ans.toString();
    }

    public boolean isVowel(char c) {
        return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
    }

    // Trie.
    public String[] spellchecker(String[] wordlist, String[] queries) {
        TrieNode root = buildTrie(wordlist);
        String[] res = new String[queries.length];
        for (int i = 0; i < queries.length; i++) {
            res[i] = search(root, queries[i]);
        }
        return res;
    }

    private String search(TrieNode root, String query) {
        TrieNode cur = root;
        for (char c : query.toCharArray()) {
            if ("aeiouAEIOU".indexOf(c) != -1) {
                if (cur.children[0] == null) {
                    return "";
                }
                cur = cur.children[0];
            } else {
                c = Character.toLowerCase(c);
                if (cur.children[c - 'a'] == null) {
                    return "";
                }
                cur = cur.children[c - 'a'];
            }
        }
        if (!cur.isEnd) {
            return "";
        }
        if (cur.words.contains(query)) {
            return query;
        }
        String res = findCap(cur.words, query); // Search for the first capitalized match
        if (res != "") {
            return res;
        }
        return cur.words.get(0);
    }

    private String findCap(List<String> list, String word) {
        for (String str : list) {
            if (str.toLowerCase().equals(word.toLowerCase())) {
                return str;
            }
        }
        return "";
    }

    private TrieNode buildTrie(String[] wordList) {
        TrieNode root = new TrieNode();
        for (String word : wordList) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                if ("aeiouAEIOU".indexOf(c) != -1) {
                    if (cur.children[0] == null) {
                        cur.children[0] = new TrieNode();
                    }
                    cur = cur.children[0];
                } else {
                    c = Character.toLowerCase(c);
                    if (cur.children[c - 'a'] == null) {
                        cur.children[c - 'a'] = new TrieNode();
                    }
                    cur = cur.children[c - 'a'];
                }
            }
            cur.isEnd = true;
            cur.words.add(word);
        }
        return root;
    }

    static class TrieNode {
        TrieNode[] children;
        boolean isEnd;
        List<String> words;

        TrieNode() {
            children = new TrieNode[26];
            words = new ArrayList<>();
        }
    }
}
