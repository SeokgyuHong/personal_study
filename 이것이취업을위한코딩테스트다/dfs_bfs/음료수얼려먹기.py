from collections import deque
import sys
n,m =4,5
arr =[]
dx =[-1,0,1,0]
dy =[0,1,0,-1]
for i in range(n):
    temp = list(map(int,sys.stdin.readline().rstrip().split()))
    arr.append(temp)
queue = deque()
cnt=0
for i in range(n):
    for j in range(m):
        if arr[i][j]==0:
            arr[i][j]=1
            queue.append((i,j))
            while queue:
                temp = queue.popleft()
                x,y = temp[0], temp[1]
                for k in range(4):
                    if x+dx[k] >=0 and x+dx[k]<n and y+dy[k]>=0 and y+dy[k]<m:
                        if arr[x+dx[k]][y+dy[k]]==0:
                            queue.append((x+dx[k],y+dy[k]))
                            arr[x+dx[k]][y+dy[k]]=1
            cnt+=1

print(cnt)



print(arr)
