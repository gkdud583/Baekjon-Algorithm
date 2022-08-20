import java.util.Scanner;class baekjoon_1303 {  static boolean map[][];  static int DIRECTION_SIZE = 4;  static final int DY[] = {-1, 1, 0, 0};  static final int DX[] = {0, 0, -1, 1};  public static void main(String[] args) {    Scanner sc = new Scanner(System.in);    String input[] = sc.nextLine().split(" ");    int n = Integer.parseInt(input[0]);    int m = Integer.parseInt(input[1]);    map = new boolean[m][n];    for (int i = 0; i < m; i++) {      input = sc.nextLine().split("");      for (int j = 0; j < n; j++) {        String v = input[j];        if (v.equals("W")) {          map[i][j] = true;          continue;        }        map[i][j] = false;      }    }    boolean visited[][] = new boolean[m][n];    int white = 0;    int blue = 0;    for (int i = 0; i < m; i++) {      for (int j = 0; j < n; j++) {        if (!visited[i][j]) {          visited[i][j] = true;          boolean isWhite = map[i][j];          int v = solve(isWhite, i, j, n, m, visited) + 1;          if (isWhite) {            white += Math.pow(v, 2);            continue;          }          blue += Math.pow(v, 2);        }      }    }    System.out.println(white + " " + blue);  }  public static int solve(boolean isWhite, int y, int x, int n, int m, boolean visited[][]) {    int count = 0;    for (int i = 0; i < DIRECTION_SIZE; i++) {      int ny = y + DY[i];      int nx = x + DX[i];      if (isOutOfArray(ny, nx, n, m)) {        continue;      }      if (visited[ny][nx]) {        continue;      }      if (map[ny][nx] != isWhite) {        continue;      }      visited[ny][nx] = true;      count += solve(isWhite, ny, nx, n, m, visited) + 1;    }    return count;  }  public static boolean isOutOfArray(int y, int x, int n, int m) {    if (y < 0 || x < 0 || y >= m || x >= n) {      return true;    }    return false;  }}