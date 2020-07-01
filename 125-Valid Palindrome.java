// import java.util.*;

class ValidPalindrome {
    public boolean isPalindrome(String s) {
        String cur = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String rev = new StringBuffer(cur).reverse().toString();
        return cur.equals(rev);
    }

    // two pointers
    public boolean isPalindromeII(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i <= j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    // stack
    public boolean isPalindromeIII(String s) {
        int length = s.length();

        // Allocating the memory for the stack
        stack = new char[length * 4];

        // Finding the mid
        int i, mid = length / 2;

        for (i = 0; i < mid; i++) {
            push(s.charAt(i));
        }

        // Checking if the length of the String
        // is odd, if odd then neglect the
        // middle character
        if (length % 2 != 0) {
            i++;
        }

        // While not the end of the String
        while (i < length) {
            char ele = pop();

            // If the characters differ then the
            // given String is not a palindrome
            if (ele != s.charAt(i))
                return false;
            i++;
        }

        return true;
    }

    static char[] stack;
    static int top = -1;

    static void push(char ele) {
        stack[++top] = ele;
    }

    // pop function
    static char pop() {
        return stack[top--];
    }
}