
def solution(numbers):
    answer=''
    tup=[]
    for i in numbers:
        div = i
        temp=0
        key = 0
        bef = -1
        while(div!=0):
            temp = div%10
            if bef!=-1 and temp<bef:
                key = (10-temp-0.1)
                break
            bef = temp
            key+=(10-temp)
            div = div // 10
        tup.append((i,key))
    
    tup.sort(key= lambda x: x[1])
    for i in tup:
        answer+=str(i[0])
    #print(answer)
        
    return answer

solution([3,30,34,5,9])