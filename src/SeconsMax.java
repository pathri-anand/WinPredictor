import java.util.*;
public class SeconsMax {
    public static void main(String args[]){
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();
        int a[]= new int[n];
        for(int i=0;i<n;i++){
            a[i] = s.nextInt();
        }
        int fmax=Integer.MIN_VALUE;
        int smax=Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            fmax=Math.max(fmax,a[i]);
        }
        for(int i=0;i<n;i++){
            if(a[i]<fmax){
                smax=Math.max(smax,a[i]);
            }
        }
        System.out.println(smax);
    }
}