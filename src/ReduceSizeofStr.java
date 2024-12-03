//str=aaabbccdddd
//o/p=a3b2c2d4
import java.util.*;

public class ReduceSizeofStr {
    static String reducedStr(String str) {
        String x = "";
        int a[] = new int[26];
        for (int i = 0; i < str.length(); i++) {
            if (a[str.charAt(i) - 'a'] == 0) {
                x += str.charAt(i);
            }
            a[str.charAt(i) - 'a']++;
        }
        String ans = "";
        for (int i = 0; i < x.length(); i++) {
            ans += x.charAt(i);
            ans += String.valueOf(a[x.charAt(i) - 'a']);
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String str = s.next();
        System.out.println(reducedStr(str));
    }
}
