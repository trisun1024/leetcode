
class DetermineIfStringHalvesAreAlike {

    // Count Vowels.
    public boolean halvesAreAlike(String s) {
        int n = s.length();

        String vowels = "aeiouAEIOU";
        String a = s.substring(0, n / 2);
        String b = s.substring(n / 2, n);

        int aVowelCount = 0;
        for (char c : a.toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                aVowelCount++;
            }
        }

        int bVowelCount = 0;
        for (char c : b.toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                bVowelCount++;
            }
        }

        return aVowelCount == bVowelCount;
    }

    // Count Vowels + In-place.
    public boolean halvesAreAlikeI(String s) {
        int n = s.length();

        String vowels = "aeiouAEIOU";

        int aVowelCount = 0;
        for (int i = 0; i < n / 2; i++) {
            if (vowels.indexOf(s.charAt(i)) != -1) {
                aVowelCount++;
            }
        }

        int bVowelCount = 0;
        for (int i = n / 2; i < n; i++) {
            if (vowels.indexOf(s.charAt(i)) != -1) {
                bVowelCount++;
            }
        }

        return aVowelCount == bVowelCount;
    }

    public boolean halvesAreAlikeII(String s) {
        int vowel = 0;
        int half = s.length() / 2;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (i < half && isVowel(c)) {
                vowel++;
            } else if (i >= half && isVowel(c)) {
                vowel--;
            }
        }

        return vowel == 0;
    }

    private boolean isVowel(char c) {
        c = Character.toLowerCase(c);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}