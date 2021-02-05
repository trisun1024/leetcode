import java.util.*;
import extensions.ListNode;

class LinkedListCycle {
    // HashSet
    public boolean hasCycle(ListNode head) {
        Set<ListNode> check = new HashSet<>();
        while (head != null) {
            if (check.contains(head)) {
                return true;
            } else {
                check.add(head);
            }
            head = head.next;
        }
        return false;
    }

    // two pointers
    public boolean hasCycleII(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}
