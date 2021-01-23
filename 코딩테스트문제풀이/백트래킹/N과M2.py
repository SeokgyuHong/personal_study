import sys
arr =[]

n,m = map(int,input().split())

visited= [False]*(n+1)
def dfs(cnt):
    if cnt>=m:
        flag=True
        start=arr[0]
        for i in range(1,len(arr)):
            if arr[i]>start:
                start=arr[i]
            else:
                flag=False
                break
        if flag==True:
            for i in range(len(arr)):
                print(arr[i],end=' ')
            print()
        return
    for i in range(1,n+1):
        if visited[i]==False:
            visited[i]=True
            arr.append(i)
            dfs(cnt+1)
            arr.pop()
            visited[i]=False


dfs(0)