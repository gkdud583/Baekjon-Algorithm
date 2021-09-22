
import java.util.*;

class Input{
    int outputLength;
    int count;
    int clipboardLength;

    Input(int outputLength, int count, int clipboardLength){
        this.outputLength = outputLength;
        this.count = count;
        this.clipboardLength = clipboardLength;
    }

}

class Main
{
    
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        int S = sc.nextInt();

        System.out.println(solve(S));
    }
    static int solve(int S){
        Queue<Input> queue = new LinkedList<>();
        boolean check[][] = new boolean[S+1][S];
        
        //현재 출력 글자, 시도 횟수, 클립보드 저장 길이
        queue.add(new Input(1,0,0));
        //check[i][j] = 현재 글자 길이가 i이고 현재 클립보드 문자 길이가 j
        check[1][0] = true;

        while(!queue.isEmpty()){
            Input cur = queue.poll();

            //1.화면에 있는 이모티콘 모두 클립보드에 복사 - 화면에 있는 이모티콘 아무것도 없으면 x
            //2.클립보드에 있는 모든 이모티콘 화면에 붙여넣기 - 클립보드에 아무것도 없으면  x
            //3.화면에 있는 이모티콘 중 하나 삭제 - 화면에 있는 이모티콘 아무것도 없으면 x

            if(cur.outputLength != 0 && !check[cur.outputLength][cur.outputLength]){
                queue.add(new Input(cur.outputLength, cur.count+1, cur.outputLength));
                check[cur.outputLength][cur.outputLength] = true;
            }

            if(cur.clipboardLength != 0 && cur.outputLength + cur.clipboardLength <= S && !check[cur.outputLength + cur.clipboardLength][cur.clipboardLength]){
                if(cur.outputLength + cur.clipboardLength == S){
                    return cur.count + 1;
                }
                queue.add(new Input(cur.outputLength + cur.clipboardLength, cur.count+1, cur.clipboardLength));
                check[cur.outputLength + cur.clipboardLength][cur.clipboardLength] = true;
            }

            if(cur.outputLength != 0 && cur.outputLength-1 <= S && !check[cur.outputLength-1][cur.clipboardLength]){
                if(cur.outputLength + cur.clipboardLength == S){
                    return cur.count + 1;
                }

                queue.add(new Input(cur.outputLength-1, cur.count+1,  cur.clipboardLength));
                check[cur.outputLength-1][cur.clipboardLength] = true;
            }

        }
        return 0;


    }
}