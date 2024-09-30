import java.util.Scanner;

public class DigitDp {
    private static long[][][][] dp; // DP array for long values
    private static String digits;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long a = scanner.nextLong();
        long b = scanner.nextLong();
        scanner.close();

        // Count unique-pattern numbers in the range [a, b]
        long ans = solve(a, b);
        System.out.println(ans);
    }

    private static long count(long num) {
        digits = String.valueOf(num);
        int sz = digits.length();

        // Initialize DP array
        dp = new long[sz + 1][11][2][2];

        // Fill DP array
        for (int index = sz; index >= 0; index--) {
            for (int prev = 10; prev >= 0; prev--) {
                for (int tight = 1; tight >= 0; tight--) {
                    for (int zero = 1; zero >= 0; zero--) {
                        if (index == sz) {
                            dp[index][prev][tight][zero] = 1; // Base case
                        } else {
                            dp[index][prev][tight][zero] = 0; // Initialize count to 0
                            if (tight == 1) {
                                for (int digit = 0; digit <= digits.charAt(index) - '0'; digit++) {
                                    if (zero == 0 || digit != prev) {
                                        dp[index][prev][tight][zero] += dp[index + 1][digit][digit == (digits.charAt(index) - '0') ? 1 : 0][digit != 0 || zero != 0 ? 1 : 0];
                                    }
                                }
                            } else {
                                for (int digit = 0; digit <= 9; digit++) {
                                    if (zero == 0 || digit != prev) {
                                        dp[index][prev][tight][zero] += dp[index + 1][digit][0][digit != 0 || zero != 0 ? 1 : 0];
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return dp[0][10][1][0]; // Return the final count
    }

    private static long solve(long a, long b) {
        long countB = count(b);
        long countA = (a > 0) ? count(a - 1) : 0; // Count for range [a, b]
        return countB - countA; // Return the difference
    }
}