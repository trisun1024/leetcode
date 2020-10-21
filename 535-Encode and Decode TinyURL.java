import java.util.*;

class EncodeAndDecodeTinyURL {

    public class Codec {
        String dict = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        HashMap<String, String> hm = new HashMap<>();
        Random rand = new Random();
        String key = getKey();

        // Encodes a URL to a shortened URL.
        public String encode(String longUrl) {
            while (hm.containsKey(key)) {
                key = getKey();
            }
            hm.put(key, longUrl);
            return key;
        }

        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            return hm.get(key);
        }

        public String getKey() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                sb.append(dict.charAt(rand.nextInt(62)));
            }
            return sb.toString();
        }
    }
}