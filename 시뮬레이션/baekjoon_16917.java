import java.util.*;



class baekjoon_16917{
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);  
        
        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();
        int X = sc.nextInt();
        int Y = sc.nextInt();

        int price = 0;
   
        
        if(A + B < C * 2){
            //각각 따로 구매하는게 더 싸다
            price = A * X + B * Y;

        }else{
            //같이 사는게 더 싸다
            int amount = Math.min(X,Y);
            price = amount*C*2;
            X -= amount;
            Y -= amount;
            if(X > 0){
                price += X * A > X * C * 2 ? X * C * 2 : X * A;
               
            }else if(Y > 0){
                price += Y * B > Y * C * 2 ? Y * C * 2 : Y * B;

            }
        }
        System.out.println(price);
    }
}

