import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class OccurrenceOfEachElement {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = s.nextInt();
        }
        Map<Integer, Integer> hm = new LinkedHashMap<>();
        for (int i = 0; i < n; i++) {
            hm.put(a[i], hm.getOrDefault(a[i], 0) + 1);
        }
        for (Integer key : hm.keySet()) {
            System.out.println(key + " occurred " + hm.get(key) + " times");
        }
    }
}
