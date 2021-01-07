import sys
stack=[]
cnt=0
def solution(numbers, target):
    answer = 0
    size = len(numbers)
    dfs('+',size,target,numbers)
    dfs.pop()
    dfs('-',size,target,numbers)
    answer=cnt
    return answer

def dfs(ope,size,target,numbers):
    stack.append(ope)
    if len(stack)==size:
        temp=0
        for i in range(len(stack)):
            if stack[i]=='-':
                temp-=numbers[i]
            else:
                temp+=numbers[i]
        if temp==target:
            global cnt
            cnt+=1
        return

    dfs('+',size,target,numbers)
    stack.pop()
    dfs('-',size,target,numbers)
    stack.pop()

print(solution([1,1,1,1,1],3))