
class LicenseKeyFormatting {

    // StringBuilder.
    public String licenseKeyFormatting(String S, int K) {
        StringBuilder sb = new StringBuilder();
        for (int i = S.length() - 1; i >= 0; i--) {
            if (S.charAt(i) != '-') {
                sb.append(sb.length() % (K + 1) == K ? '-' : "").append(S.charAt(i));
            }
        }
        return sb.reverse().toString().toUpperCase();
    }

    // Char Array.
    public String licenseKeyFormattingI(String S, int K) {
        char[] arr = S.toCharArray();
        int slow = 0;
        int fast = 0;
        while (fast < arr.length) {
            if (arr[fast] == '-') {
                fast++;
            } else {
                if (arr[fast] >= 'a' && arr[fast] <= 'z') {
                    arr[slow++] = (char) (arr[fast++] - 'a' + 'A');
                } else {
                    arr[slow++] = arr[fast++];
                }
            }
        }
        int len = slow + (slow - 1) / K;
        char[] res = new char[len];
        fast = len - 1;
        slow--;
        while (fast >= 0) {
            for (int i = 0; i < K && fast >= 0; i++) {
                res[fast--] = arr[slow--];
            }
            if (fast >= 0) {
                res[fast--] = '-';
            }
        }
        return new String(res);
    }
}