import sys
import heapq
n,m = map(int,sys.stdin.readline().rstrip().split())
INF = int(1e9)
distance =[[[INF for j in range(2)] for i in range(m)] for _ in range(n)]


graph=[]
dx=[-1,0,1,0]
dy =[0,1,0,-1]
for _ in range(n):
    temp = list(map(int,sys.stdin.readline().rstrip()))
    graph.append(temp)

heap=[]
heapq.heappush(heap,(0,0,0,0)) #비용, 엑스, 와이, 벽부순거
distance[0][0][0] =0
distance[0][0][1] =0
while heap:
    cost,x,y,check = heapq.heappop(heap) #비용, 엑스, 와이, 벽부순거
    for i in range(len(dx)):
        dir_x = x+dx[i]
        dir_y = y+dy[i]
        if 0<=dir_x<n and 0<=dir_y<m:
            if check==0: #나는아직 벽을 안부쉇기때문에
                if graph[dir_x][dir_y]==0: #만약 벽을부수적도 없고 지금도없ㅇ므ㅕㄴ
                    if distance[dir_x][dir_y][0]>cost+1:
                        distance[dir_x][dir_y][0]=cost+1
                        heapq.heappush(heap,(cost+1,dir_x,dir_y,0))
                elif graph[dir_x][dir_y]==1:
                    if distance[dir_x][dir_y][1]>cost+1:
                        distance[dir_x][dir_y][1]=cost+1
                        heapq.heappush(heap,(cost+1,dir_x,dir_y,1))
            if check==1:
                if graph[dir_x][dir_y]==0:
                    if distance[dir_x][dir_y][1]>cost+1:
                        distance[dir_x][dir_y][1]=cost+1
                        heapq.heappush(heap,(cost+1,dir_x,dir_y,1))

if distance[n-1][m-1][0]==INF and distance[n-1][m-1][1]==INF:
    print(-1)
else:
    print(min(distance[n-1][m-1])+1)

# while heap:
#     cost,x,y,cnt = heapq.heappop(heap)
#
#     for i in range(len(dx)):
#         dir_x = x+dx[i]
#         dir_y = y+dy[i]
#         if 0<=dir_x<n and 0<=dir_y<m:
#             if cnt==1:
#                 if graph[dir_x][dir_y]==0:
#                     if distance[dir_x][dir_y]>cost+1:
#                         distance[dir_x][dir_y]=cost+1
#                         heapq.heappush(heap,(cost+1,dir_x,dir_y,cnt))
#             if cnt==0:
#                 if graph[dir_x][dir_y]==0:
#                     if distance[dir_x][dir_y]>cost+1:
#                         distance[dir_x][dir_y]=cost+1
#                         heapq.heappush(heap,(cost+1,dir_x,dir_y,cnt))
#                 else:
#                     if distance[dir_x][dir_y]>cost+1:
#                         distance[dir_x][dir_y]=cost+1
#                         heapq.heappush(heap,(cost+1,dir_x,dir_y,cnt+1))
