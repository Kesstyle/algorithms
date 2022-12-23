package pl.kes.algorithms;

public class StudentAttendanceRecord2 {

  public static void main(String... args) {
    StudentAttendanceRecord2 studentAttendanceRecord2 = new StudentAttendanceRecord2();
    int n = 10101;
    System.out.println(studentAttendanceRecord2.checkRecord(n));
  }

  private int M = 1000000007;
  private long[] S;

  public int checkRecord(int n) {
    if (n == 1) {
      return 3;
    }
    if (n == 2) {
      return 8;
    }
    S = new long[n <= 5 ? 6 : n + 1];
    S[0] = 1;
    S[1] = 2;
    S[2] = 4;
    S[3] = 7;
    for (int i = 4; i <= n; i++) {
      S[i] = ((2 * S[i - 1]) % M + (M - S[i - 4])) % M;
    }

    for (int i = 1; i <= n; i++) {
      S[n] += (S[i - 1] * S[n - i]) % M;
    }

    return (int) (S[n] % M);
  }
}
