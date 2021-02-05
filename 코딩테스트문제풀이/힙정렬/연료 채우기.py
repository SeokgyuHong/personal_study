import sys
import heapq
n =int(input())

heap=[]
distance=[]
oil=[]
for i in range(n):
    a,b=map(int,sys.stdin.readline().rstrip().split())
    distance.append((a,b))

distance.sort()
end_point,cur_amount = map(int,sys.stdin.readline().rstrip().split())

cnt=0
for i in range(n):
    if distance[i][0]<=cur_amount:
        heapq.heappush(heap,(-distance[i][1],distance[i][0]))
    elif distance[i][0]>cur_amount:
        while heap:
            extra_oil,dist=heapq.heappop(heap)
            cur_amount+=(-extra_oil)
            cnt+=1
            if cur_amount>=distance[i][0]:
                break
        if cur_amount<distance[i][0]:
            print(-1)
            sys.exit()
        heapq.heappush(heap,(-distance[i][1],distance[i][0]))

if cur_amount>=end_point:
    pass
else:
    while heap:
        extra_oil,dist = heapq.heappop(heap)
        cur_amount+=(-extra_oil)
        cnt+=1
        if cur_amount>=distance[i][0]:
            break
    if cur_amount<distance[i][0]:
        print(-1)
        sys.exit()
if cur_amount<end_point:
    print(-1)
else:
    print(cnt)