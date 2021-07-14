import java.util.*;

class baekjon_2920{
    static boolean is_descending(int []arr){
        //8 7 6 5 4 3 2 1
        int n = arr.length;
        for(int i=0; i<arr.length; i++){
            if(arr[i] != n--)
                return false;
        }
        return true;
    }
    static boolean is_ascending(int []arr){
        for(int i=0; i<arr.length; i++){
            if(arr[i] != i + 1){
                return false;
            }
        }
        return true;
    }
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        int arr[] = new int[8];
        for(int i=0; i<8; i++){
            arr[i] = sc.nextInt();
        }
        if(is_ascending(arr))
            System.out.println("ascending");
        else if(is_descending(arr))
            System.out.println("descending");
        else
            System.out.println("mixed");

    }
}