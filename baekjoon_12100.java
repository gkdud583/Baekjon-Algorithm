import java.util.*;

class baekjoon_12100{
    static int MAX = 0;
    static final int MAX_SIZE = 5;
    static ArrayList<Integer>[] makeListArray(int board[][], String d){
        int n = board.length;
        ArrayList<Integer> list[] = new ArrayList[n];
        for(int i=0; i<n; i++){
            list[i] = new ArrayList<>();
        }
        if(d.equals("0") || d.equals("1")){
            for(int c = 0; c < n; c++){
                for(int r = 0; r < n; r++){
                    if(board[r][c] != 0)
                        list[c].add(board[r][c]);
                }
            }
        }
        else{
            for(int r = 0; r < n; r++){
                for(int c = 0; c < n; c++){
                    if(board[r][c] != 0)
                        list[r].add(board[r][c]);
                }
            }
        }
        return list;
    }
    static void combineBlock(int [][]board,String d){
        ArrayList<Integer> list[] = makeListArray(board,d);
        int n = list.length;
        if(d.equals("0") || d.equals("1")){
            if(d.equals("0")){
                for(int c=0; c<n; c++){
                    int first = 0;
                    for(int size = list[c].size(); size > 0; size--){
                        int cur = list[c].get(first);
                    
                        if(size >= 2 && list[c].get(first + 1) == cur){
                            //맨앞과 그뒤의 수를 빼서 add해서 마지막에 넣음
                            int nxt = list[c].get(first + 1);
                            list[c].remove(first);
                            list[c].remove(first);
                            MAX = Math.max(MAX,cur + nxt);
                            list[c].add(first,cur + nxt);
                            size--;
                        }
                        first++;
                    }
                }
            }else{
                for(int c=0; c<n; c++){
                    int last = list[c].size()-1;
                    for(int size = list[c].size(); size > 0; size--){
                        int cur = list[c].get(last);
                        if(size >= 2 && list[c].get(last - 1) == cur){
                            int nxt = list[c].get(last - 1);
                            MAX = Math.max(MAX,cur + nxt);
                            list[c].remove(last);
                            list[c].remove(last - 1);
                            list[c].add(last-1,cur + nxt);
                            last--;
                            size--;
                        }
                        last--;
                    }
                }

            }
        }else{
            if(d.equals("2")){
                for(int r = 0; r < n; r++){
                    int first = 0;
                    for(int size = list[r].size(); size > 0; size--){
                        int cur = list[r].get(first);
                        if(size >= 2 && list[r].get(first + 1) == cur){
                            int nxt = list[r].get(first + 1);
                            MAX = Math.max(MAX,cur + nxt);
                            list[r].remove(first);
                            list[r].remove(first);
                            list[r].add(first,cur + nxt);
                            size--;

                        }
                        first++;
                        
                    }
                }
            }else{
                for(int r = 0; r < n; r++){
                    int last = list[r].size()-1;
                    for(int size = list[r].size(); size > 0; size--){
                        int cur = list[r].get(last);
                        if(size >= 2 && list[r].get(last - 1) == cur){
                            int nxt = list[r].get(last - 1);
                            MAX = Math.max(MAX,cur + nxt);
                            
                            list[r].remove(last);
                            list[r].remove(last - 1);
                            list[r].add(last-1,cur + nxt);
                            last -= 1;
                            size -= 1;
                        }
                        last -= 1;
                    }
                }

            }
        }
        moveBlock(list,board,d);
    }
    
    static void moveBlock(ArrayList<Integer> list[],int [][]board,String d){
        int n = board.length;
        int temp[][] = new int[n][n];

        if(d.equals("0") || d.equals("1")){
            if(d.equals("0")){
                for(int c = 0; c < n; c++){
                    for(int r = 0; r < list[c].size(); r++){
                        temp[r][c] = list[c].get(r);
                    }
                }
    
            }else{
                for(int c = 0; c < n; c++){
                    int r = n - 1;
                    for(int i = list[c].size()-1; i >= 0; i--){
                        temp[r][c] = list[c].get(i);
                        r--;
                    } 
                }
            }
        }else{
            if(d.equals("2")){
                for(int r = 0; r < n; r++){
                    for(int c = 0; c < list[r].size(); c++){
                        temp[r][c] = list[r].get(c);
                    }
                }
            }else{
                for(int r = 0; r < n; r++){
                    int c = n - 1;
                    for(int i = list[r].size()-1; i >= 0; i--){
                        temp[r][c] = list[r].get(i);
                        c--;
                    }
                }
            }
        }
        for(int i=0; i<n; i++){
            board[i] = (int[])temp[i].clone();
        }
    }
    static void getCom(ArrayList<String> comList,String order){
        if(order.length() >= MAX_SIZE)
        {
            comList.add(order);
            return;
        }
        for(int i=0; i<4; i++){
            //0:위, 1:아래, 2:왼쪽, 3:오른쪽
            getCom(comList,order+i);
        }
    }
    public static void main(String[]args){

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int board[][] = new int[n][n];
        int tempBoard[][] = new int[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                board[i][j] = sc.nextInt();
                tempBoard[i][j] = board[i][j];
                MAX = Math.max(MAX,board[i][j]);
            }
        }
        
        ArrayList<String> comList = new ArrayList<>();

        getCom(comList,"");


        for(int i=0; i<comList.size(); i++){
            if(comList.get(i).equals("21031")){
                int dd = 3;
            }
           
            String combintaion[] = comList.get(i).split("");
            
            for(int j=0; j<board.length; j++)
                tempBoard[j] = (int[])board[j].clone();
            for(int j=0; j<combintaion.length; j++){
                combineBlock(tempBoard,combintaion[j]);
            }
        }
        System.out.println(MAX);
    }
}
