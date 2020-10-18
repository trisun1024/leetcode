
class DetectCapital {

    public boolean detectCapitalUse(String word) {
        int len = word.length();
        if (len <= 1) {
            return true;
        }
        if (Character.isUpperCase(word.charAt(0)) && Character.isUpperCase(word.charAt(1))) {
            for (int i = 2; i < len; i++) {
                if (Character.isLowerCase(word.charAt(i))) {
                    return false;
                }
            }
        } else {
            for (int i = 1; i < len; i++) {
                if (Character.isUpperCase(word.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean detectCapitalUseI(String word) {
        int len = word.length();
        if (len == 1) {
            return true;
        }
        // case 1. starting with upper case
        if (word.charAt(0) - 'a' < 0) {
            return checkIsAllUpperCase(word, 1, len - 1) || checkIsAllLowerCase(word, 1, len - 1);
        } else {
            // case 2. starting with lower case
            return checkIsAllLowerCase(word, 1, len - 1);
        }
    }

    private boolean checkIsAllUpperCase(String word, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (word.charAt(i) - 'a' >= 0) {
                return false;
            }
        }
        return true;
    }

    private boolean checkIsAllLowerCase(String word, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (word.charAt(i) - 'a' < 0) {
                return false;
            }
        }
        return true;
    }
}