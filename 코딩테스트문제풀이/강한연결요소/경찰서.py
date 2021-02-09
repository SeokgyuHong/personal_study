import sys
sys.setrecursionlimit(10000)
n = int(input())
cost = list(map(int,sys.stdin.readline().rstrip().split()))
graph=[[] for _ in range(n+1)]
in_graph = [[] for _ in range(n+1)]
stack=[]
for i in range(1,n+1):
    temp = list(map(int,sys.stdin.readline().rstrip()))
    for j in range(len(temp)):
        if temp[j]==1:
            graph[i].append(j+1)
            in_graph[j+1].append(i)

visited=[False]*(n+1)
def dfs(start):
    for temp in graph[start]:
        if visited[temp]==False:
            visited[temp]=True
            dfs(temp)
    stack.append(start)

for i in range(1,n+1):
    if visited[i]==False:
        visited[i]=True
        dfs(i)

visited=[False]*(n+1)
temp_answer=[]
def dfs2(start):
    for temp in in_graph[start]:
        if visited[temp]==False:
            visited[temp]=True
            dfs2(temp)
    temp_answer.append(start)
answer=[]
while stack:
    top = stack.pop()
    if visited[top]==False:
        visited[top]=True
        temp_answer=[]
        dfs2(top)
        answer.append(temp_answer)



min_cost=int(1e9)
final_answer=0
for i in range(len(answer)):
    for j in range(len(answer[i])):
        if cost[answer[i][j]-1]<min_cost:
            min_cost=cost[answer[i][j]-1]
    final_answer+=min_cost
    min_cost=int(1e9)

print(final_answer)