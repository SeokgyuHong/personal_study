def solution(n):
    total =0
    for i in range(1,n+1):
        total+=i
           
    print(total)
    answer = [0 for i in range(0,total)]
    print(answer)
    val =1 #들어갈 값
    cur =0 #현재위치
    term = 0 #간격
    flag = True
    while(1):
        if val >total:
            break
        while(1): #아랫 채우기

            if (cur+term)>=total: #배열 범위를 넘어서면 break;
                break
            if answer[cur+term]==0:
                answer[cur+term]=val
                val+=1
                cur = cur+term
                term+=1
            else:
                break
        cur = cur+1
        while(1):
            
            if (cur)>=total:
                cur-=1
                break
            if answer[cur]!=0:
                flag = False
                break
            else:
                answer[cur]=val
                cur+=1
                val+=1
        #term = term+1
        if flag== False:
            cur-=1
            flag = True
        while(1):
            if (cur-term)<0:
                break
            if answer[cur-term]==0:
                answer[cur-term]=val
                val+=1
                cur=cur-term
                term-=1
            else:
                break;

    return answer
