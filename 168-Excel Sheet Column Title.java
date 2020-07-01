class ExcelSheetColumnTitle {

    // each match A-Z is 1-26 convert to A-Z is 0-25. Append all the possible results, and reverse the string.
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            int rem = (n - 1) % 26;
            sb.append((char) (rem + 'A'));
            n = (n - 1) / 26;
        }
        return sb.reverse().toString();
    }
}