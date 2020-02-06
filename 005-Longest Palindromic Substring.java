import java.util.*;
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1)
            return "";
        
        StringBuilder newStr = new StringBuilder();
        newStr.append('#');
        for (int i = 0; i < s.length(); i++) {
            newStr.append(s.charAt(i));
            newStr.append('#');
        }

        int rightIndex = 0;
        int centerIndex = 0;
        //求len中的最大
        int answer = 0;
        //answer最大时的中心
        int index = 0;
        int len[] = new int[newStr.length() ];
        for (int i = 1; i < newStr.length(); i++) {
            //当rightIndex > i，那么我们就在rightIndex - i 与len[2 * centerIndex - i](len[j])，取得最小值
            //因为当i + len[j] < rightIndex时，我们就把len[i]更新为len[j]
            //但是如果i + len[j] >= rightIndex时，我们暂且将len[i]定更新为rightIndex - i,超出的部分需要我们一个一个的匹配
            if (rightIndex > i) {
                len[i] = Math.min(rightIndex - i, len[2 * centerIndex - i]);
            } else {
                len[i] = 1;
            }
            //一个一个匹配
            //要么是超出的部分，要么是i > rightIndex
            while(i - len[i] >= 0 && i + len[i] < newStr.length() && newStr.charAt(i - len[i]) == newStr.charAt(i + len[i])) {
                len[i]++;
            }
            //当 len[i] + i > rightIndex,我们需要更新centerIndex和rightIndex
            //至于为什么会这样做，理解一下rightIndex和centerIndex的含义
            if(len[i] + i > rightIndex) {
                rightIndex = len[i] + i;
                centerIndex = i;
            }
            if(len[i] > answer) {
                answer = len[i];
                index = i;
            }
        }
        //截取字符串
        //为什么index - answer + 1,因为len[i] - 1才是原来的回文字符串长度，而answer记录的是len中最大值
        return newStr.substring(index - answer, index + answer - 1).replace("#", "");
    }
}