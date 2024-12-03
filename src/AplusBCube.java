import java.util.Scanner;

public class AplusBCube {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int a=s.nextInt();
        int b=s.nextInt();
        System.out.println((int)Math.pow((a+b),3));
    }
}
