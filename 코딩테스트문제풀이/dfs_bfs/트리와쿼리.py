import sys
sys.setrecursionlimit(100000)
n,r,q = map(int,sys.stdin.readline().rstrip().split())

graph=[[] for _ in range(n+1)]
stack=[]
for i in range(1,n):
    a,b = map(int,sys.stdin.readline().rstrip().split())
    graph[a].append(b)
    graph[b].append(a)
root=[]
for _ in range(q):
    temp=int(input())
    root.append(temp)
dp= [1]*(n+1)
visited=[False]*(n+1)
def dfs(cur):
    if len(stack)==0:
        return
    x = stack.pop()
    for temp in graph[x]:
        if visited[temp]!=True:
            visited[temp]=True
            stack.append(temp)
            dfs(temp)
            dp[x]+=dp[temp]
stack.append(1)
visited[1]=True
dfs(1)
print(dp)
for temp in root:
    print(dp[temp])