import java.util.*;
import extensions.ListNode;

class LinkedListCycleII {

    // HashSet store the visited node Time = O(N); Space = O(N)
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> visited = new HashSet<>();
        ListNode cur = head;
        while (cur != null) {
            if (visited.contains(cur)) {
                return cur;
            }
            visited.add(cur);
            cur = cur.next;
        }
        return null;
    }

    // slow and fast node
    public ListNode detectCycleII(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode intersect = findIntersect(head);
        if (intersect == null) {
            return null;
        }
        ListNode n1 = head;
        ListNode n2 = intersect;
        while (n1 != n2) {
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }

    private ListNode findIntersect(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode s = head;
        ListNode f = head;
        while (f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
            if (s == f) {
                return s;
            }
        }
        return null;
    }
}