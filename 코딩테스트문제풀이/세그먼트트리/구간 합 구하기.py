import sys
import math
sys.setrecursionlimit(100000)
n,m,k = map(int,sys.stdin.readline().rstrip().split())
arr=[]
size=math.ceil(math.log2(n))
size=(2**(size+1))
#print(size)
tree=[0]*3000000
answer=0
#print(tree)
for _ in range(n):
    temp = int(sys.stdin.readline().rstrip())
    arr.append(temp)


def init(node,start,end):
    if start==end:
        tree[node]=arr[start]
        return tree[node]

    else:
        mid = (start+end)//2
        tree[node]= init(node*2,start,mid)+init(node*2+1,mid+1,end)
        return tree[node]
init(1,0,n-1)
def update(node,start,end,index,diff,val):
    if not (start<=index<=end):
        return
    tree[node]+=diff
    if start==end:
        arr[start]=val
        return
    if start!=end:
        mid=(start+end)//2
        update(node*2,start,mid,index,diff,val)
        update(node*2+1,mid+1,end,index,diff,val)

def sum(node,start,end,left,right):
    if end < left or right < start:
        return 0
    if left <= start and end <= right:
        return tree[node]
    mid =(start+end)//2
    return sum(node*2,start,mid,left,right)+sum(node*2+1,mid+1,end,left,right)
        # temp = sum(node*2,start,mid,left,right)+sum(node*2+1,mid+1,end,left,right)
        # return temp

for _ in range(m+k):
    a,b,c = map(int,sys.stdin.readline().rstrip().split())
    if a==1:
        update(1,0,n-1,b-1,c-arr[b-1],c)
    if a==2:
        print(sum(1,0,n-1,b-1,c-1))



