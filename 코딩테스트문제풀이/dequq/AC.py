import sys
import collections

t = int(sys.stdin.readline().rstrip())

for _ in range(t):
    func = str(sys.stdin.readline().rstrip())
    n = int(sys.stdin.readline().rstrip())
    queue=collections.deque()
    temp = sys.stdin.readline().rstrip().replace("[","").replace("]","")
    temp =list(temp.split(","))
    for i in range(len(temp)):
        if temp[i]!="":
            queue.append(temp[i])
    flag=True
    check=0 #앞
    for i in range(len(func)):
        if func[i]=='R' and check==0:
            check=1
        elif func[i]=='R' and check==1:
            check=0
        elif func[i]=='D':
            if len(queue)==0:
                flag=False
                break
            if check==0:#앞에서버리면된다
                queue.popleft()
            else:
                queue.pop()
    if flag==False:
        print('error')
    else:
        if check==1:
            queue.reverse()
            # print("[",end="")
            # print(",".join(list(queue)),end="")
            # print("]")
        queue=list(queue)
        print("[",end="")
        print(",".join(queue),end="")
        print("]")

