import java.util.*;
import java.io.*;



class baekjoon_9202{
    static final int DY[] = {-1,1,0,0,-1,-1,1,1};
    static final int DX[] = {0,0,-1,1,-1,1,-1,1};
    static char map[][] = new char[4][4];
    static ArrayList<String> dictionary = new ArrayList<>();
    static StringBuffer ans = new StringBuffer();
    static boolean isFind = false;
    static String longestWord = "";
    static int score = 0;
    public static void main(String[]args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        
        int W = Integer.parseInt(br.readLine());
        
        for(int i=0; i<W; i++){
            dictionary.add(br.readLine());
        }

        br.readLine();
        int B = Integer.parseInt(br.readLine());

        for(int i=0; i<B; i++){
            for(int j=0; j<4; j++){
                String input[] = br.readLine().split("");
                for(int k=0; k<4; k++){
                    map[j][k] = input[k].charAt(0);
                }
            }
            if(i != B-1){
                br.readLine();
            }
            
            longestWord = "";
            score = 0;
            solve();

            


        }
        System.out.println(ans);

    }
    
    static void solve(){
        HashSet<String> set = new HashSet<>();
        for(int i=0; i<dictionary.size(); i++){ //사전 단어
            String dw = dictionary.get(i);
            isFind = false;
            for(int j=0; j<4; j++){ //행
                for(int k=0; k<4; k++){ //열
                    if(dw.charAt(0) == map[j][k]){
                        boolean check[][] = new boolean[4][4];
                        check[j][k] = true;
                        StringBuffer sb = new StringBuffer();
                        sb.append(map[j][k]);
                        dfs(j,k,sb,check,set,dw);

                        if(isFind){
                            if(longestWord.length() < dw.length())
                                longestWord = dw;
                            else if(longestWord.length() == dw.length())
                            {
                                if(longestWord.compareTo(dw) > 0){
                                    longestWord = dw;
                                }
                            }
                            score += getScore(dw); 
                            break;
                        }
   
                    }
                    
                }
                if(isFind)
                    break;
            }
        }
        ans.append(score).append(" ").append(longestWord).append(" ").append(set.size()).append("\n");


    }
    static void dfs(int y, int x, StringBuffer sb, boolean check[][], HashSet<String> set, String dw){
        
        // System.out.println(sb.toString());
        String word = sb.toString();
        if(word.equals(dw.substring(0, word.length()))){
            if(word.length() == dw.length()){
                isFind = true;
                set.add(word);
                return;
            }
            for(int j=0; j<8; j++){
                int ny = y + DY[j];
                int nx = x + DX[j];
    
                if(ny < 0 || nx < 0 || ny >= 4 || nx >= 4 || check[ny][nx])
                    continue;
                check[ny][nx] = true;
                StringBuffer tempSB = new StringBuffer(sb.toString());
                tempSB.append(map[ny][nx]);
                dfs(ny, nx, tempSB, check, set, dw);
                
                if(!isFind)
                    check[ny][nx] = false;
                else    
                    return;
    
            }
        }

    }
    static int getScore(String word){
        if(word.length() <= 2)
            return 0;
        else if(word.length() <= 4)
            return 1;
        else if(word.length() == 5)
            return 2;
        else if(word.length() == 6)
            return 3;
        else if(word.length() == 7)
            return 5;
        else
            return 11;
    }
 
}