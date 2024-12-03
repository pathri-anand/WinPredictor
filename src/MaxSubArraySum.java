import java.util.Scanner;

public class MaxSubArraySum {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n= s.nextInt();
        int a[]= new int[n];
        for(int i=0;i<n;i++){
            a[i]=s.nextInt();
        }
        int maxEnd=0,maxSofar=Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            maxEnd=maxEnd+a[i];
            if(maxEnd<a[i]){
                maxEnd=a[i];
            }
            if(maxSofar<maxEnd){
                maxSofar=maxEnd;
            }
        }
        System.out.println(maxSofar);
    }
}
