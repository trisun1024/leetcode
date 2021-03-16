import java.util.*;

class EncodeAndDecodeTinyURL {

    public class Codec {
        private String dict = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        private Map<String, String> map = new HashMap<>();
        private Random rand = new Random();
        private String key = getKey();

        // Encodes a URL to a shortened URL.
        public String encode(String longUrl) {
            while (map.containsKey(key)) {
                key = getKey();
            }
            map.put(key, longUrl);
            return key;
        }

        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            return map.get(shortUrl);
        }

        private String getKey() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                sb.append(dict.charAt(rand.nextInt(62)));
            }
            return sb.toString();
        }
    }
}