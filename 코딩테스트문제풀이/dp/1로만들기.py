n = int(input())


dp=[0]*(n+1)
if n>=4:
    dp[1] =0
    dp[2] =1
    dp[3] = 1
    for i in range(4,n+1):
        if i%3==0 and i%2==0:
            dp[i] = min(dp[i//3],dp[i//2],dp[i-1])+1
        elif i%3==0:
            dp[i] = min(dp[i//3],dp[i-1])+1
        elif i%2==0:
            dp[i]=min(dp[i//2],dp[i-1])+1
        else:
            dp[i]=dp[i-1]+1

if n>=4:
    print(dp[n])
elif n==3 or n==2:
    print(1)
else:
    print(0)