import java.util.Scanner;

public class DigitSumOFLowerUpperRange {
    public static void main(String[] args) {
        Scanner s= new Scanner(System.in);
        int lbound=s.nextInt();
        int upBound=s.nextInt();
        long digitSum=0;
        for(int i=lbound;i<=upBound;i++){
            int num=i;
            while(num>0){
                digitSum+=num%10;
                num=num/10;
            }
        }
        System.out.println(digitSum);
    }
}
