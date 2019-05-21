class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode newL = new ListNode(-1);
        ListNode temp = newL;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                temp.next = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }
        temp.next = l1 == null ? l2 : l1;
        return newL.next;
    }
}