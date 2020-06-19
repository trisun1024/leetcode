import java.util.*;

class Solution {
    /*
     * Rabin-Karp with polynomial rolling hash. Search a substring of given length
     * that occurs at least 2 times. Return start position if the substring exits
     * and -1 otherwise.
     */
    public int search(int L, int a, long modulus, int n, int[] nums) {
        // compute the hash of string S[:L]
        long h = 0;
        for (int i = 0; i < L; ++i)
            h = (h * a + nums[i]) % modulus;

        // already seen hashes of strings of length L
        HashSet<Long> seen = new HashSet<>();
        seen.add(h);
        // const value to be used often : a**L % modulus
        long aL = 1;
        for (int i = 1; i <= L; ++i)
            aL = (aL * a) % modulus;

        for (int start = 1; start < n - L + 1; ++start) {
            // compute rolling hash in O(1) time
            h = (h * a - nums[start - 1] * aL % modulus + modulus) % modulus;
            h = (h + nums[start + L - 1]) % modulus;
            if (seen.contains(h))
                return start;
            seen.add(h);
        }
        return -1;
    }

    public String longestDupSubstring(String S) {
        int n = S.length();
        // convert string to array of integers
        // to implement constant time slice
        int[] nums = new int[n];
        for (int i = 0; i < n; ++i)
            nums[i] = (int) S.charAt(i) - (int) 'a';
        // base value for the rolling hash function
        int a = 26;
        // modulus value for the rolling hash function to avoid overflow
        long modulus = (long) Math.pow(2, 32);

        // binary search, L = repeating string length
        int left = 1, right = n;
        int L;
        while (left <= right) {
            L = left + (right - left) / 2;
            if (search(L, a, modulus, n, nums) != -1)
                left = L + 1;
            else
                right = L - 1;
        }

        int start = search(left - 1, a, modulus, n, nums);
        return S.substring(start, start + left - 1);
    }

    // Trie Solution

    /*
     * A trie is a discrete data structure that's not quite well-known or
     * widely-mentioned in typical algorithm courses, but nevertheless an important
     * one.
     * 
     * A trie (also known as a digital tree) and sometimes even radix tree or prefix
     * tree (as they can be searched by prefixes), is an ordered tree structure,
     * which takes advantage of the keys that it stores – usually strings.
     * 
     * A node's position in the tree defines the key with which that node is
     * associated, which makes tries different in comparison to binary search trees,
     * in which a node stores a key that corresponds only to that node.
     * 
     * In a trie, every node (except the root node) stores one character or a digit.
     * By traversing the trie down from the root node to a particular node n, a
     * common prefix of characters or digits can be formed which is shared by other
     * branches of the trie as well.
     */

    private static class TrieNode {
        // By storing indexes and offsets in the trie nodes, almost all string handling
        // is avoided.
        private TrieNode[] next;
        private int i;// i indicates the start position
        private int depth;

        public TrieNode(int i, int depth) {
            this.i = i;
            this.depth = depth;
        }
    }

    public String longestDupSubstringII(String S) {
        int maxStart = 0, maxLength = 0;
        TrieNode root = new TrieNode(0, 0);
        for (int i = 1; i + maxLength < S.length(); i++) {
            int len = addNew(S, root, i);
            if (len > maxLength) {
                maxLength = len;
                maxStart = i;
            }
        }
        return S.substring(maxStart, maxStart + maxLength);
    }

    private boolean isLeaf(TrieNode node) {
        return node.next == null;
    }

    private int getIndex(String S, int i, int depth) {
        return S.charAt(i + depth) - 'a';
    }

    private int addNew(String S, TrieNode node, int i) {
        int depth = node.depth;
        if (i + depth == S.length())
            return depth;
        if (isLeaf(node)) {
            node.next = new TrieNode[26];
            node.next[getIndex(S,node.i, node.depth)] = new TrieNode(node.i, depth + 1);
        }
        int c = getIndex(S,i, node.depth);
        TrieNode x = node.next[c];
        if (x == null) {
            node.next[c] = new TrieNode(i, depth + 1);
            return depth;
        }
        return addNew(S,x, i);
    }
    // T = O(N*K), S = O(N), where N is length of S and K avg depth of trie.

}