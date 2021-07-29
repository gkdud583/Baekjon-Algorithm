import java.util.*;


class baekjoon_10804{
    
    static void reverseCard(int a, int b, ArrayList<Integer> card_list){
        ArrayList<Integer> temp = (ArrayList<Integer>)card_list.clone();

        int e = b;
        for(int s=a; s<=b; s++){
            card_list.remove(s);
            card_list.add(s,temp.get(e--));
        }
    }
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);

        ArrayList<Integer> card_list = new ArrayList<>();

        for(int i=1; i<=20; i++)
            card_list.add(i);
        
        for(int i=0; i<10; i++){
            int a = sc.nextInt()-1;
            int b = sc.nextInt()-1;

            reverseCard(a,b,card_list);
        }

        for(int i=0; i<card_list.size(); i++){
            System.out.print(card_list.get(i)+" ");
        }
        
        
    }
}
