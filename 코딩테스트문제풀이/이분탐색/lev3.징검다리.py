def solution(stones, k):
    answer=0
    minn = min(stones)
    maxx = max(stones)

    while(minn<=maxx):
        mid = (minn+maxx)//2
        cur = 0
        flag =True
        while(cur<len(stones)):
            if stones[cur]<mid:
                cnt =1
                cur +=1
                while(cur < len(stones) and stones[cur]>mid):
                    cur+=1
                    cnt+=1
                    if cnt>k:
                        flag = False
                        break
                if len(stones)-cur <k:
                    flag=True
                if flag==False:
                    break
            else:
                cur+=1
        if flag==False: #건너지못했다.
            maxx = mid-1
        else: #건넜다.
            answer=mid
            minn = mid+1
    return answer

print(solution([2,4,5,3,2,1,4,2,5,1],3))