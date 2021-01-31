import sys

n = int(input())
arr = list(map(int,sys.stdin.readline().rstrip().split()))
stack=[]
answer=[-1]*len(arr)

stack.append(0)
cur=1
while stack and cur<n:
    while stack and arr[stack[-1]]<arr[cur]:
        answer[stack[-1]]=arr[cur]
        stack.pop()
    stack.append(cur)
    cur+=1

for temp in answer:
    print(temp,end=' ')
#
# for i in range(len(arr)):
#     if len(stack)==0:
#         stack.append((arr[i],i))
#     else:
#         if arr[i]<=stack[-1][0]:
#             stack.append((arr[i],i))
#         else:
#             while True:
#                 if len(stack)==0:
#                     break
#                 if stack[-1][0]>arr[i]:
#                     break
#                 else:
#                     a,b=stack.pop()
#                     answer[b]=arr[i]
#             stack.append((arr[i],i))
#
# for i in range(len(answer)-1):
#     print(answer[i],end=' ')
# print(answer[-1])

