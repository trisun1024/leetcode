class Solution {
    public String addBinary(String a, String b) {
        int i = a.length()-1;
        int j = b.length()-1;
        int pin = 0;
        StringBuilder sb = new StringBuilder();
        while(i>=0 || j>=0) {
            int temp = pin;
            if(i>=0) temp+=a.charAt(i--) - '0'; // - '0' convert to int
            if(j>=0) temp+=b.charAt(j--) - '0';
            sb.append(temp%2);
            pin = temp/2;
        }
        if(pin!=0) sb.append(pin);
        return sb.reverse().toString();
    }
}