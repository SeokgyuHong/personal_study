import sys
import heapq
INF = int(1e9)
n,m,r = map(int,sys.stdin.readline().rstrip().split())
#0부터 시작
item = list(map(int,sys.stdin.readline().rstrip().split()))
graph= [[] for _ in range(n+1)]

answer=0
for _ in range(r):
    a,b,c = map(int,sys.stdin.readline().rstrip().split())
    graph[a].append((b,c)) #방향과 거리
    graph[b].append((a,c)) #거리


def find(start):
    distance = [INF] * (n + 1)
    distance[start] = 0
    heap = []
    heapq.heappush(heap,(0,start)) #시작위치의 거리가 0

    while heap:
        dist,cur = heapq.heappop(heap)
        for temp in graph[cur]:
            if distance[temp[0]]>dist+temp[1]:
                distance[temp[0]]=dist+temp[1]
                heapq.heappush(heap,(distance[temp[0]],temp[0]))
    global answer,b
    _max=0
    for i in range(1,n+1):
        if distance[i]<=m:
            _max += item[i-1]

    if _max>answer:
        answer=_max


for i in range(1,n+1):
    find(i)

print(answer)