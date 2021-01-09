
class CheckIfTwoStringArraysAreEquivalent {

    // String Build. Time = O(M+N); Space = O(M+N);
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder sb1 = new StringBuilder();
        for (String w : word1) {
            sb1.append(w);
        }
        StringBuilder sb2 = new StringBuilder();
        for (String w : word2) {
            sb2.append(w);
        }
        return sb1.toString().equals(sb2.toString());
    }

    // Time = O(N); Space = O(1);
    public boolean arrayStringsAreEqualI(String[] word1, String[] word2) {
        MyString a = new MyString(word1), b = new MyString(word2);
        Character i = a.nextChar(), j = b.nextChar();
        while (i != null && j != null) {
            if (i != j) {
                return false;
            }
            i = a.nextChar();
            j = b.nextChar();
        }
        return (i != null || j != null) ? false : true;
    }

    class MyString {
        final String[] word;
        int wordIndex = 0, charIndex = 0;

        MyString(String[] word) {
            this.word = word;
        }

        public Character nextChar() {
            if (wordIndex >= word.length) {
                return null;
            }
            if (charIndex >= word[wordIndex].length()) {
                charIndex = 0;
                wordIndex++;
                if (wordIndex >= word.length) {
                    return null;
                }
            }
            return word[wordIndex].charAt(charIndex++);
        }
    }
}