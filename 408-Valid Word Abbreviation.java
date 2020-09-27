
class ValidWordAbbreviation {

    // Two Pointers.
    public boolean validWordAbbreviation(String word, String abbr) {
        int skip = 0; // number of character to skip before current index
        int j = 0; // current index of word
        for (int i = 0; i < abbr.length(); i++) {
            if (Character.isLetter(abbr.charAt(i))) {
                j += skip; // skipped current abbr
                if (j >= word.length() || abbr.charAt(i) != word.charAt(j)) {
                    return false;
                }
                j++; // move to next character
                skip = 0; // reset skip;
            } else {
                // no leading zeros in abbr numbers
                if (skip == 0 && abbr.charAt(i) == '0') {
                    return false;
                }
                skip = skip * 10 + (abbr.charAt(i) - '0');
            }
        }
        return skip == word.length() - j; // handling case of remaining letters are skipped.
    }
}