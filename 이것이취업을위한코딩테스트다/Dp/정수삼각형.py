import sys
n = int(input())
arr=[]
for i in range(n):
    temp = list(map(int,sys.stdin.readline().rstrip().split()))
    arr.append(temp)


for i in range(1,len(arr)):
    for j in range(len(arr[i])):
        if j==0:
            arr[i][j] += arr[i-1][j]
        elif j==len(arr[i])-1:
            arr[i][j] += arr[i-1][j-1]
        else:
            arr[i][j] += max(arr[i-1][j],arr[i-1][j-1])

print(max(arr[n-1]))
