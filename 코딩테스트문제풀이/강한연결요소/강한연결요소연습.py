import sys
v,e = map(int,sys.stdin.readline().rstrip().split())
graph=[[] for _ in range(v+1)]
in_graph=[[] for _ in range(v+1)]
for _ in range(e):
    a,b = map(int,sys.stdin.readline().rstrip().split())
    graph[a].append(b)
    in_graph[b].append(a)

stack = []
visited = [False]*(v+1)
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

visited= [False]*(v+1)
def dfs2(start):
    global temp_ans
    for temp in graph[start]:
        if visited[temp]==False:
            visited[temp]=True
            dfs2(temp)
    temp_ans.append(start)

answer=[]
temp_ans=[]
while stack:
    top = stack.pop()
    if visited[top]==False:
        visited[top]=True
        temp_ans=[]
        dfs2(top)
        answer.append(temp_ans)

print(answer)
