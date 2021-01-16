import sys
import collections
n = int(input())
dx = [-1,0,1,0]
dy = [0,1,0,-1]
graph = [[]*n for _ in range(n)]
visited1 = [[False]*n for _ in range(n)]
visited2 = [[False]*n for _ in range(n)]



for i in range(n):
    temp = sys.stdin.readline().rstrip()
    for j in range(len(temp)):
        graph[i].append(temp[j])

queue = collections.deque()

cnt=0
for i in range(n):
    for j in range(n):
        if visited1[i][j]==False:
            queue.append((i,j))
            visited1[i][j] = True
            while queue:
                cur_x,cur_y = queue.popleft()
                color = graph[cur_x][cur_y] #현재색상

                for a in range(len(dx)):
                    x = cur_x+ dx[a]
                    y = cur_y+ dy[a]
                    if 0 <= x < n and 0 <= y < n and visited1[x][y]==False:
                        if graph[x][y] ==color:
                            visited1[x][y]=True
                            queue.append((x,y))
            cnt+=1

cnt2=0
for i in range(n):
    for j in range(n):
        if visited2[i][j]==False:
            queue.append((i,j))
            visited2[i][j] = True
            while queue:
                cur_x,cur_y = queue.popleft()
                color = graph[cur_x][cur_y] #현재색상
                for a in range(len(dx)):
                    x = cur_x+ dx[a]
                    y = cur_y+ dy[a]
                    if 0 <= x < n and 0 <= y < n and visited2[x][y]==False:
                        if color=='R' or color=='G':
                            if graph[x][y]=='R' or graph[x][y]=='G':
                                visited2[x][y]=True
                                queue.append((x,y))
                        else:
                            if graph[x][y]==color:
                                visited2[x][y]=True
                                queue.append((x,y))

            cnt2+=1



print(cnt,cnt2)