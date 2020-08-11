class ExcelSheetColumnNumber {

    // think the base is 26. each position means the power 
    public int titleToNumber(String s) {
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            num = num * 26 + (s.charAt(i) - 'A' + 1);
        }
        return num;
    }
}