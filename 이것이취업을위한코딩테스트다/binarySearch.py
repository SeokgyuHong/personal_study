import sys
def binary_search(array,target,start,end):
    if start>end:
        return None
    mid = (start+end)//2
    if target==array[mid]:
        return array[mid]
    else:
        if target<array[mid]:
            binary_search(array,target,start,mid-1)
        else:
            binary_search(array,target,mid+1,end)

n,target = map(int,sys.stdin.readline().rstrip().split())
array = list(map(int,sys.stdin.readline().rstrip().split()))

result = binary_search(array,target,0,n-1)

if result ==None:
    print("원소가 존재하지 않습니다.")
else:
    print(result+1)