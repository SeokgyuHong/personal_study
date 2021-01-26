import sys
n= int(input())
color=[]
for _ in range(n):
    temp = list(map(int,sys.stdin.readline().rstrip().split()))
    color.append(temp)

dp=[[0]*3 for _ in range(n)]

dp[0][0] = color[0][0]
dp[0][1] = color[0][1]
dp[0][2] = color[0][2]

for i in range(1,len(dp)):
    for j in range(len(dp[i])):
        min_=987654321
        for k in range(len(dp[i])):
            if k!=j:
                min_=min(min_,dp[i-1][k])
        dp[i][j]=min_+color[i][j]

print(min(dp[n-1]))