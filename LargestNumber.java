import java.util.*;

public class LargestNumber {
   public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       int T = scanner.nextInt(); // number of test cases

       for (int t = 0; t < T; t++) {
           int N = scanner.nextInt(); // amount of money
           int[] X = new int[9]; // costs of each digit
           for (int i = 0; i < 9; i++) {
               X[i] = scanner.nextInt();
           }
           System.out.println(getLargestNumber(N, X));
       }
       scanner.close();
   }

   public static String getLargestNumber(int N, int[] X) {
       StringBuilder result = new StringBuilder();

       // Start from the highest cost digit
       for (int digit = 9; digit >= 1; digit--) {
           int count = Math.min(N / X[digit - 1], 9); // Calculate how many times the current digit can be bought
           if (count > 0) {
               result.append(String.valueOf(digit).repeat(count)); // Append the digit count times
               N -= count * X[digit - 1]; // Update the remaining money
           }
       }

       // If no number could be formed within the budget, return -1
       if (result.length() == 0) {
           return "-1";
       }
       return result.toString();
   }
}