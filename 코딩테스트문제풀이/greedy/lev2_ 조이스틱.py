def solution(name):
    answer = 0
    start = ['A' for temp in name ]

    final = "".join(start)
    cur=0
    while(final!=name):
        cur_ask = ord(start[cur])
        final_ask = ord(name[cur])
        #조이스틱을 몇번 움직여야하는지
        if (final_ask-cur_ask>(cur_ask-65)+(90-final_ask+1)):
            start[cur]=chr(final_ask)
            answer+=(cur_ask-65)+(90-final_ask+1)
        else:
            start[cur]=chr(final_ask)
            answer+=(final_ask-cur_ask)
        #어디로 옮겨야하는지
        min_move=0
        check_min_val=99999
        move_cur =0
        for i in range(len(name)):
            if name[i]!=start[i]:
                temp1 = abs(i-cur)
                temp2 = (cur-0)+(len(name)-i)
                temp3 = (temp2) if temp2<temp1 else temp1
                if temp3<check_min_val:
                    check_min_val = temp3
                    move_cur = i
        cur = move_cur #
        answer+=check_min_val #최소이동횟수 추가
        cur_ask = ord(start[cur])
        final_ask=ord(name[cur])
        if (final_ask-cur_ask>(cur_ask-65)+(90-final_ask+1)):
            start[cur]=chr(final_ask)
            answer+=(cur_ask-65)+(90-final_ask+1)
        else:
            start[cur]=chr(final_ask)
            answer+=(final_ask-cur_ask)


        
        final = "".join(start)
    print(answer)

    return answer


print(solution("JAN"))