import java.util.Scanner;

class Dice {

  // 1 동 2 서 3 남 4 북
  private static final int SIZE = 6;
  private static final int DX[] = {0, 1, -1, 0, 0};
  private static final int DY[] = {0, 0, 0, 1, -1};
  private static final int CLOCKWISE_DIRECTION[] = {0, 3, 4, 2, 1};
  private static final int COUNTERCLOCKWISE[] = {0, 4, 3, 1, 2};
  private static final int OPPOSITE_DIRECTION[] = {0, 2, 1, 4, 3};
  private int y = 1;
  private int x = 1;
  private int d = 1;
  private int top = 1; // 1
  private int bottom = 6; // 2
  private int left = 4; // 3
  private int right = 3; // 4
  private int front = 5; // 5
  private int back = 2; // 6

  public void roll(int R, int C) {
    int temp[] = new int[SIZE + 1];

    int ny = y + DY[d];
    int nx = x + DX[d];

    if (baekjoon_23288.isOutOfArray(ny, nx, R, C)) {
      d = OPPOSITE_DIRECTION[d];
      ny = y + DY[d];
      nx = x + DX[d];
    }

    y = ny;
    x = nx;

    switch (d) {
      case 1: {
        // 동쪽
        temp[1] = left;
        temp[2] = right;
        temp[3] = bottom;
        temp[4] = top;
        temp[5] = front;
        temp[6] = back;
        break;
      }
      case 2: {
        // 서쪽
        temp[1] = right;
        temp[2] = left;
        temp[3] = top;
        temp[4] = bottom;
        temp[5] = front;
        temp[6] = back;
        break;
      }
      case 3: {
        // 남쪽
        temp[1] = back;
        temp[2] = front;
        temp[3] = left;
        temp[4] = right;
        temp[5] = top;
        temp[6] = bottom;
        break;
      }
      case 4: {
        // 북쪽
        temp[1] = front;
        temp[2] = back;
        temp[3] = left;
        temp[4] = right;
        temp[5] = bottom;
        temp[6] = top;
        break;
      }
    }

    top = temp[1];
    bottom = temp[2];
    left = temp[3];
    right = temp[4];
    front = temp[5];
    back = temp[6];
  }

  public int getY() {
    return y;
  }

  public int getX() {
    return x;
  }

  public void changeDirection() {
    int cur = baekjoon_23288.map[this.y][this.x];

    if (bottom > cur) {
      d = CLOCKWISE_DIRECTION[d];
    } else if (bottom < cur) {
      d = COUNTERCLOCKWISE[d];
    }
  }
}

public class baekjoon_23288 {

  public static final int DY[] = {-1, 1, 0, 0};
  public static final int DX[] = {0, 0, -1, 1};
  public static int map[][];
  public static Dice dice = new Dice();

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();
    int M = sc.nextInt();
    int K = sc.nextInt();

    map = new int[N + 1][M + 1];

    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= M; j++) {
        map[i][j] = sc.nextInt();
      }
    }

    int sum = 0;
    for (int i = 0; i < K; i++) {
      if (i == 6) {
        int t = 3;
      }
      dice.roll(N, M);
      sum += getScore(dice.getY(), dice.getX(), N, M);
      dice.changeDirection();
    }

    System.out.println(sum);
  }

  public static int getScore(int y, int x, int R, int C) {
    boolean[][] visited = new boolean[R + 1][C + 1];
    visited[y][x] = true;
    int c = getCount(y, x, map[y][x], 1, R, C, visited);
    return c * map[y][x];
  }

  private static int getCount(int y, int x, int v, int c, int R, int C, boolean visited[][]) {
    for (int i = 0; i < 4; i++) {
      int ny = y + DY[i];
      int nx = x + DX[i];

      if (isOutOfArray(ny, nx, R, C)) {
        continue;
      }
      if (visited[ny][nx] || map[ny][nx] != v) {
        continue;
      }

      visited[ny][nx] = true;
      c = getCount(ny, nx, v, ++c, R, C, visited);
    }
    return c;
  }

  public static boolean isOutOfArray(int y, int x, int R, int C) {
    if (y < 1 || x < 1 || y > R || x > C) {
      return true;
    }
    return false;
  }
}
