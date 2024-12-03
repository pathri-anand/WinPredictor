import java.util.*;

public class LongestSubStringWithoutRepeatingCharacter {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String str = s.next();
        int j = 0, maxlen = 0;
        HashMap<Character, Integer> hm = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            System.out.println(hm);
            if (hm.containsKey(currentChar)) {
                j = Math.max(j, hm.get(currentChar) + 1);
            }
            hm.put(currentChar, i);
            maxlen = Math.max(maxlen, i - j + 1);
        }
        System.out.println(maxlen);
    }
}
