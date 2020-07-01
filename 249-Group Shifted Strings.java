import java.util.*;

class GroupShiftedStrings {
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> result = new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strings) {
            int offset = str.charAt(0) - 'a';
            String key = "";
            for (int i = 0; i < str.length(); i++) {
                char c = (char) (str.charAt(i) - offset);
                if (c < 'a') {
                    c += 26;
                }
                key += c;
            }
            if (!map.containsKey(key)) {
                List<String> list = new ArrayList<String>();
                map.put(key, list);
            }
            map.get(key).add(str);
        }
        for (String key : map.keySet()) {
            List<String> list = map.get(key);
            Collections.sort(list);
            result.add(list);
        }
        return result;
    }

    public List<List<String>> groupStringsI(String[] strings) {
        Map<Integer, List<String>> map = new HashMap<>();
        for (String str : strings) {
            char[] arr = str.toCharArray();
            int key = 0;
            for (int i = 1; i < arr.length; i++) {
                int diff = (arr[i] - arr[i - 1] + 26) % 26 + 1;
                key = key * 27 + diff;
            }
            if (arr.length == 1) {
                key = -1;
            }

            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }

        List<List<String>> res = new ArrayList<>();
        for (Integer key : map.keySet()) {
            res.add(map.get(key));
        }

        return res;
    }
}