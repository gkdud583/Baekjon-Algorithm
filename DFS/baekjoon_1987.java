import java.io.*;
import java.nio.file.attribute.DosFileAttributeView;
import java.util.*;


  

public class baekjoon_1987 {
    static final int dy[] = {-1,1,0,0};
    static final int dx[] = {0,0,-1,1};
    static int max = 1;
    static char map[][];
    public static void main(String[]args) throws Exception{
        Scanner sc = new Scanner(System.in);

        String input[] = sc.nextLine().split(" ");

        int R = Integer.parseInt(input[0]);
        int C = Integer.parseInt(input[1]);

        map = new char[R][C];

        for(int i=0; i<R; i++){
            input = sc.nextLine().split("");
            for(int j=0; j<C; j++){
                map[i][j] = input[j].charAt(0);
            }
        }

        boolean alphabet[] = new boolean[26];
        alphabet[map[0][0]-'A'] = true;

        dfs(R,C,0,0,1,alphabet);
        System.out.println(max);
    }
    static void dfs(int R, int C, int y, int x, int cnt, boolean alphabet[]){
        for(int i=0; i<4; i++){
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(ny < 0 || nx < 0 || ny >= R || nx >= C || alphabet[map[ny][nx]-'A'])
                continue;
            boolean tempAlphabet[] = copyArray(alphabet);
            tempAlphabet[map[ny][nx]-'A'] = true;
            max = Math.max(max, cnt+1);
            dfs(R,C,ny,nx,cnt+1,tempAlphabet);
            
        }

    }
    static boolean[] copyArray(boolean[]alphabet){
        boolean tempAlphabet[] = new boolean[alphabet.length];

        for(int i=0; i<alphabet.length; i++){
            tempAlphabet[i] = alphabet[i];
        }
        return tempAlphabet;
    }
}
