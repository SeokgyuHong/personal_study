from itertools import permutations

def solution(numbers):
    answer = 0
    before = 0
    sort =[]
    for i in range(1,len(numbers)+1):
        per = permutations(numbers,i)
        per = list(per)
        #print(per)
        for j in range(len(per)):
            per[j] = int("".join(per[j]))
            sort.append(per[j])

    sort = set(sort)
    sort = list(sort)
    print(sort)
    for temp in sort:
        flag=False
        if temp==0 or temp==1:
            continue
        for i in range(2,temp):
            if temp%i ==0:
                flag=True
                break
        if flag==False:
            answer+=1

                
    return answer
