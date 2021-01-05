n,k = map(int,input().split())
a= list(map(int,input().split()))
b = list(map(int,input().split()))
answer=0
a.sort()
b.sort(reverse=True)

for i in range(k):
    if a[i]<b[i]:
        a[i],b[i] = b[i],a[i]
    else:
        break
for temp in a:
    answer+=temp
