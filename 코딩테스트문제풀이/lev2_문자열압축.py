def solution(s):
    answer = 987654321
    cut =1
    #list_s = list(s)
    while((len(s)-cut)>=cut-1):
        temp=[]
        for i in range(0,len(s),cut):
            temp.append(s[i:i+cut])
            #print(list_s[i:i+cut])
        before=''
        cnt=0
        result=''
        for item in temp:
            if before=='':
                before=item
                cnt+=1
            elif before!='':
                if item==before:
                    cnt+=1
                    before=item
                else:
                    if cnt!=1:
                        result +=str(cnt)
                    result +=before
                    before=item
                    cnt=1
        if cnt!=1:
            result+=str(cnt)
        result+=before
        if len(result)<answer:
            answer=len(result)
        
        cut+=1


    
    return answer

print(solution('xababcdcdababcdcd'))