import sys

n,m = map(int,input().split())
array = list(map(int,sys.stdin.readline().rstrip().split()))
# array.sort()
start =0
end = max(array)
max =0
answer =0
# def binarySearch(array,target,start,end):
#     if start>end:
#         return
#     mid = (start+end)//2
#
#     cnt=0
#     for i in range(mid,len(array)):
#         cnt+=(array[i]-array[mid])
#     if cnt==m:
#         return print(array[mid])
#     else:
#         if cnt<m:
#             binarySearch(array,target,start,mid-1)
#         else:
#             binarySearch(array,target,mid+1,end)
def binarySearch(array,target,start,end):
    if start>end:
        return
    mid = (start+end)//2 #자르는길이
    cnt=0
    for temp in array:
        if temp>mid:
            cnt+=(temp-mid)
    if target==cnt:
        return print(target)
    else:
        if cnt>target:
            binarySearch(array,target,mid+1,end)
        else:
            binarySearch(array,target,0,mid-1)


#binarySearch(array,m,0,n-1)

###While 문 사용
result = 0
while(start<=end):
    mid = (start+end)//2
    cnt=0
    for temp in array:
        if temp>mid:
            cnt+=(temp-mid)
    if cnt<m:
        end = mid-1
    else: #높이를 최대로 했을때니까 result를 계속 담아둔다.
        result = mid #떡을 최대한 덜 잘랏을때이므로 계속 저장
        start = mid+1

print(result)