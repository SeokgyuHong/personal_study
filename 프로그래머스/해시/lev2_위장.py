def solution(clothes):
    answer = 0
    arr1 = []
    arr2 = []
    for i in range(len(clothes)):
        if clothes[i][1] in arr2:
            arr1[arr2.index(clothes[i][1])] += 1
        else:
            arr2.append(clothes[i][1])
            arr1.append(1)
    start=1
    for temp in arr1:
        if temp!=0:
            start*=(temp+1)
    answer=start-1
    return answer

#파이썬에는 from collections import Counter 의 카운터 함수가 있다. cnt = Counter([kind for name, kind in clothes])