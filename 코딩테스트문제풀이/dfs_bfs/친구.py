import sys

n = int(input())
answer=-1
graph = [[] for _ in range(n+1)]

for i in range(1,n+1):
    temp = list(map(str,sys.stdin.readline().rstrip()))
    for j in range(len(temp)):
        if temp[j]=='Y':
            graph[i].append(j+1)
def check_def(cur):
    cnt=0
    check = [False]*(n+1)
    check[cur]=True #이미 친구다.
    for temp in graph[cur]:
        check[temp]=True
        cnt+=1
    for i in range(1,n+1):
        if check[i]==False:
            for temp in graph[i]:
                if temp in graph[cur]:
                    cnt+=1
                    break
    global answer
    if cnt>answer:
        answer=cnt
for i in range(1,n+1):
    check_def(i)

print(answer)
