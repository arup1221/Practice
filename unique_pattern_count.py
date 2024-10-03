def count(num: int) -> int:
    digits = str(num)
    sz = len(digits)

    dp = [[[[0 for l in range(2)]  
            for k in range(2)]
           for j in range(11)] 
          for i in range(sz + 1)]
    
    for index in range(sz, -1, -1):
        for prev in range(10, -1, -1):
            for tight in range(1, -1, -1):
                for zero in range(1, -1, -1):
                    if index == sz:
                        dp[index][prev][tight][zero] = 1
                    else:
                        dp[index][prev][tight][zero] = 0
                        if tight:
                            for digit in range(0, ord(digits[index]) - ord('0') + 1):
                                if zero == 0 or digit != prev:
                                    dp[index][prev][tight][zero] += dp[index + 1][digit][digit == ord(digits[index]) - ord('0')][digit != 0 or zero]
                        else:
                            for digit in range(0, 10):
                                if zero == 0 or digit != prev:
                                    dp[index][prev][tight][zero] += dp[index + 1][digit][0][digit != 0 or zero]
    return dp[0][10][1][0]

def solve(a: int, b: int) -> int:
    return count(b) - (count(a - 1) if a - 1 >= 0 else 0)

def main():
    # Input prompt for user
    a, b = map(int, input("Enter two integers (a and b) separated by space: ").split())
    ans = solve(a, b)
    print(f"The count of unique-pattern numbers in the range [{a}, {b}] is: {ans}")

if __name__ == '__main__':
    main()
