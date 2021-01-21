import sys
import collections

r,c,m = map(int,sys.stdin.readline().rstrip().split())
shark=collections.deque()
start = 0 #낚시왕이 시작하는 위치
graph =[[0]*(c+1) for _ in range(r+1)]
for _ in range(m):
    a,b,s,d,z = map(int,sys.stdin.readline().rstrip().split())
    #graph[a][b]= 1#상어 존재
    graph[a][b]=(s,d,z)
    #shark.append((a,b,s,d,z))
    #graph[a][b]=1


answer=0
while True:
    start+=1
    if start>c:#종료조건
        break
    if m<=0: #남은 상어가 없어도
        break
    for i in range(1,r+1):
        for j in range(1,c+1):
            if graph[i][j]!=0:
                shark.append((i,j,graph[i][j][0],graph[i][j][1],graph[i][j][2]))

    for i in range(1,r+1): #해당열의 땅에서 가장 가까운 상어 잡기
        if graph[i][start]!=0:
            for j in range(len(shark)):
                if shark[j][0]==i and shark[j][1]==start:
                    answer+=shark[j][4]
                    del shark[j]
                    m-=1 #상어 갯수 삭제
                    graph[i][start]=0
                    break
            break

    cur_shark=0
    while cur_shark<m: #현재상어가 m보다 작을때
        cur_x,cur_y,speed,dir,size = shark.popleft()
        graph[cur_x][cur_y]=0 #상어위치 변경
        cnt=0
        dir_x=0
        dir_y=0
        while cnt < speed:
            if dir == 1:  # 위쪽방향
                if 1 <= (cur_x - 1):
                    cur_x -= 1
                else:
                    dir = 2
                    cur_x += 1
                cnt += 1
            elif dir == 3:  # 오른쪽방향
                if cur_y + 1 <= c:
                    cur_y += 1
                else:
                    dir = 4
                    cur_y -= 1
                cnt += 1
            elif dir == 2:  # 아래쪽방향
                if cur_x + 1 <= r:
                    cur_x += 1
                else:
                    dir = 1
                    cur_x -= 1
                cnt += 1
            elif dir == 4:  # 왼쪽방향
                if 1 <= cur_y - 1:
                    cur_y -= 1
                else:
                    dir = 3
                    cur_y += 1
                cnt += 1
        shark.append((cur_x,cur_y,speed,dir,size))
        cur_shark+=1
    while shark:
        cur_x,cur_y,speed,dir,size =shark.popleft()
        if graph[cur_x][cur_y]!=0: #이미 있다면
            if size>graph[cur_x][cur_y][2]:
                graph[cur_x][cur_y] = (speed,dir,size)
                m-=1
            else:
                m-=1
        else:
            graph[cur_x][cur_y]=(speed,dir,size)

print(answer)

