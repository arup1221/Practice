import java.util.*;

public class Pratice {
    // public static void main(String[] args) {
    //     Scanner scanner = new Scanner(System.in);

    //     int n = scanner.nextInt();
    //     int[][] jobs = new int[n][3];

    //     for (int i = 0; i < n; i++) {
    //         jobs[i][0] = scanner.nextInt(); // Start day
    //         jobs[i][1] = scanner.nextInt(); // End day
    //         jobs[i][2] = scanner.nextInt(); // Reward
    //     }
    //     int ans = JobSchedulingWithStartTIme(jobs);
    //     System.out.println(ans);

    //     // int ans2 = JobSchedulingWithEndTIme(arr);
    //     // System.out.println(ans2);;
    //     scanner.close();
    // }

    // public static int JobSchedulingWithEndTIme(int[][] jobs) {
    //     // int n = startTime.length;
    //     // int[][] jobs = new int[n][3];
    //     // for(int i=0; i<n; i++){
    //     // jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
    //     // }
    //     Arrays.sort(jobs, (a, b) -> a[1] - b[1]);
    //     TreeMap<Integer, Integer> dp = new TreeMap<>();
    //     dp.put(0, 0);

    //     for (int[] job : jobs) {
    //         int val = job[2] + dp.floorEntry(job[0]).getValue();
    //         if (val > dp.lastEntry().getValue()) {
    //             dp.put(job[1], val);
    //         }
    //     }
    //     return dp.lastEntry().getValue();
    // }

    // public static int JobSchedulingWithStartTIme(int[][] jobs) {

    //     Arrays.sort(jobs, (a, b) -> Integer.compare(a[0], b[0]));
    //     int n = jobs.length;
    //     int[] dp = new int[n + 1];
    //     Arrays.fill(dp, Integer.MIN_VALUE);
    //     dp[n] = 0;

    //     for (int i = n - 1; i >= 0; i--) {
    //         int ans = Integer.MIN_VALUE;
    //         if (i + 1 < n) {
    //             int[] nxt = { jobs[i][1] + 1, -1, -1 };
    //             int indx = binarySearch(jobs, nxt);
    //             if (i + 1 == indx) {
    //                 ans = dp[indx] + jobs[i][2];
    //             } else {
    //                 ans = Math.max(dp[i + 1], dp[indx] + jobs[i][2]);
    //             }
    //         }
    //         dp[i] = ans;
    //     }
    //     return dp[0];
    // }

    // public static int binarySearch(int[][] arr, int[] key) {
    //     int low = 0;
    //     int high = arr.length;
    //     while (low < high) {
    //         int mid = (high + low) / 2;
    //         if (arr[mid][0] < key[0]) {
    //             low = mid + 1;
    //         } else {
    //             high = mid;
    //         }
    //     }

    //     return low;
    // }

    public static long dp[][][][];
    public static String digits;

    public static void main(String[] args) {
        Scanner in  = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        in.close();

        int ans = solve(a, b);
        System.out.println(ans);
    }
 public static int solve(int a, int b){
    long countB = count(b);
    long countA = (a>0) ? count(a-1) : 0;
    long ans = countB - countA;
    return (int)ans;
 }

 public static long count(long num){
    digits = String.valueOf(num);
    int sz = digits.length();

    dp = new long[sz+1][11][2][2];

    for(int index = sz; index >= 0; index--){
        for(int prev = 10; prev>=0; prev--){
            for(int tight = 1; tight >=0; tight--){
                for(int zero = 1; zero>=0; zero--){
                    if(index == sz){
                        dp[index][prev][tight][zero] = 1; // base case
                    }
                    else{
                        dp[index][prev][tight][zero] = 0;
                        if(tight == 1){
                            for(int dg =0; dg <= digits.charAt(index)-'0'; dg++){
                                if(zero == 0 || dg != prev){
                                    dp[index][prev][tight][zero] += dp[index+1][dg][dg == digits.charAt(index)-'0'? 1: 0][zero != 0 || dg != 0?1:0];
                                }
                            }
                        }
                        else{
                            for(int dg =0; dg <= 9; dg++){
                                if(zero == 0 || dg != prev){
                                    dp[index][prev][tight][zero] += dp[index+1][dg][0][zero != 0 || dg != 0?1:0];
                                }
                            }

                        }
                    }
                }
            }
        }
    }


    return dp[0][10][1][0];
 }

}
