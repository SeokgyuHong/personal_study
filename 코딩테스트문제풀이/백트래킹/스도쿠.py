import sys
import collections

graph =[]

for _ in range(9):
    temp=list(map(int,sys.stdin.readline().rstrip().split()))
    graph.append(temp)
qeu =collections.deque()
for i in range(9):
    for j in range(9):
        if graph[i][j]==0:
            qeu.append((i,j))
cnt=len(qeu)
def find(x,y):
    check=[0]*10
    for i in range(9):
        check[graph[i][y]]+=1
        check[graph[x][i]]+=1
    check_x = (x//3)*3
    check_y = (y//3)*3
    for i in range(check_x,check_x+3):
        for j in range(check_y,check_y+3):
            check[graph[i][j]]+=1
    tmp=[]
    for i in range(1,10):
        if check[i]==0:
            tmp.append(i)
    if len(tmp)!=0:
        return tmp
    else:
        return 0


# def dfs(cur):
#     global qeu
#     if cur==cnt:
#         for i in range(9):
#             for j in range(9):
#                 print(graph[i][j],end=' ')
#             print()
#         sys.exit()
#     #x,y=qeu.popleft()
#     tmp = find(qeu[cur][0],qeu[cur][1])
#     if tmp==0:
#         return
#     for i in range(len(tmp)):
#         graph[qeu[cur][0]][qeu[cur][1]]=tmp[i]
#         dfs(cur+1)
#         graph[qeu[cur][0]][qeu[cur][1]]=0
def dfs(cur):
    global qeu
    if cur==cnt:
        for i in range(9):
            for j in range(9):
                print(graph[i][j],end=' ')
            print()
        sys.exit()
    x,y=qeu.popleft()
    # tmp = find(qeu[cur][0],qeu[cur][1])
    tmp = find(x,y)
    if tmp==0:
        qeu.appendleft((x,y))
        return
    for i in range(len(tmp)):
        graph[x][y]=tmp[i]
        dfs(cur+1)
        graph[x][y]=0
    if graph[x][y]==0:
        qeu.appendleft((x,y))

dfs(0)


