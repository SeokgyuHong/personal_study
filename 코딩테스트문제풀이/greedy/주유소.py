import sys
n= int(input())
cost = list(map(int,sys.stdin.readline().rstrip().split()))
city = list(map(int,sys.stdin.readline().rstrip().split()))

answer=0
cur=0
idx=0
while True:
    if cur==len(city)-1:
        break
    if cur==0:
        answer+=cost[cur]*city[cur]
        idx=cur
        cur+=1
    else:
        if city[cur]>city[idx]:
            answer+=city[idx]*cost[cur]
        else:
            answer+=cost[cur]*city[cur]
            idx=cur
        cur+=1

print(answer)

