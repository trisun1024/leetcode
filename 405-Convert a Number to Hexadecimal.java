import java.util.*;

class ConvertNumbertoHexadecimal {

    // Char Array.
    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        char[] map = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        int defaultValue = 0;
        if (num < 0) {
            num = -1 * num - 1;
            defaultValue = 15;
        }
        StringBuilder sb = new StringBuilder();
        convert(num, map, defaultValue, sb);
        return sb.reverse().toString();
    }

    private void convert(int num, char[] map, int defaultValue, StringBuilder sb) {
        while (num > 0) {
            int rest = defaultValue > 0 ? defaultValue - num % 16 : num % 16;
            sb.append(map[rest]);
            num /= 16;
        }
        if (defaultValue > 0) {
            while (sb.length() < 8) {
                sb.append(map[defaultValue]);
            }
        }
    }

    // Bit Manipulation
    public String toHexI(int num) {
        StringBuilder sb = new StringBuilder();
        Map<Integer, Character> map = new HashMap<>();
        map.put(10, 'a');
        map.put(11, 'b');
        map.put(12, 'c');
        map.put(13, 'd');
        map.put(14, 'e');
        map.put(15, 'f');

        if (num == 0)
            return "0";

        while (num != 0) {
            int digit = num & 15;

            if (digit >= 10) {
                sb.append(map.get(digit));
            } else {
                sb.append(digit);
            }

            num = num >>> 4;
        }
        return sb.reverse().toString();
    }
}