import sys
import heapq
from itertools import combinations
sys.setrecursionlimit(100000)
n,m,k = map(int,sys.stdin.readline().rstrip().split())

heap =[]
graph=[[] for _ in range(n+1)]
money = list(map(int,sys.stdin.readline().rstrip().split())) #친구비용



start_list=[]
visited=[False]*(n+1)
answer=0
parent=[0]*(n+1)
for i in range(len(parent)):
    parent[i]=i

def find(a):
    if parent[a]!=a:
        parent[a] =find(parent[a])
    return parent[a]


for i in range(m):
    v,w=map(int,sys.stdin.readline().rstrip().split())
    v = find(v)
    w = find(w)
    if money[v-1]<money[w-1]:
        parent[w]=v
    else:
        parent[v]=w

for i in range(1,n+1):
    if parent[i]==i:
        answer+=money[i-1]

if answer>k:
    print("Oh no")
else:
    print(answer)
