import java.util.*;

class WordLadderII {

    // bfs + dfs
    List<List<String>> res = new ArrayList<>();
    List<String> list = new LinkedList<>();
    Map<String, List<String>> map = new HashMap<>();

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (wordList.size() == 0)
            return res;

        Queue<String> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        Set<String> unvisited = new HashSet<>(wordList);
        q.add(beginWord);
        unvisited.remove(beginWord);
        boolean found = false;

        // bfs
        while (!q.isEmpty()) {
            int size = q.size();
            for (int k = size - 1; k >= 0; k--) { // for each string in the queue
                String word = q.poll();
                for (int i = 0; i < word.length(); i++) {
                    char chs[] = word.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        chs[i] = c;
                        String newStr = new String(chs);
                        if (unvisited.contains(newStr)) {
                            if (!visited.contains(newStr)) {
                                visited.add(newStr);
                                q.add(newStr);
                            }
                            // build adjacent graph
                            if (map.containsKey(newStr))
                                map.get(newStr).add(word);
                            else {
                                List<String> l = new ArrayList<>();
                                l.add(word);
                                map.put(newStr, l);
                            }
                            if (newStr.equals(endWord))
                                found = true;
                        }
                    } // a-z
                } // first index-last index
            } // for each string
            if (found)
                break;
            unvisited.removeAll(visited);
            visited.clear();
        }

        // back trace based on the adjacent graph that we have built
        backTrace(endWord, beginWord);
        return res;
    }

    private void backTrace(String cur, String start) {
        if (cur.equals(start)) {
            list.add(0, start);
            res.add(new ArrayList<String>(list));
            list.remove(0); // backtrace by one step
            return;
        }
        list.add(0, cur);
        if (map.get(cur) != null) {
            for (String s : map.get(cur)) { // for each neighbors
                backTrace(s, start);
            }
        }
        list.remove(0);
    }

    // two set 
    public List<List<String>> findLaddersII(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Set<String> words = new HashSet<>(wordList);

        Set<String> start = new HashSet<>();
        start.add(beginWord);
        boolean found = false;

        // use hashMap to store all possible route ending at key
        Map<String, List<List<String>>> map = new HashMap<>();
        List<String> init = new ArrayList<>();
        init.add(beginWord);
        map.put(beginWord, new ArrayList<>());
        map.get(beginWord).add(init);

        while (!words.isEmpty() && !found && !start.isEmpty()) {
            // eliminate all previous layer words from dict
            words.removeAll(start);
            // use another set to record next layers' words
            Set<String> newStart = new HashSet<>();

            // iterate through all new starts
            for (String s : start) {
                char[] chs = s.toCharArray();
                List<List<String>> endPath = map.get(s);
                for (int i = 0; i < chs.length; i++) {
                    // randomly change a character
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        if (chs[i] == ch)
                            continue;
                        char tmp = chs[i];
                        chs[i] = ch;
                        String word = new String(chs);
                        // check if it is in the dict, if so new start found, extending all routes
                        if (words.contains(word)) {
                            newStart.add(word);
                            for (List<String> path : endPath) {
                                List<String> nextPath = new ArrayList<>(path);
                                nextPath.add(word);
                                map.putIfAbsent(word, new ArrayList<>());
                                map.get(word).add(nextPath);
                                if (word.equals(endWord)) {
                                    found = true;
                                    res.add(nextPath);
                                }
                            }

                        }
                        chs[i] = tmp;
                    }
                }
                map.remove(s);
            }
            // clear the previous layers words and update
            start.clear();
            start.addAll(newStart);

        }

        return res;
    }

}