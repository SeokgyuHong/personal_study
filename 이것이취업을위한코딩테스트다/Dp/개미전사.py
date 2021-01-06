import sys
x = int(input())
arr = list(map(int,sys.stdin.readline().rstrip().split()))
dp = [0]*101
dp[1] = arr[0]
dp[2] = arr[1]
dp[3] = arr[2]
for i in range(4,x+1):
    dp[i] = max(dp[i-2],dp[i-3]) + arr[i-1]



print(max(dp))


