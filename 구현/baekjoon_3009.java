import java.util.*;

class baekjoon_3009{
   
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> x_list = new ArrayList<>();
        ArrayList<Integer> y_list = new ArrayList<>();
        
        for(int i=0; i<3; i++){
            int y = sc.nextInt();
            int x = sc.nextInt();
            if(y_list.contains(y))
                y_list.remove((Integer)y);
            else
                y_list.add(y);
            if(x_list.contains(x))
                x_list.remove((Integer)x);
            else
                x_list.add(x);

        }
        System.out.println(y_list.get(0)+" "+x_list.get(0));

    }
}