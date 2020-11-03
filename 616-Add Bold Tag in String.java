
class AddBoldTagInString {

    public String addBoldTagI(String s, String[] dict) {
        StringBuilder sb = new StringBuilder();
        int lastLog = 0;
        int end = -1;

        for (int start = 0; start < s.length(); start++) {
            for (String d : dict) {
                if (s.startsWith(d, start)) {
                    end = Math.max(end, start + d.length());
                }
            }
            if (start == end) {
                sb.append("<b>" + s.substring(lastLog, start) + "</b>");
            }
            if (start >= end) {
                sb.append(s.charAt(start));
                lastLog = start + 1;
            }
        }
        if (end >= s.length()) {
            sb.append("<b>" + s.substring(lastLog) + "</b>");
        }
        return sb.toString();
    }

    // Array. Time = O(N); Space = O(N);
    /*
     * iterate dic[] while(start>=0) boolean[] record idx of word in s as
     * true(start->end) start = s.indexof(dic,start) if(start<0) break; end = start
     * + dic.length
     * 
     * bold[i] &&(i< 1 || !bold[i-1]) append<b> bold[i] &&(i+1 >len || !bold[i+1])
     * append</b>
     */
    public String addBoldTag(String s, String[] dict) {
        // base case.
        if (s == null || dict == null) {
            return "";
        }
        boolean[] bold = new boolean[s.length()];
        for (String w : dict) {
            int start = 0;
            while (start <= s.length()) {
                start = s.indexOf(w, start);
                if (start < 0) {
                    break;
                }
                int end = start + w.length();
                for (int i = start; i < end; i++) {
                    bold[i] = true;
                }
                start++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bold.length; i++) {
            if (bold[i] && (i == 0 || !bold[i - 1])) {
                sb.append("<b>");
            }
            sb.append(s.charAt(i));
            if (bold[i] && (i == bold.length - 1 || !bold[i + 1])) {
                sb.append("</b>");
            }
        }

        return sb.toString();
    }
}