
class PrefixAndSuffixSearch {

    class WordFilter {
        TrieNode root;

        public WordFilter(String[] words) {
            root = new TrieNode();
            for (int weight = 0; weight < words.length; weight++) {
                String word = words[weight] + "{";
                for (int i = 0; i < word.length(); i++) {
                    TrieNode cur = root;
                    cur.weight = weight;
                    for (int j = i; j < 2 * word.length() - 1; j++) {
                        int k = word.charAt(j % word.length()) - 'a';
                        if (cur.children[k] == null) {
                            cur.children[k] = new TrieNode();
                        }
                        cur = cur.children[k];
                        cur.weight = weight;
                    }
                }
            }
        }

        public int f(String prefix, String suffix) {
            TrieNode cur = root;
            for (char letter : (suffix + '{' + prefix).toCharArray()) {
                if (cur.children[letter - 'a'] == null) {
                    return -1;
                }
                cur = cur.children[letter - 'a'];
            }
            return cur.weight;
        }
    }

    static class TrieNode {
        TrieNode[] children;
        int weight;

        TrieNode() {
            this.children = new TrieNode[27];
            this.weight = 0;
        }
    }
}