import java.util.*;

class MaximumXORofTwoNumbersInArray {

    // Brute Force. Time = O(N^2);
    public int findMaximumXOR(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                max = Math.max(max, nums[i] ^ nums[j]);
            }
        }
        return max;
    }

    // HashSet. Time = O(N);
    public int findMaximumXORI(int[] nums) {
        int max = nums[0];
        for (int num : nums) {
            max = Math.max(max, num);
        }
        int len = (Integer.toBinaryString(max)).length();
        int maxXOR = 0;
        int curXOR;
        Set<Integer> prefix = new HashSet<>();
        for (int i = len - 1; i >= 0; i--) {
            maxXOR <<= 1;
            curXOR = maxXOR | 1;
            prefix.clear();
            for (int num : nums) {
                prefix.add(num >> i);
            }
            for (int p : prefix) {
                if (prefix.contains(curXOR ^ p)) {
                    maxXOR = curXOR;
                    break;
                }
            }
        }
        return maxXOR;
    }

    // Trie. Time = O(N);
    public int findMaximumXORII(int[] nums) {
        // Compute length L of max number in a binary representation
        int maxNum = nums[0];
        for (int num : nums)
            maxNum = Math.max(maxNum, num);
        int L = (Integer.toBinaryString(maxNum)).length();

        // zero left-padding to ensure L bits for each number
        int n = nums.length, bitmask = 1 << L;
        String[] strNums = new String[n];
        for (int i = 0; i < n; ++i) {
            strNums[i] = Integer.toBinaryString(bitmask | nums[i]).substring(1);
        }

        TrieNode trie = new TrieNode();
        int maxXor = 0;
        for (String num : strNums) {
            TrieNode node = trie, xorNode = trie;
            int currXor = 0;
            for (Character bit : num.toCharArray()) {
                // insert new number in trie
                if (node.children.containsKey(bit)) {
                    node = node.children.get(bit);
                } else {
                    TrieNode newNode = new TrieNode();
                    node.children.put(bit, newNode);
                    node = newNode;
                }

                // compute max xor of that new number
                // with all previously inserted
                Character toggledBit = bit == '1' ? '0' : '1';
                if (xorNode.children.containsKey(toggledBit)) {
                    currXor = (currXor << 1) | 1;
                    xorNode = xorNode.children.get(toggledBit);
                } else {
                    currXor = currXor << 1;
                    xorNode = xorNode.children.get(bit);
                }
            }
            maxXor = Math.max(maxXor, currXor);
        }

        return maxXor;
    }

    static class TrieNode {
        HashMap<Character, TrieNode> children;

        public TrieNode() {
            this.children = new HashMap<Character, TrieNode>();
        }

    }
}