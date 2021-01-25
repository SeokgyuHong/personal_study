import sys
sys.setrecursionlimit(100000)
n =int(input())
people = list(map(int,sys.stdin.readline().rstrip().split()))

graph=[[] for _ in range(n+1)]
for _ in range(1,n):
    a,b= map(int,sys.stdin.readline().rstrip().split())
    graph[a].append(b)
    graph[b].append(a)

visited=[False]*(n+1)
dp=[[0]*2 for _ in range(n+1)]

# def dfs(cur):
#     for temp in graph[cur]:
#         if visited[temp]==False:
#             visited[temp]=True
#             dfs(temp)
#             if len(graph[cur])==3 and dp[cur][0]!=0:
#                 #현재 내 0값이 0이아니라는뜻은 이미 자식 우수랑 더한값이 존재한다
#                 dp[cur][0]+=max(dp[temp][0],dp[temp][1])
#                 dp[cur][1]+=dp[temp][0]
#             else:
#                 dp[cur][0]+=dp[temp][1]
#                 dp[cur][1]+=dp[temp][0]
#
#     dp[cur][0]+=0
#     dp[cur][1]+=people[cur-1] #내 우수값 더하기

def dfs(cur):
    for temp in graph[cur]:
        if visited[temp]==False:
            visited[temp]=True
            dfs(temp)
            dp[cur][1]+=dp[temp][0] #내가 우수면 자식은 무조건 안우수여야함
            dp[cur][0]+=max(dp[temp][0],dp[temp][1])
    dp[cur][1]+=people[cur-1]
visited[1]=True
dfs(1)
print(max(dp[1]))
