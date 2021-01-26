import sys
import collections
n = int(input())
arr = list(map(int,sys.stdin.readline().rstrip().split()))
min_=987654321
max_=-987654321
temp= list(map(int,sys.stdin.readline().rstrip().split()))
ope=[]
#1은 더하기
#2는 빼기
#3은 곱
#4는 나누
for i in range(len(temp)):
    if temp[i]>0:
        for j in range(temp[i]):
            ope.append(i+1)
visited=[False]*(n-1)

def dfs(cur,idx):
    global max_,min_
    if idx==len(visited):
        if cur>max_:
            max_=cur
        if cur<min_:
            min_=cur
        return
    next=0
    for i in range(len(ope)):
        if visited[i]==False:
            visited[i]=True
            if ope[i]==1:
                next = cur+arr[idx+1]
            elif ope[i]==2:
                next=cur-arr[idx+1]
            elif ope[i]==3:
                next=cur*arr[idx+1]
            elif ope[i]==4:
                if cur<0:
                    next= -1*cur
                    next=-1*(next//arr[idx+1])
                else:
                    next=cur//arr[idx+1]
            dfs(next,idx+1)
            visited[i]=False

dfs(arr[0],0)

print(max_)
print(min_)