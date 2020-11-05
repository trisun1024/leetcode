import java.util.*;

// Convert String to Numbers
class LogSystem {

    List<Integer> logs;
    Map<Integer, String> logtime;

    public LogSystem() {
        logs = new ArrayList<>();
        logtime = new HashMap<>();
    }

    public void put(int id, String timestamp) {
        logtime.put(id, timestamp);
        if (logs.size() == 0) {
            logs.add(id);
            return;
        }
        int i = 0, j = logs.size();
        while (i < j) {
            int mid = (i + j) / 2;
            if (timestamp.compareTo(logtime.get(logs.get(mid))) <= 0) {
                j = mid;
            } else {
                i = mid + 1;
            }
        }
        logs.add(j, id);
    }

    public List<Integer> retrieve(String start, String end, String granularity) {
        List<Integer> list = new ArrayList<>();
        if (logs.size() == 0)
            return list;
        int l = getLen(granularity);
        start = start.substring(0, l);
        end = end.substring(0, l);
        int a, b;
        int i = 0, j = logs.size();
        while (i < j) {
            int mid = (i + j) / 2;
            if (start.compareTo(logtime.get(logs.get(mid)).substring(0, l)) <= 0) {
                j = mid;
            } else {
                i = mid + 1;
            }
        }
        a = j;
        i = -1;
        j = logs.size() - 1;
        while (i < j) {
            int mid = (i + j + 1) / 2;
            if (end.compareTo(logtime.get(logs.get(mid)).substring(0, l)) >= 0) {
                i = mid;
            } else {
                j = mid - 1;
            }
        }
        b = i;
        for (int k = a; k <= b; k++) {
            list.add(logs.get(k));
        }
        return list;
    }

    private int getLen(String g) {
        if (g.charAt(0) == 'Y')
            return 4;
        else if (g.charAt(0) == 'M' && g.charAt(1) == 'o')
            return 7;
        else if (g.charAt(0) == 'D')
            return 10;
        else if (g.charAt(0) == 'H')
            return 13;
        else if (g.charAt(0) == 'M')
            return 16;
        else
            return 19;
    }
}

/**
 * Your LogSystem object will be instantiated and called as such: LogSystem obj
 * = new LogSystem(); obj.put(id,timestamp); List<Integer> param_2 =
 * obj.retrieve(start,end,granularity);
 */