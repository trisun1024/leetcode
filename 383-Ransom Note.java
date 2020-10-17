import java.util.*;

class RansomNote {

    // Simulation
    public boolean canConstruct(String ransomNote, String magazine) {
        // For each character, c, in the ransom note.
        for (char c : ransomNote.toCharArray()) {
            // Find the index of the first occurrence of c in the magazine.
            int index = magazine.indexOf(c);
            // If there are none of c left in the String, return False.
            if (index == -1) {
                return false;
            }
            // Use substring to make a new string with the characters
            // before "index" (but not including), and the characters
            // after "index".
            magazine = magazine.substring(0, index) + magazine.substring(index + 1);
        }
        // If we got this far, we can successfully build the note.
        return true;
    }

    // Two HashMap

    // Takes a String, and returns a HashMap with counts of
    // each character.
    private Map<Character, Integer> makeCountsMap(String s) {
        Map<Character, Integer> counts = new HashMap<>();
        for (char c : s.toCharArray()) {
            int currentCount = counts.getOrDefault(c, 0);
            counts.put(c, currentCount + 1);
        }
        return counts;
    }

    public boolean canConstructII(String ransomNote, String magazine) {

        // Check for obvious fail case.
        if (ransomNote.length() > magazine.length()) {
            return false;
        }

        // Make the count maps.
        Map<Character, Integer> ransomNoteCounts = makeCountsMapI(ransomNote);
        Map<Character, Integer> magazineCounts = makeCountsMapI(magazine);

        // For each unique character, c, in the ransom note:
        for (char c : ransomNoteCounts.keySet()) {
            // Check that the count of char in the magazine is equal
            // or higher than the count in the ransom note.
            int countInMagazine = magazineCounts.getOrDefault(c, 0);
            int countInRansomNote = ransomNoteCounts.get(c);
            if (countInMagazine < countInRansomNote) {
                return false;
            }
        }

        // If we got this far, we can successfully build the note.
        return true;
    }
 
    
    // One HashMap

    // Takes a String, and returns a HashMap with counts of
    // each character.
    private Map<Character, Integer> makeCountsMapI(String s) {
        Map<Character, Integer> counts = new HashMap<>();
        for (char c : s.toCharArray()) {
            int currentCount = counts.getOrDefault(c, 0);
            counts.put(c, currentCount + 1);
        }
        return counts;
    }
    
    
    public boolean canConstructIII(String ransomNote, String magazine) {
        
        // Check for obvious fail case.
        if (ransomNote.length() > magazine.length()) {
            return false;
        }

        // Make a counts map for the magazine.
        Map<Character, Integer> magazineCounts = makeCountsMap(magazine);
        
        // For each character in the ransom note:
        for (char c : ransomNote.toCharArray()) {
            // Get the current count for c in the magazine.
            int countInMagazine = magazineCounts.getOrDefault(c, 0);
            // If there are none of c left, return false.
            if (countInMagazine == 0) {
                return false;
            }
            // Put the updated count for c back into magazineCounts.
            magazineCounts.put(c, countInMagazine - 1);
        }
        
        // If we got this far, we can successfully build the note.
        return true;
    }

    // Sort and Stack 
    
    // Please, if there's a nicer way of doing this, without getting tangled in
    // primitives vs Java's generics let me know in the article comments :-)
    private Stack<Character> sortedCharacterStack(String s) {
        char[] charArray = s.toCharArray();
        Arrays.sort(charArray);
        Stack<Character> stack = new Stack<>();
        for (int i = s.length() - 1; i >= 0; i--) {
            stack.push(charArray[i]);
        }
        return stack;
    }
    
    
    public boolean canConstructIV(String ransomNote, String magazine) {
        
        // Check for obvious fail case.
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        
        // Reverse sort the characters of the note and magazine, and then
        // put them into stacks.
        Stack<Character> magazineStack = sortedCharacterStack(magazine);
        Stack<Character> ransomNoteStack = sortedCharacterStack(ransomNote);
        
        // And now process the stacks, while both have letters remaining.
        while (!magazineStack.isEmpty() && !ransomNoteStack.isEmpty()) {
            // If the tops are the same, pop both because we have found a match.
            if (magazineStack.peek().equals(ransomNoteStack.peek())) {
                ransomNoteStack.pop();
                magazineStack.pop();
            } 
            // If magazine's top is earlier in the alphabet, we should remove that 
            // character of magazine as we definitely won't need that letter.
            else if (magazineStack.peek() < ransomNoteStack.peek()) {
                magazineStack.pop();
            }
            // Otherwise, it's impossible for top of ransomNote to be in magazine.
            else {
                return false;
            }
        }
                
        // Return true iff the entire ransomNote was built.
        return ransomNoteStack.isEmpty();
        
    }
}