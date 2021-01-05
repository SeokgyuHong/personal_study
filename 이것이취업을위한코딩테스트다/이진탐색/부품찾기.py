import sys

def binarySearch(array,target,start,end):
    if start>end:
        return print("No")

    mid = (start+end)//2
    if target == array[mid]:
        return print("Yes")
    else:
        if target<array[mid]:
            binarySearch(array,target,start,mid-1)
        else:
            binarySearch(array,target,mid+1,end)


n = int(input())
array = list(map(int,sys.stdin.readline().rstrip().split()))
m = int(input())
array2 = list(map(int,sys.stdin.readline().rstrip().split()))
array.sort()
for i in range(len(array2)):
    binarySearch(array,array2[i],0,n-1)

