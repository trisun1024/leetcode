import java.util.*;

class BinaryWatch {

    // Time = O(N^2);
    public List<String> readBinaryWatch(int num) {
        List<String> possibleTimes = new ArrayList<>();
        for (int hour = 0; hour < 12; hour++) {
            for (int min = 0; min < 60; min++) {
                if (Integer.bitCount(hour) + Integer.bitCount(min) == num) {
                    StringBuilder sb = new StringBuilder();
                    if (min < 10) {
                        possibleTimes.add(sb.append(hour).append(":").append("0").append(min).toString());
                    } else {
                        possibleTimes.add(sb.append(hour).append(":").append(min).toString());
                    }
                }
            }
        }
        return possibleTimes;
    }
}