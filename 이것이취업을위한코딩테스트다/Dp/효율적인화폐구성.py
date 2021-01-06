import sys

n,m = map(int,sys.stdin.readline().rstrip().split())
money = []
for i in range(n):
    temp = int(sys.stdin.readline().rstrip())
    money.append(temp)

dp = [10001]*(m+1)
dp[0] = 0

for i in range(n):
    for j in range(money[i],m+1):
        dp[j] = min(dp[j],dp[j-money[i]]+1)
if dp[m]==10001:
    print(-1)
else:
    print(dp[m])
