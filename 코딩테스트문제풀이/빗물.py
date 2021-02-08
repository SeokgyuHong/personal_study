import sys
from collections import deque
sys.setrecursionlimit(10000)
h,w = map(int,sys.stdin.readline().rstrip().split())
flag=False
input = list(map(int,sys.stdin.readline().rstrip().split()))
answer=0

for i in range(1,len(input)-1):
    left_max = max(input[:i])
    right_max = max(input[i+1:])
    min_value=min(left_max,right_max)
    if input[i]>=min_value:
        continue
    answer+=(min_value-input[i])
print(answer)
# heap=deque()
# for i in range(len(input)-1):
#     if input[i]>input[i+1]:
#         heap.append(i)
# visited = [False]*(w)
# answer=0
# while heap:
#     start = heap.popleft()
#     if visited[start]==True:
#         continue
#     end=0
#     for i in range(start+1,len(input)):
#         if input[i]>=input[start]:
#             end=i
#             for j in range(start+1,end):
#                 answer+=(input[start]-input[j])
#                 visited[j]=True
#             break
#     if end==0 and heap:
#         end = heap.popleft()
#         if input[start]<=input[end]:
#             temp = start
#         else:
#             temp = end
#
#
#         for i in range(start+1,end):
#             answer+=(input[temp]-input[i])
#             visited[i]=True
#     elif end==0 and len(heap)==0:
#         if start<len(input)-1 and input[-1]>input[-2]:
#             end=len(input)-1
#             if input[start]<=input[end]:
#                 temp = start
#             else:
#                 temp=end
#             for i in range(start+1,end):
#                 answer+=(input[temp]-input[i])
#
#

