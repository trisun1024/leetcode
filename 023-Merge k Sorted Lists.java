import java.util.*;

class MergeKLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;

        PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode l1, ListNode l2) {
                if (l1.val < l2.val)
                    return -1;
                else if (l1.val == l2.val)
                    return 0;
                else
                    return 1;
            }
        });

        for (ListNode l : lists) {
            if (l != null)
                pq.offer(l);
        }

        ListNode res = new ListNode(0);
        ListNode point = res;

        while (!pq.isEmpty()) {
            point.next = pq.poll();
            point = point.next;
            if (point.next != null)
                pq.offer(point.next);
        }

        return res.next;
    }
}
