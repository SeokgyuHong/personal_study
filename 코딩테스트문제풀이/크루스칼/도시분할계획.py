import sys
import heapq
v,e = map(int,sys.stdin.readline().rstrip().split())

parent = [0]*(v+1)

heap=[]
for  i in range(1,v+1):
    parent[i]=i

for _ in range(e):
    a,b,cost = map(int,sys.stdin.readline().rstrip().split())
    heapq.heappush(heap,(cost,a,b))

def find(x):
    if parent[x]!=x:
        parent[x] = find(parent[x])
    return parent[x]
def union(a,b):
    a = find(a)
    b = find(b)
    if a<b:
        parent[b]=a
    else:
        parent[a]=b
cnt=0
load=[]
while cnt<v-1:
    cost,a,b=heapq.heappop(heap)
    if find(a)!=find(b):
        union(a,b)
        cnt+=1
        load.append(cost)

answer=0
for i in range(len(load)-1):
    answer+=load[i]
print(answer)
