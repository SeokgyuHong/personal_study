def solution(n):
    answer = 0
    arr = []
    arr.append(0)
    arr.append(1)
    arr.append(2)
    if n<3:
        answer=arr[n]
    else:
        minus=1
        for i in range(3,n+1):
            temp = (arr[i-2]*2)+arr[i-1]-arr[i-2]
            temp = temp%1000000007
            arr.append(temp)
        answer=arr[n]
    return answer