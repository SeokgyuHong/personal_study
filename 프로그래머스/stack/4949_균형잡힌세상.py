import sys

flag = True
while(1):
    n = sys.stdin.readline().rstrip()
    #print(n)
    stack =[]
    flag = True
    if n=='.':
        break
    for i in n:
        if i=='[':
            stack.append('[')
        elif i=='(':
            stack.append('(')
        elif i==')':
            if len(stack)==0:
                print('no')
                flag=False
                break
            temp = stack.pop()
            if temp!='(':
                print('no')
                flag=False
                break
        elif i==']':
            if len(stack)==0:
                print('no')
                flag=False
                break            
            temp = stack.pop()
            if temp!='[':
                print('no')
                flag=False
                break
    if flag==True:
        if len(stack)==0:
            print('yes')
        else:
            print('no')

