import extensions.NestedInteger;

class MiniParser {
    public int start = 0;

    public NestedInteger deserialize(String s) {
        char[] nest = s.toCharArray();
        if (nest[0] != '[')
            return new NestedInteger(Integer.valueOf(s));
        return getNest(nest);
    }

    public NestedInteger getNest(char[] nest) {
        NestedInteger result = new NestedInteger();
        int temp = 0, sign = 1;
        while (start < nest.length) {
            start++;
            if (nest[start] == '[')
                result.add(getNest(nest));
            else if (nest[start] == ',')
                continue;
            else if (nest[start] == ']')
                return result;
            else if (nest[start] == '-')
                sign = -1;
            else {
                temp = temp * 10 + nest[start] - '0';
                if (nest[start + 1] == ',' || nest[start + 1] == ']') {
                    result.add(new NestedInteger(temp * sign));
                    temp = 0;
                    sign = 1;
                }
            }
        }
        return null;
    }
}