import java.util.*;

class Node {
    char val;
    Node left;
    Node right;

    Node() {
        this.val = ' ';
    }

    Node(char val) {
        this.val = val;
    }

    Node(char val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class BuildBinaryExpressionTreeFromInfixExpression {

    // Stack.
    public Node expTree(String s) {
        s = '(' + s + ')';
        Deque<Node> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c) && c != '(') {
                Node right = stack.pollFirst();
                if (c == '*' || c == '/') {
                    if (stack.peekFirst().val == '*' || stack.peekFirst().val == '/') {
                        Node ops = stack.pollFirst();
                        Node left = stack.pollFirst();
                        ops.left = left;
                        ops.right = right;
                        right = ops;
                    }
                } else {
                    while (stack.peekFirst().val != '(') {
                        Node ops = stack.pollFirst();
                        Node left = stack.pollFirst();
                        ops.left = left;
                        ops.right = right;
                        right = ops;
                    }
                    if (c == ')') {
                        stack.pollFirst();
                    }
                }
                stack.offerFirst(right);
            }

            if (c != ')') {
                stack.offerFirst(new Node(c));
            }
        }
        return stack.pollFirst();
    }

    // DFS.
    public Node expTreeI(String s) {
        Queue<Character> queue = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            queue.offer(c);
        }
        return dfs(queue);
    }

    private Node dfs(Queue<Character> queue) {
        Deque<Node> deque = new ArrayDeque<>();
        while (!queue.isEmpty()) {
            char c = queue.poll();
            if (Character.isDigit(c)) {
                deque.offerFirst(new Node(c));
            } else if (c == '(') {
                deque.offerFirst(dfs(queue));
            } else if (c == ')') {
                break;
            } else if (c == '+' || c == '-') {
                deque.offerFirst(new Node(c));
            } else if (c == '*' || c == '/') {
                Node cur = new Node(c);
                cur.left = deque.pollFirst();
                if (queue.peek() == '(') {
                    queue.poll();
                    cur.right = dfs(queue);
                } else {
                    cur.right = new Node(queue.poll());
                }
                deque.offerFirst(cur);
            }
        }
        Node root = deque.removeLast();
        while (!deque.isEmpty()) {
            Node cur = deque.removeLast();
            cur.left = root;
            cur.right = deque.removeLast();
            root = cur;
        }
        return root;
    }

    // Stack.
    public Node expTreeII(String s) {
        Deque<Node> nodes = new ArrayDeque<>();
        Deque<Character> ops = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                nodes.offerFirst(new Node(c));
            } else {
                if (c == '(') {
                    ops.offerFirst(c);
                } else if (c == ')') {
                    while (ops.peekFirst() != '(') {
                        nodes.offerFirst(buildNode(ops.pollFirst(), nodes.pollFirst(), nodes.pollFirst()));
                    }
                    ops.pollFirst();
                } else {
                    while (!ops.isEmpty() && precedence(ops.peekFirst()) >= precedence(c)) {
                        nodes.offerFirst(buildNode(ops.pollFirst(), nodes.pollFirst(), nodes.pollFirst()));
                    }
                    ops.offerFirst(c);
                }
            }
        }
        while (!ops.isEmpty())
            nodes.offerFirst(buildNode(ops.pop(), nodes.pop(), nodes.pop()));
        return nodes.peekFirst();
    }

    Node buildNode(char op, Node right, Node left) {
        return new Node(op, left, right);
    }

    int precedence(char c) {
        if (c == '+' || c == '-') {
            return 1;
        } else if (c == '*' || c == '/') {
            return 2;
        }
        return 0;
    }
}