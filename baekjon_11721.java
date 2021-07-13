import java.util.*;


class baekjon_11721{
    public static void main(String[]args){
         Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        for(int i=0; i<s.length(); i += 10){
            if(i+10 > s.length())
                System.out.println(s.substring(i));
            else
                System.out.println(s.substring(i,i+10));
        }
    }
}