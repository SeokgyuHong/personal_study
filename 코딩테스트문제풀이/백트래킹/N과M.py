import sys

n,m = map(int,input().split())
arr =[]

visited = [False]*(n+1)



def dfs(cnt):
    global m,arr
    if cnt==m:
        for temp in arr:
            print(temp,end=' ')
        print()
        return
    for i in range(1,n+1):
        if visited[i]!=True:
            visited[i]=True#방문으로변경
            arr.append(i)
            dfs(cnt+1)
            arr.pop()
            visited[i]=False


dfs(0)