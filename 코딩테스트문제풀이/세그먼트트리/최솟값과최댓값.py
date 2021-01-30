import sys
import math
arr=[]
n,m= map(int,sys.stdin.readline().rstrip().split())

size=math.ceil(math.log2(n))
tree= [0]*(2**(size+1))
tree2=[0]*(2**(size+1))
for _ in range(n):
    temp =int(sys.stdin.readline().rstrip())
    arr.append(temp)

def init(node,start,end):
    if start==end:
        tree[node]=arr[start]
        return tree[node]
    mid = (start+end)//2
    tree[node]=min(init(node*2,start,mid),init(node*2+1,mid+1,end))
    return tree[node]
def maxinit(node,start,end):
    if start==end:
        tree2[node]=arr[start]
        return tree2[node]
    mid = (start+end)//2
    tree2[node]= max(maxinit(node*2,start,mid),maxinit(node*2+1,mid+1,end))
    return tree2[node]
init(1,0,n-1)
maxinit(1,0,n-1)
def find(node,left,right,start,end):
    if left>end or right<start:
        return 1000000001
    if left<=start and end<=right:
        return tree[node]
    mid=(start+end)//2
    return min(find(node*2,left,right,start,mid),find(node*2+1,left,right,mid+1,end))
def max_find(node,left,right,start,end):
    if right<start or left>end:
        return 0
    if left<=start and end<=right:
        return tree2[node]
    mid=(start+end)//2
    return max(max_find(node*2,left,right,start,mid),max_find(node*2+1,left,right,mid+1,end))

for _ in  range(m):
    a,b = map(int,sys.stdin.readline().rstrip().split())
    print(find(1,a-1,b-1,0,n-1),max_find(1,a-1,b-1,0,n-1))