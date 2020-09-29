
class StrongPasswordChecker {

    //
    public int strongPasswordChecker(String s) {
        int count = 0;
        int a = 1;
        int A = 1;
        int d = 1;
        char[] array = s.toCharArray();
        int[] nums = new int[array.length];
        // find if there exists lower case, upper case, and numeric
        for (int i = 0; i < array.length;) {
            if (Character.isLowerCase(array[i])) {
                a = 0;
            }
            if (Character.isUpperCase(array[i])) {
                A = 0;
            }
            if (Character.isDigit(array[i])) {
                d = 0;
            }
            int j = i;
            while (i < array.length && array[i] == array[j]) {
                i++;
            }
            nums[j] = i - j;
        }
        // total missing
        int totalMissing = a + A + d;
        if (array.length < 6) {
            count += totalMissing + Math.max(0, 6 - (array.length + totalMissing));
        } else {
            int overLen = Math.max(array.length - 20, 0);
            int leftOver = 0;
            count += overLen;
            for (int k = 1; k < 3; k++) {
                for (int i = 0; i < nums.length && overLen > 0; i++) {
                    if (nums[i] < 3 || nums[i] % 3 != (k - 1))
                        continue;
                    nums[i] -= Math.min(overLen, k);
                    overLen -= k;
                }
            }
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] >= 3 && overLen > 0) {
                    int need = nums[i] - 2;
                    nums[i] -= overLen;
                    overLen -= need;
                }
                if (nums[i] >= 3) {
                    leftOver += nums[i] / 3;
                }
            }
            count += Math.max(totalMissing, leftOver);
        }
        return count;
    }

    // 
    public int strongPasswordCheckerI(String s) {
        int len = lengthCheck(s);
        int tri = tripleCheck(s);
        int rep = repeatCheck(s);

        return (s.length() < 6) ? Math.max(len, tri) : len + Math.max(rep, tri);
    }

    private int lengthCheck(String s) {
        return Math.max((6 - s.length()), Math.max(s.length() - 20, 0));
    }

    private int tripleCheck(String s) {
        int lwr = 1;
        int upr = 1;
        int num = 1;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; ++i) {
            if (Character.isLowerCase(chars[i]))
                lwr = 0;
            else if (Character.isUpperCase(chars[i]))
                upr = 0;
            else if (Character.isDigit(chars[i]))
                num = 0;
            if (lwr + upr + num == 0)
                break;
        }
        return lwr + upr + num;
    }

    private int repeatCheck(String s) {
        int rep = 0;
        int sum = 0;
        int r1 = 0;
        int r2 = 0;

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length - 2; i += rep) {
            rep = 1;
            while ((i + rep) < chars.length && chars[i] == chars[i + rep]) {
                rep++;
            }
            if (rep < 3)
                continue;
            if (rep % 3 == 0)
                r1++;
            else if (rep % 3 == 1)
                r2++;
            sum += rep / 3;
        }

        int len = lengthCheck(s);
        int cor = Math.min(r1, len) + Math.min(r2, Math.max(len - r1, 0) / 2) + Math.max((len - r1 - 2 * r2) / 3, 0);

        return Math.max(sum - cor, 0);
    }
}