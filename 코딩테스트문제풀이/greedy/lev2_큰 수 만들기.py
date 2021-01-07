def solution(number,k):
    answer =''
    size = len(number)-k
    print(size)
    temp = size-1
    start =0

    for i in range(0,size): #size만큼 반복
        max =0
        pos = 0
        for j in range(start,len(number)-temp):
            if int(number[j])>max:
                max = int(number[j])
                pos = j
        answer+=number[pos]
        temp-=1
        start = pos+1
                
            
        
        
        
    return answer



print(solution("1924",2))