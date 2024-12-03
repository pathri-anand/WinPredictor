import java.util.Scanner;

public class RotateArrayKtimes {
    public static void main(String[] args) {
        Scanner s= new Scanner(System.in);
        int n=s.nextInt();
        int a[]=new int[n];
        for(int i=0;i<n;i++){
            a[i]=s.nextInt();
        }
        int k=s.nextInt();
        k=k%n;
        int i=0,j=n-1;
        while(i<=j){
            int tmp=a[i];
            a[i]=a[j];
            a[j]=tmp;
            i++;j--;
        }
        i=0;
        j=k-1;
        while(i<=j){
            int tmp=a[i];
            a[i]=a[j];
            a[j]=tmp;
            i++;j--;
        }
        i=k;
        j=n-1;
        while(i<=j){
            int tmp=a[i];
            a[i]=a[j];
            a[j]=tmp;
            i++;j--;
        }
        for(int p=0;p<n;p++){
            System.out.print(a[p]+" ");
        }
    }
}
