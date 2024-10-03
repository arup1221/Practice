import java.util.*;

public class JobScheduling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[][] jobs = new int[n][3];

        for (int i = 0; i < n; i++) {
            jobs[i][0] = scanner.nextInt(); // Start day
            jobs[i][1] = scanner.nextInt(); // End day
            jobs[i][2] = scanner.nextInt(); // Reward
        }
        // int ans = jobSchedulingStartTime(jobs);
       int ans = jobSchedulingEndTime(jobs);
        System.out.println(ans);
        scanner.close();
    }
// solving with endtime
    public static int jobSchedulingStartTime(int[][] jobs){

        Arrays.sort(jobs, (a,b) -> a[1]- b[1]);

        TreeMap<Integer, Integer> dp = new TreeMap<>();
        dp.put(0,0);
        for(int[] job:jobs){
            int val = job[2] + dp.floorEntry(job[0]).getValue();
            if(val > dp.lastEntry().getValue()){
                dp.put(job[1], val);
            }
        }
        return dp.lastEntry().getValue();
    }



    // when don't overlap && solving with startTime
    public static int jobSchedulingEndTime(int[][] a) {

        int n = a.length;
        Arrays.sort(a, (x, y) -> Integer.compare(x[0], y[0]));
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[n] = 0;

        for (int i = n - 1; i >= 0; i--) {
            int ans = Integer.MIN_VALUE;
            if (i + 1 < n) {
                int[] nxt = {a[i][1] + 1, -1, -1};
                int idx = binarySearch(a, nxt);
                if (i + 1 == idx) {
                    ans = dp[idx] + a[i][2];
                } else {
                    ans = Math.max(dp[i + 1], dp[idx] + a[i][2]);
                }
            }
            dp[i] = ans;
        }

        return dp[0];
    }

    public static int binarySearch(int[][] a, int[] key) {
        int low = 0;
        int high = a.length;

        while (low < high) {
            int mid = (low + high) / 2;
            if (a[mid][0] < key[0]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }
}




/* 
Sample Input
4
2 4 4
3 6 6
6 8 2
5 7 3


Sample Output
7
*/