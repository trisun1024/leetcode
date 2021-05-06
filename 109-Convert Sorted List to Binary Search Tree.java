import extensions.*;
import java.util.*;

class ConvertSortedListToBST {

    // Recursion Time = O(N*H); Space = O(H);
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode mid = findMiddle(head);
        TreeNode root = new TreeNode(mid.val);
        if (head == mid) {
            return root;
        }
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(mid.next);
        return root;
    }

    private ListNode findMiddle(ListNode head) {
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if (prev != null) {
            prev.next = null;
        }
        return slow;
    }

    // Convert to List
    public TreeNode sortedListToBSTII(ListNode head) {
        List<Integer> list = convertToList(head);
        // System.out.println(list);
        return listToBST(list, 0, list.size() - 1);
    }

    private List<Integer> convertToList(ListNode head) {
        List<Integer> res = new ArrayList<>();
        while (head != null) {
            res.add(head.val);
        }
        return res;
    }

    private TreeNode listToBST(List<Integer> list, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode node = new TreeNode(list.get(mid));
        if (left == right) {
            return node;
        }
        node.left = listToBST(list, left, mid - 1);
        node.right = listToBST(list, mid + 1, right);
        return node;
    }
}