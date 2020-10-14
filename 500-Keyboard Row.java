import java.util.*;

class KeyboardRow {

    public String[] findWords(String[] words) {
        int l1 = toInt("QWERTYUIOP");
        int l2 = toInt("ASDFGHJKL");
        int l3 = toInt("ZXCVBNM");

        List<String> ret = new ArrayList<String>();

        for (String s : words) {
            int tmp = toInt(s);
            if (((tmp & l1) == tmp) || ((tmp & l2) == tmp) || ((tmp & l3) == tmp)) {
                ret.add(s);
            }
        }
        return ret.toArray(new String[0]);
    }

    public int toInt(String s) {
        int ret = 0;
        s = s.toLowerCase();
        char[] carray = s.toCharArray();
        for (int i = 0; i < carray.length; i++) {
            char c = carray[i];
            ret = ret | (1 << (c - 'a'));
        }
        return ret;
    }
}