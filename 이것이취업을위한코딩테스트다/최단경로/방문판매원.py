import sys
import heapq

INF = int(1e9)

n,m = map(int,input().split())

graph = [[INF]*(n+1) for _ in range(n+1)]

for i in range(1,n+1):
    for j in range(1,n+1):
        if i==j:
            graph[i][j]==0


for i in range(m):
    a, b = map(int,input().split())
    graph[a][b] =1
    graph[b][a] = 1

x, k = map(int,input().split()) #k거쳐서 x

for m in range(1,n+1):
    for a in range(1,n+1):
        for b in range(1,n+1):
            graph[a][b] = min(graph[a][b],graph[a][m]+graph[m][b])

answer = graph[1][k] + graph[k][x]
if answer>INF:
    answer=-1
print(answer)


