import java.util.*;

public class MinDiscount {
    public static void main(String[] args) {
        Scanner s= new Scanner(System.in);
        int n=s.nextInt();
        HashMap<String,Integer>  hm = new HashMap<>();
        for(int i=0;i<n;i++){
            String product=s.next();
            int price=s.nextInt();
            int discount=s.nextInt();
            int discAmnt=(price*discount)/100;
            hm.put(product,discAmnt);
        }
        int mindisc=Integer.MAX_VALUE;
        String ans="";
        for(String key: hm.keySet()){
            if(mindisc>hm.get(key)){
                ans=key;
                mindisc=hm.get(key);
            }
        }
        System.out.println(ans);
    }
}
