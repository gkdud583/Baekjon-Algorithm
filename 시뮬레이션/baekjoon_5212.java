import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class Island {

  private static final int MIN_SINKABLE_SIZE = 3;
  private static final int DS = 4;
  private static final int DY[] = {-1, 1, 0, 0};
  private static final int DX[] = {0, 0, -1, 1};
  private int y;
  private int x;

  public Island(int y, int x) {
    this.y = y;
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public int getX() {
    return x;
  }

  public boolean isSinkable() {
    int count = 0;
    for (int i = 0; i < DS; i++) {
      int ny = this.y + DY[i];
      int nx = this.x + DX[i];

      if (isSea(ny, nx)) {
        count++;
      }
    }
    if (count < MIN_SINKABLE_SIZE) {
      return false;
    }
    return true;
  }

  private boolean isSea(int y, int x) {
    int R = baekjoon_5212.map.length;
    int C = baekjoon_5212.map[0].length;

    if (y < 0 || x < 0 || y >= R || x >= C) {
      return true;
    }
    if (baekjoon_5212.map[y][x] == 0) {
      return true;
    }
    return false;
  }

  public void sink(int[][] map) {
    map[this.y][this.x] = 0;
  }
}

public class baekjoon_5212 {

  public static Set<Island> islandSet = new HashSet<>();
  public static int map[][];

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    String[] input = sc.nextLine().split(" ");
    int R = Integer.parseInt(input[0]);
    int C = Integer.parseInt(input[1]);

    map = new int[R][C];

    for (int i = 0; i < R; i++) {
      input = sc.nextLine().split("");
      for (int j = 0; j < C; j++) {
        //0은 바다 1은 섬
        if (input[j].equals(".")) {
          map[i][j] = 0;
          continue;
        }
        map[i][j] = 1;
        islandSet.add(new Island(i, j));
      }
    }

    destroyIsland(R, C);
    findMap(R, C);
  }

  private static void findMap(int R, int C) {
    int minY = R, maxY = 0;
    int minX = C, maxX = 0;

    int islandCount = 0;
    for (int i = 0; i < R; i++) {
      for (int j = 0; j < C; j++) {
        if (map[i][j] == 1) {
          islandCount++;
          minY = Math.min(minY, i);
          minX = Math.min(minX, j);
          maxY = Math.max(maxY, i);
          maxX = Math.max(maxX, j);
        }
      }
    }

    if (islandCount == 0) {
      System.out.println("X");
      return;
    }

    for (int i = minY; i <= maxY; i++) {
      for (int j = minX; j <= maxX; j++) {
        int v = map[i][j];
        if (v == 0) {
          System.out.print(".");
          continue;
        }
        System.out.print("X");
      }
      System.out.println();
    }
  }

  private static void destroyIsland(int R, int C) {
    int tempMap[][] = new int[R][C];
    for (int i = 0; i < R; i++) {

      for (int j = 0; j < C; j++) {
        tempMap[i][j] = map[i][j];
      }
    }

    for (Island island : islandSet) {
      if (island.isSinkable()) {
        island.sink(tempMap);
        continue;
      }
    }
    map = tempMap;
  }
}
