import java.util.*;

class baekjoon_6359 {
    public static void main(String[]args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        int escapeableCount = 0;
        for(int i = 0; i < T; i++) {
            int N = sc.nextInt();
            escapeableCount = N;
            boolean roomState[] = new boolean[N+1];
            Arrays.fill(roomState, true);
            for(int j = 1; j <= N; j++) {
                for(int k = 2; k <= N; k++) {
                    if (j % k == 0) {
                        escapeableCount = changeState(roomState, j, escapeableCount);
                    }
                }
            }
            System.out.println(escapeableCount);
        }

    }
    private static int changeState(boolean roomState[], int roomNum, int escapeableCount) {
        if (roomState[roomNum]) {
            roomState[roomNum] = false;
            return escapeableCount -= 1;
        }
        roomState[roomNum] = true;
        return escapeableCount += 1;
    }
}

