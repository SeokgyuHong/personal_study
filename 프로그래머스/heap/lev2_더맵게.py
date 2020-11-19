import heapq
def solution(scoville, K):
    answer = 0
    heap =[] #최소힙 
    for temp in scoville:
        heapq.heappush(heap,temp)
    while(1):

        flag = True
        for temp in heap:
            if temp<K:
                flag = False
                break
        if flag == True:
            break
        first = heapq.heappop(heap)
        if len(heap)==0:
            answer=-1
            break
        second = heapq.heappop(heap)
        if first+second*2==0:
            answer=-1
            break
        heapq.heappush(heap,(first+second*2))
        if len(heap)<2 and heap[0]<K:
            answer=-1
            break
        answer+=1
    return answer