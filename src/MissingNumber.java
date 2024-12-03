import java.util.Scanner;

public class MissingNumber {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n= s.nextInt();
        int sum=0;

        for(int i=0;i<n;i++){
            sum+=s.nextInt();
        }
        System.out.println((n+1)*(n+2)/2-sum);
    }
}
