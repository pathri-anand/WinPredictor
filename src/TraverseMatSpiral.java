import java.util.*;
public class TraverseMatSpiral {
    static void traverseMatSpiral(int r,int c,int mat[][]){
        int top=0,left=0,right=c-1,bottom=r-1;
        while(top<=bottom && left<=right){
            for(int i=left;i<=right;i++){
                System.out.print(mat[top][i]+" ");
            }
            top++;
            for(int i=top;i<=bottom;i++){
                System.out.print(mat[i][right]+" ");
            }
            right--;
            if(!(top<=bottom && left<=right)) return;;
            for(int i=right;i>=left;i--){
                System.out.print(mat[bottom][i]+" ");
            }
            bottom--;
            for(int i=bottom;i>=top;i--){
                System.out.print(mat[i][left]+" ");
            }
            left++;
        }
    }
    public static void main(String[] args) {
        Scanner s= new Scanner(System.in);
        int r=s.nextInt();
        int c=s.nextInt();
        int mat[][]=new int[r][c];
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                mat[i][j]=s.nextInt();
            }
        }
        traverseMatSpiral(r,c,mat);
    }
}
