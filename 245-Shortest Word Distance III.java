import java.util.*;

class ShortestWordDistanceIII {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        List<Integer> listOne = new ArrayList<>();
        List<Integer> listTwo = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                listOne.add(i);
            }
            if (words[i].equals(word2)) {
                listTwo.add(i);
            }
        }
        int min = Integer.MAX_VALUE;
        if(word1.equals(word2)){
            for(int i = 1; i< listOne.size(); i++) {
                min = Math.min(min, listOne.get(i)-listOne.get(i-1));
            }
        } else {
            int i = 0;
            int j = 0;
            while(i < listOne.size() && j < listTwo.size()) {
                min = Math.min(min , Math.abs(listOne.get(i)-listTwo.get(j)));
                if(listOne.get(i) < listTwo.get(j)) {
                    i++;
                } else {
                    j++;
                }
            }
        }
        return min;
    }
}