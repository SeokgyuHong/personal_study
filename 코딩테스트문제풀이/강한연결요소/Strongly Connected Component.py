import sys
sys.setrecursionlimit(10000)
v,e = map(int,sys.stdin.readline().rstrip().split())
graph=[[] for _ in range(v+1)]
in_graph=[[] for _ in range(v+1)]

for i in range(e):
    a,b = map(int,sys.stdin.readline().rstrip().split())
    graph[a].append(b)
    in_graph[b].append(a)

stack=[]
visited=[False]*(v+1)

def dfs(start):
    for temp in graph[start]:
        if visited[temp]==False:
            visited[temp]=True
            dfs(temp)
    stack.append(start)
for i in range(1,v+1):
    if visited[i]==False:
        visited[i]=True
        dfs(i)
visited=[False]*(v+1)
answer=[]
temp_answer=[]
def dfs2(start):
    for temp in in_graph[start]:
        if visited[temp]==False:
            visited[temp]=True
            dfs2(temp)
    temp_answer.append(start)

while stack:
    top = stack.pop()
    if visited[top]==False:
        visited[top]=True
        temp_answer=[]
        dfs2(top)
        temp_answer.sort()
        answer.append(temp_answer)
answer.sort(key=lambda x:x[0])
print(len(answer))
for i in range(len(answer)):
    for j in answer[i]:
        print(j,end=' ')
    print(-1)
