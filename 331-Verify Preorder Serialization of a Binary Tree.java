class VerifyPreorderSerializationOfBinaryTree {

    // Iteration. Time = O(N); Space = O(N);
    public boolean isValidSerialization(String preorder) {
        int slot = 1;
        for (String node : preorder.split(",")) {
            --slot;
            // out of range
            if (slot < 0) {
                return false;
            }
            if (!node.equals("#")) {
                slot += 2;
            }
        }
        return slot == 0;
    }

    // One-pass.
    public boolean isValidSerializationI(String preorder) {
        int slot = 1;
        int n = preorder.length();
        for (int i = 0; i < n; i++) {
            if (preorder.charAt(i) == ',') {
                --slot;// out of range
                if (slot < 0) {
                    return false;
                }
                if (preorder.charAt(i - 1) != '#') {
                    slot += 2;
                }
            }

        }
        slot = (preorder.charAt(n - 1) == '#') ? slot - 1 : slot + 1;
        return slot == 0;
    }
}