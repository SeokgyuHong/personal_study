
import sys
class Trie:
    def __init__(self):
        self.root={}
        self.endSymbol='*'

    def add(self,words):
        node = self.root
        for temp in words:
            if temp not in node:
                node[temp]={}
            node = node[temp]
        if node.keys():
            return False
        node[self.endSymbol]=words
        return True



t = int(input())
for _ in range(t):
    n = int(input())
    trie=Trie()
    flag=True
    words=[]
    flag=True
    for i in range(n):
        temp = sys.stdin.readline().rstrip()
        words.append(temp)
    words.sort(key=lambda x:-len(x))
    for temp in words:
        flag=trie.add(temp)
        if flag==False:
            print("NO")
            break
    if flag==True:
        print("YES")




