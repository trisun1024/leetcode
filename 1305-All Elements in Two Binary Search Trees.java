import extensions.*;
import java.util.*;

class AllElementsInBST {

    // Inorder + Sort. Time = O(M+N+(M+N)*log(M+N));
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> res = new ArrayList<>();
        inorder(root1, res);
        inorder(root2, res);
        Collections.sort(res);
        return res;
    }

    private void inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }

    // Iteration + Linear Sort. Time = O(M+N);
    public List<Integer> getAllElementsI(TreeNode root1, TreeNode root2) {
        List<Integer> result = new LinkedList<>();
        Deque<TreeNode> stack1 = new LinkedList<>();
        Deque<TreeNode> stack2 = new LinkedList<>();
        TreeNode current1 = root1;
        TreeNode current2 = root2;
        while((!stack1.isEmpty() || current1 != null) || (!stack2.isEmpty() || current2 != null)) {
            while(current1 != null) {
                stack1.push(current1);
                current1 = current1.left;
            }
            while(current2 != null) {
                stack2.push(current2);
                current2 = current2.left;
            }

            if(!stack1.isEmpty()) {
                current1 = stack1.pop();
            }
            
            if(!stack2.isEmpty()) {
                current2 = stack2.pop();
            }
        
            if(current1 == null) {
                result.add(current2.val);
                current2 = current2.right;
            } else if (current2 == null) {
                result.add(current1.val);
                current1 = current1.right;
            } else {
                if (current1.val <= current2.val) {
                    result.add(current1.val);
                    current1 = current1.right;
                    stack2.push(current2);
                    current2 = null;
                } else {
                    result.add(current2.val);
                    current2 = current2.right;
                    stack1.push(current1);
                    current1 = null;
                }
            }
    
        }
        return result;
    }
}