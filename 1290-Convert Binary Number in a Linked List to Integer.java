import extensions.ListNode;

class ConvertBinaryNumberInLinkedListToInteger {

    // Power.
    public int getDecimalValueI(ListNode head) {
        int ans = 0;
        while (head != null) {
            ans = ans * 2 + head.val;
            head = head.next;
        }
        return ans;
    }

    // Binary Operator.
    public int getDecimalValue(ListNode head) {
        int ans = 0;
        while (head != null) {
            ans = (ans << 1) | head.val;
            head = head.next;
        }
        return ans;
    }
}
