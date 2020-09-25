
/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode() {} ListNode(int val) { this.val = val; } ListNode(int val,
 * ListNode next) { this.val = val; this.next = next; } }
 */
import extensions.ListNode;
import java.util.*;

class LinkedListRandomNode {

    /**
     * @param head The linked list's head. Note that the head is guaranteed to be
     *             not null, so it contains at least one node.
     */

    private int size;
    private ListNode head;
    private Random rand;

    public LinkedListRandomNode(ListNode head) {
        this.head = head;
        ListNode cur = head;
        while (cur != null) {
            this.size++;
            cur = cur.next;
        }
        this.rand = new Random();
    }

    /** Returns a random node's value. */
    public int getRandom() {
        int index = rand.nextInt(this.size);
        ListNode cur = head;
        while (index > 0 && cur != null) {
            cur = cur.next;
            index--;
        }
        return cur.val;
    }
}

/**
 * Your Solution object will be instantiated and called as such: Solution obj =
 * new Solution(head); int param_1 = obj.getRandom();
 */