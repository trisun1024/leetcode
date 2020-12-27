import java.util.*;

class ReformatPhoneNumber {

    public String reformatNumber(String number) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= number.length(); i++) {
            if (sb.length() == 3 || i == number.length()) {
                if (sb.length() > 0)
                    res.add(new String(sb));
                sb = new StringBuilder();
            }
            if (i < number.length() && number.charAt(i) >= '0' && number.charAt(i) <= '9') {
                sb.append(number.charAt(i));
            }
        }
        System.out.println(res);
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < res.size(); i++) {
            if (i == res.size() - 2 && res.get(res.size() - 1).length() == 1) {
                String temp = res.get(i);
                ans.append(res.get(i).substring(0, res.get(i).length() - 1));
                ans.append('-');
                ans.append(res.get(i).charAt(res.get(i).length() - 1));
                ans.append(res.get(i + 1));
                break;
            }
            ans.append(res.get(i));
            if (i != res.size() - 1) {
                ans.append('-');
            }
        }

        return ans.toString();
    }
}