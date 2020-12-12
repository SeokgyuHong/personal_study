# def solution(number, k):
#     max = 0
#     start=0
#     cnt=0
#     answer=[]
#     n = len(number)
#     #number =list(number)
       
#     final = n-k #최종 도달해야하는 자릿수 
#     check=0
#     while(cnt!=final):
#         max=0
#         start=0
#         for i in range(len(number)):
#             if len(number)-i >=final-cnt: #자릿수보다 더 큰지 
#                 if max< int(number[i]):
#                     max=int(number[i])
#                     start=i
#             else:
#                 break
#         answer.append(number[start])
#         #answer+=number[start]

#         number =number[start+1:]
#         check = start+1
#         cnt+=1
#     answer = "".join(answer)           
#     return answer


# def solution(number, k):
#     max = 0
#     start=0
#     cnt=0
#     answer=[]
#     n = len(number)
#     #number =list(number)
       
#     final = n-k #최종 도달해야하는 자릿수 
#     check=0
#     while(cnt!=final):
#         max=0
#         start=0
#         for i in range(len(number)):
#             if len(number)-i >=final-cnt: #자릿수보다 더 큰지 
#                 if max< int(number[i]):
#                     max=int(number[i])
#                     start=i
#             else:
#                 break
#         answer.append(number[start])
#         #answer+=number[start]

#         number =number[start+1:]
#         check = start+1
#         cnt+=1
#     answer = "".join(answer)           
#     return answer


def solution(number, k):
    answer=''
    answer=[]
    #final = n-k  #구해야하는자릿수 
    cnt=0
    temp=0
    start=0
    for i in range(len(number)):
        if len(answer)==0:
            answer.append(number[i])
        else:
            temp=0
            flag = True
            while(1):
                if temp==len(answer):
                    break
                if len(answer)==0:
                    break
                if answer[temp]>=number[i]:
                    temp+=1

                elif answer[temp]<number[i]:
                    answer.pop(temp)
                    cnt+=1

            answer.append(number[i])
            start=i+1
            if cnt==k:
                break
            
            
        

    answer = "".join(answer)           
    return answer


solution("1231234",3)