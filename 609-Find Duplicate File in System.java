import java.util.*;

class FindDuplicateFileInSystem {

// HashMap. Time = O(N*X);
 public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap<>();
        for(String path: paths) {
            String[] values = path.split(" ");
            for(int i = 1; i < values.length; i++) {
                String[] nameCount = values[i].split("\\(");
                nameCount[1] = nameCount[1].replace(")", ""); 
                if(!map.containsKey(nameCount[1])) {
                    map.put(nameCount[1], new ArrayList<>());
                }
                map.get(nameCount[1]).add(values[0] + "/" + nameCount[0]);
            }
        }
        
        List<List<String>> res = new ArrayList<>();
        for(String key: map.keySet()) {
            if(map.get(key).size() > 1) {
                res.add(map.get(key));
            }
        }
        return res;
    }
}
