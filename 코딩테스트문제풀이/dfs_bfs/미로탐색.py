import sys
import heapq
n,m  = map(int,input().split())

graph=[]

dx = [-1,0,1,0]
dy = [0,1,0,-1]
for i in range(n):
    temp = list(map(int,sys.stdin.readline().rstrip()))
    graph.append(temp)
INF = int(1e9)
arr =[[INF]*m for _ in range(n)]
heap = []

heapq.heappush(heap,(1,0,0))
arr[0][0] = 1

while heap:
    cur,x,y = heapq.heappop(heap)
    for i in range(len(dx)):
        dir_x = x+dx[i]
        dir_y = y+dy[i]
        if 0<=dir_x<n and 0<=dir_y<m:
            if graph[dir_x][dir_y]==1:
                if arr[dir_x][dir_y]>cur+1:
                    arr[dir_x][dir_y]=cur+1
                    heapq.heappush(heap,(arr[dir_x][dir_y],dir_x,dir_y))

print(arr[n-1][m-1])