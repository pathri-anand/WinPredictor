//move # tags to the front of String
import java.util.*;
public class moveHashesToFront {
    static String moveHashes(String str){
        String str1="";
        String str2="";
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='#'){
                str1+="#";
            }
            else{
                str2+=str.charAt(i);
            }
        }
        return str1+str2;
    }
    public static  void main(String args[]){
        Scanner s= new Scanner(System.in);
        String str= s.next();
        System.out.println(moveHashes(str));
    }
}
