import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

class Cloud {

  private int y;
  private int x;

  public Cloud(int y, int x) {
    this.y = y;
    this.x = x;
  }
  
  public int getY() {
    return y;
  }

  public int getX() {
    return x;
  }

  public Cloud move(int d, int s) {
    int ny = y;
    int nx = x;
    for (int i = 0; i < s; i++) {
      ny += baekjoon_21610.DY[d];
      nx += baekjoon_21610.DX[d];

      ny = checkLocation(ny);
      nx = checkLocation(nx);
    }
    return new Cloud(ny, nx);
  }

  public void rain() {
    baekjoon_21610.map[this.y][this.x]++;
  }

  private int checkLocation(int loc) {
    int N = baekjoon_21610.map.length - 1;
    if (loc < 1) {
      return N;
    }
    if (loc > N) {
      return 1;
    }
    return loc;
  }

  @Override
  public boolean equals(Object o) {
    Cloud other = (Cloud) o;
    if (this.y == other.getY() && this.x == other.getX()) {
      return true;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(y, x);
  }
}

public class baekjoon_21610 {

  static final int DY[] = {0, 0, -1, -1, -1, 0, 1, 1, 1};
  static final int DX[] = {0, -1, -1, 0, 1, 1, 1, 0, -1};
  static int map[][];
  static Set<Cloud> cloudSet = new HashSet<>();

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();
    int M = sc.nextInt();

    map = new int[N + 1][N + 1];

    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        map[i][j] = sc.nextInt();
      }
    }
    init();

    for (int i = 0; i < M; i++) {
      int d = sc.nextInt();
      int s = sc.nextInt();

      HashSet<Cloud> tempCloudSet = new HashSet<>();
      for (Cloud cloud : cloudSet) {
        Cloud movedCloud = cloud.move(d, s);
        tempCloudSet.add(movedCloud);
        movedCloud.rain();
      }

      cloudSet = tempCloudSet;
      for (Cloud cloud : cloudSet) {
        copyWater(cloud.getY(), cloud.getX());
      }

      tempCloudSet = new HashSet<>();

      for (int j = 1; j <= N; j++) {
        for (int k = 1; k <= N; k++) {
          if (map[j][k] >= 2) {
            makeCloud(j, k, cloudSet, tempCloudSet);
          }
        }
      }
      cloudSet = tempCloudSet;
    }

    int sum = 0;
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        sum += map[i][j];
      }
    }
    System.out.println(sum);
  }

  private static void makeCloud(int y, int x, Set<Cloud> cloudSet, Set<Cloud> tempCloudSet) {
    Cloud cloud = new Cloud(y, x);
    if (cloudSet.contains(cloud)) {
      return;
    }
    map[y][x] -= 2;
    tempCloudSet.add(cloud);
  }

  private static void init() {
    int N = map.length - 1;
    cloudSet.add(new Cloud(N, 1));
    cloudSet.add(new Cloud(N, 2));
    cloudSet.add(new Cloud(N - 1, 1));
    cloudSet.add(new Cloud(N - 1, 2));
  }

  private static void copyWater(int y, int x) {
    map[y][x] += countDiagonal(y, x);
  }


  private static int countDiagonal(int y, int x) {
    int dy[] = {-1, -1, 1, 1};
    int dx[] = {-1, 1, -1, 1};

    int size = dy.length;
    int count = 0;
    for (int i = 0; i < size; i++) {
      int ny = y + dy[i];
      int nx = x + dx[i];

      if (isOutOfArray(ny, nx)) {
        continue;
      }
      if (baekjoon_21610.map[ny][nx] == 0) {
        continue;
      }
      count++;
    }
    return count;
  }

  private static boolean isOutOfArray(int y, int x) {
    int N = baekjoon_21610.map.length - 1;

    if (y < 1 || x < 1 || y > N || x > N) {
      return true;
    }
    return false;
  }
}
