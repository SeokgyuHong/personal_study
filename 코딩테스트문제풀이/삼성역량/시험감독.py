import sys
import math

N = int(input())
arr = list(map(int,sys.stdin.readline().rstrip().split())) #arr에 저장
check = [False]*N
main, sub = map(int,input().split()) #메인 체크인원
cnt=0
for i in range(len(arr)):
    arr[i]-=main
    if arr[i]<0:
        arr[i]=0
    cnt+=1
for i in range(len(arr)):
    if arr[i]<=0:
        continue
    #div = math.ceil(arr[i]/sub)
    div = arr[i]//sub
    temp = arr[i]%sub
    if temp!=0:
        div+=1
    arr[i]=div

for i in range(len(arr)):
    cnt+=arr[i]
print(cnt)
