array = [5,7,9,0,3,1,6,2,4,8]

def quickSort(array,start,end):
    if start>=end:
        return
    pivot = array[start]
    left = start+1
    right = end
    while left<=right:
        while left<=end and array[left]<=pivot: #왼쪽에는 큰데이터를 찾아가야함
            left+=1
        while right>start and array[right]>=pivot:
            right-=1

        if right<left: #엇갈렷을경우
            array[pivot],array[right] = array[right],array[pivot]
        else:
            array[left],array[right] = array[right],array[left]
    quickSort(array,start,right-1)
    quickSort(array,right+1,end)

quickSort(array,0,len(array)-1)

print(array)
    